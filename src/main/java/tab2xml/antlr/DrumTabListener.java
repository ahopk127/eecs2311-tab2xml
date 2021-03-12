// Generated from DrumTab.g4 by ANTLR 4.9.1

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
	 * Enter a parse tree produced by {@link DrumTabParser#strike}.
	 * @param ctx the parse tree
	 */
	void enterStrike(DrumTabParser.StrikeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#strike}.
	 * @param ctx the parse tree
	 */
	void exitStrike(DrumTabParser.StrikeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#accent}.
	 * @param ctx the parse tree
	 */
	void enterAccent(DrumTabParser.AccentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#accent}.
	 * @param ctx the parse tree
	 */
	void exitAccent(DrumTabParser.AccentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#ghost}.
	 * @param ctx the parse tree
	 */
	void enterGhost(DrumTabParser.GhostContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#ghost}.
	 * @param ctx the parse tree
	 */
	void exitGhost(DrumTabParser.GhostContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#roll}.
	 * @param ctx the parse tree
	 */
	void enterRoll(DrumTabParser.RollContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#roll}.
	 * @param ctx the parse tree
	 */
	void exitRoll(DrumTabParser.RollContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#choke}.
	 * @param ctx the parse tree
	 */
	void enterChoke(DrumTabParser.ChokeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#choke}.
	 * @param ctx the parse tree
	 */
	void exitChoke(DrumTabParser.ChokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#flam}.
	 * @param ctx the parse tree
	 */
	void enterFlam(DrumTabParser.FlamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#flam}.
	 * @param ctx the parse tree
	 */
	void exitFlam(DrumTabParser.FlamContext ctx);
}