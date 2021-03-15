package tab2xml.gui;

import java.awt.Color;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;

import tab2xml.Main;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.model.guitar.ErrorToken;
import tab2xml.parser.Instrument;

/**
 * A graphical View implemented in Swing.
 * <p>
 * This class also assumes a text box is used to store the input and output.
 *
 * @since 2021-03-15
 */
abstract class AbstractSwingView implements View {
	private static final HighlightPainter ERROR_PAINTER = new DefaultHighlightPainter(
			Color.RED);
	private static final HighlightPainter WARNING_PAINTER = new DefaultHighlightPainter(
			Color.YELLOW);
	
	/**
	 * Highlights a token in the text box.
	 *
	 * @param token   token to highlight
	 * @param painter highlighting settings
	 * @since 2021-03-05
	 */
	private static void highlightToken(ErrorToken token, Highlighter highlighter,
			HighlightPainter painter) {
		try {
			highlighter.addHighlight(token.getStart(), token.getStop(), painter);
		} catch (final BadLocationException e) {
			throw new AssertionError("Invalid token " + token, e);
		}
	}
	
	/**
	 * The frame where everything is on.
	 */
	protected final JFrame frame;
	/**
	 * The presenter associated with this View.
	 */
	protected final Presenter presenter;
	
	/**
	 * A combo box used to select the instrument.
	 */
	protected final JComboBox<Instrument> instrumentSelector;
	
	/**
	 * Creates the {@code AbstractSwingView}, initializing its frame and
	 * presenter
	 *
	 * @since 2021-03-15
	 */
	protected AbstractSwingView() {
		this.frame = new JFrame("TAB2XML " + Main.PROGRAM_VERSION);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.presenter = new Presenter(this);
		
		this.instrumentSelector = new JComboBox<>(Instrument.values());
	}
	
	/**
	 * @return text box that contains input text
	 * @since 2021-03-15
	 */
	protected abstract JTextComponent getInput();
	
	@Override
	public String getInputText() {
		return this.getInput().getText();
	}
	
	/**
	 * @return text box that contains output text
	 * @since 2021-03-15
	 */
	protected abstract JTextComponent getOutput();
	
	@Override
	public String getOutputText() {
		return this.getOutput().getText();
	}
	
	@Override
	public Instrument getSelectedInstrument() {
		// The only objects in this list are Instrument instances, so the cast
		// should work.
		return (Instrument) this.instrumentSelector.getSelectedItem();
	}
	
	@Override
	public void handleParseWarnings(Collection<ParsingWarning> warnings) {
		// show dialog box
		View.super.handleParseWarnings(warnings);
		
		// highlight position of each warning
		warnings.stream().map(ParsingWarning::getLocation)
				.flatMap(Optional::stream).forEach(token -> highlightToken(token,
						this.getInput().getHighlighter(), WARNING_PAINTER));
	}
	
	@Override
	public void onParseError(UnparseableInputException error) {
		// show dialog box
		View.super.onParseError(error);
		
		// highlight position of each error
		error.getErrors().forEach(token -> highlightToken(token,
				this.getInput().getHighlighter(), ERROR_PAINTER));
	}
	
	@Override
	public Optional<Path> promptForFile(FileNameExtensionFilter preferredType) {
		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(preferredType);
		fc.setFileFilter(preferredType);
		
		if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION)
			return Optional.of(fc.getSelectedFile().toPath());
		else
			return Optional.empty();
	}
	
	@Override
	public void setInputText(String text) {
		this.getInput().setText(text);
	}
	
	@Override
	public void setOutputText(String text) {
		this.getOutput().setText(text);
	}
	
	@Override
	public void setSelectedInstrument(Instrument instrument) {
		this.instrumentSelector.setSelectedItem(instrument);
	}
	
	@Override
	public void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(this.frame, message, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
