package tab2xml.exceptions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.Token;

/**
 * Input is unparseable, and the location of the error is known.
 *
 * @since 2021-02-28
 */
public final class UnparseableInputException extends InvalidInputException {
	private static final long serialVersionUID = 2307637151275887207L;
	
	/**
	 * Generates an error message from a list of invalid tokens.
	 *
	 * @since 2021-02-28
	 */
	private static final String generateErrorMessage(List<Token> errors) {
		// stream code! converts each error to an error message then joins them
		// using \n as a delimiter
		return errors.stream().map(UnparseableInputException::singleErrorMessage)
				.collect(Collectors.joining("\n"));
	}
	
	/**
	 * Gets an UnparseableInputException from a list of invalid tokens
	 *
	 * @since 2021-02-28
	 */
	public static UnparseableInputException get(List<Token> errors) {
		return new UnparseableInputException(new LinkedList<>(errors));
	}
	
	/**
	 * Creates an error message from an invalid token.
	 *
	 * @since 2021-02-28
	 */
	private static final String singleErrorMessage(Token error) {
		return String.format("Invalid token \"%s\" at line %d, column %d",
				error.getText(), error.getLine(), error.getCharPositionInLine());
	}
	
	/**
	 * A list of all invalid tokens
	 */
	private final List<Token> errors;
	
	/**
	 * Creates the exception
	 *
	 * @param errors list of all invalid tokens
	 * @since 2021-02-28
	 */
	private UnparseableInputException(List<Token> errors) {
		super(generateErrorMessage(errors));
		this.errors = errors;
	}
	
	/**
	 * @return unmodifiable list of invalid tokens
	 * @since 2021-02-28
	 */
	public final List<Token> getErrors() {
		return Collections.unmodifiableList(this.errors);
	}
	
}
