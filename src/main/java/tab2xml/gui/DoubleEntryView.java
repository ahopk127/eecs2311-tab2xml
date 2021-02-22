package tab2xml.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import tab2xml.parser.Instrument;

/**
 * The view of Tab2XML. This class handles the GUI, and all interaction
 * (input/output) with the user.
 *
 * @since 2021-01-18
 */
final class DoubleEntryView implements View {
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
		View.createView(View.ViewType.DOUBLE_ENTRY);
	}
	
	/** The frame that the GUI is displayed on. */
	private final JFrame frame;
	
	/** The presenter that handles the view's input */
	private final Presenter presenter;
	/** The text box that contains the input text. */
	private final JTextArea input;
	/** The text box that will contain the output text. */
	private final JTextArea output;
	
	/** The dropdown box to select the instrument. */
	private final JComboBox<Instrument> instrumentSelection;
	
	/**
	 * Creates the view.
	 * 
	 * @since 2021-01-18
	 */
	public DoubleEntryView() {
		this.frame = new JFrame("Tab2XML");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.presenter = new Presenter(this);
		
		// create components
		final JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new GridBagLayout());
		this.frame.add(masterPanel);
		
		// labels
		final JLabel inputLabel = new JLabel("Input Text Tab:");
		masterPanel.add(inputLabel, gridBag(0, 0));
		
		final JLabel convertLabel = new JLabel(" ---> ");
		masterPanel.add(convertLabel, gridBag(1, 0, 1, 2));
		
		final JLabel outputLabel = new JLabel("MusicXML Output:");
		masterPanel.add(outputLabel, gridBag(2, 0));
		
		// buttons
		final JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(e -> this.presenter.convert());
		masterPanel.add(convertButton, gridBag(1, 2));
		
		final JButton loadFileButton = new JButton("Load From File");
		loadFileButton.addActionListener(e -> this.loadFromFile());
		masterPanel.add(loadFileButton, gridBag(0, 4));
		
		final JButton saveFileButton = new JButton("Save to File");
		saveFileButton.addActionListener(e -> this.saveToFile());
		masterPanel.add(saveFileButton, gridBag(2, 4));
		
		// text boxes
		this.input = new PromptingTextArea(
				"Enter the text tab or load from a file...", 15, 50);
		this.input.setBorder(new LineBorder(Color.BLACK));
		this.input.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		FileDragDropTarget.enableDragAndDrop(this.input);
		masterPanel.add(new JScrollPane(this.input), gridBag(0, 1, 1, 2));
		
		this.output = new JTextArea(15, 50);
		this.output.setBorder(new LineBorder(Color.BLACK));
		this.output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		masterPanel.add(new JScrollPane(this.output), gridBag(2, 1, 1, 2));
		
		// combo boxes
		this.instrumentSelection = new JComboBox<>(Instrument.values());
		masterPanel.add(this.instrumentSelection, gridBag(1, 4));
		
		// give everything the correct size
		this.frame.pack();
		
		// open the window
		this.frame.setVisible(true);
	}
	
	@Override
	public String getInputText() {
		return this.input.getText();
	}
	
	@Override
	public String getOutputText() {
		return this.output.getText();
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
		this.input.setText(text);
	}
	
	@Override
	public void setOutputText(String text) {
		this.output.setText(text);
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
