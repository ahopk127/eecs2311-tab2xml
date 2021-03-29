package tab2xml.model.drum;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import tab2xml.model.Staff;
import tab2xml.model.LineItem;
import tab2xml.model.Measure;
import tab2xml.model.Score;
import tab2xml.model.guitar.Bar;

public class DrumStaff extends Staff<DrumLine, DrumNote> {
    private static final long serialVersionUID = 8730635738592350695L;

    public DrumStaff() {
    }

    /**
     * Return a custom iterator for traversing a staff at a specified index.
     * 
     * @return an iterator for a staff at specified <b>index</b>
     */
    @Override
    public Iterator<DrumNote> iterator() {
	return null;
    }

    /**
     * A custom iterator for traversing a staff and its notes.
     * 
     * @author amir
     *
     */
    public class StaffIterator implements Iterator<LineItem> {
	private List<LinkedList<LineItem>> notes;
	private PriorityQueue<LineItem> pq;
	private int x = 0;
	private int y;
	private int barsNotSeen;
	private int numLines;
	private int lengths[];
	private int totalNotesInCurrMeasure;
	private int totalNotesInStaff;
	private boolean collecting;
	private boolean setFirstRepeatNote;
	private DrumNote previousNote = null;

	/**
	 * Construct a staff iterator.
	 * 
	 * @param staff the staff to iterate
	 */
	public StaffIterator(DrumStaff staff) {
	    notes = staff.toList();
	    y = staff.size() - 1;
	    barsNotSeen = staff.numberOfMeasures();
	    numLines = staff.size();
	    lengths = new int[numLines];
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
	 * Return the next chronological note within a specified staff.
	 * 
	 * @return the next note within a specified staff.
	 */
	@Override
	public LineItem next() {
	    // TODO: figure out how to calculate duration and type??
	    while ((pq.isEmpty() || collecting) && totalNotesInCurrMeasure != 0) {
		collecting = true;

		if (y > notes.size() - 1)
		    y = 0;

		if (lengths[y] == 0) {
		    y++;
		    continue;
		}

		LineItem item = notes.get(y).get(x);

		if (item.getClass() == DrumNote.class) {
		    DrumNote note = (DrumNote) item;
		    note.setMeasure(Score.getAccumulateMeasure());
		    pq.add(item);
		    notes.get(y).remove(item);
		    lengths[y]--;
		    totalNotesInCurrMeasure--;

		}
		y++;
	    }
	    collecting = false;
	    y = 0;

	    DrumNote note = (DrumNote) pq.poll();

	    if (note == null)
		return null;

	    // if the bars are repeat start, set the count and start note
	    // if the count is not specified by default it is 1.
	    if (setFirstRepeatNote) {
		note.setRepeatedStart(true);
		Bar[] endRepeats = getEndRepeatBars();
		if (endRepeats != null)
		    note.setRepeatCount(endRepeats[0].getRepeatCount());
		setFirstRepeatNote = false;
	    }
	    Bar[] bars = getFirstBars();

	    if (pq.isEmpty() && bars != null) {
		if (totalNotesInCurrMeasure == 0) {
		    boolean isRepeatEnd = false;

		    for (int i = 1; i < bars.length; i++)
			if (bars[i].isRepeat() && bars[i].isStop())
			    isRepeatEnd = true;

		    if (bars[0].isRepeat() && bars[0].isStop() && !isRepeatEnd) {
			String value = bars[0].toString();
			String fret = value.substring(0, value.indexOf("|"));
			double position = bars[0].getColumn() - 1;
			DrumNote newNote = new DrumNote(bars[0].getTune(), fret);
			newNote.setColumn(position);
			newNote.setLineNum(bars[0].getLineNum());
			pq.add(newNote);
			totalNotesInStaff++;
		    }
		}

		if (bars[2].hasDoubleBar() && bars[2].isRepeat() && bars[2].isStart()) {
		    setFirstRepeatNote = true;
		}

		if (bars[2].hasDoubleBar() && bars[2].isRepeat() && bars[2].isStop()) {
		    note.setRepeatedStop(true);
		}

		if (Arrays.stream(bars).filter(b -> b.hasDoubleBar() && !b.isRepeat()).count() == bars.length) {
		    note.setDoubleBar(true);
		}

		notes.stream().filter(l -> l.size() > 0).forEach(l -> l.remove(x));
		totalNotesInCurrMeasure = setNotesInCurrMeasure(lengths);
		barsNotSeen--;

		if (barsNotSeen == 0 || totalNotesInCurrMeasure != 0)
		    Score.setAccumulateMeasure(Score.getAccumulateMeasure() + 1);
	    }

	    if (previousNote != null && note.getColumn() == previousNote.getColumn()) {
		note.setChord(true);
		note.setDuration(1);
	    } else {
		note.setChord(false);
		note.setDuration(1);
	    }
	    previousNote = (DrumNote) LineItem.deepClone(note);

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
		    } else if (obj.getClass() == DrumNote.class)
			len++;
		}
	    }
	    return Arrays.stream(lengths).sum();
	}

	private Bar[] getFirstBars() {
	    Bar[] bars = new Bar[numLines];
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
		Bar[] bars = new Bar[numLines];
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

		if (bars[2].hasDoubleBar() && bars[2].isRepeat() && bars[2].isStop() && bars[0].isStop())
		    return bars;
		column++;
	    }
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

    @Override
    public Iterator<Measure<DrumNote>> measureIterator() {
	// TODO Auto-generated method stub
	return null;
    }
}