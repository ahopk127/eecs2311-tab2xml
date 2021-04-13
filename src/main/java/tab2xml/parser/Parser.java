package tab2xml.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tab2xml.ImmutablePair;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.exceptions.MeasureWarning;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.model.Instrument;
import tab2xml.model.Measure;
import tab2xml.model.Note;
import tab2xml.model.Score;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.drum.DrumStaff;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.guitar.GuitarStaff;
import tab2xml.xmlconversion.Transform;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * A parser used to delegate tablature conversion.
 * 
 * <p>
 * Pre-conditions:
 * <ul>
 * <li><b>A parser needs a tablature as a {@code String}, a corresponding
 * {@code Instrument}, and an optional {@code MetaData} which can be
 * {@code null}</b>
 * </ul>
 * 
 * @author amir
 */
public class Parser {
	/* General patterns for each instrument built using more basic patterns. */
	private static final String COMMENTS = "((/[*])(([\s]|[^ ])+)([*]/))|([/]{2,}[^\n]+)";
	private static final String OUTLIER_G = "(" + COMMENTS
			+ "|(^(?![ \t]*([a-gA-G]#?)?[ \t]*[|][^\s]*[-|]\r?\n?).+\r?\n?))";
	private static final String OUTLIER_B = "((^(?!([a-gA-G]#?)?[|-][^\r\n]*[|-]\r?\n?).*\r?\n?)\r?\n)";
	private static final String OUTLIER_D = "(" + COMMENTS
			+ "|(^(?![ \t]*([ABCcDdEFHhLMOPRSTt]{2})[ \t]*[|][^\s]*[-|]\r?\n?).+\r?\n?))";
	private static final String OUTLIER_GUITAR = "(" + OUTLIER_G + "+)";
	private static final String OUTLIER_DRUM = "(" + OUTLIER_D + "+)";
	private static final String STRING = "(^(?!((^(?!([ \t]*([a-gA-G]#?)?[ \t]*[|-])[^\s]*[-|]).*$))).+\r?\n?)";
	private static final String STRING_UNBOUND = "(" + STRING + "+)";
	private static final String GP = STRING + "{6,}";
	private static final String BP = OUTLIER_B + "(" + STRING + "{4,5})" + OUTLIER_B;
	private static final String DP = "(^(?!((^(?!([ \t]*([ABCcDdEFHhLMOPRSTt]{2})[ \t]*[|])[^\s]*[-|]).*)+)).*\r?\n?)+";

	/** A pattern to match comments. */
	public static final Pattern comments = Pattern.compile(COMMENTS, Pattern.MULTILINE);
	/** A pattern to match metadata around guitar/bass staffs. */
	public static final Pattern outlierPlucked = Pattern.compile(OUTLIER_GUITAR, Pattern.MULTILINE);
	/** A pattern to match metadata around drum staffs. */
	public static final Pattern outlierPercussion = Pattern.compile(OUTLIER_DRUM, Pattern.MULTILINE);
	/** A pattern to match guitar staffs. */
	public static final Pattern guitarPattern = Pattern.compile(GP, Pattern.MULTILINE);
	/** A pattern to match guitar staffs without a lower bound. */
	public static final Pattern guitarPatterGreedy = Pattern.compile(STRING_UNBOUND, Pattern.MULTILINE);
	/** A pattern to match bass staffs( the standard of 4 or 5 strings). */
	public static final Pattern bassPattern = Pattern.compile(BP, Pattern.MULTILINE);
	/** A pattern to match drum staffs. */
	public static final Pattern drumPattern = Pattern.compile(DP, Pattern.MULTILINE);

	/**
	 * An aggregate processor used to preprocess and generate a {@code Score}
	 * object.
	 */
	private final Processor processor;
	/**
	 * The instrument that is either detected by the system or selected by the user.
	 */
	private final Instrument instrument;
	/** The XML metadata to be in the final XML output. */
	private final XMLMetadata metadata;
	/** the {@code Score} object to hold the parsed information. */
	private final Score<?> sheet;
	/** A list of parse warnings that will not halt parsing. */
	@SuppressWarnings("unused")
	private final Collection<ParsingWarning> preprocessWarnings;

	/**
	 * @param input      the tablature to parse
	 * @param instrument the corresponding instrument
	 * @param metadata   the optional metadata
	 * @throws InvalidInputException if invalid input occurs while parsing
	 */
	public Parser(String input, Instrument instrument, XMLMetadata metadata) throws InvalidInputException {
		this.metadata = metadata;
		this.processor = new Processor(input, instrument, metadata);
		this.instrument = instrument;
		this.sheet = this.processor.process();
		this.preprocessWarnings = processor.getPreprocessWarnings();
	}

	/**
	 * Parse the data by instrument.
	 * 
	 * @return an XML conversion of the input plus any warnings raised during
	 *         parsing
	 * @throws InvalidInputException if invalid input is parsed
	 * @throws InvalidTokenException if invalid token is parsed
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

	/**
	 * @param input the tablature to parse
	 * @return an empty Optional if no instrument is detected, otherwise returns the
	 *         detected instrument
	 * @throws UnsupportedOperationException if multiple instruments are detected
	 */
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
		System.out.println(ins);
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
		Iterator<Measure<? extends Note>> measureItr = this.sheet.measureIterator();

		while (measureItr.hasNext()) {
			// next will always return a Measure<GuitarNote>
			@SuppressWarnings("unchecked")
			Measure<GuitarNote> measure = (Measure<GuitarNote>) measureItr.next();

			if (measure.size() == 0) {
				warnings.add(new MeasureWarning("", String.format("Empty measure %d", measure.getMeasure() + 1),
						(int) measure.getRange().getFirst().getStart(),
						(int) measure.getRange().getSecond().getStop()));
			}
		}
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
		Iterator<Measure<? extends Note>> measureItr = this.sheet.measureIterator();

		while (measureItr.hasNext()) {
			// next will always return a Measure<GuitarNote>
			@SuppressWarnings("unchecked")
			Measure<GuitarNote> measure = (Measure<GuitarNote>) measureItr.next();

			if (measure.size() == 0) {
				warnings.add(new MeasureWarning("", String.format("Empty measure %d", measure.getMeasure() + 1),
						(int) measure.getRange().getFirst().getStart(),
						(int) measure.getRange().getSecond().getStop()));
			}
		}
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
		Iterator<Measure<? extends Note>> measureItr = this.sheet.measureIterator();

		// measure warnings
		while (measureItr.hasNext()) {
			// next will always return a Measure<DrumNote>
			@SuppressWarnings("unchecked")
			Measure<DrumNote> measure = (Measure<DrumNote>) measureItr.next();

			if (measure.size() == 0) {
				warnings.add(new MeasureWarning("", String.format("Empty measure %d", measure.getMeasure()+ 1),
						(int) measure.getRange().getFirst().getStart(),
						(int) measure.getRange().getSecond().getStop()));
			}
		}
		return ImmutablePair.of(xmlOutput, warnings);
	}
}
