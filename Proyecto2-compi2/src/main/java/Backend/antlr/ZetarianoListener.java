// Generated from Zetariano.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZetarianoParser}.
 */
public interface ZetarianoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#definicionClase}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionClase(ZetarianoParser.DefinicionClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#definicionClase}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionClase(ZetarianoParser.DefinicionClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#miembroClase}.
	 * @param ctx the parse tree
	 */
	void enterMiembroClase(ZetarianoParser.MiembroClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#miembroClase}.
	 * @param ctx the parse tree
	 */
	void exitMiembroClase(ZetarianoParser.MiembroClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionCampo}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionCampo(ZetarianoParser.DeclaracionCampoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionCampo}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionCampo(ZetarianoParser.DeclaracionCampoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#definicionConstructor}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionConstructor(ZetarianoParser.DefinicionConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#definicionConstructor}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionConstructor(ZetarianoParser.DefinicionConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#definicionMetodo}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionMetodo(ZetarianoParser.DefinicionMetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#definicionMetodo}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionMetodo(ZetarianoParser.DefinicionMetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#modificadorAcceso}.
	 * @param ctx the parse tree
	 */
	void enterModificadorAcceso(ZetarianoParser.ModificadorAccesoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#modificadorAcceso}.
	 * @param ctx the parse tree
	 */
	void exitModificadorAcceso(ZetarianoParser.ModificadorAccesoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void enterTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipoRetorno}.
	 * @param ctx the parse tree
	 */
	void exitTipoRetorno(ZetarianoParser.TipoRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(ZetarianoParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipoBasico}.
	 * @param ctx the parse tree
	 */
	void enterTipoBasico(ZetarianoParser.TipoBasicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipoBasico}.
	 * @param ctx the parse tree
	 */
	void exitTipoBasico(ZetarianoParser.TipoBasicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#dimensionesTipo}.
	 * @param ctx the parse tree
	 */
	void enterDimensionesTipo(ZetarianoParser.DimensionesTipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#dimensionesTipo}.
	 * @param ctx the parse tree
	 */
	void exitDimensionesTipo(ZetarianoParser.DimensionesTipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void enterListaParametros(ZetarianoParser.ListaParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#listaParametros}.
	 * @param ctx the parse tree
	 */
	void exitListaParametros(ZetarianoParser.ListaParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(ZetarianoParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(ZetarianoParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(ZetarianoParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaracionVariableLocal}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionVariableLocal(ZetarianoParser.DeclaracionVariableLocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaracionVariableLocal}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionVariableLocal(ZetarianoParser.DeclaracionVariableLocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#listaDeclaradoresVariable}.
	 * @param ctx the parse tree
	 */
	void enterListaDeclaradoresVariable(ZetarianoParser.ListaDeclaradoresVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#listaDeclaradoresVariable}.
	 * @param ctx the parse tree
	 */
	void exitListaDeclaradoresVariable(ZetarianoParser.ListaDeclaradoresVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 */
	void enterDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#declaradorVariable}.
	 * @param ctx the parse tree
	 */
	void exitDeclaradorVariable(ZetarianoParser.DeclaradorVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void enterInicializador(ZetarianoParser.InicializadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#inicializador}.
	 * @param ctx the parse tree
	 */
	void exitInicializador(ZetarianoParser.InicializadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#literalArreglo}.
	 * @param ctx the parse tree
	 */
	void enterLiteralArreglo(ZetarianoParser.LiteralArregloContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#literalArreglo}.
	 * @param ctx the parse tree
	 */
	void exitLiteralArreglo(ZetarianoParser.LiteralArregloContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionIf}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionIf(ZetarianoParser.InstruccionIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionIf}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionIf(ZetarianoParser.InstruccionIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionSwitch}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionSwitch(ZetarianoParser.InstruccionSwitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionSwitch}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionSwitch(ZetarianoParser.InstruccionSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#bloqueSwitch}.
	 * @param ctx the parse tree
	 */
	void enterBloqueSwitch(ZetarianoParser.BloqueSwitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#bloqueSwitch}.
	 * @param ctx the parse tree
	 */
	void exitBloqueSwitch(ZetarianoParser.BloqueSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 */
	void enterEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#etiquetaSwitch}.
	 * @param ctx the parse tree
	 */
	void exitEtiquetaSwitch(ZetarianoParser.EtiquetaSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionFor}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionFor(ZetarianoParser.InstruccionForContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionFor}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionFor(ZetarianoParser.InstruccionForContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(ZetarianoParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(ZetarianoParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(ZetarianoParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(ZetarianoParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 */
	void enterListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#listaExpresiones}.
	 * @param ctx the parse tree
	 */
	void exitListaExpresiones(ZetarianoParser.ListaExpresionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionWhile}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionWhile(ZetarianoParser.InstruccionWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionWhile}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionWhile(ZetarianoParser.InstruccionWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionDoWhile}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionDoWhile(ZetarianoParser.InstruccionDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionDoWhile}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionDoWhile(ZetarianoParser.InstruccionDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionBreak}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionBreak(ZetarianoParser.InstruccionBreakContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionBreak}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionBreak(ZetarianoParser.InstruccionBreakContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionContinue}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionContinue(ZetarianoParser.InstruccionContinueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionContinue}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionContinue(ZetarianoParser.InstruccionContinueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionReturn}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionReturn(ZetarianoParser.InstruccionReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionReturn}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionReturn(ZetarianoParser.InstruccionReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#instruccionExpresion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionExpresion(ZetarianoParser.InstruccionExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#instruccionExpresion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionExpresion(ZetarianoParser.InstruccionExpresionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAsignacion(ZetarianoParser.ExprAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAsignacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAsignacion(ZetarianoParser.ExprAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLlamadaSistema}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaSistema(ZetarianoParser.ExprLlamadaSistemaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLlamadaSistema}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaSistema(ZetarianoParser.ExprLlamadaSistemaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIgualdad(ZetarianoParser.ExprIgualdadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIgualdad}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIgualdad(ZetarianoParser.ExprIgualdadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIdentificador(ZetarianoParser.ExprIdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIdentificador}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIdentificador(ZetarianoParser.ExprIdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprCreacionObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprCreacionObjeto(ZetarianoParser.ExprCreacionObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprCreacionObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprCreacionObjeto(ZetarianoParser.ExprCreacionObjetoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprTernaria(ZetarianoParser.ExprTernariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprTernaria}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprTernaria(ZetarianoParser.ExprTernariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNotLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNotLogico(ZetarianoParser.ExprNotLogicoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNotLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNotLogico(ZetarianoParser.ExprNotLogicoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAditiva(ZetarianoParser.ExprAditivaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAditiva}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAditiva(ZetarianoParser.ExprAditivaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSuper}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprSuper(ZetarianoParser.ExprSuperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSuper}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprSuper(ZetarianoParser.ExprSuperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteralArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteralArreglo(ZetarianoParser.ExprLiteralArregloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteralArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteralArreglo(ZetarianoParser.ExprLiteralArregloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAndLogico(ZetarianoParser.ExprAndLogicoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAndLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAndLogico(ZetarianoParser.ExprAndLogicoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprCreacionArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprCreacionArreglo(ZetarianoParser.ExprCreacionArregloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprCreacionArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprCreacionArreglo(ZetarianoParser.ExprCreacionArregloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIndexacion(ZetarianoParser.ExprIndexacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIndexacion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIndexacion(ZetarianoParser.ExprIndexacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOrLogico(ZetarianoParser.ExprOrLogicoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprOrLogico}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOrLogico(ZetarianoParser.ExprOrLogicoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPostfijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostfijoIncDec(ZetarianoParser.ExprPostfijoIncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPostfijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostfijoIncDec(ZetarianoParser.ExprPostfijoIncDecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnariaAritmetica}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprUnariaAritmetica(ZetarianoParser.ExprUnariaAritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnariaAritmetica}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprUnariaAritmetica(ZetarianoParser.ExprUnariaAritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteral(ZetarianoParser.ExprLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteral}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteral(ZetarianoParser.ExprLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprThis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprThis(ZetarianoParser.ExprThisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprThis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprThis(ZetarianoParser.ExprThisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicativa(ZetarianoParser.ExprMultiplicativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultiplicativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicativa(ZetarianoParser.ExprMultiplicativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPrefijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPrefijoIncDec(ZetarianoParser.ExprPrefijoIncDecContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPrefijoIncDec}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPrefijoIncDec(ZetarianoParser.ExprPrefijoIncDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#dimensionArregloNueva}.
	 * @param ctx the parse tree
	 */
	void enterDimensionArregloNueva(ZetarianoParser.DimensionArregloNuevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#dimensionArregloNueva}.
	 * @param ctx the parse tree
	 */
	void exitDimensionArregloNueva(ZetarianoParser.DimensionArregloNuevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#llamadaSistema}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaSistema(ZetarianoParser.LlamadaSistemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#llamadaSistema}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaSistema(ZetarianoParser.LlamadaSistemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#listaArgumentos}.
	 * @param ctx the parse tree
	 */
	void enterListaArgumentos(ZetarianoParser.ListaArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#listaArgumentos}.
	 * @param ctx the parse tree
	 */
	void exitListaArgumentos(ZetarianoParser.ListaArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ZetarianoParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ZetarianoParser.LiteralContext ctx);
}