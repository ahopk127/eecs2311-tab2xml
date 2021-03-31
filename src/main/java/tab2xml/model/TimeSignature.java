package tab2xml.model;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A time signature.
 * 
 * @author Adrien Hopkins
 * @since 2021-03-22
 */
public final class TimeSignature {
	/** The common time signature (4:4) */
	public static final TimeSignature COMMON_TIME = valueOf(4, 4);
	
	/** The string that separates the numerals in a time signature string */
	private static final String SIGNATURE_SEPARATOR = ":";
	
	/** A pattern that matches time signatures */
	private static final Pattern TIME_SIGNATURE = Pattern
			.compile("([+-]?\\d+)" + SIGNATURE_SEPARATOR + "([+-]?\\d+)");
	
	/**
	 * Gets a time signature from a string in the same format as produced by
	 * {@link #toString}
	 *
	 * @return time signature, or empty Optional if string is not in correct
	 *         format
	 * @throws IllegalArgumentException if either numeral is negative or zero
	 * @since 2021-03-31
	 */
	public static Optional<TimeSignature> fromString(String signatureString) {
		final Matcher match = TIME_SIGNATURE.matcher(signatureString);
		
		if (match.matches()) {
			final int top = Integer.valueOf(match.group(1));
			final int bottom = Integer.valueOf(match.group(2));
			return Optional.of(valueOf(top, bottom));
		} else
			return Optional.empty();
	}
	
	/**
	 * Gets a time signature.
	 *
	 * @param upperNumeral upper numeral in signature, must be positive
	 * @param lowerNumeral lower numeral in signature, must be positive
	 * @return time signature
	 * @throws IllegalArgumentException if either numeral is negative or zero
	 * @since 2021-03-31
	 */
	public static TimeSignature valueOf(int upperNumeral, int lowerNumeral) {
		if (upperNumeral <= 0)
			throw new IllegalArgumentException("Upper numeral must be positive");
		if (lowerNumeral <= 0)
			throw new IllegalArgumentException("Lower numeral must be positive");
		return new TimeSignature(upperNumeral, lowerNumeral);
	}
	
	private final int upperNumeral;
	private final int lowerNumeral;
	
	/**
	 * @param upperNumeral upper numeral in the signature
	 * @param lowerNumeral lower numeral in the signature
	 * @since 2021-03-22
	 */
	private TimeSignature(int upperNumeral, int lowerNumeral) {
		this.upperNumeral = upperNumeral;
		this.lowerNumeral = lowerNumeral;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TimeSignature))
			return false;
		final TimeSignature other = (TimeSignature) obj;
		return this.lowerNumeral == other.lowerNumeral
				&& this.upperNumeral == other.upperNumeral;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.lowerNumeral, this.upperNumeral);
	}
	
	/**
	 * @return lower numeral of signature
	 * @since 2021-03-22
	 */
	public final int lowerNumeral() {
		return this.lowerNumeral;
	}
	
	/**
	 * Returns a string representing this time signature. It will be composed of
	 * the decimal string representation of the upper numeral, then the character
	 * ':', then the lower numeral, with no spaces separating them. For example,
	 * the string representation of {@link #COMMON_TIME} is "4:4".
	 */
	@Override
	public String toString() {
		return this.upperNumeral + SIGNATURE_SEPARATOR + this.lowerNumeral;
	}
	
	/**
	 * @return upper numeral of signature
	 * @since 2021-03-22
	 */
	public final int upperNumeral() {
		return this.upperNumeral;
	}
}
