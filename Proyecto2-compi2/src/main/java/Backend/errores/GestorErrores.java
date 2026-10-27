package Backend.errores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorErrores {

    private final List<ErrorCompilacion> errores = new ArrayList<>();
    private String archivo = "";

    public GestorErrores() {
    }

    public GestorErrores(String archivo) {
        this.archivo = archivo == null ? "" : archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo == null ? "" : archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void agregar(ErrorCompilacion error) {
        if (error != null) {
            errores.add(error);
        }
    }

    public void agregar(TipoError tipo, String mensaje, int linea, int columna) {
        agregar(new ErrorCompilacion(tipo, mensaje, linea, columna, archivo, ""));
    }

    public void agregar(TipoError tipo, String mensaje, int linea, int columna, String lexema) {
        agregar(new ErrorCompilacion(tipo, mensaje, linea, columna, archivo, lexema));
    }

    public void agregarLexico(String mensaje, int linea, int columna, String lexema) {
        agregar(TipoError.LEXICO, mensaje, linea, columna, lexema);
    }

    public void agregarSintactico(String mensaje, int linea, int columna, String lexema) {
        agregar(TipoError.SINTACTICO, mensaje, linea, columna, lexema);
    }

    public void agregarSemantico(String mensaje, int linea, int columna) {
        agregar(TipoError.SEMANTICO, mensaje, linea, columna);
    }

    public void agregarTodos(GestorErrores otro) {
        if (otro != null) {
            errores.addAll(otro.errores);
        }
    }

    public List<ErrorCompilacion> getErrores() {
        return Collections.unmodifiableList(errores);
    }

    public List<ErrorCompilacion> getErroresPorTipo(TipoError tipo) {
        return errores.stream().filter(e -> e.getTipo() == tipo).toList();
    }

    public boolean hayErrores() {
        return !errores.isEmpty();
    }

    public int cantidad() {
        return errores.size();
    }

    public void limpiar() {
        errores.clear();
    }

    @Override
    public String toString() {
        if (errores.isEmpty()) {
            return "SIN ERRORES";
        }
        StringBuilder sb = new StringBuilder();
        for (ErrorCompilacion error : errores) {
            sb.append(error).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
