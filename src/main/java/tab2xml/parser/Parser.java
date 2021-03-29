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
	public static final String OUTLIER = "((^(?!([a-gA-G]#?)?[|-][^\r\n]*[|-]\r?\n?).*\r?\n?)\r?\n?)";
	public static final String STRING = "(^(?!((^(?!([ \t]*([a-gA-G]#?)?[ ]*[|-])[^\r\n]*\\|).*$))).+\r?\n?)";
	public static final String gP = STRING + "{6,}";
	
	public static final String bP = OUTLIER + "(" + STRING + "{4,5})" + OUTLIER;
	public static final String dP = "(^(?!((^(?!([ \t]*([ABCcDdEHhLMPRST12]{2})[\\|-]).*\\|).*)+)).*\r?\n?)+";
	public static final Pattern guitarPattern = Pattern.compile(gP,
			Pattern.MULTILINE);
	public static final Pattern bassPattern = Pattern.compile(bP,
			Pattern.MULTILINE);
	public static final Pattern drumPattern = Pattern.compile(dP,
			Pattern.MULTILINE);
	
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
	
	private final Processor processor;
	private final Instrument instrument;
	private final Score<?> sheet;
	
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
		this.instrument = instrument;
		this.sheet = this.processor.process();
	}
	
	public Instrument getDetectedInstrument() {
		return this.instrument;
	}
	
	/**
	 * Parse the data by instrument. Uses default XML metadata.
	 * 
	 * @return an XML conversion of the input plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed
	 * @throws InvalidTokenException if invalid token is parsed
	 * 
	 */
	public ImmutablePair<String, Collection<ParsingWarning>> parse()
			throws InvalidInputException, InvalidTokenException {
		return this.parse(XMLMetadata.fromDefaultTitle());
	}
	
	/**
	 * Parse the data by instrument.
	 * 
	 * @param metadata XML metadata
	 * @return an XML conversion of the input plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed
	 * @throws InvalidTokenException if invalid token is parsed
	 * 
	 */
	public ImmutablePair<String, Collection<ParsingWarning>> parse(
			XMLMetadata metadata)
			throws InvalidInputException, InvalidTokenException {
		switch (this.instrument) {
		case GUITAR:
			return this.parseGuitar(metadata);
		case BASS:
			return this.parseBass(metadata);
		case DRUM:
			return this.parseDrum(metadata);
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
	private ImmutablePair<String, Collection<ParsingWarning>> parseBass(
			XMLMetadata metadata) {
		String xmlOutput;
		
		// This method should only be called if sheet is Score<GuitarStaff>
		// so if this fails, the precondition was violated
		@SuppressWarnings("unchecked")
		final Transform<GuitarStaff> tf = new Transform<>(
				(Score<GuitarStaff>) this.sheet, this.instrument, metadata);
		xmlOutput = tf.toXML();
		
		// TODO: ADD WARNINGS from bass score data
		final List<ParsingWarning> warnings = new ArrayList<>();
		
		return ImmutablePair.of(xmlOutput, warnings);
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
	@SuppressWarnings("unused")
	private ImmutablePair<String, Collection<ParsingWarning>> parseDrum(
			XMLMetadata metadata) {
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
	private ImmutablePair<String, Collection<ParsingWarning>> parseGuitar(
			XMLMetadata metadata) {
		String xmlOutput;
		
		// This method should only be called if sheet is Score<GuitarStaff>
		// so if this fails, the precondition was violated
		@SuppressWarnings("unchecked")
		final Transform<GuitarStaff> tf = new Transform<>(
				(Score<GuitarStaff>) this.sheet, this.instrument, metadata);
		xmlOutput = tf.toXML();
		
		// GuitarStaff staff = (GuitarStaff) sheet.getStaffs().get(0);s
		// staff.setBeats(metadata.getTimeSignatures());
		
		// TODO: ADD WARNINGS from guitar score data
		final List<ParsingWarning> warnings = new ArrayList<>();
		
		return ImmutablePair.of(xmlOutput, warnings);
	}
}
