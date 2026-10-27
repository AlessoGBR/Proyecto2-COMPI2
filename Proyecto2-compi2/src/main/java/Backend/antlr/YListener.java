// Generated from Y.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YParser}.
 */
public interface YListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(YParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(YParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 */
	void enterSeccionEstructuras(YParser.SeccionEstructurasContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 */
	void exitSeccionEstructuras(YParser.SeccionEstructurasContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#declaracionEstructura}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionEstructura(YParser.DeclaracionEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#declaracionEstructura}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionEstructura(YParser.DeclaracionEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#bloqueEstructura}.
	 * @param ctx the parse tree
	 */
	void enterBloqueEstructura(YParser.BloqueEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#bloqueEstructura}.
	 * @param ctx the parse tree
	 */
	void exitBloqueEstructura(YParser.BloqueEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#campoEstructura}.
	 * @param ctx the parse tree
	 */
	void enterCampoEstructura(YParser.CampoEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#campoEstructura}.
	 * @param ctx the parse tree
	 */
	void exitCampoEstructura(YParser.CampoEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccionFunciones(YParser.SeccionFuncionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#seccionFunciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccionFunciones(YParser.SeccionFuncionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDefinicionFuncion(YParser.DefinicionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#definicionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDefinicionFuncion(YParser.DefinicionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#listaParametrosY}.
	 * @param ctx the parse tree
	 */
	void enterListaParametrosY(YParser.ListaParametrosYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#listaParametrosY}.
	 * @param ctx the parse tree
	 */
	void exitListaParametrosY(YParser.ListaParametrosYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#parametroY}.
	 * @param ctx the parse tree
	 */
	void enterParametroY(YParser.ParametroYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#parametroY}.
	 * @param ctx the parse tree
	 */
	void exitParametroY(YParser.ParametroYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(YParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(YParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(YParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(YParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#declaracionEstructuraLocal}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionEstructuraLocal(YParser.DeclaracionEstructuraLocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#declaracionEstructuraLocal}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionEstructuraLocal(YParser.DeclaracionEstructuraLocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#declaracionVariableY}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionVariableY(YParser.DeclaracionVariableYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#declaracionVariableY}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionVariableY(YParser.DeclaracionVariableYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#inicializadorY}.
	 * @param ctx the parse tree
	 */
	void enterInicializadorY(YParser.InicializadorYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#inicializadorY}.
	 * @param ctx the parse tree
	 */
	void exitInicializadorY(YParser.InicializadorYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#literalArregloOEstructura}.
	 * @param ctx the parse tree
	 */
	void enterLiteralArregloOEstructura(YParser.LiteralArregloOEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#literalArregloOEstructura}.
	 * @param ctx the parse tree
	 */
	void exitLiteralArregloOEstructura(YParser.LiteralArregloOEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#asignacionY}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionY(YParser.AsignacionYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#asignacionY}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionY(YParser.AsignacionYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#destinoY}.
	 * @param ctx the parse tree
	 */
	void enterDestinoY(YParser.DestinoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#destinoY}.
	 * @param ctx the parse tree
	 */
	void exitDestinoY(YParser.DestinoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#accesoPostfijoY}.
	 * @param ctx the parse tree
	 */
	void enterAccesoPostfijoY(YParser.AccesoPostfijoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#accesoPostfijoY}.
	 * @param ctx the parse tree
	 */
	void exitAccesoPostfijoY(YParser.AccesoPostfijoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionIncrementoDecrementoY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionIncrementoDecrementoY(YParser.InstruccionIncrementoDecrementoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionIncrementoDecrementoY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionIncrementoDecrementoY(YParser.InstruccionIncrementoDecrementoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionSiY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionSiY(YParser.InstruccionSiYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionSiY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionSiY(YParser.InstruccionSiYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#condicionY}.
	 * @param ctx the parse tree
	 */
	void enterCondicionY(YParser.CondicionYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#condicionY}.
	 * @param ctx the parse tree
	 */
	void exitCondicionY(YParser.CondicionYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#ramaSinoY}.
	 * @param ctx the parse tree
	 */
	void enterRamaSinoY(YParser.RamaSinoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#ramaSinoY}.
	 * @param ctx the parse tree
	 */
	void exitRamaSinoY(YParser.RamaSinoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#ramaContrarioY}.
	 * @param ctx the parse tree
	 */
	void enterRamaContrarioY(YParser.RamaContrarioYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#ramaContrarioY}.
	 * @param ctx the parse tree
	 */
	void exitRamaContrarioY(YParser.RamaContrarioYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionElegirY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionElegirY(YParser.InstruccionElegirYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionElegirY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionElegirY(YParser.InstruccionElegirYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#bloqueElegir}.
	 * @param ctx the parse tree
	 */
	void enterBloqueElegir(YParser.BloqueElegirContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#bloqueElegir}.
	 * @param ctx the parse tree
	 */
	void exitBloqueElegir(YParser.BloqueElegirContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#casoElegir}.
	 * @param ctx the parse tree
	 */
	void enterCasoElegir(YParser.CasoElegirContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#casoElegir}.
	 * @param ctx the parse tree
	 */
	void exitCasoElegir(YParser.CasoElegirContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#siempreElegir}.
	 * @param ctx the parse tree
	 */
	void enterSiempreElegir(YParser.SiempreElegirContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#siempreElegir}.
	 * @param ctx the parse tree
	 */
	void exitSiempreElegir(YParser.SiempreElegirContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionParaY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionParaY(YParser.InstruccionParaYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionParaY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionParaY(YParser.InstruccionParaYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#declaracionParaY}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionParaY(YParser.DeclaracionParaYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#declaracionParaY}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionParaY(YParser.DeclaracionParaYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#actualizacionParaY}.
	 * @param ctx the parse tree
	 */
	void enterActualizacionParaY(YParser.ActualizacionParaYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#actualizacionParaY}.
	 * @param ctx the parse tree
	 */
	void exitActualizacionParaY(YParser.ActualizacionParaYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionMientrasY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionMientrasY(YParser.InstruccionMientrasYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionMientrasY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionMientrasY(YParser.InstruccionMientrasYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionHacerMientrasY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionHacerMientrasY(YParser.InstruccionHacerMientrasYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionHacerMientrasY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionHacerMientrasY(YParser.InstruccionHacerMientrasYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionRomper}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionRomper(YParser.InstruccionRomperContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionRomper}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionRomper(YParser.InstruccionRomperContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionContinuar}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionContinuar(YParser.InstruccionContinuarContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionContinuar}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionContinuar(YParser.InstruccionContinuarContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionRetornar}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionRetornar(YParser.InstruccionRetornarContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionRetornar}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionRetornar(YParser.InstruccionRetornarContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionImprimirY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionImprimirY(YParser.InstruccionImprimirYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionImprimirY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionImprimirY(YParser.InstruccionImprimirYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionLeerY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionLeerY(YParser.InstruccionLeerYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionLeerY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionLeerY(YParser.InstruccionLeerYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#instruccionLlamadaMetodoY}.
	 * @param ctx the parse tree
	 */
	void enterInstruccionLlamadaMetodoY(YParser.InstruccionLlamadaMetodoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#instruccionLlamadaMetodoY}.
	 * @param ctx the parse tree
	 */
	void exitInstruccionLlamadaMetodoY(YParser.InstruccionLlamadaMetodoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#llamadaMetodoY}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaMetodoY(YParser.LlamadaMetodoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#llamadaMetodoY}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaMetodoY(YParser.LlamadaMetodoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#listaArgumentosY}.
	 * @param ctx the parse tree
	 */
	void enterListaArgumentosY(YParser.ListaArgumentosYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#listaArgumentosY}.
	 * @param ctx the parse tree
	 */
	void exitListaArgumentosY(YParser.ListaArgumentosYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#finSentencia}.
	 * @param ctx the parse tree
	 */
	void enterFinSentencia(YParser.FinSentenciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#finSentencia}.
	 * @param ctx the parse tree
	 */
	void exitFinSentencia(YParser.FinSentenciaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPrefijaIncDecY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPrefijaIncDecY(YParser.ExprPrefijaIncDecYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPrefijaIncDecY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPrefijaIncDecY(YParser.ExprPrefijaIncDecYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLeerY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLeerY(YParser.ExprLeerYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLeerY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLeerY(YParser.ExprLeerYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPostfijaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostfijaY(YParser.ExprPostfijaYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPostfijaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostfijaY(YParser.ExprPostfijaYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAndLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAndLogicoY(YParser.ExprAndLogicoYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAndLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAndLogicoY(YParser.ExprAndLogicoYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAditivaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAditivaY(YParser.ExprAditivaYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAditivaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAditivaY(YParser.ExprAditivaYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIndexacionY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIndexacionY(YParser.ExprIndexacionYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIndexacionY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIndexacionY(YParser.ExprIndexacionYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLlamadaMetodoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaMetodoY(YParser.ExprLlamadaMetodoYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLlamadaMetodoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaMetodoY(YParser.ExprLlamadaMetodoYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIgualdadY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIgualdadY(YParser.ExprIgualdadYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIgualdadY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIgualdadY(YParser.ExprIgualdadYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParentesisY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesisY(YParser.ExprParentesisYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParentesisY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesisY(YParser.ExprParentesisYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMenosUnarioY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMenosUnarioY(YParser.ExprMenosUnarioYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMenosUnarioY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMenosUnarioY(YParser.ExprMenosUnarioYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteralEstructuraY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteralEstructuraY(YParser.ExprLiteralEstructuraYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteralEstructuraY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteralEstructuraY(YParser.ExprLiteralEstructuraYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprOrLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOrLogicoY(YParser.ExprOrLogicoYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprOrLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOrLogicoY(YParser.ExprOrLogicoYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAccesoMiembroY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembroY(YParser.ExprAccesoMiembroYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAccesoMiembroY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembroY(YParser.ExprAccesoMiembroYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultiplicativaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicativaY(YParser.ExprMultiplicativaYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultiplicativaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicativaY(YParser.ExprMultiplicativaYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLiteralY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteralY(YParser.ExprLiteralYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLiteralY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteralY(YParser.ExprLiteralYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprIdentificadorY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprIdentificadorY(YParser.ExprIdentificadorYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprIdentificadorY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprIdentificadorY(YParser.ExprIdentificadorYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRelacionalY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacionalY(YParser.ExprRelacionalYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRelacionalY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacionalY(YParser.ExprRelacionalYContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNotLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNotLogicoY(YParser.ExprNotLogicoYContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNotLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNotLogicoY(YParser.ExprNotLogicoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#tipoY}.
	 * @param ctx the parse tree
	 */
	void enterTipoY(YParser.TipoYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#tipoY}.
	 * @param ctx the parse tree
	 */
	void exitTipoY(YParser.TipoYContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#tipoBasico}.
	 * @param ctx the parse tree
	 */
	void enterTipoBasico(YParser.TipoBasicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#tipoBasico}.
	 * @param ctx the parse tree
	 */
	void exitTipoBasico(YParser.TipoBasicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link YParser#literalY}.
	 * @param ctx the parse tree
	 */
	void enterLiteralY(YParser.LiteralYContext ctx);
	/**
	 * Exit a parse tree produced by {@link YParser#literalY}.
	 * @param ctx the parse tree
	 */
	void exitLiteralY(YParser.LiteralYContext ctx);
}