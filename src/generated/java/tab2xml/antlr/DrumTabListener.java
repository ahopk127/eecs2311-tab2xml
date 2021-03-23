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
	 * Enter a parse tree produced by {@link DrumTabParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(DrumTabParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(DrumTabParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#lineItems}.
	 * @param ctx the parse tree
	 */
	void enterLineItems(DrumTabParser.LineItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#lineItems}.
	 * @param ctx the parse tree
	 */
	void exitLineItems(DrumTabParser.LineItemsContext ctx);
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
	 * Enter a parse tree produced by {@link DrumTabParser#cymbal}.
	 * @param ctx the parse tree
	 */
	void enterCymbal(DrumTabParser.CymbalContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#cymbal}.
	 * @param ctx the parse tree
	 */
	void exitCymbal(DrumTabParser.CymbalContext ctx);
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
}