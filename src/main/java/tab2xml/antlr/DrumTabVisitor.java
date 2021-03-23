// Generated from DrumTab.g4 by ANTLR 4.9.2

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
	 * Visit a parse tree produced by {@link DrumTabParser#sheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSheet(DrumTabParser.SheetContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#staff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaff(DrumTabParser.StaffContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(DrumTabParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#lineItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineItems(DrumTabParser.LineItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#drumType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrumType(DrumTabParser.DrumTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#cymbal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCymbal(DrumTabParser.CymbalContext ctx);
	/**
	 * Visit a parse tree produced by {@link DrumTabParser#drum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDrum(DrumTabParser.DrumContext ctx);
}