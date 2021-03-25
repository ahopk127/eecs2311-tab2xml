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
		CYMBALS=1, DRUMS=2, TYPE=3, BAR=4, HYPHEN=5, SPACE=6, NEWLINE=7, MULTI_COMMENT=8, 
		LINE_COMMENT=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CYMBALS", "DRUMS", "TYPE", "BAR", "HYPHEN", "SPACE", "NEWLINE", "MULTI_COMMENT", 
			"LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "CYMBALS", "DRUMS", "TYPE", "BAR", "HYPHEN", "SPACE", "NEWLINE", 
			"MULTI_COMMENT", "LINE_COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13v\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4K"+
		"\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\7\bT\n\b\f\b\16\bW\13\b\3\b\5\bZ\n\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\7\tb\n\t\f\t\16\te\13\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\7\np\n\n\f\n\16\ns\13\n\3\n\3\n\3c\2\13\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\3\2\6\7\2%%ZZdeqqzz\6\2QQffhiqq\4\2\13\13\""+
		"\"\4\2\f\f\17\17\2\u0091\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\3\25"+
		"\3\2\2\2\5\27\3\2\2\2\7J\3\2\2\2\tL\3\2\2\2\13N\3\2\2\2\rP\3\2\2\2\17"+
		"U\3\2\2\2\21]\3\2\2\2\23k\3\2\2\2\25\26\t\2\2\2\26\4\3\2\2\2\27\30\t\3"+
		"\2\2\30\6\3\2\2\2\31\32\7D\2\2\32K\7f\2\2\33\34\7D\2\2\34K\7F\2\2\35\36"+
		"\7U\2\2\36K\7U\2\2\37 \7U\2\2 K\7F\2\2!\"\7G\2\2\"K\7U\2\2#$\7V\2\2$K"+
		"\7\63\2\2%&\7E\2\2&K\7J\2\2\'(\7V\2\2(K\7\64\2\2)*\7R\2\2*K\7J\2\2+,\7"+
		"N\2\2,K\7V\2\2-.\7J\2\2.K\7J\2\2/\60\7N\2\2\60K\7O\2\2\61\62\7O\2\2\62"+
		"K\7V\2\2\63\64\7E\2\2\64K\7e\2\2\65\66\7J\2\2\66K\7V\2\2\678\7T\2\28K"+
		"\7f\2\29:\7E\2\2:K\7j\2\2;<\7T\2\2<K\7D\2\2=K\7V\2\2>?\7U\2\2?K\7E\2\2"+
		"@A\7E\2\2AK\7D\2\2BC\7E\2\2CK\7E\2\2DE\7T\2\2EK\7F\2\2FG\7J\2\2GK\7E\2"+
		"\2HI\7N\2\2IK\7E\2\2J\31\3\2\2\2J\33\3\2\2\2J\35\3\2\2\2J\37\3\2\2\2J"+
		"!\3\2\2\2J#\3\2\2\2J%\3\2\2\2J\'\3\2\2\2J)\3\2\2\2J+\3\2\2\2J-\3\2\2\2"+
		"J/\3\2\2\2J\61\3\2\2\2J\63\3\2\2\2J\65\3\2\2\2J\67\3\2\2\2J9\3\2\2\2J"+
		";\3\2\2\2J=\3\2\2\2J>\3\2\2\2J@\3\2\2\2JB\3\2\2\2JD\3\2\2\2JF\3\2\2\2"+
		"JH\3\2\2\2K\b\3\2\2\2LM\7~\2\2M\n\3\2\2\2NO\7/\2\2O\f\3\2\2\2PQ\t\4\2"+
		"\2Q\16\3\2\2\2RT\5\r\7\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2"+
		"\2\2WU\3\2\2\2XZ\7\17\2\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\7\f\2\2\\\20"+
		"\3\2\2\2]^\7\61\2\2^_\7,\2\2_c\3\2\2\2`b\13\2\2\2a`\3\2\2\2be\3\2\2\2"+
		"cd\3\2\2\2ca\3\2\2\2df\3\2\2\2ec\3\2\2\2fg\7,\2\2gh\7\61\2\2hi\3\2\2\2"+
		"ij\b\t\2\2j\22\3\2\2\2kl\7\61\2\2lm\7\61\2\2mq\3\2\2\2np\n\5\2\2on\3\2"+
		"\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\b\n\2\2u\24\3"+
		"\2\2\2\b\2JUYcq\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}