package tab2xml.parser;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String message) {
		super(message);
	}
}
