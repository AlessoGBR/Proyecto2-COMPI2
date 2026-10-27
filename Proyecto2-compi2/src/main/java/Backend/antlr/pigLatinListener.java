// Generated from pigLatin.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pigLatinParser}.
 */
public interface pigLatinListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(pigLatinParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionImportaciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccionImportaciones(pigLatinParser.SeccionImportacionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionImportaciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccionImportaciones(pigLatinParser.SeccionImportacionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 */
	void enterImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#importacion}.
	 * @param ctx the parse tree
	 */
	void exitImportacion(pigLatinParser.ImportacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#rutaImport}.
	 * @param ctx the parse tree
	 */
	void enterRutaImport(pigLatinParser.RutaImportContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#rutaImport}.
	 * @param ctx the parse tree
	 */
	void exitRutaImport(pigLatinParser.RutaImportContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionVariablesGlobales}.
	 * @param ctx the parse tree
	 */
	void enterSeccionVariablesGlobales(pigLatinParser.SeccionVariablesGlobalesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionVariablesGlobales}.
	 * @param ctx the parse tree
	 */
	void exitSeccionVariablesGlobales(pigLatinParser.SeccionVariablesGlobalesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionGlobal}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionGlobal(pigLatinParser.DeclaracionGlobalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionGlobal}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionGlobal(pigLatinParser.DeclaracionGlobalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccionFunciones(pigLatinParser.SeccionFuncionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccionFunciones(pigLatinParser.SeccionFuncionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 */
	void enterSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionPrincipal}.
	 * @param ctx the parse tree
	 */
	void exitSeccionPrincipal(pigLatinParser.SeccionPrincipalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 */
	void enterInicializadorVariable(pigLatinParser.InicializadorVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#inicializadorVariable}.
	 * @param ctx the parse tree
	 */
	void exitInicializadorVariable(pigLatinParser.InicializadorVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instanciacionObjeto}.
	 * @param ctx the parse tree
	 */
	void enterInstanciacionObjeto(pigLatinParser.InstanciacionObjetoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instanciacionObjeto}.
	 * @param ctx the parse tree
	 */
	void exitInstanciacionObjeto(pigLatinParser.InstanciacionObjetoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#valorBooleano}.
	 * @param ctx the parse tree
	 */
	void enterValorBooleano(pigLatinParser.ValorBooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#valorBooleano}.
	 * @param ctx the parse tree
	 */
	void exitValorBooleano(pigLatinParser.ValorBooleanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionArreglo}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#listaValoresArreglo}.
	 * @param ctx the parse tree
	 */
	void enterListaValoresArreglo(pigLatinParser.ListaValoresArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#listaValoresArreglo}.
	 * @param ctx the parse tree
	 */
	void exitListaValoresArreglo(pigLatinParser.ListaValoresArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#elementoArreglo}.
	 * @param ctx the parse tree
	 */
	void enterElementoArreglo(pigLatinParser.ElementoArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#elementoArreglo}.
	 * @param ctx the parse tree
	 */
	void exitElementoArreglo(pigLatinParser.ElementoArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#literalStruct}.
	 * @param ctx the parse tree
	 */
	void enterLiteralStruct(pigLatinParser.LiteralStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#literalStruct}.
	 * @param ctx the parse tree
	 */
	void exitLiteralStruct(pigLatinParser.LiteralStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#elementoStruct}.
	 * @param ctx the parse tree
	 */
	void enterElementoStruct(pigLatinParser.ElementoStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#elementoStruct}.
	 * @param ctx the parse tree
	 */
	void exitElementoStruct(pigLatinParser.ElementoStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#tipoPrimitivo}.
	 * @param ctx the parse tree
	 */
	void enterTipoPrimitivo(pigLatinParser.TipoPrimitivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#tipoPrimitivo}.
	 * @param ctx the parse tree
	 */
	void exitTipoPrimitivo(pigLatinParser.TipoPrimitivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(pigLatinParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionFuncion(pigLatinParser.DefinicionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionFuncion(pigLatinParser.DefinicionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#funcionSinRetorno}.
	 * @param ctx the parse tree
	 */
	void enterFuncionSinRetorno(pigLatinParser.FuncionSinRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#funcionSinRetorno}.
	 * @param ctx the parse tree
	 */
	void exitFuncionSinRetorno(pigLatinParser.FuncionSinRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#funcionConRetorno}.
	 * @param ctx the parse tree
	 */
	void enterFuncionConRetorno(pigLatinParser.FuncionConRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#funcionConRetorno}.
	 * @param ctx the parse tree
	 */
	void exitFuncionConRetorno(pigLatinParser.FuncionConRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void enterListaParametros(pigLatinParser.ListaParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void exitListaParametros(pigLatinParser.ListaParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(pigLatinParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(pigLatinParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#seccionVariablesLocales}.
	 * @param ctx the parse tree
	 */
	void enterSeccionVariablesLocales(pigLatinParser.SeccionVariablesLocalesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#seccionVariablesLocales}.
	 * @param ctx the parse tree
	 */
	void exitSeccionVariablesLocales(pigLatinParser.SeccionVariablesLocalesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionLocal}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionLocal(pigLatinParser.DeclaracionLocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionLocal}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionLocal(pigLatinParser.DeclaracionLocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(pigLatinParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(pigLatinParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#destino}.
	 * @param ctx the parse tree
	 */
	void enterDestino(pigLatinParser.DestinoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#destino}.
	 * @param ctx the parse tree
	 */
	void exitDestino(pigLatinParser.DestinoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#accesoMiembro}.
	 * @param ctx the parse tree
	 */
	void enterAccesoMiembro(pigLatinParser.AccesoMiembroContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#accesoMiembro}.
	 * @param ctx the parse tree
	 */
	void exitAccesoMiembro(pigLatinParser.AccesoMiembroContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionLlamadaMetodo}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionLlamadaMetodo(pigLatinParser.InstruccionLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionLlamadaMetodo}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionLlamadaMetodo(pigLatinParser.InstruccionLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#llamadaMetodo}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaMetodo(pigLatinParser.LlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#llamadaMetodo}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaMetodo(pigLatinParser.LlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#accesoPostfijo}.
	 * @param ctx the parse tree
	 */
	void enterAccesoPostfijo(pigLatinParser.AccesoPostfijoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#accesoPostfijo}.
	 * @param ctx the parse tree
	 */
	void exitAccesoPostfijo(pigLatinParser.AccesoPostfijoContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#listaArgumentos}.
	 * @param ctx the parse tree
	 */
	void enterListaArgumentos(pigLatinParser.ListaArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#listaArgumentos}.
	 * @param ctx the parse tree
	 */
	void exitListaArgumentos(pigLatinParser.ListaArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionSi}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionSi(pigLatinParser.InstruccionSiContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionSi}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionSi(pigLatinParser.InstruccionSiContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#ramaAliterCondicional}.
	 * @param ctx the parse tree
	 */
	void enterRamaAliterCondicional(pigLatinParser.RamaAliterCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#ramaAliterCondicional}.
	 * @param ctx the parse tree
	 */
	void exitRamaAliterCondicional(pigLatinParser.RamaAliterCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#ramaAliterFinal}.
	 * @param ctx the parse tree
	 */
	void enterRamaAliterFinal(pigLatinParser.RamaAliterFinalContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#ramaAliterFinal}.
	 * @param ctx the parse tree
	 */
	void exitRamaAliterFinal(pigLatinParser.RamaAliterFinalContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#bloqueInstrucciones}.
	 * @param ctx the parse tree
	 */
	void enterBloqueInstrucciones(pigLatinParser.BloqueInstruccionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#bloqueInstrucciones}.
	 * @param ctx the parse tree
	 */
	void exitBloqueInstrucciones(pigLatinParser.BloqueInstruccionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionDum}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionDum(pigLatinParser.InstruccionDumContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionDum}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionDum(pigLatinParser.InstruccionDumContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionFacere}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionFacere(pigLatinParser.InstruccionFacereContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionFacere}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionFacere(pigLatinParser.InstruccionFacereContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionPer}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionPer(pigLatinParser.InstruccionPerContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionPer}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionPer(pigLatinParser.InstruccionPerContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#declaracionCicloFor}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionCicloFor(pigLatinParser.DeclaracionCicloForContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#declaracionCicloFor}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionCicloFor(pigLatinParser.DeclaracionCicloForContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#actualizacionCiclo}.
	 * @param ctx the parse tree
	 */
	void enterActualizacionCiclo(pigLatinParser.ActualizacionCicloContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#actualizacionCiclo}.
	 * @param ctx the parse tree
	 */
	void exitActualizacionCiclo(pigLatinParser.ActualizacionCicloContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionInterrumpe}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionInterrumpe(pigLatinParser.InstruccionInterrumpeContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionInterrumpe}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionInterrumpe(pigLatinParser.InstruccionInterrumpeContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionPerge}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionPerge(pigLatinParser.InstruccionPergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionPerge}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionPerge(pigLatinParser.InstruccionPergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionReddere}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionReddere(pigLatinParser.InstruccionReddereContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionReddere}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionReddere(pigLatinParser.InstruccionReddereContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionImprimir}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionImprimir(pigLatinParser.InstruccionImprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionImprimir}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionImprimir(pigLatinParser.InstruccionImprimirContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionLeer}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionLeer(pigLatinParser.InstruccionLeerContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionLeer}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionLeer(pigLatinParser.InstruccionLeerContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#instruccionIncrementoDecremento}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionIncrementoDecremento(pigLatinParser.InstruccionIncrementoDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#instruccionIncrementoDecremento}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionIncrementoDecremento(pigLatinParser.InstruccionIncrementoDecrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInstanciacionNovus}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprInstanciacionNovus(pigLatinParser.ExprInstanciacionNovusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInstanciacionNovus}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprInstanciacionNovus(pigLatinParser.ExprInstanciacionNovusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaMetodo(pigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaMetodo(pigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacional(pigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacional(pigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIgualdad(pigLatinParser.ExprIgualdadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIgualdad(pigLatinParser.ExprIgualdadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIdentificador(pigLatinParser.ExprIdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIdentificador(pigLatinParser.ExprIdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNegacionLogica}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegacionLogica(pigLatinParser.ExprNegacionLogicaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNegacionLogica}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegacionLogica(pigLatinParser.ExprNegacionLogicaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAditiva(pigLatinParser.ExprAditivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAditiva(pigLatinParser.ExprAditivaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesis(pigLatinParser.ExprParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesis(pigLatinParser.ExprParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAndLogico(pigLatinParser.ExprAndLogicoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAndLogico(pigLatinParser.ExprAndLogicoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIndexacion(pigLatinParser.ExprIndexacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIndexacion(pigLatinParser.ExprIndexacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOrLogico(pigLatinParser.ExprOrLogicoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOrLogico(pigLatinParser.ExprOrLogicoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteral(pigLatinParser.ExprLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteral(pigLatinParser.ExprLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembro(pigLatinParser.ExprAccesoMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembro(pigLatinParser.ExprAccesoMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicativa(pigLatinParser.ExprMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicativa(pigLatinParser.ExprMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNotSimbolo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNotSimbolo(pigLatinParser.ExprNotSimboloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNotSimbolo}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNotSimbolo(pigLatinParser.ExprNotSimboloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPostfija}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostfija(pigLatinParser.ExprPostfijaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPostfija}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostfija(pigLatinParser.ExprPostfijaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMenosUnario}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMenosUnario(pigLatinParser.ExprMenosUnarioContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMenosUnario}
	 * labeled alternative in {@link pigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMenosUnario(pigLatinParser.ExprMenosUnarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(pigLatinParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link pigLatinParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(pigLatinParser.LiteralContext ctx);
}