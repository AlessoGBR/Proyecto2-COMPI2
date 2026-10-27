grammar Y;


tokens { INDENT, DEDENT }


programa
    : (NEWLINE | SEMI)* seccionEstructuras? (NEWLINE | SEMI)* seccionFunciones (NEWLINE | SEMI)* EOF
    ;

seccionEstructuras
    : KW_SEC_ESTRUCTURAS finSentencia declaracionEstructura*
    ;

declaracionEstructura
    : KW_ESTRUCTURA ID COLON finSentencia bloqueEstructura
    ;

bloqueEstructura
    : INDENT campoEstructura+ DEDENT
    | LBRACE finSentencia? campoEstructura* RBRACE finSentencia?
    ;

campoEstructura
    : tipoY ID (LBRACKET expresion RBRACKET)* finSentencia
    ;

seccionFunciones
    : KW_SEC_FUNCIONES finSentencia definicionFuncion*
    ;

definicionFuncion
    : KW_DEFINIR ID LPAREN listaParametrosY? RPAREN (FLECHA tipoY)? COLON finSentencia bloque
    ;

listaParametrosY
    : parametroY (COMMA parametroY)*
    ;

parametroY
    : LBRACKET RBRACKET tipoY ID   
    | LBRACE RBRACE tipoY ID       
    | tipoY ID                     
    ;

bloque
    : INDENT instruccion+ DEDENT
    | LBRACE finSentencia? instruccion* RBRACE finSentencia?
    ;

instruccion
    : declaracionEstructuraLocal
    | declaracionVariableY
    | asignacionY
    | instruccionIncrementoDecrementoY
    | instruccionSiY
    | instruccionElegirY
    | instruccionParaY
    | instruccionMientrasY
    | instruccionHacerMientrasY
    | instruccionRomper
    | instruccionContinuar
    | instruccionRetornar
    | instruccionImprimirY
    | instruccionLeerY
    | instruccionLlamadaMetodoY
    | finSentencia
    | RBRACE finSentencia?
    ;

declaracionEstructuraLocal
    : declaracionEstructura
    ;

declaracionVariableY
    : tipoY ID (LBRACKET expresion RBRACKET)* (ASSIGN inicializadorY)? finSentencia
    ;

inicializadorY
    : literalArregloOEstructura
    | expresion
    ;

literalArregloOEstructura
    : LBRACE (expresion (COMMA expresion)*)? COMMA? RBRACE
    ;

asignacionY
    : destinoY ASSIGN (literalArregloOEstructura | expresion) finSentencia
    ;

destinoY
    : ID accesoPostfijoY*
    ;

accesoPostfijoY
    : DOT ID
    | LBRACKET expresion RBRACKET
    | LPAREN listaArgumentosY? RPAREN
    ;

instruccionIncrementoDecrementoY
    : destinoY op=(INC | DEC) finSentencia
    ;

instruccionSiY
    : KW_SI condicionY KW_ENTONCES finSentencia bloque
      ramaSinoY*
      ramaContrarioY?
    ;

condicionY
    : LPAREN expresion RPAREN
    | expresion
    ;

ramaSinoY
    : KW_SINO condicionY KW_ENTONCES finSentencia bloque
    ;

ramaContrarioY
    : KW_CONTRARIO finSentencia bloque
    ;

instruccionElegirY
    : KW_ELEGIR LPAREN? expresion RPAREN? COLON finSentencia bloqueElegir
    ;

bloqueElegir
    : INDENT casoElegir+ siempreElegir? DEDENT
    | LBRACE finSentencia? casoElegir+ siempreElegir? RBRACE finSentencia?
    ;

casoElegir
    : KW_CASO expresion COLON finSentencia bloque
    ;

siempreElegir
    : KW_SIEMPRE COLON finSentencia bloque
    ;

instruccionParaY
    : KW_PARA LPAREN declaracionParaY SEMI expresion SEMI actualizacionParaY RPAREN COLON finSentencia bloque
    ;

declaracionParaY
    : tipoY ID ASSIGN expresion
    ;

actualizacionParaY
    : expresion
    ;

instruccionMientrasY
    : KW_MIENTRAS LPAREN? expresion RPAREN? KW_HACER finSentencia bloque
    ;

instruccionHacerMientrasY
    : KW_HACER COLON finSentencia bloque KW_MIENTRAS LPAREN? expresion RPAREN? finSentencia
    ;

instruccionRomper
    : KW_ROMPER finSentencia
    ;

instruccionContinuar
    : KW_CONTINUAR finSentencia
    ;

instruccionRetornar
    : KW_RETORNAR expresion? finSentencia
    ;

instruccionImprimirY
    : KW_IMPRIMIR LPAREN expresion RPAREN finSentencia
    ;

