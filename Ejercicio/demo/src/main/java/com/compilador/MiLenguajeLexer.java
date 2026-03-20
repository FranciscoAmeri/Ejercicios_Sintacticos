// Generated from com\compilador\MiLenguaje.g4 by ANTLR 4.9.3
package com.compilador;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiLenguajeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUM=1, RES=2, MUL=3, DIV=4, MOD=5, ID=6, INTEGER=7, DECIMAL=8, CHARACTER=9, 
		COMENTARIO_LINEA=10, COMENTARIO_BLOQUE=11, WS=12, OTRO=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LETRA", "DIGITO", "SUM", "RES", "MUL", "DIV", "MOD", "ID", "INTEGER", 
			"DECIMAL", "CHARACTER", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "WS", 
			"OTRO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'%'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SUM", "RES", "MUL", "DIV", "MOD", "ID", "INTEGER", "DECIMAL", 
			"CHARACTER", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "WS", "OTRO"
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


	public MiLenguajeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiLenguaje.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17k\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\5\t\62\n\t\3\t\3\t\3\t\7"+
		"\t\67\n\t\f\t\16\t:\13\t\3\n\6\n=\n\n\r\n\16\n>\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\5\fI\n\f\3\f\3\f\3\r\3\r\3\r\3\r\7\rQ\n\r\f\r\16\rT\13"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\\\n\16\f\16\16\16_\13\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3]\2\21\3\2\5\2\7\3\t\4"+
		"\13\5\r\6\17\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17\3\2\7\4\2C\\"+
		"c|\3\2\62;\5\2\f\f\17\17))\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2p\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t\'\3\2\2\2\13)\3"+
		"\2\2\2\r+\3\2\2\2\17-\3\2\2\2\21\61\3\2\2\2\23<\3\2\2\2\25@\3\2\2\2\27"+
		"D\3\2\2\2\31L\3\2\2\2\33W\3\2\2\2\35e\3\2\2\2\37i\3\2\2\2!\"\t\2\2\2\""+
		"\4\3\2\2\2#$\t\3\2\2$\6\3\2\2\2%&\7-\2\2&\b\3\2\2\2\'(\7/\2\2(\n\3\2\2"+
		"\2)*\7,\2\2*\f\3\2\2\2+,\7\61\2\2,\16\3\2\2\2-.\7\'\2\2.\20\3\2\2\2/\62"+
		"\5\3\2\2\60\62\7a\2\2\61/\3\2\2\2\61\60\3\2\2\2\628\3\2\2\2\63\67\5\3"+
		"\2\2\64\67\5\5\3\2\65\67\7a\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2"+
		"\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\22\3\2\2\2:8\3\2\2\2;=\5\5\3\2"+
		"<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?\24\3\2\2\2@A\5\23\n\2AB\7\60"+
		"\2\2BC\5\23\n\2C\26\3\2\2\2DH\7)\2\2EI\n\4\2\2FG\7^\2\2GI\13\2\2\2HE\3"+
		"\2\2\2HF\3\2\2\2IJ\3\2\2\2JK\7)\2\2K\30\3\2\2\2LM\7\61\2\2MN\7\61\2\2"+
		"NR\3\2\2\2OQ\n\5\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2"+
		"TR\3\2\2\2UV\b\r\2\2V\32\3\2\2\2WX\7\61\2\2XY\7,\2\2Y]\3\2\2\2Z\\\13\2"+
		"\2\2[Z\3\2\2\2\\_\3\2\2\2]^\3\2\2\2][\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\7"+
		",\2\2ab\7\61\2\2bc\3\2\2\2cd\b\16\2\2d\34\3\2\2\2ef\t\6\2\2fg\3\2\2\2"+
		"gh\b\17\2\2h\36\3\2\2\2ij\13\2\2\2j \3\2\2\2\n\2\61\668>HR]\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}