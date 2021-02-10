package tab2xml.parser;

import tab2xml.parser.Lexer.InvalidTokenException;

/**
 * An atomic token in the ASCII tablature
 * 
 * @author amir
 */
public class Token {
	private TokenType type;
	private String data;

	/**
	 * Construct a token with a specified token type.
	 * 
	 * @param type the type of this token
	 */
	public Token(TokenType type) {
		this.type = type;
	}

	/**
	 * Construct a token with a specified token type and its data.
	 * 
	 * @param type the type of token
	 * @param data the contents of the token
	 * @throws InvalidTokenException thrown if there is a mismatch between data and
	 *                               the token type
	 */
	public Token(TokenType type, String data) throws InvalidTokenException {
		this.type = type;
		if (type.matches(data))
			this.data = data;
		else
			throw new InvalidTokenException("Token mismatch.");
	}

	/**
	 * Return the value of this token.
	 * 
	 * @return the value contained within this token
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * Return the type of this token.
	 * 
	 * @return the type of this token
	 */
	public TokenType type() {
		return this.type;
	}

	/**
	 * Return a string representation of this token.
	 * 
	 * @return the string format of this token
	 */
	@Override
	public String toString() {
		return String.format("%s", data);
	}

}