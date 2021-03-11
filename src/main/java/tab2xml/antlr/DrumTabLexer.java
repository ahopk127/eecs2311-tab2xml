// Generated from DrumTab.g4 by ANTLR 4.4

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
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, TYPE=5, STRIKES=6, ACCENTS=7, BAR=8, HYPHEN=9, 
		LSB=10, RSB=11, SPACE=12, NEWLINE=13, MULTI_COMMENT=14, LINE_COMMENT=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "TYPE", "STRIKES", "ACCENTS", "BAR", "HYPHEN", 
		"LSB", "RSB", "SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
	};


	public DrumTabLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DrumTab.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\5\6:\n\6\3\7\5\7=\n\7\3\b\5\b@\n\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\7\16O\n\16\f\16\16\16R\13\16\3\16\3\16\3\16"+
		"\5\16W\n\16\3\17\3\17\3\17\3\17\7\17]\n\17\f\17\16\17`\13\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20k\n\20\f\20\16\20n\13\20\3\20"+
		"\3\20\3^\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21\3\2\b\4\2VVvv\6\2DEHHJJTT\4\2qqzz\4\2QQZZ\4\2\13\13"+
		"\"\"\4\2\f\f\17\17\177\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2\2\139\3\2\2\2\r<\3\2\2"+
		"\2\17?\3\2\2\2\21A\3\2\2\2\23C\3\2\2\2\25G\3\2\2\2\27I\3\2\2\2\31K\3\2"+
		"\2\2\33P\3\2\2\2\35X\3\2\2\2\37f\3\2\2\2!\"\7%\2\2\"\4\3\2\2\2#$\7f\2"+
		"\2$\6\3\2\2\2%&\7h\2\2&\b\3\2\2\2\'(\7i\2\2(\n\3\2\2\2)*\7E\2\2*:\7E\2"+
		"\2+,\7U\2\2,:\7F\2\2-.\7J\2\2.:\7J\2\2/\60\7J\2\2\60:\7V\2\2\61\62\7O"+
		"\2\2\62:\7V\2\2\63\64\7D\2\2\64:\7F\2\2\65:\t\2\2\2\66\67\7H\2\2\67:\7"+
		"V\2\28:\t\3\2\29)\3\2\2\29+\3\2\2\29-\3\2\2\29/\3\2\2\29\61\3\2\2\29\63"+
		"\3\2\2\29\65\3\2\2\29\66\3\2\2\298\3\2\2\29:\3\2\2\2:\f\3\2\2\2;=\t\4"+
		"\2\2<;\3\2\2\2<=\3\2\2\2=\16\3\2\2\2>@\t\5\2\2?>\3\2\2\2?@\3\2\2\2@\20"+
		"\3\2\2\2AB\7~\2\2B\22\3\2\2\2CD\7/\2\2DE\3\2\2\2EF\b\n\2\2F\24\3\2\2\2"+
		"GH\7]\2\2H\26\3\2\2\2IJ\7_\2\2J\30\3\2\2\2KL\t\6\2\2L\32\3\2\2\2MO\5\31"+
		"\r\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QV\3\2\2\2RP\3\2\2\2ST\7\17"+
		"\2\2TW\7\f\2\2UW\7\f\2\2VS\3\2\2\2VU\3\2\2\2W\34\3\2\2\2XY\7\61\2\2YZ"+
		"\7,\2\2Z^\3\2\2\2[]\13\2\2\2\\[\3\2\2\2]`\3\2\2\2^_\3\2\2\2^\\\3\2\2\2"+
		"_a\3\2\2\2`^\3\2\2\2ab\7,\2\2bc\7\61\2\2cd\3\2\2\2de\b\17\2\2e\36\3\2"+
		"\2\2fg\7\61\2\2gh\7\61\2\2hl\3\2\2\2ik\n\7\2\2ji\3\2\2\2kn\3\2\2\2lj\3"+
		"\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\b\20\2\2p \3\2\2\2\n\29<?PV^l\3"+
		"\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}