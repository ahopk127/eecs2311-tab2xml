package tab2xml.parser;



/**
 * An exception which to thrown when invalid input occurs.
 * 
 * @author amir
 */
@SuppressWarnings("serial")
public class InvalidInputException extends Exception {

	/**
	 * Construct an exception with a specified message.
	 * 
	 * @param message the message to display when thrown
	 */
	public InvalidInputException(String message) {
		super(message);
	}
}
