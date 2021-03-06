package tab2xml.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.stream.Collectors;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import com.formdev.flatlaf.FlatDarkLaf;

import tab2xml.Main;
import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.model.ErrorToken;
import tab2xml.model.Instrument;

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
			
			final List<DataFlavor> dataFlavours = event
					.getCurrentDataFlavorsAsList();
			
			if (dataFlavours.contains(DataFlavor.javaFileListFlavor)) {
				this.loadFromFile(event);
			} else if (dataFlavours.contains(DataFlavor.stringFlavor)) {
				this.loadFromText(event);
			}
		}
		
		/**
		 * Run when a file is dragged and dropped into the input.
		 *
		 * @param event drag and drop event
		 * @since 2021-04-09
		 */
		private synchronized void loadFromFile(DropTargetDropEvent event) {
			// get a list of the files that were dragged and dropped into the
			// text
			// area.
			final List<Path> droppedFiles;
			try {
				/*
				 * Using DataFlavor.javaFileListFlavor as an argument guarantees the
				 * runtime type of result will be List, and all its elements will be
				 * instances of File. Therefore, this cast will never cause an
				 * error.
				 */
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
				final Path droppedFile = droppedFiles.get(0);
				
				// sanity check if extension is not txt
				if (!droppedFile.toString().endsWith(".txt")) {
					final Optional<Boolean> response = AbstractSwingView.this
							.promptOK("Drag and Drop",
									"The file you are dragging and dropping is not a .txt file.  "
											+ "Are you sure you want to drag and drop this file?");
					if (response.isEmpty() || response.get() == false)
						return;
				}
				
				// load the file
				AbstractSwingView.this.presenter.loadFromFile(droppedFile)
						.ifPresent(AbstractSwingView.this::setInputText);
				AbstractSwingView.this.defaultDirectory = droppedFile.toFile();
			} else {
				AbstractSwingView.this.showErrorMessage("Wrong number of files.",
						"You can only use one file at a time.");
				return;
			}
		}
		
		/**
		 * Run whenever text is dragged and dropped into the input.
		 *
		 * @param event drag and drop event
		 * @since 2021-04-09
		 */
		private synchronized void loadFromText(DropTargetDropEvent event) {
			// this cast should always succeed, because the stringFlavor argument
			// guarantees the result is of type String.
			final String result;
			try {
				result = (String) event.getTransferable()
						.getTransferData(DataFlavor.stringFlavor);
			} catch (IOException | UnsupportedFlavorException e) {
				e.printStackTrace();
				AbstractSwingView.this.showErrorMessage(e.getClass() + " occurred.",
						e.getLocalizedMessage());
				return;
			}
			
			AbstractSwingView.this.setInputText(result);
		}
	}
	
	private static final HighlightPainter ERROR_PAINTER = new TabHighlightPainter(
			new Color(247, 19, 37, 50));
	private static final HighlightPainter WARNING_PAINTER = new TabHighlightPainter(
			Color.YELLOW);
	private static final int UNDO_LIMIT = 200;
	
	/**
	 * Enables the LaF look-and-feel in Swing, if it works.
	 * 
	 * @since 2021-03-10
	 */
	public static void enableLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (final Exception ex) {
			System.err.println("Failed to initialize LaF");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Creates a {@code GridBagConstraints} object.
	 *
	 * @since 2021-01-18
	 */
	public static GridBagConstraints gridBag(int x, int y) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		return gbc;
	}
	
	/**
	 * Creates a {@code GridBagConstraints} object.
	 *
	 * @since 2021-01-18
	 */
	public static GridBagConstraints gridBag(int x, int y, int width,
			int height) {
		final GridBagConstraints gbc = gridBag(x, y);
		gbc.gridwidth = width;
		gbc.gridheight = height;
		return gbc;
	}
	
	/**
	 * Creates a {@code GridBagConstraints} object.
	 *
	 * @since 2021-02-25
	 */
	public static GridBagConstraints gridBag(int x, int y, int width, int height,
			Insets insets) {
		final GridBagConstraints gbc = gridBag(x, y, width, height);
		gbc.insets = insets;
		return gbc;
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
	 * An undo manager.
	 */
	protected final UndoManager undoManager;
	
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
		AbstractSwingView.enableLookAndFeel();
		
		this.frame = new JFrame("TAB2XML " + Main.PROGRAM_VERSION);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.presenter = new Presenter(this);
		this.undoManager = new UndoManager();
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
		
		final ErrorToken firstError = error.getErrors().get(0);
		try {
			final Rectangle2D rec = this.getInput()
					.modelToView2D(firstError.getStart());
			this.getInput().scrollRectToVisible(rec.getBounds());
		} catch (final BadLocationException e) {
			System.err.format("BadLocation: %s%n", e);
			e.printStackTrace();
		}
		// highlight position of each error
		error.getErrors().forEach(token -> highlightToken(token,
				this.getInput().getHighlighter(), ERROR_PAINTER));
	}
	
	/**
	 * Prompts for a file. The default implementation uses JFileChooser.
	 * 
	 * @param preferredType preferred file type
	 * @param forSave       whether the prompt is for saving or loading
	 * @return file selected by user, or empty optional if no file was selected
	 */
	@Override
	public Optional<Path> promptForFile(FileNameExtensionFilter preferredType,
			boolean forSave) {
		final JFileChooser fc = new JFileChooser(this.defaultDirectory);
		fc.addChoosableFileFilter(preferredType);
		fc.setFileFilter(preferredType);
		
		final var result = forSave ? fc.showSaveDialog(this.frame)
				: fc.showOpenDialog(fc);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			this.defaultDirectory = fc.getCurrentDirectory();
			return Optional.of(fc.getSelectedFile().toPath());
		} else
			return Optional.empty();
	}
	
	/**
	 * Shows a Yes/No/Cancel prompt. The default implementation uses JOptionPane.
	 * 
	 * @param title   title of dialog
	 * @param message message to prompt the user with
	 */
	@Override
	public Optional<Boolean> promptOK(String title, String message) {
		switch (JOptionPane.showConfirmDialog(this.frame, message, title,
				JOptionPane.YES_NO_OPTION)) {
		case JOptionPane.YES_OPTION:
			return Optional.of(Boolean.TRUE);
		case JOptionPane.CANCEL_OPTION:
		case JOptionPane.NO_OPTION:
			return Optional.of(Boolean.FALSE);
		case JOptionPane.CLOSED_OPTION:
			return Optional.empty();
		default:
			throw new AssertionError("Should not happen.");
		}
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
	 * Sets up the input so that any edit to it clears all of the highlighting.
	 * 
	 * @since 2021-04-02
	 */
	protected final void setUpHighlightingRemoval() {
		this.getInput().getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				AbstractSwingView.this.getInput().getHighlighter()
						.removeAllHighlights();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				AbstractSwingView.this.getInput().getHighlighter()
						.removeAllHighlights();
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				AbstractSwingView.this.getInput().getHighlighter()
						.removeAllHighlights();
			}
		});
	}
	
	/**
	 * This method removes only highlights that use an instances of {@code TabHighlightPainter}
	 */
	protected final void removeAllHighlights() {
		Highlighter highlighter = this.getInput().getHighlighter();
		Highlighter.Highlight[] currHighlights = highlighter.getHighlights();
		
		for (int i = 0; i < currHighlights.length; i++) {
			if (currHighlights[i].getPainter() instanceof TabHighlightPainter)
				highlighter.removeHighlight(currHighlights[i]);
		}
	}
	
	/** 
	 * This method highlights all the invalid input and scrolls to the first occurrence.
	 * 
	 * @param errors a collection of errors form the Validation process.
	 */
	protected final void highlightErrors(SortedSet< ? extends ErrorToken> errors) {
		if (errors == null)
			return;
		if (errors.isEmpty())
			return;
		
		ErrorToken firstError = errors.first();
		try {
			Rectangle2D rec = this.getInput().modelToView2D(firstError.getStart());
			this.getInput().scrollRectToVisible(rec.getBounds());
		} catch(BadLocationException e) {
			System.err.format("BadLocation: %s%n", e);
			e.printStackTrace();
		}
		// remove all highlights before update
		removeAllHighlights();
		// highlight position of each error token
		errors.forEach(token -> highlightToken(token,
				this.getInput().getHighlighter(), ERROR_PAINTER));
	}
	
	/**
	 * Sets up the undo manager for the given input document.
	 */
	protected final void setUpUndoManager() {
		this.undoManager.setLimit(UNDO_LIMIT);
		this.getInput().getDocument().addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undoManager.addEdit(e.getEdit());
			}
		});
		
		this.getInput().getActionMap().put("Undo", new AbstractAction() {
			private static final long serialVersionUID = -2412630467987603551L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (AbstractSwingView.this.undoManager.canUndo()) {
						AbstractSwingView.this.undoManager.undo();
					}
				} catch (final CannotUndoException ex) {
					System.err.format("CannotUndoException: %s%n", ex);
				}
			}
		});
		
		this.getInput().getInputMap()
				.put(KeyStroke.getKeyStroke("control Z"), "Undo");
	}
	
	/**
	 * Sets up the redo manager for the given input document.
	 */
	protected final void setUpRedoManager() {
		this.getInput().getActionMap().put("Redo", new AbstractAction() {
			private static final long serialVersionUID = -2412630467987603551L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (AbstractSwingView.this.undoManager.canRedo()) {
						AbstractSwingView.this.undoManager.redo();
					}
				} catch (final CannotRedoException ex) {
					System.err.format("CannotRedoException: %s%n", ex);
				}
			}
		});
		
		this.getInput().getInputMap().put(KeyStroke.getKeyStroke("control shift Z"),
				"Redo");
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
