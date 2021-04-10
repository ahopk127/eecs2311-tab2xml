package tab2xml.model.guitar;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeSet;

import tab2xml.ImmutablePair;
import tab2xml.model.Staff;
import tab2xml.model.Bar;
import tab2xml.model.LineItem;
import tab2xml.model.Measure;
import tab2xml.model.Note;
import tab2xml.model.NoteType;
import tab2xml.model.Range;
import tab2xml.model.Score;

/**
 * A representation of a guitar staff in tablature.
 * 
 * @author amir
 */
public class GuitarStaff extends Staff<GuitarString> {
	private static final long serialVersionUID = 5418273130827075188L;

	/** A list of {@code GuitarString} objects. */
	private List<GuitarString> lines;
	/** A list of {@code GuitarNote} objects in this staff. */
	private TreeSet<GuitarNote> notes;

	/** Construct an empty guitar staff. */
	public GuitarStaff() {
		lines = new LinkedList<>();
		notes = new TreeSet<>();
	}

	/**
	 * Initialize the guitar staffs utilizing the {@code InitialStaffIterator}.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>This method only runs <b>ONLY ONCE</b> for each staff</li>
	 * </ul>
	 * </p>
	 * 
	 * @param staff the {@code GuitarStaff} to initialize
	 */
	public void init(GuitarStaff staff) {
		Iterator<LineItem> itr = new InitialStaffIterator(staff);
		while (itr.hasNext()) {
			notes.add(((GuitarNote) itr.next()));
		}
	}

	@Override
	public boolean add(GuitarString line) {
		if (line == null)
			return false;
		lines.add(line);
		return true;
	}

	@Override
	public boolean addLines(Collection<? extends GuitarString> lines) {
		if (lines == null)
			return false;
		for (GuitarString l : lines)
			if (!add(l))
				return false;
		return true;
	}

	@Override
	public List<GuitarString> getLines() {
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
		for (GuitarNote n : notes) {
			copy.add((GuitarNote) LineItem.deepClone(n));
		}
		return copy;
	}

	@Override
	public Iterator<Note> noteIterator() {
		return new NoteIterator(this);
	}

	@Override
	public Iterator<Measure<Note>> measureIterator() {
		return new MeasureIterator(this);
	}

	@Override
	public Iterator<GuitarString> iterator() {
		return new LineIterator(this);
	}

	@Override
	public String toString() {
		return "guitar staff";
	}

	/**
	 * Iterate over the notes in this staff.
	 */
	private static class MeasureIterator implements Iterator<Measure<Note>> {
		private TreeSet<Note> notes;
		private List<LinkedList<LineItem>> staff;
		private int startPosition = 0;
		private int stopPosition = 1;

		public MeasureIterator(GuitarStaff staff) {
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
			Range bottomRange = new Range(barsStart[barsStart.length - 1].rightPos(),
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
	public static class LineIterator implements Iterator<GuitarString> {
		List<GuitarString> lines;
		int index = 0;

		public LineIterator(GuitarStaff staff) {
			lines = staff.getLines();
		}

		@Override
		public boolean hasNext() {
			return index < lines.size();
		}

		@Override
		public GuitarString next() {
			return lines.get(index++);
		}
	}

	/**
	 * A custom {@code Iterator} implementation which iterates the {@code Note}
	 * objects in this guitar staff.
	 */
	private static class NoteIterator implements Iterator<Note> {
		private TreeSet<Note> notes;

		public NoteIterator(GuitarStaff staff) {
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
	 * A custom {@code Iterator} implementation which iterates the {@code LineItem}
	 * objects in this guitar staff.
	 */
	private static class InitialStaffIterator implements Iterator<LineItem> {
		private List<LinkedList<LineItem>> notes;
		private PriorityQueue<LineItem> pq;
		private Stack<Integer> repeatStack; 
		private final int X = 1;
		private int y;
		private int numStrings;
		private int lengths[];
		private int totalNotesInCurrMeasure;
		private int totalNotesInStaff;
		private boolean collecting;
		private boolean remaining;
		private boolean setFirstRepeatNote;
		private GuitarNote previousNote = null;

		/**
		 * Construct a guitar staff iterator.
		 * 
		 * @param staff the guitar staff to iterate
		 */
		public InitialStaffIterator(GuitarStaff staff) {
			notes = staff.toList();
			y = staff.size() - 1;
			numStrings = staff.size();
			lengths = new int[numStrings];
			Arrays.fill(lengths, -1);
			collecting = true;
			remaining = false;
			setFirstRepeatNote = false;
			totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
			totalNotesInStaff = staff.getNoteCount();
			pq = new PriorityQueue<>();
			repeatStack = new Stack<>();
		}

		/**
		 * @return true the specified guitar staff has notes remaining.
		 */
		@Override
		public boolean hasNext() {
			return totalNotesInStaff-- > 0 || totalNotesInCurrMeasure != 0;
		}

		/**
		 * Return the next chronological note within a specified staff (defined by a
		 * {@code LineItem} objects natural ordering.
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

				if (item.getClass() == GuitarNote.class) {
					GuitarNote note = (GuitarNote) item;
					note.setMeasure(Score.getAccumulateMeasure());
					pq.add(item);
					notes.get(y).remove(item);
					lengths[y]--;
					totalNotesInCurrMeasure--;

					if (note.isGrace()) {
						while (notes.get(y).get(X).getClass() != Bar.class
								&& ((GuitarNote) notes.get(y).get(X)).isGrace()) {
							GuitarNote gNote = ((GuitarNote) notes.get(y).get(X));
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

			GuitarNote note = (GuitarNote) pq.poll();
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
					repeatStack.push(c);
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
						double column = bars[i].getColumn();
						double position = bars[i].leftPos();
						GuitarNote newNote = new GuitarNote(bars[i].getTune().getTune(), fret);
						newNote.setColumn(column);
						newNote.setPosition(position);
						newNote.setLineNum(bars[i].getLineNum());
						newNote.setMeasure(Score.getAccumulateMeasure());
						newNote.setOctave(NoteType.getOctave(bars[i].getTune(), Integer.parseInt(fret)));

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
					note.setRepeatCount(repeatStack.pop());
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
			previousNote = (GuitarNote) LineItem.deepClone(note);

			if (pq.isEmpty())
				note.setLastInMeasure(true);

			return note;
		}

		/**
		 * Set the number of notes in each line of the given staff and return the total
		 * staffs in the measure.
		 */
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
					} else if (obj.getClass() == GuitarNote.class)
						len++;
				}
			}
			return Arrays.stream(lengths).sum();
		}
	}
}
