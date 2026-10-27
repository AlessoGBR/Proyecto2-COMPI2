grammar pigLatin;

programa
    : seccionImportaciones? seccionVariablesGlobales? seccionFunciones? seccionPrincipal EOF
    ;

seccionImportaciones
    : importacion+
    ;

importacion
    : KW_IMPORT rutaImport SEMI?
    ;

rutaImport
    : ID (DOT ID)+
    ;

seccionVariablesGlobales
    : KW_VARIABILES GT declaracionGlobal*
    ;

declaracionGlobal
    : declaracionVariable
    | declaracionArreglo
    ;

seccionFunciones
    : KW_MUNERA GT definicionFuncion*
    ;

seccionPrincipal
    : KW_MAIOR GT instruccion* FINIS_PROGRAMA SEMI?
    ;

declaracionVariable
    : KW_ESTO ID COLON? inicializadorVariable SEMI?
    ;

inicializadorVariable
    : tipoPrimitivo expresion?
    | KW_BOOL expresion?
    | ID literalStruct
    | expresion COLON tipo
    | instanciacionObjeto
    | valorBooleano
    | tipo (expresion | literalStruct)?
    | expresion
    ;

instanciacionObjeto
    : KW_NOVUS ID LPAREN listaArgumentos? RPAREN
    ;

valorBooleano
    : KW_VERUM
    | KW_FALSUS
    ;

declaracionArreglo
    : KW_SERIES ID LBRACKET expresion RBRACKET COLON? tipo (LBRACE listaValoresArreglo RBRACE)? SEMI?
    ;

listaValoresArreglo
    : elementoArreglo (COMMA elementoArreglo)*
    ;

elementoArreglo
    : literalStruct
    | expresion
    ;

literalStruct
    : LBRACE (elementoStruct (COMMA elementoStruct)*)? RBRACE
    ;

elementoStruct
    : ID COLON (literalStruct | expresion)
    | literalStruct
    | expresion
    ;

tipoPrimitivo
    : KW_NUMERUS
    | KW_TEXTUM
    | KW_DECIMALIS
    | KW_LITTERA
    ;

tipo
    : tipoPrimitivo
    | KW_BOOL
    | ID
    ;

definicionFuncion
    : funcionSinRetorno
    | funcionConRetorno
    ;

funcionSinRetorno
    : KW_ACTIO ID LPAREN listaParametros? RPAREN
      LBRACE seccionVariablesLocales? instruccion* RBRACE FINIS_BLOQUE SEMI?
    ;

funcionConRetorno
    : KW_RATIO tipo ID LPAREN listaParametros? RPAREN
      LBRACE seccionVariablesLocales? instruccion* RBRACE FINIS_BLOQUE SEMI?
    ;

listaParametros
    : parametro (COMMA parametro)*
    ;

parametro
    : KW_ESTO ID COLON? tipo
    ;

seccionVariablesLocales
    : KW_VARIABILES LBRACKET declaracionLocal* RBRACKET
    ;

declaracionLocal
    : declaracionVariable
    | declaracionArreglo
    ;

instruccion
    : declaracionVariable
    | declaracionArreglo
    | instruccionImprimir
    | instruccionLeer
    | asignacion
    | instruccionIncrementoDecremento
    | instruccionSi
    | instruccionDum
    | instruccionFacere
    | instruccionPer
    | instruccionInterrumpe
    | instruccionPerge
    | instruccionReddere
    | instruccionLlamadaMetodo
    ;

asignacion
    : destino ASSIGN (literalStruct | expresion) SEMI?
    ;

destino
    : ID accesoMiembro*
    ;

accesoMiembro
    : DOT ID
    | LBRACKET expresion RBRACKET
    ;

instruccionLlamadaMetodo
    : llamadaMetodo SEMI?
    ;

llamadaMetodo
    : ID (accesoPostfijo)+
    ;

accesoPostfijo
    : DOT ID
    | LBRACKET expresion RBRACKET
    | LPAREN listaArgumentos? RPAREN
    ;

listaArgumentos
    : expresion (COMMA expresion)*
    ;

instruccionSi
    : KW_SI LPAREN expresion RPAREN bloqueInstrucciones
      ramaAliterCondicional*
      ramaAliterFinal?
      FINIS_BLOQUE SEMI?
    ;

ramaAliterCondicional
    : KW_ALITER LPAREN expresion RPAREN bloqueInstrucciones
    ;

ramaAliterFinal
    : KW_ALITER bloqueInstrucciones
    ;

bloqueInstrucciones
    : LBRACE instruccion* RBRACE
    ;

instruccionDum
    : KW_DUM LPAREN expresion RPAREN bloqueInstrucciones FINIS_BLOQUE SEMI?
    ;

instruccionFacere
    : KW_FACERE bloqueInstrucciones KW_DUM LPAREN expresion RPAREN SEMI?
    ;

