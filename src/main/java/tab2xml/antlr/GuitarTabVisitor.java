// Generated from .\GuitarTab.g4 by ANTLR 4.9.2

	package tab2xml.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GuitarTabParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GuitarTabVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#sheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSheet(GuitarTabParser.SheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#staff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaff(GuitarTabParser.StaffContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(GuitarTabParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#tune}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTune(GuitarTabParser.TuneContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#stringItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringItems(GuitarTabParser.StringItemsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HammerPull}
	 * labeled alternative in {@link GuitarTabParser#hampullchain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHammerPull(GuitarTabParser.HammerPullContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#pulloff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPulloff(GuitarTabParser.PulloffContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#hammeron}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHammeron(GuitarTabParser.HammeronContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#slide}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlide(GuitarTabParser.SlideContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#harmonic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHarmonic(GuitarTabParser.HarmonicContext ctx);
	/**
	 * Visit a parse tree produced by {@link GuitarTabParser#fret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFret(GuitarTabParser.FretContext ctx);
}