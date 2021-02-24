// Generated from GuitarTab.g4 by ANTLR 4.9.1

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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NOTE=6, BAR=7, HYPHEN=8, FRET_NUM=9, 
		SPACE=10, NEWLINE=11, MULTI_COMMENT=12, LINE_COMMENT=13;
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_string = 2, RULE_tune = 3, RULE_stringItems = 4, 
		RULE_slide = 5, RULE_pulloff = 6, RULE_hammeron = 7, RULE_hampullchain = 8, 
		RULE_harmonic = 9, RULE_fret = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"sheet", "staff", "string", "tune", "stringItems", "slide", "pulloff", 
			"hammeron", "hampullchain", "harmonic", "fret"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'s'", "'p'", "'h'", "'['", "']'", null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "NOTE", "BAR", "HYPHEN", "FRET_NUM", 
			"SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
	public String getGrammarFileName() { return "GuitarTab.g4"; }

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
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOTE) | (1L << BAR) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(22);
				staff();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
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
		public List<TerminalNode> NEWLINE() { return getTokens(GuitarTabParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GuitarTabParser.NEWLINE, i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(30);
				match(NEWLINE);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(36);
					string();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(39); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(41);
					match(NEWLINE);
					}
					} 
				}
				setState(46);
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

	public static class StringContext extends ParserRuleContext {
		public TuneContext tune() {
			return getRuleContext(TuneContext.class,0);
		}
		public StringItemsContext stringItems() {
			return getRuleContext(StringItemsContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(GuitarTabParser.NEWLINE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(GuitarTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(GuitarTabParser.SPACE, i);
		}
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
			setState(47);
			tune();
			setState(48);
			stringItems();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(49);
				match(SPACE);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			match(NEWLINE);
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
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOTE) {
				{
				setState(57);
				match(NOTE);
				}
			}

			setState(60);
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

	public static class StringItemsContext extends ParserRuleContext {
		public List<TerminalNode> BAR() { return getTokens(GuitarTabParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(GuitarTabParser.BAR, i);
		}
		public List<TerminalNode> HYPHEN() { return getTokens(GuitarTabParser.HYPHEN); }
		public TerminalNode HYPHEN(int i) {
			return getToken(GuitarTabParser.HYPHEN, i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
		}
		public List<HarmonicContext> harmonic() {
			return getRuleContexts(HarmonicContext.class);
		}
		public HarmonicContext harmonic(int i) {
			return getRuleContext(HarmonicContext.class,i);
		}
		public List<HampullchainContext> hampullchain() {
			return getRuleContexts(HampullchainContext.class);
		}
		public HampullchainContext hampullchain(int i) {
			return getRuleContext(HampullchainContext.class,i);
		}
		public List<HammeronContext> hammeron() {
			return getRuleContexts(HammeronContext.class);
		}
		public HammeronContext hammeron(int i) {
			return getRuleContext(HammeronContext.class,i);
		}
		public List<PulloffContext> pulloff() {
			return getRuleContexts(PulloffContext.class);
		}
		public PulloffContext pulloff(int i) {
			return getRuleContext(PulloffContext.class,i);
		}
		public List<SlideContext> slide() {
			return getRuleContexts(SlideContext.class);
		}
		public SlideContext slide(int i) {
			return getRuleContext(SlideContext.class,i);
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
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(79);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case HYPHEN:
					{
					setState(63); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(62);
							match(HYPHEN);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(65); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					break;
				case T__3:
				case BAR:
				case FRET_NUM:
					{
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__3 || _la==FRET_NUM) {
						{
						setState(73);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(67);
							fret();
							}
							break;
						case 2:
							{
							setState(68);
							harmonic();
							}
							break;
						case 3:
							{
							setState(69);
							hampullchain();
							}
							break;
						case 4:
							{
							setState(70);
							hammeron();
							}
							break;
						case 5:
							{
							setState(71);
							pulloff();
							}
							break;
						case 6:
							{
							setState(72);
							slide();
							}
							break;
						}
						}
						setState(77);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(78);
					match(BAR);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << BAR) | (1L << HYPHEN) | (1L << FRET_NUM))) != 0) );
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
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
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
			setState(83);
			fret();
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				match(T__0);
				setState(85);
				fret();
				}
				}
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
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
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
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
			setState(90);
			fret();
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(91);
				match(T__1);
				setState(92);
				fret();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
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
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
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
			setState(97);
			fret();
			setState(100); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(98);
				match(T__2);
				setState(99);
				fret();
				}
				}
				setState(102); 
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
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
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
			_localctx = new HammerPullContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			fret();
			setState(107); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(106);
				fret();
				}
				}
				setState(109); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 || _la==T__2 );
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
			setState(111);
			match(T__3);
			setState(112);
			fret();
			setState(113);
			match(T__4);
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
			setState(115);
			match(FRET_NUM);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17x\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\7\3\"\n\3\f\3\16\3"+
		"%\13\3\3\3\6\3(\n\3\r\3\16\3)\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\4\3\4\3"+
		"\4\7\4\65\n\4\f\4\16\48\13\4\3\4\3\4\3\5\5\5=\n\5\3\5\3\5\3\6\6\6B\n\6"+
		"\r\6\16\6C\3\6\3\6\3\6\3\6\3\6\3\6\7\6L\n\6\f\6\16\6O\13\6\3\6\6\6R\n"+
		"\6\r\6\16\6S\3\7\3\7\3\7\6\7Y\n\7\r\7\16\7Z\3\b\3\b\3\b\6\b`\n\b\r\b\16"+
		"\ba\3\t\3\t\3\t\6\tg\n\t\r\t\16\th\3\n\3\n\3\n\6\nn\n\n\r\n\16\no\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\4"+
		"\5\2\177\2\33\3\2\2\2\4#\3\2\2\2\6\61\3\2\2\2\b<\3\2\2\2\nQ\3\2\2\2\f"+
		"U\3\2\2\2\16\\\3\2\2\2\20c\3\2\2\2\22j\3\2\2\2\24q\3\2\2\2\26u\3\2\2\2"+
		"\30\32\5\4\3\2\31\30\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2"+
		"\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\2\2\3\37\3\3\2\2\2 \"\7\r\2\2! \3"+
		"\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\'\3\2\2\2%#\3\2\2\2&(\5\6\4\2\'"+
		"&\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*.\3\2\2\2+-\7\r\2\2,+\3\2\2\2"+
		"-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\62\5\b\5\2"+
		"\62\66\5\n\6\2\63\65\7\f\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66"+
		"\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7\r\2\2:\7\3\2\2\2;=\7\b\2\2<;\3"+
		"\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\t\2\2?\t\3\2\2\2@B\7\n\2\2A@\3\2\2\2BC"+
		"\3\2\2\2CA\3\2\2\2CD\3\2\2\2DR\3\2\2\2EL\5\26\f\2FL\5\24\13\2GL\5\22\n"+
		"\2HL\5\20\t\2IL\5\16\b\2JL\5\f\7\2KE\3\2\2\2KF\3\2\2\2KG\3\2\2\2KH\3\2"+
		"\2\2KI\3\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2"+
		"\2\2PR\7\t\2\2QA\3\2\2\2QM\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T\13\3"+
		"\2\2\2UX\5\26\f\2VW\7\3\2\2WY\5\26\f\2XV\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z"+
		"[\3\2\2\2[\r\3\2\2\2\\_\5\26\f\2]^\7\4\2\2^`\5\26\f\2_]\3\2\2\2`a\3\2"+
		"\2\2a_\3\2\2\2ab\3\2\2\2b\17\3\2\2\2cf\5\26\f\2de\7\5\2\2eg\5\26\f\2f"+
		"d\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\21\3\2\2\2jm\5\26\f\2kl\t\2\2"+
		"\2ln\5\26\f\2mk\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2p\23\3\2\2\2qr\7"+
		"\6\2\2rs\5\26\f\2st\7\7\2\2t\25\3\2\2\2uv\7\13\2\2v\27\3\2\2\2\21\33#"+
		").\66<CKMQSZaho";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}