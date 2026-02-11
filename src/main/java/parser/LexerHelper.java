package parser;

import java.util.IllegalFormatConversionException;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {

		// Quitamos las comillas
		String content = str.substring(1, str.length() - 1);

		// Caso normal
		if (content.charAt(0) != '\\') {
			return content.charAt(0);
		}

		// Caso escapado
		switch(content){
			case("\\n"): return '\n';
			case("\\t"): return '\t';
		}

		// Caso ASCII
		return (char) Integer.parseInt(content.substring(1));
	}

	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(IllegalFormatConversionException e) {
			System.out.println(e);
		}
		return -1;
	}
}
