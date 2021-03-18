package tab2xml.listeners;

import java.util.LinkedHashMap;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

/**
 * A listener that tracks syntax errors.
 * 
 * @author amir
 *
 */
public class ErrorListener extends BaseErrorListener {
	private static LinkedHashMap<Token, String> errors = new LinkedHashMap<>();

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		errors.put(((Token) offendingSymbol), msg);
		System.out.println(msg);
	}

	/**
	 * Get the list of syntax errors
	 * 
	 * @return the list of errors caught
	 */
	public LinkedHashMap<Token, String> getErrors() {
		return errors;
	}

	/**
	 * Return if the listener has caught any syntax errors.
	 * 
	 * @return {@code true} if the listener has errors, otherwise false
	 */
	public boolean hasErrors() {
		return errors.size() > 0;
	}
}
