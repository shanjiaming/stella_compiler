// Generated from D:/hw/pg/compiler/workspace/src/Parser\Mxstar.g4 by ANTLR 4.9.1
package Parser;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, Int=9, 
		Bool=10, String=11, Null=12, Void=13, If=14, Else=15, For=16, While=17, 
		Break=18, Continue=19, Return=20, New=21, Class=22, This=23, Whitespace=24, 
		Comment=25, BoolConst=26, IntConst=27, StringConst=28, Identifier=29, 
		LeftParen=30, RightParen=31, LeftBracket=32, RightBracket=33, LeftBrace=34, 
		RightBrace=35, Less=36, LessEqual=37, Greater=38, GreaterEqual=39, LeftShift=40, 
		RightShift=41, Plus=42, Minus=43, And=44, Or=45, AndAnd=46, OrOr=47, Caret=48, 
		Not=49, Tilde=50, Question=51, Colon=52, Semi=53, Comma=54, Assign=55, 
		Equal=56, NotEqual=57;
	public static final int
		RULE_program = 0, RULE_declare = 1, RULE_baseType = 2, RULE_typename = 3, 
		RULE_constType = 4, RULE_varDeclare = 5, RULE_varSingleDeclare = 6, RULE_funcDeclare = 7, 
		RULE_parameterList = 8, RULE_classDeclare = 9, RULE_classIdentity = 10, 
		RULE_constructDeclare = 11, RULE_expressionList = 12, RULE_value = 13, 
		RULE_newExpr = 14, RULE_lambda = 15, RULE_expression = 16, RULE_suite = 17, 
		RULE_statement = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "declare", "baseType", "typename", "constType", "varDeclare", 
			"varSingleDeclare", "funcDeclare", "parameterList", "classDeclare", "classIdentity", 
			"constructDeclare", "expressionList", "value", "newExpr", "lambda", "expression", 
			"suite", "statement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'[&]'", "'->'", "'.'", "'++'", "'--'", "'*'", "'/'", "'%'", "'int'", 
			"'bool'", "'string'", "'null'", "'void'", "'if'", "'else'", "'for'", 
			"'while'", "'break'", "'continue'", "'return'", "'new'", "'class'", "'this'", 
			null, null, null, null, null, null, "'('", "')'", "'['", "']'", "'{'", 
			"'}'", "'<'", "'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'", "'-'", "'&'", 
			"'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", "'?'", "':'", "';'", "','", 
			"'='", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "Int", "Bool", 
			"String", "Null", "Void", "If", "Else", "For", "While", "Break", "Continue", 
			"Return", "New", "Class", "This", "Whitespace", "Comment", "BoolConst", 
			"IntConst", "StringConst", "Identifier", "LeftParen", "RightParen", "LeftBracket", 
			"RightBracket", "LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", 
			"GreaterEqual", "LeftShift", "RightShift", "Plus", "Minus", "And", "Or", 
			"AndAnd", "OrOr", "Caret", "Not", "Tilde", "Question", "Colon", "Semi", 
			"Comma", "Assign", "Equal", "NotEqual"
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
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxstarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxstarParser.EOF, 0); }
		public List<DeclareContext> declare() {
			return getRuleContexts(DeclareContext.class);
		}
		public DeclareContext declare(int i) {
			return getRuleContext(DeclareContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier) | (1L << Semi))) != 0)) {
				{
				{
				setState(38);
				declare();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
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

	public static class DeclareContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public VarDeclareContext varDeclare() {
			return getRuleContext(VarDeclareContext.class,0);
		}
		public ClassDeclareContext classDeclare() {
			return getRuleContext(ClassDeclareContext.class,0);
		}
		public FuncDeclareContext funcDeclare() {
			return getRuleContext(FuncDeclareContext.class,0);
		}
		public DeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareContext declare() throws RecognitionException {
		DeclareContext _localctx = new DeclareContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declare);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				varDeclare();
				setState(48);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				classDeclare();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(51);
				funcDeclare();
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

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode Bool() { return getToken(MxstarParser.Bool, 0); }
		public TerminalNode Int() { return getToken(MxstarParser.Int, 0); }
		public TerminalNode String() { return getToken(MxstarParser.String, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterBaseType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitBaseType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
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

	public static class TypenameContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode LeftBracket() { return getToken(MxstarParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxstarParser.RightBracket, 0); }
		public TypenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterTypename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitTypename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitTypename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypenameContext typename() throws RecognitionException {
		return typename(0);
	}

	private TypenameContext typename(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypenameContext _localctx = new TypenameContext(_ctx, _parentState);
		TypenameContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_typename, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(57);
			baseType();
			}
			_ctx.stop = _input.LT(-1);
			setState(64);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypenameContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_typename);
					setState(59);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(60);
					match(LeftBracket);
					setState(61);
					match(RightBracket);
					}
					} 
				}
				setState(66);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class ConstTypeContext extends ParserRuleContext {
		public TerminalNode IntConst() { return getToken(MxstarParser.IntConst, 0); }
		public TerminalNode StringConst() { return getToken(MxstarParser.StringConst, 0); }
		public TerminalNode BoolConst() { return getToken(MxstarParser.BoolConst, 0); }
		public TerminalNode Null() { return getToken(MxstarParser.Null, 0); }
		public ConstTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConstType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConstType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConstType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstTypeContext constType() throws RecognitionException {
		ConstTypeContext _localctx = new ConstTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) ) {
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

	public static class VarDeclareContext extends ParserRuleContext {
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public List<VarSingleDeclareContext> varSingleDeclare() {
			return getRuleContexts(VarSingleDeclareContext.class);
		}
		public VarSingleDeclareContext varSingleDeclare(int i) {
			return getRuleContext(VarSingleDeclareContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public VarDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclareContext varDeclare() throws RecognitionException {
		VarDeclareContext _localctx = new VarDeclareContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			typename(0);
			setState(70);
			varSingleDeclare();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(71);
				match(Comma);
				setState(72);
				varSingleDeclare();
				}
				}
				setState(77);
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

	public static class VarSingleDeclareContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LeftBracket() { return getToken(MxstarParser.LeftBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(MxstarParser.RightBracket, 0); }
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public VarSingleDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varSingleDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterVarSingleDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitVarSingleDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitVarSingleDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarSingleDeclareContext varSingleDeclare() throws RecognitionException {
		VarSingleDeclareContext _localctx = new VarSingleDeclareContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varSingleDeclare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(Identifier);
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBracket:
				{
				setState(79);
				match(LeftBracket);
				setState(80);
				expression(0);
				setState(81);
				match(RightBracket);
				}
				break;
			case Assign:
				{
				setState(83);
				match(Assign);
				setState(84);
				expression(0);
				}
				break;
			case Int:
			case Bool:
			case String:
			case Void:
			case Identifier:
			case RightBrace:
			case Semi:
			case Comma:
				break;
			default:
				break;
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

	public static class FuncDeclareContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public TypenameContext typename() {
			return getRuleContext(TypenameContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxstarParser.Void, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FuncDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterFuncDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitFuncDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitFuncDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclareContext funcDeclare() throws RecognitionException {
		FuncDeclareContext _localctx = new FuncDeclareContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
			case Identifier:
				{
				setState(87);
				typename(0);
				}
				break;
			case Void:
				{
				setState(88);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(91);
			match(Identifier);
			setState(92);
			parameterList();
			setState(93);
			match(LeftBrace);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Semi))) != 0)) {
				{
				{
				setState(94);
				statement();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			match(RightBrace);
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

	public static class ParameterListContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public List<TypenameContext> typename() {
			return getRuleContexts(TypenameContext.class);
		}
		public TypenameContext typename(int i) {
			return getRuleContext(TypenameContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxstarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxstarParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(LeftParen);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(103);
				typename(0);
				setState(104);
				match(Identifier);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(105);
					match(Comma);
					setState(106);
					typename(0);
					setState(107);
					match(Identifier);
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(116);
			match(RightParen);
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

	public static class ClassDeclareContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxstarParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public List<ClassIdentityContext> classIdentity() {
			return getRuleContexts(ClassIdentityContext.class);
		}
		public ClassIdentityContext classIdentity(int i) {
			return getRuleContext(ClassIdentityContext.class,i);
		}
		public ClassDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclareContext classDeclare() throws RecognitionException {
		ClassDeclareContext _localctx = new ClassDeclareContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(Class);
			setState(119);
			match(Identifier);
			setState(120);
			match(LeftBrace);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier) | (1L << Semi))) != 0)) {
				{
				{
				setState(121);
				classIdentity();
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(RightBrace);
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

	public static class ClassIdentityContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxstarParser.Semi, 0); }
		public ConstructDeclareContext constructDeclare() {
			return getRuleContext(ConstructDeclareContext.class,0);
		}
		public VarDeclareContext varDeclare() {
			return getRuleContext(VarDeclareContext.class,0);
		}
		public FuncDeclareContext funcDeclare() {
			return getRuleContext(FuncDeclareContext.class,0);
		}
		public ClassIdentityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classIdentity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterClassIdentity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitClassIdentity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitClassIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassIdentityContext classIdentity() throws RecognitionException {
		ClassIdentityContext _localctx = new ClassIdentityContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classIdentity);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				constructDeclare();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				varDeclare();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				funcDeclare();
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

	public static class ConstructDeclareContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ConstructDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterConstructDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitConstructDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitConstructDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructDeclareContext constructDeclare() throws RecognitionException {
		ConstructDeclareContext _localctx = new ConstructDeclareContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_constructDeclare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(Identifier);
			setState(136);
			match(LeftParen);
			setState(137);
			match(RightParen);
			setState(138);
			match(LeftBrace);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Semi))) != 0)) {
				{
				{
				setState(139);
				statement();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(145);
			match(RightBrace);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(LeftParen);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Null) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde))) != 0)) {
				{
				setState(148);
				expression(0);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(149);
					match(Comma);
					setState(150);
					expression(0);
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(158);
			match(RightParen);
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ConstTypeContext constType() {
			return getRuleContext(ConstTypeContext.class,0);
		}
		public NewExprContext newExpr() {
			return getRuleContext(NewExprContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public TerminalNode This() { return getToken(MxstarParser.This, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_value);
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(Identifier);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				constType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				newExpr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(Identifier);
				setState(164);
				expressionList();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(165);
				lambda();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(166);
				match(This);
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

	public static class NewExprContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxstarParser.New, 0); }
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public TerminalNode Identifier() { return getToken(MxstarParser.Identifier, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxstarParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxstarParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxstarParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxstarParser.RightBracket, i);
		}
		public NewExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExprContext newExpr() throws RecognitionException {
		NewExprContext _localctx = new NewExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_newExpr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(New);
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(170);
				baseType();
				}
				break;
			case 2:
				{
				setState(171);
				baseType();
				setState(172);
				match(LeftParen);
				setState(173);
				expression(0);
				setState(174);
				match(RightParen);
				}
				break;
			case 3:
				{
				setState(176);
				baseType();
				setState(181); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(177);
						match(LeftBracket);
						setState(178);
						expression(0);
						setState(179);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(183); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(185);
						match(LeftBracket);
						setState(186);
						match(RightBracket);
						}
						} 
					}
					setState(191);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				break;
			case 4:
				{
				setState(192);
				match(Identifier);
				setState(193);
				parameterList();
				}
				break;
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

	public static class LambdaContext extends ParserRuleContext {
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T__0);
			setState(197);
			parameterList();
			setState(198);
			match(T__1);
			setState(199);
			match(LeftBrace);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Semi))) != 0)) {
				{
				{
				setState(200);
				statement();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
			match(RightBrace);
			setState(207);
			expressionList();
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

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public TerminalNode Not() { return getToken(MxstarParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(MxstarParser.Tilde, 0); }
		public TerminalNode Plus() { return getToken(MxstarParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxstarParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxstarParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxstarParser.RightShift, 0); }
		public TerminalNode Assign() { return getToken(MxstarParser.Assign, 0); }
		public TerminalNode Less() { return getToken(MxstarParser.Less, 0); }
		public TerminalNode Greater() { return getToken(MxstarParser.Greater, 0); }
		public TerminalNode LessEqual() { return getToken(MxstarParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxstarParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(MxstarParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(MxstarParser.NotEqual, 0); }
		public TerminalNode And() { return getToken(MxstarParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxstarParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxstarParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxstarParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxstarParser.OrOr, 0); }
		public List<TerminalNode> LeftBracket() { return getTokens(MxstarParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxstarParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxstarParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxstarParser.RightBracket, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case Null:
			case New:
			case This:
			case BoolConst:
			case IntConst:
			case StringConst:
			case Identifier:
				{
				setState(210);
				value();
				}
				break;
			case LeftParen:
				{
				setState(211);
				match(LeftParen);
				setState(212);
				expression(0);
				setState(213);
				match(RightParen);
				}
				break;
			case Not:
			case Tilde:
				{
				setState(215);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Not || _la==Tilde) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(216);
				expression(10);
				}
				break;
			case Plus:
			case Minus:
				{
				setState(217);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(218);
				expression(9);
				}
				break;
			case T__3:
			case T__4:
				{
				setState(219);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(220);
				expression(8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(262);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(223);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(224);
						match(T__2);
						setState(225);
						expression(12);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(227);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(228);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(230);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(231);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(232);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(233);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(234);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(235);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(236);
						match(Assign);
						setState(237);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(238);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(239);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual) | (1L << Equal) | (1L << NotEqual))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(240);
						expression(3);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(241);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(242);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << And) | (1L << Or) | (1L << AndAnd) | (1L << OrOr) | (1L << Caret))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(243);
						expression(2);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(244);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(249); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(245);
								match(LeftBracket);
								setState(246);
								expression(0);
								setState(247);
								match(RightBracket);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(251); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						setState(257);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(253);
								match(LeftBracket);
								setState(254);
								match(RightBracket);
								}
								} 
							}
							setState(259);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
						}
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(260);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(261);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxstarParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxstarParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			match(LeftBrace);
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde) | (1L << Semi))) != 0)) {
				{
				{
				setState(268);
				statement();
				}
				}
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(274);
			match(RightBrace);
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

	public static class StatementContext extends ParserRuleContext {
		public List<TerminalNode> Semi() { return getTokens(MxstarParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxstarParser.Semi, i);
		}
		public VarDeclareContext varDeclare() {
			return getRuleContext(VarDeclareContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode If() { return getToken(MxstarParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxstarParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxstarParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxstarParser.Else, 0); }
		public TerminalNode For() { return getToken(MxstarParser.For, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxstarParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxstarParser.Comma, i);
		}
		public TerminalNode While() { return getToken(MxstarParser.While, 0); }
		public TerminalNode Return() { return getToken(MxstarParser.Return, 0); }
		public TerminalNode Break() { return getToken(MxstarParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxstarParser.Continue, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxstarListener ) ((MxstarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxstarVisitor ) return ((MxstarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_statement);
		int _la;
		try {
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(276);
				match(Semi);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
				varDeclare();
				setState(278);
				match(Semi);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(280);
				expression(0);
				setState(281);
				match(Semi);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
				match(If);
				setState(284);
				match(LeftParen);
				setState(285);
				expression(0);
				setState(286);
				match(RightParen);
				setState(287);
				statement();
				setState(290);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(288);
					match(Else);
					setState(289);
					statement();
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(292);
				match(For);
				setState(293);
				match(LeftParen);
				setState(305);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(294);
					varDeclare();
					}
					break;
				case 2:
					{
					setState(303);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Null) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde))) != 0)) {
						{
						setState(295);
						expression(0);
						setState(300);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==Comma) {
							{
							{
							setState(296);
							match(Comma);
							setState(297);
							expression(0);
							}
							}
							setState(302);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					}
					break;
				}
				setState(307);
				match(Semi);
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Null) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde))) != 0)) {
					{
					setState(308);
					expression(0);
					}
				}

				setState(311);
				match(Semi);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__3) | (1L << T__4) | (1L << Null) | (1L << New) | (1L << This) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier) | (1L << LeftParen) | (1L << Plus) | (1L << Minus) | (1L << Not) | (1L << Tilde))) != 0)) {
					{
					setState(312);
					expression(0);
					}
				}

				setState(315);
				match(RightParen);
				setState(316);
				statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(317);
				match(While);
				setState(318);
				match(LeftParen);
				setState(319);
				expression(0);
				setState(320);
				match(RightParen);
				setState(321);
				statement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(323);
				match(Return);
				setState(325);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(324);
					expression(0);
					}
					break;
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(327);
				match(Break);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(328);
				match(Continue);
				setState(329);
				match(Semi);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(330);
				suite();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return typename_sempred((TypenameContext)_localctx, predIndex);
		case 16:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean typename_sempred(TypenameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		case 8:
			return precpred(_ctx, 12);
		case 9:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u0150\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\67\n\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5A\n\5\f\5\16"+
		"\5D\13\5\3\6\3\6\3\7\3\7\3\7\3\7\7\7L\n\7\f\7\16\7O\13\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\bX\n\b\3\t\3\t\5\t\\\n\t\3\t\3\t\3\t\3\t\7\tb\n\t\f\t"+
		"\16\te\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\np\n\n\f\n\16\ns\13"+
		"\n\5\nu\n\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13}\n\13\f\13\16\13\u0080\13"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u0088\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u008f"+
		"\n\r\f\r\16\r\u0092\13\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u009a\n\16\f"+
		"\16\16\16\u009d\13\16\5\16\u009f\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\5\17\u00aa\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\6\20\u00b8\n\20\r\20\16\20\u00b9\3\20\3\20\7\20\u00be"+
		"\n\20\f\20\16\20\u00c1\13\20\3\20\3\20\5\20\u00c5\n\20\3\21\3\21\3\21"+
		"\3\21\3\21\7\21\u00cc\n\21\f\21\16\21\u00cf\13\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00e0\n\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\6\22\u00fc"+
		"\n\22\r\22\16\22\u00fd\3\22\3\22\7\22\u0102\n\22\f\22\16\22\u0105\13\22"+
		"\3\22\3\22\7\22\u0109\n\22\f\22\16\22\u010c\13\22\3\23\3\23\7\23\u0110"+
		"\n\23\f\23\16\23\u0113\13\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0125\n\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\7\24\u012d\n\24\f\24\16\24\u0130\13\24\5\24\u0132\n\24"+
		"\5\24\u0134\n\24\3\24\3\24\5\24\u0138\n\24\3\24\3\24\5\24\u013c\n\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0148\n\24\3\24"+
		"\3\24\3\24\3\24\5\24\u014e\n\24\3\24\2\4\b\"\25\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&\2\13\4\2\13\r\37\37\4\2\16\16\34\36\3\2\63\64\3\2"+
		",-\3\2\6\7\3\2\b\n\3\2*+\4\2&):;\3\2.\62\2\u017a\2+\3\2\2\2\4\66\3\2\2"+
		"\2\68\3\2\2\2\b:\3\2\2\2\nE\3\2\2\2\fG\3\2\2\2\16P\3\2\2\2\20[\3\2\2\2"+
		"\22h\3\2\2\2\24x\3\2\2\2\26\u0087\3\2\2\2\30\u0089\3\2\2\2\32\u0095\3"+
		"\2\2\2\34\u00a9\3\2\2\2\36\u00ab\3\2\2\2 \u00c6\3\2\2\2\"\u00df\3\2\2"+
		"\2$\u010d\3\2\2\2&\u014d\3\2\2\2(*\5\4\3\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2"+
		"\2+,\3\2\2\2,.\3\2\2\2-+\3\2\2\2./\7\2\2\3/\3\3\2\2\2\60\67\7\67\2\2\61"+
		"\62\5\f\7\2\62\63\7\67\2\2\63\67\3\2\2\2\64\67\5\24\13\2\65\67\5\20\t"+
		"\2\66\60\3\2\2\2\66\61\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\5\3\2\2"+
		"\289\t\2\2\29\7\3\2\2\2:;\b\5\1\2;<\5\6\4\2<B\3\2\2\2=>\f\4\2\2>?\7\""+
		"\2\2?A\7#\2\2@=\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\t\3\2\2\2DB\3\2"+
		"\2\2EF\t\3\2\2F\13\3\2\2\2GH\5\b\5\2HM\5\16\b\2IJ\78\2\2JL\5\16\b\2KI"+
		"\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\r\3\2\2\2OM\3\2\2\2PW\7\37\2\2"+
		"QR\7\"\2\2RS\5\"\22\2ST\7#\2\2TX\3\2\2\2UV\79\2\2VX\5\"\22\2WQ\3\2\2\2"+
		"WU\3\2\2\2WX\3\2\2\2X\17\3\2\2\2Y\\\5\b\5\2Z\\\7\17\2\2[Y\3\2\2\2[Z\3"+
		"\2\2\2\\]\3\2\2\2]^\7\37\2\2^_\5\22\n\2_c\7$\2\2`b\5&\24\2a`\3\2\2\2b"+
		"e\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7%\2\2g\21\3\2\2\2"+
		"ht\7 \2\2ij\5\b\5\2jq\7\37\2\2kl\78\2\2lm\5\b\5\2mn\7\37\2\2np\3\2\2\2"+
		"ok\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2sq\3\2\2\2ti\3\2\2\2"+
		"tu\3\2\2\2uv\3\2\2\2vw\7!\2\2w\23\3\2\2\2xy\7\30\2\2yz\7\37\2\2z~\7$\2"+
		"\2{}\5\26\f\2|{\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7%\2\2\u0082\25\3\2\2\2\u0083\u0088"+
		"\7\67\2\2\u0084\u0088\5\30\r\2\u0085\u0088\5\f\7\2\u0086\u0088\5\20\t"+
		"\2\u0087\u0083\3\2\2\2\u0087\u0084\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0086"+
		"\3\2\2\2\u0088\27\3\2\2\2\u0089\u008a\7\37\2\2\u008a\u008b\7 \2\2\u008b"+
		"\u008c\7!\2\2\u008c\u0090\7$\2\2\u008d\u008f\5&\24\2\u008e\u008d\3\2\2"+
		"\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093"+
		"\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7%\2\2\u0094\31\3\2\2\2\u0095"+
		"\u009e\7 \2\2\u0096\u009b\5\"\22\2\u0097\u0098\78\2\2\u0098\u009a\5\""+
		"\22\2\u0099\u0097\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u0096\3\2"+
		"\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\7!\2\2\u00a1"+
		"\33\3\2\2\2\u00a2\u00aa\7\37\2\2\u00a3\u00aa\5\n\6\2\u00a4\u00aa\5\36"+
		"\20\2\u00a5\u00a6\7\37\2\2\u00a6\u00aa\5\32\16\2\u00a7\u00aa\5 \21\2\u00a8"+
		"\u00aa\7\31\2\2\u00a9\u00a2\3\2\2\2\u00a9\u00a3\3\2\2\2\u00a9\u00a4\3"+
		"\2\2\2\u00a9\u00a5\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa"+
		"\35\3\2\2\2\u00ab\u00c4\7\27\2\2\u00ac\u00c5\5\6\4\2\u00ad\u00ae\5\6\4"+
		"\2\u00ae\u00af\7 \2\2\u00af\u00b0\5\"\22\2\u00b0\u00b1\7!\2\2\u00b1\u00c5"+
		"\3\2\2\2\u00b2\u00b7\5\6\4\2\u00b3\u00b4\7\"\2\2\u00b4\u00b5\5\"\22\2"+
		"\u00b5\u00b6\7#\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b3\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bf\3\2\2\2\u00bb"+
		"\u00bc\7\"\2\2\u00bc\u00be\7#\2\2\u00bd\u00bb\3\2\2\2\u00be\u00c1\3\2"+
		"\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c5\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c2\u00c3\7\37\2\2\u00c3\u00c5\5\22\n\2\u00c4\u00ac\3"+
		"\2\2\2\u00c4\u00ad\3\2\2\2\u00c4\u00b2\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5"+
		"\37\3\2\2\2\u00c6\u00c7\7\3\2\2\u00c7\u00c8\5\22\n\2\u00c8\u00c9\7\4\2"+
		"\2\u00c9\u00cd\7$\2\2\u00ca\u00cc\5&\24\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00cd\3\2\2\2\u00d0\u00d1\7%\2\2\u00d1\u00d2\5\32\16\2\u00d2!\3\2\2\2"+
		"\u00d3\u00d4\b\22\1\2\u00d4\u00e0\5\34\17\2\u00d5\u00d6\7 \2\2\u00d6\u00d7"+
		"\5\"\22\2\u00d7\u00d8\7!\2\2\u00d8\u00e0\3\2\2\2\u00d9\u00da\t\4\2\2\u00da"+
		"\u00e0\5\"\22\f\u00db\u00dc\t\5\2\2\u00dc\u00e0\5\"\22\13\u00dd\u00de"+
		"\t\6\2\2\u00de\u00e0\5\"\22\n\u00df\u00d3\3\2\2\2\u00df\u00d5\3\2\2\2"+
		"\u00df\u00d9\3\2\2\2\u00df\u00db\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u010a"+
		"\3\2\2\2\u00e1\u00e2\f\r\2\2\u00e2\u00e3\7\5\2\2\u00e3\u0109\5\"\22\16"+
		"\u00e4\u00e5\f\b\2\2\u00e5\u00e6\t\7\2\2\u00e6\u0109\5\"\22\t\u00e7\u00e8"+
		"\f\7\2\2\u00e8\u00e9\t\5\2\2\u00e9\u0109\5\"\22\b\u00ea\u00eb\f\6\2\2"+
		"\u00eb\u00ec\t\b\2\2\u00ec\u0109\5\"\22\7\u00ed\u00ee\f\5\2\2\u00ee\u00ef"+
		"\79\2\2\u00ef\u0109\5\"\22\5\u00f0\u00f1\f\4\2\2\u00f1\u00f2\t\t\2\2\u00f2"+
		"\u0109\5\"\22\5\u00f3\u00f4\f\3\2\2\u00f4\u00f5\t\n\2\2\u00f5\u0109\5"+
		"\"\22\4\u00f6\u00fb\f\16\2\2\u00f7\u00f8\7\"\2\2\u00f8\u00f9\5\"\22\2"+
		"\u00f9\u00fa\7#\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fc\u00fd"+
		"\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0103\3\2\2\2\u00ff"+
		"\u0100\7\"\2\2\u0100\u0102\7#\2\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2"+
		"\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0109\3\2\2\2\u0105"+
		"\u0103\3\2\2\2\u0106\u0107\f\t\2\2\u0107\u0109\t\6\2\2\u0108\u00e1\3\2"+
		"\2\2\u0108\u00e4\3\2\2\2\u0108\u00e7\3\2\2\2\u0108\u00ea\3\2\2\2\u0108"+
		"\u00ed\3\2\2\2\u0108\u00f0\3\2\2\2\u0108\u00f3\3\2\2\2\u0108\u00f6\3\2"+
		"\2\2\u0108\u0106\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b#\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u0111\7$\2\2\u010e"+
		"\u0110\5&\24\2\u010f\u010e\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2"+
		"\2\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2\2\2\u0113\u0111\3\2\2\2\u0114"+
		"\u0115\7%\2\2\u0115%\3\2\2\2\u0116\u014e\7\67\2\2\u0117\u0118\5\f\7\2"+
		"\u0118\u0119\7\67\2\2\u0119\u014e\3\2\2\2\u011a\u011b\5\"\22\2\u011b\u011c"+
		"\7\67\2\2\u011c\u014e\3\2\2\2\u011d\u011e\7\20\2\2\u011e\u011f\7 \2\2"+
		"\u011f\u0120\5\"\22\2\u0120\u0121\7!\2\2\u0121\u0124\5&\24\2\u0122\u0123"+
		"\7\21\2\2\u0123\u0125\5&\24\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2"+
		"\u0125\u014e\3\2\2\2\u0126\u0127\7\22\2\2\u0127\u0133\7 \2\2\u0128\u0134"+
		"\5\f\7\2\u0129\u012e\5\"\22\2\u012a\u012b\78\2\2\u012b\u012d\5\"\22\2"+
		"\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0129\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u0128\3\2\2\2\u0133\u0131\3\2"+
		"\2\2\u0134\u0135\3\2\2\2\u0135\u0137\7\67\2\2\u0136\u0138\5\"\22\2\u0137"+
		"\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\7\67"+
		"\2\2\u013a\u013c\5\"\22\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013d\3\2\2\2\u013d\u013e\7!\2\2\u013e\u014e\5&\24\2\u013f\u0140\7\23"+
		"\2\2\u0140\u0141\7 \2\2\u0141\u0142\5\"\22\2\u0142\u0143\7!\2\2\u0143"+
		"\u0144\5&\24\2\u0144\u014e\3\2\2\2\u0145\u0147\7\26\2\2\u0146\u0148\5"+
		"\"\22\2\u0147\u0146\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u014e\3\2\2\2\u0149"+
		"\u014e\7\24\2\2\u014a\u014b\7\25\2\2\u014b\u014e\7\67\2\2\u014c\u014e"+
		"\5$\23\2\u014d\u0116\3\2\2\2\u014d\u0117\3\2\2\2\u014d\u011a\3\2\2\2\u014d"+
		"\u011d\3\2\2\2\u014d\u0126\3\2\2\2\u014d\u013f\3\2\2\2\u014d\u0145\3\2"+
		"\2\2\u014d\u0149\3\2\2\2\u014d\u014a\3\2\2\2\u014d\u014c\3\2\2\2\u014e"+
		"\'\3\2\2\2#+\66BMW[cqt~\u0087\u0090\u009b\u009e\u00a9\u00b9\u00bf\u00c4"+
		"\u00cd\u00df\u00fd\u0103\u0108\u010a\u0111\u0124\u012e\u0131\u0133\u0137"+
		"\u013b\u0147\u014d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}