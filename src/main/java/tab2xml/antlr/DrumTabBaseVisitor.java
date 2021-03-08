// Generated from DrumTab.g4 by ANTLR 4.9.1

	package tab2xml.antlr; 
	

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link DrumTabVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class DrumTabBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements DrumTabVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitTune(DrumTabParser.TuneContext ctx) { return visitChildren(ctx); }
}