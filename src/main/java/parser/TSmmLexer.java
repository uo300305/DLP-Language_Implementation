// Generated from C:/Users/USUARIO/Desktop/wp/3.2/DLP/DLP_LenguageImplementation/src/main/java/parser/TSmm.g4 by ANTLR 4.13.2
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TSmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRASH=1, INT_CONSTANT=2, REAL_CONSTANT=3, REAL_PART=4, MANTISA_PART=5, 
		ONE_LINE_COMMENT=6, MORE_LINES_COMMENT=7, ID=8, CHAR_CONSTANT=9, MACRO_LETTER=10, 
		MACRO_DIGIT=11;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TRASH", "INT_CONSTANT", "REAL_CONSTANT", "REAL_PART", "MANTISA_PART", 
			"ONE_LINE_COMMENT", "MORE_LINES_COMMENT", "ID", "CHAR_CONSTANT", "MACRO_LETTER", 
			"MACRO_DIGIT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TRASH", "INT_CONSTANT", "REAL_CONSTANT", "REAL_PART", "MANTISA_PART", 
			"ONE_LINE_COMMENT", "MORE_LINES_COMMENT", "ID", "CHAR_CONSTANT", "MACRO_LETTER", 
			"MACRO_DIGIT"
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


	public TSmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TSmm.g4"; }

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
		"\u0004\u0000\u000bx\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001\u001f\b\u0001\n\u0001\f\u0001\"\t\u0001\u0003\u0001$\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002(\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002-\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u00032\b\u0003\n\u0003\f\u00035\t\u0003\u0001\u0003\u0001\u0003"+
		"\u0004\u00039\b\u0003\u000b\u0003\f\u0003:\u0003\u0003=\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0003\u0004A\b\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005I\b\u0005\n\u0005"+
		"\f\u0005L\t\u0005\u0001\u0005\u0003\u0005O\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006W\b\u0006"+
		"\n\u0006\f\u0006Z\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007c\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0005\u0007g\b\u0007\n\u0007\f\u0007j\t\u0007\u0001\b\u0001"+
		"\b\u0005\bn\b\b\n\b\f\bq\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0003JXo\u0000\u000b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0001"+
		"\u0000\u0007\u0003\u0000\t\n\r\r  \u0001\u000019\u0002\u0000EEee\u0002"+
		"\u0000++--\u0001\u0001\n\n\u0003\u0000AZ__az\u0001\u000009\u0085\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0003#\u0001"+
		"\u0000\u0000\u0000\u0005,\u0001\u0000\u0000\u0000\u0007<\u0001\u0000\u0000"+
		"\u0000\t>\u0001\u0000\u0000\u0000\u000bD\u0001\u0000\u0000\u0000\rR\u0001"+
		"\u0000\u0000\u0000\u000fb\u0001\u0000\u0000\u0000\u0011k\u0001\u0000\u0000"+
		"\u0000\u0013t\u0001\u0000\u0000\u0000\u0015v\u0001\u0000\u0000\u0000\u0017"+
		"\u0018\u0007\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019"+
		"\u001a\u0006\u0000\u0000\u0000\u001a\u0002\u0001\u0000\u0000\u0000\u001b"+
		"$\u00050\u0000\u0000\u001c \u0007\u0001\u0000\u0000\u001d\u001f\u0003"+
		"\u0015\n\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000"+
		"\u0000\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000"+
		"!$\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000#\u001b\u0001\u0000"+
		"\u0000\u0000#\u001c\u0001\u0000\u0000\u0000$\u0004\u0001\u0000\u0000\u0000"+
		"%\'\u0003\u0007\u0003\u0000&(\u0003\t\u0004\u0000\'&\u0001\u0000\u0000"+
		"\u0000\'(\u0001\u0000\u0000\u0000(-\u0001\u0000\u0000\u0000)*\u0003\u0003"+
		"\u0001\u0000*+\u0003\t\u0004\u0000+-\u0001\u0000\u0000\u0000,%\u0001\u0000"+
		"\u0000\u0000,)\u0001\u0000\u0000\u0000-\u0006\u0001\u0000\u0000\u0000"+
		"./\u0003\u0003\u0001\u0000/3\u0005.\u0000\u000002\u0003\u0015\n\u0000"+
		"10\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000"+
		"\u000034\u0001\u0000\u0000\u00004=\u0001\u0000\u0000\u000053\u0001\u0000"+
		"\u0000\u000068\u0005.\u0000\u000079\u0003\u0015\n\u000087\u0001\u0000"+
		"\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001"+
		"\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000<.\u0001\u0000\u0000\u0000"+
		"<6\u0001\u0000\u0000\u0000=\b\u0001\u0000\u0000\u0000>@\u0007\u0002\u0000"+
		"\u0000?A\u0007\u0003\u0000\u0000@?\u0001\u0000\u0000\u0000@A\u0001\u0000"+
		"\u0000\u0000AB\u0001\u0000\u0000\u0000BC\u0003\u0003\u0001\u0000C\n\u0001"+
		"\u0000\u0000\u0000DE\u0005/\u0000\u0000EF\u0005/\u0000\u0000FJ\u0001\u0000"+
		"\u0000\u0000GI\t\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000IL\u0001\u0000"+
		"\u0000\u0000JK\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KN\u0001"+
		"\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000MO\u0007\u0004\u0000\u0000"+
		"NM\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0006\u0005\u0000"+
		"\u0000Q\f\u0001\u0000\u0000\u0000RS\u0005/\u0000\u0000ST\u0005*\u0000"+
		"\u0000TX\u0001\u0000\u0000\u0000UW\t\u0000\u0000\u0000VU\u0001\u0000\u0000"+
		"\u0000WZ\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000XV\u0001\u0000"+
		"\u0000\u0000Y[\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005"+
		"*\u0000\u0000\\]\u0005/\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0006"+
		"\u0006\u0000\u0000_\u000e\u0001\u0000\u0000\u0000`c\u0003\u0013\t\u0000"+
		"ac\u0005_\u0000\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000\u0000"+
		"ch\u0001\u0000\u0000\u0000dg\u0003\u0013\t\u0000eg\u0003\u0015\n\u0000"+
		"fd\u0001\u0000\u0000\u0000fe\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000"+
		"\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\u0010\u0001"+
		"\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000ko\u0005\'\u0000\u0000ln\t"+
		"\u0000\u0000\u0000ml\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000\u0000"+
		"op\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000pr\u0001\u0000\u0000"+
		"\u0000qo\u0001\u0000\u0000\u0000rs\u0005\'\u0000\u0000s\u0012\u0001\u0000"+
		"\u0000\u0000tu\u0007\u0005\u0000\u0000u\u0014\u0001\u0000\u0000\u0000"+
		"vw\u0007\u0006\u0000\u0000w\u0016\u0001\u0000\u0000\u0000\u0010\u0000"+
		" #\',3:<@JNXbfho\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}