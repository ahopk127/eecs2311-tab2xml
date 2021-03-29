package tab2xml;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	public IntRange(int beginningInclusive, int endExclusive) {
		if (beginningInclusive > endExclusive)
			throw new IllegalArgumentException(
					"Beginning must be less than or equal to end");
		this.beginningInclusive = beginningInclusive;
		this.endExclusive = endExclusive;
	}
	
	@Override
	public boolean contains(Object o) {
		if (o instanceof Integer) {
			final int i = (Integer) o;
			return this.beginningInclusive <= i && i < this.endExclusive;
		} else
			return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IntRange))
			return false;
		final IntRange other = (IntRange) obj;
		return this.beginningInclusive == other.beginningInclusive
				&& this.endExclusive == other.endExclusive;
	}
	
	/**
	 * @return beginning of range, inclusive
	 * @since 2021-03-22
	 */
	public final int getBeginningInclusive() {
		return this.beginningInclusive;
	}
	
	/**
	 * @return end of range, exclusive
	 * @since 2021-03-22
	 */
	public final int getEndExclusive() {
		return this.endExclusive;
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
	
	@Override
	public String toString() {
		return "[" + this.beginningInclusive + ", " + this.endExclusive + ")";
	}
}
