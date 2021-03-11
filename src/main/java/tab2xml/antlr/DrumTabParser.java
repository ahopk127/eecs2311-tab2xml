// Generated from DrumTab.g4 by ANTLR 4.4

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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, TYPE=5, STRIKES=6, ACCENTS=7, BAR=8, HYPHEN=9, 
		LSB=10, RSB=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static final String[] tokenNames = {
		"<INVALID>", "'#'", "'d'", "'f'", "'g'", "TYPE", "STRIKES", "ACCENTS", 
		"'|'", "'-'", "'['", "']'", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_line = 2, RULE_lineItems = 3, RULE_drumType = 4, 
		RULE_strike = 5, RULE_accent = 6, RULE_ghost = 7, RULE_roll = 8, RULE_choke = 9, 
		RULE_flam = 10;
	public static final String[] ruleNames = {
		"sheet", "staff", "line", "lineItems", "drumType", "strike", "accent", 
		"ghost", "roll", "choke", "flam"
	};

	@Override
	public String getGrammarFileName() { return "DrumTab.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__2) | (1L << T__1) | (1L << T__0) | (1L << TYPE) | (1L << STRIKES) | (1L << ACCENTS) | (1L << BAR) | (1L << HYPHEN) | (1L << LSB) | (1L << RSB) | (1L << SPACE) | (1L << NEWLINE) | (1L << MULTI_COMMENT) | (1L << LINE_COMMENT))) != 0)) {
				{
				{
				setState(25);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(22);
						matchWildcard();
						}
						} 
					}
					setState(27);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
				}
				setState(28); staff();
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(29);
						matchWildcard();
						}
						} 
					}
					setState(34);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(40); match(EOF);
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
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(DrumTabParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(DrumTabParser.NEWLINE, i);
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
			setState(43);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(42); match(NEWLINE);
				}
			}

			setState(46); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(45); line();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(48); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(51);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(50); match(NEWLINE);
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

	public static class LineContext extends ParserRuleContext {
		public LineItemsContext lineItems() {
			return getRuleContext(LineItemsContext.class,0);
		}
		public DrumTypeContext drumType() {
			return getRuleContext(DrumTypeContext.class,0);
		}
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(DrumTabParser.NEWLINE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
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
			setState(53); drumType();
			setState(54); lineItems();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(55); match(SPACE);
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61); match(NEWLINE);
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
		public List<AccentContext> accent() {
			return getRuleContexts(AccentContext.class);
		}
		public RollContext roll(int i) {
			return getRuleContext(RollContext.class,i);
		}
		public GhostContext ghost(int i) {
			return getRuleContext(GhostContext.class,i);
		}
		public TerminalNode BAR(int i) {
			return getToken(DrumTabParser.BAR, i);
		}
		public List<RollContext> roll() {
			return getRuleContexts(RollContext.class);
		}
		public StrikeContext strike(int i) {
			return getRuleContext(StrikeContext.class,i);
		}
		public AccentContext accent(int i) {
			return getRuleContext(AccentContext.class,i);
		}
		public ChokeContext choke(int i) {
			return getRuleContext(ChokeContext.class,i);
		}
		public List<TerminalNode> BAR() { return getTokens(DrumTabParser.BAR); }
		public List<TerminalNode> HYPHEN() { return getTokens(DrumTabParser.HYPHEN); }
		public FlamContext flam(int i) {
			return getRuleContext(FlamContext.class,i);
		}
		public TerminalNode HYPHEN(int i) {
			return getToken(DrumTabParser.HYPHEN, i);
		}
		public List<FlamContext> flam() {
			return getRuleContexts(FlamContext.class);
		}
		public List<StrikeContext> strike() {
			return getRuleContexts(StrikeContext.class);
		}
		public List<GhostContext> ghost() {
			return getRuleContexts(GhostContext.class);
		}
		public List<ChokeContext> choke() {
			return getRuleContexts(ChokeContext.class);
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
			setState(71); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(71);
					switch (_input.LA(1)) {
					case HYPHEN:
						{
						setState(63); match(HYPHEN);
						}
						break;
					case STRIKES:
						{
						setState(64); strike();
						}
						break;
					case ACCENTS:
						{
						setState(65); accent();
						}
						break;
					case T__0:
						{
						setState(66); ghost();
						}
						break;
					case T__2:
						{
						setState(67); roll();
						}
						break;
					case T__3:
						{
						setState(68); choke();
						}
						break;
					case T__1:
						{
						setState(69); flam();
						}
						break;
					case BAR:
						{
						setState(70); match(BAR);
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
				setState(73); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(75); match(BAR);
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
			setState(78);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(77); match(TYPE);
				}
			}

			setState(80); match(BAR);
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

	public static class StrikeContext extends ParserRuleContext {
		public TerminalNode STRIKES() { return getToken(DrumTabParser.STRIKES, 0); }
		public StrikeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strike; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterStrike(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitStrike(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitStrike(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrikeContext strike() throws RecognitionException {
		StrikeContext _localctx = new StrikeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_strike);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(STRIKES);
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

	public static class AccentContext extends ParserRuleContext {
		public TerminalNode ACCENTS() { return getToken(DrumTabParser.ACCENTS, 0); }
		public AccentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterAccent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitAccent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitAccent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccentContext accent() throws RecognitionException {
		AccentContext _localctx = new AccentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_accent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(ACCENTS);
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

	public static class GhostContext extends ParserRuleContext {
		public GhostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ghost; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterGhost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitGhost(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitGhost(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GhostContext ghost() throws RecognitionException {
		GhostContext _localctx = new GhostContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ghost);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(T__0);
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

	public static class RollContext extends ParserRuleContext {
		public RollContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roll; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterRoll(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitRoll(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitRoll(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RollContext roll() throws RecognitionException {
		RollContext _localctx = new RollContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_roll);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(T__2);
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

	public static class ChokeContext extends ParserRuleContext {
		public ChokeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_choke; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterChoke(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitChoke(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitChoke(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChokeContext choke() throws RecognitionException {
		ChokeContext _localctx = new ChokeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_choke);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); match(T__3);
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

	public static class FlamContext extends ParserRuleContext {
		public FlamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterFlam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitFlam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitFlam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlamContext flam() throws RecognitionException {
		FlamContext _localctx = new FlamContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_flam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92); match(T__1);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21a\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\7\2!\n\2\f\2\16\2$\13\2"+
		"\7\2&\n\2\f\2\16\2)\13\2\3\2\3\2\3\3\5\3.\n\3\3\3\6\3\61\n\3\r\3\16\3"+
		"\62\3\3\5\3\66\n\3\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\6\5J\n\5\r\5\16\5K\3\5\3\5\3\6\5\6Q\n\6\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\4\33\"\2\r\2"+
		"\4\6\b\n\f\16\20\22\24\26\2\2e\2\'\3\2\2\2\4-\3\2\2\2\6\67\3\2\2\2\bI"+
		"\3\2\2\2\nP\3\2\2\2\fT\3\2\2\2\16V\3\2\2\2\20X\3\2\2\2\22Z\3\2\2\2\24"+
		"\\\3\2\2\2\26^\3\2\2\2\30\32\13\2\2\2\31\30\3\2\2\2\32\35\3\2\2\2\33\34"+
		"\3\2\2\2\33\31\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36\"\5\4\3\2\37!\13"+
		"\2\2\2 \37\3\2\2\2!$\3\2\2\2\"#\3\2\2\2\" \3\2\2\2#&\3\2\2\2$\"\3\2\2"+
		"\2%\33\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+"+
		"\7\2\2\3+\3\3\2\2\2,.\7\17\2\2-,\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/\61\5\6"+
		"\4\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2"+
		"\2\64\66\7\17\2\2\65\64\3\2\2\2\65\66\3\2\2\2\66\5\3\2\2\2\678\5\n\6\2"+
		"8<\5\b\5\29;\7\16\2\2:9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2"+
		"\2><\3\2\2\2?@\7\17\2\2@\7\3\2\2\2AJ\7\13\2\2BJ\5\f\7\2CJ\5\16\b\2DJ\5"+
		"\20\t\2EJ\5\22\n\2FJ\5\24\13\2GJ\5\26\f\2HJ\7\n\2\2IA\3\2\2\2IB\3\2\2"+
		"\2IC\3\2\2\2ID\3\2\2\2IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2JK\3\2\2"+
		"\2KI\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\n\2\2N\t\3\2\2\2OQ\7\7\2\2PO\3\2"+
		"\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\n\2\2S\13\3\2\2\2TU\7\b\2\2U\r\3\2\2\2VW"+
		"\7\t\2\2W\17\3\2\2\2XY\7\6\2\2Y\21\3\2\2\2Z[\7\4\2\2[\23\3\2\2\2\\]\7"+
		"\3\2\2]\25\3\2\2\2^_\7\5\2\2_\27\3\2\2\2\f\33\"\'-\62\65<IKP";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}