// Generated from tab2xml\antlr\DrumTab.g4 by ANTLR 4.9.2

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
		DRUMS=1, CYMBALS=2, DRUMTYPE=3, CYMBALTYPE=4, DOUBLEBAR=5, BAR=6, HYPHEN=7, 
		SPACE=8, NEWLINE=9, MULTI_COMMENT=10, LINE_COMMENT=11;
	public static final int
		RULE_sheet = 0, RULE_staff = 1, RULE_drumLine = 2, RULE_cymbalLine = 3, 
		RULE_drumActions = 4, RULE_cymbalActions = 5, RULE_drumType = 6, RULE_cymbalType = 7, 
		RULE_drum = 8, RULE_cymbal = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"sheet", "staff", "drumLine", "cymbalLine", "drumActions", "cymbalActions", 
			"drumType", "cymbalType", "drum", "cymbal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DRUMS", "CYMBALS", "DRUMTYPE", "CYMBALTYPE", "DOUBLEBAR", "BAR", 
			"HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DRUMTYPE) | (1L << CYMBALTYPE) | (1L << SPACE) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(20);
				staff();
				}
				}
				setState(25);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(26);
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
		public List<CymbalLineContext> cymbalLine() {
			return getRuleContexts(CymbalLineContext.class);
		}
		public CymbalLineContext cymbalLine(int i) {
			return getRuleContext(CymbalLineContext.class,i);
		}
		public List<DrumLineContext> drumLine() {
			return getRuleContexts(DrumLineContext.class);
		}
		public DrumLineContext drumLine(int i) {
			return getRuleContext(DrumLineContext.class,i);
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
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(28);
				match(NEWLINE);
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(36);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(34);
						cymbalLine();
						}
						break;
					case 2:
						{
						setState(35);
						drumLine();
						}
						break;
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(38); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(43);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(40);
					match(NEWLINE);
					}
					} 
				}
				setState(45);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class DrumLineContext extends ParserRuleContext {
		public DrumTypeContext drumType() {
			return getRuleContext(DrumTypeContext.class,0);
		}
		public DrumActionsContext drumActions() {
			return getRuleContext(DrumActionsContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(DrumTabParser.NEWLINE, 0); }
		public DrumLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drumLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterDrumLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitDrumLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitDrumLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DrumLineContext drumLine() throws RecognitionException {
		DrumLineContext _localctx = new DrumLineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_drumLine);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(46);
				match(SPACE);
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			drumType();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(53);
				match(SPACE);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			drumActions();
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60);
					match(SPACE);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(66);
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

	public static class CymbalLineContext extends ParserRuleContext {
		public CymbalTypeContext cymbalType() {
			return getRuleContext(CymbalTypeContext.class,0);
		}
		public CymbalActionsContext cymbalActions() {
			return getRuleContext(CymbalActionsContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
		public TerminalNode NEWLINE() { return getToken(DrumTabParser.NEWLINE, 0); }
		public CymbalLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cymbalLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterCymbalLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitCymbalLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitCymbalLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CymbalLineContext cymbalLine() throws RecognitionException {
		CymbalLineContext _localctx = new CymbalLineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cymbalLine);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(69);
				match(SPACE);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			cymbalType();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(76);
				match(SPACE);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			cymbalActions();
			setState(86);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(83);
					match(SPACE);
					}
					} 
				}
				setState(88);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(89);
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

	public static class DrumActionsContext extends ParserRuleContext {
		public List<TerminalNode> BAR() { return getTokens(DrumTabParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(DrumTabParser.BAR, i);
		}
		public List<TerminalNode> DOUBLEBAR() { return getTokens(DrumTabParser.DOUBLEBAR); }
		public TerminalNode DOUBLEBAR(int i) {
			return getToken(DrumTabParser.DOUBLEBAR, i);
		}
		public List<DrumContext> drum() {
			return getRuleContexts(DrumContext.class);
		}
		public DrumContext drum(int i) {
			return getRuleContext(DrumContext.class,i);
		}
		public List<TerminalNode> HYPHEN() { return getTokens(DrumTabParser.HYPHEN); }
		public TerminalNode HYPHEN(int i) {
			return getToken(DrumTabParser.HYPHEN, i);
		}
		public DrumActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drumActions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterDrumActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitDrumActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitDrumActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DrumActionsContext drumActions() throws RecognitionException {
		DrumActionsContext _localctx = new DrumActionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_drumActions);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(96);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case DRUMS:
						{
						setState(92);
						drum();
						}
						break;
					case BAR:
						{
						setState(93);
						match(BAR);
						}
						break;
					case DOUBLEBAR:
						{
						setState(94);
						match(DOUBLEBAR);
						}
						break;
					case HYPHEN:
						{
						setState(95);
						match(HYPHEN);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(101);
			_la = _input.LA(1);
			if ( !(_la==DOUBLEBAR || _la==BAR) ) {
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

	public static class CymbalActionsContext extends ParserRuleContext {
		public List<TerminalNode> BAR() { return getTokens(DrumTabParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(DrumTabParser.BAR, i);
		}
		public List<TerminalNode> DOUBLEBAR() { return getTokens(DrumTabParser.DOUBLEBAR); }
		public TerminalNode DOUBLEBAR(int i) {
			return getToken(DrumTabParser.DOUBLEBAR, i);
		}
		public List<CymbalContext> cymbal() {
			return getRuleContexts(CymbalContext.class);
		}
		public CymbalContext cymbal(int i) {
			return getRuleContext(CymbalContext.class,i);
		}
		public List<TerminalNode> HYPHEN() { return getTokens(DrumTabParser.HYPHEN); }
		public TerminalNode HYPHEN(int i) {
			return getToken(DrumTabParser.HYPHEN, i);
		}
		public CymbalActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cymbalActions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterCymbalActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitCymbalActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitCymbalActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CymbalActionsContext cymbalActions() throws RecognitionException {
		CymbalActionsContext _localctx = new CymbalActionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cymbalActions);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(107);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case CYMBALS:
						{
						setState(103);
						cymbal();
						}
						break;
					case BAR:
						{
						setState(104);
						match(BAR);
						}
						break;
					case DOUBLEBAR:
						{
						setState(105);
						match(DOUBLEBAR);
						}
						break;
					case HYPHEN:
						{
						setState(106);
						match(HYPHEN);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			setState(112);
			_la = _input.LA(1);
			if ( !(_la==DOUBLEBAR || _la==BAR) ) {
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

	public static class DrumTypeContext extends ParserRuleContext {
		public TerminalNode DRUMTYPE() { return getToken(DrumTabParser.DRUMTYPE, 0); }
		public TerminalNode BAR() { return getToken(DrumTabParser.BAR, 0); }
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
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
		enterRule(_localctx, 12, RULE_drumType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(DRUMTYPE);
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(115);
				match(SPACE);
				}
				break;
			}
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(118);
				match(SPACE);
				}
				break;
			}
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(121);
				match(SPACE);
				}
			}

			setState(124);
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

	public static class CymbalTypeContext extends ParserRuleContext {
		public TerminalNode CYMBALTYPE() { return getToken(DrumTabParser.CYMBALTYPE, 0); }
		public TerminalNode BAR() { return getToken(DrumTabParser.BAR, 0); }
		public List<TerminalNode> SPACE() { return getTokens(DrumTabParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(DrumTabParser.SPACE, i);
		}
		public CymbalTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cymbalType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).enterCymbalType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DrumTabListener ) ((DrumTabListener)listener).exitCymbalType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DrumTabVisitor ) return ((DrumTabVisitor<? extends T>)visitor).visitCymbalType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CymbalTypeContext cymbalType() throws RecognitionException {
		CymbalTypeContext _localctx = new CymbalTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cymbalType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(CYMBALTYPE);
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(127);
				match(SPACE);
				}
				break;
			}
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(130);
				match(SPACE);
				}
				break;
			}
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(133);
				match(SPACE);
				}
			}

			setState(136);
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
		enterRule(_localctx, 16, RULE_drum);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
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
		enterRule(_localctx, 18, RULE_cymbal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\r\u0091\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\7\2\30\n\2\f\2\16\2\33\13\2\3\2\3\2\3\3\7\3 \n\3\f\3\16\3#\13"+
		"\3\3\3\3\3\6\3\'\n\3\r\3\16\3(\3\3\7\3,\n\3\f\3\16\3/\13\3\3\4\7\4\62"+
		"\n\4\f\4\16\4\65\13\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\3\4\3\4\7\4@\n\4"+
		"\f\4\16\4C\13\4\3\4\5\4F\n\4\3\5\7\5I\n\5\f\5\16\5L\13\5\3\5\3\5\7\5P"+
		"\n\5\f\5\16\5S\13\5\3\5\3\5\7\5W\n\5\f\5\16\5Z\13\5\3\5\5\5]\n\5\3\6\3"+
		"\6\3\6\3\6\7\6c\n\6\f\6\16\6f\13\6\3\6\3\6\3\7\3\7\3\7\3\7\7\7n\n\7\f"+
		"\7\16\7q\13\7\3\7\3\7\3\b\3\b\5\bw\n\b\3\b\5\bz\n\b\3\b\5\b}\n\b\3\b\3"+
		"\b\3\t\3\t\5\t\u0083\n\t\3\t\5\t\u0086\n\t\3\t\5\t\u0089\n\t\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\3\3\2\7\b\2\u00a1"+
		"\2\31\3\2\2\2\4!\3\2\2\2\6\63\3\2\2\2\bJ\3\2\2\2\nd\3\2\2\2\fo\3\2\2\2"+
		"\16t\3\2\2\2\20\u0080\3\2\2\2\22\u008c\3\2\2\2\24\u008e\3\2\2\2\26\30"+
		"\5\4\3\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\34"+
		"\3\2\2\2\33\31\3\2\2\2\34\35\7\2\2\3\35\3\3\2\2\2\36 \7\13\2\2\37\36\3"+
		"\2\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"&\3\2\2\2#!\3\2\2\2$\'\5\b\5"+
		"\2%\'\5\6\4\2&$\3\2\2\2&%\3\2\2\2\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)-\3\2"+
		"\2\2*,\7\13\2\2+*\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\5\3\2\2\2/-\3"+
		"\2\2\2\60\62\7\n\2\2\61\60\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3"+
		"\2\2\2\64\66\3\2\2\2\65\63\3\2\2\2\66:\5\16\b\2\679\7\n\2\28\67\3\2\2"+
		"\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=A\5\n\6\2>@\7\n\2"+
		"\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BE\3\2\2\2CA\3\2\2\2DF\7\13"+
		"\2\2ED\3\2\2\2EF\3\2\2\2F\7\3\2\2\2GI\7\n\2\2HG\3\2\2\2IL\3\2\2\2JH\3"+
		"\2\2\2JK\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MQ\5\20\t\2NP\7\n\2\2ON\3\2\2\2PS"+
		"\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TX\5\f\7\2UW\7\n\2\2V"+
		"U\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2[]\7\13\2"+
		"\2\\[\3\2\2\2\\]\3\2\2\2]\t\3\2\2\2^c\5\22\n\2_c\7\b\2\2`c\7\7\2\2ac\7"+
		"\t\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3"+
		"\2\2\2eg\3\2\2\2fd\3\2\2\2gh\t\2\2\2h\13\3\2\2\2in\5\24\13\2jn\7\b\2\2"+
		"kn\7\7\2\2ln\7\t\2\2mi\3\2\2\2mj\3\2\2\2mk\3\2\2\2ml\3\2\2\2nq\3\2\2\2"+
		"om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\t\2\2\2s\r\3\2\2\2tv\7\5\2"+
		"\2uw\7\n\2\2vu\3\2\2\2vw\3\2\2\2wy\3\2\2\2xz\7\n\2\2yx\3\2\2\2yz\3\2\2"+
		"\2z|\3\2\2\2{}\7\n\2\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\7\b\2\2\177"+
		"\17\3\2\2\2\u0080\u0082\7\6\2\2\u0081\u0083\7\n\2\2\u0082\u0081\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0086\7\n\2\2\u0085\u0084"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0089\7\n\2\2\u0088"+
		"\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\7\b"+
		"\2\2\u008b\21\3\2\2\2\u008c\u008d\7\3\2\2\u008d\23\3\2\2\2\u008e\u008f"+
		"\7\4\2\2\u008f\25\3\2\2\2\31\31!&(-\63:AEJQX\\bdmovy|\u0082\u0085\u0088";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}