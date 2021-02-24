package tab2xml.model;

import tab2xml.exceptions.InvalidTokenException;

/**
 * An atomic token in the ASCII tablature
 * 
 * @author amir
 */
public class GToken {
	private TokenType type;
	private String data;

	/**
	 * Construct a token with a specified token type.
	 * 
	 * @param type the type of this token
	 */
	public GToken(TokenType type) {
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
	public GToken(TokenType type, String data){
		this.type = type;
		if (type.matches(data))
			this.data = data;
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