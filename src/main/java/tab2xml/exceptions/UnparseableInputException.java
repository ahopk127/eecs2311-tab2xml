package tab2xml.exceptions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import tab2xml.model.guitar.ErrorToken;

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
	private static final String generateErrorMessage(LinkedList<ErrorToken> errors) {
		// stream code! converts each error to an error message then joins them
		// using \n as a delimiter
		return errors.stream().map(UnparseableInputException::singleErrorMessage).collect(Collectors.joining("\n"));
	}

	/**
	 * Gets an UnparseableInputException from a list of invalid tokens
	 *
	 * @since 2021-02-28
	 */
	public static UnparseableInputException get(LinkedList<ErrorToken> errors) {
		return new UnparseableInputException(new LinkedList<>(errors));
	}

	/**
	 * Creates an error message from an invalid token.
	 *
	 * @since 2021-02-28
	 */
	private static final String singleErrorMessage(ErrorToken error) {
		switch (error.getMesage()) {
		case "extraneous input '-' expecting {NOTE, '|', NEWLINE}":
		case "missing '|' at '-'":
			return String.format("Incomplete tune: line %d column %d", error.getLine(), error.getColumn());
		case "missing FRET_NUM at '-'":
			return String.format("Missing Fret: line %d column %d", error.getLine(), error.getColumn());
		case "extraneous input '\\n' expecting {'g', '|', '-', FRET_NUM, '['}":
			return String.format("Missing end of string: line %d column %d", error.getLine(), error.getColumn());
		case "extraneous input 'p' expecting FRET_NUM":
		case "extraneous input 'h' expecting FRET_NUM":
			return String.format("Expecting fret: line %d column %d", error.getLine(), error.getColumn());
		default:
			return error.getMesage();
		}
	}

	/**
	 * A list of all invalid tokens
	 */
	private final List<ErrorToken> errors;

	/**
	 * Creates the exception
	 *
	 * @param errors list of all invalid tokens
	 * @since 2021-02-28
	 */
	private UnparseableInputException(LinkedList<ErrorToken> errors) {
		super(generateErrorMessage(errors));
		this.errors = errors;
	}

	/**
	 * @return unmodifiable list of invalid tokens
	 * @since 2021-02-28
	 */
	public final List<ErrorToken> getErrors() {
		return Collections.unmodifiableList(this.errors);
	}

}
