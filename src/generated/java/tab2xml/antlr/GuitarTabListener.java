// Generated from tab2xml\antlr\GuitarTab.g4 by ANTLR 4.9.2

	package tab2xml.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GuitarTabParser}.
 */
public interface GuitarTabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void enterSheet(GuitarTabParser.SheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void exitSheet(GuitarTabParser.SheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void enterStaff(GuitarTabParser.StaffContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void exitStaff(GuitarTabParser.StaffContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(GuitarTabParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(GuitarTabParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#stringItems}.
	 * @param ctx the parse tree
	 */
	void enterStringItems(GuitarTabParser.StringItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#stringItems}.
	 * @param ctx the parse tree
	 */
	void exitStringItems(GuitarTabParser.StringItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#fret}.
	 * @param ctx the parse tree
	 */
	void enterFret(GuitarTabParser.FretContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#fret}.
	 * @param ctx the parse tree
	 */
	void exitFret(GuitarTabParser.FretContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#harmonic}.
	 * @param ctx the parse tree
	 */
	void enterHarmonic(GuitarTabParser.HarmonicContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#harmonic}.
	 * @param ctx the parse tree
	 */
	void exitHarmonic(GuitarTabParser.HarmonicContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#pulloff}.
	 * @param ctx the parse tree
	 */
	void enterPulloff(GuitarTabParser.PulloffContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#pulloff}.
	 * @param ctx the parse tree
	 */
	void exitPulloff(GuitarTabParser.PulloffContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#hammeron}.
	 * @param ctx the parse tree
	 */
	void enterHammeron(GuitarTabParser.HammeronContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#hammeron}.
	 * @param ctx the parse tree
	 */
	void exitHammeron(GuitarTabParser.HammeronContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#slide}.
	 * @param ctx the parse tree
	 */
	void enterSlide(GuitarTabParser.SlideContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#slide}.
	 * @param ctx the parse tree
	 */
	void exitSlide(GuitarTabParser.SlideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HammerPull}
	 * labeled alternative in {@link GuitarTabParser#hampullchain}.
	 * @param ctx the parse tree
	 */
	void enterHammerPull(GuitarTabParser.HammerPullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HammerPull}
	 * labeled alternative in {@link GuitarTabParser#hampullchain}.
	 * @param ctx the parse tree
	 */
	void exitHammerPull(GuitarTabParser.HammerPullContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void enterTune(GuitarTabParser.TuneContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void exitTune(GuitarTabParser.TuneContext ctx);
}