package tab2xml.model;

import java.util.List;
import java.util.TreeSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import tab2xml.model.guitar.Bar;
import tab2xml.model.guitar.Tune;

/**
 * An iterable abstract staff object. You can iterate over the staff measure by
 * measure or note by note. The default iterator iterates the staff note by
 * note.
 * 
 * @author amir
 */
public abstract class Staff<E extends Line<?>, T extends Note> extends ScoreItem<T> {
	private static final long serialVersionUID = -1721480917571243686L;

	/* General defaults of staffs */
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

	protected String beats;
	protected String beatType;
	protected List<E> lines;
	protected TreeSet<T> notes;

	/**
	 * Constructor to reset line count on new {@code Staff}
	 */
	public Staff() {
		lines = new ArrayList<>();
		notes = new TreeSet<>();
		Line.setLine(0);
	}

	/**
	 * Add an abstract string/line item to this staff.
	 * 
	 * @param s the string/line object to add to this staff.
	 */
	public boolean add(E line) {
		if (line == null)
			return false;
		lines.add(line);
		return true;
	}

	/**
	 * Add multiple abstract string/line item to this staff.
	 * 
	 * @param s the string/line object to add to this staff.
	 */
	public boolean addLines(Collection<? extends E> lines) {
		for (E l : lines)
			if (!add(l))
				return false;
		return true;
	}

	/**
	 * @return a list of abstract line objects.
	 */
	public List<E> getLines() {
		return lines;
	}

	/**
	 * @return return the beats attribute of a staff.
	 */
	public String getBeats() {
		return beats;
	}

	/**
	 * @param beatType the value to set the beats of this staff.
	 */
	public void setBeats(String beatType) {
		this.beatType = beatType;
	}

	/**
	 * @return the beat type attribute of this staff.
	 */
	public String getBeatType() {
		return beatType;
	}

	/**
	 * @param beatType the value to set the beat type of this staff.
	 */
	public void setBeatType(String beatType) {
		this.beatType = beatType;
	}

	/**
	 * @return the number of measures in this staff.
	 */
	public int numberOfMeasures() {
		return lines.get(0).getNumMeasures();
	}

	/**
	 * @return the number of lines in this staff as a string.
	 */
	public String lineCount() {
		return String.valueOf(lines.size());
	}

	/**
	 * @return the number of lines in this staff.
	 */
	public int size() {
		return lines.size();
	}

	/**
	 * @return the width of this staff.
	 */
	public int width() {
		return lines.get(0).width();
	}

	@SuppressWarnings("unchecked")
	public TreeSet<T> getNotes() {
		TreeSet<T> copy = new TreeSet<>();
		for (T n : notes) {
			copy.add((T) LineItem.deepClone(n));
		}
		return copy;
	}

	/**
	 * Extract notes from this staff.
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

	/**
	 * @return the total number of notes in the staff
	 */
	@Override
	public int getNoteCount() {
		int count = 0;
		for (E line : lines) {
			count += line.getNoteCount();
		}
		return count;
	}

	@Override
	public abstract Iterator<T> iterator();

	public abstract Iterator<Measure<T>> measureIterator();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (notes == null || notes.size() == 0)
			return String.format("[]");
		Iterator<Measure<T>> itr = measureIterator();
		sb.append("[");
		while (itr.hasNext()) {
			sb.append(itr.next().toString());
			sb.append((itr.hasNext() ? ", " : "]"));
		}
		return sb.toString();
	}

	/* functional methods */

	protected static Bar[] getFirstBarsAt(int index, List<LinkedList<LineItem>> list) {
		Bar[] bars = new Bar[list.size()];
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++) {
				LineItem item = list.get(i).get(j);
				if ((item != null && item.getClass() == Bar.class || item.getClass() == Tune.class)
						&& count++ == index) {
					if (item.getClass() == Tune.class) {
						Bar bar = new Bar();
						Tune tune = (Tune) item;
						bar.setColumn(tune.getColumn());
						bar.setPosition(tune.getPosition());
						bar.setLineNum(tune.getLineNum());
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
