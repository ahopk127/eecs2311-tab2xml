package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import tab2xml.parser.Instrument;

/**
 * A view with a single text box, handling both input and output.
 *
 * @since 2021-01-18
 */
public final class SingleEntryView implements View {
	/**
	 * Whether the text box is storing input text or output text.
	 *
	 * @since 2021-01-29
	 */
	private static enum State {
		INPUT {
			@Override
			public void enable(SingleEntryView v) {
				v.textBoxState = INPUT;
				v.convertButton.setEnabled(true);
				v.revertButton.setEnabled(false);
			}
		},
		OUTPUT {
			@Override
			public void enable(SingleEntryView v) {
				v.textBoxState = OUTPUT;
				v.convertButton.setEnabled(false);
				v.revertButton.setEnabled(true);
				
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
		return gridBag(x, y, 1, 1);
	}
	
	/**
	 * Creates a {@code GridBagConstraints} object.
	 *
	 * @since 2021-01-18
	 */
	private static GridBagConstraints gridBag(int x, int y, int width,
			int height) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		return gbc;
	}
	
	/**
	 * Creates and opens a View.
	 *
	 * @since 2021-01-18
	 */
	public static void main(String[] args) {
		// The side effects of the constructor are all we need
		@SuppressWarnings("unused")
		final View view = new SingleEntryView();
	}
	
	/** The frame that the GUI is displayed on. */
	private final JFrame frame;
	
	/** The presenter that handles the view's input */
	private final Presenter presenter;
	
	/** The text box that handles both input and output. */
	private final JTextArea textBox;
	/** The dropdown box to select the instrument. */
	private final JComboBox<Instrument> instrumentSelection;
	/** The button that converts tab to XML. */
	private final JButton convertButton;
	/** The undo button for conversion. */
	private final JButton revertButton;
	
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
		this.textBoxState = State.OUTPUT;
		
		// create components
		final JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		this.frame.add(masterPanel);
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		masterPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// text box
		this.textBox = new JTextArea(18, 80);
		this.textBox.setBorder(new LineBorder(Color.BLACK));
		masterPanel.add(new JScrollPane(this.textBox), BorderLayout.CENTER);
		
		// buttons
		final JButton loadFileButton = new JButton("Load From File");
		loadFileButton.addActionListener(e -> this.loadFromFile());
		buttonPanel.add(loadFileButton, gridBag(1, 0));
		
		this.convertButton = new JButton("Convert");
		this.convertButton.addActionListener(e -> this.presenter.convert());
		buttonPanel.add(this.convertButton, gridBag(2, 0));
		
		this.revertButton = new JButton("Undo Conversion");
		this.revertButton
				.addActionListener(e -> this.setInputText(this.previousInputText));
		this.revertButton.setEnabled(false);
		buttonPanel.add(this.revertButton, gridBag(3, 0));
		
		final JButton saveFileButton = new JButton("Save to File");
		saveFileButton.addActionListener(e -> this.saveToFile());
		buttonPanel.add(saveFileButton, gridBag(4, 0));
		
		// combo boxes
		this.instrumentSelection = new JComboBox<>(Instrument.values());
		buttonPanel.add(this.instrumentSelection, gridBag(0, 0));
		
		// give everything the correct size
		this.frame.pack();
		
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
	
	/**
	 * Allows the user to choose a file, then loads input text from that file.
	 * 
	 * @since 2021-01-29
	 */
	private void loadFromFile() {
		final JFileChooser fc = new JFileChooser();
		
		if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			final Path path = fc.getSelectedFile().toPath();
			try {
				this.presenter.loadFromFile(path);
			} catch (final IOException e) {
				JOptionPane.showMessageDialog(this.frame,
						"An error happened while reading the file: "
								+ e.getLocalizedMessage(),
						"File Read Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Prompts the user to choose a file, then saves output text to that file.
	 * 
	 * @since 2021-01-29
	 */
	private void saveToFile() {
		final JFileChooser fc = new JFileChooser();
		
		if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			final Path path = fc.getSelectedFile().toPath();
			try {
				this.presenter.saveToFile(path);
			} catch (final IOException e) {
				JOptionPane.showMessageDialog(this.frame,
						"An error happened while writing to the file: "
								+ e.getLocalizedMessage(),
						"File Write Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void setInputText(String text) {
		this.previousInputText = null;
		this.textBox.setText(text);
		State.INPUT.enable(this);
	}
	
	@Override
	public void setOutputText(String text) {
		this.previousInputText = this.textBox.getText();
		this.textBox.setText(text);
		State.OUTPUT.enable(this);
	}
}
