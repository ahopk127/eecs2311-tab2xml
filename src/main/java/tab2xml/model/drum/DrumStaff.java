package tab2xml.model.drum;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import tab2xml.model.Staff;
import tab2xml.ImmutablePair;
import tab2xml.model.Bar;
import tab2xml.model.LineItem;
import tab2xml.model.Measure;
import tab2xml.model.Note;
import tab2xml.model.Range;
import tab2xml.model.Score;

/**
 * A representation of a drum staff in tablature.
 * 
 * @author amir
 */
public class DrumStaff extends Staff<DrumLine> {
	private static final long serialVersionUID = 8730635738592350695L;
	private List<DrumLine> lines;
	private TreeSet<DrumNote> notes;

	public DrumStaff() {
		lines = new LinkedList<>();
		notes = new TreeSet<>();
	}

	public void init(DrumStaff staff) {
		Iterator<LineItem> itr = new InitialStaffIterator(staff);
		while (itr.hasNext()) {
			notes.add(((DrumNote) itr.next()));
		}
	}

	@Override
	public boolean add(DrumLine line) {
		if (line == null)
			return false;
		lines.add(line);
		return true;
	}

	@Override
	public boolean addLines(Collection<? extends DrumLine> lines) {
		for (DrumLine l : lines)
			if (!add(l))
				return false;
		return true;
	}

	@Override
	public List<DrumLine> getLines() {
		return lines;
	}

	@Override
	public int numberOfMeasures() {
		return lines.get(0).getNumMeasures();
	}

	@Override
	public String lineCount() {
		return String.valueOf(lines.size());
	}

	@Override
	public int size() {
		return lines.size();
	}

	@Override
	public int width() {
		return lines.get(0).width();
	}

	@Override
	public TreeSet<Note> getNotes() {
		TreeSet<Note> copy = new TreeSet<>();
		for (DrumNote n : notes) {
			copy.add((DrumNote) LineItem.deepClone(n));
		}
		return copy;
	}

