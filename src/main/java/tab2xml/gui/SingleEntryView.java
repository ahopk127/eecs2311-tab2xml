package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.HighlightPainter;

import org.antlr.v4.runtime.Token;

import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
import tab2xml.parser.Instrument;

/**
 * A view with a single text box, handling both input and output.
 *
 * @since 2021-01-18
 */
final class SingleEntryView implements View {
	/**
	 * Whether the text box is storing input text or output text.
	 *
	 * @since 2021-01-29
	 */
	private static enum State {
		INPUT {
			@Override
			public void enable(SingleEntryView view) {
				view.textBoxState = INPUT;
				view.convertButton.setEnabled(true);
				view.revertButton.setEnabled(false);
				view.saveFileButton.setText("Convert and Save");
			}
		},
		OUTPUT {
			@Override
			public void enable(SingleEntryView view) {
				view.textBoxState = OUTPUT;
				view.convertButton.setEnabled(false);
				view.revertButton.setEnabled(true);
				view.saveFileButton.setText("     Save to File     ");
			}
		};
		
		/**
		 * Enables this state in the given view.
		 */
		public abstract void enable(SingleEntryView v);
	}
	
//	private static final JPanel fillerPanel() {
//		final JPanel panel = new JPanel();
//		panel.setOpaque(false);
//		return panel;
//	}
	
