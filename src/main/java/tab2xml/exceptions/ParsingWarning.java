package tab2xml.exceptions;

import java.util.Optional;

import org.antlr.v4.runtime.Token;

/**
 * A warning describing a minor error in the input, which is not serious enough
 * to prevent parsing.
 *
 * @since 2021-03-05
 */
public interface ParsingWarning {
	/**
	 * @return location of warning in input text
	 * @since 2021-03-05
	 */
	public Optional<Token> getLocation();
	
	/**
	 * @return a message describing the warning to the user
	 * @since 2021-03-05
	 */
	@Override
	public String toString();
}
