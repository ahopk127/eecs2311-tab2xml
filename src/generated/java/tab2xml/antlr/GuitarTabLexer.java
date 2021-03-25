// Generated from tab2xml\antlr\GuitarTab.g4 by ANTLR 4.9.2

	package tab2xml.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GuitarTabLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, NOTE=7, BAR=8, DOUBLEBAR=9, 
		FRET_NUM=10, HYPHEN=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "NOTE", "BAR", "DOUBLEBAR", 
			"FRET_NUM", "HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'g'", "'p'", "'h'", "'s'", null, "'|'", null, null, 
			"'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "NOTE", "BAR", "DOUBLEBAR", 
			"FRET_NUM", "HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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


	public GuitarTabLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GuitarTab.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21k\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\5\b\60\n\b\3\t\3\t\3\n\5\n\65\n\n"+
		"\3\n\3\n\5\n9\n\n\3\n\3\n\5\n=\n\n\3\13\6\13@\n\13\r\13\16\13A\3\f\3\f"+
		"\3\r\3\r\3\16\7\16I\n\16\f\16\16\16L\13\16\3\16\5\16O\n\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\7\17W\n\17\f\17\16\17Z\13\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\7\20e\n\20\f\20\16\20h\13\20\3\20\3\20\3X\2\21"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21\3\2\6\4\2CIci\3\2\62;\4\2\13\13\"\"\4\2\f\f\17\17\2s\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2"+
		"\2\2\t\'\3\2\2\2\13)\3\2\2\2\r+\3\2\2\2\17-\3\2\2\2\21\61\3\2\2\2\23\64"+
		"\3\2\2\2\25?\3\2\2\2\27C\3\2\2\2\31E\3\2\2\2\33J\3\2\2\2\35R\3\2\2\2\37"+
		"`\3\2\2\2!\"\7]\2\2\"\4\3\2\2\2#$\7_\2\2$\6\3\2\2\2%&\7i\2\2&\b\3\2\2"+
		"\2\'(\7r\2\2(\n\3\2\2\2)*\7j\2\2*\f\3\2\2\2+,\7u\2\2,\16\3\2\2\2-/\t\2"+
		"\2\2.\60\7%\2\2/.\3\2\2\2/\60\3\2\2\2\60\20\3\2\2\2\61\62\7~\2\2\62\22"+
		"\3\2\2\2\63\65\7,\2\2\64\63\3\2\2\2\64\65\3\2\2\2\658\3\2\2\2\669\5\21"+
		"\t\2\679\5\25\13\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:<\7~\2\2;=\7,\2\2"+
		"<;\3\2\2\2<=\3\2\2\2=\24\3\2\2\2>@\t\3\2\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2"+
		"\2AB\3\2\2\2B\26\3\2\2\2CD\7/\2\2D\30\3\2\2\2EF\t\4\2\2F\32\3\2\2\2GI"+
		"\5\31\r\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2\2"+
		"MO\7\17\2\2NM\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\7\f\2\2Q\34\3\2\2\2RS\7\61"+
		"\2\2ST\7,\2\2TX\3\2\2\2UW\13\2\2\2VU\3\2\2\2WZ\3\2\2\2XY\3\2\2\2XV\3\2"+
		"\2\2Y[\3\2\2\2ZX\3\2\2\2[\\\7,\2\2\\]\7\61\2\2]^\3\2\2\2^_\b\17\2\2_\36"+
		"\3\2\2\2`a\7\61\2\2ab\7\61\2\2bf\3\2\2\2ce\n\5\2\2dc\3\2\2\2eh\3\2\2\2"+
		"fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\b\20\2\2j \3\2\2\2\f\2/\64"+
		"8<AJNXf\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}