// Generated from GuitarTab.g4 by ANTLR 4.9.1

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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NOTE=1, BAR=2, HYPHEN=3, FRET_NUM=4, H=5, P=6, S=7, LSB=8, RSB=9, SPACE=10, 
		NEWLINE=11, MULTI_COMMENT=12, LINE_COMMENT=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NOTE", "BAR", "HYPHEN", "FRET_NUM", "H", "P", "S", "LSB", "RSB", "SPACE", 
			"NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'|'", "'-'", null, "'h'", "'p'", "'s'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NOTE", "BAR", "HYPHEN", "FRET_NUM", "H", "P", "S", "LSB", "RSB", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17Z\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\5\2 \n\2\3\3\3\3\3\4\3\4\3\5\5\5"+
		"\'\n\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\7"+
		"\f8\n\f\f\f\16\f;\13\f\3\f\5\f>\n\f\3\f\3\f\3\r\3\r\3\r\3\r\7\rF\n\r\f"+
		"\r\16\rI\13\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16T\n\16\f\16"+
		"\16\16W\13\16\3\16\3\16\3G\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\3\2\7\4\2CIci\3\2\63\64\3\2\62;\4\2\13\13\""+
		"\"\4\2\f\f\17\17\2_\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5!\3\2\2\2"+
		"\7#\3\2\2\2\t&\3\2\2\2\13*\3\2\2\2\r,\3\2\2\2\17.\3\2\2\2\21\60\3\2\2"+
		"\2\23\62\3\2\2\2\25\64\3\2\2\2\279\3\2\2\2\31A\3\2\2\2\33O\3\2\2\2\35"+
		"\37\t\2\2\2\36 \7%\2\2\37\36\3\2\2\2\37 \3\2\2\2 \4\3\2\2\2!\"\7~\2\2"+
		"\"\6\3\2\2\2#$\7/\2\2$\b\3\2\2\2%\'\t\3\2\2&%\3\2\2\2&\'\3\2\2\2\'(\3"+
		"\2\2\2()\t\4\2\2)\n\3\2\2\2*+\7j\2\2+\f\3\2\2\2,-\7r\2\2-\16\3\2\2\2."+
		"/\7u\2\2/\20\3\2\2\2\60\61\7]\2\2\61\22\3\2\2\2\62\63\7_\2\2\63\24\3\2"+
		"\2\2\64\65\t\5\2\2\65\26\3\2\2\2\668\5\25\13\2\67\66\3\2\2\28;\3\2\2\2"+
		"9\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<>\7\17\2\2=<\3\2\2\2=>\3\2"+
		"\2\2>?\3\2\2\2?@\7\f\2\2@\30\3\2\2\2AB\7\61\2\2BC\7,\2\2CG\3\2\2\2DF\13"+
		"\2\2\2ED\3\2\2\2FI\3\2\2\2GH\3\2\2\2GE\3\2\2\2HJ\3\2\2\2IG\3\2\2\2JK\7"+
		",\2\2KL\7\61\2\2LM\3\2\2\2MN\b\r\2\2N\32\3\2\2\2OP\7\61\2\2PQ\7\61\2\2"+
		"QU\3\2\2\2RT\n\6\2\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2"+
		"WU\3\2\2\2XY\b\16\2\2Y\34\3\2\2\2\t\2\37&9=GU\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}