package tab2xml.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * A static utility class with methods to handle narrowing to a measure.
 * 
 * <p>
 * Assumptions made by this class about text tabs:
 * <ul>
 * <li>Measures start and end with '|'
 * <li>Every row in a measure is the same length
 * <li>Measure counting starts at 1
 * <li>Lines that do not contain '|' can be safely ignored (treated as empty)
 * <li>Tab lines are separated by at least one empty line
 * </ul>
 * 
 * <p>
 * Notes:
 * <ul>
 * <li><b>This class only works with strings that end in a newline.</b>
 * <li><b>This class only works with Unix line endings ({@code \n}).</b> Use
 * {@code string.replaceAll("\\r\\n", "\n")} to convert from Windows line
 * endings to Unix.
 * </ul>
 * 
 * @author Adrien Hopkins
 * @since 2021-03-22
 */
public final class MeasureNarrowing {
	/**
	 * A rectangular (row/column) position in a {@code String}.
	 *
	 * @since 2021-03-26
	 */
	static final class StringPosition implements Comparable<StringPosition> {
		/** Comparator used for natural order */
		private static final Comparator<StringPosition> naturalOrder = Comparator
				.comparingInt((StringPosition p) -> p.row)
				.thenComparingInt(p -> p.col);
		
		/**
		 * Converts a String index into a {@code StringPosition}.
		 *
		 * @param string string to find index of
		 * @param index  index to convert
		 * @return string position
		 * @since 2021-03-26
		 */
		public static final StringPosition fromIndex(String string, int index) {
			int startOfRow = 0;
			int endOfRow = -1;
			int rowNum = -1;
			
			while (endOfRow < index) {
				startOfRow = endOfRow + 1;
				endOfRow = string.indexOf(ROW_END, startOfRow);
				rowNum++;
			}
			
			return new StringPosition(rowNum, index - startOfRow);
		}
		
		final int row;
		final int col;
		
		public StringPosition(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public int compareTo(StringPosition o) {
			return naturalOrder.compare(this, o);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (this.getClass() != obj.getClass())
				return false;
			final StringPosition other = (StringPosition) obj;
			return this.col == other.col && this.row == other.row;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.col, this.row);
		}
		
		/**
		 * Converts this {@code StringPosition} to an index in a string.
		 *
		 * @param string string to use for conversion
		 * @return index
		 * @since 2021-03-26
		 */
		public int toIndex(String string) {
			// find position of first character in row by repeatedly searching for
			// ROW_END
			int startOfRow = 0;
			for (int i = 0; i < this.row; i++) {
				startOfRow = string.indexOf(ROW_END, startOfRow) + 1;
			}
			
			// now that we have the start of the row, we can easily calculate the
			// position
			return startOfRow + this.col;
		}
		
		@Override
		public String toString() {
			return "(row=" + this.row + ", col=" + this.col + ")";
		}
	}
	
	/** The minimum length of a measure, excluding surrounding '|' characters */
	private static final int MIN_MEASURE_LENGTH = 1;
	
	/**
	 * The character that separates measures.
	 */
	private static final char MEASURE_SEPARATOR = '|';
	
	/**
	 * The character that marks the end of a row.
	 */
	private static final String ROW_END = "\n";
	
	/**
	 * Finds the bottom right corner of a measure in a text tab.
	 *
	 * @param textTab text tab to search in
	 * @param measure measure to search for
	 * @return position of bottom-right corner
	 * @since 2021-03-26
	 */
	static StringPosition bottomRightCorner(String textTab, int measure) {
		// do not include the last character (in case it is a terminating newline)
		final int startOfLastRow = textTab.lastIndexOf(ROW_END,
				textTab.length() - 2) + 1;
		
		int endOfMeasure = textTab.indexOf(MEASURE_SEPARATOR, startOfLastRow);
		
		for (int i = 0; i < measure; i++) {
			endOfMeasure = textTab.indexOf(MEASURE_SEPARATOR,
					endOfMeasure + MIN_MEASURE_LENGTH + 1);
		}
		
		// in case it's a repeated measure, find the REAL ending | character
		while (textTab.charAt(endOfMeasure + 1) == MEASURE_SEPARATOR) {
			endOfMeasure++;
		}
		
		return StringPosition.fromIndex(textTab, endOfMeasure);
	}
	
