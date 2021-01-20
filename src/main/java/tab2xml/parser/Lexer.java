package tab2xml.parser;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author amir
 *
 */
public class Lexer {
	
	/**
	 * @author amir
	 *
	 */
	public static enum Instrument {
		GUITAR, DRUM;
	}
	
	/**
	 * @author amir
	 *
	 */
	public static enum TokenType {
		NOTE("[A-G]"), FRET("[0-21]"), HARMONIC("\\[[1-7]\\]"), PULLOFF("p"), HAMMERON("h"), SLIDE("s"), SLIDEUP("/");
		
		public final Pattern pattern;
		
		private TokenType(String pattern) {
			this.pattern = Pattern.compile(pattern);
		}
		
		/**
		 * Matches the input to every token type, and returns the first type that matches.  Returns null if the provided token does not match any of the types.
		 * @param token token to test
		 * @return type of token
		 * @since 2021-01-20
		 */
		public TokenType getType(String token) {
			for (TokenType type : TokenType.values()) {
				if (type.pattern.matcher(token).matches()) {
					return type;
				}
			}
			return null;
		}
	}
	
	/**
	 * @author amir
	 *
	 */
	public static class Token {
		private TokenType type;
		public String data;
		
		public Token(TokenType type,  String data) {
			this.type = type;
			this.data = data;
		}
		
	}
	
	/**
	 * @param input
	 * @return
	 */
	public static ArrayList<Token> tokenizeGuitar(String input){ 
		ArrayList<Token> tokens = new ArrayList<>();
		
		// logic to tokenize guitar tablature
		
		
		
		return tokens;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static ArrayList<Token> tokenizeDrum(String input){ 
		ArrayList<Token> tokens = new ArrayList<>();
		
		// logic to tokenize drum tablature (do later)
		
		
		
		return tokens;
	}
	
}
