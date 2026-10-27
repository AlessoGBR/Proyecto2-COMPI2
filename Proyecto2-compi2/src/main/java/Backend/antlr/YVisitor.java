// Generated from Y.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(YParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#seccionEstructuras}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionEstructuras(YParser.SeccionEstructurasContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#declaracionEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionEstructura(YParser.DeclaracionEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#bloqueEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloqueEstructura(YParser.BloqueEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#campoEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCampoEstructura(YParser.CampoEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#seccionFunciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccionFunciones(YParser.SeccionFuncionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#definicionFuncion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinicionFuncion(YParser.DefinicionFuncionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#listaParametrosY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaParametrosY(YParser.ListaParametrosYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#parametroY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametroY(YParser.ParametroYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(YParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(YParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#declaracionEstructuraLocal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionEstructuraLocal(YParser.DeclaracionEstructuraLocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#declaracionVariableY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionVariableY(YParser.DeclaracionVariableYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#inicializadorY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInicializadorY(YParser.InicializadorYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#literalArregloOEstructura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralArregloOEstructura(YParser.LiteralArregloOEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#asignacionY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionY(YParser.AsignacionYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#destinoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestinoY(YParser.DestinoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#accesoPostfijoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccesoPostfijoY(YParser.AccesoPostfijoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionIncrementoDecrementoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionIncrementoDecrementoY(YParser.InstruccionIncrementoDecrementoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionSiY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionSiY(YParser.InstruccionSiYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#condicionY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicionY(YParser.CondicionYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#ramaSinoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRamaSinoY(YParser.RamaSinoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#ramaContrarioY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRamaContrarioY(YParser.RamaContrarioYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionElegirY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionElegirY(YParser.InstruccionElegirYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#bloqueElegir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloqueElegir(YParser.BloqueElegirContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#casoElegir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasoElegir(YParser.CasoElegirContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#siempreElegir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSiempreElegir(YParser.SiempreElegirContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionParaY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionParaY(YParser.InstruccionParaYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#declaracionParaY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracionParaY(YParser.DeclaracionParaYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#actualizacionParaY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualizacionParaY(YParser.ActualizacionParaYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionMientrasY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionMientrasY(YParser.InstruccionMientrasYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionHacerMientrasY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionHacerMientrasY(YParser.InstruccionHacerMientrasYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionRomper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionRomper(YParser.InstruccionRomperContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionContinuar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionContinuar(YParser.InstruccionContinuarContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionRetornar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionRetornar(YParser.InstruccionRetornarContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionImprimirY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionImprimirY(YParser.InstruccionImprimirYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionLeerY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionLeerY(YParser.InstruccionLeerYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#instruccionLlamadaMetodoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccionLlamadaMetodoY(YParser.InstruccionLlamadaMetodoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#llamadaMetodoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlamadaMetodoY(YParser.LlamadaMetodoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#listaArgumentosY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListaArgumentosY(YParser.ListaArgumentosYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#finSentencia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinSentencia(YParser.FinSentenciaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPrefijaIncDecY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrefijaIncDecY(YParser.ExprPrefijaIncDecYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLeerY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLeerY(YParser.ExprLeerYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPostfijaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostfijaY(YParser.ExprPostfijaYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAndLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAndLogicoY(YParser.ExprAndLogicoYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAditivaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAditivaY(YParser.ExprAditivaYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIndexacionY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIndexacionY(YParser.ExprIndexacionYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLlamadaMetodoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaMetodoY(YParser.ExprLlamadaMetodoYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIgualdadY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIgualdadY(YParser.ExprIgualdadYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParentesisY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesisY(YParser.ExprParentesisYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMenosUnarioY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMenosUnarioY(YParser.ExprMenosUnarioYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteralEstructuraY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteralEstructuraY(YParser.ExprLiteralEstructuraYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOrLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOrLogicoY(YParser.ExprOrLogicoYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAccesoMiembroY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembroY(YParser.ExprAccesoMiembroYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultiplicativaY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicativaY(YParser.ExprMultiplicativaYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLiteralY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteralY(YParser.ExprLiteralYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprIdentificadorY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIdentificadorY(YParser.ExprIdentificadorYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRelacionalY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacionalY(YParser.ExprRelacionalYContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNotLogicoY}
	 * labeled alternative in {@link YParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotLogicoY(YParser.ExprNotLogicoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#tipoY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoY(YParser.TipoYContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#tipoBasico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoBasico(YParser.TipoBasicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link YParser#literalY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralY(YParser.LiteralYContext ctx);
}