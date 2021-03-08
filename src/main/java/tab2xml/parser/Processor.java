package tab2xml.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import tab2xml.model.ErrorToken;
import tab2xml.model.Score;
import tab2xml.listeners.*;

/**
 * A processor which works in 2 stages serializing ASCII tablature for a
 * specified instrument.
 * 
 * @author amir
 */
public class Processor {
	private String input;
	private Instrument instrument;

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
		case DRUM:
			return processDrum();
		case BASS:
			return processBass();
		default:
			throw new UnsupportedOperationException("This instrument is not supported.");
		}
	}

	public Score processGuitar() throws InvalidInputException {
		input = preprocessGuitar(input);
		final LinkedList<ErrorToken> errorTokens = new LinkedList<>();

		System.out.println(input);

		LinkedList<Integer> positions = new LinkedList<>();

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
			int rightComment;
			int leftComment;
			int leastCommentCount;
			@SuppressWarnings("unused")
			final StringBuilder msg = new StringBuilder();
			final LinkedHashMap<Token, String> errors = listener.getErrors();

			for (Map.Entry<Token, String> error : errors.entrySet()) {
				Token token = error.getKey();
				String message = error.getValue();

				ErrorToken errorToken = new ErrorToken(token.getText());

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
				System.out.println("left: " + leftComment);
				System.out.println("right: " + rightComment);
				
				
				leastCommentCount = leftComment * 3 + rightComment * 2;

				start = token.getStartIndex() - (leastCommentCount);
				stop = token.getStopIndex() - (leastCommentCount) + 1;

				System.out.println("start: " + start);
				System.out.println("stop: " + stop);

				errorToken.setStart(start);
				errorToken.setStop(stop);
				errorToken.setMessage(message);
				errorTokens.add(errorToken);
			}
			errors.clear();
			showErrors(errorTokens);
		}
		SerializeScore ss = new SerializeScore();
		Score sheet = ss.visit(root);
		System.out.println();
		return sheet;
	}

	public Score processDrum() {
		// TODO process the sheet for drum
		return null;
	}

	public Score processBass() {
		// TODO process the sheet for bass
		return null;
	}

	private String preprocessGuitar(String input) {
		input += "\r\n";

		final ArrayList<String> guitarMetadata = new ArrayList<>();
		guitarMetadata.clear();

		if (input == null || input.length() == 0)
			return "";

		String pattern = "(^(?!(((^(?!(([a-gA-G]#?)?(\\||-)).*?\\|).*$)+\\n)+)).*\\n)+";
		StringBuilder commentedInput = new StringBuilder();
		StringBuilder staffMeta = new StringBuilder();

		Pattern staffPattern = Pattern.compile(pattern, Pattern.MULTILINE);
		Matcher staffMatcher = staffPattern.matcher(input);

		int count = 0;
		int prevIndex = 0;
		final String leftComment = "/*\n";
		final String rightComment = "*/\n";
		StringBuffer sb = new StringBuffer();

		while (staffMatcher.find()) {
			sb.append(leftComment);
			sb.append(input.substring(prevIndex, staffMatcher.start() - 1));
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

	private void showErrors(LinkedList<ErrorToken> errors) throws UnparseableInputException {
		UnparseableInputException e = UnparseableInputException.get(errors);
		throw e;
	}
}