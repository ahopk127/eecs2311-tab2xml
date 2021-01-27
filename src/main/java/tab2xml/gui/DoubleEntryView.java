package tab2xml.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public final class DoubleEntryView implements View {
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
		final View view = new DoubleEntryView();
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
		masterPanel.add(loadFileButton, gridBag(0, 4));
		
		final JButton saveFileButton = new JButton("Save to File");
		masterPanel.add(saveFileButton, gridBag(2, 4));
		
		// text boxes
		this.input = new JTextArea(15, 30);
		this.input.setBorder(new LineBorder(Color.BLACK));
		masterPanel.add(new JScrollPane(this.input), gridBag(0, 1, 1, 2));
		
		this.output = new JTextArea(15, 30);
		this.output.setBorder(new LineBorder(Color.BLACK));
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
	public Instrument getSelectedInstrument() {
		// The only objects in this list are Instrument instances, so the cast
		// should work.
		return (Instrument) this.instrumentSelection.getSelectedItem();
	}
	
	@Override
	public void setOutputText(String text) {
		this.output.setText(text);
	}
}
