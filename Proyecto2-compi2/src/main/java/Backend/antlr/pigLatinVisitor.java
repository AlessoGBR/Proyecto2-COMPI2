// Generated from pigLatin.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link pigLatinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface pigLatinVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionImportaciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionImportaciones(pigLatinParser.SeccionImportacionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#rutaImport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRutaImport(pigLatinParser.RutaImportContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionVariablesGlobales}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionVariablesGlobales(pigLatinParser.SeccionVariablesGlobalesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionGlobal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionGlobal(pigLatinParser.DeclaracionGlobalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionFunciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionFunciones(pigLatinParser.SeccionFuncionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializadorVariable(pigLatinParser.InicializadorVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instanciacionObjeto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanciacionObjeto(pigLatinParser.InstanciacionObjetoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#valorBooleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorBooleano(pigLatinParser.ValorBooleanoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#listaValoresArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaValoresArreglo(pigLatinParser.ListaValoresArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#elementoArreglo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementoArreglo(pigLatinParser.ElementoArregloContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#literalStruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralStruct(pigLatinParser.LiteralStructContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#elementoStruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementoStruct(pigLatinParser.ElementoStructContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#tipoPrimitivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoPrimitivo(pigLatinParser.TipoPrimitivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#definicionFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionFuncion(pigLatinParser.DefinicionFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#funcionSinRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncionSinRetorno(pigLatinParser.FuncionSinRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#funcionConRetorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncionConRetorno(pigLatinParser.FuncionConRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#listaParametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaParametros(pigLatinParser.ListaParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(pigLatinParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#seccionVariablesLocales}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionVariablesLocales(pigLatinParser.SeccionVariablesLocalesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionLocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionLocal(pigLatinParser.DeclaracionLocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#destino}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestino(pigLatinParser.DestinoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#accesoMiembro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccesoMiembro(pigLatinParser.AccesoMiembroContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionLlamadaMetodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionLlamadaMetodo(pigLatinParser.InstruccionLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#llamadaMetodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaMetodo(pigLatinParser.LlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#accesoPostfijo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccesoPostfijo(pigLatinParser.AccesoPostfijoContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#listaArgumentos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaArgumentos(pigLatinParser.ListaArgumentosContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionSi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionSi(pigLatinParser.InstruccionSiContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#ramaAliterCondicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRamaAliterCondicional(pigLatinParser.RamaAliterCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#ramaAliterFinal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRamaAliterFinal(pigLatinParser.RamaAliterFinalContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#bloqueInstrucciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloqueInstrucciones(pigLatinParser.BloqueInstruccionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionDum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionDum(pigLatinParser.InstruccionDumContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionFacere}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionFacere(pigLatinParser.InstruccionFacereContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionPer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionPer(pigLatinParser.InstruccionPerContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#declaracionCicloFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionCicloFor(pigLatinParser.DeclaracionCicloForContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#actualizacionCiclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacionCiclo(pigLatinParser.ActualizacionCicloContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionInterrumpe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionInterrumpe(pigLatinParser.InstruccionInterrumpeContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionPerge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionPerge(pigLatinParser.InstruccionPergeContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionReddere}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionReddere(pigLatinParser.InstruccionReddereContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionImprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionImprimir(pigLatinParser.InstruccionImprimirContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionLeer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionLeer(pigLatinParser.InstruccionLeerContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#instruccionIncrementoDecremento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionIncrementoDecremento(pigLatinParser.InstruccionIncrementoDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInstanciacionNovus}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInstanciacionNovus(pigLatinParser.ExprInstanciacionNovusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaMetodo(pigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacional(pigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIgualdad(pigLatinParser.ExprIgualdadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdentificador(pigLatinParser.ExprIdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNegacionLogica}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegacionLogica(pigLatinParser.ExprNegacionLogicaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAditiva(pigLatinParser.ExprAditivaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesis(pigLatinParser.ExprParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAndLogico(pigLatinParser.ExprAndLogicoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIndexacion(pigLatinParser.ExprIndexacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOrLogico(pigLatinParser.ExprOrLogicoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteral(pigLatinParser.ExprLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembro(pigLatinParser.ExprAccesoMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicativa(pigLatinParser.ExprMultiplicativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNotSimbolo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotSimbolo(pigLatinParser.ExprNotSimboloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPostfija}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostfija(pigLatinParser.ExprPostfijaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMenosUnario}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMenosUnario(pigLatinParser.ExprMenosUnarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(pigLatinParser.LiteralContext ctx);
}