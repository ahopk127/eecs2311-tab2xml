package tab2xml.exceptions;

/**
 * Thrown if invalid token occurs.
 */
public class InvalidTokenException extends Exception {
	/**
	 * Serializing ID for {@code InvalidTokenException}
	 */
	private static final long serialVersionUID = -1097452365856785055L;
	
	public InvalidTokenException(String message) {
		super(message);
	}
}