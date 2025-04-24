// Generated from com\compilador\MiLenguaje.g4 by ANTLR 4.9.3
package com.compilador;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiLenguajeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiLenguajeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiLenguajeParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(MiLenguajeParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConParentesis}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConParentesis(MiLenguajeParser.ConParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VacioA}
	 * labeled alternative in {@link MiLenguajeParser#a}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVacioA(MiLenguajeParser.VacioAContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimerHexa}
	 * labeled alternative in {@link MiLenguajeParser#b}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimerHexa(MiLenguajeParser.PrimerHexaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HexaRecursiva}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexaRecursiva(MiLenguajeParser.HexaRecursivaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VacioC}
	 * labeled alternative in {@link MiLenguajeParser#c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVacioC(MiLenguajeParser.VacioCContext ctx);
}