package Backend.visitor;

import Backend.Ast.ProgramNode;
import Backend.errores.ErrorCompilacion;
import Backend.errores.GestorErrores;

import java.util.List;

public class ResultadoAnalisis {

    private final ProgramNode programa;
    private final GestorErrores gestorErrores;
    private final ProgramNode.SourceLanguage lenguaje;
    private final String archivo;

    public ResultadoAnalisis(ProgramNode programa, GestorErrores gestorErrores,
                             ProgramNode.SourceLanguage lenguaje, String archivo) {
        this.programa = programa;
        this.gestorErrores = gestorErrores;
        this.lenguaje = lenguaje;
        this.archivo = archivo == null ? "" : archivo;
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

    public ProgramNode.SourceLanguage getLenguaje() {
        return lenguaje;
    }

    public String getArchivo() {
        return archivo;
    }

    public boolean esValido() {
        return programa != null && !gestorErrores.hayErrores();
    }
}
