// Generated from pigLatin.g4 by ANTLR 4.13.1
package Backend.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class pigLatinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		KW_IMPORT=1, KW_NOVUS=2, KW_VARIABILES=3, KW_MUNERA=4, KW_MAIOR=5, FINIS_PROGRAMA=6, 
		FINIS_BLOQUE=7, KW_ESTO=8, KW_SERIES=9, KW_NUMERUS=10, KW_TEXTUM=11, KW_DECIMALIS=12, 
		KW_LITTERA=13, KW_BOOL=14, KW_VERUM=15, KW_FALSUS=16, KW_NULL=17, KW_SI=18, 
		KW_ALITER=19, KW_DUM=20, KW_FACERE=21, KW_PER=22, KW_PERGE=23, KW_INTERRUMPE=24, 
		KW_ACTIO=25, KW_RATIO=26, KW_REDDERE=27, KW_NON=28, NOT=29, LE=30, GE=31, 
		EQ=32, NEQ=33, LT=34, GT=35, AND=36, OR=37, PLUS=38, MINUS=39, STAR=40, 
		SLASH=41, MOD=42, INC=43, DEC=44, SHIFT_OUT=45, SHIFT_IN=46, ASSIGN=47, 
		DOT=48, COMMA=49, SEMI=50, COLON=51, LPAREN=52, RPAREN=53, LBRACE=54, 
		RBRACE=55, LBRACKET=56, RBRACKET=57, DECIMALIS_LIT=58, NUMERUS_LIT=59, 
		TEXTUM_LIT=60, LITTERA_LIT=61, ID=62, LINE_COMMENT=63, BLOCK_COMMENT=64, 
		WS=65;
	public static final int
		RULE_programa = 0, RULE_seccionImportaciones = 1, RULE_importacion = 2, 
		RULE_rutaImport = 3, RULE_seccionVariablesGlobales = 4, RULE_declaracionGlobal = 5, 
		RULE_seccionFunciones = 6, RULE_seccionPrincipal = 7, RULE_declaracionVariable = 8, 
		RULE_inicializadorVariable = 9, RULE_instanciacionObjeto = 10, RULE_valorBooleano = 11, 
		RULE_declaracionArreglo = 12, RULE_listaValoresArreglo = 13, RULE_elementoArreglo = 14, 
		RULE_literalStruct = 15, RULE_elementoStruct = 16, RULE_tipoPrimitivo = 17, 
		RULE_tipo = 18, RULE_definicionFuncion = 19, RULE_funcionSinRetorno = 20, 
		RULE_funcionConRetorno = 21, RULE_listaParametros = 22, RULE_parametro = 23, 
		RULE_seccionVariablesLocales = 24, RULE_declaracionLocal = 25, RULE_instruccion = 26, 
		RULE_asignacion = 27, RULE_destino = 28, RULE_accesoMiembro = 29, RULE_instruccionLlamadaMetodo = 30, 
		RULE_llamadaMetodo = 31, RULE_accesoPostfijo = 32, RULE_listaArgumentos = 33, 
		RULE_instruccionSi = 34, RULE_ramaAliterCondicional = 35, RULE_ramaAliterFinal = 36, 
		RULE_bloqueInstrucciones = 37, RULE_instruccionDum = 38, RULE_instruccionFacere = 39, 
		RULE_instruccionPer = 40, RULE_declaracionCicloFor = 41, RULE_actualizacionCiclo = 42, 
		RULE_instruccionInterrumpe = 43, RULE_instruccionPerge = 44, RULE_instruccionReddere = 45, 
		RULE_instruccionImprimir = 46, RULE_instruccionLeer = 47, RULE_instruccionIncrementoDecremento = 48, 
		RULE_expresion = 49, RULE_literal = 50;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "seccionImportaciones", "importacion", "rutaImport", "seccionVariablesGlobales", 
			"declaracionGlobal", "seccionFunciones", "seccionPrincipal", "declaracionVariable", 
			"inicializadorVariable", "instanciacionObjeto", "valorBooleano", "declaracionArreglo", 
			"listaValoresArreglo", "elementoArreglo", "literalStruct", "elementoStruct", 
			"tipoPrimitivo", "tipo", "definicionFuncion", "funcionSinRetorno", "funcionConRetorno", 
			"listaParametros", "parametro", "seccionVariablesLocales", "declaracionLocal", 
			"instruccion", "asignacion", "destino", "accesoMiembro", "instruccionLlamadaMetodo", 
			"llamadaMetodo", "accesoPostfijo", "listaArgumentos", "instruccionSi", 
			"ramaAliterCondicional", "ramaAliterFinal", "bloqueInstrucciones", "instruccionDum", 
			"instruccionFacere", "instruccionPer", "declaracionCicloFor", "actualizacionCiclo", 
			"instruccionInterrumpe", "instruccionPerge", "instruccionReddere", "instruccionImprimir", 
			"instruccionLeer", "instruccionIncrementoDecremento", "expresion", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'novus'", "'VARIABILES'", "'MUNERA'", "'MAIOR'", "'FINIS'", 
			"'finis'", "'esto'", "'series'", "'numerus'", "'textum'", "'decimalis'", 
			"'littera'", "'bool'", "'verum'", "'falsus'", "'null'", "'si'", "'aliter'", 
			"'dum'", "'facere'", "'per'", "'perge'", "'interrumpe'", "'actio'", "'ratio'", 
			"'reddere'", "'non'", "'!'", "'<='", "'>='", "'=='", "'!='", "'<'", "'>'", 
			"'&&'", "'||'", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", "'>>'", 
			"'<<'", "'='", "'.'", "','", "';'", "':'", "'('", "')'", "'{'", "'}'", 
			"'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "KW_IMPORT", "KW_NOVUS", "KW_VARIABILES", "KW_MUNERA", "KW_MAIOR", 
			"FINIS_PROGRAMA", "FINIS_BLOQUE", "KW_ESTO", "KW_SERIES", "KW_NUMERUS", 
			"KW_TEXTUM", "KW_DECIMALIS", "KW_LITTERA", "KW_BOOL", "KW_VERUM", "KW_FALSUS", 
			"KW_NULL", "KW_SI", "KW_ALITER", "KW_DUM", "KW_FACERE", "KW_PER", "KW_PERGE", 
			"KW_INTERRUMPE", "KW_ACTIO", "KW_RATIO", "KW_REDDERE", "KW_NON", "NOT", 
			"LE", "GE", "EQ", "NEQ", "LT", "GT", "AND", "OR", "PLUS", "MINUS", "STAR", 
			"SLASH", "MOD", "INC", "DEC", "SHIFT_OUT", "SHIFT_IN", "ASSIGN", "DOT", 
			"COMMA", "SEMI", "COLON", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACKET", 
			"RBRACKET", "DECIMALIS_LIT", "NUMERUS_LIT", "TEXTUM_LIT", "LITTERA_LIT", 
			"ID", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "pigLatin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public pigLatinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public SeccionPrincipalContext seccionPrincipal() {
			return getRuleContext(SeccionPrincipalContext.class,0);
		}
		public TerminalNode EOF() { return getToken(pigLatinParser.EOF, 0); }
		public SeccionImportacionesContext seccionImportaciones() {
			return getRuleContext(SeccionImportacionesContext.class,0);
		}
		public SeccionVariablesGlobalesContext seccionVariablesGlobales() {
			return getRuleContext(SeccionVariablesGlobalesContext.class,0);
		}
		public SeccionFuncionesContext seccionFunciones() {
			return getRuleContext(SeccionFuncionesContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_IMPORT) {
				{
				setState(102);
				seccionImportaciones();
				}
			}

			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_VARIABILES) {
				{
				setState(105);
				seccionVariablesGlobales();
				}
			}

			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_MUNERA) {
				{
				setState(108);
				seccionFunciones();
				}
			}

			setState(111);
			seccionPrincipal();
			setState(112);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionImportacionesContext extends ParserRuleContext {
		public List<ImportacionContext> importacion() {
			return getRuleContexts(ImportacionContext.class);
		}
		public ImportacionContext importacion(int i) {
			return getRuleContext(ImportacionContext.class,i);
		}
		public SeccionImportacionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionImportaciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionImportaciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionImportaciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitSeccionImportaciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeccionImportacionesContext seccionImportaciones() throws RecognitionException {
		SeccionImportacionesContext _localctx = new SeccionImportacionesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_seccionImportaciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				importacion();
				}
				}
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KW_IMPORT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportacionContext extends ParserRuleContext {
		public TerminalNode KW_IMPORT() { return getToken(pigLatinParser.KW_IMPORT, 0); }
		public RutaImportContext rutaImport() {
			return getRuleContext(RutaImportContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public ImportacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterImportacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitImportacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitImportacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportacionContext importacion() throws RecognitionException {
		ImportacionContext _localctx = new ImportacionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(KW_IMPORT);
			setState(120);
			rutaImport();
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(121);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RutaImportContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(pigLatinParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(pigLatinParser.ID, i);
		}
		public List<TerminalNode> DOT() { return getTokens(pigLatinParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(pigLatinParser.DOT, i);
		}
		public RutaImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rutaImport; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterRutaImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitRutaImport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitRutaImport(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RutaImportContext rutaImport() throws RecognitionException {
		RutaImportContext _localctx = new RutaImportContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_rutaImport);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(ID);
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125);
				match(DOT);
				setState(126);
				match(ID);
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DOT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionVariablesGlobalesContext extends ParserRuleContext {
		public TerminalNode KW_VARIABILES() { return getToken(pigLatinParser.KW_VARIABILES, 0); }
		public TerminalNode GT() { return getToken(pigLatinParser.GT, 0); }
		public List<DeclaracionGlobalContext> declaracionGlobal() {
			return getRuleContexts(DeclaracionGlobalContext.class);
		}
		public DeclaracionGlobalContext declaracionGlobal(int i) {
			return getRuleContext(DeclaracionGlobalContext.class,i);
		}
		public SeccionVariablesGlobalesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionVariablesGlobales; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionVariablesGlobales(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionVariablesGlobales(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitSeccionVariablesGlobales(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeccionVariablesGlobalesContext seccionVariablesGlobales() throws RecognitionException {
		SeccionVariablesGlobalesContext _localctx = new SeccionVariablesGlobalesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_seccionVariablesGlobales);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(KW_VARIABILES);
			setState(132);
			match(GT);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ESTO || _la==KW_SERIES) {
				{
				{
				setState(133);
				declaracionGlobal();
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionGlobalContext extends ParserRuleContext {
		public DeclaracionVariableContext declaracionVariable() {
			return getRuleContext(DeclaracionVariableContext.class,0);
		}
		public DeclaracionArregloContext declaracionArreglo() {
			return getRuleContext(DeclaracionArregloContext.class,0);
		}
		public DeclaracionGlobalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionGlobal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionGlobal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDeclaracionGlobal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionGlobalContext declaracionGlobal() throws RecognitionException {
		DeclaracionGlobalContext _localctx = new DeclaracionGlobalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_declaracionGlobal);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_ESTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				declaracionVariable();
				}
				break;
			case KW_SERIES:
				enterOuterAlt(_localctx, 2);
				{
				setState(140);
				declaracionArreglo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionFuncionesContext extends ParserRuleContext {
		public TerminalNode KW_MUNERA() { return getToken(pigLatinParser.KW_MUNERA, 0); }
		public TerminalNode GT() { return getToken(pigLatinParser.GT, 0); }
		public List<DefinicionFuncionContext> definicionFuncion() {
			return getRuleContexts(DefinicionFuncionContext.class);
		}
		public DefinicionFuncionContext definicionFuncion(int i) {
			return getRuleContext(DefinicionFuncionContext.class,i);
		}
		public SeccionFuncionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionFunciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionFunciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionFunciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitSeccionFunciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeccionFuncionesContext seccionFunciones() throws RecognitionException {
		SeccionFuncionesContext _localctx = new SeccionFuncionesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_seccionFunciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(KW_MUNERA);
			setState(144);
			match(GT);
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ACTIO || _la==KW_RATIO) {
				{
				{
				setState(145);
				definicionFuncion();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionPrincipalContext extends ParserRuleContext {
		public TerminalNode KW_MAIOR() { return getToken(pigLatinParser.KW_MAIOR, 0); }
		public TerminalNode GT() { return getToken(pigLatinParser.GT, 0); }
		public TerminalNode FINIS_PROGRAMA() { return getToken(pigLatinParser.FINIS_PROGRAMA, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public SeccionPrincipalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionPrincipal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionPrincipal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionPrincipal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitSeccionPrincipal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeccionPrincipalContext seccionPrincipal() throws RecognitionException {
		SeccionPrincipalContext _localctx = new SeccionPrincipalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_seccionPrincipal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(KW_MAIOR);
			setState(152);
			match(GT);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611791571710640896L) != 0)) {
				{
				{
				setState(153);
				instruccion();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159);
			match(FINIS_PROGRAMA);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(160);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionVariableContext extends ParserRuleContext {
		public TerminalNode KW_ESTO() { return getToken(pigLatinParser.KW_ESTO, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public InicializadorVariableContext inicializadorVariable() {
			return getRuleContext(InicializadorVariableContext.class,0);
		}
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public DeclaracionVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDeclaracionVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionVariableContext declaracionVariable() throws RecognitionException {
		DeclaracionVariableContext _localctx = new DeclaracionVariableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaracionVariable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(KW_ESTO);
			setState(164);
			match(ID);
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(165);
				match(COLON);
				}
			}

			setState(168);
			inicializadorVariable();
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(169);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InicializadorVariableContext extends ParserRuleContext {
		public TipoPrimitivoContext tipoPrimitivo() {
			return getRuleContext(TipoPrimitivoContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode KW_BOOL() { return getToken(pigLatinParser.KW_BOOL, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public LiteralStructContext literalStruct() {
			return getRuleContext(LiteralStructContext.class,0);
		}
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public InstanciacionObjetoContext instanciacionObjeto() {
			return getRuleContext(InstanciacionObjetoContext.class,0);
		}
		public ValorBooleanoContext valorBooleano() {
			return getRuleContext(ValorBooleanoContext.class,0);
		}
		public InicializadorVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inicializadorVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInicializadorVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInicializadorVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInicializadorVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InicializadorVariableContext inicializadorVariable() throws RecognitionException {
		InicializadorVariableContext _localctx = new InicializadorVariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_inicializadorVariable);
		try {
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				tipoPrimitivo();
				setState(174);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(173);
					expresion(0);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(KW_BOOL);
				setState(178);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(177);
					expresion(0);
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(180);
				match(ID);
				setState(181);
				literalStruct();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(182);
				expresion(0);
				setState(183);
				match(COLON);
				setState(184);
				tipo();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(186);
				instanciacionObjeto();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(187);
				valorBooleano();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(188);
				tipo();
				setState(191);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(189);
					expresion(0);
					}
					break;
				case 2:
					{
					setState(190);
					literalStruct();
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(193);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstanciacionObjetoContext extends ParserRuleContext {
		public TerminalNode KW_NOVUS() { return getToken(pigLatinParser.KW_NOVUS, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public ListaArgumentosContext listaArgumentos() {
			return getRuleContext(ListaArgumentosContext.class,0);
		}
		public InstanciacionObjetoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instanciacionObjeto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstanciacionObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstanciacionObjeto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstanciacionObjeto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstanciacionObjetoContext instanciacionObjeto() throws RecognitionException {
		InstanciacionObjetoContext _localctx = new InstanciacionObjetoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_instanciacionObjeto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(KW_NOVUS);
			setState(197);
			match(ID);
			setState(198);
			match(LPAREN);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8939645810891784196L) != 0)) {
				{
				setState(199);
				listaArgumentos();
				}
			}

			setState(202);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValorBooleanoContext extends ParserRuleContext {
		public TerminalNode KW_VERUM() { return getToken(pigLatinParser.KW_VERUM, 0); }
		public TerminalNode KW_FALSUS() { return getToken(pigLatinParser.KW_FALSUS, 0); }
		public ValorBooleanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valorBooleano; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterValorBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitValorBooleano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitValorBooleano(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValorBooleanoContext valorBooleano() throws RecognitionException {
		ValorBooleanoContext _localctx = new ValorBooleanoContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_valorBooleano);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !(_la==KW_VERUM || _la==KW_FALSUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionArregloContext extends ParserRuleContext {
		public TerminalNode KW_SERIES() { return getToken(pigLatinParser.KW_SERIES, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(pigLatinParser.LBRACKET, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(pigLatinParser.RBRACKET, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public TerminalNode LBRACE() { return getToken(pigLatinParser.LBRACE, 0); }
		public ListaValoresArregloContext listaValoresArreglo() {
			return getRuleContext(ListaValoresArregloContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(pigLatinParser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public DeclaracionArregloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionArreglo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionArreglo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionArreglo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDeclaracionArreglo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionArregloContext declaracionArreglo() throws RecognitionException {
		DeclaracionArregloContext _localctx = new DeclaracionArregloContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declaracionArreglo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(KW_SERIES);
			setState(207);
			match(ID);
			setState(208);
			match(LBRACKET);
			setState(209);
			expresion(0);
			setState(210);
			match(RBRACKET);
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(211);
				match(COLON);
				}
			}

			setState(214);
			tipo();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(215);
				match(LBRACE);
				setState(216);
				listaValoresArreglo();
				setState(217);
				match(RBRACE);
				}
			}

			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(221);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaValoresArregloContext extends ParserRuleContext {
		public List<ElementoArregloContext> elementoArreglo() {
			return getRuleContexts(ElementoArregloContext.class);
		}
		public ElementoArregloContext elementoArreglo(int i) {
			return getRuleContext(ElementoArregloContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(pigLatinParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(pigLatinParser.COMMA, i);
		}
		public ListaValoresArregloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaValoresArreglo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterListaValoresArreglo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitListaValoresArreglo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitListaValoresArreglo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaValoresArregloContext listaValoresArreglo() throws RecognitionException {
		ListaValoresArregloContext _localctx = new ListaValoresArregloContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_listaValoresArreglo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			elementoArreglo();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(225);
				match(COMMA);
				setState(226);
				elementoArreglo();
				}
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementoArregloContext extends ParserRuleContext {
		public LiteralStructContext literalStruct() {
			return getRuleContext(LiteralStructContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ElementoArregloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementoArreglo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterElementoArreglo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitElementoArreglo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitElementoArreglo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementoArregloContext elementoArreglo() throws RecognitionException {
		ElementoArregloContext _localctx = new ElementoArregloContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_elementoArreglo);
		try {
			setState(234);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(232);
				literalStruct();
				}
				break;
			case KW_NOVUS:
			case KW_VERUM:
			case KW_FALSUS:
			case KW_NULL:
			case KW_NON:
			case NOT:
			case MINUS:
			case LPAREN:
			case DECIMALIS_LIT:
			case NUMERUS_LIT:
			case TEXTUM_LIT:
			case LITTERA_LIT:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(233);
				expresion(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralStructContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(pigLatinParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(pigLatinParser.RBRACE, 0); }
		public List<ElementoStructContext> elementoStruct() {
			return getRuleContexts(ElementoStructContext.class);
		}
		public ElementoStructContext elementoStruct(int i) {
			return getRuleContext(ElementoStructContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(pigLatinParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(pigLatinParser.COMMA, i);
		}
		public LiteralStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLiteralStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLiteralStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitLiteralStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralStructContext literalStruct() throws RecognitionException {
		LiteralStructContext _localctx = new LiteralStructContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_literalStruct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(LBRACE);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8957660209401266180L) != 0)) {
				{
				setState(237);
				elementoStruct();
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(238);
					match(COMMA);
					setState(239);
					elementoStruct();
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(247);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementoStructContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public LiteralStructContext literalStruct() {
			return getRuleContext(LiteralStructContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ElementoStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementoStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterElementoStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitElementoStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitElementoStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementoStructContext elementoStruct() throws RecognitionException {
		ElementoStructContext _localctx = new ElementoStructContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_elementoStruct);
		try {
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(ID);
				setState(250);
				match(COLON);
				setState(253);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LBRACE:
					{
					setState(251);
					literalStruct();
					}
					break;
				case KW_NOVUS:
				case KW_VERUM:
				case KW_FALSUS:
				case KW_NULL:
				case KW_NON:
				case NOT:
				case MINUS:
				case LPAREN:
				case DECIMALIS_LIT:
				case NUMERUS_LIT:
				case TEXTUM_LIT:
				case LITTERA_LIT:
				case ID:
					{
					setState(252);
					expresion(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				literalStruct();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoPrimitivoContext extends ParserRuleContext {
		public TerminalNode KW_NUMERUS() { return getToken(pigLatinParser.KW_NUMERUS, 0); }
		public TerminalNode KW_TEXTUM() { return getToken(pigLatinParser.KW_TEXTUM, 0); }
		public TerminalNode KW_DECIMALIS() { return getToken(pigLatinParser.KW_DECIMALIS, 0); }
		public TerminalNode KW_LITTERA() { return getToken(pigLatinParser.KW_LITTERA, 0); }
		public TipoPrimitivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoPrimitivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterTipoPrimitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitTipoPrimitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitTipoPrimitivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoPrimitivoContext tipoPrimitivo() throws RecognitionException {
		TipoPrimitivoContext _localctx = new TipoPrimitivoContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_tipoPrimitivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15360L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoPrimitivoContext tipoPrimitivo() {
			return getRuleContext(TipoPrimitivoContext.class,0);
		}
		public TerminalNode KW_BOOL() { return getToken(pigLatinParser.KW_BOOL, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitTipo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitTipo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_tipo);
		try {
			setState(264);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_NUMERUS:
			case KW_TEXTUM:
			case KW_DECIMALIS:
			case KW_LITTERA:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				tipoPrimitivo();
				}
				break;
			case KW_BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(KW_BOOL);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(263);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinicionFuncionContext extends ParserRuleContext {
		public FuncionSinRetornoContext funcionSinRetorno() {
			return getRuleContext(FuncionSinRetornoContext.class,0);
		}
		public FuncionConRetornoContext funcionConRetorno() {
			return getRuleContext(FuncionConRetornoContext.class,0);
		}
		public DefinicionFuncionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definicionFuncion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDefinicionFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDefinicionFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDefinicionFuncion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinicionFuncionContext definicionFuncion() throws RecognitionException {
		DefinicionFuncionContext _localctx = new DefinicionFuncionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_definicionFuncion);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_ACTIO:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				funcionSinRetorno();
				}
				break;
			case KW_RATIO:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				funcionConRetorno();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncionSinRetornoContext extends ParserRuleContext {
		public TerminalNode KW_ACTIO() { return getToken(pigLatinParser.KW_ACTIO, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(pigLatinParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(pigLatinParser.RBRACE, 0); }
		public TerminalNode FINIS_BLOQUE() { return getToken(pigLatinParser.FINIS_BLOQUE, 0); }
		public ListaParametrosContext listaParametros() {
			return getRuleContext(ListaParametrosContext.class,0);
		}
		public SeccionVariablesLocalesContext seccionVariablesLocales() {
			return getRuleContext(SeccionVariablesLocalesContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public FuncionSinRetornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcionSinRetorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterFuncionSinRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitFuncionSinRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitFuncionSinRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncionSinRetornoContext funcionSinRetorno() throws RecognitionException {
		FuncionSinRetornoContext _localctx = new FuncionSinRetornoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_funcionSinRetorno);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(KW_ACTIO);
			setState(271);
			match(ID);
			setState(272);
			match(LPAREN);
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ESTO) {
				{
				setState(273);
				listaParametros();
				}
			}

			setState(276);
			match(RPAREN);
			setState(277);
			match(LBRACE);
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_VARIABILES) {
				{
				setState(278);
				seccionVariablesLocales();
				}
			}

			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611791571710640896L) != 0)) {
				{
				{
				setState(281);
				instruccion();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287);
			match(RBRACE);
			setState(288);
			match(FINIS_BLOQUE);
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(289);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncionConRetornoContext extends ParserRuleContext {
		public TerminalNode KW_RATIO() { return getToken(pigLatinParser.KW_RATIO, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(pigLatinParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(pigLatinParser.RBRACE, 0); }
		public TerminalNode FINIS_BLOQUE() { return getToken(pigLatinParser.FINIS_BLOQUE, 0); }
		public ListaParametrosContext listaParametros() {
			return getRuleContext(ListaParametrosContext.class,0);
		}
		public SeccionVariablesLocalesContext seccionVariablesLocales() {
			return getRuleContext(SeccionVariablesLocalesContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public FuncionConRetornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcionConRetorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterFuncionConRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitFuncionConRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitFuncionConRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncionConRetornoContext funcionConRetorno() throws RecognitionException {
		FuncionConRetornoContext _localctx = new FuncionConRetornoContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_funcionConRetorno);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(KW_RATIO);
			setState(293);
			tipo();
			setState(294);
			match(ID);
			setState(295);
			match(LPAREN);
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ESTO) {
				{
				setState(296);
				listaParametros();
				}
			}

			setState(299);
			match(RPAREN);
			setState(300);
			match(LBRACE);
			setState(302);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_VARIABILES) {
				{
				setState(301);
				seccionVariablesLocales();
				}
			}

			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611791571710640896L) != 0)) {
				{
				{
				setState(304);
				instruccion();
				}
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(310);
			match(RBRACE);
			setState(311);
			match(FINIS_BLOQUE);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(312);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaParametrosContext extends ParserRuleContext {
		public List<ParametroContext> parametro() {
			return getRuleContexts(ParametroContext.class);
		}
		public ParametroContext parametro(int i) {
			return getRuleContext(ParametroContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(pigLatinParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(pigLatinParser.COMMA, i);
		}
		public ListaParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaParametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterListaParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitListaParametros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitListaParametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaParametrosContext listaParametros() throws RecognitionException {
		ListaParametrosContext _localctx = new ListaParametrosContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_listaParametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			parametro();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(316);
				match(COMMA);
				setState(317);
				parametro();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametroContext extends ParserRuleContext {
		public TerminalNode KW_ESTO() { return getToken(pigLatinParser.KW_ESTO, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterParametro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitParametro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitParametro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_parametro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(KW_ESTO);
			setState(324);
			match(ID);
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(325);
				match(COLON);
				}
			}

			setState(328);
			tipo();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SeccionVariablesLocalesContext extends ParserRuleContext {
		public TerminalNode KW_VARIABILES() { return getToken(pigLatinParser.KW_VARIABILES, 0); }
		public TerminalNode LBRACKET() { return getToken(pigLatinParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(pigLatinParser.RBRACKET, 0); }
		public List<DeclaracionLocalContext> declaracionLocal() {
			return getRuleContexts(DeclaracionLocalContext.class);
		}
		public DeclaracionLocalContext declaracionLocal(int i) {
			return getRuleContext(DeclaracionLocalContext.class,i);
		}
		public SeccionVariablesLocalesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccionVariablesLocales; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterSeccionVariablesLocales(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitSeccionVariablesLocales(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitSeccionVariablesLocales(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeccionVariablesLocalesContext seccionVariablesLocales() throws RecognitionException {
		SeccionVariablesLocalesContext _localctx = new SeccionVariablesLocalesContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_seccionVariablesLocales);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(KW_VARIABILES);
			setState(331);
			match(LBRACKET);
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ESTO || _la==KW_SERIES) {
				{
				{
				setState(332);
				declaracionLocal();
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(338);
			match(RBRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionLocalContext extends ParserRuleContext {
		public DeclaracionVariableContext declaracionVariable() {
			return getRuleContext(DeclaracionVariableContext.class,0);
		}
		public DeclaracionArregloContext declaracionArreglo() {
			return getRuleContext(DeclaracionArregloContext.class,0);
		}
		public DeclaracionLocalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionLocal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionLocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionLocal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDeclaracionLocal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionLocalContext declaracionLocal() throws RecognitionException {
		DeclaracionLocalContext _localctx = new DeclaracionLocalContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_declaracionLocal);
		try {
			setState(342);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_ESTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				declaracionVariable();
				}
				break;
			case KW_SERIES:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				declaracionArreglo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionContext extends ParserRuleContext {
		public DeclaracionVariableContext declaracionVariable() {
			return getRuleContext(DeclaracionVariableContext.class,0);
		}
		public DeclaracionArregloContext declaracionArreglo() {
			return getRuleContext(DeclaracionArregloContext.class,0);
		}
		public InstruccionImprimirContext instruccionImprimir() {
			return getRuleContext(InstruccionImprimirContext.class,0);
		}
		public InstruccionLeerContext instruccionLeer() {
			return getRuleContext(InstruccionLeerContext.class,0);
		}
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public InstruccionIncrementoDecrementoContext instruccionIncrementoDecremento() {
			return getRuleContext(InstruccionIncrementoDecrementoContext.class,0);
		}
		public InstruccionSiContext instruccionSi() {
			return getRuleContext(InstruccionSiContext.class,0);
		}
		public InstruccionDumContext instruccionDum() {
			return getRuleContext(InstruccionDumContext.class,0);
		}
		public InstruccionFacereContext instruccionFacere() {
			return getRuleContext(InstruccionFacereContext.class,0);
		}
		public InstruccionPerContext instruccionPer() {
			return getRuleContext(InstruccionPerContext.class,0);
		}
		public InstruccionInterrumpeContext instruccionInterrumpe() {
			return getRuleContext(InstruccionInterrumpeContext.class,0);
		}
		public InstruccionPergeContext instruccionPerge() {
			return getRuleContext(InstruccionPergeContext.class,0);
		}
		public InstruccionReddereContext instruccionReddere() {
			return getRuleContext(InstruccionReddereContext.class,0);
		}
		public InstruccionLlamadaMetodoContext instruccionLlamadaMetodo() {
			return getRuleContext(InstruccionLlamadaMetodoContext.class,0);
		}
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_instruccion);
		try {
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				declaracionVariable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(345);
				declaracionArreglo();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(346);
				instruccionImprimir();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				instruccionLeer();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(348);
				asignacion();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(349);
				instruccionIncrementoDecremento();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(350);
				instruccionSi();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(351);
				instruccionDum();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(352);
				instruccionFacere();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(353);
				instruccionPer();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(354);
				instruccionInterrumpe();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(355);
				instruccionPerge();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(356);
				instruccionReddere();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(357);
				instruccionLlamadaMetodo();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionContext extends ParserRuleContext {
		public DestinoContext destino() {
			return getRuleContext(DestinoContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(pigLatinParser.ASSIGN, 0); }
		public LiteralStructContext literalStruct() {
			return getRuleContext(LiteralStructContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitAsignacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_asignacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			destino();
			setState(361);
			match(ASSIGN);
			setState(364);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(362);
				literalStruct();
				}
				break;
			case KW_NOVUS:
			case KW_VERUM:
			case KW_FALSUS:
			case KW_NULL:
			case KW_NON:
			case NOT:
			case MINUS:
			case LPAREN:
			case DECIMALIS_LIT:
			case NUMERUS_LIT:
			case TEXTUM_LIT:
			case LITTERA_LIT:
			case ID:
				{
				setState(363);
				expresion(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(366);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DestinoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public List<AccesoMiembroContext> accesoMiembro() {
			return getRuleContexts(AccesoMiembroContext.class);
		}
		public AccesoMiembroContext accesoMiembro(int i) {
			return getRuleContext(AccesoMiembroContext.class,i);
		}
		public DestinoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destino; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDestino(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDestino(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDestino(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DestinoContext destino() throws RecognitionException {
		DestinoContext _localctx = new DestinoContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_destino);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(ID);
			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT || _la==LBRACKET) {
				{
				{
				setState(370);
				accesoMiembro();
				}
				}
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccesoMiembroContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(pigLatinParser.DOT, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(pigLatinParser.LBRACKET, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(pigLatinParser.RBRACKET, 0); }
		public AccesoMiembroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accesoMiembro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterAccesoMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitAccesoMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitAccesoMiembro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccesoMiembroContext accesoMiembro() throws RecognitionException {
		AccesoMiembroContext _localctx = new AccesoMiembroContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_accesoMiembro);
		try {
			setState(382);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(DOT);
				setState(377);
				match(ID);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				match(LBRACKET);
				setState(379);
				expresion(0);
				setState(380);
				match(RBRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionLlamadaMetodoContext extends ParserRuleContext {
		public LlamadaMetodoContext llamadaMetodo() {
			return getRuleContext(LlamadaMetodoContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionLlamadaMetodoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionLlamadaMetodo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionLlamadaMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionLlamadaMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionLlamadaMetodo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionLlamadaMetodoContext instruccionLlamadaMetodo() throws RecognitionException {
		InstruccionLlamadaMetodoContext _localctx = new InstruccionLlamadaMetodoContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_instruccionLlamadaMetodo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			llamadaMetodo();
			setState(386);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(385);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LlamadaMetodoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public List<AccesoPostfijoContext> accesoPostfijo() {
			return getRuleContexts(AccesoPostfijoContext.class);
		}
		public AccesoPostfijoContext accesoPostfijo(int i) {
			return getRuleContext(AccesoPostfijoContext.class,i);
		}
		public LlamadaMetodoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llamadaMetodo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLlamadaMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLlamadaMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitLlamadaMetodo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlamadaMetodoContext llamadaMetodo() throws RecognitionException {
		LlamadaMetodoContext _localctx = new LlamadaMetodoContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_llamadaMetodo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(ID);
			setState(390); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(389);
				accesoPostfijo();
				}
				}
				setState(392); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 76842668642009088L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccesoPostfijoContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(pigLatinParser.DOT, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TerminalNode LBRACKET() { return getToken(pigLatinParser.LBRACKET, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(pigLatinParser.RBRACKET, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public ListaArgumentosContext listaArgumentos() {
			return getRuleContext(ListaArgumentosContext.class,0);
		}
		public AccesoPostfijoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accesoPostfijo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterAccesoPostfijo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitAccesoPostfijo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitAccesoPostfijo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccesoPostfijoContext accesoPostfijo() throws RecognitionException {
		AccesoPostfijoContext _localctx = new AccesoPostfijoContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_accesoPostfijo);
		int _la;
		try {
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(394);
				match(DOT);
				setState(395);
				match(ID);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				match(LBRACKET);
				setState(397);
				expresion(0);
				setState(398);
				match(RBRACKET);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				match(LPAREN);
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8939645810891784196L) != 0)) {
					{
					setState(401);
					listaArgumentos();
					}
				}

				setState(404);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListaArgumentosContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(pigLatinParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(pigLatinParser.COMMA, i);
		}
		public ListaArgumentosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listaArgumentos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterListaArgumentos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitListaArgumentos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitListaArgumentos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListaArgumentosContext listaArgumentos() throws RecognitionException {
		ListaArgumentosContext _localctx = new ListaArgumentosContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_listaArgumentos);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			expresion(0);
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(408);
				match(COMMA);
				setState(409);
				expresion(0);
				}
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionSiContext extends ParserRuleContext {
		public TerminalNode KW_SI() { return getToken(pigLatinParser.KW_SI, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(pigLatinParser.FINIS_BLOQUE, 0); }
		public List<RamaAliterCondicionalContext> ramaAliterCondicional() {
			return getRuleContexts(RamaAliterCondicionalContext.class);
		}
		public RamaAliterCondicionalContext ramaAliterCondicional(int i) {
			return getRuleContext(RamaAliterCondicionalContext.class,i);
		}
		public RamaAliterFinalContext ramaAliterFinal() {
			return getRuleContext(RamaAliterFinalContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionSiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionSi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionSi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionSi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionSi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionSiContext instruccionSi() throws RecognitionException {
		InstruccionSiContext _localctx = new InstruccionSiContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_instruccionSi);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(KW_SI);
			setState(416);
			match(LPAREN);
			setState(417);
			expresion(0);
			setState(418);
			match(RPAREN);
			setState(419);
			bloqueInstrucciones();
			setState(423);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(420);
					ramaAliterCondicional();
					}
					} 
				}
				setState(425);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(427);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ALITER) {
				{
				setState(426);
				ramaAliterFinal();
				}
			}

			setState(429);
			match(FINIS_BLOQUE);
			setState(431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(430);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RamaAliterCondicionalContext extends ParserRuleContext {
		public TerminalNode KW_ALITER() { return getToken(pigLatinParser.KW_ALITER, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public RamaAliterCondicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ramaAliterCondicional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterRamaAliterCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitRamaAliterCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitRamaAliterCondicional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RamaAliterCondicionalContext ramaAliterCondicional() throws RecognitionException {
		RamaAliterCondicionalContext _localctx = new RamaAliterCondicionalContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ramaAliterCondicional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(KW_ALITER);
			setState(434);
			match(LPAREN);
			setState(435);
			expresion(0);
			setState(436);
			match(RPAREN);
			setState(437);
			bloqueInstrucciones();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RamaAliterFinalContext extends ParserRuleContext {
		public TerminalNode KW_ALITER() { return getToken(pigLatinParser.KW_ALITER, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public RamaAliterFinalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ramaAliterFinal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterRamaAliterFinal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitRamaAliterFinal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitRamaAliterFinal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RamaAliterFinalContext ramaAliterFinal() throws RecognitionException {
		RamaAliterFinalContext _localctx = new RamaAliterFinalContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_ramaAliterFinal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(KW_ALITER);
			setState(440);
			bloqueInstrucciones();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BloqueInstruccionesContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(pigLatinParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(pigLatinParser.RBRACE, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public BloqueInstruccionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloqueInstrucciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterBloqueInstrucciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitBloqueInstrucciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitBloqueInstrucciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueInstruccionesContext bloqueInstrucciones() throws RecognitionException {
		BloqueInstruccionesContext _localctx = new BloqueInstruccionesContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_bloqueInstrucciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			match(LBRACE);
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4611791571710640896L) != 0)) {
				{
				{
				setState(443);
				instruccion();
				}
				}
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(449);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionDumContext extends ParserRuleContext {
		public TerminalNode KW_DUM() { return getToken(pigLatinParser.KW_DUM, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(pigLatinParser.FINIS_BLOQUE, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionDumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionDum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionDum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionDum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionDum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionDumContext instruccionDum() throws RecognitionException {
		InstruccionDumContext _localctx = new InstruccionDumContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_instruccionDum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(KW_DUM);
			setState(452);
			match(LPAREN);
			setState(453);
			expresion(0);
			setState(454);
			match(RPAREN);
			setState(455);
			bloqueInstrucciones();
			setState(456);
			match(FINIS_BLOQUE);
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(457);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionFacereContext extends ParserRuleContext {
		public TerminalNode KW_FACERE() { return getToken(pigLatinParser.KW_FACERE, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public TerminalNode KW_DUM() { return getToken(pigLatinParser.KW_DUM, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionFacereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionFacere; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionFacere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionFacere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionFacere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionFacereContext instruccionFacere() throws RecognitionException {
		InstruccionFacereContext _localctx = new InstruccionFacereContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_instruccionFacere);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			match(KW_FACERE);
			setState(461);
			bloqueInstrucciones();
			setState(462);
			match(KW_DUM);
			setState(463);
			match(LPAREN);
			setState(464);
			expresion(0);
			setState(465);
			match(RPAREN);
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(466);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionPerContext extends ParserRuleContext {
		public TerminalNode KW_PER() { return getToken(pigLatinParser.KW_PER, 0); }
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public DeclaracionCicloForContext declaracionCicloFor() {
			return getRuleContext(DeclaracionCicloForContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(pigLatinParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(pigLatinParser.SEMI, i);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ActualizacionCicloContext actualizacionCiclo() {
			return getRuleContext(ActualizacionCicloContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public BloqueInstruccionesContext bloqueInstrucciones() {
			return getRuleContext(BloqueInstruccionesContext.class,0);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(pigLatinParser.FINIS_BLOQUE, 0); }
		public InstruccionPerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionPer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionPer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionPer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionPer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionPerContext instruccionPer() throws RecognitionException {
		InstruccionPerContext _localctx = new InstruccionPerContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_instruccionPer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			match(KW_PER);
			setState(470);
			match(LPAREN);
			setState(471);
			declaracionCicloFor();
			setState(472);
			match(SEMI);
			setState(473);
			expresion(0);
			setState(474);
			match(SEMI);
			setState(475);
			actualizacionCiclo();
			setState(476);
			match(RPAREN);
			setState(477);
			bloqueInstrucciones();
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINIS_BLOQUE) {
				{
				setState(478);
				match(FINIS_BLOQUE);
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(479);
					match(SEMI);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaracionCicloForContext extends ParserRuleContext {
		public TerminalNode KW_ESTO() { return getToken(pigLatinParser.KW_ESTO, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode COLON() { return getToken(pigLatinParser.COLON, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclaracionCicloForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracionCicloFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterDeclaracionCicloFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitDeclaracionCicloFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitDeclaracionCicloFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracionCicloForContext declaracionCicloFor() throws RecognitionException {
		DeclaracionCicloForContext _localctx = new DeclaracionCicloForContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_declaracionCicloFor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(484);
			match(KW_ESTO);
			setState(485);
			match(ID);
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(486);
				match(COLON);
				}
			}

			setState(489);
			tipo();
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8939645810891784196L) != 0)) {
				{
				setState(490);
				expresion(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActualizacionCicloContext extends ParserRuleContext {
		public DestinoContext destino() {
			return getRuleContext(DestinoContext.class,0);
		}
		public TerminalNode INC() { return getToken(pigLatinParser.INC, 0); }
		public TerminalNode DEC() { return getToken(pigLatinParser.DEC, 0); }
		public TerminalNode ASSIGN() { return getToken(pigLatinParser.ASSIGN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ActualizacionCicloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualizacionCiclo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterActualizacionCiclo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitActualizacionCiclo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitActualizacionCiclo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualizacionCicloContext actualizacionCiclo() throws RecognitionException {
		ActualizacionCicloContext _localctx = new ActualizacionCicloContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_actualizacionCiclo);
		int _la;
		try {
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(493);
				destino();
				setState(494);
				_la = _input.LA(1);
				if ( !(_la==INC || _la==DEC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(496);
				destino();
				setState(497);
				match(ASSIGN);
				setState(498);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionInterrumpeContext extends ParserRuleContext {
		public TerminalNode KW_INTERRUMPE() { return getToken(pigLatinParser.KW_INTERRUMPE, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionInterrumpeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionInterrumpe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionInterrumpe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionInterrumpe(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionInterrumpe(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionInterrumpeContext instruccionInterrumpe() throws RecognitionException {
		InstruccionInterrumpeContext _localctx = new InstruccionInterrumpeContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_instruccionInterrumpe);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			match(KW_INTERRUMPE);
			setState(504);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(503);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionPergeContext extends ParserRuleContext {
		public TerminalNode KW_PERGE() { return getToken(pigLatinParser.KW_PERGE, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionPergeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionPerge; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionPerge(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionPerge(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionPerge(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionPergeContext instruccionPerge() throws RecognitionException {
		InstruccionPergeContext _localctx = new InstruccionPergeContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_instruccionPerge);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
			match(KW_PERGE);
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(507);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionReddereContext extends ParserRuleContext {
		public TerminalNode KW_REDDERE() { return getToken(pigLatinParser.KW_REDDERE, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionReddereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionReddere; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionReddere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionReddere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionReddere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionReddereContext instruccionReddere() throws RecognitionException {
		InstruccionReddereContext _localctx = new InstruccionReddereContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_instruccionReddere);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			match(KW_REDDERE);
			setState(512);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(511);
				expresion(0);
				}
				break;
			}
			setState(515);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(514);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionImprimirContext extends ParserRuleContext {
		public List<TerminalNode> SHIFT_OUT() { return getTokens(pigLatinParser.SHIFT_OUT); }
		public TerminalNode SHIFT_OUT(int i) {
			return getToken(pigLatinParser.SHIFT_OUT, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionImprimirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionImprimir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionImprimir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionImprimir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionImprimir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionImprimirContext instruccionImprimir() throws RecognitionException {
		InstruccionImprimirContext _localctx = new InstruccionImprimirContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_instruccionImprimir);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(SHIFT_OUT);
			setState(518);
			expresion(0);
			setState(523);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(519);
					match(SHIFT_OUT);
					setState(520);
					expresion(0);
					}
					} 
				}
				setState(525);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
			}
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(526);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionLeerContext extends ParserRuleContext {
		public TerminalNode SHIFT_IN() { return getToken(pigLatinParser.SHIFT_IN, 0); }
		public DestinoContext destino() {
			return getRuleContext(DestinoContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionLeerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionLeer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionLeer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionLeer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionLeer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionLeerContext instruccionLeer() throws RecognitionException {
		InstruccionLeerContext _localctx = new InstruccionLeerContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_instruccionLeer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(529);
				destino();
				}
			}

			setState(532);
			match(SHIFT_IN);
			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(533);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionIncrementoDecrementoContext extends ParserRuleContext {
		public DestinoContext destino() {
			return getRuleContext(DestinoContext.class,0);
		}
		public TerminalNode INC() { return getToken(pigLatinParser.INC, 0); }
		public TerminalNode DEC() { return getToken(pigLatinParser.DEC, 0); }
		public TerminalNode SEMI() { return getToken(pigLatinParser.SEMI, 0); }
		public InstruccionIncrementoDecrementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccionIncrementoDecremento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterInstruccionIncrementoDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitInstruccionIncrementoDecremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitInstruccionIncrementoDecremento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionIncrementoDecrementoContext instruccionIncrementoDecremento() throws RecognitionException {
		InstruccionIncrementoDecrementoContext _localctx = new InstruccionIncrementoDecrementoContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_instruccionIncrementoDecremento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536);
			destino();
			setState(537);
			_la = _input.LA(1);
			if ( !(_la==INC || _la==DEC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(539);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(538);
				match(SEMI);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	 
		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprInstanciacionNovusContext extends ExpresionContext {
		public InstanciacionObjetoContext instanciacionObjeto() {
			return getRuleContext(InstanciacionObjetoContext.class,0);
		}
		public ExprInstanciacionNovusContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprInstanciacionNovus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprInstanciacionNovus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprInstanciacionNovus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaMetodoContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public ListaArgumentosContext listaArgumentos() {
			return getRuleContext(ListaArgumentosContext.class,0);
		}
		public ExprLlamadaMetodoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprLlamadaMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprLlamadaMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprLlamadaMetodo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprRelacionalContext extends ExpresionContext {
		public Token op;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode LE() { return getToken(pigLatinParser.LE, 0); }
		public TerminalNode GE() { return getToken(pigLatinParser.GE, 0); }
		public TerminalNode LT() { return getToken(pigLatinParser.LT, 0); }
		public TerminalNode GT() { return getToken(pigLatinParser.GT, 0); }
		public ExprRelacionalContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprRelacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprRelacional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIgualdadContext extends ExpresionContext {
		public Token op;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode EQ() { return getToken(pigLatinParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(pigLatinParser.NEQ, 0); }
		public TerminalNode ASSIGN() { return getToken(pigLatinParser.ASSIGN, 0); }
		public ExprIgualdadContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprIgualdad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprIgualdad(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprIgualdad(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIdentificadorContext extends ExpresionContext {
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public ExprIdentificadorContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprIdentificador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprIdentificador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprIdentificador(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNegacionLogicaContext extends ExpresionContext {
		public TerminalNode KW_NON() { return getToken(pigLatinParser.KW_NON, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegacionLogicaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprNegacionLogica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprNegacionLogica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprNegacionLogica(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAditivaContext extends ExpresionContext {
		public Token op;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(pigLatinParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(pigLatinParser.MINUS, 0); }
		public ExprAditivaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprAditiva(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprAditiva(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprAditiva(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesisContext extends ExpresionContext {
		public TerminalNode LPAREN() { return getToken(pigLatinParser.LPAREN, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(pigLatinParser.RPAREN, 0); }
		public ExprParentesisContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprParentesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprParentesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprParentesis(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAndLogicoContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode AND() { return getToken(pigLatinParser.AND, 0); }
		public ExprAndLogicoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprAndLogico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprAndLogico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprAndLogico(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprIndexacionContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode LBRACKET() { return getToken(pigLatinParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(pigLatinParser.RBRACKET, 0); }
		public ExprIndexacionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprIndexacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprIndexacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprIndexacion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprOrLogicoContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode OR() { return getToken(pigLatinParser.OR, 0); }
		public ExprOrLogicoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprOrLogico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprOrLogico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprOrLogico(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLiteralContext extends ExpresionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExprLiteralContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoMiembroContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(pigLatinParser.DOT, 0); }
		public TerminalNode ID() { return getToken(pigLatinParser.ID, 0); }
		public ExprAccesoMiembroContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprAccesoMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprAccesoMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprAccesoMiembro(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultiplicativaContext extends ExpresionContext {
		public Token op;
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode STAR() { return getToken(pigLatinParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(pigLatinParser.SLASH, 0); }
		public TerminalNode MOD() { return getToken(pigLatinParser.MOD, 0); }
		public ExprMultiplicativaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprMultiplicativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprMultiplicativa(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprMultiplicativa(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNotSimboloContext extends ExpresionContext {
		public TerminalNode NOT() { return getToken(pigLatinParser.NOT, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNotSimboloContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprNotSimbolo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprNotSimbolo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprNotSimbolo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostfijaContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode INC() { return getToken(pigLatinParser.INC, 0); }
		public TerminalNode DEC() { return getToken(pigLatinParser.DEC, 0); }
		public ExprPostfijaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprPostfija(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprPostfija(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprPostfija(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMenosUnarioContext extends ExpresionContext {
		public TerminalNode MINUS() { return getToken(pigLatinParser.MINUS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprMenosUnarioContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterExprMenosUnario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitExprMenosUnario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitExprMenosUnario(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 98;
		enterRecursionRule(_localctx, 98, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(555);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case KW_NOVUS:
				{
				_localctx = new ExprInstanciacionNovusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(542);
				instanciacionObjeto();
				}
				break;
			case KW_NON:
				{
				_localctx = new ExprNegacionLogicaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(543);
				match(KW_NON);
				setState(544);
				expresion(12);
				}
				break;
			case NOT:
				{
				_localctx = new ExprNotSimboloContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(545);
				match(NOT);
				setState(546);
				expresion(11);
				}
				break;
			case MINUS:
				{
				_localctx = new ExprMenosUnarioContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(547);
				match(MINUS);
				setState(548);
				expresion(10);
				}
				break;
			case LPAREN:
				{
				_localctx = new ExprParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(549);
				match(LPAREN);
				setState(550);
				expresion(0);
				setState(551);
				match(RPAREN);
				}
				break;
			case ID:
				{
				_localctx = new ExprIdentificadorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(553);
				match(ID);
				}
				break;
			case KW_VERUM:
			case KW_FALSUS:
			case KW_NULL:
			case DECIMALIS_LIT:
			case NUMERUS_LIT:
			case TEXTUM_LIT:
			case LITTERA_LIT:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(554);
				literal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(593);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(591);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultiplicativaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(557);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(558);
						((ExprMultiplicativaContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7696581394432L) != 0)) ) {
							((ExprMultiplicativaContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(559);
						expresion(10);
						}
						break;
					case 2:
						{
						_localctx = new ExprAditivaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(560);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(561);
						((ExprAditivaContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((ExprAditivaContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(562);
						expresion(9);
						}
						break;
					case 3:
						{
						_localctx = new ExprRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(563);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(564);
						((ExprRelacionalContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 54760833024L) != 0)) ) {
							((ExprRelacionalContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(565);
						expresion(8);
						}
						break;
					case 4:
						{
						_localctx = new ExprIgualdadContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(566);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(567);
						((ExprIgualdadContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 140750373257216L) != 0)) ) {
							((ExprIgualdadContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(568);
						expresion(7);
						}
						break;
					case 5:
						{
						_localctx = new ExprAndLogicoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(569);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(570);
						match(AND);
						setState(571);
						expresion(6);
						}
						break;
					case 6:
						{
						_localctx = new ExprOrLogicoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(572);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(573);
						match(OR);
						setState(574);
						expresion(5);
						}
						break;
					case 7:
						{
						_localctx = new ExprPostfijaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(575);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(576);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 8:
						{
						_localctx = new ExprAccesoMiembroContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(577);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(578);
						match(DOT);
						setState(579);
						match(ID);
						}
						break;
					case 9:
						{
						_localctx = new ExprIndexacionContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(580);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(581);
						match(LBRACKET);
						setState(582);
						expresion(0);
						setState(583);
						match(RBRACKET);
						}
						break;
					case 10:
						{
						_localctx = new ExprLlamadaMetodoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(585);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(586);
						match(LPAREN);
						setState(588);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8939645810891784196L) != 0)) {
							{
							setState(587);
							listaArgumentos();
							}
						}

						setState(590);
						match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(595);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMERUS_LIT() { return getToken(pigLatinParser.NUMERUS_LIT, 0); }
		public TerminalNode DECIMALIS_LIT() { return getToken(pigLatinParser.DECIMALIS_LIT, 0); }
		public TerminalNode TEXTUM_LIT() { return getToken(pigLatinParser.TEXTUM_LIT, 0); }
		public TerminalNode LITTERA_LIT() { return getToken(pigLatinParser.LITTERA_LIT, 0); }
		public TerminalNode KW_VERUM() { return getToken(pigLatinParser.KW_VERUM, 0); }
		public TerminalNode KW_FALSUS() { return getToken(pigLatinParser.KW_FALSUS, 0); }
		public TerminalNode KW_NULL() { return getToken(pigLatinParser.KW_NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pigLatinListener ) ((pigLatinListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof pigLatinVisitor ) return ((pigLatinVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4323455642275905536L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 49:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 17);
		case 7:
			return precpred(_ctx, 16);
		case 8:
			return precpred(_ctx, 15);
		case 9:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001A\u0257\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u0001\u0000\u0003\u0000h\b\u0000\u0001\u0000\u0003\u0000k\b\u0000"+
		"\u0001\u0000\u0003\u0000n\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0004\u0001t\b\u0001\u000b\u0001\f\u0001u\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002{\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0004\u0003\u0080\b\u0003\u000b\u0003\f\u0003\u0081\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004\u0087\b\u0004\n\u0004\f\u0004\u008a"+
		"\t\u0004\u0001\u0005\u0001\u0005\u0003\u0005\u008e\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006\u0093\b\u0006\n\u0006\f\u0006\u0096"+
		"\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u009b\b\u0007"+
		"\n\u0007\f\u0007\u009e\t\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a2"+
		"\b\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u00a7\b\b\u0001\b\u0001\b\u0003"+
		"\b\u00ab\b\b\u0001\t\u0001\t\u0003\t\u00af\b\t\u0001\t\u0001\t\u0003\t"+
		"\u00b3\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00c0\b\t\u0001\t\u0003\t\u00c3\b\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c9\b\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00d5\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00dc\b\f"+
		"\u0001\f\u0003\f\u00df\b\f\u0001\r\u0001\r\u0001\r\u0005\r\u00e4\b\r\n"+
		"\r\f\r\u00e7\t\r\u0001\u000e\u0001\u000e\u0003\u000e\u00eb\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00f1\b\u000f\n"+
		"\u000f\f\u000f\u00f4\t\u000f\u0003\u000f\u00f6\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00fe"+
		"\b\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0102\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0109\b\u0012"+
		"\u0001\u0013\u0001\u0013\u0003\u0013\u010d\b\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0113\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u0118\b\u0014\u0001\u0014\u0005\u0014\u011b\b"+
		"\u0014\n\u0014\f\u0014\u011e\t\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u0123\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u012a\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u012f\b\u0015\u0001\u0015\u0005\u0015\u0132\b\u0015\n\u0015"+
		"\f\u0015\u0135\t\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u013a\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0005\u0016\u013f\b"+
		"\u0016\n\u0016\f\u0016\u0142\t\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0003\u0017\u0147\b\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0005\u0018\u014e\b\u0018\n\u0018\f\u0018\u0151\t\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u0157\b\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0003\u001a\u0167\b\u001a\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u016d\b\u001b\u0001\u001b\u0003\u001b\u0170"+
		"\b\u001b\u0001\u001c\u0001\u001c\u0005\u001c\u0174\b\u001c\n\u001c\f\u001c"+
		"\u0177\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0003\u001d\u017f\b\u001d\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0183\b\u001e\u0001\u001f\u0001\u001f\u0004\u001f\u0187\b\u001f\u000b"+
		"\u001f\f\u001f\u0188\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 "+
		"\u0001 \u0003 \u0193\b \u0001 \u0003 \u0196\b \u0001!\u0001!\u0001!\u0005"+
		"!\u019b\b!\n!\f!\u019e\t!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0005\"\u01a6\b\"\n\"\f\"\u01a9\t\"\u0001\"\u0003\"\u01ac\b\"\u0001"+
		"\"\u0001\"\u0003\"\u01b0\b\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001$\u0001$\u0001$\u0001%\u0001%\u0005%\u01bd\b%\n%\f%\u01c0\t%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u01cb"+
		"\b&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u01d4"+
		"\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0003(\u01e1\b(\u0003(\u01e3\b(\u0001)\u0001)\u0001)\u0003)\u01e8"+
		"\b)\u0001)\u0001)\u0003)\u01ec\b)\u0001*\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0003*\u01f5\b*\u0001+\u0001+\u0003+\u01f9\b+\u0001,\u0001,\u0003"+
		",\u01fd\b,\u0001-\u0001-\u0003-\u0201\b-\u0001-\u0003-\u0204\b-\u0001"+
		".\u0001.\u0001.\u0001.\u0005.\u020a\b.\n.\f.\u020d\t.\u0001.\u0003.\u0210"+
		"\b.\u0001/\u0003/\u0213\b/\u0001/\u0001/\u0003/\u0217\b/\u00010\u0001"+
		"0\u00010\u00030\u021c\b0\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00031\u022c\b1\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00031\u024d\b1\u00011\u00051\u0250\b1\n1\f1\u0253\t1\u00012\u00012"+
		"\u00012\u0000\u0001b3\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\"+
		"^`bd\u0000\b\u0001\u0000\u000f\u0010\u0001\u0000\n\r\u0001\u0000+,\u0001"+
		"\u0000(*\u0001\u0000&\'\u0002\u0000\u001e\u001f\"#\u0002\u0000 !//\u0002"+
		"\u0000\u000f\u0011:=\u0291\u0000g\u0001\u0000\u0000\u0000\u0002s\u0001"+
		"\u0000\u0000\u0000\u0004w\u0001\u0000\u0000\u0000\u0006|\u0001\u0000\u0000"+
		"\u0000\b\u0083\u0001\u0000\u0000\u0000\n\u008d\u0001\u0000\u0000\u0000"+
		"\f\u008f\u0001\u0000\u0000\u0000\u000e\u0097\u0001\u0000\u0000\u0000\u0010"+
		"\u00a3\u0001\u0000\u0000\u0000\u0012\u00c2\u0001\u0000\u0000\u0000\u0014"+
		"\u00c4\u0001\u0000\u0000\u0000\u0016\u00cc\u0001\u0000\u0000\u0000\u0018"+
		"\u00ce\u0001\u0000\u0000\u0000\u001a\u00e0\u0001\u0000\u0000\u0000\u001c"+
		"\u00ea\u0001\u0000\u0000\u0000\u001e\u00ec\u0001\u0000\u0000\u0000 \u0101"+
		"\u0001\u0000\u0000\u0000\"\u0103\u0001\u0000\u0000\u0000$\u0108\u0001"+
		"\u0000\u0000\u0000&\u010c\u0001\u0000\u0000\u0000(\u010e\u0001\u0000\u0000"+
		"\u0000*\u0124\u0001\u0000\u0000\u0000,\u013b\u0001\u0000\u0000\u0000."+
		"\u0143\u0001\u0000\u0000\u00000\u014a\u0001\u0000\u0000\u00002\u0156\u0001"+
		"\u0000\u0000\u00004\u0166\u0001\u0000\u0000\u00006\u0168\u0001\u0000\u0000"+
		"\u00008\u0171\u0001\u0000\u0000\u0000:\u017e\u0001\u0000\u0000\u0000<"+
		"\u0180\u0001\u0000\u0000\u0000>\u0184\u0001\u0000\u0000\u0000@\u0195\u0001"+
		"\u0000\u0000\u0000B\u0197\u0001\u0000\u0000\u0000D\u019f\u0001\u0000\u0000"+
		"\u0000F\u01b1\u0001\u0000\u0000\u0000H\u01b7\u0001\u0000\u0000\u0000J"+
		"\u01ba\u0001\u0000\u0000\u0000L\u01c3\u0001\u0000\u0000\u0000N\u01cc\u0001"+
		"\u0000\u0000\u0000P\u01d5\u0001\u0000\u0000\u0000R\u01e4\u0001\u0000\u0000"+
		"\u0000T\u01f4\u0001\u0000\u0000\u0000V\u01f6\u0001\u0000\u0000\u0000X"+
		"\u01fa\u0001\u0000\u0000\u0000Z\u01fe\u0001\u0000\u0000\u0000\\\u0205"+
		"\u0001\u0000\u0000\u0000^\u0212\u0001\u0000\u0000\u0000`\u0218\u0001\u0000"+
		"\u0000\u0000b\u022b\u0001\u0000\u0000\u0000d\u0254\u0001\u0000\u0000\u0000"+
		"fh\u0003\u0002\u0001\u0000gf\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hj\u0001\u0000\u0000\u0000ik\u0003\b\u0004\u0000ji\u0001\u0000\u0000"+
		"\u0000jk\u0001\u0000\u0000\u0000km\u0001\u0000\u0000\u0000ln\u0003\f\u0006"+
		"\u0000ml\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000no\u0001\u0000"+
		"\u0000\u0000op\u0003\u000e\u0007\u0000pq\u0005\u0000\u0000\u0001q\u0001"+
		"\u0001\u0000\u0000\u0000rt\u0003\u0004\u0002\u0000sr\u0001\u0000\u0000"+
		"\u0000tu\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000"+
		"\u0000\u0000v\u0003\u0001\u0000\u0000\u0000wx\u0005\u0001\u0000\u0000"+
		"xz\u0003\u0006\u0003\u0000y{\u00052\u0000\u0000zy\u0001\u0000\u0000\u0000"+
		"z{\u0001\u0000\u0000\u0000{\u0005\u0001\u0000\u0000\u0000|\u007f\u0005"+
		">\u0000\u0000}~\u00050\u0000\u0000~\u0080\u0005>\u0000\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u007f\u0001"+
		"\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0007\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0005\u0003\u0000\u0000\u0084\u0088\u0005"+
		"#\u0000\u0000\u0085\u0087\u0003\n\u0005\u0000\u0086\u0085\u0001\u0000"+
		"\u0000\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000"+
		"\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\t\u0001\u0000\u0000"+
		"\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008e\u0003\u0010\b\u0000"+
		"\u008c\u008e\u0003\u0018\f\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d"+
		"\u008c\u0001\u0000\u0000\u0000\u008e\u000b\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005\u0004\u0000\u0000\u0090\u0094\u0005#\u0000\u0000\u0091\u0093"+
		"\u0003&\u0013\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0096\u0001"+
		"\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001"+
		"\u0000\u0000\u0000\u0095\r\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005\u0005\u0000\u0000\u0098\u009c\u0005#\u0000"+
		"\u0000\u0099\u009b\u00034\u001a\u0000\u009a\u0099\u0001\u0000\u0000\u0000"+
		"\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000"+
		"\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000"+
		"\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a1\u0005\u0006\u0000\u0000"+
		"\u00a0\u00a2\u00052\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a2\u000f\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0005\b\u0000\u0000\u00a4\u00a6\u0005>\u0000\u0000\u00a5\u00a7"+
		"\u00053\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00aa\u0003"+
		"\u0012\t\u0000\u00a9\u00ab\u00052\u0000\u0000\u00aa\u00a9\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u0011\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ae\u0003\"\u0011\u0000\u00ad\u00af\u0003b1\u0000"+
		"\u00ae\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000"+
		"\u00af\u00c3\u0001\u0000\u0000\u0000\u00b0\u00b2\u0005\u000e\u0000\u0000"+
		"\u00b1\u00b3\u0003b1\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b2\u00b3"+
		"\u0001\u0000\u0000\u0000\u00b3\u00c3\u0001\u0000\u0000\u0000\u00b4\u00b5"+
		"\u0005>\u0000\u0000\u00b5\u00c3\u0003\u001e\u000f\u0000\u00b6\u00b7\u0003"+
		"b1\u0000\u00b7\u00b8\u00053\u0000\u0000\u00b8\u00b9\u0003$\u0012\u0000"+
		"\u00b9\u00c3\u0001\u0000\u0000\u0000\u00ba\u00c3\u0003\u0014\n\u0000\u00bb"+
		"\u00c3\u0003\u0016\u000b\u0000\u00bc\u00bf\u0003$\u0012\u0000\u00bd\u00c0"+
		"\u0003b1\u0000\u00be\u00c0\u0003\u001e\u000f\u0000\u00bf\u00bd\u0001\u0000"+
		"\u0000\u0000\u00bf\u00be\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00c3\u0003b1\u0000"+
		"\u00c2\u00ac\u0001\u0000\u0000\u0000\u00c2\u00b0\u0001\u0000\u0000\u0000"+
		"\u00c2\u00b4\u0001\u0000\u0000\u0000\u00c2\u00b6\u0001\u0000\u0000\u0000"+
		"\u00c2\u00ba\u0001\u0000\u0000\u0000\u00c2\u00bb\u0001\u0000\u0000\u0000"+
		"\u00c2\u00bc\u0001\u0000\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c3\u0013\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005\u0002\u0000\u0000"+
		"\u00c5\u00c6\u0005>\u0000\u0000\u00c6\u00c8\u00054\u0000\u0000\u00c7\u00c9"+
		"\u0003B!\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cb\u00055\u0000"+
		"\u0000\u00cb\u0015\u0001\u0000\u0000\u0000\u00cc\u00cd\u0007\u0000\u0000"+
		"\u0000\u00cd\u0017\u0001\u0000\u0000\u0000\u00ce\u00cf\u0005\t\u0000\u0000"+
		"\u00cf\u00d0\u0005>\u0000\u0000\u00d0\u00d1\u00058\u0000\u0000\u00d1\u00d2"+
		"\u0003b1\u0000\u00d2\u00d4\u00059\u0000\u0000\u00d3\u00d5\u00053\u0000"+
		"\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00db\u0003$\u0012\u0000"+
		"\u00d7\u00d8\u00056\u0000\u0000\u00d8\u00d9\u0003\u001a\r\u0000\u00d9"+
		"\u00da\u00057\u0000\u0000\u00da\u00dc\u0001\u0000\u0000\u0000\u00db\u00d7"+
		"\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00de"+
		"\u0001\u0000\u0000\u0000\u00dd\u00df\u00052\u0000\u0000\u00de\u00dd\u0001"+
		"\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u0019\u0001"+
		"\u0000\u0000\u0000\u00e0\u00e5\u0003\u001c\u000e\u0000\u00e1\u00e2\u0005"+
		"1\u0000\u0000\u00e2\u00e4\u0003\u001c\u000e\u0000\u00e3\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e4\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u001b\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e5\u0001\u0000\u0000\u0000\u00e8\u00eb\u0003\u001e"+
		"\u000f\u0000\u00e9\u00eb\u0003b1\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000"+
		"\u00ea\u00e9\u0001\u0000\u0000\u0000\u00eb\u001d\u0001\u0000\u0000\u0000"+
		"\u00ec\u00f5\u00056\u0000\u0000\u00ed\u00f2\u0003 \u0010\u0000\u00ee\u00ef"+
		"\u00051\u0000\u0000\u00ef\u00f1\u0003 \u0010\u0000\u00f0\u00ee\u0001\u0000"+
		"\u0000\u0000\u00f1\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f5\u00ed\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000"+
		"\u0000\u0000\u00f7\u00f8\u00057\u0000\u0000\u00f8\u001f\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fa\u0005>\u0000\u0000\u00fa\u00fd\u00053\u0000\u0000\u00fb"+
		"\u00fe\u0003\u001e\u000f\u0000\u00fc\u00fe\u0003b1\u0000\u00fd\u00fb\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fc\u0001\u0000\u0000\u0000\u00fe\u0102\u0001"+
		"\u0000\u0000\u0000\u00ff\u0102\u0003\u001e\u000f\u0000\u0100\u0102\u0003"+
		"b1\u0000\u0101\u00f9\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000"+
		"\u0000\u0101\u0100\u0001\u0000\u0000\u0000\u0102!\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u0007\u0001\u0000\u0000\u0104#\u0001\u0000\u0000\u0000\u0105"+
		"\u0109\u0003\"\u0011\u0000\u0106\u0109\u0005\u000e\u0000\u0000\u0107\u0109"+
		"\u0005>\u0000\u0000\u0108\u0105\u0001\u0000\u0000\u0000\u0108\u0106\u0001"+
		"\u0000\u0000\u0000\u0108\u0107\u0001\u0000\u0000\u0000\u0109%\u0001\u0000"+
		"\u0000\u0000\u010a\u010d\u0003(\u0014\u0000\u010b\u010d\u0003*\u0015\u0000"+
		"\u010c\u010a\u0001\u0000\u0000\u0000\u010c\u010b\u0001\u0000\u0000\u0000"+
		"\u010d\'\u0001\u0000\u0000\u0000\u010e\u010f\u0005\u0019\u0000\u0000\u010f"+
		"\u0110\u0005>\u0000\u0000\u0110\u0112\u00054\u0000\u0000\u0111\u0113\u0003"+
		",\u0016\u0000\u0112\u0111\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0115\u00055\u0000"+
		"\u0000\u0115\u0117\u00056\u0000\u0000\u0116\u0118\u00030\u0018\u0000\u0117"+
		"\u0116\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118"+
		"\u011c\u0001\u0000\u0000\u0000\u0119\u011b\u00034\u001a\u0000\u011a\u0119"+
		"\u0001\u0000\u0000\u0000\u011b\u011e\u0001\u0000\u0000\u0000\u011c\u011a"+
		"\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011f"+
		"\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011f\u0120"+
		"\u00057\u0000\u0000\u0120\u0122\u0005\u0007\u0000\u0000\u0121\u0123\u0005"+
		"2\u0000\u0000\u0122\u0121\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000"+
		"\u0000\u0000\u0123)\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u001a\u0000"+
		"\u0000\u0125\u0126\u0003$\u0012\u0000\u0126\u0127\u0005>\u0000\u0000\u0127"+
		"\u0129\u00054\u0000\u0000\u0128\u012a\u0003,\u0016\u0000\u0129\u0128\u0001"+
		"\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000\u012a\u012b\u0001"+
		"\u0000\u0000\u0000\u012b\u012c\u00055\u0000\u0000\u012c\u012e\u00056\u0000"+
		"\u0000\u012d\u012f\u00030\u0018\u0000\u012e\u012d\u0001\u0000\u0000\u0000"+
		"\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0133\u0001\u0000\u0000\u0000"+
		"\u0130\u0132\u00034\u001a\u0000\u0131\u0130\u0001\u0000\u0000\u0000\u0132"+
		"\u0135\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0133"+
		"\u0134\u0001\u0000\u0000\u0000\u0134\u0136\u0001\u0000\u0000\u0000\u0135"+
		"\u0133\u0001\u0000\u0000\u0000\u0136\u0137\u00057\u0000\u0000\u0137\u0139"+
		"\u0005\u0007\u0000\u0000\u0138\u013a\u00052\u0000\u0000\u0139\u0138\u0001"+
		"\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a+\u0001\u0000"+
		"\u0000\u0000\u013b\u0140\u0003.\u0017\u0000\u013c\u013d\u00051\u0000\u0000"+
		"\u013d\u013f\u0003.\u0017\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f"+
		"\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140"+
		"\u0141\u0001\u0000\u0000\u0000\u0141-\u0001\u0000\u0000\u0000\u0142\u0140"+
		"\u0001\u0000\u0000\u0000\u0143\u0144\u0005\b\u0000\u0000\u0144\u0146\u0005"+
		">\u0000\u0000\u0145\u0147\u00053\u0000\u0000\u0146\u0145\u0001\u0000\u0000"+
		"\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0003$\u0012\u0000\u0149/\u0001\u0000\u0000\u0000\u014a"+
		"\u014b\u0005\u0003\u0000\u0000\u014b\u014f\u00058\u0000\u0000\u014c\u014e"+
		"\u00032\u0019\u0000\u014d\u014c\u0001\u0000\u0000\u0000\u014e\u0151\u0001"+
		"\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150\u0001"+
		"\u0000\u0000\u0000\u0150\u0152\u0001\u0000\u0000\u0000\u0151\u014f\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u00059\u0000\u0000\u01531\u0001\u0000\u0000"+
		"\u0000\u0154\u0157\u0003\u0010\b\u0000\u0155\u0157\u0003\u0018\f\u0000"+
		"\u0156\u0154\u0001\u0000\u0000\u0000\u0156\u0155\u0001\u0000\u0000\u0000"+
		"\u01573\u0001\u0000\u0000\u0000\u0158\u0167\u0003\u0010\b\u0000\u0159"+
		"\u0167\u0003\u0018\f\u0000\u015a\u0167\u0003\\.\u0000\u015b\u0167\u0003"+
		"^/\u0000\u015c\u0167\u00036\u001b\u0000\u015d\u0167\u0003`0\u0000\u015e"+
		"\u0167\u0003D\"\u0000\u015f\u0167\u0003L&\u0000\u0160\u0167\u0003N\'\u0000"+
		"\u0161\u0167\u0003P(\u0000\u0162\u0167\u0003V+\u0000\u0163\u0167\u0003"+
		"X,\u0000\u0164\u0167\u0003Z-\u0000\u0165\u0167\u0003<\u001e\u0000\u0166"+
		"\u0158\u0001\u0000\u0000\u0000\u0166\u0159\u0001\u0000\u0000\u0000\u0166"+
		"\u015a\u0001\u0000\u0000\u0000\u0166\u015b\u0001\u0000\u0000\u0000\u0166"+
		"\u015c\u0001\u0000\u0000\u0000\u0166\u015d\u0001\u0000\u0000\u0000\u0166"+
		"\u015e\u0001\u0000\u0000\u0000\u0166\u015f\u0001\u0000\u0000\u0000\u0166"+
		"\u0160\u0001\u0000\u0000\u0000\u0166\u0161\u0001\u0000\u0000\u0000\u0166"+
		"\u0162\u0001\u0000\u0000\u0000\u0166\u0163\u0001\u0000\u0000\u0000\u0166"+
		"\u0164\u0001\u0000\u0000\u0000\u0166\u0165\u0001\u0000\u0000\u0000\u0167"+
		"5\u0001\u0000\u0000\u0000\u0168\u0169\u00038\u001c\u0000\u0169\u016c\u0005"+
		"/\u0000\u0000\u016a\u016d\u0003\u001e\u000f\u0000\u016b\u016d\u0003b1"+
		"\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016c\u016b\u0001\u0000\u0000"+
		"\u0000\u016d\u016f\u0001\u0000\u0000\u0000\u016e\u0170\u00052\u0000\u0000"+
		"\u016f\u016e\u0001\u0000\u0000\u0000\u016f\u0170\u0001\u0000\u0000\u0000"+
		"\u01707\u0001\u0000\u0000\u0000\u0171\u0175\u0005>\u0000\u0000\u0172\u0174"+
		"\u0003:\u001d\u0000\u0173\u0172\u0001\u0000\u0000\u0000\u0174\u0177\u0001"+
		"\u0000\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0175\u0176\u0001"+
		"\u0000\u0000\u0000\u01769\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000"+
		"\u0000\u0000\u0178\u0179\u00050\u0000\u0000\u0179\u017f\u0005>\u0000\u0000"+
		"\u017a\u017b\u00058\u0000\u0000\u017b\u017c\u0003b1\u0000\u017c\u017d"+
		"\u00059\u0000\u0000\u017d\u017f\u0001\u0000\u0000\u0000\u017e\u0178\u0001"+
		"\u0000\u0000\u0000\u017e\u017a\u0001\u0000\u0000\u0000\u017f;\u0001\u0000"+
		"\u0000\u0000\u0180\u0182\u0003>\u001f\u0000\u0181\u0183\u00052\u0000\u0000"+
		"\u0182\u0181\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000"+
		"\u0183=\u0001\u0000\u0000\u0000\u0184\u0186\u0005>\u0000\u0000\u0185\u0187"+
		"\u0003@ \u0000\u0186\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000"+
		"\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0188\u0189\u0001\u0000"+
		"\u0000\u0000\u0189?\u0001\u0000\u0000\u0000\u018a\u018b\u00050\u0000\u0000"+
		"\u018b\u0196\u0005>\u0000\u0000\u018c\u018d\u00058\u0000\u0000\u018d\u018e"+
		"\u0003b1\u0000\u018e\u018f\u00059\u0000\u0000\u018f\u0196\u0001\u0000"+
		"\u0000\u0000\u0190\u0192\u00054\u0000\u0000\u0191\u0193\u0003B!\u0000"+
		"\u0192\u0191\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000"+
		"\u0193\u0194\u0001\u0000\u0000\u0000\u0194\u0196\u00055\u0000\u0000\u0195"+
		"\u018a\u0001\u0000\u0000\u0000\u0195\u018c\u0001\u0000\u0000\u0000\u0195"+
		"\u0190\u0001\u0000\u0000\u0000\u0196A\u0001\u0000\u0000\u0000\u0197\u019c"+
		"\u0003b1\u0000\u0198\u0199\u00051\u0000\u0000\u0199\u019b\u0003b1\u0000"+
		"\u019a\u0198\u0001\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000"+
		"\u019c\u019a\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000"+
		"\u019dC\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f"+
		"\u01a0\u0005\u0012\u0000\u0000\u01a0\u01a1\u00054\u0000\u0000\u01a1\u01a2"+
		"\u0003b1\u0000\u01a2\u01a3\u00055\u0000\u0000\u01a3\u01a7\u0003J%\u0000"+
		"\u01a4\u01a6\u0003F#\u0000\u01a5\u01a4\u0001\u0000\u0000\u0000\u01a6\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a7\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a8\u01ab\u0001\u0000\u0000\u0000\u01a9\u01a7"+
		"\u0001\u0000\u0000\u0000\u01aa\u01ac\u0003H$\u0000\u01ab\u01aa\u0001\u0000"+
		"\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000"+
		"\u0000\u0000\u01ad\u01af\u0005\u0007\u0000\u0000\u01ae\u01b0\u00052\u0000"+
		"\u0000\u01af\u01ae\u0001\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000"+
		"\u0000\u01b0E\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005\u0013\u0000\u0000"+
		"\u01b2\u01b3\u00054\u0000\u0000\u01b3\u01b4\u0003b1\u0000\u01b4\u01b5"+
		"\u00055\u0000\u0000\u01b5\u01b6\u0003J%\u0000\u01b6G\u0001\u0000\u0000"+
		"\u0000\u01b7\u01b8\u0005\u0013\u0000\u0000\u01b8\u01b9\u0003J%\u0000\u01b9"+
		"I\u0001\u0000\u0000\u0000\u01ba\u01be\u00056\u0000\u0000\u01bb\u01bd\u0003"+
		"4\u001a\u0000\u01bc\u01bb\u0001\u0000\u0000\u0000\u01bd\u01c0\u0001\u0000"+
		"\u0000\u0000\u01be\u01bc\u0001\u0000\u0000\u0000\u01be\u01bf\u0001\u0000"+
		"\u0000\u0000\u01bf\u01c1\u0001\u0000\u0000\u0000\u01c0\u01be\u0001\u0000"+
		"\u0000\u0000\u01c1\u01c2\u00057\u0000\u0000\u01c2K\u0001\u0000\u0000\u0000"+
		"\u01c3\u01c4\u0005\u0014\u0000\u0000\u01c4\u01c5\u00054\u0000\u0000\u01c5"+
		"\u01c6\u0003b1\u0000\u01c6\u01c7\u00055\u0000\u0000\u01c7\u01c8\u0003"+
		"J%\u0000\u01c8\u01ca\u0005\u0007\u0000\u0000\u01c9\u01cb\u00052\u0000"+
		"\u0000\u01ca\u01c9\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000"+
		"\u0000\u01cbM\u0001\u0000\u0000\u0000\u01cc\u01cd\u0005\u0015\u0000\u0000"+
		"\u01cd\u01ce\u0003J%\u0000\u01ce\u01cf\u0005\u0014\u0000\u0000\u01cf\u01d0"+
		"\u00054\u0000\u0000\u01d0\u01d1\u0003b1\u0000\u01d1\u01d3\u00055\u0000"+
		"\u0000\u01d2\u01d4\u00052\u0000\u0000\u01d3\u01d2\u0001\u0000\u0000\u0000"+
		"\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4O\u0001\u0000\u0000\u0000\u01d5"+
		"\u01d6\u0005\u0016\u0000\u0000\u01d6\u01d7\u00054\u0000\u0000\u01d7\u01d8"+
		"\u0003R)\u0000\u01d8\u01d9\u00052\u0000\u0000\u01d9\u01da\u0003b1\u0000"+
		"\u01da\u01db\u00052\u0000\u0000\u01db\u01dc\u0003T*\u0000\u01dc\u01dd"+
		"\u00055\u0000\u0000\u01dd\u01e2\u0003J%\u0000\u01de\u01e0\u0005\u0007"+
		"\u0000\u0000\u01df\u01e1\u00052\u0000\u0000\u01e0\u01df\u0001\u0000\u0000"+
		"\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e2\u01de\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e3Q\u0001\u0000\u0000\u0000\u01e4\u01e5\u0005\b\u0000\u0000"+
		"\u01e5\u01e7\u0005>\u0000\u0000\u01e6\u01e8\u00053\u0000\u0000\u01e7\u01e6"+
		"\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01e9"+
		"\u0001\u0000\u0000\u0000\u01e9\u01eb\u0003$\u0012\u0000\u01ea\u01ec\u0003"+
		"b1\u0000\u01eb\u01ea\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000"+
		"\u0000\u01ecS\u0001\u0000\u0000\u0000\u01ed\u01ee\u00038\u001c\u0000\u01ee"+
		"\u01ef\u0007\u0002\u0000\u0000\u01ef\u01f5\u0001\u0000\u0000\u0000\u01f0"+
		"\u01f1\u00038\u001c\u0000\u01f1\u01f2\u0005/\u0000\u0000\u01f2\u01f3\u0003"+
		"b1\u0000\u01f3\u01f5\u0001\u0000\u0000\u0000\u01f4\u01ed\u0001\u0000\u0000"+
		"\u0000\u01f4\u01f0\u0001\u0000\u0000\u0000\u01f5U\u0001\u0000\u0000\u0000"+
		"\u01f6\u01f8\u0005\u0018\u0000\u0000\u01f7\u01f9\u00052\u0000\u0000\u01f8"+
		"\u01f7\u0001\u0000\u0000\u0000\u01f8\u01f9\u0001\u0000\u0000\u0000\u01f9"+
		"W\u0001\u0000\u0000\u0000\u01fa\u01fc\u0005\u0017\u0000\u0000\u01fb\u01fd"+
		"\u00052\u0000\u0000\u01fc\u01fb\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001"+
		"\u0000\u0000\u0000\u01fdY\u0001\u0000\u0000\u0000\u01fe\u0200\u0005\u001b"+
		"\u0000\u0000\u01ff\u0201\u0003b1\u0000\u0200\u01ff\u0001\u0000\u0000\u0000"+
		"\u0200\u0201\u0001\u0000\u0000\u0000\u0201\u0203\u0001\u0000\u0000\u0000"+
		"\u0202\u0204\u00052\u0000\u0000\u0203\u0202\u0001\u0000\u0000\u0000\u0203"+
		"\u0204\u0001\u0000\u0000\u0000\u0204[\u0001\u0000\u0000\u0000\u0205\u0206"+
		"\u0005-\u0000\u0000\u0206\u020b\u0003b1\u0000\u0207\u0208\u0005-\u0000"+
		"\u0000\u0208\u020a\u0003b1\u0000\u0209\u0207\u0001\u0000\u0000\u0000\u020a"+
		"\u020d\u0001\u0000\u0000\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020b"+
		"\u020c\u0001\u0000\u0000\u0000\u020c\u020f\u0001\u0000\u0000\u0000\u020d"+
		"\u020b\u0001\u0000\u0000\u0000\u020e\u0210\u00052\u0000\u0000\u020f\u020e"+
		"\u0001\u0000\u0000\u0000\u020f\u0210\u0001\u0000\u0000\u0000\u0210]\u0001"+
		"\u0000\u0000\u0000\u0211\u0213\u00038\u001c\u0000\u0212\u0211\u0001\u0000"+
		"\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213\u0214\u0001\u0000"+
		"\u0000\u0000\u0214\u0216\u0005.\u0000\u0000\u0215\u0217\u00052\u0000\u0000"+
		"\u0216\u0215\u0001\u0000\u0000\u0000\u0216\u0217\u0001\u0000\u0000\u0000"+
		"\u0217_\u0001\u0000\u0000\u0000\u0218\u0219\u00038\u001c\u0000\u0219\u021b"+
		"\u0007\u0002\u0000\u0000\u021a\u021c\u00052\u0000\u0000\u021b\u021a\u0001"+
		"\u0000\u0000\u0000\u021b\u021c\u0001\u0000\u0000\u0000\u021ca\u0001\u0000"+
		"\u0000\u0000\u021d\u021e\u00061\uffff\uffff\u0000\u021e\u022c\u0003\u0014"+
		"\n\u0000\u021f\u0220\u0005\u001c\u0000\u0000\u0220\u022c\u0003b1\f\u0221"+
		"\u0222\u0005\u001d\u0000\u0000\u0222\u022c\u0003b1\u000b\u0223\u0224\u0005"+
		"\'\u0000\u0000\u0224\u022c\u0003b1\n\u0225\u0226\u00054\u0000\u0000\u0226"+
		"\u0227\u0003b1\u0000\u0227\u0228\u00055\u0000\u0000\u0228\u022c\u0001"+
		"\u0000\u0000\u0000\u0229\u022c\u0005>\u0000\u0000\u022a\u022c\u0003d2"+
		"\u0000\u022b\u021d\u0001\u0000\u0000\u0000\u022b\u021f\u0001\u0000\u0000"+
		"\u0000\u022b\u0221\u0001\u0000\u0000\u0000\u022b\u0223\u0001\u0000\u0000"+
		"\u0000\u022b\u0225\u0001\u0000\u0000\u0000\u022b\u0229\u0001\u0000\u0000"+
		"\u0000\u022b\u022a\u0001\u0000\u0000\u0000\u022c\u0251\u0001\u0000\u0000"+
		"\u0000\u022d\u022e\n\t\u0000\u0000\u022e\u022f\u0007\u0003\u0000\u0000"+
		"\u022f\u0250\u0003b1\n\u0230\u0231\n\b\u0000\u0000\u0231\u0232\u0007\u0004"+
		"\u0000\u0000\u0232\u0250\u0003b1\t\u0233\u0234\n\u0007\u0000\u0000\u0234"+
		"\u0235\u0007\u0005\u0000\u0000\u0235\u0250\u0003b1\b\u0236\u0237\n\u0006"+
		"\u0000\u0000\u0237\u0238\u0007\u0006\u0000\u0000\u0238\u0250\u0003b1\u0007"+
		"\u0239\u023a\n\u0005\u0000\u0000\u023a\u023b\u0005$\u0000\u0000\u023b"+
		"\u0250\u0003b1\u0006\u023c\u023d\n\u0004\u0000\u0000\u023d\u023e\u0005"+
		"%\u0000\u0000\u023e\u0250\u0003b1\u0005\u023f\u0240\n\u0011\u0000\u0000"+
		"\u0240\u0250\u0007\u0002\u0000\u0000\u0241\u0242\n\u0010\u0000\u0000\u0242"+
		"\u0243\u00050\u0000\u0000\u0243\u0250\u0005>\u0000\u0000\u0244\u0245\n"+
		"\u000f\u0000\u0000\u0245\u0246\u00058\u0000\u0000\u0246\u0247\u0003b1"+
		"\u0000\u0247\u0248\u00059\u0000\u0000\u0248\u0250\u0001\u0000\u0000\u0000"+
		"\u0249\u024a\n\u000e\u0000\u0000\u024a\u024c\u00054\u0000\u0000\u024b"+
		"\u024d\u0003B!\u0000\u024c\u024b\u0001\u0000\u0000\u0000\u024c\u024d\u0001"+
		"\u0000\u0000\u0000\u024d\u024e\u0001\u0000\u0000\u0000\u024e\u0250\u0005"+
		"5\u0000\u0000\u024f\u022d\u0001\u0000\u0000\u0000\u024f\u0230\u0001\u0000"+
		"\u0000\u0000\u024f\u0233\u0001\u0000\u0000\u0000\u024f\u0236\u0001\u0000"+
		"\u0000\u0000\u024f\u0239\u0001\u0000\u0000\u0000\u024f\u023c\u0001\u0000"+
		"\u0000\u0000\u024f\u023f\u0001\u0000\u0000\u0000\u024f\u0241\u0001\u0000"+
		"\u0000\u0000\u024f\u0244\u0001\u0000\u0000\u0000\u024f\u0249\u0001\u0000"+
		"\u0000\u0000\u0250\u0253\u0001\u0000\u0000\u0000\u0251\u024f\u0001\u0000"+
		"\u0000\u0000\u0251\u0252\u0001\u0000\u0000\u0000\u0252c\u0001\u0000\u0000"+
		"\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0254\u0255\u0007\u0007\u0000"+
		"\u0000\u0255e\u0001\u0000\u0000\u0000Kgjmuz\u0081\u0088\u008d\u0094\u009c"+
		"\u00a1\u00a6\u00aa\u00ae\u00b2\u00bf\u00c2\u00c8\u00d4\u00db\u00de\u00e5"+
		"\u00ea\u00f2\u00f5\u00fd\u0101\u0108\u010c\u0112\u0117\u011c\u0122\u0129"+
		"\u012e\u0133\u0139\u0140\u0146\u014f\u0156\u0166\u016c\u016f\u0175\u017e"+
		"\u0182\u0188\u0192\u0195\u019c\u01a7\u01ab\u01af\u01be\u01ca\u01d3\u01e0"+
		"\u01e2\u01e7\u01eb\u01f4\u01f8\u01fc\u0200\u0203\u020b\u020f\u0212\u0216"+
		"\u021b\u022b\u024c\u024f\u0251";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}