// Generated from DrumTab.g4 by ANTLR 4.9.1

	package tab2xml.antlr; 
	

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DrumTabParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DrumTabVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#tune}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTune(DrumTabParser.TuneContext ctx);
}