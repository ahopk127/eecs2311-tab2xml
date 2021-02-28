// Generated from GuitarTab.g4 by ANTLR 4.4

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
public class GuitarTabParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, NOTE=6, BAR=7, HYPHEN=8, FRET_NUM=9, 
		SPACE=10, NEWLINE=11, MULTI_COMMENT=12, LINE_COMMENT=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'p'", "'s'", "'h'", "'['", "']'", "NOTE", "'|'", "'-'", 
		"FRET_NUM", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_string = 2, RULE_tune = 3, RULE_stringItems = 4, 
		RULE_slide = 5, RULE_pulloff = 6, RULE_hammeron = 7, RULE_hampullchain = 8, 
		RULE_harmonic = 9, RULE_fret = 10;
	public static final String[] ruleNames = {
		"sheet", "staff", "string", "tune", "stringItems", "slide", "pulloff", 
		"hammeron", "hampullchain", "harmonic", "fret"
	};

	@Override
	public String getGrammarFileName() { return "GuitarTab.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GuitarTabParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SheetContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(GuitarTabParser.EOF, 0); }
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
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterSheet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitSheet(this);
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__3) | (1L << T__2) | (1L << T__1) | (1L << T__0) | (1L << NOTE) | (1L << BAR) | (1L << HYPHEN) | (1L << FRET_NUM) | (1L << SPACE) | (1L << NEWLINE) | (1L << MULTI_COMMENT) | (1L << LINE_COMMENT))) != 0)) {
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
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(GuitarTabParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GuitarTabParser.NEWLINE, i);
		}
		public StaffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterStaff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitStaff(this);
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
					setState(45); string();
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

	public static class StringContext extends ParserRuleContext {
		public StringItemsContext stringItems() {
			return getRuleContext(StringItemsContext.class,0);
		}
		public TuneContext tune() {
			return getRuleContext(TuneContext.class,0);
		}
		public TerminalNode SPACE(int i) {
			return getToken(GuitarTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(GuitarTabParser.NEWLINE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(GuitarTabParser.SPACE); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); tune();
			setState(54); stringItems();
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

	public static class TuneContext extends ParserRuleContext {
		public TerminalNode BAR() { return getToken(GuitarTabParser.BAR, 0); }
		public TerminalNode NOTE() { return getToken(GuitarTabParser.NOTE, 0); }
		public TuneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tune; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterTune(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitTune(this);
		}
	}

	public final TuneContext tune() throws RecognitionException {
		TuneContext _localctx = new TuneContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tune);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if (_la==NOTE) {
				{
				setState(63); match(NOTE);
				}
			}

			setState(66); match(BAR);
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

	public static class StringItemsContext extends ParserRuleContext {
		public List<PulloffContext> pulloff() {
			return getRuleContexts(PulloffContext.class);
		}
		public TerminalNode BAR(int i) {
			return getToken(GuitarTabParser.BAR, i);
		}
		public List<HarmonicContext> harmonic() {
			return getRuleContexts(HarmonicContext.class);
		}
		public List<HampullchainContext> hampullchain() {
			return getRuleContexts(HampullchainContext.class);
		}
		public List<TerminalNode> BAR() { return getTokens(GuitarTabParser.BAR); }
		public List<TerminalNode> HYPHEN() { return getTokens(GuitarTabParser.HYPHEN); }
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public SlideContext slide(int i) {
			return getRuleContext(SlideContext.class,i);
		}
		public HarmonicContext harmonic(int i) {
			return getRuleContext(HarmonicContext.class,i);
		}
		public HampullchainContext hampullchain(int i) {
			return getRuleContext(HampullchainContext.class,i);
		}
		public TerminalNode HYPHEN(int i) {
			return getToken(GuitarTabParser.HYPHEN, i);
		}
		public List<HammeronContext> hammeron() {
			return getRuleContexts(HammeronContext.class);
		}
		public List<SlideContext> slide() {
			return getRuleContexts(SlideContext.class);
		}
		public PulloffContext pulloff(int i) {
			return getRuleContext(PulloffContext.class,i);
		}
		public HammeronContext hammeron(int i) {
			return getRuleContext(HammeronContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public StringItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterStringItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitStringItems(this);
		}
	}

	public final StringItemsContext stringItems() throws RecognitionException {
		StringItemsContext _localctx = new StringItemsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stringItems);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(85);
				switch (_input.LA(1)) {
				case HYPHEN:
					{
					setState(69); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(68); match(HYPHEN);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(71); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case T__1:
				case BAR:
				case FRET_NUM:
					{
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1 || _la==FRET_NUM) {
						{
						setState(79);
						switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
						case 1:
							{
							setState(73); fret();
							}
							break;
						case 2:
							{
							setState(74); harmonic();
							}
							break;
						case 3:
							{
							setState(75); hampullchain();
							}
							break;
						case 4:
							{
							setState(76); hammeron();
							}
							break;
						case 5:
							{
							setState(77); pulloff();
							}
							break;
						case 6:
							{
							setState(78); slide();
							}
							break;
						}
						}
						setState(83);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(84); match(BAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << BAR) | (1L << HYPHEN) | (1L << FRET_NUM))) != 0) );
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

	public static class SlideContext extends ParserRuleContext {
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public SlideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterSlide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitSlide(this);
		}
	}

	public final SlideContext slide() throws RecognitionException {
		SlideContext _localctx = new SlideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_slide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); fret();
			setState(92); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90); match(T__3);
				setState(91); fret();
				}
				}
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
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

	public static class PulloffContext extends ParserRuleContext {
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public PulloffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pulloff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterPulloff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitPulloff(this);
		}
	}

	public final PulloffContext pulloff() throws RecognitionException {
		PulloffContext _localctx = new PulloffContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pulloff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); fret();
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97); match(T__4);
				setState(98); fret();
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
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

	public static class HammeronContext extends ParserRuleContext {
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public HammeronContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hammeron; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterHammeron(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitHammeron(this);
		}
	}

	public final HammeronContext hammeron() throws RecognitionException {
		HammeronContext _localctx = new HammeronContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_hammeron);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); fret();
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104); match(T__2);
				setState(105); fret();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
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

	public static class HampullchainContext extends ParserRuleContext {
		public HampullchainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hampullchain; }
	 
		public HampullchainContext() { }
		public void copyFrom(HampullchainContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class HammerPullContext extends HampullchainContext {
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public HammerPullContext(HampullchainContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterHammerPull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitHammerPull(this);
		}
	}

	public final HampullchainContext hampullchain() throws RecognitionException {
		HampullchainContext _localctx = new HampullchainContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_hampullchain);
		int _la;
		try {
			int _alt;
			_localctx = new HammerPullContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(110); fret();
			setState(111);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(116); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(112); fret();
					setState(113);
					_la = _input.LA(1);
					if ( !(_la==T__4 || _la==T__2) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(114); fret();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(118); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class HarmonicContext extends ParserRuleContext {
		public FretContext fret() {
			return getRuleContext(FretContext.class,0);
		}
		public HarmonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_harmonic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterHarmonic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitHarmonic(this);
		}
	}

	public final HarmonicContext harmonic() throws RecognitionException {
		HarmonicContext _localctx = new HarmonicContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_harmonic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(T__1);
			setState(121); fret();
			setState(122); match(T__0);
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

	public static class FretContext extends ParserRuleContext {
		public TerminalNode FRET_NUM() { return getToken(GuitarTabParser.FRET_NUM, 0); }
		public FretContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fret; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).enterFret(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GuitarTabListener ) ((GuitarTabListener)listener).exitFret(this);
		}
	}

	public final FretContext fret() throws RecognitionException {
		FretContext _localctx = new FretContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fret);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(FRET_NUM);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17\u0081\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\7\2!\n\2\f\2\16"+
		"\2$\13\2\7\2&\n\2\f\2\16\2)\13\2\3\2\3\2\3\3\5\3.\n\3\3\3\6\3\61\n\3\r"+
		"\3\16\3\62\3\3\5\3\66\n\3\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\4\3\4"+
		"\3\5\5\5C\n\5\3\5\3\5\3\6\6\6H\n\6\r\6\16\6I\3\6\3\6\3\6\3\6\3\6\3\6\7"+
		"\6R\n\6\f\6\16\6U\13\6\3\6\6\6X\n\6\r\6\16\6Y\3\7\3\7\3\7\6\7_\n\7\r\7"+
		"\16\7`\3\b\3\b\3\b\6\bf\n\b\r\b\16\bg\3\t\3\t\3\t\6\tm\n\t\r\t\16\tn\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\6\nw\n\n\r\n\16\nx\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\4\33\"\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\4\2\3\3\5\5\u008a\2\'\3"+
		"\2\2\2\4-\3\2\2\2\6\67\3\2\2\2\bB\3\2\2\2\nW\3\2\2\2\f[\3\2\2\2\16b\3"+
		"\2\2\2\20i\3\2\2\2\22p\3\2\2\2\24z\3\2\2\2\26~\3\2\2\2\30\32\13\2\2\2"+
		"\31\30\3\2\2\2\32\35\3\2\2\2\33\34\3\2\2\2\33\31\3\2\2\2\34\36\3\2\2\2"+
		"\35\33\3\2\2\2\36\"\5\4\3\2\37!\13\2\2\2 \37\3\2\2\2!$\3\2\2\2\"#\3\2"+
		"\2\2\" \3\2\2\2#&\3\2\2\2$\"\3\2\2\2%\33\3\2\2\2&)\3\2\2\2\'%\3\2\2\2"+
		"\'(\3\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\7\2\2\3+\3\3\2\2\2,.\7\r\2\2-,\3\2"+
		"\2\2-.\3\2\2\2.\60\3\2\2\2/\61\5\6\4\2\60/\3\2\2\2\61\62\3\2\2\2\62\60"+
		"\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\66\7\r\2\2\65\64\3\2\2\2\65\66"+
		"\3\2\2\2\66\5\3\2\2\2\678\5\b\5\28<\5\n\6\29;\7\f\2\2:9\3\2\2\2;>\3\2"+
		"\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\r\2\2@\7\3\2\2\2AC\7"+
		"\b\2\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2DE\7\t\2\2E\t\3\2\2\2FH\7\n\2\2GF"+
		"\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JX\3\2\2\2KR\5\26\f\2LR\5\24\13"+
		"\2MR\5\22\n\2NR\5\20\t\2OR\5\16\b\2PR\5\f\7\2QK\3\2\2\2QL\3\2\2\2QM\3"+
		"\2\2\2QN\3\2\2\2QO\3\2\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3"+
		"\2\2\2US\3\2\2\2VX\7\t\2\2WG\3\2\2\2WS\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3"+
		"\2\2\2Z\13\3\2\2\2[^\5\26\f\2\\]\7\4\2\2]_\5\26\f\2^\\\3\2\2\2_`\3\2\2"+
		"\2`^\3\2\2\2`a\3\2\2\2a\r\3\2\2\2be\5\26\f\2cd\7\3\2\2df\5\26\f\2ec\3"+
		"\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\17\3\2\2\2il\5\26\f\2jk\7\5\2\2"+
		"km\5\26\f\2lj\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o\21\3\2\2\2pq\5\26"+
		"\f\2qv\t\2\2\2rs\5\26\f\2st\t\2\2\2tu\5\26\f\2uw\3\2\2\2vr\3\2\2\2wx\3"+
		"\2\2\2xv\3\2\2\2xy\3\2\2\2y\23\3\2\2\2z{\7\6\2\2{|\5\26\f\2|}\7\7\2\2"+
		"}\25\3\2\2\2~\177\7\13\2\2\177\27\3\2\2\2\23\33\"\'-\62\65<BIQSWY`gnx";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}