instruccionLeerY
    : KW_LEER LPAREN RPAREN finSentencia
    ;

instruccionLlamadaMetodoY
    : llamadaMetodoY finSentencia
    ;

llamadaMetodoY
    : ID (accesoPostfijoY)+
    ;

listaArgumentosY
    : expresion (COMMA expresion)*
    ;

finSentencia
    : (SEMI | NEWLINE)+
    ;

expresion
    : expresion op=(INC | DEC)                                         # ExprPostfijaY
    | expresion DOT ID                                                 # ExprAccesoMiembroY
    | expresion LBRACKET expresion RBRACKET                            # ExprIndexacionY
    | expresion LPAREN listaArgumentosY? RPAREN                        # ExprLlamadaMetodoY
    | op=(INC | DEC) expresion                                         # ExprPrefijaIncDecY
    | NOT expresion                                                    # ExprNotLogicoY
    | MINUS expresion                                                  # ExprMenosUnarioY
    | expresion op=(STAR | SLASH) expresion                            # ExprMultiplicativaY
    | expresion op=(PLUS | MINUS) expresion                            # ExprAditivaY
    | expresion op=(LT | LE | GT | GE) expresion                       # ExprRelacionalY
    | expresion op=(EQ | NEQ) expresion                                # ExprIgualdadY
    | expresion AND expresion                                          # ExprAndLogicoY
    | expresion OR expresion                                           # ExprOrLogicoY
    | KW_LEER LPAREN RPAREN                                            # ExprLeerY
    | LPAREN expresion RPAREN                                          # ExprParentesisY
    | literalArregloOEstructura                                        # ExprLiteralEstructuraY
    | literalY                                                         # ExprLiteralY
    | ID                                                               # ExprIdentificadorY
    ;

tipoY
    : tipoBasico
    | ID
    ;

tipoBasico
    : KW_ENTERO
    | KW_FLOTANTE
    | KW_CADENA
    | KW_CARACTER
    | KW_BOOL
    ;

literalY
    : ENTERO_LIT
    | DECIMAL_LIT
    | CADENA_LIT
    | CARACTER_LIT
    | KW_VERDADERO
    | KW_FALSO
    ;

// LEXER

KW_SEC_ESTRUCTURAS : '%estructuras';
KW_SEC_FUNCIONES   : '%funciones';
KW_ESTRUCTURA      : 'estructura';
KW_DEFINIR         : 'definir';

KW_ENTERO          : 'entero';
KW_FLOTANTE        : 'flotante';
KW_CADENA          : 'cadena';
KW_CARACTER        : 'caracter';
KW_BOOL            : 'bool';

KW_VERDADERO       : 'verdadero';
KW_FALSO           : 'falso';

KW_SI              : 'si';
KW_ENTONCES        : 'entonces';
KW_SINO            : 'sino';
KW_CONTRARIO       : 'contrario';

KW_ELEGIR          : 'elegir';
KW_CASO            : 'caso';
KW_SIEMPRE         : 'siempre';

KW_PARA            : 'para';
KW_MIENTRAS        : 'mientras';
KW_HACER           : 'hacer';
KW_ROMPER          : 'romper';
KW_CONTINUAR       : 'continuar';
KW_RETORNAR        : 'retornar';

KW_IMPRIMIR        : 'imprimir';
KW_LEER            : 'leer';

FLECHA             : '->';
EQ                 : '==';
NEQ                : '!=';
LE                 : '<=';
GE                 : '>=';
LT                 : '<';
GT                 : '>';

AND                : '&&';
OR                 : '||';
NOT                : '!';

INC                : '++';
DEC                : '--';

PLUS               : '+';
MINUS              : '-';
STAR               : '*';
SLASH              : '/';

ASSIGN             : '=';
COLON              : ':';
SEMI               : ';';
COMMA              : ',';
DOT                : '.';

LPAREN             : '(';
RPAREN             : ')';
LBRACE             : '{';
RBRACE             : '}';
LBRACKET           : '[';
RBRACKET           : ']';

DECIMAL_LIT        : [0-9]+ '.' [0-9]+;
ENTERO_LIT         : [0-9]+;
CADENA_LIT         : '"' (ESC_SEQ | ~["\\\r\n])* '"';
CARACTER_LIT       : '\'' (ESC_SEQ | ~['\\\r\n]) '\'';

fragment ESC_SEQ   : '\\' ["'\\btnrf];

ID                 : [a-zA-Z_][a-zA-Z_0-9]*;

NEWLINE            : ('\r'? '\n' [ \t]*)+;

WS                 : [ \t]+ -> skip;
LINE_COMMENT       : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT      : '/*' .*? '*/' -> skip;
