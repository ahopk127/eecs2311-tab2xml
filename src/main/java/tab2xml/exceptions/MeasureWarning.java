package tab2xml.exceptions;

import java.util.Optional;

import tab2xml.model.ErrorToken;

/**
 * A warning describing a measure in a score.
 * 
 * @author amir
 */
public class MeasureWarning extends ErrorToken implements ParsingWarning {
	/**
	 * Construct an measure warning with specified attributes.
	 * 
	 * @param message the message describing this error token
	 * @param data    the value of this error token
	 * @param start   the index of the first character of this error token in the
	 *                input stream
	 * @param stop    the index of the last character of this error token in the
	 *                input stream
	 */
	public MeasureWarning(String message, String data, int start, int stop) {
		super(message, data, start, stop);
	}

	@Override
	public Optional<ErrorToken> getLocation() {
		return Optional.ofNullable(this);
	}
}
