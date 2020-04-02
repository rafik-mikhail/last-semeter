// Generated from /home/fico/Documents/GUC/last/1002/task 4/Fdfa.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FdfaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		A=1, B=2, C=3, D=4, WS=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"A", "B", "C", "D", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "A", "B", "C", "D", "WS"
	};
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


	public FdfaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Fdfa.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0:
			A_action((RuleContext)_localctx, actionIndex);
			break;
		case 1:
			B_action((RuleContext)_localctx, actionIndex);
			break;
		case 2:
			C_action((RuleContext)_localctx, actionIndex);
			break;
		case 3:
			D_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void A_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			System.out.println("01");
			break;
		}
	}
	private void B_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			System.out.println("01");
			break;
		}
	}
	private void C_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			System.out.println("10");
			break;
		}
	}
	private void D_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			System.out.println("10");
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7i\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\17\n\2\f\2\16\2\22\13\2\3\2\6\2"+
		"\25\n\2\r\2\16\2\26\3\2\3\2\3\3\7\3\34\n\3\f\3\16\3\37\13\3\3\3\6\3\""+
		"\n\3\r\3\16\3#\3\3\3\3\6\3(\n\3\r\3\16\3)\3\3\6\3-\n\3\r\3\16\3.\6\3\61"+
		"\n\3\r\3\16\3\62\3\3\3\3\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\6\4>\n\4\r\4"+
		"\16\4?\3\4\3\4\3\4\3\4\3\5\7\5G\n\5\f\5\16\5J\13\5\3\5\6\5M\n\5\r\5\16"+
		"\5N\3\5\3\5\6\5S\n\5\r\5\16\5T\3\5\6\5X\n\5\r\5\16\5Y\3\5\6\5]\n\5\r\5"+
		"\16\5^\3\5\3\5\3\6\6\6d\n\6\r\6\16\6e\3\6\3\6\2\2\7\3\3\5\4\7\5\t\6\13"+
		"\7\3\2\3\3\2\f\f\2w\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\3\20\3\2\2\2\5\60\3\2\2\2\79\3\2\2\2\t\\\3\2\2\2\13c\3\2\2"+
		"\2\r\17\7\62\2\2\16\r\3\2\2\2\17\22\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2"+
		"\2\21\24\3\2\2\2\22\20\3\2\2\2\23\25\7\63\2\2\24\23\3\2\2\2\25\26\3\2"+
		"\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\30\3\2\2\2\30\31\b\2\2\2\31\4\3\2"+
		"\2\2\32\34\7\62\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3"+
		"\2\2\2\36!\3\2\2\2\37\35\3\2\2\2 \"\7\63\2\2! \3\2\2\2\"#\3\2\2\2#!\3"+
		"\2\2\2#$\3\2\2\2$%\3\2\2\2%\'\7\62\2\2&(\7\62\2\2\'&\3\2\2\2()\3\2\2\2"+
		")\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+-\7\63\2\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2"+
		"\2./\3\2\2\2/\61\3\2\2\2\60\35\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\64\3\2\2\2\64\65\b\3\3\2\65\6\3\2\2\2\668\7\62\2\2\67\66"+
		"\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<>\7\63\2"+
		"\2=<\3\2\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB\7\62\2\2BC\3\2"+
		"\2\2CD\b\4\4\2D\b\3\2\2\2EG\7\62\2\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3"+
		"\2\2\2IL\3\2\2\2JH\3\2\2\2KM\7\63\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2\2\2NO"+
		"\3\2\2\2OP\3\2\2\2PR\7\62\2\2QS\7\62\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2"+
		"TU\3\2\2\2UW\3\2\2\2VX\7\63\2\2WV\3\2\2\2XY\3\2\2\2YW\3\2\2\2YZ\3\2\2"+
		"\2Z[\3\2\2\2[]\7\62\2\2\\H\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3"+
		"\2\2\2`a\b\5\5\2a\n\3\2\2\2bd\t\2\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef"+
		"\3\2\2\2fg\3\2\2\2gh\b\6\6\2h\f\3\2\2\2\22\2\20\26\35#).\629?HNTY^e\7"+
		"\3\2\2\3\3\3\3\4\4\3\5\5\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}