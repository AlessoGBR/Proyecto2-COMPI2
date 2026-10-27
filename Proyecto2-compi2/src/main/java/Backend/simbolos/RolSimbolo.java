package Backend.simbolos;

public enum RolSimbolo {
    VARIABLE("Variable"),
    ARREGLO("Arreglo"),
    PARAMETRO("Parametro"),
    FUNCION("Funcion"),
    METODO("Metodo"),
    CONSTRUCTOR("Constructor"),
    ESTRUCTURA("Estructura"),
    CLASE("Clase");

    private final String etiqueta;

    RolSimbolo(String etiqueta) {
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
