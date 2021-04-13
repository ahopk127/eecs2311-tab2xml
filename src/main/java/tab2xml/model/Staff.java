package tab2xml.model;

import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.TreeSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import tab2xml.model.drum.DrumType;
import tab2xml.model.guitar.Tune;

/**
 * A representation of a staff containing a collection of a parameterized
 * {@code Line} types.
 * 
 * <p>
 * Properties of this object:
 * <ul>
 * <li>A {@code Staff} can by default iterate over its {@code Line} objects in
 * their natural ordering.
 * <li>A {@code Staff} can also iterate over its {@code Note} objects in their
 * natural ordering.
 * <li>A {@code Staff} has an initial iterator which should be called <b>ONLY
 * ONCE</b></li>
 * </ul>
 * 
 * @author amir
 * @param <E> a type of {@code Line} which this staff contains
 */
public abstract class Staff<E extends Line> extends ScoreItem implements Iterable<E> {
	private static final long serialVersionUID = -1721480917571243686L;

	/** The beats of this staff. */
	private Optional<Integer> beats = Optional.empty();
	/** The beatType of this staff. */
	private Optional<Integer> beatType = Optional.empty();
	/** The division of this staff. */
	private Optional<Integer> division = Optional.empty();
	/** A stack to keep track of repeat sections in a score. */
	protected static final Stack<Note> repeatNoteStack = new Stack<>();

	/**
	 * Construct an empty staff with set default attributes. {@code Line} object
	 * will keep track of groups of lines in a staff.
	 */
	public Staff() {
		beats = Optional.of(Score.DEFAULT_BEATS);
		beatType = Optional.of(Score.DEFAULT_BEATTYPE);
		division = Optional.of(Score.DEFAULT_DIVISION);
		Line.setLine(0);
	}

	/**
	 * Add an abstract {@code Line} object to this staff.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>The line to add MUST not be {@code null}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param line the line object to add to this staff
	 * @return {@code true} if the line was added to this staff successfully
	 */
	public abstract boolean add(E line);

	/**
	 * Add multiple abstract {@code Line} objects to this staff.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>The lines to add MUST not be {@code null}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param lines the line objects to add to this staff
	 * @return {@code true} if all the lines were added to this staff successfully
	 */
	public abstract boolean addLines(Collection<? extends E> lines);

	/** @return a list of {@code Line} objects in this staff */
	public abstract List<E> getLines();

	/** @return the number of measures in this staff */
	public abstract int numberOfMeasures();

	/** @return the number of lines in this staff as a string */
	public abstract String lineCount();

	/** @return the number of lines in this staff */
	public abstract int size();

	/** @return the width of this staff in the input stream */
	public abstract int width();

	/** @return the list of {@code Note} objects in this staff */
	public abstract TreeSet<Note> getNotes();

	/** @return an {@code Iterator} which iterates over the notes in this staff */
	public abstract Iterator<Note> noteIterator();

	/**
	 * @return an {@code Iterator} which iterates over the measures in this staff
	 */
	public abstract Iterator<Measure<Note>> measureIterator();

	/** @return an {@code Iterator} which iterates over the lines in this staff */
	@Override
	public abstract Iterator<E> iterator();

	/** @return the number of notes in this staff */
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

	/**
	 * @return the time signature of this staff in the format,
	 *         <b>beats::beatType</b>
	 */
	public String time() {
		return String.format("%d:%d", getBeats(), getBeatType());
	}

	/** @return the beats attribute of this staff (default value if not set) */
	public int getBeats() {
		return beats.orElse(Score.DEFAULT_BEATS);
	}

	/**
	 * Set the beats attribute of this staff to a specified value.
	 * 
	 * @param beats the value to set
	 */
	public void setBeats(int beats) {
		this.beats = Optional.of(beats);
	}

	/** @return the beatType attribute of this staff (default value if not set) */
	public int getBeatType() {
		return beatType.orElse(Score.DEFAULT_BEATTYPE);
	}

	/**
	 * Set the beatType attribute of this staff to a specified value.
	 * 
	 * @param beatType the value to set
	 */
	public void setBeatType(int beatType) {
		this.beatType = Optional.of(beatType);
	}

	/** @return the division attribute of this staff (default value if not set) */
	public int getDivision() {
		return division.orElse(Score.DEFAULT_DIVISION);
	}

	/**
	 * Set the division attribute of this staff to a specified value.
	 * 
	 * @param division the value to set
	 */
	public void setDivision(int division) {
		this.division = Optional.of(division);
	}

	/**
	 * Extract notes from this staff to a 2d list (containing the {@code Bar}
	 * objects).
	 * 
	 * @return a 2d list of notes in this staff
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

	/* Functional methods */
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

	protected static Bar[] getStartRepeatBars(List<LinkedList<LineItem>> list) {
		int column = 1;
		for (;;) {
			Bar[] bars = getFirstBarsAt(column, list);
			if (isEmpty(bars))
				return null;
			if (isRepeatBegin(bars))
				return bars;
			column++;
		}
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
		return (bars.length / 2 == 0 ? bars[bars.length / 2 - 1].isStartBar() && bars[bars.length / 2].isStartBar()
				: bars[bars.length / 2].isStartBar());
	}

	protected static boolean isRepeatEnd(Bar[] bars) {
		if (bars == null || bars.length == 0)
			return false;
		return (bars.length / 2 == 0 ? bars[bars.length / 2 - 1].isStopBar() && bars[bars.length / 2].isStopBar()
				: bars[bars.length / 2].isStopBar());
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
		if (bars == null)
			return isEmpty;
		for (Bar bar : bars) {
			if (bar != null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}
}
