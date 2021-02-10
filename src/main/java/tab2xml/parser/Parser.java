package tab2xml.parser;

import java.util.ArrayList;

import tab2xml.parser.Lexer.InvalidTokenException;
import tab2xml.xmlconversion.Transform;

/**
 * The parser is responsible for getting note, information from ASCII tablature.
 * 
 * @author amir
 */
public class Parser {
	private Lexer lexer;
	private ArrayList<ArrayList<Token>> tokens;
	private Instrument instrument;

	public Parser(String input, Instrument instrument) {
		lexer = new Lexer(input, instrument);
		tokens = lexer.tokenize();
		this.instrument = instrument;
	}

	public String parse() throws InvalidInputException, InvalidTokenException {
		switch (instrument) {
		case GUITAR:
			return parseGuitar();
		case DRUM:
			return parseDrum();
		default:
			return "";
		}
	}

	private String parseGuitar() throws InvalidInputException, InvalidTokenException {
		if (tokens == null || tokens.size() == 0)
			throw new InvalidInputException("Could not parse input.");

		String xmlOutput;
		ArrayList<ArrayList<Object>> data = new ArrayList<>();
		int currentString = 0;
		for (ArrayList<Token> line : tokens) {
			ArrayList<Object> temp = new ArrayList<>();

			if (line.isEmpty()) {
				currentString = 0;
				continue;
			}

			String tune = line.get(0).getData();

			if (!TokenType.NOTE.matches(tune) && !TokenType.BAR.matches(tune))
				throw new InvalidInputException("The string does not have a proper tune.");

			if (TokenType.BAR.matches(tune) && instrument == Instrument.GUITAR)
				tune = Instrument.standardTuning[currentString];

			for (Token token : line) {
				switch (token.type()) {
				case FRET:
					int fret = Integer.parseInt(token.getData());
					Note note = Note.toNote(tune + fret);
					note.setFret(fret);
					note.setString(currentString + 1);
					note.setType(0);
					note.setOctave(0);
					note.setDuration(0);
					note.setHasStem(false);
					temp.add(note);
					break;
				default:
					temp.add(token);
					break;
				}
			}
			currentString++;
			data.add(temp);
		}

		Transform tf = new Transform(data, instrument);
		xmlOutput = tf.toXML();

		return xmlOutput;
	}

	private String parseDrum() {
		return "Supported not yet added for drums.";
	}
}
