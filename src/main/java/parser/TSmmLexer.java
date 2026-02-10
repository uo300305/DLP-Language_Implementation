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
			null, null, null, null, null, null, null, null, null, "'A'"
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
		"\u0004\u0000\u000bq\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001\u001f\b\u0001\n\u0001\f\u0001\"\t\u0001\u0003\u0001$\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002(\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0003\u0003,\b\u0003\u0001\u0003\u0005\u0003/\b\u0003\n\u0003\f\u0003"+
		"2\t\u0003\u0001\u0003\u0001\u0003\u0004\u00036\b\u0003\u000b\u0003\f\u0003"+
		"7\u0003\u0003:\b\u0003\u0001\u0004\u0001\u0004\u0003\u0004>\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005F\b\u0005\n\u0005\f\u0005I\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005O\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006W\b\u0006\n\u0006"+
		"\f\u0006Z\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0003\u0007c\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007g\b\u0007\n\u0007\f\u0007j\t\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0002GX\u0000\u000b\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0001\u0000\u0006\u0003\u0000\t\n\r\r  \u0001\u000019\u0002"+
		"\u0000EEee\u0002\u0000++--\u0003\u0000AZ__az\u0001\u000009~\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0003#\u0001\u0000\u0000"+
		"\u0000\u0005%\u0001\u0000\u0000\u0000\u00079\u0001\u0000\u0000\u0000\t"+
		";\u0001\u0000\u0000\u0000\u000bA\u0001\u0000\u0000\u0000\rR\u0001\u0000"+
		"\u0000\u0000\u000fb\u0001\u0000\u0000\u0000\u0011k\u0001\u0000\u0000\u0000"+
		"\u0013m\u0001\u0000\u0000\u0000\u0015o\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0007\u0000\u0000\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u001a"+
		"\u0006\u0000\u0000\u0000\u001a\u0002\u0001\u0000\u0000\u0000\u001b$\u0005"+
		"0\u0000\u0000\u001c \u0007\u0001\u0000\u0000\u001d\u001f\u0003\u0015\n"+
		"\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f\"\u0001\u0000\u0000\u0000"+
		" \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!$\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000#\u001b\u0001\u0000\u0000\u0000"+
		"#\u001c\u0001\u0000\u0000\u0000$\u0004\u0001\u0000\u0000\u0000%\'\u0003"+
		"\u0007\u0003\u0000&(\u0003\t\u0004\u0000\'&\u0001\u0000\u0000\u0000\'"+
		"(\u0001\u0000\u0000\u0000(\u0006\u0001\u0000\u0000\u0000)+\u0003\u0003"+
		"\u0001\u0000*,\u0005.\u0000\u0000+*\u0001\u0000\u0000\u0000+,\u0001\u0000"+
		"\u0000\u0000,0\u0001\u0000\u0000\u0000-/\u0003\u0015\n\u0000.-\u0001\u0000"+
		"\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u000001\u0001"+
		"\u0000\u0000\u00001:\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"35\u0005.\u0000\u000046\u0003\u0015\n\u000054\u0001\u0000\u0000\u0000"+
		"67\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000"+
		"\u00008:\u0001\u0000\u0000\u00009)\u0001\u0000\u0000\u000093\u0001\u0000"+
		"\u0000\u0000:\b\u0001\u0000\u0000\u0000;=\u0007\u0002\u0000\u0000<>\u0007"+
		"\u0003\u0000\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">?\u0001\u0000\u0000\u0000?@\u0003\u0003\u0001\u0000@\n\u0001\u0000\u0000"+
		"\u0000AB\u0005/\u0000\u0000BC\u0005/\u0000\u0000CG\u0001\u0000\u0000\u0000"+
		"DF\t\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000"+
		"GH\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HN\u0001\u0000\u0000"+
		"\u0000IG\u0001\u0000\u0000\u0000JO\u0005\n\u0000\u0000KL\u0005E\u0000"+
		"\u0000LM\u0005O\u0000\u0000MO\u0005F\u0000\u0000NJ\u0001\u0000\u0000\u0000"+
		"NK\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PQ\u0006\u0005\u0000"+
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
		"\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000kl\u0005A\u0000\u0000l\u0012"+
		"\u0001\u0000\u0000\u0000mn\u0007\u0004\u0000\u0000n\u0014\u0001\u0000"+
		"\u0000\u0000op\u0007\u0005\u0000\u0000p\u0016\u0001\u0000\u0000\u0000"+
		"\u000f\u0000 #\'+079=GNXbfh\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}