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

import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.listeners.*;
import tab2xml.model.ErrorToken;
import tab2xml.model.Instrument;
import tab2xml.model.Score;

/**
 * A processor which works in 2 stages serializing ASCII tablature for a
 * specified instrument.
 * 
 * @author amir
 */
public class Processor {
	private String input;
	private Instrument instrument;
	private static final SerializeGuitarScore sgs = new SerializeGuitarScore();
	private static final SerializeDrumScore sds = new SerializeDrumScore();

	/**
	 * Construct a processor object for an input string and instrument.
	 * 
	 * @param input      string representing ASCII tablature
	 * @param instrument the type of instrument for the tablature
	 */
	public Processor(String input, Instrument instrument) {
		this.input = input;
		this.instrument = instrument;
	}

	/**
	 * Delegate processing based on instrument.
	 *
	 * @return a score with serialized
	 * @throws InvalidInputException if invalid input occurs
	 */
	public Score process() throws InvalidInputException {
		switch (instrument) {
		case GUITAR:
			return processGuitar();
		case BASS:
			return processBass();
		case DRUM:
			return processDrum();
		default:
			throw new UnsupportedOperationException("This instrument is not supported.");
		}
	}

	public Score processGuitar() throws InvalidInputException {
		final List<ErrorToken> errorTokens = new LinkedList<>();

		if (input == null || input.length() == 0) {
			ErrorToken errorToken = new ErrorToken();
			errorToken.setStart(0);
			errorToken.setStop(0);
			errorToken.setLine(0);
			errorToken.setColumn(0);
			errorToken.setMessage("Empty input");
			errorTokens.add(errorToken);
		}

		if (!errorTokens.isEmpty())
			showErrors(errorTokens);

		// TODO: REMOVE THIS
		//						System.out.println("-----------------------");
		//						System.out.println(input);
		//						System.out.println("-----------------------");
		input = preprocessGuitar(input);
		// TODO: REMOVE THIS
		//						System.out.println("-----------------------");
		//						System.out.println(input);
		//						System.out.println("-----------------------");

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
			return new Score();

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
				line = token.getLine() - leftComment - rightComment;
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
		Score sheet = sgs.visit(root);
		return sheet;
	}

	public Score processBass() throws InvalidInputException {
		// TODO: process of bass is the same as guitar for the most part. we can avoid code duplication and change the varying 
		// other parts that are unique to bass vs guitar
		return processGuitar();
	}

	public static Score processDrum() {
		// TODO process the sheet for drum
		return null;
	}

	private static String preprocessGuitar(String input) {
		if (input == null || input.length() == 0)
			return "";

		input += "\n";
		final List<String> guitarMetadata = new ArrayList<>();
		StringBuilder commentedInput = new StringBuilder();
		StringBuilder staffMeta = new StringBuilder();
		
		Matcher staffMatcher = Parser.guitarPattern.matcher(input);

		int count = 0;
		int prevIndex = 0;
		final String leftComment = "/*\n";
		final String rightComment = "*/\n";
		StringBuffer sb = new StringBuffer();

		while (staffMatcher.find()) {
			sb.append(leftComment);
			sb.append(input.substring(prevIndex, staffMatcher.start()));
			sb.append(rightComment);
			commentedInput.append(sb.toString());
			commentedInput.append(staffMatcher.group(0));

			sb.setLength(0);
			staffMeta.append(input, prevIndex, staffMatcher.start()).append("staff::" + count++ + "\n");
			prevIndex = staffMatcher.end();
		}

		if (prevIndex < input.length()) {
			sb.append(leftComment);
			sb.append(input.substring(prevIndex, input.length()));
			sb.append(rightComment);
			commentedInput.append(sb.toString());
			sb.setLength(0);
			staffMeta.append(input, prevIndex + 1, input.length());
		}

		Pattern metaPattern = Pattern.compile("(.*\\n){0,4}(staff::\\d+)(\\n.*){0,4}", Pattern.MULTILINE);
		Matcher metaMatcher = metaPattern.matcher(staffMeta.toString());

		while (metaMatcher.find())
			guitarMetadata.add(metaMatcher.group(0));

		return commentedInput.toString();
	}

	@SuppressWarnings("unused")
	private void preprocessDrum() {
		/* TODO:  preprocess drum
			- surround anything that is not a staff within comments.
			- error check simple input deformations(early stage)
			- send string to processor.
		*/
	}

	@SuppressWarnings("unused")
	private void preprocessBass() {
		/* TODO: preprocess bass  
			- surround anything that is not a staff within comments.
			- error check simple input deformations(early stage)
			- send string to processor.
		*/
	}

	private static void showErrors(List<ErrorToken> errors) throws UnparseableInputException {
		UnparseableInputException e = UnparseableInputException.get(errors);
		throw e;
	}
}