	/**
	 * Does the reverse of linearize().
	 * 
	 * @param textTab       linearized tab to delinearize
	 * @param maxLineLength maximum length of line allowed
	 * @return delinearized tab
	 *
	 * @since 2021-03-29
	 */
	static String delinearize(String textTab, int maxLineLength) {
		final String[] linearizedLines = textTab.split(ROW_END);
		
		final List<String> lines = new ArrayList<>();
		final int prefixLength = textTab.indexOf(MEASURE_SEPARATOR) + 1;
		
		// find the beginning and end of each set of lines within the tab
		int beginningInclusive = prefixLength;
		int endExclusive = prefixLength;
		
		while (beginningInclusive < linearizedLines[0].length()) {
			// find end of current line
			int nextMeasure = beginningInclusive;
			do {
				endExclusive = nextMeasure;
				nextMeasure = linearizedLines[0].indexOf(MEASURE_SEPARATOR,
						nextMeasure) + 1;
			} while (nextMeasure != 0 && prefixLength + nextMeasure
					- beginningInclusive <= maxLineLength);
			
			// we can get the lines now
			for (int i = 0; i < linearizedLines.length; i++) {
				final String prefix = linearizedLines[i].substring(0, prefixLength);
				final String line = linearizedLines[i].substring(beginningInclusive,
						endExclusive);
				
				lines.add(prefix + line);
			}
			lines.add("");
			beginningInclusive = endExclusive;
		}
		
		return String.join(ROW_END, lines.toArray(new String[0]));
	}
	
	/**
	 * Extracts a range of measures from a text tab, and returns them as a
	 * String. The surrounding '|' characters will be included in the output.
	 *
	 * @param textTab      text tab to extract from
	 * @param measureStart first measure to extract, inclusive
	 * @param measureEnd   last measure to extract, inclusive
	 * @return extracted measures
	 * @since 2021-03-22
	 */
	public static String extractMeasureRange(String textTab, int measureStart,
			int measureEnd) {
		final int maxLineLength = textTab.lines().mapToInt(String::length).max()
				.orElse(0);
		
		final String linearized = linearize(textTab);
		final StringPosition topLeft = topLeftCorner(linearized, measureStart);
		final StringPosition bottomRight = bottomRightCorner(linearized,
				measureEnd);
		
		final String linearizedResult = extractRectangle(linearized, topLeft.row,
				topLeft.col, bottomRight.row, bottomRight.col);
		return delinearize(linearizedResult, maxLineLength);
	}
	
	/**
	 * Extracts a rectangular region from a String. For example, if s is:
	 *
	 * <pre>
	 * e|-----2--------|-----2--------|--0-----3--0--|--------------|--2--------0--|
	 * B|--------------|--------1-----|--0-----0--3--|--------------|--------1-----|
	 * G|--------------|-----------0--|-----------2--|-----2--2--4--|--------------|
	 * D|-----------2--|-----0--------|--2--2--------|-----0--4-----|-----2--------|
	 * A|--3--------2--|--0--------2--|--------------|--0-----2-----|--2--------0--|
	 * E|--0--0--------|--------------|--------------|--------------|--------------|
	 * </pre>
	 * 
	 * then {@code extractRectangle(s, 0, 16, 5, 31)} will return:
	 * 
	 * <pre>
	 * |-----2--------|
	 * |--------1-----|
	 * |-----------0--|
	 * |-----0--------|
	 * |--0--------2--|
	 * |--------------|
	 * </pre>
	 *
	 * @param s        string to extract from
	 * @param beginRow row of top-left corner of rectangle
	 * @param beginCol column of top-left corner of rectangle
	 * @param endRow   row of bottom-right corner of rectangle
	 * @param endCol   column of botton-right corner of rectangle
	 * @return extracted rectangle
	 * @since 2021-03-26
	 */
	private static final String extractRectangle(String s, int beginRow,
			int beginCol, int endRow, int endCol) {
		final StringBuilder rectangle = new StringBuilder();
		
		for (int row = beginRow; row <= endRow; row++) {
			// calculate row string (e.g. |-----2--------|)
			final StringPosition beginning = new StringPosition(row, beginCol);
			final StringPosition end = new StringPosition(row, endCol);
			final String rowString = s.substring(beginning.toIndex(s),
					end.toIndex(s) + 1);
			
			// add it to rectangle
			rectangle.append(rowString);
			rectangle.append(ROW_END);
		}
		
		return rectangle.toString();
	}
	
