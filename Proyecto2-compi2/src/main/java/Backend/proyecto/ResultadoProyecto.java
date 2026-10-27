package Backend.proyecto;

import Backend.Ast.ProgramNode;
import Backend.errores.ErrorCompilacion;
import Backend.errores.GestorErrores;
import Backend.visitor.ResultadoAnalisis;

import java.util.List;

public class ResultadoProyecto {

    private final ProgramNode programa;
    private final GestorErrores gestorErrores;
    private final List<ResultadoAnalisis> modulos;

    public ResultadoProyecto(ProgramNode programa, GestorErrores gestorErrores, List<ResultadoAnalisis> modulos) {
        this.programa = programa;
        this.gestorErrores = gestorErrores;
        this.modulos = modulos;
    }

    public ProgramNode getPrograma() {
        return programa;
    }

    public GestorErrores getGestorErrores() {
        return gestorErrores;
    }

    public List<ErrorCompilacion> getErrores() {
        return gestorErrores.getErrores();
    }

    public List<ResultadoAnalisis> getModulos() {
        return modulos;
    }

    public boolean esValido() {
        return programa != null && !gestorErrores.hayErrores();
    }
}
