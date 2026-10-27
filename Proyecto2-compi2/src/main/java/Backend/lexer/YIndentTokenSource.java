package Backend.lexer;

import Backend.antlr.YLexer;
import Backend.antlr.YParser;
import Backend.estructuras.Cola;
import Backend.estructuras.Pila;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Pair;

public class YIndentTokenSource implements TokenSource {

    public static final int ESPACIOS_POR_TABULACION = 4;
    private final YLexer lexer;
    private final Pila<Integer> niveles = new Pila<>();
    private final Cola<Token> pendientes = new Cola<>();
    private Token adelantado;
    private int ultimoTipoEmitido = -1;
    private boolean finArchivoProcesado = false;

    public YIndentTokenSource(YLexer lexer) {
        this.lexer = lexer;
        this.niveles.apilar(0);
    }

    @Override
    public Token nextToken() {
        if (!pendientes.estaVacia()) {
            return emitir(pendientes.desencolar());
        }

        Token actual = tomarSiguiente();

        if (actual.getType() == YParser.NEWLINE) {
            return procesarSaltoDeLinea(actual);
        }

        if (actual.getType() == Token.EOF) {
            return procesarFinDeArchivo(actual);
        }

        return emitir(actual);
    }

    private Token procesarSaltoDeLinea(Token primerSalto) {
        Token ultimoSalto = primerSalto;
        Token siguiente = lexer.nextToken();
        while (siguiente.getType() == YParser.NEWLINE) {
            ultimoSalto = siguiente;
            siguiente = lexer.nextToken();
        }
        adelantado = siguiente;

        if (siguiente.getType() == Token.EOF) {
            finArchivoProcesado = true;
            cerrarBloquesAbiertos(siguiente);
        } else {
            ajustarNivel(contarIndentacion(ultimoSalto.getText()), siguiente);
        }

        CommonToken saltoLinea = new CommonToken(primerSalto);
        saltoLinea.setText("\n");
        return emitir(saltoLinea);
    }

    private Token procesarFinDeArchivo(Token eof) {
        if (finArchivoProcesado) {
            return emitir(eof);
        }
        finArchivoProcesado = true;

        boolean faltaSaltoFinal = ultimoTipoEmitido != -1 && ultimoTipoEmitido != YParser.NEWLINE;

        adelantado = eof;
        cerrarBloquesAbiertos(eof);

        if (faltaSaltoFinal) {
            return emitir(crearToken(YParser.NEWLINE, "\n", eof));
        }
        if (!pendientes.estaVacia()) {
            return emitir(pendientes.desencolar());
        }
        adelantado = null;
        return emitir(eof);
    }

    private void ajustarNivel(int indentacion, Token referencia) {
        int nivelActual = niveles.cima();

        if (indentacion > nivelActual) {
            niveles.apilar(indentacion);
            pendientes.encolar(crearToken(YParser.INDENT, "<INDENT>", referencia));
            return;
        }

        while (niveles.tamanio() > 1 && indentacion < niveles.cima()) {
            niveles.desapilar();
            pendientes.encolar(crearToken(YParser.DEDENT, "<DEDENT>", referencia));
        }
    }

    private void cerrarBloquesAbiertos(Token referencia) {
        while (niveles.tamanio() > 1) {
            niveles.desapilar();
            pendientes.encolar(crearToken(YParser.DEDENT, "<DEDENT>", referencia));
        }
    }

    private Token tomarSiguiente() {
        if (adelantado != null) {
            Token token = adelantado;
            adelantado = null;
            return token;
        }
        return lexer.nextToken();
    }

    private Token emitir(Token token) {
        ultimoTipoEmitido = token.getType();
        return token;
    }

    private int contarIndentacion(String textoSalto) {
        if (textoSalto == null) {
            return 0;
        }
        int inicio = textoSalto.lastIndexOf('\n') + 1;
        int cuenta = 0;
        for (int i = inicio; i < textoSalto.length(); i++) {
            char c = textoSalto.charAt(i);
            if (c == '\t') {
                cuenta += ESPACIOS_POR_TABULACION;
            } else if (c == ' ') {
                cuenta++;
            }
        }
        return cuenta;
    }

    private Token crearToken(int tipo, String texto, Token referencia) {
        Pair<TokenSource, CharStream> fuente = new Pair<>(this, lexer.getInputStream());
        CommonToken token = new CommonToken(fuente, tipo, Token.DEFAULT_CHANNEL,
                referencia.getStartIndex(), referencia.getStartIndex());
        token.setText(texto);
        token.setLine(referencia.getLine());
        token.setCharPositionInLine(referencia.getCharPositionInLine());
        return token;
    }

    @Override
    public int getLine() {
        return lexer.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return lexer.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return lexer.getInputStream();
    }

    @Override
    public String getSourceName() {
        return lexer.getSourceName();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> factory) {
        lexer.setTokenFactory(factory);
    }

    @Override
    public TokenFactory<?> getTokenFactory() {
        return lexer.getTokenFactory();
    }
}
