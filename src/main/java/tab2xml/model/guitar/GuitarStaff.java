package tab2xml.model.guitar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import tab2xml.model.Staff;
import tab2xml.ImmutablePair;
import tab2xml.model.LineItem;
import tab2xml.model.Measure;
import tab2xml.model.Range;
import tab2xml.model.Score;

public class GuitarStaff extends Staff<GuitarString, GuitarNote> {
    private static final long serialVersionUID = 5418273130827075188L;

    /* Default values guitar */
    public static final int DEFAULT_NUM_STRINGS = 6;
    /**
     * The default beats.
     */
    public final static int DEFAULT_BEATS = 4;
    /**
     * The default beat type of every score.
     */
    public final static int DEFAULT_BEATTYPE = 4;

    /**
     * The default number of divisions per quarter note.
     */
    public final static int DEFAULT_DIVISION = 2;

    public GuitarStaff() {
    }

    public void init(GuitarStaff staff) {
	Iterator<LineItem> itr = new InitialStaffIterator(staff);
	while (itr.hasNext()) {
	    notes.add(((GuitarNote) itr.next()));
	}
    }

    @Override
    public Iterator<Measure<GuitarNote>> measureIterator() {
	return new MeasureIterator(this);
    }

    @Override
    public Iterator<GuitarNote> iterator() {
	return new NoteIterator(this);
    }

    /**
     * Iterate over the notes in this staff.
     */
    private static class MeasureIterator implements Iterator<Measure<GuitarNote>> {
	private TreeSet<GuitarNote> notes;
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
	public Measure<GuitarNote> next() {
	    Bar[] barsStart = getFirstBarsAt(startPosition, staff);
	    Bar[] barsEnd = getFirstBarsAt(stopPosition, staff);

	    Range upperRange = new Range(barsStart[0].getPosition(), barsEnd[0].getPosition());
	    Range bottomRange = new Range(barsStart[barsEnd.length - 1].getPosition(),
		    barsEnd[barsEnd.length - 1].getPosition());
	    ImmutablePair<Range, Range> range = ImmutablePair.of(upperRange, bottomRange);

	    Measure<GuitarNote> measure = new Measure<>(Score.getAccumulateMeasureScore(), range);
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
     * Iterate over the notes in this staff.
     */
    private static class NoteIterator implements Iterator<GuitarNote> {
	private TreeSet<GuitarNote> notes;

	public NoteIterator(GuitarStaff staff) {
	    notes = staff.getNotes();
	}

	@Override
	public boolean hasNext() {
	    return !notes.isEmpty();
	}

	@Override
	public GuitarNote next() {
	    return notes.pollFirst();
	}
    }

    /**
     * A custom iterator for traversing a staff initializing each note.
     * 
     * @author amir
     */
    private static class InitialStaffIterator implements Iterator<LineItem> {
	private List<LinkedList<LineItem>> notes;
	private PriorityQueue<LineItem> pq;
	private final int X = 1;
	private int y;
	private int numStrings;
	private int lengths[];
	private int totalNotesInCurrMeasure;
	private int totalNotesInStaff;
	private int durationCurrNote;
	private boolean collecting;
	private boolean remaining;
	private boolean setFirstRepeatNote;
	private GuitarNote previousNote = null;
	private GuitarNote chordBase = null;

	/**
	 * Construct a staff iterator.
	 * 
	 * @param staff the staff to iterate
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
			GuitarNote newNote = new GuitarNote(bars[i].getTune(), fret);
			newNote.setColumn(position);
			newNote.setLineNum(bars[i].getLineNum());
			newNote.setMeasure(Score.getAccumulateMeasure());

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
		    if (note.getNoteCount() > 1)
			note.getNotes().forEach(n -> n.setDuration(durationCurrNote));
		}

		if (isJustDoubleBars(bars)) {
		    note.setDoubleBar(true);
		}

		if (totalNotesInCurrMeasure == 0 && !remaining) {
		    notes.stream().filter(l -> l.size() > 0).forEach(l -> l.remove(0));
		    totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
		    Score.setAccumulateMeasure(Score.getAccumulateMeasure() + 1);
		}
	    }

	    if (previousNote != null && note.getColumn() == previousNote.getColumn()) {
		note.setChord(true);
		note.setDuration(1);
	    } else {
		note.setChord(false);
		note.setDuration(1);
	    }
	    previousNote = (GuitarNote) LineItem.deepClone(note);

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
		    } else if (obj.getClass() == GuitarNote.class)
			len++;
		}
	    }
	    return Arrays.stream(lengths).sum();
	}
    }
}
