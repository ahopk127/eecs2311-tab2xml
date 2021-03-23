// Generated from DrumTab.g4 by ANTLR 4.9.2

	package tab2xml.antlr; 
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DrumTabParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CYMBALS=1, DRUMS=2, TYPE=3, BAR=4, HYPHEN=5, SPACE=6, NEWLINE=7, MULTI_COMMENT=8, 
		LINE_COMMENT=9;
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_line = 2, RULE_lineItems = 3, RULE_drumType = 4, 
		RULE_cymbal = 5, RULE_drum = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"sheet", "staff", "line", "lineItems", "drumType", "cymbal", "drum"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CYMBALS", "DRUMS", "TYPE", "BAR", "HYPHEN", "SPACE", "NEWLINE", 
			"MULTI_COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "DrumTab.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DrumTabParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SheetContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(DrumTabParser.EOF, 0); }
		public List<StaffContext> staff() {
			return getRuleContexts(StaffContext.class);
		}
		public StaffContext staff(int i) {
			return getRuleContext(StaffContext.class,i);
		}
		public SheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sheet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterSheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitSheet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitSheet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SheetContext sheet() throws RecognitionException {
		SheetContext _localctx = new SheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE) | (1L << BAR) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(14);
				staff();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
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

	public static class StaffContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(DrumTabParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DrumTabParser.NEWLINE, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public StaffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterStaff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitStaff(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitStaff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaffContext staff() throws RecognitionException {
		StaffContext _localctx = new StaffContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_staff);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(22);
				match(NEWLINE);
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(28);
					line();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(31); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(33);
					match(NEWLINE);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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

	public static class LineContext extends ParserRuleContext {
		public DrumTypeContext drumType() {
			return getRuleContext(DrumTypeContext.class,0);
		}
		public LineItemsContext lineItems() {
			return getRuleContext(LineItemsContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(DrumTabParser.NEWLINE, 0); }
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_line);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			drumType();
			setState(40);
			lineItems();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(41);
				match(SPACE);
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(47);
				match(NEWLINE);
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

	public static class LineItemsContext extends ParserRuleContext {
		public List<TerminalNode> BAR() { return getTokens(DrumTabParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(DrumTabParser.BAR, i);
		}
		public List<TerminalNode> HYPHEN() { return getTokens(DrumTabParser.HYPHEN); }
		public TerminalNode HYPHEN(int i) {
			return getToken(DrumTabParser.HYPHEN, i);
		}
		public List<CymbalContext> cymbal() {
			return getRuleContexts(CymbalContext.class);
		}
		public CymbalContext cymbal(int i) {
			return getRuleContext(CymbalContext.class,i);
		}
		public List<DrumContext> drum() {
			return getRuleContexts(DrumContext.class);
		}
		public DrumContext drum(int i) {
			return getRuleContext(DrumContext.class,i);
		}
		public LineItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lineItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterLineItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitLineItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitLineItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineItemsContext lineItems() throws RecognitionException {
		LineItemsContext _localctx = new LineItemsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lineItems);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(54); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(54);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case HYPHEN:
						{
						setState(50);
						match(HYPHEN);
						}
						break;
					case CYMBALS:
						{
						setState(51);
						cymbal();
						}
						break;
					case DRUMS:
						{
						setState(52);
						drum();
						}
						break;
					case BAR:
						{
						setState(53);
						match(BAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(56); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(58);
			match(BAR);
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

	public static class DrumTypeContext extends ParserRuleContext {
		public TerminalNode BAR() { return getToken(DrumTabParser.BAR, 0); }
		public TerminalNode TYPE() { return getToken(DrumTabParser.TYPE, 0); }
		public DrumTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drumType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterDrumType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitDrumType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitDrumType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DrumTypeContext drumType() throws RecognitionException {
		DrumTypeContext _localctx = new DrumTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_drumType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(60);
				match(TYPE);
				}
			}

			setState(63);
			match(BAR);
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

	public static class CymbalContext extends ParserRuleContext {
		public TerminalNode CYMBALS() { return getToken(DrumTabParser.CYMBALS, 0); }
		public CymbalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cymbal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterCymbal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitCymbal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitCymbal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CymbalContext cymbal() throws RecognitionException {
		CymbalContext _localctx = new CymbalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cymbal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(CYMBALS);
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

	public static class DrumContext extends ParserRuleContext {
		public TerminalNode DRUMS() { return getToken(DrumTabParser.DRUMS, 0); }
		public DrumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterDrum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitDrum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitDrum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DrumContext drum() throws RecognitionException {
		DrumContext _localctx = new DrumContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(DRUMS);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13H\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\2\3\2\3\3\7\3\32\n\3\f\3\16\3\35\13\3\3\3\6\3 \n\3\r\3\16\3!\3"+
		"\3\7\3%\n\3\f\3\16\3(\13\3\3\4\3\4\3\4\7\4-\n\4\f\4\16\4\60\13\4\3\4\5"+
		"\4\63\n\4\3\5\3\5\3\5\3\5\6\59\n\5\r\5\16\5:\3\5\3\5\3\6\5\6@\n\6\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\b\2\2\t\2\4\6\b\n\f\16\2\2\2K\2\23\3\2\2\2\4\33"+
		"\3\2\2\2\6)\3\2\2\2\b8\3\2\2\2\n?\3\2\2\2\fC\3\2\2\2\16E\3\2\2\2\20\22"+
		"\5\4\3\2\21\20\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\26"+
		"\3\2\2\2\25\23\3\2\2\2\26\27\7\2\2\3\27\3\3\2\2\2\30\32\7\t\2\2\31\30"+
		"\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\37\3\2\2\2\35\33"+
		"\3\2\2\2\36 \5\6\4\2\37\36\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\""+
		"&\3\2\2\2#%\7\t\2\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\5\3\2\2"+
		"\2(&\3\2\2\2)*\5\n\6\2*.\5\b\5\2+-\7\b\2\2,+\3\2\2\2-\60\3\2\2\2.,\3\2"+
		"\2\2./\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\61\63\7\t\2\2\62\61\3\2\2\2\62"+
		"\63\3\2\2\2\63\7\3\2\2\2\649\7\7\2\2\659\5\f\7\2\669\5\16\b\2\679\7\6"+
		"\2\28\64\3\2\2\28\65\3\2\2\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2"+
		"\2:;\3\2\2\2;<\3\2\2\2<=\7\6\2\2=\t\3\2\2\2>@\7\5\2\2?>\3\2\2\2?@\3\2"+
		"\2\2@A\3\2\2\2AB\7\6\2\2B\13\3\2\2\2CD\7\3\2\2D\r\3\2\2\2EF\7\4\2\2F\17"+
		"\3\2\2\2\13\23\33!&.\628:?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}