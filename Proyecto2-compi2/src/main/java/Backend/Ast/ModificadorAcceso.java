package Backend.Ast;

public enum ModificadorAcceso {
    PRIVADO("private", 0),
    DEFECTO("default", 1),
    PROTEGIDO("protected", 2),
    PUBLICO("public", 3);

    private final String palabra;
    private final int nivel;

    ModificadorAcceso(String palabra, int nivel) {
        this.palabra = palabra;
        this.nivel = nivel;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return palabra;
    }
}
