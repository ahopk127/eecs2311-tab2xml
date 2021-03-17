package tab2xml.gui;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
 * This class also assumes a text box (or other {@link JTextComponent}) is used
 * to store the input and output.
 *
 * @since 2021-03-15
 */
public abstract class AbstractSwingView implements View {
	/**
	 * An object that can be used to enable file drag-and-drop.
	 *
	 * @since 2021-03-15
	 */
	private final class FileDragDropTarget extends DropTarget {
		private static final long serialVersionUID = -281039257803497320L;
		
		@Override
		public synchronized void drop(DropTargetDropEvent event) {
			event.acceptDrop(DnDConstants.ACTION_COPY);
			
			// get a list of the files that were dragged and dropped into the text
			// area.
			final List<Path> droppedFiles;
			try {
				// Using DataFlavor.javaFileListFlavor as an argument guarantees the
				// runtime type of result will be List, and all its elements will be
				// instances of File. Therefore, this cast will never cause an
				// error.
				@SuppressWarnings("unchecked")
				final List<File> result = (List<File>) event.getTransferable()
						.getTransferData(DataFlavor.javaFileListFlavor);
				
				// convert File to the more useful Path
				droppedFiles = result.stream().map(File::toPath)
						.collect(Collectors.toList());
			} catch (final IOException | UnsupportedFlavorException e) {
				e.printStackTrace();
				AbstractSwingView.this.showErrorMessage(e.getClass() + " occurred.",
						e.getLocalizedMessage());
				return;
			}
			
			// read files
			if (droppedFiles.size() == 1) {
				AbstractSwingView.this.presenter.loadFromFile(droppedFiles.get(0))
						.ifPresent(AbstractSwingView.this::setInputText);
				AbstractSwingView.this.defaultDirectory = droppedFiles.get(0)
						.toFile();
			} else {
				AbstractSwingView.this.showErrorMessage("Wrong number of files.",
						"You can only use one file at a time.");
				return;
			}
		}
	}
	
	private static final HighlightPainter ERROR_PAINTER = new DefaultHighlightPainter(
			Color.RED);
	private static final HighlightPainter WARNING_PAINTER = new DefaultHighlightPainter(
			Color.YELLOW);
	
