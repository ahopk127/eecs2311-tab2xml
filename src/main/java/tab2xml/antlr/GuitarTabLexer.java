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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NOTE=6, BAR=7, HYPHEN=8, FRET_NUM=9, 
		SPACE=10, NEWLINE=11, MULTI_COMMENT=12, LINE_COMMENT=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "NOTE", "BAR", "HYPHEN", "FRET_NUM", 
			"SPACE", "NEWLINE", "MULTI_COMMENT", "LINE_COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'s'", "'p'", "'h'", "'['", "']'", null, "'|'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "NOTE", "BAR", "HYPHEN", "FRET_NUM", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17\\\b\1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\5\7*\n\7\3\b\3\b\3\t\3\t\3\t\3\t\3\n\5\n\63\n\n\3\n\3\n\3\13"+
		"\3\13\3\f\7\f:\n\f\f\f\16\f=\13\f\3\f\3\f\3\f\5\fB\n\f\3\r\3\r\3\r\3\r"+
		"\7\rH\n\r\f\r\16\rK\13\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16"+
		"V\n\16\f\16\16\16Y\13\16\3\16\3\16\3I\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\3\2\7\3\2CI\3\2\63\64\3\2\62;\4\2"+
		"\13\13\"\"\4\2\f\f\17\17\2a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37"+
		"\3\2\2\2\7!\3\2\2\2\t#\3\2\2\2\13%\3\2\2\2\r\'\3\2\2\2\17+\3\2\2\2\21"+
		"-\3\2\2\2\23\62\3\2\2\2\25\66\3\2\2\2\27;\3\2\2\2\31C\3\2\2\2\33Q\3\2"+
		"\2\2\35\36\7u\2\2\36\4\3\2\2\2\37 \7r\2\2 \6\3\2\2\2!\"\7j\2\2\"\b\3\2"+
		"\2\2#$\7]\2\2$\n\3\2\2\2%&\7_\2\2&\f\3\2\2\2\')\t\2\2\2(*\7%\2\2)(\3\2"+
		"\2\2)*\3\2\2\2*\16\3\2\2\2+,\7~\2\2,\20\3\2\2\2-.\7/\2\2./\3\2\2\2/\60"+
		"\b\t\2\2\60\22\3\2\2\2\61\63\t\3\2\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64"+
		"\3\2\2\2\64\65\t\4\2\2\65\24\3\2\2\2\66\67\t\5\2\2\67\26\3\2\2\28:\5\25"+
		"\13\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<A\3\2\2\2=;\3\2\2\2>?\7"+
		"\17\2\2?B\7\f\2\2@B\7\f\2\2A>\3\2\2\2A@\3\2\2\2B\30\3\2\2\2CD\7\61\2\2"+
		"DE\7,\2\2EI\3\2\2\2FH\13\2\2\2GF\3\2\2\2HK\3\2\2\2IJ\3\2\2\2IG\3\2\2\2"+
		"JL\3\2\2\2KI\3\2\2\2LM\7,\2\2MN\7\61\2\2NO\3\2\2\2OP\b\r\2\2P\32\3\2\2"+
		"\2QR\7\61\2\2RS\7\61\2\2SW\3\2\2\2TV\n\6\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2"+
		"\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\b\16\2\2[\34\3\2\2\2\t\2)\62;AIW"+
		"\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}