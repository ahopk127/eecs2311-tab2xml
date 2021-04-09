package tab2xml.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import tab2xml.model.Bar;
import tab2xml.model.ErrorToken;
import tab2xml.model.Instrument;

/**
 * A static utility class to validate well-formed tablature.
 * 
 * <p>
 * Validation tasks which prevent parsing:
 * <ul>
 * <li>Measure lines must end in '|'
 * <li>Every row in a measure is the same length
 * <li>Unrecognized sequences within staffs
 * </ul>
 * 
 * @author amir
 */
public final class InputValidation {
	/** The character that marks the end of a row. */
	private static final String ROW_END = "\n";

	/** A pattern for a general guitar item in a staff. */
	private static final Pattern GUITAR_ITEM = Pattern
			.compile("[ \t]*([a-gA-G]#?)?[ \t]*\\*?(\\||\\d+)?\\|\\|?\\*?|[ \t]*(-?([^-|*\r\n]+)-?)[ \t]*");

	/** A pattern for a general drum item in a staff. */
	private static final Pattern DRUM_ITEM = Pattern.compile(
			"\\*?(\\||[^-\r\n]+)?\\|\\|?\\*?|[ \t]*[ABCcDdEFHhLMOPRSTt]{2}[ \t]*[|]|[ \t]*(-?([^-|*\r\n]+)-)[ \t]*");

	/** A list of general score errors which halt parsing */
	private static final List<ErrorToken> scoreErrors = new LinkedList<>();

	/** A list of staff errors which halt parsing */
	private static final List<ErrorToken> staffErrors = new LinkedList<>();

	/** This is a static utility class for validation. */
	private InputValidation() {
		throw new AssertionError();
	}

	/**
	 * Makes sure that a given input is well-formed tablature for the respective
	 * instrument.
	 * 
	 * @param input      the input to validate
	 * @param instrument the given instrument of this tablature
	 */
	public static void validate(String input, Instrument instrument) {
		if (scoreErrors != null)
			scoreErrors.clear();
		if (staffErrors != null)
			staffErrors.clear();
		switch (instrument) {
		case GUITAR:
		case BASS:
			validateGuitarBass(input);
			break;
		case DRUM:
			validateDrum(input);
			break;
		default:
			break;
		}
	}

