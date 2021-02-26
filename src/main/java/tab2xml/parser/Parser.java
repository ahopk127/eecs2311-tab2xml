package tab2xml.parser;

import tab2xml.xmlconversion.Transform;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.Score;

/**
 * The parser is responsible for delegating extraction from data based on
 * instrument.
 * 
 * @author amir
 */
public class Parser {
	private Processor processor;
	private Score sheet;
	private Instrument instrument;

	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 * @throws InvalidTokenException
	 * @throws IOException
	 */
	public Parser(String input, Instrument instrument) throws InvalidTokenException {
		processor = new Processor(input, instrument);
		sheet = processor.process();
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
	private String parseGuitar() {
		String xmlOutput;
		Transform tf = new Transform(sheet, instrument);
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
