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
		try {
			return str.charAt(0);
		}
		catch(IllegalFormatConversionException e) {
			System.out.println(e);
		}
		return '0';
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
