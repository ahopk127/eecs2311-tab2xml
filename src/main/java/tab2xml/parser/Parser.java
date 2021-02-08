package tab2xml.parser;

import java.util.ArrayList;

import tab2xml.parser.Lexer.Token;
import tab2xml.parser.Lexer.TokenType;
import tab2xml.xmlconversion.Transform;

/**
 * The parser is responsible for getting note, information from ASCII tablature.
 * 
 * @author amir
 */
public class Parser {
	/*
	 * Pre: we know Adrien will pass a string from a file or pasted by the user.
	 * 
	 * API design: 
	 * -------------------------------------------------------------- 
	 * 1. get parsed notes and other information in the Parser class. 
	 * 2. pass the data from step 1 to another process and convert to XML using XML DOM. 
	 * 		2.1. we need a really good understanding of musicXML format. 
	 * 3. pass the XML as a file or string to the presenter to be displayed or download. 3.1. will have access
	 * points for this.
	 *
	 */
	private Lexer lexer;
	private ArrayList<ArrayList<Token>> tokens;
	private Instrument instrument;
	
	public Parser(String input, Instrument instrument) {
		lexer = new Lexer(input, instrument);
		tokens = lexer.tokenize();
		this.instrument = instrument;
	}

	public String parse() {
		if (tokens == null || tokens.size() == 0)
			return "invalid input.";

		String xmlOutput;
		String tune;
		ArrayList<ArrayList<Object>> data = new ArrayList<>();

		for (ArrayList<Token> line : tokens) {
			ArrayList<Object> temp = new ArrayList<>();

			if (!line.isEmpty()) {
				if (TokenType.NOTE.matches(line.get(0).getData())) {
					tune = line.get(0).getData();

					for (Token token : line) {
						switch (token.getType()) {
						case FRET:
							// tune + fret -> note
							Note note = Note.toNote(tune + Integer.parseInt(token.getData()));
							temp.add(note);
							break;
						default:
							temp.add(token);
							break;
						}
					}
					data.add(temp);
				}
			}
		}

		Transform tf = new Transform(data, instrument);
		xmlOutput = tf.toXML();

		return xmlOutput;
	}
}