	@Override
	public String toString() {
		return "drum staff";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<DrumLine> iterator() {
		return new LineIterator(this);
	}

	public Iterator<Measure<Note>> measureIterator() {
		return new MeasureIterator(this);
	}

	public Iterator<Note> noteIterator() {
		return new NoteIterator(this);
	}

	/**
	 * Iterate over the notes in this staff.
	 */
	private static class MeasureIterator implements Iterator<Measure<Note>> {
		private TreeSet<Note> notes;
		private List<LinkedList<LineItem>> staff;
		private int startPosition = 0;
		private int stopPosition = 1;

		public MeasureIterator(DrumStaff staff) {
			notes = staff.getNotes();
			this.staff = staff.toList();
		}

		@Override
		public boolean hasNext() {
			return notes != null && !notes.isEmpty() && notes.first().getMeasure() == Score.getAccumulateMeasureScore();
		}

		@Override
		public Measure<Note> next() {
			Bar[] barsStart = getFirstBarsAt(startPosition, staff);
			Bar[] barsEnd = getFirstBarsAt(stopPosition, staff);

			Range columnRange = new Range(barsStart[0].getColumn(), barsEnd[0].getColumn());
			Range upperRange = new Range(barsStart[0].rightPos(), barsEnd[0].leftPos());
			Range bottomRange = new Range(barsStart[barsEnd.length - 1].rightPos(),
					barsEnd[barsEnd.length - 1].leftPos());
			ImmutablePair<Range, Range> range = ImmutablePair.of(upperRange, bottomRange);

			Measure<Note> measure = new Measure<>(Score.getAccumulateMeasureScore(), range);
			measure.setColumnRange(columnRange);
			while (!notes.isEmpty() && notes.first().getMeasure() == Score.getAccumulateMeasureScore()) {
				measure.add(notes.pollFirst());
			}
			startPosition++;
			stopPosition++;
			Score.setAccumulateMeasureScore(Score.getAccumulateMeasureScore() + 1);
			return measure;
		}
	}

	/**
	 * Iterator over the lines in this staff.
	 * 
	 * @author amir
	 */
	public static class LineIterator implements Iterator<DrumLine> {
		List<DrumLine> lines;
		int index = 0;

		public LineIterator(DrumStaff staff) {
			lines = staff.getLines();
		}

		@Override
		public boolean hasNext() {
			return index < lines.size();
		}

		@Override
		public DrumLine next() {
			return lines.get(index++);
		}
	}

	/**
	 * Iterate over the notes in this staff.
	 */
	private static class NoteIterator implements Iterator<Note> {
		private TreeSet<Note> notes;

		public NoteIterator(DrumStaff staff) {
			notes = staff.getNotes();
		}

		@Override
		public boolean hasNext() {
			return !notes.isEmpty();
		}

		@Override
		public Note next() {
			return notes.pollFirst();
		}
	}

	/**
	 * A custom iterator for traversing a staff and its notes.
	 * 
	 * @author amir
	 *
	 */
	private static class InitialStaffIterator implements Iterator<LineItem> {
		private List<LinkedList<LineItem>> notes;
		private PriorityQueue<LineItem> pq;
		private final int X = 1;
		private int y;
		private int numLines;
		private int lengths[];
		private int totalNotesInCurrMeasure;
		private int totalNotesInStaff;
		private boolean collecting;
		private boolean remaining;
		private boolean setFirstRepeatNote;
		private DrumNote previousNote = null;

		/**
		 * Construct a staff iterator.
		 * 
		 * @param staff the staff to iterate
		 */
		public InitialStaffIterator(DrumStaff staff) {
			notes = staff.toList();
			y = staff.size() - 1;
			numLines = staff.size();
			lengths = new int[numLines];
			Arrays.fill(lengths, -1);
			collecting = true;
			remaining = false;
			setFirstRepeatNote = false;
			totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
			totalNotesInStaff = staff.getNoteCount();
			pq = new PriorityQueue<>();
		}

		/**
		 * @return true the specified staff has notes remaining.
		 */
		@Override
		public boolean hasNext() {
			return totalNotesInStaff-- > 0 || totalNotesInCurrMeasure != 0;
		}

		/**
		 * Return the next chronological note within a specified staff.
		 * 
		 * @return the next note within a specified staff.
		 */
		@Override
		public LineItem next() {
			// while collecting notes from current measure
			while ((pq.isEmpty() || collecting) && totalNotesInCurrMeasure != 0) {
				collecting = true;

				// we are going to next column
				if (y > notes.size() - 1)
					y = 0;

				if (lengths[y] == 0) {
					y++;
					continue;
				}

				LineItem item = notes.get(y).get(X);

				if (item.getClass() == DrumNote.class) {
					DrumNote note = (DrumNote) item;
					note.setMeasure(Score.getAccumulateMeasure());
					pq.add(item);
					notes.get(y).remove(item);
					lengths[y]--;
					totalNotesInCurrMeasure--;

					if (note.isGrace()) {
						while (notes.get(y).get(X).getClass() != Bar.class
								&& ((DrumNote) notes.get(y).get(X)).isGrace()) {
							DrumNote gNote = ((DrumNote) notes.get(y).get(X));
							gNote.setGrace(false);
							gNote.setMeasure(Score.getAccumulateMeasure());
							pq.add(gNote);
							notes.get(y).remove(gNote);
							lengths[y]--;
							totalNotesInCurrMeasure--;
						}
					}
				}
				y++;
			}
			// there are notes remaining after collection, and totalNotesInCurrMeasure == 0
			// by variant
			collecting = false;
			y = 0;

			DrumNote note = (DrumNote) pq.poll();
			if (note == null)
				return null;

			// by this point note is not null, we have either remaining notes or we are at
			// last pass
			if (totalNotesInCurrMeasure != 0)
				totalNotesInCurrMeasure--;

			if (setFirstRepeatNote) {
				note.setRepeatedStart(true);
				Bar[] endRepeats = getEndRepeatBars(notes);
				if (endRepeats != null) {
					int c = endRepeats[0].getRepeatCount();
					note.setRepeatCount((c == -1 ? 1 : c));
				}
				setFirstRepeatNote = false;
			}

			// if there are no notes in queue
			if (pq.isEmpty()) {
				Bar[] bars = getFirstBarsAt(X, notes);

				if (isFretEndBars(bars)) {
					for (int i = 0; i < bars.length; i++) {
						if (!bars[i].isFretEndBar() && !bars[i].isFretEndDoubleBar())
							continue;

						// extract the fret from the bar
						String value = bars[i].toString();
						String fret = value.substring(0, value.indexOf("|"));
						double position = bars[i].getColumn() + fret.length() - 1;
						DrumNote newNote = new DrumNote(bars[i].getDrumType());
						newNote.setColumn(position);
						newNote.setLineNum(bars[i].getLineNum());
						newNote.setMeasure(Score.getAccumulateMeasure());
						newNote.setOctave(bars[i].getTune().getOctave());

						// add new note to the queue and increase notes left count
						pq.add(newNote);
						totalNotesInStaff++;
						totalNotesInCurrMeasure++;
						remaining = true;
					}
					for (Bar bar : bars)
						bar.resetFretEndBars();
				} else
					remaining = false;

				if (isRepeatBegin(bars) && !remaining) {
					setFirstRepeatNote = true;
				}

				// last note passes
				if (isRepeatEnd(bars)) {
					note.setRepeatedStop(true);
				}

				if (isJustDoubleBars(bars)) {
					note.setDoubleBar(true);
				}

				if (totalNotesInCurrMeasure == 0 && !remaining) {
					notes.stream().filter(l -> l.size() > 0).forEach(l -> l.remove(X));
					totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
					Score.setAccumulateMeasure(Score.getAccumulateMeasure() + 1);
				}
			}

			if (previousNote != null && note.getColumn() == previousNote.getColumn()) {
				note.setChord(true);
			} else {
				note.setChord(false);
			}
			previousNote = (DrumNote) LineItem.deepClone(note);

			if (pq.isEmpty())
				note.setLastInMeasure(true);

			return note;
		}

		private int setNotesInCurrMeasure(int[] lengths) {
			int len = 0;
			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<LineItem> line = notes.get(i);
				for (int j = X; j < line.size(); j++) {
					Object obj = line.get(j);
					if (obj.getClass() == Bar.class) {
						lengths[i] = len;
						len = 0;
						break;
					} else if (obj.getClass() == DrumNote.class)
						len++;
				}
			}
			return Arrays.stream(lengths).sum();
		}
	}
}