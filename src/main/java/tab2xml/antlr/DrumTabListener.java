// Generated from DrumTab.g4 by ANTLR 4.4

	package tab2xml.antlr; 
	

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DrumTabParser}.
 */
public interface DrumTabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#lineItems}.
	 * @param ctx the parse tree
	 */
	void enterLineItems(@NotNull DrumTabParser.LineItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#lineItems}.
	 * @param ctx the parse tree
	 */
	void exitLineItems(@NotNull DrumTabParser.LineItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#choke}.
	 * @param ctx the parse tree
	 */
	void enterChoke(@NotNull DrumTabParser.ChokeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#choke}.
	 * @param ctx the parse tree
	 */
	void exitChoke(@NotNull DrumTabParser.ChokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#ghost}.
	 * @param ctx the parse tree
	 */
	void enterGhost(@NotNull DrumTabParser.GhostContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#ghost}.
	 * @param ctx the parse tree
	 */
	void exitGhost(@NotNull DrumTabParser.GhostContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(@NotNull DrumTabParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(@NotNull DrumTabParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#strike}.
	 * @param ctx the parse tree
	 */
	void enterStrike(@NotNull DrumTabParser.StrikeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#strike}.
	 * @param ctx the parse tree
	 */
	void exitStrike(@NotNull DrumTabParser.StrikeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#roll}.
	 * @param ctx the parse tree
	 */
	void enterRoll(@NotNull DrumTabParser.RollContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#roll}.
	 * @param ctx the parse tree
	 */
	void exitRoll(@NotNull DrumTabParser.RollContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void enterSheet(@NotNull DrumTabParser.SheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void exitSheet(@NotNull DrumTabParser.SheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void enterStaff(@NotNull DrumTabParser.StaffContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void exitStaff(@NotNull DrumTabParser.StaffContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#drumType}.
	 * @param ctx the parse tree
	 */
	void enterDrumType(@NotNull DrumTabParser.DrumTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#drumType}.
	 * @param ctx the parse tree
	 */
	void exitDrumType(@NotNull DrumTabParser.DrumTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#accent}.
	 * @param ctx the parse tree
	 */
	void enterAccent(@NotNull DrumTabParser.AccentContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#accent}.
	 * @param ctx the parse tree
	 */
	void exitAccent(@NotNull DrumTabParser.AccentContext ctx);
	/**
	 * Enter a parse tree produced by {@link DrumTabParser#flam}.
	 * @param ctx the parse tree
	 */
	void enterFlam(@NotNull DrumTabParser.FlamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DrumTabParser#flam}.
	 * @param ctx the parse tree
	 */
	void exitFlam(@NotNull DrumTabParser.FlamContext ctx);
}