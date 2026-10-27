package Backend.errores;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class ListenerErroresAntlr extends BaseErrorListener {

    private final GestorErrores gestor;

    public ListenerErroresAntlr(GestorErrores gestor) {
        this.gestor = gestor;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        if (offendingSymbol instanceof Token token) {
            gestor.agregarSintactico(msg, line, charPositionInLine, textoVisible(token));
        } else {
            gestor.agregarLexico(msg, line, charPositionInLine, "");
        }
    }

    private String textoVisible(Token token) {
        String texto = token.getText();
        if (texto == null) {
            return "";
        }
        return texto.replace("\r", "").replace("\n", "\\n");
    }
}
