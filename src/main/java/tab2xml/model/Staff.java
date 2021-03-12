package tab2xml.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Staff extends StaffItem implements Iterable<StringItem> {
	private static final long serialVersionUID = 5418273130827075188L;
	private List<GuitarString> strings;
	private String upperBeat;
	private String lowerBeat;
	private static int accumulateMeasure = 0;

	public Staff() {
		strings = new ArrayList<>();
		GuitarString.setCount(0);
	}

	public void addString(GuitarString s) {
		strings.add(s);
	}

	public List<GuitarString> getStrings() {
		return strings;
	}

	public String getUpperBeat() {
		return upperBeat;
	}

	public void setUpperBeat(String upperBeat) {
		this.upperBeat = upperBeat;
	}

	public String getLowerBeat() {
		return lowerBeat;
	}

	public void setLowerBeat(String lowerBeat) {
		this.lowerBeat = lowerBeat;
	}

	/**
	 * Return the total measure count in this staff.
	 * 
	 * @return the number of measures in this staff
	 */
	public int numberOfMeasures() {
		return strings.get(0).getNumMeasures();
	}

	/**
	 * @return the number of strings in the staff as a string
	 */
	public String stringCount() {
		return Integer.toString(strings.size());
	}

	/**
	 * @return the number of strings in the staff
	 */
	public int size() {
		return strings.size();
	}

	/**
	 * @return the total number of notes in the staff
	 */
	public int getNoteCount() {
		int count = 0;
		for (GuitarString string : strings) {
			count += string.getNoteCount();
		}
		return count;
	}

	/**
	 * Extract notes from a staff at a specified index.
	 * 
	 * @return a list of notes representing a staff at a specified index
	 */
	public List<LinkedList<StringItem>> toList() {
		List<LinkedList<StringItem>> res = new ArrayList<>();

		for (GuitarString s : this.getStrings()) {
			List<StringItem> items = s.getItems();
			LinkedList<StringItem> notes = new LinkedList<>();

			for (StringItem item : items) {
				if (item.getClass() == Slide.class) {
					Slide sl = (Slide) item;
					notes.addAll(sl.getNotes());

				} else if (item.getClass() == PullOff.class) {
					PullOff po = (PullOff) item;
					notes.addAll(po.getNotes());

				} else if (item.getClass() == HammerOn.class) {
					HammerOn ho = (HammerOn) item;
					notes.addAll(ho.getNotes());

				} else if (item.getClass() == HammerPull.class) {
					HammerPull hp = (HammerPull) item;
					notes.addAll(hp.getNotes());

				} else if (item.getClass() == Harmonic.class) {
					Harmonic h = (Harmonic) item;
					notes.addAll(h.getNotes());

				} else if (item.getClass() == Note.class) {
					Note note = (Note) item;
					notes.add((StringItem) StringItem.deepClone(note));

				} else if (item.getClass() == Bar.class) {
					Bar bar = (Bar) item;
					notes.add((StringItem) StringItem.deepClone(bar));
				}
			}
			res.add(notes);
		}
		return res;
	}

	/**
	 * Return a custom iterator for traversing a staff at a specified index.
	 * 
	 * @return an iterator for a staff at specified <b>index</b>
	 */
	@Override
	public Iterator<StringItem> iterator() {
		return new StaffIterator(this);
	}

	/**
	 * A custom iterator for traversing a staff and its notes.
	 * 
	 * @author amir
	 *
	 */
	public class StaffIterator implements Iterator<StringItem> {
		private List<LinkedList<StringItem>> notes;
		private PriorityQueue<StringItem> pq;
		private int x = 0;
		private int y;
		private int barsNotSeen;
		private int numStrings;
		private int lengths[];
		private int totalNotesInCurrMeasure;
		private int totalNotesInStaff;
		private boolean collecting;
		private boolean setFirstRepeatNote;
		private Note previousNote = null;

		/**
		 * Construct a staff iterator.
		 * 
		 * @param staff the staff to iterate
		 */
		public StaffIterator(Staff staff) {
			notes = staff.toList();
			y = staff.size() - 1;
			barsNotSeen = staff.numberOfMeasures();
			numStrings = staff.size();
			lengths = new int[numStrings];
			Arrays.fill(lengths, -1);
			collecting = true;
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
			return totalNotesInStaff-- > 0;
		}

		/**
		 * Return the next chronological note within the staff.
		 * 
		 * Return the next note within a specified staff.
		 * 
		 */
		@Override
		public StringItem next() {
			// TODO: figure out how to calculate duration and type??
			while ((pq.isEmpty() || collecting) && totalNotesInCurrMeasure != 0) {
				collecting = true;

				if (y > notes.size() - 1)
					y = 0;

				if (lengths[y] == 0) {
					y++;
					continue;
				}

				StringItem item = (StringItem) notes.get(y).get(x);

				if (item.getClass() == Note.class) {
					Note note = (Note) item;
					note.setMeasure(accumulateMeasure);
					pq.add(item);
					notes.get(y).remove(item);
					lengths[y]--;
					totalNotesInCurrMeasure--;
					
					if (note.isGrace()) {
						while (((Note) notes.get(y).get(x)).isGrace()) {
							Note gNote = ((Note) notes.get(y).get(x));
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
			collecting = false;
			y = 0;

			Note note = (Note) pq.poll();

			if (note == null)
				return null;

			if (setFirstRepeatNote) {
				note.setRepeatedStart(true);
				Bar[] bars = getEndRepeatBars();
				note.setRepeatCount(bars[0].getRepeatCount());
				setFirstRepeatNote = false;
			}

			if (pq.isEmpty()) {
				Bar bar = (Bar) notes.get(2).get(0);

				if (bar.isDoubleBar() && bar.isRepeat() && bar.isStart())
					setFirstRepeatNote = true;
				else if (bar.isDoubleBar() && bar.isRepeat() && bar.isStop()) {
					note.setRepeatedStop(true);
				}

				notes.stream().filter(l -> l.size() > 0).forEach(l -> l.remove(0));
				totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
				barsNotSeen--;
				if (barsNotSeen == 0 || totalNotesInCurrMeasure != 0)
					setAccumulateMeasure(accumulateMeasure + 1);
			}

			if (previousNote != null && note.getPosition() == previousNote.getPosition()) {
				note.setChord(true);
				note.setType(1);
				note.setDuration("1");
			} else {
				note.setChord(false);
				note.setType(1);
				note.setDuration("1");
			}
			previousNote = (Note) StringItem.deepClone(note);

			return note;
		}

		private int setNotesInCurrMeasure(int[] lengths) {
			int len = 0;
			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<StringItem> line = notes.get(i);
				for (int j = 0; j < line.size(); j++) {
					Object obj = line.get(j);
					if (obj.getClass() == Bar.class) {
						lengths[i] = len;
						len = 0;
						break;
					} else if (obj.getClass() == Note.class)
						len++;
				}
			}
			return Arrays.stream(lengths).sum();
		}

		private Bar[] getEndRepeatBars() {
			Bar[] bars = new Bar[numStrings];

			for (int i = notes.size() - 1; i >= 0; i--) {
				LinkedList<StringItem> line = notes.get(i);
				for (int j = 0; j < line.size(); j++) {
					Object obj = line.get(j);
					if (obj.getClass() == Bar.class) {
						bars[i] = (Bar) obj;
					} else if (obj.getClass() == Note.class)
						continue;
				}
			}
			return bars;
		}

		@SuppressWarnings("unused")
		private void printNotes() {
			for (LinkedList<StringItem> line : notes) {
				for (StringItem item : line)
					System.out.print(item.toString() + " ");
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void setAccumulateMeasure(int accumulateMeasure) {
		Staff.accumulateMeasure = accumulateMeasure;
	}
}