	/**
	 * Creates a {@code GridBagConstraints} object.
	 *
	 * @since 2021-01-18
	 */
	private static GridBagConstraints gridBag(int x, int y) {
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
	private static GridBagConstraints gridBag(int x, int y, int width,
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
	private static GridBagConstraints gridBag(int x, int y, int width,
			int height, Insets insets) {
		final GridBagConstraints gbc = gridBag(x, y, width, height);
		gbc.insets = insets;
		return gbc;
	}
	
	/**
	 * Creates and opens a View.
	 *
	 * @since 2021-01-18
	 */
	public static void main(String[] args) {
		View.createView(View.ViewType.SINGLE_ENTRY);
	}
	
	/** The frame that the GUI is displayed on. */
	private final JFrame frame;
	
	/** The presenter that handles the view's input */
	private final Presenter presenter;
	/** The text box that handles both input and output. */
	private final PromptingTextArea textBox;
	/** The dropdown box to select the instrument. */
	private final JComboBox<Instrument> instrumentSelection;
	/** The button that converts tab to XML. */
	private final JButton convertButton;
	/** The undo button for conversion. */
	private final JButton revertButton;
	
	/** The button that saves output text to files. */
	private final JButton saveFileButton;
	/** What kind of text is stored in the text box */
	private State textBoxState;
	
	/**
	 * The input text that was inputted before a conversion. This variable is
	 * used for the "Undo Conversion" button.
	 */
	private String previousInputText;
	
	/**
	 * Creates the {@code SingleEntryView}.
	 * 
	 * @since 2021-01-18
	 */
	public SingleEntryView() {
		this.frame = new JFrame("Tab2XML");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.presenter = new Presenter(this);
		
		final String nativeLF = UIManager.getSystemLookAndFeelClassName();
		
		// Install the look and feel
		try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (final ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (final UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// load images
		final Image bgImage;
		try {
			bgImage = ImageIO
					.read(Path.of("src/main/resources/appmap.png").toFile());
		} catch (final IOException e) {
			throw new AssertionError("Image loading failed.", e);
		}
		
		final JPanel backgroundPanel = new BackgroundImagePanel(bgImage);
		backgroundPanel.setLayout(new GridLayout(1, 1));
		this.frame.add(backgroundPanel);
		
		// create components
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		backgroundPanel.add(mainPanel);
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// text box
		this.textBox = new PromptingTextArea(
				"Enter text tab or load it from a file...", 24, 80);
		this.textBox.setBorder(new LineBorder(Color.BLACK));
		this.textBox.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		FileDragDropTarget.enableDragAndDrop(this.textBox);
		this.textBox.addCaretListener(
				e -> this.textBox.getHighlighter().removeAllHighlights());
		mainPanel.add(new JScrollPane(this.textBox), BorderLayout.CENTER);
		
		// buttons
		final Insets buttonInsets = new Insets(3, 8, 3, 8);
		
		final JButton loadFileButton = new JButton("Load From File");
		loadFileButton.addActionListener(e -> this.presenter.loadFromFile());
		buttonPanel.add(loadFileButton, gridBag(1, 0, 1, 1, buttonInsets));
		
		this.convertButton = new JButton("Convert");
		this.convertButton.addActionListener(e -> this.presenter.convert());
		buttonPanel.add(this.convertButton, gridBag(2, 0, 1, 1, buttonInsets));
		
		this.revertButton = new JButton("Undo Conversion");
		this.revertButton
				.addActionListener(e -> this.setInputText(this.previousInputText));
		buttonPanel.add(this.revertButton, gridBag(3, 0, 1, 1, buttonInsets));
		
		this.saveFileButton = new JButton("Save to File");
		this.saveFileButton.addActionListener(e -> {
			if (this.textBoxState == State.INPUT) {
				this.presenter.convertAndSave(false);
			} else {
				this.presenter.saveToFile();
			}
		});
		buttonPanel.add(this.saveFileButton, gridBag(4, 0, 1, 1, buttonInsets));
		
		// combo boxes
		this.instrumentSelection = new JComboBox<>(Instrument.values());
		buttonPanel.add(this.instrumentSelection,
				gridBag(0, 0, 1, 1, buttonInsets));
		
		// background
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(mainPanel);
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
//		backgroundPanel.add(fillerPanel());
		
		// set the frame to INPUT state.
		State.INPUT.enable(this);
		
		// give everything the correct size
		this.frame.pack();
		
		loadFileButton.requestFocusInWindow();
		
		// open the window
		this.frame.setVisible(true);
	}
	
	@Override
	public String getInputText() {
		return this.textBoxState == State.INPUT ? this.textBox.getText()
				: this.previousInputText;
	}
	
	@Override
	public String getOutputText() {
		return this.textBoxState == State.OUTPUT ? this.textBox.getText() : "";
	}
	
	@Override
	public Instrument getSelectedInstrument() {
		// The only objects in this list are Instrument instances, so the cast
		// should work.
		return (Instrument) this.instrumentSelection.getSelectedItem();
	}
	
	@Override
	public void handleParseWarnings(Collection<ParsingWarning> warnings) {
		// show dialog box
		View.super.handleParseWarnings(warnings);
		
		// highlight position of each warning
		final HighlightPainter painter = new DefaultHighlightPainter(
				Color.YELLOW);
		warnings.stream().map(ParsingWarning::getLocation)
				.flatMap(Optional::stream)
				.forEach(token -> this.highlightToken(token, painter));
	}
	
	/**
	 * Highlights a token in the text box.
	 *
	 * @param token   token to highlight
	 * @param painter highlighting settings
	 * @since 2021-03-05
	 */
	private void highlightToken(Token token, HighlightPainter painter) {
		try {
			this.textBox.getHighlighter().addHighlight(token.getStartIndex(),
					token.getStopIndex(), painter);
		} catch (final BadLocationException e) {
			throw new AssertionError("Invalid token " + token, e);
		}
	}
	
	@Override
	public void onParseError(UnparseableInputException error) {
		// show dialog box
		View.super.onParseError(error);
		
		// highlight position of each error
		final HighlightPainter painter = new DefaultHighlightPainter(Color.RED);
		error.getErrors().forEach(token -> this.highlightToken(token, painter));
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
		this.previousInputText = null;
		if (text.isBlank()) {
			this.textBox.setPrompting(true);
		} else {
			this.textBox.setText(text);
		}
		State.INPUT.enable(this);
	}
	
	@Override
	public void setOutputText(String text) {
		this.previousInputText = this.textBox.isPrompting() ? ""
				: this.textBox.getText();
		this.textBox.setText(text);
		State.OUTPUT.enable(this);
	}
	
	@Override
	public void setSelectedInstrument(Instrument instrument) {
		this.instrumentSelection.setSelectedItem(instrument);
	}
	
	@Override
	public void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(this.frame, message, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