	/**
	 * @return true if {@code line} contains measure text
	 * @since 2021-03-29
	 */
	private static boolean isMeasureLine(String line) {
		return line.contains("|");
	}
	
	/**
	 * "Linearizes" a text tab, moving all of its "lines" into one.
	 *
	 * @param textTab tab to linearize
	 * @return linearized tab
	 * @since 2021-03-29
	 */
	static String linearize(String textTab) {
		final String[] oldLines = textTab.split(ROW_END);
		
		// determine number of strings/lines in tab
		int numLines = -1;
		for (int i = 0; i < oldLines.length; i++) {
			if (!isMeasureLine(oldLines[i])) {
				numLines = i;
				break;
			}
		}
		
		if (numLines == -1)
			// the tab is already linear
			return textTab;
		else {
			// create new lines using stringbuilders
			final StringBuilder[] newLines = new StringBuilder[numLines];
			for (int i = 0; i < newLines.length; i++) {
				newLines[i] = new StringBuilder(oldLines[i]);
			}
			
			// add the stuff below to these stringbuilders
			int newLine = 0; // new line that current line will be added to
			
			// start at first line not already included in the builders
			for (int i = numLines; i < oldLines.length; i++) {
				if (isMeasureLine(oldLines[i])) {
					// strip line of stuff before and after the measures
					// specifically, I want to include the region from after the
					// first | to the last |
					// I am excluding the first | because the previous line will end
					// in | and I do not want to create a double-|
					final String oldLine = oldLines[i];
					final String strippedLine = oldLine.substring(
							oldLine.indexOf(MEASURE_SEPARATOR) + 1,
							oldLine.lastIndexOf(MEASURE_SEPARATOR) + 1);
					
					newLines[newLine].append(strippedLine);
					newLine = (newLine + 1) % numLines;
				}
			}
			
			// combine lines
			final int totalSize = Arrays.stream(newLines)
					.mapToInt(StringBuilder::length).sum() + numLines;
			final StringBuilder combined = new StringBuilder(totalSize);
			
			for (final StringBuilder line : newLines) {
				combined.append(line);
				combined.append(ROW_END);
			}
			
			return combined.toString();
		}
	}
	
