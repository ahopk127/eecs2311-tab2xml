package tab2xml.parser;

import java.util.ArrayList;
import java.util.Stack;

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

	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 */
	public Parser(String input, Instrument instrument) {
		lexer = new Lexer(input, instrument);
		tokens = lexer.tokenize();
		this.instrument = instrument;
	}

	/**
	 * Parse the data by instrument.
	 * 
	 * @return an XML conversion of the input
	 * @throws InvalidInputException if invalid input is parsed
	 * @throws InvalidTokenException if invalid token is parsed
	 */
	public String parse() throws InvalidInputException, InvalidTokenException {
		switch (instrument) {
		case GUITAR:
			return parseGuitar();
		case DRUM:
			return parseDrum();
		case BASS:
			return parseBass();
		default:
			return "Instrument not supported.";
		}
	}

	/**
	 * Parse the input as Guitar tab.
	 * 
	 * @return an XML conversion for a Guitar
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 */
	private String parseGuitar() throws InvalidInputException, InvalidTokenException {
		if (tokens == null || tokens.size() == 0)
			throw new InvalidInputException("Could not parse input.");

		if (tokens.size() != 6)
			throw new InvalidInputException("Must have 6 strings for prototype.");

		String xmlOutput;
		ArrayList<ArrayList<Object>> data = new ArrayList<>();
		Stack<Token> oracle = new Stack<>();
		int currentString = 0;
		int hyphenCount = 0;

		for (ArrayList<Token> line : tokens) {
			ArrayList<Object> temp = new ArrayList<>();

			if (line.isEmpty()) {
				currentString = 0;
				data.add(temp);
				continue;
			}

			String tune = line.get(0).getData();

			if (!TokenType.NOTE.matches(tune) && !TokenType.BAR.matches(tune))
				throw new InvalidInputException(
						String.format("String %d does not have a proper tune.", currentString + 1));

			if (TokenType.BAR.matches(tune) && instrument == Instrument.GUITAR) {
				tune = Instrument.standardTuningGuitar[currentString];
				Instrument.isStandardTuningGuitar = true;
			} else 
				Instrument.isStandardTuningGuitar = false;
			

			for (int i = 0; i < line.size(); i++) {
				Token token = line.get(i);
				switch (token.type()) {
				case HYPHEN:
					if (!Instrument.isStandardTuningGuitar && i == 1)
						throw new InvalidInputException(String.format("String %d does not have a proper start of measure.", currentString + 1));
					hyphenCount++;
					break;
				case FRET:
					int fret = Integer.parseInt(token.getData());
					Note note = Note.toNote(tune + fret);
					note.setFret(fret);
					note.setString(currentString + 1);

					if (Instrument.isStandardTuningGuitar)
						note.setOctave(Instrument.tuningOctaveGuitar[currentString]);
					else
						note.setOctave(Integer.toString((int) Math.ceil(fret / 12)));
					note.setHasStem(false);
					note.setPosition(hyphenCount);
					temp.add(note);
					hyphenCount = 0;
					break;
				case BAR:
					hyphenCount = 0;
					if (oracle.isEmpty())
						oracle.push(token);
					else {
						oracle.pop();

						if (line.indexOf(token) != line.size() - 1)
							oracle.push(token);
					}
					temp.add(token);
					break;
				default:
					//TODO: add support for pull off, hammer on
					//temp.add(token);
					break;
				}
			}
			if (!oracle.isEmpty())
				throw new InvalidInputException(String.format("Measure incomplete on string %d.", currentString + 1));
			currentString++;
			data.add(temp);
		}

		Transform tf = new Transform(data, instrument);
		xmlOutput = tf.toXML();

		return xmlOutput;
	}

	/**
	 * Parse the input as Drum tab.
	 * 
	 * @return an XML conversion for a Drum
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 */
	private String parseDrum() {
		return "Support not yet added for drums.";
	}

	/**
	 * Parse the input as Bass tab.
	 * 
	 * @return an XML conversion for a Bass
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 */
	private String parseBass() {
		return "Support not yet added for bass.";
	}
}
