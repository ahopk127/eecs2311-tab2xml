package tab2xml.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.ImmutablePair;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.model.Score;
import tab2xml.xmlconversion.Transform;

/**
 * The parser is responsible for delegating extraction from data based on
 * instrument.
 * 
 * @author amir
 * 
 */
public class Parser {
	private final Processor processor;
	private final Score sheet;
	private final Instrument instrument;
	
	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 * @throws InvalidInputException if invalid input is parsed
	 * 
	 */
	public Parser(String input, Instrument instrument)
			throws InvalidInputException {
		this.processor = new Processor(input, instrument);
		this.sheet = this.processor.process();
		this.instrument = instrument;
	}
	
	/**
	 * Parse the data by instrument.
	 * 
	 * @return an XML conversion of the input plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed
	 * @throws InvalidTokenException if invalid token is parsed
	 * 
	 */
	public ImmutablePair<String, Collection<ParsingWarning>> parse()
			throws InvalidInputException, InvalidTokenException {
		switch (this.instrument) {
		case GUITAR:
			return this.parseGuitar();
		case DRUM:
			return this.parseDrum();
		case BASS:
			return this.parseBass();
		default:
			throw new UnsupportedOperationException(
					"Instrument " + this.instrument + " not supported.");
		}
	}
	
	/**
	 * Parse the input as Bass tab.
	 * 
	 * @return an XML conversion for a Bass plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 * 
	 */
	private ImmutablePair<String, Collection<ParsingWarning>> parseBass() {
		throw new UnsupportedOperationException(
				"Support not yet added for bass.");
	}
	
	/**
	 * Parse the input as Drum tab.
	 * 
	 * @return an XML conversion for a Drum plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 * 
	 */
	private ImmutablePair<String, Collection<ParsingWarning>> parseDrum() {
		throw new UnsupportedOperationException(
				"Support not yet added for drums.");
	}
	
	/**
	 * Parse the input as Guitar tab.
	 * 
	 * @return an XML conversion for a Guitar plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 */
	private ImmutablePair<String, Collection<ParsingWarning>> parseGuitar() {
		String xmlOutput;
		final Transform tf = new Transform(this.sheet, this.instrument);
		xmlOutput = tf.toXML();
		
		// NEW ERROR CODE - put any warnings here
		final List<ParsingWarning> warnings = new ArrayList<>();
		
		return ImmutablePair.of(xmlOutput, warnings);
	}
}
