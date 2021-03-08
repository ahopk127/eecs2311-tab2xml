package tab2xml;

/**
 * An immutable pair that contains two objects.
 *
 * @since 2021-03-05
 */
public final class ImmutablePair<T, U> {
	/**
	 * Gets a pair containing two elements.
	 *
	 * @param <T>    type of first element
	 * @param <U>    type of second element
	 * @param first  first element
	 * @param second second element
	 * @return pair
	 * @since 2021-03-05
	 */
	public static <T, U> ImmutablePair<T, U> of(T first, U second) {
		return new ImmutablePair<>(first, second);
	}
	
	private final T first;
	private final U second;
	
	/**
	 * Creates a pair.
	 *
	 * @param first  first element in pair
	 * @param second second element in pair
	 * @since 2021-03-05
	 */
	private ImmutablePair(T first, U second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * @return the first element
	 * @since 2021-03-05
	 */
	public final T getFirst() {
		return this.first;
	}
	
	/**
	 * @return the second element
	 * @since 2021-03-05
	 */
	public final U getSecond() {
		return this.second;
	}
}
