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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, NOTE=7, FRET_NUM=8, DOUBLEBAR=9, 
		BAR=10, HYPHEN=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "NOTE", "FRET_NUM", "DOUBLEBAR", 
			"BAR", "HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'g'", "'p'", "'h'", "'s'", null, null, null, "'|'", 
			"'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "NOTE", "FRET_NUM", "DOUBLEBAR", 
			"BAR", "HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21p\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\5\b\60\n\b\3\t\6\t\63\n\t\r\t\16"+
		"\t\64\3\n\5\n8\n\n\3\n\3\n\5\n<\n\n\3\n\3\n\5\n@\n\n\3\n\5\nC\n\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\7\16N\n\16\f\16\16\16Q\13\16\3\16\5"+
		"\16T\n\16\3\16\3\16\3\17\3\17\3\17\3\17\7\17\\\n\17\f\17\16\17_\13\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20j\n\20\f\20\16\20m\13"+
		"\20\3\20\3\20\3]\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21\3\2\6\4\2CIci\3\2\62;\4\2\13\13\"\"\4\2\f\f"+
		"\17\17\2y\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2"+
		"\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2\2\13)\3\2\2\2\r+\3\2\2\2\17-\3\2\2"+
		"\2\21\62\3\2\2\2\23\67\3\2\2\2\25D\3\2\2\2\27F\3\2\2\2\31J\3\2\2\2\33"+
		"O\3\2\2\2\35W\3\2\2\2\37e\3\2\2\2!\"\7]\2\2\"\4\3\2\2\2#$\7_\2\2$\6\3"+
		"\2\2\2%&\7i\2\2&\b\3\2\2\2\'(\7r\2\2(\n\3\2\2\2)*\7j\2\2*\f\3\2\2\2+,"+
		"\7u\2\2,\16\3\2\2\2-/\t\2\2\2.\60\7%\2\2/.\3\2\2\2/\60\3\2\2\2\60\20\3"+
		"\2\2\2\61\63\t\3\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3"+
		"\2\2\2\65\22\3\2\2\2\668\7,\2\2\67\66\3\2\2\2\678\3\2\2\28;\3\2\2\29<"+
		"\5\25\13\2:<\5\21\t\2;9\3\2\2\2;:\3\2\2\2<=\3\2\2\2=?\7~\2\2>@\7~\2\2"+
		"?>\3\2\2\2?@\3\2\2\2@B\3\2\2\2AC\7,\2\2BA\3\2\2\2BC\3\2\2\2C\24\3\2\2"+
		"\2DE\7~\2\2E\26\3\2\2\2FG\7/\2\2GH\3\2\2\2HI\b\f\2\2I\30\3\2\2\2JK\t\4"+
		"\2\2K\32\3\2\2\2LN\5\31\r\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS"+
		"\3\2\2\2QO\3\2\2\2RT\7\17\2\2SR\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\7\f\2\2"+
		"V\34\3\2\2\2WX\7\61\2\2XY\7,\2\2Y]\3\2\2\2Z\\\13\2\2\2[Z\3\2\2\2\\_\3"+
		"\2\2\2]^\3\2\2\2][\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7,\2\2ab\7\61\2\2bc\3"+
		"\2\2\2cd\b\17\2\2d\36\3\2\2\2ef\7\61\2\2fg\7\61\2\2gk\3\2\2\2hj\n\5\2"+
		"\2ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\b\20"+
		"\2\2o \3\2\2\2\r\2/\64\67;?BOS]k\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}