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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, NOTE=7, BAR=8, DOUBLEBAR=9, 
		HYPHEN=10, FRET_NUM=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_string = 2, RULE_tune = 3, RULE_stringItems = 4, 
		RULE_hampullchain = 5, RULE_pulloff = 6, RULE_hammeron = 7, RULE_slide = 8, 
		RULE_harmonic = 9, RULE_fret = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"sheet", "staff", "string", "tune", "stringItems", "hampullchain", "pulloff", 
			"hammeron", "slide", "harmonic", "fret"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'g'", "'h'", "'p'", "'s'", "'['", "']'", null, "'|'", null, "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "NOTE", "BAR", "DOUBLEBAR", 
			"HYPHEN", "FRET_NUM", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitSheet(this);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitStaff(this);
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
		public List<TerminalNode> SPACE() { return getTokens(GuitarTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(GuitarTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(GuitarTabParser.NEWLINE, 0); }
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
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
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(55);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitTune(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TuneContext tune() throws RecognitionException {
		TuneContext _localctx = new TuneContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tune);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOTE) {
				{
				setState(58);
				match(NOTE);
				}
			}

			setState(61);
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
		public List<TerminalNode> DOUBLEBAR() { return getTokens(GuitarTabParser.DOUBLEBAR); }
		public TerminalNode DOUBLEBAR(int i) {
			return getToken(GuitarTabParser.DOUBLEBAR, i);
		}
		public List<TerminalNode> HYPHEN() { return getTokens(GuitarTabParser.HYPHEN); }
		public TerminalNode HYPHEN(int i) {
			return getToken(GuitarTabParser.HYPHEN, i);
		}
		public List<HampullchainContext> hampullchain() {
			return getRuleContexts(HampullchainContext.class);
		}
		public HampullchainContext hampullchain(int i) {
			return getRuleContext(HampullchainContext.class,i);
		}
		public List<PulloffContext> pulloff() {
			return getRuleContexts(PulloffContext.class);
		}
		public PulloffContext pulloff(int i) {
			return getRuleContext(PulloffContext.class,i);
		}
		public List<HammeronContext> hammeron() {
			return getRuleContexts(HammeronContext.class);
		}
		public HammeronContext hammeron(int i) {
			return getRuleContext(HammeronContext.class,i);
		}
		public List<SlideContext> slide() {
			return getRuleContexts(SlideContext.class);
		}
		public SlideContext slide(int i) {
			return getRuleContext(SlideContext.class,i);
		}
		public List<HarmonicContext> harmonic() {
			return getRuleContexts(HarmonicContext.class);
		}
		public HarmonicContext harmonic(int i) {
			return getRuleContext(HarmonicContext.class,i);
		}
		public List<FretContext> fret() {
			return getRuleContexts(FretContext.class);
		}
		public FretContext fret(int i) {
			return getRuleContext(FretContext.class,i);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitStringItems(this);
			else return visitor.visitChildren(this);
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
			setState(72); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(63);
						match(HYPHEN);
						}
						break;
					case 2:
						{
						setState(64);
						hampullchain();
						}
						break;
					case 3:
						{
						setState(65);
						pulloff();
						}
						break;
					case 4:
						{
						setState(66);
						hammeron();
						}
						break;
					case 5:
						{
						setState(67);
						slide();
						}
						break;
					case 6:
						{
						setState(68);
						harmonic();
						}
						break;
					case 7:
						{
						setState(69);
						fret();
						}
						break;
					case 8:
						{
						setState(70);
						match(BAR);
						}
						break;
					case 9:
						{
						setState(71);
						match(DOUBLEBAR);
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(74); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==BAR || _la==DOUBLEBAR) ) {
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitHammerPull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HampullchainContext hampullchain() throws RecognitionException {
		HampullchainContext _localctx = new HampullchainContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_hampullchain);
		int _la;
		try {
			_localctx = new HammerPullContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(78);
				match(T__0);
				}
			}

			setState(81);
			fret();
			setState(82);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(83);
			fret();
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(85);
				fret();
				}
				}
				setState(88); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitPulloff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PulloffContext pulloff() throws RecognitionException {
		PulloffContext _localctx = new PulloffContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pulloff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(90);
				match(T__0);
				}
			}

			setState(93);
			fret();
			setState(94);
			match(T__2);
			setState(95);
			fret();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitHammeron(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HammeronContext hammeron() throws RecognitionException {
		HammeronContext _localctx = new HammeronContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_hammeron);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(97);
				match(T__0);
				}
			}

			setState(100);
			fret();
			setState(101);
			match(T__1);
			setState(102);
			fret();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitSlide(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SlideContext slide() throws RecognitionException {
		SlideContext _localctx = new SlideContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_slide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(104);
				match(T__0);
				}
			}

			setState(107);
			fret();
			setState(108);
			match(T__3);
			setState(109);
			fret();
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitHarmonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HarmonicContext harmonic() throws RecognitionException {
		HarmonicContext _localctx = new HarmonicContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_harmonic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(T__4);
			setState(112);
			fret();
			setState(113);
			match(T__5);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitFret(this);
			else return visitor.visitChildren(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21x\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\7\3\"\n\3\f\3\16\3"+
		"%\13\3\3\3\6\3(\n\3\r\3\16\3)\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\4\3\4\3"+
		"\4\7\4\65\n\4\f\4\16\48\13\4\3\4\5\4;\n\4\3\5\5\5>\n\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6K\n\6\r\6\16\6L\3\6\3\6\3\7\5\7R\n\7\3"+
		"\7\3\7\3\7\3\7\3\7\6\7Y\n\7\r\7\16\7Z\3\b\5\b^\n\b\3\b\3\b\3\b\3\b\3\t"+
		"\5\te\n\t\3\t\3\t\3\t\3\t\3\n\5\nl\n\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\n\13\3\2\4"+
		"\5\2\u0081\2\33\3\2\2\2\4#\3\2\2\2\6\61\3\2\2\2\b=\3\2\2\2\nJ\3\2\2\2"+
		"\fQ\3\2\2\2\16]\3\2\2\2\20d\3\2\2\2\22k\3\2\2\2\24q\3\2\2\2\26u\3\2\2"+
		"\2\30\32\5\4\3\2\31\30\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2"+
		"\2\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\2\2\3\37\3\3\2\2\2 \"\7\17\2\2"+
		"! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\'\3\2\2\2%#\3\2\2\2&(\5\6\4"+
		"\2\'&\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*.\3\2\2\2+-\7\17\2\2,+\3"+
		"\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\5\3\2\2\2\60.\3\2\2\2\61\62\5"+
		"\b\5\2\62\66\5\n\6\2\63\65\7\16\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3"+
		"\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29;\7\17\2\2:9\3\2\2\2:;\3"+
		"\2\2\2;\7\3\2\2\2<>\7\t\2\2=<\3\2\2\2=>\3\2\2\2>?\3\2\2\2?@\7\n\2\2@\t"+
		"\3\2\2\2AK\7\f\2\2BK\5\f\7\2CK\5\16\b\2DK\5\20\t\2EK\5\22\n\2FK\5\24\13"+
		"\2GK\5\26\f\2HK\7\n\2\2IK\7\13\2\2JA\3\2\2\2JB\3\2\2\2JC\3\2\2\2JD\3\2"+
		"\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2"+
		"\2\2LM\3\2\2\2MN\3\2\2\2NO\t\2\2\2O\13\3\2\2\2PR\7\3\2\2QP\3\2\2\2QR\3"+
		"\2\2\2RS\3\2\2\2ST\5\26\f\2TU\t\3\2\2UX\5\26\f\2VW\t\3\2\2WY\5\26\f\2"+
		"XV\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\r\3\2\2\2\\^\7\3\2\2]\\\3\2"+
		"\2\2]^\3\2\2\2^_\3\2\2\2_`\5\26\f\2`a\7\5\2\2ab\5\26\f\2b\17\3\2\2\2c"+
		"e\7\3\2\2dc\3\2\2\2de\3\2\2\2ef\3\2\2\2fg\5\26\f\2gh\7\4\2\2hi\5\26\f"+
		"\2i\21\3\2\2\2jl\7\3\2\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\5\26\f\2no\7"+
		"\6\2\2op\5\26\f\2p\23\3\2\2\2qr\7\7\2\2rs\5\26\f\2st\7\b\2\2t\25\3\2\2"+
		"\2uv\7\r\2\2v\27\3\2\2\2\20\33#).\66:=JLQZ]dk";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}