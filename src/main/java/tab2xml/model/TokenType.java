package tab2xml.model;

import java.util.regex.Pattern;
import tab2xml.exceptions.InvalidTokenException;

/**
 * List of token types and their regular expressions.
 * 
 * @author amir
 */
public enum TokenType {
	HYPHEN("-"), BAR("\\|"), NOTE("[A-G]#?"), FRET("[1-2]?\\d"), HARMONIC("\\[[1-7]\\]"), PULLOFF("p"), HAMMERON("h"), SLIDE("s");

	public final Pattern pattern;

	private TokenType(String pattern) {
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
	public static TokenType getType(String token) throws InvalidTokenException {
		for (TokenType type : TokenType.values()) {
			if (type.matches(token)) {
				return type;
			}
		}
		throw new InvalidTokenException("Invalid token.");
	}

	/**
	 * Checks if a token is an instance of its type.
	 * 
	 * @param token token as a string
	 * @return returns true if the specified token matches the token type
	 */
	public boolean matches(String token) {
		return this.pattern.matcher(token).matches();
	}
}