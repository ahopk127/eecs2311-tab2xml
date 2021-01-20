package tab2xml.parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A lexer for tokenizing ASCII tablature for a specified instrument.
 * 
 * @author amir
 */
public class Lexer {

	private String input;
	private Instrument instrument;

	public static void main(String args[]) {
		// sample simple test case
		String input = "E|--0-----------------------|-------------------------|\n"
				+ "B|------------------3-----5-|-2-----------------------|\n"
				+ "G|------------------3-------|-2-----------------------|\n"
				+ "D|------------------5-------|-2-----------------------|\n"
				+ "A|--------------------------|-0-----------------------|\n"
				+ "D|--------------------------|-------------------------|\n";

		Lexer lx = new Lexer(input, Instrument.GUITAR);
		ArrayList<ArrayList<Token>> res = lx.tokenize();

		System.out.println(res.toString());

	}

	/**
	 * Instantiate a Lexer object for an input string and instrument.
	 * 
	 * @param input      string representing ASCII tablature
	 * @param instrument the type of instrument for the tablature
	 */
	public Lexer(String input, Instrument instrument) {
		this.input = input;
		this.instrument = instrument;
	}

	/**
	 * Delegate tokenization based on instrument.
	 *
	 * @return a list of list of tokens representing the ASCII tablature
	 */
	public ArrayList<ArrayList<Token>> tokenize() {
		switch (this.instrument) {
		case GUITAR:
			return tokenizeGuitar(input);
		case DRUM:
			return tokenizeDrum(input);
		default:
			return null;
		}
	}

	/**
	 * This method will extract tokens from the guitar tablature.
	 * 
	 * @param input guitar ASCII tablature
	 * @return a list of list of tokens representing the guitar tablature
	 */
	public static ArrayList<ArrayList<Token>> tokenizeGuitar(String input) {
		ArrayList<ArrayList<Token>> tokens = new ArrayList<>();
		try (Scanner sc = new Scanner(input)) {
			StringBuffer sb = new StringBuffer();

			// g-0() | g-1() | g-2() | ... g-n()
			// named capturing group: (name + pattern of a token)
			for (TokenType type : TokenType.values())
				sb.append(String.format("|(?<%s>%s)", type.name(), type.pattern));

			Pattern p = Pattern.compile(new String(sb.substring(1)));

			while (sc.hasNextLine()) {
				String currentLine = sc.nextLine();
				Matcher m = p.matcher(currentLine);

				ArrayList<Token> newTokens = new ArrayList<>();

				while (m.find()) {
					for (TokenType type : TokenType.values()) {
						String name = type.name();
						if (m.group(name) != null) {
							try {
								newTokens.add(new Token(type, m.group(name)));
							} catch (InvalidTokenException e) {
								e.printStackTrace();
							}
						}
					}
				}
				tokens.add(newTokens);
			}
		}
		return tokens;
	}

	/**
	 * This method will extract tokens from the drum tablature.
	 * 
	 * @param input drum ASCII tablature
	 * @return a list of list of tokens representing the drum tablature
	 */
	public static ArrayList<ArrayList<Token>> tokenizeDrum(String input) {
		ArrayList<ArrayList<Token>> tokens = new ArrayList<>();
		// TO-DO

		return tokens;
	}

	/**
	 * List of supported instruments.
	 * 
	 * @author amir
	 *
	 */
	public static enum Instrument {
		GUITAR, DRUM;
	}

	/**
	 * List of token types and their regular expressions.
	 * 
	 * @author amir
	 */
	public static enum TokenType {
		BAR("\\|"), NOTE("[A-G]"), FRET("[1-2]?\\d"), HARMONIC("\\[[1-7]\\]"), PULLOFF("p"), HAMMERON("h"), SLIDE("s");

		public final Pattern pattern;

		/**
		 * Set pattern for the respective token type.
		 * 
		 * @param pattern a pattern for a token type
		 */
		private TokenType(String pattern) {
			this.pattern = Pattern.compile(pattern);
		}

		/**
		 * Matches the input to every token type, and returns the first type that
		 * matches.
		 * 
		 * @param token token to test
		 * @return type of token
		 * @throws InvalidTokenException
		 * @since 2021-01-20
		 */
		public static TokenType getType(String token) throws InvalidTokenException {
			for (TokenType type : TokenType.values()) {
				if (type.matches(token)) {
					return type;
				}
			}
			throw new InvalidTokenException("Invalid token.");
		}

		/**
		 * Checks if a token is an instance of its type.
		 * 
		 * @param token token as a string
		 * @return returns true if the specified token matches the token type
		 */
		public boolean matches(String token) {
			return this.pattern.matcher(token).matches();
		}
	}

	/**
	 * An atomic token in the ASCII tablature
	 * 
	 * @author amir
	 *
	 */
	public static class Token {
		private TokenType type;
		private String data;

		/**
		 * Instantiate a token with a token type and its data.
		 * 
		 * @param type the type of token
		 * @param data the contents of the token
		 * @throws InvalidTokenException thrown if there is a mismatch between data and
		 *                               the token type
		 */
		public Token(TokenType type, String data) throws InvalidTokenException {
			this.type = type;
			if (type.matches(data))
				this.data = data;
			else
				throw new InvalidTokenException("Token mismatch.");
		}

		@Override
		public String toString() {
			return String.format("(%s %s)", type.name(), data);
		}
	}

	/**
	 * Thrown if invalid token occurs.
	 * 
	 */
	public static class InvalidTokenException extends Exception {
		private static final long serialVersionUID = 1L;

		public InvalidTokenException(String message) {
			super(message);
		}
	}

}
