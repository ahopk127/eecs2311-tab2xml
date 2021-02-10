package tab2xml.parser;

import tab2xml.parser.Lexer.InvalidTokenException;

/**
 * An atomic token in the ASCII tablature
 * 
 * @author amir
 *
 */
public class Token {
	private TokenType type;
	private String data;

	public Token(TokenType type) {
		this.type = type;
	}

	/**
	 * Instantiate a token with a token type and its data.
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

	public String getData() {
		return this.data;
	}

	public TokenType type() {
		return this.type;
	}

	@Override
	public String toString() {
		return String.format("%s", data);
	}

}