instruccionPer
    : KW_PER LPAREN declaracionCicloFor SEMI expresion SEMI actualizacionCiclo RPAREN
      bloqueInstrucciones (FINIS_BLOQUE SEMI?)?
    ;

declaracionCicloFor
    : KW_ESTO ID COLON? tipo expresion?
    ;

actualizacionCiclo
    : destino (INC | DEC)
    | destino ASSIGN expresion
    ;

instruccionInterrumpe
    : KW_INTERRUMPE SEMI?
    ;

instruccionPerge
    : KW_PERGE SEMI?
    ;

instruccionReddere
    : KW_REDDERE expresion? SEMI?
    ;

instruccionImprimir
    : SHIFT_OUT expresion (SHIFT_OUT expresion)* SEMI?
    ;

instruccionLeer
    : destino? SHIFT_IN SEMI?
    ;

instruccionIncrementoDecremento
    : destino (INC | DEC) SEMI?
    ;

expresion
    : expresion (INC | DEC)                                         # ExprPostfija
    | expresion DOT ID                                              # ExprAccesoMiembro
    | expresion LBRACKET expresion RBRACKET                         # ExprIndexacion
    | expresion LPAREN listaArgumentos? RPAREN                      # ExprLlamadaMetodo
    | instanciacionObjeto                                           # ExprInstanciacionNovus
    | KW_NON expresion                                              # ExprNegacionLogica
    | NOT expresion                                                 # ExprNotSimbolo
    | MINUS expresion                                               # ExprMenosUnario
    | expresion op=(STAR | SLASH | MOD) expresion                   # ExprMultiplicativa
    | expresion op=(PLUS | MINUS) expresion                         # ExprAditiva
    | expresion op=(LE | GE | LT | GT) expresion                    # ExprRelacional
    | expresion op=(EQ | NEQ | ASSIGN) expresion                    # ExprIgualdad
    | expresion AND expresion                                       # ExprAndLogico
    | expresion OR expresion                                        # ExprOrLogico
    | LPAREN expresion RPAREN                                       # ExprParentesis
    | ID                                                            # ExprIdentificador
    | literal                                                       # ExprLiteral
    ;

literal
    : NUMERUS_LIT
    | DECIMALIS_LIT
    | TEXTUM_LIT
    | LITTERA_LIT
    | KW_VERUM
    | KW_FALSUS
    | KW_NULL
    ;


KW_IMPORT     : 'import';
KW_NOVUS      : 'novus';

KW_VARIABILES : 'VARIABILES';
KW_MUNERA     : 'MUNERA';
KW_MAIOR      : 'MAIOR';
FINIS_PROGRAMA: 'FINIS';
FINIS_BLOQUE  : 'finis';

KW_ESTO       : 'esto';
KW_SERIES     : 'series';

KW_NUMERUS    : 'numerus';
KW_TEXTUM     : 'textum';
KW_DECIMALIS  : 'decimalis';
KW_LITTERA    : 'littera';
KW_BOOL       : 'bool';

KW_VERUM      : 'verum';
KW_FALSUS     : 'falsus';
KW_NULL       : 'null';

KW_SI         : 'si';
KW_ALITER     : 'aliter';
KW_DUM        : 'dum';
KW_FACERE     : 'facere';
KW_PER        : 'per';
KW_PERGE      : 'perge';
KW_INTERRUMPE : 'interrumpe';

KW_ACTIO      : 'actio';
KW_RATIO      : 'ratio';
KW_REDDERE    : 'reddere';

KW_NON        : 'non';
NOT           : '!';

LE            : '<=';
GE            : '>=';
EQ            : '==';
NEQ           : '!=';
LT            : '<';
GT            : '>';

AND           : '&&';
OR            : '||';

PLUS          : '+';
MINUS         : '-';
STAR          : '*';
SLASH         : '/';
MOD           : '%';

INC           : '++';
DEC           : '--';

SHIFT_OUT     : '>>';
SHIFT_IN      : '<<';

ASSIGN        : '=';
DOT           : '.';
COMMA         : ',';
SEMI          : ';';
COLON         : ':';
LPAREN        : '(';
RPAREN        : ')';
LBRACE        : '{';
RBRACE        : '}';
LBRACKET      : '[';
RBRACKET      : ']';

DECIMALIS_LIT : [0-9]+ '.' [0-9]+;
NUMERUS_LIT   : [0-9]+;
TEXTUM_LIT    : '"' (ESC_SEQ | ~["\\\r\n])* '"';
LITTERA_LIT   : '\'' (ESC_SEQ | ~['\\\r\n]) '\'';

fragment ESC_SEQ : '\\' ["'\\btnrf];

ID            : [a-zA-Z_][a-zA-Z_0-9]*;

LINE_COMMENT  : '//' ~[\r\n]*  -> skip;
BLOCK_COMMENT : '##' .*? '##'  -> skip;
WS            : [ \t\r\n\f]+   -> skip;
