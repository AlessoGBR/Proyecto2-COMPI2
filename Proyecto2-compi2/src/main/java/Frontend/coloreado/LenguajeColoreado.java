package Frontend.coloreado;

import Backend.antlr.YLexer;
import Backend.antlr.ZetarianoLexer;
import Backend.antlr.pigLatinLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public enum LenguajeColoreado {

    PIG_LATIN {
        @Override
        public Lexer crearLexer(CharStream entrada) {
            return new pigLatinLexer(entrada);
        }

        @Override
        public CategoriaToken clasificar(int tipo) {
            return switch (tipo) {
                case pigLatinLexer.KW_VARIABILES, pigLatinLexer.KW_MUNERA, pigLatinLexer.KW_MAIOR,
                     pigLatinLexer.FINIS_PROGRAMA -> CategoriaToken.SECCION;
                case pigLatinLexer.KW_NUMERUS, pigLatinLexer.KW_TEXTUM, pigLatinLexer.KW_DECIMALIS,
                     pigLatinLexer.KW_LITTERA, pigLatinLexer.KW_BOOL -> CategoriaToken.TIPO_DATO;
                case pigLatinLexer.KW_VERUM, pigLatinLexer.KW_FALSUS, pigLatinLexer.KW_NULL -> CategoriaToken.CONSTANTE;
                case pigLatinLexer.SHIFT_OUT, pigLatinLexer.SHIFT_IN -> CategoriaToken.FUNCION_SISTEMA;
                case pigLatinLexer.KW_IMPORT, pigLatinLexer.KW_NOVUS, pigLatinLexer.FINIS_BLOQUE,
                     pigLatinLexer.KW_ESTO, pigLatinLexer.KW_SERIES, pigLatinLexer.KW_SI,
                     pigLatinLexer.KW_ALITER, pigLatinLexer.KW_DUM, pigLatinLexer.KW_FACERE,
                     pigLatinLexer.KW_PER, pigLatinLexer.KW_PERGE, pigLatinLexer.KW_INTERRUMPE,
                     pigLatinLexer.KW_ACTIO, pigLatinLexer.KW_RATIO, pigLatinLexer.KW_REDDERE,
                     pigLatinLexer.KW_NON -> CategoriaToken.PALABRA_RESERVADA;
                case pigLatinLexer.TEXTUM_LIT, pigLatinLexer.LITTERA_LIT -> CategoriaToken.CADENA;
                case pigLatinLexer.NUMERUS_LIT, pigLatinLexer.DECIMALIS_LIT -> CategoriaToken.NUMERO;
                case pigLatinLexer.ID -> CategoriaToken.IDENTIFICADOR;
                case pigLatinLexer.LPAREN, pigLatinLexer.RPAREN, pigLatinLexer.LBRACE, pigLatinLexer.RBRACE,
                     pigLatinLexer.LBRACKET, pigLatinLexer.RBRACKET, pigLatinLexer.COMMA,
                     pigLatinLexer.SEMI, pigLatinLexer.COLON, pigLatinLexer.DOT -> CategoriaToken.DELIMITADOR;
                default -> CategoriaToken.OPERADOR;
            };
        }

        @Override
        public boolean introduceTipo(int tipo) {
            return tipo == pigLatinLexer.KW_NOVUS;
        }

        @Override
        public boolean esParentesisApertura(int tipo) {
            return tipo == pigLatinLexer.LPAREN;
        }

        @Override
        public boolean continuaSeccion(int tipo) {
            return tipo == pigLatinLexer.GT; // el '>' de VARIABILES> y MAIOR>
        }
    },

    Y {
        @Override
        public Lexer crearLexer(CharStream entrada) {
            return new YLexer(entrada);
        }

        @Override
        public CategoriaToken clasificar(int tipo) {
            return switch (tipo) {
                case YLexer.KW_SEC_ESTRUCTURAS, YLexer.KW_SEC_FUNCIONES -> CategoriaToken.SECCION;
                case YLexer.KW_ENTERO, YLexer.KW_FLOTANTE, YLexer.KW_CADENA, YLexer.KW_CARACTER,
                     YLexer.KW_BOOL -> CategoriaToken.TIPO_DATO;
                case YLexer.KW_VERDADERO, YLexer.KW_FALSO -> CategoriaToken.CONSTANTE;
                case YLexer.KW_IMPRIMIR, YLexer.KW_LEER -> CategoriaToken.FUNCION_SISTEMA;
                case YLexer.KW_ESTRUCTURA, YLexer.KW_DEFINIR, YLexer.KW_SI, YLexer.KW_ENTONCES,
                     YLexer.KW_SINO, YLexer.KW_CONTRARIO, YLexer.KW_ELEGIR, YLexer.KW_CASO,
                     YLexer.KW_SIEMPRE, YLexer.KW_PARA, YLexer.KW_MIENTRAS, YLexer.KW_HACER,
                     YLexer.KW_ROMPER, YLexer.KW_CONTINUAR, YLexer.KW_RETORNAR -> CategoriaToken.PALABRA_RESERVADA;
                case YLexer.CADENA_LIT, YLexer.CARACTER_LIT -> CategoriaToken.CADENA;
                case YLexer.ENTERO_LIT, YLexer.DECIMAL_LIT -> CategoriaToken.NUMERO;
                case YLexer.ID -> CategoriaToken.IDENTIFICADOR;
                case YLexer.LPAREN, YLexer.RPAREN, YLexer.LBRACE, YLexer.RBRACE, YLexer.LBRACKET,
                     YLexer.RBRACKET, YLexer.COMMA, YLexer.SEMI, YLexer.COLON,
                     YLexer.DOT -> CategoriaToken.DELIMITADOR;
                case YLexer.NEWLINE -> null;
                default -> CategoriaToken.OPERADOR;
            };
        }

        @Override
        public boolean introduceTipo(int tipo) {
            return tipo == YLexer.KW_ESTRUCTURA;
        }

        @Override
        public boolean esParentesisApertura(int tipo) {
            return tipo == YLexer.LPAREN;
        }
    },

    ZETARIANO {
        @Override
        public Lexer crearLexer(CharStream entrada) {
            return new ZetarianoLexer(entrada);
        }

        @Override
        public CategoriaToken clasificar(int tipo) {
            return switch (tipo) {
                case ZetarianoLexer.KW_INT, ZetarianoLexer.KW_DOUBLE, ZetarianoLexer.KW_CHAR,
                     ZetarianoLexer.KW_BOOLEAN, ZetarianoLexer.KW_STRING,
                     ZetarianoLexer.KW_VOID -> CategoriaToken.TIPO_DATO;
                case ZetarianoLexer.KW_TRUE, ZetarianoLexer.KW_FALSE, ZetarianoLexer.KW_NULL -> CategoriaToken.CONSTANTE;
                case ZetarianoLexer.KW_PRINTLN, ZetarianoLexer.KW_PRINT,
                     ZetarianoLexer.KW_READLN -> CategoriaToken.FUNCION_SISTEMA;
                case ZetarianoLexer.KW_PUBLIC, ZetarianoLexer.KW_PRIVATE, ZetarianoLexer.KW_PROTECTED,
                     ZetarianoLexer.KW_CLASS, ZetarianoLexer.KW_EXTENDS,
                     ZetarianoLexer.KW_IF, ZetarianoLexer.KW_ELSE, ZetarianoLexer.KW_SWITCH,
                     ZetarianoLexer.KW_CASE, ZetarianoLexer.KW_DEFAULT, ZetarianoLexer.KW_FOR,
                     ZetarianoLexer.KW_WHILE, ZetarianoLexer.KW_DO, ZetarianoLexer.KW_BREAK,
                     ZetarianoLexer.KW_CONTINUE, ZetarianoLexer.KW_RETURN, ZetarianoLexer.KW_NEW,
                     ZetarianoLexer.KW_THIS, ZetarianoLexer.KW_SUPER -> CategoriaToken.PALABRA_RESERVADA;
                case ZetarianoLexer.ANOTACION -> CategoriaToken.SECCION;
                case ZetarianoLexer.STRING_LIT, ZetarianoLexer.CHAR_LIT -> CategoriaToken.CADENA;
                case ZetarianoLexer.ENTERO_LIT, ZetarianoLexer.DECIMAL_LIT -> CategoriaToken.NUMERO;
                case ZetarianoLexer.ID -> CategoriaToken.IDENTIFICADOR;
                case ZetarianoLexer.LPAREN, ZetarianoLexer.RPAREN, ZetarianoLexer.LBRACE,
                     ZetarianoLexer.RBRACE, ZetarianoLexer.LBRACKET, ZetarianoLexer.RBRACKET,
                     ZetarianoLexer.COMMA, ZetarianoLexer.SEMI, ZetarianoLexer.COLON,
                     ZetarianoLexer.DOT -> CategoriaToken.DELIMITADOR;
                default -> CategoriaToken.OPERADOR;
            };
        }

        @Override
        public boolean introduceTipo(int tipo) {
            return tipo == ZetarianoLexer.KW_NEW || tipo == ZetarianoLexer.KW_CLASS
                    || tipo == ZetarianoLexer.KW_EXTENDS;
        }

        @Override
        public boolean esParentesisApertura(int tipo) {
            return tipo == ZetarianoLexer.LPAREN;
        }
    };

    public abstract Lexer crearLexer(CharStream entrada);

    public abstract CategoriaToken clasificar(int tipo);

    public abstract boolean introduceTipo(int tipo);

    public abstract boolean esParentesisApertura(int tipo);

    public boolean continuaSeccion(int tipo) {
        return false;
    }

    public static LenguajeColoreado desdeNombreArchivo(String nombre) {
        String minusculas = nombre.toLowerCase();
        if (minusculas.endsWith(".pig")) {
            return PIG_LATIN;
        }
        if (minusculas.endsWith(".y")) {
            return Y;
        }
        if (minusculas.endsWith(".z")) {
            return ZETARIANO;
        }
        return null;
    }
}
