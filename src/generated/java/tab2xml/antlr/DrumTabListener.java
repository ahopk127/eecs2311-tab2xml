// Generated from tab2xml\antlr\DrumTab.g4 by ANTLR 4.9.2

	package tab2xml.antlr; 

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DrumTabParser}.
 */
public interface DrumTabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void enterSheet(DrumTabParser.SheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void exitSheet(DrumTabParser.SheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void enterStaff(DrumTabParser.StaffContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void exitStaff(DrumTabParser.StaffContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#drumLine}.
	 * @param ctx the parse tree
	 */
	void enterDrumLine(DrumTabParser.DrumLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#drumLine}.
	 * @param ctx the parse tree
	 */
	void exitDrumLine(DrumTabParser.DrumLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#cymbalLine}.
	 * @param ctx the parse tree
	 */
	void enterCymbalLine(DrumTabParser.CymbalLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#cymbalLine}.
	 * @param ctx the parse tree
	 */
	void exitCymbalLine(DrumTabParser.CymbalLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#drumActions}.
	 * @param ctx the parse tree
	 */
	void enterDrumActions(DrumTabParser.DrumActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#drumActions}.
	 * @param ctx the parse tree
	 */
	void exitDrumActions(DrumTabParser.DrumActionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#cymbalActions}.
	 * @param ctx the parse tree
	 */
	void enterCymbalActions(DrumTabParser.CymbalActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#cymbalActions}.
	 * @param ctx the parse tree
	 */
	void exitCymbalActions(DrumTabParser.CymbalActionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#drumType}.
	 * @param ctx the parse tree
	 */
	void enterDrumType(DrumTabParser.DrumTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#drumType}.
	 * @param ctx the parse tree
	 */
	void exitDrumType(DrumTabParser.DrumTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#cymbalType}.
	 * @param ctx the parse tree
	 */
	void enterCymbalType(DrumTabParser.CymbalTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#cymbalType}.
	 * @param ctx the parse tree
	 */
	void exitCymbalType(DrumTabParser.CymbalTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#drum}.
	 * @param ctx the parse tree
	 */
	void enterDrum(DrumTabParser.DrumContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#drum}.
	 * @param ctx the parse tree
	 */
	void exitDrum(DrumTabParser.DrumContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#cymbal}.
	 * @param ctx the parse tree
	 */
	void enterCymbal(DrumTabParser.CymbalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#cymbal}.
	 * @param ctx the parse tree
	 */
	void exitCymbal(DrumTabParser.CymbalContext ctx);
}