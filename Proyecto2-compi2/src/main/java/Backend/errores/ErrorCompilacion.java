package Backend.errores;

public class ErrorCompilacion {

    private final TipoError tipo;
    private final String mensaje;
    private final int linea;
    private final int columna;
    private final String archivo;
    private final String lexemaProblematico;

    public ErrorCompilacion(TipoError tipo, String mensaje, int linea, int columna,
                            String archivo, String lexemaProblematico) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
        this.archivo = archivo == null ? "" : archivo;
        this.lexemaProblematico = lexemaProblematico == null ? "" : lexemaProblematico;
    }

    public ErrorCompilacion(TipoError tipo, String mensaje, int linea, int columna) {
        this(tipo, mensaje, linea, columna, "", "");
    }

    public TipoError getTipo() {
        return tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getArchivo() {
        return archivo;
    }

    public String getLexemaProblematico() {
        return lexemaProblematico;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(tipo.getEtiqueta()).append(']');
        if (!archivo.isEmpty()) {
            sb.append(' ').append(archivo);
        }
        sb.append(" LINEA ").append(linea).append(", COLUMNA ").append(columna).append(": ").append(mensaje);
        if (!lexemaProblematico.isEmpty()) {
            sb.append(" -> '").append(lexemaProblematico).append('\'');
        }
        return sb.toString();
    }
}
