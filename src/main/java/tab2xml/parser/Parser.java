package tab2xml.parser;

import java.util.ArrayList;
import java.util.Stack;

import tab2xml.xmlconversion.Transform;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.GToken;
import tab2xml.model.TokenType;

/**
 * The parser is responsible for getting note, information from ASCII tablature.
 * 
 * @author amir
 */
public class Parser {
	private Lexer lexer;
	private ArrayList<ArrayList<GToken>> tokens;
	private Instrument instrument;

	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 * @throws InvalidTokenException 
	 */
	public Parser(String input, Instrument instrument) throws InvalidTokenException {
		//TODO we don't need the lexer.
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

		// TODO: we don't need to limit strings to 6
		//if (tokens.size() != 6)
		//throw new InvalidInputException("Must have 6 strings for prototype.");

		String xmlOutput;
		ArrayList<ArrayList<Object>> data = new ArrayList<>();
		Stack<GToken> oracle = new Stack<>();
		int currentString = 0;

		for (ArrayList<GToken> line : tokens) {
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
				GToken token = line.get(i);
				switch (token.type()) {
				case HYPHEN:
					if (!Instrument.isStandardTuningGuitar && i == 1)
						throw new InvalidInputException(
								String.format("String %d does not have a proper start of measure.", currentString + 1));
					break;
				case FRET:
					
					break;
				case BAR:
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