	/**
	 * Enables the system look-and-feel in Swing, if it works.
	 * 
	 * @since 2021-03-10
	 */
	public static void enableSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// system view couldn't be installed - not the end of the world
			// stuff will just look a bit worse
			System.err.println("Failed to enable system look-and-feel.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Highlights a token in the text box.
	 *
	 * @param token   token to highlight
	 * @param painter highlighting settings
	 * @since 2021-03-05
	 */
	private static final void highlightToken(ErrorToken token,
			Highlighter highlighter, HighlightPainter painter) {
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
	private final JComboBox<Instrument> instrumentSelector;
	
	/**
	 * The default/starting directory for the file chooser.
	 */
	private File defaultDirectory = null;
	
	/**
	 * Creates the {@code AbstractSwingView}, initializing its frame and
	 * presenter
	 * 
	 * @implSpec Any implementation of {@code AbstractSwingView} should probably
	 *           (but is not required to) do the following in its constructor:
	 *           <ul>
	 *           <li>Execute the method {@link #setUpFileDragAndDrop()} after the
	 *           input text box is created to set up file drag-and-drop.
	 *           <li>Add the return value of {@link #getInstrumentSelector()} to
	 *           the frame in the desired panel/location.
	 *           </ul>
	 *
	 * @since 2021-03-15
	 */
	protected AbstractSwingView() {
		AbstractSwingView.enableSystemLookAndFeel();
		
		this.frame = new JFrame("TAB2XML " + Main.PROGRAM_VERSION);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.presenter = new Presenter(this);
		
		this.instrumentSelector = new JComboBox<>(Instrument.values());
	}
	
	/**
	 * @return text box that contains input text
	 * @since 2021-03-15
	 * @implNote This method is used by the default implementations of many of
	 *           this class's methods. Overriding it will likely change the
	 *           behaviour of many of these methods.
	 */
	protected abstract JTextComponent getInput();
	
	/**
	 * {@inheritDoc}
	 * 
	 * @implSpec The code of the default implementation of this method is
	 *           equivalent to {@code return this.getInput().getText()}.
	 */
	@Override
	public String getInputText() {
		return this.getInput().getText();
	}
	
	/**
	 * @return component used to select instrument
	 * @implNote The return value of this method is used for the default
	 *           implementation of {@link #getSelectedInstrument} and
	 *           {@link #setSelectedInstrument}.
	 * @since 2021-03-15
	 */
	protected final JComponent getInstrumentSelector() {
		return this.instrumentSelector;
	}
	
	/**
	 * @return text box that contains output text
	 * @since 2021-03-15
	 * @implNote This method is used by the default implementations of many of
	 *           this class's methods. Overriding it will likely change the
	 *           behaviour of many of these methods.
	 */
	protected abstract JTextComponent getOutput();
	
	/**
	 * Gets the text outputted to the user.
	 * 
	 * @implSpec The code of the default implementation of this method is
	 *           equivalent to {@code return this.getOutput().getText()}.
	 */
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
	
	/**
	 * Shows an error message and highlights the approximate location of all
	 * warnings in the input text component.
	 * 
	 * @implSpec The input's highlighter
	 *           ({@code this.getInput().getHighlighter()}) is used to highlight
	 *           warnings. Overriding {@link #getInput()} will result in a
	 *           different text area being highlighted.
	 */
	@Override
	public void handleParseWarnings(Collection<ParsingWarning> warnings) {
		// show dialog box
		View.super.handleParseWarnings(warnings);
		
		// highlight position of each warning
		warnings.stream().map(ParsingWarning::getLocation)
				.flatMap(Optional::stream).forEach(token -> highlightToken(token,
						this.getInput().getHighlighter(), WARNING_PAINTER));
	}
	
	/**
	 * Shows an error message and highlights the approximate location of all
	 * errors in the input text component.
	 * 
	 * @implSpec The input's highlighter
	 *           ({@code this.getInput().getHighlighter()}) is used to highlight
	 *           errors. Overriding {@link #getInput()} will result in a
	 *           different text area being highlighted.
	 */
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
		final JFileChooser fc = new JFileChooser(this.defaultDirectory);
		fc.addChoosableFileFilter(preferredType);
		fc.setFileFilter(preferredType);
		
		if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			this.defaultDirectory = fc.getCurrentDirectory();
			return Optional.of(fc.getSelectedFile().toPath());
		} else
			return Optional.empty();
	}
	
	/**
	 * Sets the view's input text to {@code text}.
	 * 
	 * @implSpec the default implementation of this method is equivalent to
	 *           {@code this.getInput().setText(text)}.
	 */
	@Override
	public void setInputText(String text) {
		this.getInput().setText(text);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @implSpec the default implementation of this method is equivalent to
	 *           {@code this.getOutput().setText(text)}.
	 */
	@Override
	public void setOutputText(String text) {
		this.getOutput().setText(text);
	}
	
	@Override
	public void setSelectedInstrument(Instrument instrument) {
		this.instrumentSelector.setSelectedItem(instrument);
	}
	
	/**
	 * Sets up drag-and-drop for files. This method should be called in the
	 * constructor (or other initializing method), after the input text box is
	 * created.
	 * 
	 * @implNote This implementation calls the overridable method
	 *           {@link #getInput()} and uses its
	 *           {@link JTextComponent#setDropTarget} method to set up
	 *           drag-and-drop. The drag-and-drop functionality enabled by this
	 *           method relies on the {@link #setInputText} method to set the
	 *           input text to the dropped file's contents.
	 * 				
	 * @since 2021-03-15
	 */
	protected final void setUpFileDragAndDrop() {
		this.getInput().setDropTarget(new FileDragDropTarget());
	}
	
	/**
	 * Shows an error message. The default implementation shows the error in a
	 * {@link JOptionPane} dialog.
	 * 
	 * @param title   title of error dialog
	 * @param message message shown in error dialog
	 */
	@Override
	public void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(this.frame, message, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
