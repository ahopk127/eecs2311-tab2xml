// Generated from DrumTab.g4 by ANTLR 4.9.1

	package tab2xml.antlr; 
	

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DrumTabParser}.
 */
public interface DrumTabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void enterTune(DrumTabParser.TuneContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void exitTune(DrumTabParser.TuneContext ctx);
}