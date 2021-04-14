package tab2xml.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

import tab2xml.ImmutablePair;

/**
 * A representation of a measure containing a collection of a parameterized
 * {@code Note} types.
 * 
 * <p>
 * Properties of this object:
 * <ul>
 * <li>Contains a range 2d range of the measure in the input stream.
 * <li>A {@code Measure} can by default iterate over its notes in natural
 * ordering.
 * <li>Measure counting starts at 0.
 * <li>This is the central point where the duration of each note gets processed.
 * </ul>
 * 
 * <p>
 * Pre-conditions:
 * <ul>
 * <li><b>The staffs must be processed prior to creating measures and processing
 * duration</b>
 * </ul>
 * 
 * @author amir
 * @param <E> a type of {@code Note} which this measure contains
 */
public class Measure<E extends Note> implements Comparable<Measure<E>>, Iterable<E> {
	/** The beats of this measure. */
	private Optional<Integer> beats = Optional.empty();
	/** The beatType of this measure. */
	private Optional<Integer> beatType = Optional.empty();
	/** The division of this measure. */
	private Optional<Integer> division = Optional.empty();

	/** The measure value [0, n-1] (inclusive) */
	private int measure;
	/**
	 * A 2d range containing the upper and lower bounds of this measure in the input
	 * stream.
	 */
	private final ImmutablePair<Range, Range> range;
	/**
	 * A 1d column range of this measure, with respect to the start of the line.
	 */
	private Range columnRange;
	/** A list of notes in this measure. */
	private TreeSet<E> notes;

	/**
	 * Construct a {@code Measure} with a 2d range.
	 * 
	 * @param range the 2d range with the upper and lower bounds in the input stream
	 */
	public Measure(ImmutablePair<Range, Range> range) {
		this.range = range;
		this.notes = new TreeSet<>();
	}

	/**
	 * Construct a {@code Measure} with a value and a 2d range.
	 * 
	 * @param measure the number of this measure
	 * @param range   the 2d range with the upper and lower bounds in the input
	 *                stream
	 */
	public Measure(int measure, ImmutablePair<Range, Range> range) {
		this.measure = measure;
		this.range = range;
		this.notes = new TreeSet<>();
	}

	public Measure(int measure, ImmutablePair<Range, Range> range, Collection<E> notes) {
		this.measure = measure;
		this.notes = new TreeSet<>(notes);
		this.notes.addAll(notes);
		this.range = range;
	}

	/**
	 * This method processes the duration of each note in this measure, while
	 * considering the {@code TimeSignature} of this measure.
	 * 
	 * pre-conditions:
	 * <ul>
	 * <li><b>If a measure is extremely inaccurate with its timing, this will result
	 * in precision loss</b>
	 * </ul>
	 */
	public void processDuration() {
		TreeSet<E> durationNotes = new TreeSet<>(new DurationComparator());
		durationNotes.addAll(notes);

		for (E n : durationNotes) {
			E next = durationNotes.higher(n);
			if (next == null) {
				Range pairRange = new Range(this);
				pairRange.setStart(n.getColumn());
				pairRange.setStop(this.columnRange.getStop());
				double width = width() / (pairRange.size() - 1);
				n.setDurationVal(duration(width));
				continue;
			}
			Range pairRange = new Range(this);
			pairRange.setStart(n.getColumn());
			pairRange.setStop(next.getColumn());
			double width = width() / (pairRange.size() - 1);
			n.setDurationVal(duration(width));
		}

		for (E n : notes) {
			for (E n2 : durationNotes) {
				if (n.getColumn() == n2.getColumn()) {
					n.setDurationVal(n2.getDurationVal());
					if (n.getNotes().size() > 0)
						n.getNotes().forEach(group -> group.setDurationVal(n2.getDurationVal()));
				}
			}
		}
	}

	/**
	 * @return the number of this measure
	 */
	public int getMeasure() {
		return measure;
	}

	/**
	 * Set the number of this measure a specified value.
	 * 
	 * @param measure the value to set
	 */
	public void setMeasure(int measure) {
		this.measure = measure;
	}

	/**
	 * @return the 2d range with the upper and lower bounds in the input stream
	 */
	public ImmutablePair<Range, Range> getRange() {
		return range;
	}

	/**
	 * Add a note to this measure.
	 * 
	 * @param note the note to add to this measure
	 * @return {@code true} if the note was added successfully
	 */
	public boolean add(E note) {
		if (note == null)
			return false;
		notes.add(note);
		return true;
	}

