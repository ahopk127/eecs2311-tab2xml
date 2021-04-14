package tab2xml.parser;

import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;

/**
 * List of drum tablature token types and their regular expressions.
 * 
 * @author amir
 */
public enum DrumTokens {
	// expandable along with grammar
	BAR("\\*?(\\||[^-\r\n]+)?\\|\\|?\\*?"),
	DRUMPART("[ \t]*(BD|Bd|SS|SD|ES|FT|HH|Ft|PH|LT|OH|LM|MT|CC|HT|RD|Ch|RB|TA|SC|CB|Cc|Rd|HC|LC)[ \t]*"),
	DRUMTYPE("[ \t]*([ABCcDdEFHhLMOPRSTt]{2})[ \t]*[|]"), DRUMS("[oOgfdbB@]"), CYMBALS("[xXo#scbp]");

	public final Pattern pattern;

	/**
	 * Set pattern for the respective token type.
	 * 
	 * @param pattern a pattern for a token type
	 */
	private DrumTokens(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	/**
	 * Matches the input to every token type, and returns the first type that
	 * matches.
	 * 
	 * @param token token to test
	 * @return true if the token matches one drum token
	 * @throws InvalidTokenException
	 * @since 2021-01-20
	 */
	public static boolean isValid(String token) {
		for (DrumTokens type : DrumTokens.values()) {
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
	 * @return returns true if the specified token matches this drum token
	 */
	public boolean matches(String token) {
		return this.pattern.matcher(token).matches();
	}
}