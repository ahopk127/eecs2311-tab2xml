package tab2xml.model;

/**
 * A static utility class with methods to handle narrowing to a measure.
 *
 * @since 2021-03-22
 */
public final class MeasureNarrowing {
	/**
	 * Extracts a range of measures from a text tab, and returns them as a
	 * String.
	 *
	 * @param textTab      text tab to extract from
	 * @param measureStart first measure to extract, inclusive
	 * @param measureEnd   last measure to extract, inclusive
	 * @return extracted measures
	 * @since 2021-03-22
	 */
	public static String extractMeasureRange(String textTab, int measureStart,
			int measureEnd) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * Accepts a text tab, and returns the text tab with a set of measures
	 * replaced.
	 *
	 * @param textTab        original text tab
	 * @param measureStart   first measure to replace, inclusive
	 * @param measureEnd     last measure to replace, inclusive
	 * @param newMeasureText new text to replace measures with
	 * @return text tab with measures replaced
	 * @since 2021-03-22
	 */
	public static String replaceMeasureRange(String textTab, int measureStart,
			int measureEnd, String newMeasureText) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * This is a static utility class, you shouldn't be able to get any measures
	 */
	private MeasureNarrowing() {
		throw new AssertionError();
	}
}
