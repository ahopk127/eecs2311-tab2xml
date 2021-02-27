package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Path;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		
		// create components
		final JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		this.frame.add(masterPanel);
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// text box
		this.textBox = new PromptingTextArea(
				"Enter text tab or load it from a file...", 24, 80);
		this.textBox.setBorder(new LineBorder(Color.BLACK));
		this.textBox.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		FileDragDropTarget.enableDragAndDrop(this.textBox);
		masterPanel.add(new JScrollPane(this.textBox), BorderLayout.CENTER);
		
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
