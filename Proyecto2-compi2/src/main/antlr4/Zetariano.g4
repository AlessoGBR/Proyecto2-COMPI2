grammar Zetariano;


programa
    : definicionClase EOF
    ;

definicionClase
    : KW_PUBLIC? KW_CLASS ID (KW_EXTENDS ID (COMMA ID)*)? LBRACE miembroClase* RBRACE
    ;

miembroClase
    : declaracionCampo
    | definicionConstructor
    | definicionMetodo
    | SEMI
    ;

declaracionCampo
    : modificadorAcceso? tipo ID (ASSIGN (expresion | literalArreglo))? SEMI
    ;

definicionConstructor
    : modificadorAcceso? ID LPAREN listaParametros? RPAREN bloque
    ;

definicionMetodo
    : ANOTACION? modificadorAcceso? tipoRetorno ID LPAREN listaParametros? RPAREN bloque
    ;

modificadorAcceso
    : KW_PUBLIC
    | KW_PRIVATE
    | KW_PROTECTED
    ;

tipoRetorno
    : tipo
    | KW_VOID
    ;

tipo
    : tipoBasico dimensionesTipo*
    ;

tipoBasico
    : KW_INT
    | KW_DOUBLE
    | KW_CHAR
    | KW_BOOLEAN
    | KW_STRING
    | ID
    ;

dimensionesTipo
    : LBRACKET RBRACKET
    ;

listaParametros
    : parametro (COMMA parametro)*
    ;

parametro
    : tipo ID
    ;

bloque
    : LBRACE instruccion* RBRACE
    ;

instruccion
    : bloque
    | declaracionVariableLocal
    | instruccionIf
    | instruccionSwitch
    | instruccionFor
    | instruccionWhile
    | instruccionDoWhile
    | instruccionBreak
    | instruccionContinue
    | instruccionReturn
    | instruccionExpresion
    | SEMI
    ;

declaracionVariableLocal
    : tipo listaDeclaradoresVariable SEMI
    ;

listaDeclaradoresVariable
    : declaradorVariable (COMMA declaradorVariable)*
    ;

declaradorVariable
    : ID (ASSIGN inicializador)?
    ;

inicializador
    : expresion
    | literalArreglo
    ;

literalArreglo
    : LBRACE (inicializador (COMMA inicializador)*)? COMMA? RBRACE
    ;

instruccionIf
    : KW_IF LPAREN expresion RPAREN instruccion (KW_ELSE instruccion)?
    ;

instruccionSwitch
    : KW_SWITCH LPAREN expresion RPAREN LBRACE bloqueSwitch* RBRACE
    ;

bloqueSwitch
    : etiquetaSwitch+ instruccion*
    ;

etiquetaSwitch
    : KW_CASE expresion COLON
    | KW_DEFAULT COLON
    ;

instruccionFor
    : KW_FOR LPAREN forInit? SEMI expresion? SEMI forUpdate? RPAREN instruccion
    ;

forInit
    : tipo listaDeclaradoresVariable
    | listaExpresiones
    ;

forUpdate
    : listaExpresiones
    ;

listaExpresiones
    : expresion (COMMA expresion)*
    ;

instruccionWhile
    : KW_WHILE LPAREN expresion RPAREN instruccion
    ;

instruccionDoWhile
    : KW_DO instruccion KW_WHILE LPAREN expresion RPAREN SEMI
    ;

instruccionBreak
    : KW_BREAK SEMI
    ;

instruccionContinue
    : KW_CONTINUE SEMI
    ;

instruccionReturn
    : KW_RETURN expresion? SEMI
    ;

instruccionExpresion
    : expresion SEMI
    ;

