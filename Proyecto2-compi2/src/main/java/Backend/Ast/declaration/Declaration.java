package Backend.Ast.declaration;

import Backend.Ast.Node;

public abstract class Declaration extends Node {

    private String archivoOrigen;

    public Declaration(int line, int column) {
        super(line, column);
    }

    public Declaration() {
        super();
    }

    public String getArchivoOrigen() {
        return archivoOrigen;
    }

    public void setArchivoOrigen(String archivoOrigen) {
        this.archivoOrigen = archivoOrigen;
    }
}

