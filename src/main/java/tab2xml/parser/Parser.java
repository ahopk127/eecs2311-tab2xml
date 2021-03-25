package tab2xml.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tab2xml.ImmutablePair;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.model.Instrument;
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
	private final Instrument instrument;
	private final Score sheet;

	/*General patterns for each instrument*/
	public static final String OUTLIER = "(^(?!([a-gA-G]#?)?[ ]*[\\|-][^\r\n]*[\\|-]\r?\n?).*\r?\n?)";
	public static final String STRING = "(^(?!((^(?!(([a-gA-G]#?)?[ ]*[\\|-])[^\r\n]*\\|).*$))).+\r?\n?)";
	public static final String gP = STRING + "{6,}";
	public static final String bP = OUTLIER + STRING + "{4,5}" + OUTLIER;
	public static final String dP = "(^(?!((^(?!(([ABCcDdEHhLMPRST12]{2})[\\|-]).*\\|).*)+)).*\r?\n?)+";

	public static final Pattern guitarPattern = Pattern.compile(gP, Pattern.MULTILINE);
	public static final Pattern bassPattern = Pattern.compile(bP, Pattern.MULTILINE);
	public static final Pattern drumPattern = Pattern.compile(dP, Pattern.MULTILINE);

	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 * @throws InvalidInputException if invalid input is parsed
	 * 
	 */
	public Parser(String input, Instrument instrument) throws InvalidInputException {
		this.processor = new Processor(input, instrument);
		this.instrument = instrument;
		this.sheet = this.processor.process();
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
		case BASS:
			return this.parseBass();
		case DRUM:
			return this.parseDrum();
		default:
			throw new UnsupportedOperationException("Instrument " + this.instrument + " not supported.");
		}
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

		// TODO: ADD WARNINGS from guitar score data
		final List<ParsingWarning> warnings = new ArrayList<>();

		return ImmutablePair.of(xmlOutput, warnings);
	}

	/**
	 * Parse the input as Bass tab.
	 * 
	 * @return an XML conversion for a Bass plus any warnings raised during parsing
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 * 
	 */
	private ImmutablePair<String, Collection<ParsingWarning>> parseBass() {
		String xmlOutput;
		final Transform tf = new Transform(this.sheet, this.instrument);
		xmlOutput = tf.toXML();

		// TODO: ADD WARNINGS from bass score data
		final List<ParsingWarning> warnings = new ArrayList<>();

		return ImmutablePair.of(xmlOutput, warnings);
	}

	/**
	 * Parse the input as Drum tab.
	 * 
	 * @return an XML conversion for a Drum plus any warnings raised during parsing
	 * @throws InvalidInputException if invalid input is parsed.
	 * @throws InvalidTokenException if invalid token is parsed.
	 * 
	 */
	private ImmutablePair<String, Collection<ParsingWarning>> parseDrum() {
		throw new UnsupportedOperationException("Support not yet added for drums.");
	}

	public Instrument getDetectedInstrument() {
		return this.instrument;
	}

	// returns an empty Optional if no instrument is detected,
	// otherwise returns the detected instrument
	public static Optional<Instrument> getDetectedInstrument(String input) {
		StringBuilder tab = new StringBuilder(input);
		tab.insert(0, "\n");
		tab.insert(tab.length(), "\n");

		Matcher gM = guitarPattern.matcher(tab.toString());
		Matcher bM = bassPattern.matcher(tab.toString());
		Matcher dM = drumPattern.matcher(tab.toString());
		int gCount = 0, bCount = 0, dCount = 0;

		while (gM.find())
			gCount++;
		while (bM.find())
			bCount++;
		while (dM.find())
			dCount++;

		int max = Math.max(Math.max(gCount, bCount), dCount);
		Instrument ins;

		if (max == gCount)
			ins = Instrument.GUITAR;
		else if (max == bCount)
			ins = Instrument.BASS;
		else
			ins = Instrument.DRUM;

		if (max == 0)
			return Optional.empty();
		else
			return Optional.of(ins);
	}
}
