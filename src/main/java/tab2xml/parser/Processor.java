package tab2xml.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import tab2xml.antlr.GuitarTabLexer;
import tab2xml.antlr.GuitarTabParser;
import tab2xml.exceptions.InvalidInputException;
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
		preprocessGuitar();
		input += "\r\n";

		InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		GuitarTabLexer lexer = null;
		try {
			lexer = new GuitarTabLexer(CharStreams.fromStream(stream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lexer == null)
			return new Score();

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		GuitarTabParser parser = new GuitarTabParser(tokens);

		ParseTree root = parser.sheet();

		ParseTreeWalker walker = new ParseTreeWalker();
		ErrorListener listener = new ErrorListener();

		walker.walk(listener, root);

		// TODO alert user. LIFO concept will be used to display errors in order of occurrence
		LinkedList<Token> errors = listener.getErrNodes();

		if (!errors.isEmpty())
			showErrors(errors);

		SerializeScore ss = new SerializeScore();
		Score sheet = ss.visit(root);
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

	public void preprocessGuitar() {
		/* TODO:  
			- surround anything that is not a staff within comments.
			- error check simple input deformations(early stage)
			- send string to processor.
		*/
	}

	public void preprocessDrum() {
		/* TODO:  
			- surround anything that is not a staff within comments.
			- error check simple input deformations(early stage)
			- send string to processor.
		*/
	}

	public void preprocessBass() {
		/* TODO:  
			- surround anything that is not a staff within comments.
			- error check simple input deformations(early stage)
			- send string to processor.
		*/
	}

	private void showErrors(LinkedList<Token> errors) throws InvalidInputException {
		StringBuilder sb = new StringBuilder();
		while (!errors.isEmpty()) {
			Token t = errors.pop();

			int col = t.getCharPositionInLine();
			int line = t.getLine();
			String token = t.getText();

			// TODO: make mode detailed error message after we retrieve more data
			sb.append("Undexpected: \"" + token + "\"" + "(" + line + ":" + col + ")");
			sb.append("\n");
		}
		throw new InvalidInputException(sb.toString());
	}
}