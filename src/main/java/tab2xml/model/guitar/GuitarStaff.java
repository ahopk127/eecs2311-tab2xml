package tab2xml.model.guitar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import tab2xml.model.Staff;
import tab2xml.model.LineItem;

public class GuitarStaff extends Staff {
	private static final long serialVersionUID = 5418273130827075188L;
	public static final int DEFAULT_NUM_STRINGS = 6;

	public GuitarStaff() {
	}

	/**
	 * Return a custom iterator for traversing a staff at a specified index.
	 * 
	 * @return an iterator for a staff at specified <b>index</b>
	 */
	@Override
	public Iterator<LineItem> iterator() {
		return new StaffIterator(this);
	}

	/**
	 * A custom iterator for traversing a staff and its notes.
	 * 
	 * @author amir
	 */
	public class StaffIterator implements Iterator<LineItem> {
		private List<LinkedList<LineItem>> notes;
		private PriorityQueue<LineItem> pq;
		private final int X = 0;
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
		 * Construct a staff iterator.
		 * 
		 * @param staff the staff to iterate
		 */
		public StaffIterator(GuitarStaff staff) {
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

				if (y > notes.size() - 1)
					y = 0;

				if (lengths[y] == 0) {
					y++;
					continue;
				}

				LineItem item = notes.get(y).get(X);

				if (item.getClass() == GuitarNote.class) {
					GuitarNote note = (GuitarNote) item;
					note.setMeasure(accumulateMeasure);
					pq.add(item);
					notes.get(y).remove(item);
					lengths[y]--;
					totalNotesInCurrMeasure--;

					if (note.isGrace()) {
						while (notes.get(y).get(X).getClass() != Bar.class
								&& ((GuitarNote) notes.get(y).get(X)).isGrace()) {
							GuitarNote gNote = ((GuitarNote) notes.get(y).get(X));
							gNote.setGrace(false);
							gNote.setMeasure(accumulateMeasure);
							pq.add(gNote);
							notes.get(y).remove(gNote);
							lengths[y]--;
							totalNotesInCurrMeasure--;
						}
					}
				}
				y++;
			}
			// there are notes remaining after collection, and totalNotesInCurrMeasure == 0 by variant
			collecting = false;
			y = 0;

			GuitarNote note = (GuitarNote) pq.poll();
			if (note == null)
				return null;

			if (totalNotesInCurrMeasure != 0)
				totalNotesInCurrMeasure--;

			if (setFirstRepeatNote) {
				note.setRepeatedStart(true);
				Bar[] endRepeats = getEndRepeatBars();
				if (endRepeats != null) {
					int c = endRepeats[0].getRepeatCount();
					note.setRepeatCount((c == -1 ? 1 : c));
				}
				setFirstRepeatNote = false;
			}

			// if there are no notes in queue
			if (pq.isEmpty()) {
				Bar[] bars = getFirstBars();

				if (isFretEndBars(bars)) {
					for (int i = 0; i < bars.length; i++) {
						if (!bars[i].isFretEndBar() && !bars[i].isFretEndDoubleBar())
							continue;

						// extract the fret from the bar
						String value = bars[i].toString();
						String fret = value.substring(0, value.indexOf("|"));
						double position = bars[i].getPosition() + fret.length() - 1;
						GuitarNote newNote = new GuitarNote(bars[i].getTune(), fret);
						newNote.setPosition(position);
						newNote.setLine(bars[i].getLineNum());
						newNote.setMeasure(accumulateMeasure);
						newNote.setPosition(position);

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
					setAccumulateMeasure(accumulateMeasure + 1);
				}
			}

			if (previousNote != null && note.getPosition() == previousNote.getPosition()) {
				note.setChord(true);
				note.setDuration("1");
			} else {
				note.setChord(false);
				note.setDuration("1");
			}
			previousNote = (GuitarNote) LineItem.deepClone(note);

			return note;
		}

		private int setNotesInCurrMeasure(int[] lengths) {
			int len = 0;
			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<LineItem> line = notes.get(i);
				for (int j = 0; j < line.size(); j++) {
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

		private Bar[] getFirstBars() {
			Bar[] bars = new Bar[numStrings];
			for (int i = 0; i < notes.size(); i++) {
				for (int j = 0; j < notes.get(i).size(); j++) {
					LineItem item = notes.get(i).get(j);
					if (item != null && item.getClass() == Bar.class) {
						bars[i] = (Bar) item;
						break;
					}
				}
			}
			if (isEmpty(bars))
				return null;

			return bars;
		}

		private Bar[] getEndRepeatBars() {
			int column = 0;
			for (;;) {
				Bar[] bars = new Bar[numStrings];
				for (int i = 0; i < notes.size(); i++) {
					for (int j = column; j < notes.get(i).size(); j++) {
						LineItem item = notes.get(i).get(j);
						if (item != null && item.getClass() == Bar.class) {
							bars[i] = (Bar) item;
							break;
						}
					}
				}
				if (isEmpty(bars))
					return null;

				if (isRepeatEnd(bars))
					return bars;
				column++;
			}
		}

		private boolean isRepeatBegin(Bar[] bars) {
			if (bars == null || bars.length == 0)
				return false;
			return bars[2].isStartBar() && bars[3].isStartBar();
		}

		private boolean isRepeatEnd(Bar[] bars) {
			if (bars == null || bars.length == 0)
				return false;
			return bars[2].isStopBar() && bars[3].isStopBar();
		}

		private boolean isJustDoubleBars(Bar[] bars) {
			if (bars == null || bars.length == 0)
				return false;
			for (int i = 0; i < bars.length; i++)
				if (!bars[i].isDoubleBar())
					return false;
			return true;
		}

		private boolean isFretEndBars(Bar[] bars) {
			if (bars == null || bars.length == 0)
				return false;
			boolean repeatEnds = isRepeatEnd(bars);
			for (int i = 0; i < bars.length; i++)
				if ((bars[i].isFretEndBar() || bars[i].isFretEndDoubleBar()) && !repeatEnds)
					return true;
			return false;
		}

		private boolean isEmpty(Bar[] bars) {
			boolean isEmpty = true;
			for (Bar bar : bars) {
				if (bar != null) {
					isEmpty = false;
					break;
				}
			}
			return isEmpty;
		}
	}
}
