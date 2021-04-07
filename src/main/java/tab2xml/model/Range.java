package tab2xml.model;

import java.util.Optional;

/**
 * A range object containing a {@code Double} range between <b>start</b> and
 * <b>stop</b> inclusive.
 * 
 * <p>
 * Properties of this object:
 * <ul>
 * <li>Contains a <b>division</b> property that divides a measure into its beats
 * parts. This then enables this {@code Range} object to get a new {@code Range}
 * of some division part.
 * </ul>
 * 
 * <p>
 * pre-conditions:
 * <ul>
 * <li><b>The <i>start</i> of a range must be strictly less than the
 * <i>stop</i></b>
 * </ul>
 * 
 * @author amir
 */
public class Range {
	/** The start of this range. */
	private Optional<Double> start = Optional.empty();
	/** The end of this range. */
	private Optional<Double> stop = Optional.empty();
	/** The division factor of some measure. */
	private Optional<Double> division = Optional.empty();
	/** The beats of the measure corresponding to the division factor. */
	private int beats;

	/**
	 * Construct a {@code Range} object from a defined {@code Measure} object.
	 * 
	 * @param measure the measure to define the <b>division</b> property
	 */
	public Range(Measure<? extends Note> measure) {
		this.division = Optional.of(measure.width() / measure.getBeats());
		this.beats = measure.getBeats();
	}

	/**
	 * Construct a {@code Range} object with a defined start and stop.
	 * 
	 * @param start the start of the range
	 * @param stop  the end of the range
	 */
	public Range(double start, double stop) {
		if (start > stop)
			throw new IllegalArgumentException("Invalid rrange.");
		this.start = Optional.of(start);
		this.stop = Optional.of(stop);
	}

	/** @return the start value of this range */
	public double getStart() {
		return start.orElse(0.0);
	}

	/**
	 * Set the start value of this range to a specified value.
	 * 
	 * @param start the value to set
	 */
	public void setStart(double start) {
		if (stop.isPresent() && start > stop.get())
			throw new IllegalArgumentException("Invalid rrange.");
		this.start = Optional.of(start);
	}

	/** @return the stop value of this range */
	public double getStop() {
		return stop.orElse(0.0);
	}

	/**
	 * Set the stop value of this range to a specified value.
	 * 
	 * @param stop the value to set
	 */
	public void setStop(double stop) {
		if (start.isPresent() && start.get() > stop)
			throw new IllegalArgumentException("Invalid rrange.");
		this.stop = Optional.of(stop);
	}

	/** @return the division factor of this range defined with a measure */
	public double getDivisionFactor() {
		return division.orElse(0.0);
	}

	/**
	 * Set the division factor value of this range to a specified value.
	 * 
	 * @param stop the value to set
	 */
	public void setDivisionFactor(double divisionFactor) {
		this.division = Optional.of(divisionFactor);
	}

	/**
	 * Check if a given object is in this {@code Range}.
	 * 
	 * @param o the object to check
	 * @return {@code true} if the object is in this {@code Range}
	 */
	public boolean contains(Object o) {
		if (o instanceof Number && start.isPresent() && stop.isPresent()) {
			final double i = (Double) o;
			return i >= start.get() && i <= stop.get();
		}
		return false;
	}

	/** @return the size of this {@code Range} */
	public double size() {
		return stop.get() - start.get() + 1;
	}

	/**
	 * Get a {@code Range} of a division in a measure at a specified index.
	 * 
	 * @param index the index of the division to get
	 * @return the {@code Range} of the division at <b>index</b>
	 */
	public Range getDivision(int index) {
		if (division.isPresent() && index >= 0 && index <= beats)
			return new Range(index * division.get(), index * division.get() + division.get());
		return null;
	}
}
