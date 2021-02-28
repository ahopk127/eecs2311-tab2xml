// Generated from GuitarTab.g4 by ANTLR 4.4

	package tab2xml.antlr; 

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GuitarTabParser}.
 */
public interface GuitarTabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#hammeron}.
	 * @param ctx the parse tree
	 */
	void enterHammeron(@NotNull GuitarTabParser.HammeronContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#hammeron}.
	 * @param ctx the parse tree
	 */
	void exitHammeron(@NotNull GuitarTabParser.HammeronContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull GuitarTabParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull GuitarTabParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#slide}.
	 * @param ctx the parse tree
	 */
	void enterSlide(@NotNull GuitarTabParser.SlideContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#slide}.
	 * @param ctx the parse tree
	 */
	void exitSlide(@NotNull GuitarTabParser.SlideContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HammerPull}
	 * labeled alternative in {@link GuitarTabParser#hampullchain}.
	 * @param ctx the parse tree
	 */
	void enterHammerPull(@NotNull GuitarTabParser.HammerPullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HammerPull}
	 * labeled alternative in {@link GuitarTabParser#hampullchain}.
	 * @param ctx the parse tree
	 */
	void exitHammerPull(@NotNull GuitarTabParser.HammerPullContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void enterSheet(@NotNull GuitarTabParser.SheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#sheet}.
	 * @param ctx the parse tree
	 */
	void exitSheet(@NotNull GuitarTabParser.SheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void enterStaff(@NotNull GuitarTabParser.StaffContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#staff}.
	 * @param ctx the parse tree
	 */
	void exitStaff(@NotNull GuitarTabParser.StaffContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#fret}.
	 * @param ctx the parse tree
	 */
	void enterFret(@NotNull GuitarTabParser.FretContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#fret}.
	 * @param ctx the parse tree
	 */
	void exitFret(@NotNull GuitarTabParser.FretContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#stringItems}.
	 * @param ctx the parse tree
	 */
	void enterStringItems(@NotNull GuitarTabParser.StringItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#stringItems}.
	 * @param ctx the parse tree
	 */
	void exitStringItems(@NotNull GuitarTabParser.StringItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void enterTune(@NotNull GuitarTabParser.TuneContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#tune}.
	 * @param ctx the parse tree
	 */
	void exitTune(@NotNull GuitarTabParser.TuneContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#harmonic}.
	 * @param ctx the parse tree
	 */
	void enterHarmonic(@NotNull GuitarTabParser.HarmonicContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#harmonic}.
	 * @param ctx the parse tree
	 */
	void exitHarmonic(@NotNull GuitarTabParser.HarmonicContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuitarTabParser#pulloff}.
	 * @param ctx the parse tree
	 */
	void enterPulloff(@NotNull GuitarTabParser.PulloffContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuitarTabParser#pulloff}.
	 * @param ctx the parse tree
	 */
	void exitPulloff(@NotNull GuitarTabParser.PulloffContext ctx);
}