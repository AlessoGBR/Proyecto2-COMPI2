package Frontend.coloreado;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ColoreadorSintaxis {

    public record Segmento(int inicio, int fin, CategoriaToken categoria) {
    }

    private static final SimpleAttributeSet ESTILO_BASE = new SimpleAttributeSet();

    static {
        StyleConstants.setForeground(ESTILO_BASE, Color.BLACK);
    }

    private ColoreadorSintaxis() {
    }

    public static List<Segmento> analizar(String texto, LenguajeColoreado lenguaje, Set<String> tiposConocidos) {
        Lexer lexer = lenguaje.crearLexer(CharStreams.fromString(texto));
        PosicionesTexto posiciones = new PosicionesTexto(texto);
        boolean[] noReconocido = new boolean[texto.length()];
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> reconocedor, Object simbolo, int linea, int columna,
                                    String mensaje, RecognitionException e) {
                Lexer l = (Lexer) reconocedor;
                int inicio = posiciones.aIndiceChar(l._tokenStartCharIndex);
                int fin = Math.min(posiciones.aIndiceChar(l.getInputStream().index() + 1), texto.length());
                for (int i = inicio; i < fin; i++) {
                    noReconocido[i] = true;
                }
            }
        });
        List<? extends Token> tokens = lexer.getAllTokens();

        Set<String> tipos = new HashSet<>(tiposConocidos);
        for (int i = 1; i < tokens.size(); i++) {
            if (lenguaje.introduceTipo(tokens.get(i - 1).getType())
                    && lenguaje.clasificar(tokens.get(i).getType()) == CategoriaToken.IDENTIFICADOR) {
                tipos.add(tokens.get(i).getText());
            }
        }

        List<Segmento> segmentos = new ArrayList<>();
        int finAnterior = 0;
        Token anterior = null;
        CategoriaToken categoriaAnterior = null;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            int inicio = posiciones.aIndiceChar(token.getStartIndex());
            int fin = posiciones.aIndiceChar(token.getStopIndex() + 1);
            agregarComentarios(texto, noReconocido, finAnterior, inicio, segmentos);

            CategoriaToken categoria = lenguaje.clasificar(token.getType());
            if (categoria == CategoriaToken.IDENTIFICADOR) {
                if (tipos.contains(token.getText())) {
                    categoria = CategoriaToken.TIPO_DATO;
                } else if (i + 1 < tokens.size() && lenguaje.esParentesisApertura(tokens.get(i + 1).getType())) {
                    categoria = CategoriaToken.FUNCION;
                }
            } else if (categoriaAnterior == CategoriaToken.SECCION && lenguaje.continuaSeccion(token.getType())
                    && anterior.getStopIndex() + 1 == token.getStartIndex()) {
                categoria = CategoriaToken.SECCION;
            }

            if (categoria != null) {
                segmentos.add(new Segmento(inicio, fin, categoria));
            }
            finAnterior = fin;
            anterior = token;
            categoriaAnterior = categoria;
        }
        agregarComentarios(texto, noReconocido, finAnterior, texto.length(), segmentos);
        return segmentos;
    }

    private static void agregarComentarios(String texto, boolean[] noReconocido, int desde, int hasta,
                                           List<Segmento> segmentos) {
        int i = desde;
        while (i < hasta) {
            if (Character.isWhitespace(texto.charAt(i)) || noReconocido[i]) {
                i++;
                continue;
            }
            int inicio = i;
            while (i < hasta && !Character.isWhitespace(texto.charAt(i)) && !noReconocido[i]) {
                i++;
            }
            segmentos.add(new Segmento(inicio, i, CategoriaToken.COMENTARIO));
        }
    }

    public static void aplicar(StyledDocument documento, List<Segmento> segmentos) {
        documento.setCharacterAttributes(0, documento.getLength(), ESTILO_BASE, true);
        for (Segmento s : segmentos) {
            if (s.fin() <= documento.getLength()) {
                documento.setCharacterAttributes(s.inicio(), s.fin() - s.inicio(), s.categoria().getEstilo(), true);
            }
        }
    }

    private static final class PosicionesTexto {
        private final int[] indiceChar;

        PosicionesTexto(String texto) {
            int codePoints = texto.codePointCount(0, texto.length());
            if (codePoints == texto.length()) {
                indiceChar = null;
                return;
            }
            indiceChar = new int[codePoints + 1];
            int charActual = 0;
            for (int cp = 0; cp < codePoints; cp++) {
                indiceChar[cp] = charActual;
                charActual += Character.charCount(texto.codePointAt(charActual));
            }
            indiceChar[codePoints] = texto.length();
        }

        int aIndiceChar(int codePoint) {
            if (indiceChar == null) {
                return codePoint;
            }
            return indiceChar[Math.min(Math.max(codePoint, 0), indiceChar.length - 1)];
        }
    }
}