	private static void validateGuitarBass(String input) {
		Matcher staffMatcher = Parser.guitarPatterGreedy.matcher(input);
		Matcher itemMatcher = GUITAR_ITEM.matcher(input);

		int staffNum = 1;
		while (staffMatcher.find()) {
			String str = staffMatcher.group(0);
			String[] arr = str.split(ROW_END);

			// score errors
			List<MatchResult> staffItems = itemMatcher.results()
					.filter(res -> res.start() >= staffMatcher.start() && res.end() <= staffMatcher.end())
					.collect(Collectors.toList());

			for (MatchResult res : staffItems) {
				String item = res.group(4);
				if (item == null)
					item = res.group(0);
				if (GuitarTokens.isValid(item))
					continue;
				scoreErrors.add(
						new ErrorToken(String.format("Invalid sequence: '%s'", item), item, res.start(4), res.end(4)));
			}

			// staff errors
			if (!equalLineStart(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalNumberOfMeasures(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalWidthOfLines(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalBarPositions(arr, staffNum, staffMatcher.toMatchResult())) {
			}
			staffNum++;
			itemMatcher.reset();
		}
	}

	private static void validateDrum(String input) {
		Matcher staffMatcher = Parser.drumPattern.matcher(input);
		Matcher itemMatcher = DRUM_ITEM.matcher(input);

		int staffNum = 1;
		while (staffMatcher.find()) {
			String str = staffMatcher.group(0);
			String[] arr = str.split(ROW_END);

			// score errors
			List<MatchResult> staffItems = itemMatcher.results()
					.filter(res -> res.start() >= staffMatcher.start() && res.end() <= staffMatcher.end())
					.collect(Collectors.toList());

			for (MatchResult res : staffItems) {
				String item = res.group(3);
				if (item == null) {
					item = res.group(0);
					if (DrumTokens.isValid(item))
						continue;
					scoreErrors.add(new ErrorToken(String.format("Invalid sequence: '%s'", item), item, res.start(0),
							res.end(0)));
				} else {
					for (int i = 0; i < item.length(); i++) {
						String subItem = item.substring(i, i + 1);
						if (DrumTokens.isValid(subItem))
							continue;
						scoreErrors.add(new ErrorToken(String.format("Invalid sequence: '%s'", subItem), subItem,
								res.start(3), res.start(3) + i + 1));
					}
				}
			}

			// staff errors
			if (!equalLineStart(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalNumberOfMeasures(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalWidthOfLines(arr, staffNum, staffMatcher.toMatchResult())) {
			} else if (!equalBarPositions(arr, staffNum, staffMatcher.toMatchResult())) {
			}
			staffNum++;
			itemMatcher.reset();
		}
	}

	private static boolean equalLineStart(String[] arr, int staffNum, MatchResult matchResult) {
		int start = -1;
		int prev = -1;
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			int c = 0;
			while (Character.isWhitespace(str.charAt(c)))
				c++;
			start = c - 1;
			if (prev != -1 && start != prev) {
				ErrorToken error = new ErrorToken();
				error.setMessage(String.format("Please align lines of staff %d", staffNum));
				error.setData(String.format("%d=/=%d", start, prev));
				error.setStart(matchResult.start());
				error.setStop(matchResult.end());
				staffErrors.add(error);
				return false;
			}
			prev = start;
		}
		return true;
	}

	private static boolean equalNumberOfMeasures(String[] arr, int staffNum, MatchResult matchResult) {
		int start = -1;
		int prev = -1;
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			Matcher m = Bar.pattern.matcher(str);
			start = (int) m.results().count();
			if (prev != -1 && start != prev) {
				ErrorToken error = new ErrorToken();
				error.setMessage(String.format("Please make the number of measures of staff %d consistent", staffNum));
				error.setData(String.format("%d=/=%d", start, prev));
				error.setStart(matchResult.start());
				error.setStop(matchResult.end());
				staffErrors.add(error);
				return false;
			}
			prev = start;
		}
		return true;
	}

	private static boolean equalWidthOfLines(String[] arr, int staffNum, MatchResult matchResult) {
		int prevWidth = -1;
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			String strip = str.strip();
			strip = strip.substring(0, strip.lastIndexOf('|') + 1);
			if (prevWidth != -1 && prevWidth != strip.length()) {
				ErrorToken error = new ErrorToken();
				error.setMessage(String.format("Please make the line widths of staff %d consistent", staffNum));
				error.setData(String.format("%d=/=%d", strip.length(), prevWidth));
				error.setStart(matchResult.start());
				error.setStop(matchResult.end());
				staffErrors.add(error);
				return false;
			}
			prevWidth = strip.length();
		}
		return true;
	}

	private static boolean equalBarPositions(String[] arr, int staffNum, MatchResult matchResult) {
		List<Integer> positions = null;
		List<Integer> prevPositions = null;
		for (int i = 0; i < arr.length; i++) {
			String str = arr[i];
			Matcher m = Bar.pattern.matcher(str);
			positions = m.results().map(res -> res.start() + res.group().lastIndexOf('|')).collect(Collectors.toList());
			if (prevPositions != null) {
				if (prevPositions.size() != positions.size()) {
					ErrorToken error = new ErrorToken();
					error.setMessage(
							String.format("Please make the number of measures of staff %d consistent", staffNum));
					error.setData(String.format("%d=/=%d", positions.size(), prevPositions.size()));
					error.setStart(matchResult.start());
					error.setStop(matchResult.end());
					staffErrors.add(error);
					return false;
				}
				for (int j = 0; j < positions.size(); j++) {
					if (!prevPositions.get(j).equals(positions.get(j))) {
						ErrorToken error = new ErrorToken();
						error.setMessage(String.format("Please align the measures of staff %d", staffNum));
						error.setData(String.format("%d=/=%d", positions.get(j), prevPositions.get(j)));
						error.setStart(matchResult.start());
						error.setStop(matchResult.end());
						staffErrors.add(error);
						return false;
					}
				}
			}
			prevPositions = new LinkedList<>(positions);
		}
		return true;
	}

	/**
	 * 
	 * @param error
	 * @return
	 */
	public static boolean containsError(ErrorToken error, String input) {
		return input.contains(error.getData());
	}
	
	/**
	 * @return true if the given input's score is well-formed tablature
	 */
	public static boolean isValidScore() {
		return scoreErrors.isEmpty();
	}

	/**
	 * @return true if the given input's staffs are well-formed tablature
	 */
	public static boolean isValidStaffs() {
		return staffErrors.isEmpty();
	}

	/**
	 * Get a list of ({@code ErrorToken} objects to represent a general error in a
	 * score.
	 * 
	 * @return a list of general score errors
	 */
	public static List<ErrorToken> getScoreErrors() {
		return new LinkedList<>(scoreErrors);
	}

	/**
	 * Get a list of ({@code ErrorToken} objects to represent a general error in a
	 * score.
	 * 
	 * @return a list of general score errors
	 */
	public static List<ErrorToken> getStaffErrors() {
		return new LinkedList<>(staffErrors);
	}
}