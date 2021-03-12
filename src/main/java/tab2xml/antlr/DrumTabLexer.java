// Generated from DrumTab.g4 by ANTLR 4.9.1

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
public class DrumTabLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, TYPE=5, STRIKES=6, ACCENTS=7, BAR=8, HYPHEN=9, 
		LSB=10, RSB=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "TYPE", "STRIKES", "ACCENTS", "BAR", 
			"HYPHEN", "LSB", "RSB", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'g'", "'d'", "'#'", "'f'", null, null, null, "'|'", "'-'", "'['", 
			"']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "TYPE", "STRIKES", "ACCENTS", "BAR", "HYPHEN", 
			"LSB", "RSB", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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


	public DrumTabLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DrumTab.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21u\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\5\6>\n\6\3\7\5\7A\n\7\3\b\5\bD\n\b\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\7\16S\n\16\f\16\16\16V\13"+
		"\16\3\16\3\16\3\16\5\16[\n\16\3\17\3\17\3\17\3\17\7\17a\n\17\f\17\16\17"+
		"d\13\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20o\n\20\f\20\16"+
		"\20r\13\20\3\20\3\20\3b\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21\3\2\b\4\2VVvv\6\2DEHHJJTU\4\2qqzz\4"+
		"\2QQZZ\4\2\13\13\"\"\4\2\f\f\17\17\2\u0085\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2\2"+
		"\13=\3\2\2\2\r@\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3\2\2\2\25K\3\2\2"+
		"\2\27M\3\2\2\2\31O\3\2\2\2\33T\3\2\2\2\35\\\3\2\2\2\37j\3\2\2\2!\"\7i"+
		"\2\2\"\4\3\2\2\2#$\7f\2\2$\6\3\2\2\2%&\7%\2\2&\b\3\2\2\2\'(\7h\2\2(\n"+
		"\3\2\2\2)*\7E\2\2*>\7E\2\2+,\7U\2\2,>\7F\2\2-.\7J\2\2.>\7J\2\2/\60\7J"+
		"\2\2\60>\7V\2\2\61\62\7O\2\2\62>\7V\2\2\63\64\7D\2\2\64>\7F\2\2\65>\t"+
		"\2\2\2\66\67\7H\2\2\67>\7V\2\28>\t\3\2\29:\7J\2\2:>\7h\2\2;<\7H\2\2<>"+
		"\7J\2\2=)\3\2\2\2=+\3\2\2\2=-\3\2\2\2=/\3\2\2\2=\61\3\2\2\2=\63\3\2\2"+
		"\2=\65\3\2\2\2=\66\3\2\2\2=8\3\2\2\2=9\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\f"+
		"\3\2\2\2?A\t\4\2\2@?\3\2\2\2@A\3\2\2\2A\16\3\2\2\2BD\t\5\2\2CB\3\2\2\2"+
		"CD\3\2\2\2D\20\3\2\2\2EF\7~\2\2F\22\3\2\2\2GH\7/\2\2HI\3\2\2\2IJ\b\n\2"+
		"\2J\24\3\2\2\2KL\7]\2\2L\26\3\2\2\2MN\7_\2\2N\30\3\2\2\2OP\t\6\2\2P\32"+
		"\3\2\2\2QS\5\31\r\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UZ\3\2\2\2"+
		"VT\3\2\2\2WX\7\17\2\2X[\7\f\2\2Y[\7\f\2\2ZW\3\2\2\2ZY\3\2\2\2[\34\3\2"+
		"\2\2\\]\7\61\2\2]^\7,\2\2^b\3\2\2\2_a\13\2\2\2`_\3\2\2\2ad\3\2\2\2bc\3"+
		"\2\2\2b`\3\2\2\2ce\3\2\2\2db\3\2\2\2ef\7,\2\2fg\7\61\2\2gh\3\2\2\2hi\b"+
		"\17\2\2i\36\3\2\2\2jk\7\61\2\2kl\7\61\2\2lp\3\2\2\2mo\n\7\2\2nm\3\2\2"+
		"\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\b\20\2\2t \3\2"+
		"\2\2\n\2=@CTZbp\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}