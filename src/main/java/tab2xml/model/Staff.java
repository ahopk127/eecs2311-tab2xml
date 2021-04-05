package tab2xml.model;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import tab2xml.model.drum.DrumType;
import tab2xml.model.guitar.Tune;

/**
 * An iterable abstract staff object. You can iterate over the staff line by
 * note by note, line by line, or measure by measure. The default iterator is
 * line by line note.
 * 
 * @author amir
 */
public abstract class Staff<E extends Line> extends ScoreItem implements Iterable<E> {
	private static final long serialVersionUID = -1721480917571243686L;

	/* General defaults of staffs */
	private Optional<Integer> beats = Optional.empty();
	private Optional<Integer> beatType = Optional.empty();
	private Optional<Integer> division = Optional.empty();

	/**
	 * Construct an empty staff.
	 */
	public Staff() {
		Line.setLine(0);
	}

	/**
	 * Add an abstract string/line item to this staff.
	 * 
	 * @param s the string/line object to add to this staff.
	 */
	public abstract boolean add(E line);

	/**
	 * Add multiple abstract string/line item to this staff.
	 * 
	 * @param s the string/line object to add to this staff.
	 */
	public abstract boolean addLines(Collection<? extends E> lines);

	/**
	 * @return a list of abstract line objects.
	 */
	public abstract List<E> getLines();

	/**
	 * @return the number of measures in this staff.
	 */
	public abstract int numberOfMeasures();

	/**
	 * @return the number of lines in this staff as a string.
	 */
	public abstract String lineCount();

	/**
	 * @return the number of lines in this staff.
	 */
	public abstract int size();

	/**
	 * @return the width of this staff.
	 */
	public abstract int width();

	public abstract TreeSet<Note> getNotes();

	public abstract Iterator<Note> noteIterator();

	public abstract Iterator<Measure<Note>> measureIterator();

	/**
	 * Iterate over this staff line by line.
	 */
	@Override
	public abstract Iterator<E> iterator();


	/**
	 * @return the total number of notes in the staff
	 */
	@Override
	public int getNoteCount() {
		int count = 0;
		for (E line : getLines()) {
			count += line.getNoteCount();
		}
		return count;
	}

	@Override
	public abstract String toString();
	
	public String time() {
		return String.format("%d:%d", getBeats(), getBeatType());
	}
	
	/**
	 * Extract notes from this staff to a 2d list.
	 * 
	 * @return a list of notes in this staff.
	 */
	public List<LinkedList<LineItem>> toList() {
		List<LinkedList<LineItem>> res = new ArrayList<>();
		for (E s : getLines()) {
			LinkedList<LineItem> notes = new LinkedList<>();
			notes.addAll(s.getNotes());
			res.add(notes);
		}
		return res;
	}

	public int getBeats() {
		return beats.orElse(Score.DEFAULT_BEATS);
	}

	public void setBeats(int beats) {
		this.beats = Optional.of(beats);
	}

	public int getBeatType() {
		return beatType.orElse(Score.DEFAULT_BEATTYPE);
	}

	public void setBeatType(int beatType) {
		this.beatType = Optional.of(beatType);
	}
	
	public int getDivision() {
		return division.orElse(Score.DEFAULT_DIVISION);
	}

	public void setDivision(int division) {
		this.division = Optional.of(division);
	}

	/* functional methods */
	protected static Bar[] getFirstBarsAt(int index, List<LinkedList<LineItem>> list) {
		Bar[] bars = new Bar[list.size()];
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				LineItem item = list.get(i).get(j);
				if ((item != null && (item.getClass() == Bar.class || item.getClass() == Tune.class
						|| item.getClass() == DrumType.class)) && count++ == index) {
					if (item.getClass() == Tune.class) {
						Bar bar = new Bar();
						Tune tune = (Tune) item;
						bar.setLineNum(tune.getLineNum());
						bar.setColumn(tune.getColumn());
						bar.setPosition(tune.getPosition());
						bar.setRightPos(tune.getPosition());
						bar.setLeftPos(tune.getPosition());
						bars[i] = bar;
					} else if (item.getClass() == DrumType.class) {
						Bar bar = new Bar();
						DrumType type = (DrumType) item;
						bar.setLineNum(type.getLineNum());
						bar.setColumn(type.getColumn());
						bar.setPosition(type.getPosition());
						bar.setRightPos(type.getPosition());
						bar.setLeftPos(type.getPosition());
						bars[i] = bar;
					} else
						bars[i] = (Bar) item;
					break;
				}
			}
			count = 0;
		}
		if (isEmpty(bars))
			return null;
		return bars;
	}

	protected static Bar[] getEndRepeatBars(List<LinkedList<LineItem>> list) {
		int column = 1;
		for (;;) {
			Bar[] bars = getFirstBarsAt(column, list);
			if (isEmpty(bars))
				return null;
			if (isRepeatEnd(bars))
				return bars;
			column++;
		}
	}

	protected static boolean isRepeatBegin(Bar[] bars) {
		if (bars == null || bars.length == 0)
			return false;
		return bars[2].isStartBar() && bars[3].isStartBar();
	}

	protected static boolean isRepeatEnd(Bar[] bars) {
		if (bars == null || bars.length == 0)
			return false;
		return bars[2].isStopBar() && bars[3].isStopBar();
	}

	protected static boolean isJustDoubleBars(Bar[] bars) {
		if (bars == null || bars.length == 0)
			return false;
		for (int i = 0; i < bars.length; i++)
			if (!bars[i].isDoubleBar())
				return false;
		return true;
	}

	protected static boolean isFretEndBars(Bar[] bars) {
		if (bars == null || bars.length == 0)
			return false;
		boolean repeatEnds = isRepeatEnd(bars);
		for (int i = 0; i < bars.length; i++)
			if ((bars[i].isFretEndBar() || bars[i].isFretEndDoubleBar()) && !repeatEnds)
				return true;
		return false;
	}

	protected static boolean isEmpty(Bar[] bars) {
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