	/**
	 * Add a {@code Collection} of notes to this measure.
	 * 
	 * @param notes the notes to add to this measure
	 * @return {@code true} if the notes were added successfully
	 */
	public boolean addAll(Collection<E> notes) {
		if (notes == null)
			return false;
		for (E n : notes)
			if (!add(n))
				return false;
		return true;
	}

	/** @return the list of notes in this measure */
	public TreeSet<E> getNotes() {
		return notes;
	}

	/** @return the number of notes in this measure */
	public int size() {
		return notes.size();
	}

	/** @return the width of this measure (excluding the bars) */
	public double width() {
		return range.getFirst().size() - 2;
	}

	/**
	 * A custom mapping of duration values calculated in {@link #processDuration()}.
	 * 
	 * @param x the value to map to the custom function
	 * @return a value mapped down to one of the 8 supported duration values
	 */
	private double duration(double x) {
		if (x >= 0.5 && x <= 1.5)
			return 1;
		else if (x >= 1.5 && x <= 3)
			return 2;
		else if (x >= 3 && x <= 6)
			return 4;
		else if (x >= 6 && x <= 12)
			return 8;
		else if (x >= 12 && x <= 24)
			return 16;
		else if (x >= 24 && x <= 48)
			return 32;
		else if (x >= 48 && x <= 96)
			return 64;
		else if (x >= 96 && x <= 192)
			return 128;
		else
			return 1;
	}

	/** @return the beats attribute of this measure (default value if not set) */
	public int getBeats() {
		return beats.orElse(Score.DEFAULT_BEATS);
	}

	/**
	 * Set the beats attribute of this measure to a specified value.
	 * 
	 * @param beats the value to set
	 */
	public void setBeats(int beats) {
		this.beats = Optional.of(beats);
	}

	/** @return the beatType attribute of this measure (default value if not set) */
	public int getBeatType() {
		return beatType.orElse(Score.DEFAULT_BEATTYPE);
	}

	/**
	 * Set the beatType attribute of this measure to a specified value.
	 * 
	 * @param beatType the value to set
	 */
	public void setBeatType(int beatType) {
		this.beatType = Optional.of(beatType);
	}

	/** @return the division attribute of this measure (default value if not set) */
	public int getDivision() {
		return division.orElse(Score.DEFAULT_DIVISION);
	}

	/**
	 * Set the division attribute of this measure to a specified value.
	 * 
	 * @param division the value to set
	 */
	public void setDivision(int division) {
		this.division = Optional.of(division);
	}

	/**
	 * @return the {@code Range} with the line respective column bounds
	 */
	public Range getColumnRange() {
		return columnRange;
	}

	/**
	 * Set this measures column bounds {@code Range} to the specified {@code Range}.
	 * 
	 * @param columnRange the value to set the columnRange value to
	 */
	public void setColumnRange(Range columnRange) {
		this.columnRange = columnRange;
	}

	/**
	 * @return an {@code Iterator<E>} which iterates over the notes in this measure
	 */
	@Override
	public Iterator<E> iterator() {
		return new MeasureIterator(this);
	}

	/**
	 * A measure can be compared to another by their natural ordering.
	 * 
	 * @param m the measure to compare to this measure.
	 * @return a negative, zero, or positive number if this measure is less then,
	 *         equal to, or greater than respectively (in terms of order)
	 */
	@Override
	public int compareTo(Measure<E> m) {
		return this.getMeasure() - m.getMeasure();
	}

	@Override
	public String toString() {
		return notes.toString();
	}

	/**
	 * A custom {@code Comparator} implementation which flattens notes in this
	 * measure to the same position along with their natural ordering.
	 */
	public static class DurationComparator implements Comparator<LineItem> {
		@Override
		public int compare(LineItem i1, LineItem i2) {
			if (i1.getColumn() == i2.getColumn())
				return 0;
			return i1.compareTo(i2);
		}
	}

	/**
	 * A custom {@code Iterator} implementation which iterates the notes in this
	 * measure.
	 */
	private class MeasureIterator implements Iterator<E> {
		TreeSet<E> notes;

		public MeasureIterator(Measure<E> measure) {
			notes = new TreeSet<>(measure.getNotes());
		}

		@Override
		public boolean hasNext() {
			return !notes.isEmpty();
		}

		@Override
		public E next() {
			return notes.pollFirst();
		}
	}
}
