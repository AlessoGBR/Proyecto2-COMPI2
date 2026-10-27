package Backend.errores;

public enum TipoError {
    LEXICO("LEXICO"),
    SINTACTICO("SINTACTICO"),
    SEMANTICO("SEMANTICO"),;

    private final String etiqueta;

    TipoError(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
