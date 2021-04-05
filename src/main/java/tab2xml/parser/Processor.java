package tab2xml.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import tab2xml.antlr.DrumTabLexer;
import tab2xml.antlr.DrumTabParser;
import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.listeners.ErrorListener;
import tab2xml.model.ErrorToken;
import tab2xml.model.Instrument;
import tab2xml.model.Score;
import tab2xml.model.drum.DrumStaff;
import tab2xml.model.guitar.GuitarStaff;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * A processor which works in 2 stages serializing ASCII tablature for a
 * specified instrument.
 * 
 * @author amir
 */
public class Processor {
	private static final SerializeGuitarScore sgs = new SerializeGuitarScore();
	private static final SerializeDrumScore sds = new SerializeDrumScore();

	private String input;
	private final Instrument instrument;
	private XMLMetadata metadata;

	/**
	 * Construct a processor object for an input string and instrument.
	 * 
	 * @param input      string representing ASCII tablature
	 * @param instrument the type of instrument for the tablature
	 */
	public Processor(String input, Instrument instrument, XMLMetadata metadata) {
		this.input = input;
		this.instrument = instrument;
		this.metadata = metadata;
	}

	/**
	 * Delegate processing based on instrument.
	 *
	 * @return a score with serialized
	 * @throws InvalidInputException if invalid input occurs
	 */
	public Score<?> process() throws InvalidInputException {
		// TODO: remove this
		System.out.println("=================input===================");
		System.out.println(this.input);
		System.out.println("=========================================");
		switch (this.instrument) {
		case GUITAR:
			this.input = preprocessGuitar(this.input);
			return this.processGuitar();
		case BASS:
			this.input = preprocessBass(this.input);
			return this.processBass();
		case DRUM:
			this.input = preprocessDrum(this.input);
			return processDrum();
		default:
			throw new UnsupportedOperationException("This instrument is not supported.");
		}
	}

