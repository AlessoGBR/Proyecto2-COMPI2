// Generated from Zetariano.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZetarianoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZetarianoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#definicionClase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionClase(ZetarianoParser.DefinicionClaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#miembroClase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembroClase(ZetarianoParser.MiembroClaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionCampo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionCampo(ZetarianoParser.DeclaracionCampoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#definicionConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionConstructor(ZetarianoParser.DefinicionConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#definicionMetodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionMetodo(ZetarianoParser.DefinicionMetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#modificadorAcceso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificadorAcceso(ZetarianoParser.ModificadorAccesoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipoBasico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoBasico(ZetarianoParser.TipoBasicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#dimensionesTipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionesTipo(ZetarianoParser.DimensionesTipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#listaParametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaParametros(ZetarianoParser.ListaParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(ZetarianoParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaracionVariableLocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionVariableLocal(ZetarianoParser.DeclaracionVariableLocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#listaDeclaradoresVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaDeclaradoresVariable(ZetarianoParser.ListaDeclaradoresVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#inicializador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializador(ZetarianoParser.InicializadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#literalArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralArreglo(ZetarianoParser.LiteralArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionIf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionIf(ZetarianoParser.InstruccionIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionSwitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionSwitch(ZetarianoParser.InstruccionSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#bloqueSwitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloqueSwitch(ZetarianoParser.BloqueSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionFor(ZetarianoParser.InstruccionForContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(ZetarianoParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(ZetarianoParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionWhile(ZetarianoParser.InstruccionWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionDoWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionDoWhile(ZetarianoParser.InstruccionDoWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionBreak}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionBreak(ZetarianoParser.InstruccionBreakContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionContinue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionContinue(ZetarianoParser.InstruccionContinueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionReturn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionReturn(ZetarianoParser.InstruccionReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#instruccionExpresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionExpresion(ZetarianoParser.InstruccionExpresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAsignacion(ZetarianoParser.ExprAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLlamadaSistema}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaSistema(ZetarianoParser.ExprLlamadaSistemaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIgualdad(ZetarianoParser.ExprIgualdadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdentificador(ZetarianoParser.ExprIdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprCreacionObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCreacionObjeto(ZetarianoParser.ExprCreacionObjetoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTernaria(ZetarianoParser.ExprTernariaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNotLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotLogico(ZetarianoParser.ExprNotLogicoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAditiva(ZetarianoParser.ExprAditivaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSuper}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSuper(ZetarianoParser.ExprSuperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteralArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteralArreglo(ZetarianoParser.ExprLiteralArregloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAndLogico(ZetarianoParser.ExprAndLogicoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprCreacionArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCreacionArreglo(ZetarianoParser.ExprCreacionArregloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIndexacion(ZetarianoParser.ExprIndexacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOrLogico(ZetarianoParser.ExprOrLogicoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPostfijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostfijoIncDec(ZetarianoParser.ExprPostfijoIncDecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnariaAritmetica}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnariaAritmetica(ZetarianoParser.ExprUnariaAritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteral(ZetarianoParser.ExprLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprThis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprThis(ZetarianoParser.ExprThisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicativa(ZetarianoParser.ExprMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPrefijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrefijoIncDec(ZetarianoParser.ExprPrefijoIncDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#dimensionArregloNueva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionArregloNueva(ZetarianoParser.DimensionArregloNuevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#llamadaSistema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaSistema(ZetarianoParser.LlamadaSistemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#listaArgumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaArgumentos(ZetarianoParser.ListaArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ZetarianoParser.LiteralContext ctx);
}