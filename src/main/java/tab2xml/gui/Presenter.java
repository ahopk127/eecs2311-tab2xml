package tab2xml.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import javax.swing.filechooser.FileNameExtensionFilter;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.parser.Instrument;
import tab2xml.parser.Parser;

/**
 * The Tab2XML presenter, which handles event code. It acts as an intermediate
 * between the front-end (View) and back-end.
 *
 * @since 2021-01-18
 */
public final class Presenter {
	/** Preferred extensions for input files. */
	private static final FileNameExtensionFilter TEXT_TAB_FILE = new FileNameExtensionFilter(
			"Text tablature (*.txt)", "txt");
	private static final FileNameExtensionFilter MUSICXML_FILE = new FileNameExtensionFilter(
			"MusicXML (*.xml)", "xml");
	
	/**
	 * If the provided filepath has no extension, returns this path with the
	 * extension {@code preferredExtension}. Otherwise, return the original
	 * filepath.
	 * 
	 * @param preferredExtension preferred file extension, without the leading
	 *                           period
	 *
	 * @since 2021-02-25
	 */
	private static final Path withPreferredExtension(Path path,
			String preferredExtension) {
		final String filename = path.getFileName().toString();
		if (filename.contains("."))
			return path;
		else
			return path.resolveSibling(filename + "." + preferredExtension);
	}
	
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
	 * Converts the view's inputted text tab to MusicXML, and puts that text into
	 * the output.
	 * 
	 * @since 2021-01-18
	 */
	public void convert() {
		final String input = this.view.getInputText();
		final Instrument selectedInstrument = this.view.getSelectedInstrument();
		final Optional<String> output = this.convert(input, selectedInstrument);
		
		if (output.isEmpty())
			return;
		else {
			this.view.setOutputText(output.get());
		}
	}
	
	/**
	 * Converts text tab to MusicXML
	 *
	 * @param input              text tab input
	 * @param selectedInstrument selected instrument
	 * @return MusicXML output, or empty optional if parsing error occurred
	 * @since 2021-02-25
	 */
	private Optional<String> convert(String input,
			Instrument selectedInstrument) {
		final String musicXMLOutput;
		try {
			final Parser parser = new Parser(input, selectedInstrument);
			
			musicXMLOutput = parser.parse();
		} catch (final InvalidInputException e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error: Invalid Input", e.getMessage());
			return Optional.empty();
		} catch (final InvalidTokenException e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error: Invalid Token", e.getMessage());
			return Optional.empty();
		} catch (final Exception e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error",
					"An error occured during parsing: " + e.getMessage());
			return Optional.empty();
		}
		
		return Optional.of(musicXMLOutput);
	}
	
	/**
	 * Converts text tab to MusicXML, then saves it to a file.
	 * 
	 * @param showInView whether the converted MusicXML should be shown in the
	 *                   View and saved, or just saved
	 * 						
	 * @since 2021-02-25
	 */
	public void convertAndSave(boolean showInView) {
		// get input and output
		final String input = this.view.getInputText();
		final Instrument selectedInstrument = this.view.getSelectedInstrument();
		final Optional<String> output = this.convert(input, selectedInstrument);
		
		// if an error occurred, abort
		if (output.isEmpty())
			return;
		
		// set output text in view
		if (showInView) {
			this.view.setOutputText(output.get());
		}
		
		// get file to save to
		final Optional<Path> savePathInput = this.view
				.promptForFile(MUSICXML_FILE);
		if (savePathInput.isEmpty())
			return; // operation cancelled
			
		final Path savePath = withPreferredExtension(savePathInput.get(), "xml");
		
		// save to file
		try {
			Files.writeString(savePath, output.get());
		} catch (final IOException e) {
			this.view.showErrorMessage("I/O Error",
					"An error occured while saving to the selected file: "
							+ e.getMessage());
		}
	}
	
	/**
	 * Gets the text from a user-selected file and writes it to the view's input
	 * field.
	 * 
	 * @throws UnsupportedOperationException if the view does not support
	 *                                       {@link View#setInputText}
	 * 													
	 * @since 2021-02-25
	 */
	public void loadFromFile() {
		final Optional<Path> loadPath = this.view.promptForFile(TEXT_TAB_FILE);
		if (loadPath.isEmpty())
			return; // user cancelled, stop function
			
		try {
			this.view.setInputText(Files.readString(loadPath.get()));
		} catch (final IOException e) {
			this.view.showErrorMessage("I/O Error",
					"An error occured while reading from the selected file: "
							+ e.getMessage());
		}
	}
	
	/**
	 * Prompts the user for an output file, then saves the view's output text to
	 * that file.
	 * 
	 * @since 2021-02-25
	 */
	public void saveToFile() {
		final Optional<Path> savePathInput = this.view
				.promptForFile(MUSICXML_FILE);
		if (savePathInput.isEmpty())
			return; // operation cancelled
			
		final Path savePath = withPreferredExtension(savePathInput.get(), "xml");
		
		try {
			Files.writeString(savePath, this.view.getOutputText());
		} catch (final IOException e) {
			this.view.showErrorMessage("I/O Error",
					"An error occured while saving to the selected file: "
							+ e.getMessage());
		}
	}
}