	private Score<GuitarStaff> processGuitar() throws InvalidInputException {
		final List<ErrorToken> errorTokens = new LinkedList<>();

		if (input == null || input.length() == 0) {
			ErrorToken errorToken = new ErrorToken();
			errorToken.setStart(-1);
			errorToken.setStop(-1);
			errorToken.setLine(-1);
			errorToken.setColumn(-1);
			errorToken.setMessage("Empty input");
			errorTokens.add(errorToken);
		}

		if (!errorTokens.isEmpty())
			showErrors(errorTokens);

		List<Integer> positions = new LinkedList<>();
		for (int i = 0; i < input.length() - 1; i++) {
			char c1 = input.charAt(i);
			char c2 = input.charAt(i + 1);
			if ((c1 == 47 && c2 == 42) || (c1 == 42 && c2 == 47))
				positions.add(i);
		}

		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		GuitarTabLexer lexer = null;
		try {
			lexer = new GuitarTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (lexer == null)
			return new Score<>();

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GuitarTabParser parser = new GuitarTabParser(tokens);
		ErrorListener listener = new ErrorListener();
		parser.addErrorListener(listener);
		ParseTree root = parser.sheet();

		if (listener.hasErrors()) {
			int start;
			int stop;
			int line;
			int column;
			int rightComment;
			int leftComment;
			int leastCommentCount;
			final StringBuilder message = new StringBuilder();
			final LinkedHashMap<Token, String> errors = listener.getErrors();

			for (Map.Entry<Token, String> error : errors.entrySet()) {
				Token token = error.getKey();
				String msg = error.getValue();
				ErrorToken errorToken = new ErrorToken(token.getText());

				// handle error messages
				if (msg.matches("extraneous input '-' expecting \\{(<EOF>, )?NOTE, '\\|', NEWLINE\\}")
						|| msg.matches("missing '\\|' at '-'"))
					message.append("Incomplete tune");

				if (msg.matches("extraneous input '\\\\n' expecting \\{'\\[', 'g', '\\|', DOUBLEBAR, FRET_NUM, '-'\\}"))
					message.append("Missing end of string");

				if (msg.matches("missing FRET_NUM at '-'") || msg.matches("extraneous input '.+' expecting FRET_NUM")
						|| msg.matches("mismatched input '.+' expecting FRET_NUM"))
					message.append("Missing Fret");

				if (msg.matches("no viable alternative at input '\\d+..+'"))
					message.append("Invalid input");

				// one char mismatches
				if (msg.matches("missing '.' at '.'"))
					message.append(String.format("Expected '%s' got '%s'", msg.split("'")[1], msg.split("'")[3]));

				if (msg.matches("mismatched input '.' expecting '.'"))
					message.append(String.format("Expected '%s' got '%s'", msg.split("'")[3], msg.split("'")[1]));

				if (msg.matches("extraneous input '.'.*"))
					message.append(String.format("Extraneous '%s'", msg.split("'")[1]));

				// if none of those errors are caught, notify invalid input
				if (message.length() == 0)
					message.append("Invalid input");

				leftComment = 0;
				rightComment = 0;

				for (int i = positions.size() - 1; i >= 0; i--) {
					if (positions.get(i) < token.getStartIndex()) {
						if (i % 2 == 0)
							rightComment++;
						else
							leftComment++;
					}
				}

				leastCommentCount = 3 * (leftComment + rightComment);
				start = token.getStartIndex() - (leastCommentCount) - 1;
				stop = token.getStopIndex() - (leastCommentCount) + 1;
				line = token.getLine() - leftComment - rightComment - 1;
				column = token.getCharPositionInLine() + 1;

				errorToken.setStart(start);
				errorToken.setStop(stop);
				errorToken.setLine(line);
				errorToken.setColumn(column);
				errorToken.setMessage(message.toString());
				errorTokens.add(errorToken);
				message.setLength(0);
			}
			errors.clear();
			showErrors(errorTokens);
		}
		// TODO: make sure input is well formed by this point.
		Score<GuitarStaff> sheet = sgs.visit(root);
		// initializing staffs in the sheet.
		sheet.forEach(s -> s.init(s));
		// next process the measures.
		sheet.processMeasures(metadata);
		// next process the duration of each note.
		sheet.getMeasures().forEach(m -> m.processDuration());
		return sheet;
	}

	private Score<GuitarStaff> processBass() throws InvalidInputException {
		// TODO: process of bass is the same as guitar for the most part. 
		return this.processGuitar();
	}

	private Score<DrumStaff> processDrum() throws InvalidInputException {
		final List<ErrorToken> errorTokens = new LinkedList<>();

		if (input == null || input.length() == 0) {
			ErrorToken errorToken = new ErrorToken();
			errorToken.setStart(-1);
			errorToken.setStop(-1);
			errorToken.setLine(-1);
			errorToken.setColumn(-1);
			errorToken.setMessage("Empty input");
			errorTokens.add(errorToken);
		}

		if (!errorTokens.isEmpty())
			showErrors(errorTokens);

		List<Integer> positions = new LinkedList<>();
		for (int i = 0; i < input.length() - 1; i++) {
			char c1 = input.charAt(i);
			char c2 = input.charAt(i + 1);
			if ((c1 == 47 && c2 == 42) || (c1 == 42 && c2 == 47))
				positions.add(i);
		}

		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		DrumTabLexer lexer = null;
		try {
			lexer = new DrumTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (lexer == null)
			return new Score<>();

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DrumTabParser parser = new DrumTabParser(tokens);
		ErrorListener listener = new ErrorListener();
		parser.addErrorListener(listener);
		ParseTree root = parser.sheet();

		if (listener.hasErrors()) {
			int start;
			int stop;
			int line;
			int column;
			int rightComment;
			int leftComment;
			int leastCommentCount;
			final StringBuilder message = new StringBuilder();
			final LinkedHashMap<Token, String> errors = listener.getErrors();

			for (Map.Entry<Token, String> error : errors.entrySet()) {
				Token token = error.getKey();
				@SuppressWarnings("unused")
				String msg = error.getValue();
				ErrorToken errorToken = new ErrorToken(token.getText());

				// handle error messages

				// if none of those errors are caught, notify invalid input
				if (message.length() == 0)
					message.append("Invalid input");

				leftComment = 0;
				rightComment = 0;

				for (int i = positions.size() - 1; i >= 0; i--) {
					if (positions.get(i) < token.getStartIndex()) {
						if (i % 2 == 0)
							rightComment++;
						else
							leftComment++;
					}
				}

				leastCommentCount = 3 * (leftComment + rightComment);
				start = token.getStartIndex() - (leastCommentCount) - 1;
				stop = token.getStopIndex() - (leastCommentCount) + 1;
				line = token.getLine() - leftComment - rightComment - 1;
				column = token.getCharPositionInLine() + 1;

				errorToken.setStart(start);
				errorToken.setStop(stop);
				errorToken.setLine(line);
				errorToken.setColumn(column);
				errorToken.setMessage(message.toString());
				errorTokens.add(errorToken);
				message.setLength(0);
			}
			errors.clear();
			showErrors(errorTokens);
		}
		// TODO: make sure input is well formed by this point.
		Score<DrumStaff> sheet = sds.visit(root);
		// set to 4 by default(per example. idk the standards)
		sheet.setDivision(4);
		// initializing staffs in the sheet.
		sheet.forEach(s -> s.init(s));
		// next process the measures.
		sheet.processMeasures(metadata);
		// next process the duration of each note.
		sheet.getMeasures().forEach(m -> m.processDuration());
		return sheet;
	}

	private static void showErrors(List<ErrorToken> errors) throws UnparseableInputException {
		final UnparseableInputException e = UnparseableInputException.get(errors);
		throw e;
	}

	private static String preprocessGuitar(String input) {
		if (input == null || input.length() == 0)
			return "";

		final StringBuilder tab = new StringBuilder();
		final List<String> guitarMetadata = new ArrayList<>();
		StringBuilder staffMeta = new StringBuilder();

		tab.append("\n");
		tab.append(input);
		tab.append("\n\n");
		StringBuffer commented = new StringBuffer(tab.toString());
		Matcher staffMatcher = Parser.bassPattern.matcher(tab.toString());
		Matcher outlierMatcher = Parser.outlierPlucked.matcher(tab.toString());

		// TODO: remove this
		//		System.out.println("==============spaced-input===============");
		//		System.out.println(tab.toString());
		//		System.out.println("=========================================");

		int count = 0;
		int prevIndex = 0;
		final String leftComment = "/*\n";
		final String rightComment = "*/\n";

		int offset = 0;
		while (outlierMatcher.find()) {
			// TODO: remove this
//			System.out.println(String.format("start-group::%d", 0));
//			System.out.print(outlierMatcher.group(0));
//			System.out.println(String.format("end-group::%d", 0));

			if (outlierMatcher.group(0).matches(Parser.COMMENTS)
					|| outlierMatcher.group(0).matches("(^(?=[ \t]*(\r?\n)+)[ \t]*)"))
				continue;

			commented.insert(outlierMatcher.start() + offset, leftComment);
			offset += 3;
			commented.insert(outlierMatcher.end() + offset, rightComment);
			offset += 3;
		}

		// TODO: remove this
		System.out.println("================commented===============");
		System.out.println(commented.toString());
		System.out.println("=========================================");

		// extract meta data
		while (staffMatcher.find() && outlierMatcher.find()) {
			staffMeta.append(input, prevIndex, staffMatcher.start()).append("staff::" + count++ + "\n");
			prevIndex = staffMatcher.end();
		}

		Pattern metaPattern = Pattern.compile("(.*\n){0,4}(staff::\\d+)(\n.*){0,4}", Pattern.MULTILINE);
		Matcher metaMatcher = metaPattern.matcher(staffMeta.toString());

		while (metaMatcher.find())
			guitarMetadata.add(metaMatcher.group(0));

		return commented.toString();
	}

	private static String preprocessBass(String input) {
		if (input == null || input.length() == 0)
			return "";

		final StringBuilder tab = new StringBuilder();
		final List<String> guitarMetadata = new ArrayList<>();
		StringBuilder staffMeta = new StringBuilder();

		tab.append("\n");
		tab.append(input);
		tab.append("\n\n");
		StringBuffer commented = new StringBuffer(tab.toString());
		Matcher staffMatcher = Parser.bassPattern.matcher(tab.toString());
		Matcher outlierMatcher = Parser.outlierPlucked.matcher(tab.toString());

		// TODO: remove this
		//		System.out.println("==============spaced-input===============");
		//		System.out.println(tab.toString());
		//		System.out.println("=========================================");

		int count = 0;
		int prevIndex = 0;
		final String leftComment = "/*\n";
		final String rightComment = "*/\n";

		int offset = 0;
		while (outlierMatcher.find()) {
			// TODO: remove this
//			System.out.println(String.format("start-group::%d", 0));
//			System.out.print(outlierMatcher.group(0));
//			System.out.println(String.format("end-group::%d", 0));

			if (outlierMatcher.group(0).matches(Parser.COMMENTS)
					|| outlierMatcher.group(0).matches("(^(?=[ \t]*(\r?\n)+)[ \t]*)"))
				continue;

			commented.insert(outlierMatcher.start() + offset, leftComment);
			offset += 3;
			commented.insert(outlierMatcher.end() + offset, rightComment);
			offset += 3;
		}

		// TODO: remove this
//		System.out.println("================commented===============");
//		System.out.println(commented.toString());
//		System.out.println("=========================================");

		// extract meta data
		while (staffMatcher.find() && outlierMatcher.find()) {
			staffMeta.append(input, prevIndex, staffMatcher.start()).append("staff::" + count++ + "\n");
			prevIndex = staffMatcher.end();
		}

		Pattern metaPattern = Pattern.compile("(.*\n){0,4}(staff::\\d+)(\n.*){0,4}", Pattern.MULTILINE);
		Matcher metaMatcher = metaPattern.matcher(staffMeta.toString());

		while (metaMatcher.find())
			guitarMetadata.add(metaMatcher.group(0));

		return commented.toString();
	}

	public static String preprocessDrum(String input) {
		if (input == null || input.length() == 0)
			return "";

		final StringBuilder tab = new StringBuilder();
		final List<String> drumMetadata = new ArrayList<>();
		StringBuilder staffMeta = new StringBuilder();

		tab.append("\n");
		tab.append(input);
		tab.append("\n\n");
		StringBuffer commented = new StringBuffer(tab.toString());
		Matcher staffMatcher = Parser.drumPattern.matcher(tab.toString());
		Matcher outlierMatcher = Parser.outlierPercussion.matcher(tab.toString());

		// TODO: remove this
//		System.out.println("==============spaced-input===============");
//		System.out.println(tab.toString());
//		System.out.println("=========================================");

		int count = 0;
		int prevIndex = 0;
		final String leftComment = "/*\n";
		final String rightComment = "*/\n";

		int offset = 0;
		while (outlierMatcher.find()) {
			// TODO: remove this
			System.out.println(String.format("start-group::%d", 0));
			System.out.print(outlierMatcher.group(0));
			System.out.println(String.format("end-group::%d", 0));

			if (outlierMatcher.group(0).matches(Parser.COMMENTS)
					|| outlierMatcher.group(0).matches("(^(?=[ \t]*(\r?\n)+)[ \t]*)"))
				continue;

			commented.insert(outlierMatcher.start() + offset, leftComment);
			offset += 3;
			commented.insert(outlierMatcher.end() + offset, rightComment);
			offset += 3;
		}

		// TODO: remove this
//		System.out.println("================commented===============");
//		System.out.println(commented.toString());
//		System.out.println("=========================================");

		// extract meta data
		while (staffMatcher.find() && outlierMatcher.find()) {
			staffMeta.append(input, prevIndex, staffMatcher.start()).append("staff::" + count++ + "\n");
			prevIndex = staffMatcher.end();
		}

		Pattern metaPattern = Pattern.compile("(.*\n){0,4}(staff::\\d+)(\n.*){0,4}", Pattern.MULTILINE);
		Matcher metaMatcher = metaPattern.matcher(staffMeta.toString());

		while (metaMatcher.find())
			drumMetadata.add(metaMatcher.group(0));

		return commented.toString();
	}
}