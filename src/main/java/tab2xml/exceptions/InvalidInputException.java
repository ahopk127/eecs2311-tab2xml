package tab2xml.exceptions;

/**
 * An exception which to thrown when invalid input occurs.
 * 
 * @author amir
 */
public class InvalidInputException extends Exception {
	/**
	 * Serializing ID for {@code InvalidInputException}
	 */
	private static final long serialVersionUID = -4619748108152754311L;
	
	/**
	 * Construct an exception with a specified message.
	 * 
	 * @param message the message to display when thrown
	 */
	public InvalidInputException(String message) {
		super(message);
	}
}
