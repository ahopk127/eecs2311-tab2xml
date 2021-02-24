package tab2xml.exceptions;

/**
 * Thrown if invalid token occurs.
 */
@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {
	public InvalidTokenException(String message) {
		super(message);
	}
}