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
		DRUMS=1, CYMBALS=2, DRUMTYPE=3, CYMBALTYPE=4, BAR=5, HYPHEN=6, SPACE=7, 
		NEWLINE=8, MULTI_COMMENT=9, LINE_COMMENT=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DRUMS", "CYMBALS", "DRUMTYPE", "CYMBALTYPE", "BAR", "HYPHEN", "SPACE", 
			"NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DRUMS", "CYMBALS", "DRUMTYPE", "CYMBALTYPE", "BAR", "HYPHEN", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\fy\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\66\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5L\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\7\tW\n\t\f\t\16\t"+
		"Z\13\t\3\t\5\t]\n\t\3\t\3\t\3\n\3\n\3\n\3\n\7\ne\n\n\f\n\16\nh\13\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13s\n\13\f\13\16\13v\13\13\3"+
		"\13\3\13\3f\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\3\2\6\t"+
		"\2BBDDQQddffhiqq\b\2%%ZZdeqruuzz\4\2\13\13\"\"\4\2\f\f\17\17\2\u0091\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5"+
		"\31\3\2\2\2\7\65\3\2\2\2\tK\3\2\2\2\13M\3\2\2\2\rO\3\2\2\2\17S\3\2\2\2"+
		"\21X\3\2\2\2\23`\3\2\2\2\25n\3\2\2\2\27\30\t\2\2\2\30\4\3\2\2\2\31\32"+
		"\t\3\2\2\32\6\3\2\2\2\33\34\7D\2\2\34\66\7F\2\2\35\36\7D\2\2\36\66\7f"+
		"\2\2\37 \7U\2\2 \66\7U\2\2!\"\7U\2\2\"\66\7F\2\2#$\7G\2\2$\66\7U\2\2%"+
		"&\7H\2\2&\66\7V\2\2\'(\7H\2\2(\66\7v\2\2)*\7N\2\2*\66\7V\2\2+,\7N\2\2"+
		",\66\7O\2\2-.\7O\2\2.\66\7V\2\2/\60\7J\2\2\60\66\7V\2\2\61\62\7V\2\2\62"+
		"\66\7C\2\2\63\64\7E\2\2\64\66\7D\2\2\65\33\3\2\2\2\65\35\3\2\2\2\65\37"+
		"\3\2\2\2\65!\3\2\2\2\65#\3\2\2\2\65%\3\2\2\2\65\'\3\2\2\2\65)\3\2\2\2"+
		"\65+\3\2\2\2\65-\3\2\2\2\65/\3\2\2\2\65\61\3\2\2\2\65\63\3\2\2\2\66\b"+
		"\3\2\2\2\678\7J\2\28L\7J\2\29:\7R\2\2:L\7J\2\2;<\7Q\2\2<L\7J\2\2=>\7E"+
		"\2\2>L\7E\2\2?@\7T\2\2@L\7F\2\2AB\7E\2\2BL\7j\2\2CD\7T\2\2DL\7D\2\2EF"+
		"\7U\2\2FL\7E\2\2GH\7E\2\2HL\7e\2\2IJ\7T\2\2JL\7f\2\2K\67\3\2\2\2K9\3\2"+
		"\2\2K;\3\2\2\2K=\3\2\2\2K?\3\2\2\2KA\3\2\2\2KC\3\2\2\2KE\3\2\2\2KG\3\2"+
		"\2\2KI\3\2\2\2L\n\3\2\2\2MN\7~\2\2N\f\3\2\2\2OP\7/\2\2PQ\3\2\2\2QR\b\7"+
		"\2\2R\16\3\2\2\2ST\t\4\2\2T\20\3\2\2\2UW\5\17\b\2VU\3\2\2\2WZ\3\2\2\2"+
		"XV\3\2\2\2XY\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2[]\7\17\2\2\\[\3\2\2\2\\]\3\2"+
		"\2\2]^\3\2\2\2^_\7\f\2\2_\22\3\2\2\2`a\7\61\2\2ab\7,\2\2bf\3\2\2\2ce\13"+
		"\2\2\2dc\3\2\2\2eh\3\2\2\2fg\3\2\2\2fd\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7"+
		",\2\2jk\7\61\2\2kl\3\2\2\2lm\b\n\2\2m\24\3\2\2\2no\7\61\2\2op\7\61\2\2"+
		"pt\3\2\2\2qs\n\5\2\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2"+
		"vt\3\2\2\2wx\b\13\2\2x\26\3\2\2\2\t\2\65KX\\ft\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}