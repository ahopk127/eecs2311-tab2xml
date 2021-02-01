package tab2xml.parser;

import java.util.ArrayList;

import tab2xml.parser.Lexer.Token;
import tab2xml.xmlconversion.Convert;

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
	ArrayList<ArrayList<Token>> tokens;

	public Parser(String input, Instrument instrument) {
		lexer = new Lexer(input, instrument);
		tokens = lexer.tokenize();
	}

	public String parse() {
		/*
		 * prerequisites: we need to understand how a note is formated in musicXML our
		 * design could change based on the way notes are represented in XML.
		 */

		if (tokens == null || tokens.size() == 0)
			return "";

		String xmlString = "sample xml output";

		// contains note and token objects
		ArrayList<ArrayList<Object>> data = new ArrayList<>();

		for (ArrayList<Token> line : tokens) {
			String tune = line.get(0).getData();
			ArrayList<Object> temp = new ArrayList<>();

			for (Token token : line) {
				switch (token.getType()) {
				case FRET:
					// check if the token is a fret: tune + fret -> note
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

		Convert convert = new Convert(data);
		xmlString = convert.getXmlString();

		return xmlString;
	}
}
