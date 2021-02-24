package tab2xml.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import tab2xml.parser.Instrument;
import tab2xml.parser.Parser;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;

/**
 * The Tab2XML presenter, which handles event code. It acts as an intermediate
 * between the front-end (View) and back-end.
 *
 * @since 2021-01-18
 */
public final class Presenter {
	/** The view that this presenter takes input from and sends output to. */
	private final View view;

	/**
	 * Creates the presenter. Should be called from the view's constructor.
	 *
	 * @param view view associated with this presenter
	 * @since 2021-01-18
	 */
	public Presenter(View view) {
		this.view = view;
	}

	/**
	 * Converts the inputted text tab to MusicXML
	 * 
	 * @since 2021-01-18
	 */
	public void convert() {
		final String textTabInput = this.view.getInputText();
		final Instrument selectedInstrument = this.view.getSelectedInstrument();
		String musicXMLOutput;
		try {
			final Parser parser = new Parser(textTabInput, selectedInstrument);

			musicXMLOutput = parser.parse();
		} catch (final InvalidInputException e) {
			this.view.showErrorMessage("Error: Invalid Input",
					e.getLocalizedMessage());
			e.printStackTrace();
			return;
		} catch (final InvalidTokenException e) {
			this.view.showErrorMessage("Error: Invalid Token",
					e.getLocalizedMessage());
			e.printStackTrace();
			return;
		}

		this.view.setOutputText(musicXMLOutput);
	}

	/**
	 * Gets the text from a file and writes it to the view's input field.
	 * 
	 * @param path file to read from
	 * @throws IOException                   if the file reading creates an
	 *                                       {@code IOException}
	 * @throws UnsupportedOperationException if the view does not support
	 *                                       {@link View#setInputText}
	 * @since 2021-01-29
	 */
	public void loadFromFile(Path path) throws IOException {
		// only works in Java 11 or later
		this.view.setInputText(Files.readString(path));
	}

	/**
	 * Writes the view's output text to a file.
	 *
	 * @param path
	 * @throws IOException                   if the file writing causes an
	 *                                       {@code IOException}
	 * @throws UnsupportedOperationException if the view does not support
	 *                                       {@link View#getOutputText}
	 * @since 2021-01-29
	 */
	public void saveToFile(Path path) throws IOException {
		// only works in Java 11 or later
		Files.writeString(path, this.view.getOutputText());
	}
}