expresion
    : expresion op=(INC | DEC)                                                                       # ExprPostfijoIncDec
    | expresion DOT ID                                                                               # ExprAccesoMiembro
    | expresion LBRACKET expresion RBRACKET                                                          # ExprIndexacion
    | expresion LPAREN listaArgumentos? RPAREN                                                       # ExprLlamadaMetodo
    | op=(INC | DEC) expresion                                                                       # ExprPrefijoIncDec
    | op=(PLUS | MINUS) expresion                                                                    # ExprUnariaAritmetica
    | BANG expresion                                                                                 # ExprNotLogico
    | KW_NEW ID LPAREN listaArgumentos? RPAREN                                                       # ExprCreacionObjeto
    | KW_NEW tipoBasico dimensionArregloNueva+                                                       # ExprCreacionArreglo
    | expresion op=(STAR | SLASH | MOD) expresion                                                    # ExprMultiplicativa
    | expresion op=(PLUS | MINUS) expresion                                                          # ExprAditiva
    | expresion op=(LT | LE | GT | GE) expresion                                                     # ExprRelacional
    | expresion op=(EQ | NEQ) expresion                                                              # ExprIgualdad
    | expresion AND expresion                                                                        # ExprAndLogico
    | expresion OR expresion                                                                         # ExprOrLogico
    | <assoc=right> expresion QUESTION expresion COLON expresion                                     # ExprTernaria
    | <assoc=right> expresion op=(ASSIGN | ADD_ASSIGN | SUB_ASSIGN | MUL_ASSIGN | DIV_ASSIGN | MOD_ASSIGN) expresion # ExprAsignacion
    | llamadaSistema                                                                                 # ExprLlamadaSistema
    | LPAREN expresion RPAREN                                                                        # ExprParentesis
    | literalArreglo                                                                                 # ExprLiteralArreglo
    | literal                                                                                        # ExprLiteral
    | KW_THIS                                                                                        # ExprThis
    | KW_SUPER                                                                                       # ExprSuper
    | ID                                                                                             # ExprIdentificador
    ;

dimensionArregloNueva
    : LBRACKET expresion RBRACKET
    ;

llamadaSistema
    : KW_PRINTLN LPAREN expresion? RPAREN
    | KW_PRINT LPAREN expresion? RPAREN
    | KW_READLN LPAREN RPAREN
    ;

listaArgumentos
    : expresion (COMMA expresion)*
    ;

literal
    : ENTERO_LIT
    | DECIMAL_LIT
    | STRING_LIT
    | CHAR_LIT
    | KW_TRUE
    | KW_FALSE
    | KW_NULL
    ;


KW_PUBLIC    : 'public';
KW_PRIVATE   : 'private';
KW_PROTECTED : 'protected';
KW_CLASS     : 'class';
KW_EXTENDS   : 'extends';
KW_VOID      : 'void';

KW_INT       : 'int';
KW_DOUBLE    : 'double';
KW_CHAR      : 'char';
KW_BOOLEAN   : 'boolean';
KW_STRING    : 'String';

KW_IF        : 'if';
KW_ELSE      : 'else';
KW_SWITCH    : 'switch';
KW_CASE      : 'case';
KW_DEFAULT   : 'default';
KW_FOR       : 'for';
KW_WHILE     : 'while';
KW_DO        : 'do';
KW_BREAK     : 'break';
KW_CONTINUE  : 'continue';
KW_RETURN    : 'return';

KW_NEW       : 'new';
KW_THIS      : 'this';
KW_SUPER     : 'super';
KW_TRUE      : 'true';
KW_FALSE     : 'false';
KW_NULL      : 'null';

KW_PRINTLN   : 'println';
KW_PRINT     : 'print';
KW_READLN    : 'readln';

ADD_ASSIGN   : '+=';
SUB_ASSIGN   : '-=';
MUL_ASSIGN   : '*=';
DIV_ASSIGN   : '/=';
MOD_ASSIGN   : '%=';

EQ           : '==';
NEQ          : '!=';
LE           : '<=';
GE           : '>=';
LT           : '<';
GT           : '>';

AND          : '&&';
OR           : '||';
BANG         : '!';

INC          : '++';
DEC          : '--';

PLUS         : '+';
MINUS        : '-';
STAR         : '*';
SLASH        : '/';
MOD          : '%';

ASSIGN       : '=';
QUESTION     : '?';
COLON        : ':';
SEMI         : ';';
COMMA        : ',';
DOT          : '.';

LPAREN       : '(';
RPAREN       : ')';
LBRACE       : '{';
RBRACE       : '}';
LBRACKET     : '[';
RBRACKET     : ']';

DECIMAL_LIT  : [0-9]+ '.' [0-9]+;
ENTERO_LIT   : [0-9]+;
STRING_LIT   : '"' (ESC_SEQ | ~["\\\r\n])* '"';
CHAR_LIT     : '\'' (ESC_SEQ | ~['\\\r\n]) '\'';

fragment ESC_SEQ : '\\' ["'\\btnrf];

// Se acepta cualquier '@Nombre' para que una anotacion mal escrita sea error semantico y no lexico
ANOTACION    : '@' [a-zA-Z_][a-zA-Z_0-9]*;

ID           : [a-zA-Z_][a-zA-Z_0-9]*;

LINE_COMMENT  : '//' ~[\r\n]*  -> skip;
BLOCK_COMMENT : '/*' .*? '*/'  -> skip;
WS            : [ \t\r\n\f]+   -> skip;

