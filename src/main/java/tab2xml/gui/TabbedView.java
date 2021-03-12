package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.nio.file.Path;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import tab2xml.parser.Instrument;

/**
 * A view that shows input and output in separate text boxes, in tabs.
 *
 * @since 2021-03-10
 */
final class TabbedView implements View {
	/**
	 * The insets used for prompt labels
	 */
	private static final Insets LABEL_INSETS = new Insets(2, 3, 2, 3);
	
	/**
	 * Runs the program with a {@code TabbedView}.
	 *
	 * @param args
	 * @since 2021-03-10
	 */
	public static void main(String[] args) {
		View.enableSystemLookAndFeel();
		View.createView(View.ViewType.TABBED);
	}
	
	private final Presenter presenter;
	
	private final JFrame frame;
	
	private final JComboBox<Instrument> instrumentSelection;
	
	/** The pane containing the input and output tabs. */
	private final JTabbedPane inputOutputPane;
	
	// INPUT COMPONENTS
	private final JTextComponent input;
	
	// OUTPUT COMPONENTS
	private final JTextComponent output;
	
	/**
	 * Creates the TabbedView.
	 * 
	 * @since 2021-03-10
	 */
	public TabbedView() {
		this.frame = new JFrame("TAB2XML");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.presenter = new Presenter(this);
		
		// master components - for both input and output
		final JPanel masterPanel = new JPanel(new BorderLayout());
		this.frame.add(new BackgroundPanel(masterPanel));
		
		this.inputOutputPane = new JTabbedPane();
		masterPanel.add(this.inputOutputPane);
		
		// ----- INPUT -----
		final JPanel inputPanel = new JPanel(new BorderLayout());
		this.inputOutputPane.addTab("Input - Text Tab", inputPanel);
		
		this.input = new JTextArea(24, 80);
		this.input.setBorder(new LineBorder(Color.BLACK));
		this.input.setDropTarget(new FileDragDropTarget(this.input));
		inputPanel.add(
				new JScrollPane(this.input,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		final JLabel inputLabel = new JLabel(
				"Enter text tab or load it from a file...");
		inputLabel.setBorder(new EmptyBorder(LABEL_INSETS));
		inputPanel.add(inputLabel, BorderLayout.NORTH);
		
		// input buttons
		final JPanel inputButtonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		inputPanel.add(inputButtonPanel, BorderLayout.SOUTH);
		
		final JButton loadFromFile = new JButton("Load from File");
		loadFromFile.addActionListener(e -> this.presenter.loadFromFile());
		inputButtonPanel.add(loadFromFile);
		
		final JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(e -> {
			if (this.presenter.convert()) {
				this.inputOutputPane.setSelectedIndex(1); // output
			}
		});
		inputButtonPanel.add(convertButton);
		
		final JButton convertAndSave = new JButton("Convert and Save");
		convertAndSave
				.addActionListener(e -> this.presenter.convertAndSave(true));
		inputButtonPanel.add(convertAndSave);
		
		// ----- OUTPUT -----
		final JPanel outputPanel = new JPanel(new BorderLayout());
		this.inputOutputPane.addTab("Output - MusicXML", outputPanel);
		
		this.output = new JTextArea(24, 80);
		this.output.setBorder(new LineBorder(Color.BLACK));
		
		outputPanel.add(
				new JScrollPane(this.output,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		final JLabel outputLabel = new JLabel(
				"The output MusicXML will be displayed here.");
		outputLabel.setBorder(new EmptyBorder(LABEL_INSETS));
		outputPanel.add(outputLabel, BorderLayout.NORTH);
		
		final JPanel outputButtonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		outputPanel.add(outputButtonPanel, BorderLayout.SOUTH);
		
		final JButton saveToFile = new JButton("Save to File");
		saveToFile.addActionListener(e -> this.presenter.saveToFile());
		outputButtonPanel.add(saveToFile);
		
		// ----- INSTRUMENT SELECTION -----
		final JPanel instrumentSelectionPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		masterPanel.add(instrumentSelectionPanel, BorderLayout.SOUTH);
		
		instrumentSelectionPanel.add(new JLabel("Instrument:"));
		
		this.instrumentSelection = new JComboBox<>(Instrument.values());
		instrumentSelectionPanel.add(this.instrumentSelection);
		
		// set the correct size, then open the window
		this.frame.pack();
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
	
	@Override
	public Optional<Path> promptForFile(
			FileNameExtensionFilter preferredFileType) {
		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(preferredFileType);
		fc.setFileFilter(preferredFileType);
		
		if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION)
			return Optional.of(fc.getSelectedFile().toPath());
		else
			return Optional.empty();
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
