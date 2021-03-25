package tab2xml.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import javax.swing.filechooser.FileNameExtensionFilter;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.model.Instrument;
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
	 * If this is false, an error will occur upon trying to save an empty input
	 * or output. If this is true, these operations will be allowed.
	 */
	private static final boolean ALLOW_EMPTY_SAVING = false;
	
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
	
	/**
	 * The number of times this presenter has attempted to read from a file.
	 * <b>For testing only.</b>
	 */
	int fileReads = 0;
	
	/**
	 * The number of times this presenter has attempted to write to a file.
	 * <b>For testing only.</b>
	 */
	int fileWrites = 0;
	
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
	 * @return true if conversion was successful, false otherwise
	 * 
	 * @since 2021-01-18
	 */
	public boolean convert() {
		final String input = this.view.getInputText();
		final Instrument selectedInstrument = this.view.getSelectedInstrument();
		
		final Optional<String> output = this.convert(input, selectedInstrument);
		
		if (output.isEmpty())
			return false;
		else {
			this.view.setOutputText(output.get());
			return true;
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
		if (input.isBlank()) {
			this.view.showErrorMessage("Error: Empty Input",
					"Please input your text tab before converting.");
			return Optional.empty();
		}
		
		// convert tab
		final String musicXMLOutput;
		final Collection<ParsingWarning> warnings;
		try {
			final Parser parser = new Parser(input, selectedInstrument);
			this.view.setSelectedInstrument(parser.getDetectedInstrument());
			
			final var output = parser.parse();
			musicXMLOutput = output.getFirst();
			warnings = output.getSecond();
			
			// handle parsing errors
		} catch (final UnparseableInputException e) {
			this.view.onParseError(e);
			return Optional.empty();
		} catch (final InvalidInputException e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error: Invalid Input", e.getMessage());
			return Optional.empty();
		} catch (final InvalidTokenException e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error: Invalid Token", e.getMessage());
			return Optional.empty();
		} catch (final UnsupportedOperationException e) {
			e.printStackTrace();
			this.view.showErrorMessage("Unsupported Operation", e.getMessage());
			return Optional.empty();
		} catch (final Exception e) {
			e.printStackTrace();
			this.view.showErrorMessage("Error",
					"An error occured during parsing: \n" + e.getMessage());
			return Optional.empty();
		}
		
		// handle parsing warnings
		if (!warnings.isEmpty()) {
			this.view.handleParseWarnings(warnings);
		}
		
		return Optional.of(musicXMLOutput);
	}
	
	/**
	 * Converts text tab to MusicXML, then saves it to a file. If an I/O
	 * exception occurs, it is shown using {@link View#showErrorMessage} and
	 * {@code false} is returned.
	 * 
	 * @param showInView whether the converted MusicXML should be shown in the
	 *                   View and saved, or just saved
	 * @return {@code true} if both conversion and saving were successful,
	 *         {@code false} otherwise
	 * 
	 * @since 2021-02-25
	 */
	public boolean convertAndSave(boolean showInView) {
		// get input and output
		final String input = this.view.getInputText();
		final Instrument selectedInstrument = this.view.getSelectedInstrument();
		final Optional<String> output = this.convert(input, selectedInstrument);
		
		// if an error occurred, abort
		if (output.isEmpty())
			return false;
		
		// set output text in view
		if (showInView) {
			this.view.setOutputText(output.get());
		}
		
		// get file to save to
		final Optional<Path> savePath = this.view
				.promptForFile(MUSICXML_FILE, true)
				.map(path -> withPreferredExtension(path, "xml"));
		
		// save to file
		if (savePath.isPresent())
			return this.saveToFile(savePath.get(), output.get());
		else
			return false; // user did not select file
	}
	
	/**
	 * Gets and returns the text from the provided file and puts it into the
	 * view's input. If an I/O error occurs, it is shown using
	 * {@link View#showErrorMessage} and an empty Optional is returned.
	 *
	 * @since 2021-03-15
	 */
	Optional<String> loadFromFile(Path file) {
		this.fileReads++;
		try {
			// read file, using only Unix line endings (\n)
			return Optional.of(Files.readString(file).replaceAll("\\r\\n", "\n"));
		} catch (final IOException e) {
			this.view.showErrorMessage("I/O Error",
					"An error occured while reading from the selected file: "
							+ e.getMessage());
			return Optional.empty();
		}
	}
	
	/**
	 * Gets the text from a user-selected file and writes it to the view's input
	 * field. If an I/O exception occurs, it is shown using
	 * {@link View#showErrorMessage} and {@code false} is returned.
	 * 
	 * @throws UnsupportedOperationException if the view does not support
	 *                                       {@link View#setInputText}
	 * @return true if loading was successful
	 * 													
	 * @since 2021-02-25
	 */
	public boolean loadInput() {
		final Optional<Path> loadPath = this.view.promptForFile(TEXT_TAB_FILE,
				false);
		
		if (loadPath.isPresent()) {
			final Optional<String> result = this.loadFromFile(loadPath.get());
			result.ifPresent(this.view::setInputText);
			result.ifPresent(res -> Parser.getDetectedInstrument(res)
					.ifPresent(this.view::setSelectedInstrument));
			return result.isPresent();
		} else
			return false; // user did not provide a file
	}
	
	/**
	 * Prompts the user for an input file, then saves the view's input text to
	 * that file. If an I/O exception occurs, it is shown using
	 * {@link View#showErrorMessage} and {@code false} is returned.
	 *
	 * @return true if saving was successful
	 * @since 2021-03-15
	 */
	public boolean saveInput() {
		if (!ALLOW_EMPTY_SAVING && this.view.getInputText().isBlank()) {
			this.view.showErrorMessage("Empty Saving Error",
					"Cannot save empty input to a file.");
			return false;
		}
		
		final Optional<Path> savePathInput = this.view
				.promptForFile(TEXT_TAB_FILE, true)
				.map(path -> withPreferredExtension(path, "txt"));
		
		if (savePathInput.isPresent())
			return this.saveToFile(savePathInput.get(), this.view.getInputText());
		else
			return false; // user did not select file
	}
	
	/**
	 * Prompts the user for an output file, then saves the view's output text to
	 * that file. If an I/O exception occurs, it is shown using
	 * {@link View#showErrorMessage} and {@code false} is returned.
	 *
	 * @return true if saving was successful
	 * 
	 * @since 2021-02-25
	 */
	public boolean saveOutput() {
		if (!ALLOW_EMPTY_SAVING && this.view.getOutputText().isBlank()) {
			this.view.showErrorMessage("Empty Saving Error",
					"Cannot save empty output to a file.");
			return false;
		}
		
		final Optional<Path> savePathOutput = this.view
				.promptForFile(MUSICXML_FILE, true)
				.map(path -> withPreferredExtension(path, "xml"));
		
		if (savePathOutput.isPresent())
			return this.saveToFile(savePathOutput.get(),
					this.view.getOutputText());
		else
			return false; // user did not select file
	}
	
	/**
	 * Saves {@code text} to the provided file. If an I/O exception occurs, it is
	 * shown using {@link View#showErrorMessage} and {@code false} is returned.
	 *
	 * @since 2021-03-15
	 */
	private boolean saveToFile(Path file, String text) {
		this.fileWrites++;
		try {
			// prompt to user first
			if (Files.exists(file)) {
				final var result = this.view.promptOK("Warning: Overwriting File",
						"The file you are about to save to already exists.  Saving to it will erase its contents.  Are you sure you want to continue?");
				
				if (result.isEmpty() || result.get().equals(Boolean.FALSE))
					return false; // do not save to file
			}
			
			Files.writeString(file, text);
			return true;
		} catch (final IOException e) {
			this.view.showErrorMessage("I/O Error",
					"An error occured while saving to the selected file: "
							+ e.getMessage());
			return false;
		}
	}
}
