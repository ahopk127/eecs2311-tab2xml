package tab2xml.parser;

import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;

/**
 * List of guitar tablature token types and their regular expressions.
 * 
 * @author amir
 */
public enum GuitarTokens {
	// expandable along with grammar
	BAR("\\*?(\\||\\d{1,2})?\\|\\|?\\*?"), TUNE("[ \t]*([a-gA-G]#?)?[ \t]*[|]"), FRET("\\d{1,2}"),
	HARMONIC("\\[\\d{1,2}\\]"), PULLOFF("g?\\d{1,2}p\\d{1,2}"), HAMMERON("g?\\d{1,2}h\\d{1,2}"),
	HAMMERCPULL("g?\\d{1,2}[hp]\\d{1,2}([hp]\\d{1,2})+"), SLIDE("g?\\d{1,2}s\\d{1,2}"),
	FRETBARFRET("(\\d{1,2})?\\|\\|?(\\d{1,2})?");

	public final Pattern pattern;

	/**
	 * Set pattern for the respective token type.
	 * 
	 * @param pattern a pattern for a token type
	 */
	private GuitarTokens(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	/**
	 * Matches the input to every token type, and returns the first type that
	 * matches.
	 * 
	 * @param token token to test
	 * @return type of token
	 * @throws InvalidTokenException
	 * @since 2021-01-20
	 */
	public static boolean isValid(String token) {
		for (GuitarTokens type : GuitarTokens.values()) {
			if (type.matches(token)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if a token is an instance of its type.
	 * 
	 * @param token token as a string
	 * @return returns true if the specified token matches this guitar token
	 */
	public boolean matches(String token) {
		return this.pattern.matcher(token).matches();
	}
}