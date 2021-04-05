package tab2xml.gui;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import javax.swing.filechooser.FileNameExtensionFilter;

import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.model.Instrument;

/**
 * An automated View, whose actions are controlled by the program. Can be used
 * for testing the View and/or Presenter.
 *
 * @since 2021-01-20
 */
public final class ViewBot implements NarrowingView {
	/**
	 * The text inputted by the user. (represents whatever text area the user is
	 * using to input text)
	 */
	private String inputText;
	/**
	 * The text outputed by the program (represents whatever output the program
	 * gives to the user)
	 */
	private String outputText;
	/**
	 * The text of the narrowing "text box".
	 */
	private String narrowText;
	/**
	 * The instrument selected by the bot.
	 */
	private Instrument selectedInstrument;
	
	/**
	 * The file selected by the "user", will be used in promptForFile()
	 */
	private Path selectedFile;
	
	/**
	 * The value that will be returned by {@link #promptOK}.
	 */
	private Optional<Boolean> okPromptResult;
	
	/**
	 * Creates a {@code ViewBot}. The initial input and output text will be the
	 * empty string, while the initial selected instrument will be {@code null}.
	 * 
	 * @since 2021-01-29
	 */
	ViewBot() {
		this.inputText = "";
		this.outputText = "";
		this.selectedInstrument = null;
		this.selectedFile = null;
		this.okPromptResult = Optional.empty();
	}
	
	@Override
	public final String getInputText() {
		return this.inputText;
	}
	
	@Override
	public String getNarrowedText() {
		return this.narrowText;
	}
	
	@Override
	public final String getOutputText() {
		return this.outputText;
	}
	
	/**
	 * @return file which will be used in {@link #promptForFile}.
	 * @since 2021-02-25
	 */
	public final Path getSelectedFile() {
		return this.selectedFile;
	}
	
	@Override
	public final Instrument getSelectedInstrument() {
		return this.selectedInstrument;
	}
	
	@Override
	public void handleParseWarnings(Collection<ParsingWarning> warnings) {
		warnings.forEach(System.err::println);
	}
	
	@Override
	public void onParseError(UnparseableInputException error) {
		throw new RuntimeException(error);
	}
	
	@Override
	public Optional<Path> promptForFile(FileNameExtensionFilter preferredType,
			boolean forSave) {
		return Optional.ofNullable(this.selectedFile);
	}
	
	@Override
	public Optional<Boolean> promptOK(String title, String message) {
		return this.okPromptResult;
	}
	
	@Override
	public final void setInputText(String inputText) {
		this.inputText = inputText;
	}
	
	@Override
	public void setNarrowedText(String text) {
		this.narrowText = text;
	}
	
	/**
	 * @param okPromptResult the value returned by {@link #promptOK}
	 * @since 2021-03-17
	 */
	public final void setOkPromptResult(Optional<Boolean> okPromptResult) {
		this.okPromptResult = okPromptResult;
	}
	
	@Override
	public final void setOutputText(String outputText) {
		this.outputText = outputText;
	}
	
	/**
	 * @param selectedFile file which will be used in {@link #promptForFile}.
	 * @since 2021-02-25
	 */
	public final void setSelectedFile(Path selectedFile) {
		this.selectedFile = selectedFile;
	}
	
	@Override
	public final void setSelectedInstrument(Instrument selectedInstrument) {
		this.selectedInstrument = selectedInstrument;
	}
	
	@Override
	public void showErrorMessage(String title, String message) {
		throw new RuntimeException(message);
	}
}
