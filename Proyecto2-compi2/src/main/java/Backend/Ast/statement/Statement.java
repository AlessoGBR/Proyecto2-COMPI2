package Backend.Ast.statement;

import Backend.Ast.Node;


public abstract class Statement extends Node {

    public Statement(int line, int column) {
        super(line, column);
    }

    public Statement() {
        super();
    }
}

