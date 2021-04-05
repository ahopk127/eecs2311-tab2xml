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
import tab2xml.model.drum.DrumStaff;
import tab2xml.model.guitar.GuitarStaff;
import tab2xml.xmlconversion.Transform;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * The parser is responsible for delegating extraction from data based on
 * instrument.
 * 
 * @author amir
 * 
 */
public class Parser {
	/* General patterns for each instrument */
	public static final String COMMENTS = "((/[*])(([\s]|[^ ])+)([*]/))|([/]{2,}[^\n]+)";
	public static final String OUTLIER_G = "(" + COMMENTS
			+ "|(^(?![ \t]*([a-gA-G]#?)?[ \t]*[|][^\s]*[-|]\r?\n?).+\r?\n?))";
	public static final String OUTLIER_D = "(" + COMMENTS
			+ "|(^(?![ \t]*([ABCcDdEFHhLMOPRSTt]{2})[ \t]*[|][^\s]*[-|]\r?\n?).+\r?\n?))";
	public static final String OUTLIER_GUITAR = "(" + OUTLIER_G + "+)";
	public static final String OUTLIER_DRUM = "(" + OUTLIER_D + "+)";
	public static final String STRING = "(^(?!((^(?!([ \t]*([a-gA-G]#?)?[ \t]*[|-])[^\s]*[-|]).*$))).+\r?\n?)";
	public static final String GP = STRING + "{6,}";
	public static final String BP = "(" + OUTLIER_G + "|\r?\n)" + "(" + STRING + "{4,5})" + "(" + OUTLIER_G + "|\r?\n)";
	public static final String DP = "(^(?!((^(?!([ \t]*([ABCcDdEFHhLMOPRSTt]{2})[ \t]*[|])[^\s]*[-|]).*)+)).*\r?\n?)+";

	public static final Pattern outlierPlucked = Pattern.compile(OUTLIER_GUITAR, Pattern.MULTILINE);
	public static final Pattern outlierPercussion = Pattern.compile(OUTLIER_DRUM, Pattern.MULTILINE);
	public static final Pattern guitarPattern = Pattern.compile(GP, Pattern.MULTILINE);
	public static final Pattern bassPattern = Pattern.compile(BP, Pattern.MULTILINE);
	public static final Pattern drumPattern = Pattern.compile(DP, Pattern.MULTILINE);

	private final Processor processor;
	private final Instrument instrument;
	private final XMLMetadata metadata;
	private final Score<?> sheet;

	/**
	 * Construct a parser with specified tablature and instrument.
	 * 
	 * @param input      the tablature to be parsed
	 * @param instrument the corresponding instrument
	 * @throws InvalidInputException if invalid input is parsed
	 * 
	 */
	public Parser(String input, Instrument instrument, XMLMetadata metadata) throws InvalidInputException {
		this.metadata = metadata;
		this.processor = new Processor(input, instrument, metadata);
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

	public Instrument getDetectedInstrument() {
		return this.instrument;
	}

	// returns an empty Optional if no instrument is detected,
	// otherwise returns the detected instrument
	public static Optional<Instrument> getDetectedInstrument(String input) {
		final StringBuilder tab = new StringBuilder();

		tab.append("\n");
		tab.append(input);
		tab.append("\n\n");

		final Matcher gM = guitarPattern.matcher(tab.toString());
		final Matcher bM = bassPattern.matcher(tab.toString());
		final Matcher dM = drumPattern.matcher(tab.toString());
		int gCount = 0, bCount = 0, dCount = 0;

		while (gM.find()) {
			gCount++;
		}
		while (bM.find()) {
			bCount++;
		}
		while (dM.find()) {
			dCount++;
		}

		final int max = Math.max(Math.max(gCount, bCount), dCount);
		Instrument ins;

		if (max == gCount) {
			ins = Instrument.GUITAR;
		} else if (max == bCount) {
			ins = Instrument.BASS;
		} else {
			ins = Instrument.DRUM;
		}

		if (max == 0)
			return Optional.empty();
		return Optional.of(ins);
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

		// This method should only be called if sheet is Score<GuitarStaff>
		// so if this fails, the precondition was violated
		@SuppressWarnings("unchecked")
		final Transform<GuitarStaff> tf = new Transform<>((Score<GuitarStaff>) this.sheet, this.instrument, metadata);
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

		// This method should only be called if sheet is Score<GuitarStaff>
		// so if this fails, the precondition was violated
		@SuppressWarnings("unchecked")
		final Transform<GuitarStaff> tf = new Transform<>((Score<GuitarStaff>) this.sheet, this.instrument,
				this.metadata);
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
		String xmlOutput;

		// This method should only be called if sheet is Score<DrumStaff>
		// so if this fails, the precondition was violated
		@SuppressWarnings("unchecked")
		final Transform<DrumStaff> tf = new Transform<>((Score<DrumStaff>) this.sheet, this.instrument, this.metadata);
		xmlOutput = tf.toXML();

		// TODO: ADD WARNINGS from drum score data
		final List<ParsingWarning> warnings = new ArrayList<>();

		return ImmutablePair.of(xmlOutput, warnings);
	}
}
