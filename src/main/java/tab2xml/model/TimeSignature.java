package tab2xml.model;

/**
 * A time signature.
 * 
 * @author Adrien Hopkins
 * @since 2021-03-22
 */
public final class TimeSignature {
	private final int upperNumeral;
	private final int lowerNumeral;

	/**
	 * @param upperNumeral upper numeral in the signature
	 * @param lowerNumeral lower numeral in the signature
	 * @since 2021-03-22
	 */
	public TimeSignature(int upperNumeral, int lowerNumeral) {
		this.upperNumeral = upperNumeral;
		this.lowerNumeral = lowerNumeral;
	}

	/**
	 * @return lower numeral of signature
	 * @since 2021-03-22
	 */
	public final int getLowerNumeral() {
		return this.lowerNumeral;
	}

	/**
	 * @return upper numeral of signature
	 * @since 2021-03-22
	 */
	public final int getUpperNumeral() {
		return this.upperNumeral;
	}

	@Override
	public String toString() {
		return this.upperNumeral + ":" + this.lowerNumeral;
	}
}
