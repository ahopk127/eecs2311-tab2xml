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
		public List<TerminalNode> NEWLINE() { return getTokens(GuitarTabParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(GuitarTabParser.NEWLINE, i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << NOTE) | (1L << BAR) | (1L << HYPHEN) | (1L << FRET_NUM) | (1L << SPACE) | (1L << NEWLINE) | (1L << MULTI_COMMENT) | (1L << LINE_COMMENT))) != 0)) {
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
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(28);
						match(NEWLINE);
						}
						} 
					}
					setState(33);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(34);
				staff();
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(35);
						matchWildcard();
						}
						} 
					}
					setState(40);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
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
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(54);
				match(NEWLINE);
				}
			}

			setState(58); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(57);
					string();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(60); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(62);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			tune();
			setState(66);
			stringItems();
			setState(70);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(67);
					match(SPACE);
					}
					} 
				}
				setState(72);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(73);
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
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOTE) {
				{
				setState(76);
				match(NOTE);
				}
			}

			setState(79);
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
			setState(98); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(98);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case HYPHEN:
						{
						setState(82); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(81);
								match(HYPHEN);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(84); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case T__3:
					case BAR:
					case FRET_NUM:
						{
						setState(94);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__3 || _la==FRET_NUM) {
							{
							setState(92);
							_errHandler.sync(this);
							switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
							case 1:
								{
								setState(86);
								fret();
								}
								break;
							case 2:
								{
								setState(87);
								harmonic();
								}
								break;
							case 3:
								{
								setState(88);
								hampullchain();
								}
								break;
							case 4:
								{
								setState(89);
								hammeron();
								}
								break;
							case 5:
								{
								setState(90);
								pulloff();
								}
								break;
							case 6:
								{
								setState(91);
								slide();
								}
								break;
							}
							}
							setState(96);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(97);
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
				setState(100); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		enterRule(_localctx, 10, RULE_slide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			fret();
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(103);
				match(T__0);
				setState(104);
				fret();
				}
				}
				setState(107); 
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
			setState(109);
			fret();
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110);
				match(T__1);
				setState(111);
				fret();
				}
				}
				setState(114); 
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
			setState(116);
			fret();
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(117);
				match(T__2);
				setState(118);
				fret();
				}
				}
				setState(121); 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GuitarTabVisitor ) return ((GuitarTabVisitor<? extends T>)visitor).visitHammerPull(this);
			else return visitor.visitChildren(this);
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
			setState(123);
			fret();
			setState(124);
			_la = _input.LA(1);
			if ( !(_la==T__1 || _la==T__2) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(129); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(125);
					fret();
					setState(126);
					_la = _input.LA(1);
					if ( !(_la==T__1 || _la==T__2) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(127);
					fret();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(131); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
			setState(133);
			match(T__3);
			setState(134);
			fret();
			setState(135);
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
			setState(137);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\u008e\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\7\2 \n\2\f\2\16\2#\13"+
		"\2\3\2\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\7\2"+
		"\62\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\5\3:\n\3\3\3\6\3=\n\3\r\3\16\3>\3"+
		"\3\5\3B\n\3\3\4\3\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\5\4M\n\4\3\5\5\5P"+
		"\n\5\3\5\3\5\3\6\6\6U\n\6\r\6\16\6V\3\6\3\6\3\6\3\6\3\6\3\6\7\6_\n\6\f"+
		"\6\16\6b\13\6\3\6\6\6e\n\6\r\6\16\6f\3\7\3\7\3\7\6\7l\n\7\r\7\16\7m\3"+
		"\b\3\b\3\b\6\bs\n\b\r\b\16\bt\3\t\3\t\3\t\6\tz\n\t\r\t\16\t{\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\6\n\u0084\n\n\r\n\16\n\u0085\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\4\33(\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\4\5\2\u009a\2\63\3"+
		"\2\2\2\49\3\2\2\2\6C\3\2\2\2\bO\3\2\2\2\nd\3\2\2\2\fh\3\2\2\2\16o\3\2"+
		"\2\2\20v\3\2\2\2\22}\3\2\2\2\24\u0087\3\2\2\2\26\u008b\3\2\2\2\30\32\13"+
		"\2\2\2\31\30\3\2\2\2\32\35\3\2\2\2\33\34\3\2\2\2\33\31\3\2\2\2\34!\3\2"+
		"\2\2\35\33\3\2\2\2\36 \7\r\2\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2!\""+
		"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$(\5\4\3\2%\'\13\2\2\2&%\3\2\2\2\'*\3\2\2"+
		"\2()\3\2\2\2(&\3\2\2\2).\3\2\2\2*(\3\2\2\2+-\7\r\2\2,+\3\2\2\2-\60\3\2"+
		"\2\2.,\3\2\2\2./\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\61\33\3\2\2\2\62\65\3"+
		"\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66\67\7"+
		"\2\2\3\67\3\3\2\2\28:\7\r\2\298\3\2\2\29:\3\2\2\2:<\3\2\2\2;=\5\6\4\2"+
		"<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@B\7\r\2\2A@\3\2\2\2"+
		"AB\3\2\2\2B\5\3\2\2\2CD\5\b\5\2DH\5\n\6\2EG\7\f\2\2FE\3\2\2\2GJ\3\2\2"+
		"\2HF\3\2\2\2HI\3\2\2\2IL\3\2\2\2JH\3\2\2\2KM\7\r\2\2LK\3\2\2\2LM\3\2\2"+
		"\2M\7\3\2\2\2NP\7\b\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7\t\2\2R\t\3\2"+
		"\2\2SU\7\n\2\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2We\3\2\2\2X_\5\26"+
		"\f\2Y_\5\24\13\2Z_\5\22\n\2[_\5\20\t\2\\_\5\16\b\2]_\5\f\7\2^X\3\2\2\2"+
		"^Y\3\2\2\2^Z\3\2\2\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2"+
		"\2`a\3\2\2\2ac\3\2\2\2b`\3\2\2\2ce\7\t\2\2dT\3\2\2\2d`\3\2\2\2ef\3\2\2"+
		"\2fd\3\2\2\2fg\3\2\2\2g\13\3\2\2\2hk\5\26\f\2ij\7\3\2\2jl\5\26\f\2ki\3"+
		"\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\r\3\2\2\2or\5\26\f\2pq\7\4\2\2q"+
		"s\5\26\f\2rp\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\17\3\2\2\2vy\5\26"+
		"\f\2wx\7\5\2\2xz\5\26\f\2yw\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\21"+
		"\3\2\2\2}~\5\26\f\2~\u0083\t\2\2\2\177\u0080\5\26\f\2\u0080\u0081\t\2"+
		"\2\2\u0081\u0082\5\26\f\2\u0082\u0084\3\2\2\2\u0083\177\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\23\3\2\2"+
		"\2\u0087\u0088\7\6\2\2\u0088\u0089\5\26\f\2\u0089\u008a\7\7\2\2\u008a"+
		"\25\3\2\2\2\u008b\u008c\7\13\2\2\u008c\27\3\2\2\2\26\33!(.\639>AHLOV^"+
		"`dfmt{\u0085";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}