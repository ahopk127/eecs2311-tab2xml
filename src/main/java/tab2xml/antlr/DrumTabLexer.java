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
		SPACE=10, NEWLINE=11, MULTI_COMMENT=12, LINE_COMMENT=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "TYPE", "STRIKES", "ACCENTS", "BAR", "HYPHEN", 
		"SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17k\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\5\6:\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\7\f"+
		"I\n\f\f\f\16\fL\13\f\3\f\3\f\3\f\5\fQ\n\f\3\r\3\r\3\r\3\r\7\rW\n\r\f\r"+
		"\16\rZ\13\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16e\n\16\f\16\16"+
		"\16h\13\16\3\16\3\16\3X\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\3\2\b\4\2VVvv\6\2DEHHJJTU\4\2qqzz\4\2QQZZ\4\2\13"+
		"\13\"\"\4\2\f\f\17\17x\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2"+
		"\2\2\7!\3\2\2\2\t#\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2"+
		"\2\2\23A\3\2\2\2\25E\3\2\2\2\27J\3\2\2\2\31R\3\2\2\2\33`\3\2\2\2\35\36"+
		"\7%\2\2\36\4\3\2\2\2\37 \7f\2\2 \6\3\2\2\2!\"\7h\2\2\"\b\3\2\2\2#$\7i"+
		"\2\2$\n\3\2\2\2%&\7E\2\2&:\7E\2\2\'(\7U\2\2(:\7F\2\2)*\7J\2\2*:\7J\2\2"+
		"+,\7J\2\2,:\7V\2\2-.\7O\2\2.:\7V\2\2/\60\7D\2\2\60:\7F\2\2\61:\t\2\2\2"+
		"\62\63\7H\2\2\63:\7V\2\2\64:\t\3\2\2\65\66\7J\2\2\66:\7h\2\2\678\7H\2"+
		"\28:\7J\2\29%\3\2\2\29\'\3\2\2\29)\3\2\2\29+\3\2\2\29-\3\2\2\29/\3\2\2"+
		"\29\61\3\2\2\29\62\3\2\2\29\64\3\2\2\29\65\3\2\2\29\67\3\2\2\2:\f\3\2"+
		"\2\2;<\t\4\2\2<\16\3\2\2\2=>\t\5\2\2>\20\3\2\2\2?@\7~\2\2@\22\3\2\2\2"+
		"AB\7/\2\2BC\3\2\2\2CD\b\n\2\2D\24\3\2\2\2EF\t\6\2\2F\26\3\2\2\2GI\5\25"+
		"\13\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KP\3\2\2\2LJ\3\2\2\2MN\7"+
		"\17\2\2NQ\7\f\2\2OQ\7\f\2\2PM\3\2\2\2PO\3\2\2\2Q\30\3\2\2\2RS\7\61\2\2"+
		"ST\7,\2\2TX\3\2\2\2UW\13\2\2\2VU\3\2\2\2WZ\3\2\2\2XY\3\2\2\2XV\3\2\2\2"+
		"Y[\3\2\2\2ZX\3\2\2\2[\\\7,\2\2\\]\7\61\2\2]^\3\2\2\2^_\b\r\2\2_\32\3\2"+
		"\2\2`a\7\61\2\2ab\7\61\2\2bf\3\2\2\2ce\n\7\2\2dc\3\2\2\2eh\3\2\2\2fd\3"+
		"\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\b\16\2\2j\34\3\2\2\2\b\29JPXf\3"+
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