	/**
	 * Accepts a text tab, and returns the text tab with a set of measures
	 * replaced. The new measure text should have '|' on both sides.
	 * 
	 * <p>
	 * <b>Warning: this method will replace any text that is not part of a
	 * measure or tuning (i.e. text on non-measure lines).</b>
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
		final int maxLineLength = textTab.lines().mapToInt(String::length).max()
				.orElse(0);
		
		final String linearizedTab = linearize(textTab);
		final String linearizedReplacement = linearize(newMeasureText);
		
		final StringPosition topLeft = topLeftCorner(linearizedTab, measureStart);
		final StringPosition bottomRight = bottomRightCorner(linearizedTab,
				measureEnd);
		
		final String linearizedOutput = replaceRectangle(linearizedTab,
				topLeft.row, topLeft.col, bottomRight.row, bottomRight.col,
				linearizedReplacement);
		return delinearize(linearizedOutput, maxLineLength);
	}
	
	/**
	 * Replaces a rectangular region in a String, returning the new String. For
	 * example, if s is:
	 *
	 * <pre>
	 * e|-----2--------|-----2--------|--0-----3--0--|--------------|--2--------0--|
	 * B|--------------|--------1-----|--0-----0--3--|--------------|--------1-----|
	 * G|--------------|-----------0--|-----------2--|-----2--2--4--|--------------|
	 * D|-----------2--|-----0--------|--2--2--------|-----0--4-----|-----2--------|
	 * A|--3--------2--|--0--------2--|--------------|--0-----2-----|--2--------0--|
	 * E|--0--0--------|--------------|--------------|--------------|--------------|
	 * </pre>
	 * 
	 * and newRectangle is:
	 * 
	 * <pre>
	 * |-----3--------|
	 * |-----5--1-----|
	 * |-----------0--|
	 * |-----0--8-----|
	 * |--0--------2--|
	 * |--------------|
	 * </pre>
	 * 
	 * then {@code replaceRectangle(s, 0, 16, 5, 31, newRectangle)} will be:
	 * 
	 * <pre>
	 * e|-----2--------|-----3--------|--0-----3--0--|--------------|--2--------0--|
	 * B|--------------|-----5--1-----|--0-----0--3--|--------------|--------1-----|
	 * G|--------------|-----------0--|-----------2--|-----2--2--4--|--------------|
	 * D|-----------2--|-----0--8-----|--2--2--------|-----0--4-----|-----2--------|
	 * A|--3--------2--|--0--------2--|--------------|--0-----2-----|--2--------0--|
	 * E|--0--0--------|--------------|--------------|--------------|--------------|
	 * </pre>
	 *
	 * @param s            string to replace rectangle in
	 * @param beginRow     row of top-left corner of rectangle
	 * @param beginCol     column of top-left corner of rectangle
	 * @param endRow       row of bottom-right corner of rectangle
	 * @param endCol       column of botton-right corner of rectangle
	 * @param newRectangle text to replace rectangular region with
	 * @return extracted rectangle
	 * @since 2021-03-26
	 */
	private static final String replaceRectangle(String s, int beginRow,
			int beginCol, int endRow, int endCol, String newRectangle) {
		final StringBuilder newTab = new StringBuilder();
		final String[] replaceRows = newRectangle.split("\n");
		
		for (int row = beginRow; row <= endRow; row++) {
			// calculate position of replacement (e.g. |-----2--------|)
			final int rowBeginning = new StringPosition(row, 0).toIndex(s);
			final StringPosition beginning = new StringPosition(row, beginCol);
			final StringPosition end = new StringPosition(row, endCol);
			
			// get parts of new row
			final String beforeReplace = s.substring(rowBeginning,
					beginning.toIndex(s));
			final String afterReplace = s.substring(end.toIndex(s) + 1,
					s.indexOf("\n", rowBeginning + 1));
			
			// assemble it in the StringBuilder
			newTab.append(beforeReplace);
			newTab.append(replaceRows[row - beginRow]);
			newTab.append(afterReplace);
			newTab.append("\n");
		}
		
		return newTab.toString();
	}
	
	/**
	 * Finds the top-left corner of a measure
	 *
	 * @param textTab text tab to look in
	 * @param measure measure number to look for
	 * @return position of top-left corner
	 * @since 2021-03-26
	 */
	static final StringPosition topLeftCorner(String textTab, int measure) {
		// start of first measure
		int startOfMeasure = textTab.indexOf(MEASURE_SEPARATOR);
		
		for (int i = 1; i < measure; i++) {
			startOfMeasure = textTab.indexOf(MEASURE_SEPARATOR,
					startOfMeasure + MIN_MEASURE_LENGTH + 1);
		}
		
		return StringPosition.fromIndex(textTab, startOfMeasure);
	}
	
	/**
	 * This is a static utility class, you shouldn't be able to get any measures
	 */
	private MeasureNarrowing() {
		throw new AssertionError();
	}
}
