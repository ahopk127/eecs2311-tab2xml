// Generated from tab2xml\antlr\DrumTab.g4 by ANTLR 4.9.2

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
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DRUMS=1, CYMBALS=2, DRUMTYPE=3, CYMBALTYPE=4, DOUBLEBAR=5, BAR=6, HYPHEN=7, 
		SPACE=8, NEWLINE=9, MULTI_COMMENT=10, LINE_COMMENT=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DRUMS", "CYMBALS", "DRUMTYPE", "CYMBALTYPE", "DOUBLEBAR", "BAR", "HYPHEN", 
			"SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\r\u008d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\48"+
		"\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5N\n\5\3\6\5\6Q\n\6\3\6\3\6\6\6U\n\6\r\6\16\6V\5\6Y\n"+
		"\6\3\6\3\6\5\6]\n\6\3\6\5\6`\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\n\7"+
		"\nk\n\n\f\n\16\nn\13\n\3\n\5\nq\n\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13y"+
		"\n\13\f\13\16\13|\13\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u0087"+
		"\n\f\f\f\16\f\u008a\13\f\3\f\3\f\3z\2\r\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\3\2\7\t\2BBDDQQddffhiqq\b\2%%ZZdeqruuzz\3\2\62;"+
		"\4\2\13\13\"\"\4\2\f\f\17\17\2\u00aa\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\5\33\3\2\2\2\7\67\3\2"+
		"\2\2\tM\3\2\2\2\13P\3\2\2\2\ra\3\2\2\2\17c\3\2\2\2\21g\3\2\2\2\23l\3\2"+
		"\2\2\25t\3\2\2\2\27\u0082\3\2\2\2\31\32\t\2\2\2\32\4\3\2\2\2\33\34\t\3"+
		"\2\2\34\6\3\2\2\2\35\36\7D\2\2\368\7F\2\2\37 \7D\2\2 8\7f\2\2!\"\7U\2"+
		"\2\"8\7U\2\2#$\7U\2\2$8\7F\2\2%&\7G\2\2&8\7U\2\2\'(\7H\2\2(8\7V\2\2)*"+
		"\7H\2\2*8\7v\2\2+,\7N\2\2,8\7V\2\2-.\7N\2\2.8\7O\2\2/\60\7O\2\2\608\7"+
		"V\2\2\61\62\7J\2\2\628\7V\2\2\63\64\7V\2\2\648\7C\2\2\65\66\7E\2\2\66"+
		"8\7D\2\2\67\35\3\2\2\2\67\37\3\2\2\2\67!\3\2\2\2\67#\3\2\2\2\67%\3\2\2"+
		"\2\67\'\3\2\2\2\67)\3\2\2\2\67+\3\2\2\2\67-\3\2\2\2\67/\3\2\2\2\67\61"+
		"\3\2\2\2\67\63\3\2\2\2\67\65\3\2\2\28\b\3\2\2\29:\7J\2\2:N\7J\2\2;<\7"+
		"R\2\2<N\7J\2\2=>\7Q\2\2>N\7J\2\2?@\7E\2\2@N\7E\2\2AB\7T\2\2BN\7F\2\2C"+
		"D\7E\2\2DN\7j\2\2EF\7T\2\2FN\7D\2\2GH\7U\2\2HN\7E\2\2IJ\7E\2\2JN\7e\2"+
		"\2KL\7T\2\2LN\7f\2\2M9\3\2\2\2M;\3\2\2\2M=\3\2\2\2M?\3\2\2\2MA\3\2\2\2"+
		"MC\3\2\2\2ME\3\2\2\2MG\3\2\2\2MI\3\2\2\2MK\3\2\2\2N\n\3\2\2\2OQ\7,\2\2"+
		"PO\3\2\2\2PQ\3\2\2\2QX\3\2\2\2RY\5\r\7\2SU\t\4\2\2TS\3\2\2\2UV\3\2\2\2"+
		"VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XR\3\2\2\2XT\3\2\2\2YZ\3\2\2\2Z\\\7~\2\2"+
		"[]\7~\2\2\\[\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^`\7,\2\2_^\3\2\2\2_`\3\2\2\2"+
		"`\f\3\2\2\2ab\7~\2\2b\16\3\2\2\2cd\7/\2\2de\3\2\2\2ef\b\b\2\2f\20\3\2"+
		"\2\2gh\t\5\2\2h\22\3\2\2\2ik\5\21\t\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm"+
		"\3\2\2\2mp\3\2\2\2nl\3\2\2\2oq\7\17\2\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2"+
		"rs\7\f\2\2s\24\3\2\2\2tu\7\61\2\2uv\7,\2\2vz\3\2\2\2wy\13\2\2\2xw\3\2"+
		"\2\2y|\3\2\2\2z{\3\2\2\2zx\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\7,\2\2~\177\7"+
		"\61\2\2\177\u0080\3\2\2\2\u0080\u0081\b\13\2\2\u0081\26\3\2\2\2\u0082"+
		"\u0083\7\61\2\2\u0083\u0084\7\61\2\2\u0084\u0088\3\2\2\2\u0085\u0087\n"+
		"\6\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\b\f"+
		"\2\2\u008c\30\3\2\2\2\16\2\67MPVX\\_lpz\u0088\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}