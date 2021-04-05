package tab2xml;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A range of integers. The beginning is inclusive and the end is exclusive.
 *
 * @since 2021-03-22
 */
public final class IntRange extends AbstractSet<Integer> {
	/**
	 * An iterator that iterates over a range of integers.
	 *
	 * @since 2021-03-22
	 */
	private static final class IntRangeIterator implements Iterator<Integer> {
		private int value;
		private final int end;
		
		/**
		 * @param start value to start at (inclusive)
		 * @param end   value to end at (exclusive)
		 * @since 2021-03-22
		 */
		public IntRangeIterator(int start, int end) {
			this.value = start;
			this.end = end;
		}
		
		@Override
		public boolean hasNext() {
			return this.value < this.end;
		}
		
		@Override
		public Integer next() {
			if (this.hasNext())
				return this.value++;
			else
				throw new NoSuchElementException();
		}
	}
	
	/**
	 * The separator used in the toString() method.
	 */
	private static final String SEPARATOR = "..";
	
	/** A regex that matches an integer. */
	private static final String INTEGER_PATTERN = "([+-]?\\d+)";
	/** Pattern that matches the toString output */
	private static final Pattern TO_STRING = Pattern
			.compile(INTEGER_PATTERN + "\\.\\." + INTEGER_PATTERN);
	
	/**
	 * Parses a string formatted as in {@link #toString} and returns the
	 * associated int range
	 *
	 * @param str string to parse
	 * @return parsed IntRange instance
	 * @throws IllegalArgumentException if format is incorrect
	 * @since 2021-04-05
	 */
	public static IntRange fromString(String str) {
		final Matcher matcher = TO_STRING.matcher(str);
		if (matcher.find()) {
			final int beginning = Integer.parseInt(matcher.group(1));
			final int end = Integer.parseInt(matcher.group(2));
			return IntRange.inclusive(beginning, end);
		} else
			throw new IllegalArgumentException(
					"Incorrect format for IntRange string.");
	}
	
	/**
	 * Gets an {@code IntRange}
	 *
	 * @param beginning beginning of range, inclusive
	 * @param end       end of range, <b>inclusive</b>
	 * @return
	 * @since 2021-03-31
	 */
	public static IntRange inclusive(int beginning, int end) {
		return valueOf(beginning, end + 1);
	}
	
	/**
	 * @return true if {@code str} is parseable by {@link #fromString}.
	 * @since 2021-04-05
	 */
	public static boolean isValidString(String str) {
		return TO_STRING.matcher(str).find();
	}
	
	/**
	 * Gets an {@code IntRange}
	 *
	 * @param beginningInclusive beginning of range, inclusive
	 * @param endExclusive       end of range, exclusive
	 * @return int range
	 * @throws IllegalArgumentException if beginning is less than end
	 * @since 2021-03-31
	 */
	public static IntRange valueOf(int beginningInclusive, int endExclusive) {
		return new IntRange(beginningInclusive, endExclusive);
	}
	
	/**
	 * The first element in the range.
	 */
	private final int beginningInclusive;
	/**
	 * The first element after the range.
	 */
	private final int endExclusive;
	
	/**
	 * @param beginningInclusive beginning of range, inclusive
	 * @param endExclusive       end of range, exclusive
	 * @throws IllegalArgumentException if beginning is less than end
	 * @since 2021-03-22
	 */
	private IntRange(int beginningInclusive, int endExclusive) {
		if (beginningInclusive > endExclusive)
			throw new IllegalArgumentException(
					"Beginning must be less than or equal to end");
		this.beginningInclusive = beginningInclusive;
		this.endExclusive = endExclusive;
	}
	
	/**
	 * @return beginning of range, exclusive
	 * @since 2021-03-31
	 */
	public final int beginningExclusive() {
		return this.beginningInclusive + 1;
	}
	
	/**
	 * @return beginning of range, inclusive
	 * @since 2021-03-22
	 */
	public final int beginningInclusive() {
		return this.beginningInclusive;
	}
	
	@Override
	public boolean contains(Object o) {
		if (o instanceof Integer) {
			final int i = (Integer) o;
			return this.beginningInclusive <= i && i < this.endExclusive;
		} else
			return false;
	}
	
	/**
	 * @return end of range, exclusive
	 * @since 2021-03-22
	 */
	public final int endExclusive() {
		return this.endExclusive;
	}
	
	/**
	 * @return end of range, inclusive
	 * @since 2021-03-31
	 */
	public final int endInclusive() {
		return this.endExclusive - 1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IntRange))
			return false;
		final IntRange other = (IntRange) obj;
		return this.beginningInclusive == other.beginningInclusive
				&& this.endExclusive == other.endExclusive;
	}
	
	@Override
	public int hashCode() {
		// use a large number so that small numbers (most likely) will make
		// different hashes more likely
		// if both numbers are nonnegative and below 8191, the hash is guaranteed
		// to be unique
		return 8191 * this.beginningInclusive + this.endExclusive;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new IntRangeIterator(this.beginningInclusive, this.endExclusive);
	}
	
	@Override
	public int size() {
		return this.endExclusive - this.beginningInclusive;
	}
	
	/**
	 * Returns a String representation of this range. It consists of the
	 * {@linkplain #beginningInclusive() inclusive beginning}, then two periods,
	 * then the {@linkplain #endInclusive() inclusive end}.
	 * <p>
	 * For example, {@code IntRange.inclusive(3, 7).toString()} returns
	 * {@code "3..7"}.
	 */
	@Override
	public String toString() {
		return this.beginningInclusive() + SEPARATOR + this.endInclusive();
	}
}
