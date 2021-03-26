package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

/**
 * A view that shows input and output in separate text boxes, in tabs.
 *
 * @since 2021-03-10
 */
final class TabbedView extends AbstractSwingView implements NarrowingView {
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
		View.createView(View.ViewType.TABBED);
	}
	
	/** The pane containing the input and output tabs. */
	private final JTabbedPane inputOutputPane;
	
	// INPUT COMPONENTS
	private final JTextComponent input;
	private final JTextComponent narrowedInput;
	
	// OUTPUT COMPONENTS
	private final JTextComponent output;
	
	/**
	 * Creates the TabbedView.
	 * 
	 * @since 2021-03-10
	 */
	public TabbedView() {
		this.frame.setResizable(false);
		
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
		this.setUpFileDragAndDrop();
		this.input.addCaretListener(new RateLimitedCaretListener(
				e -> this.presenter.detectInstrument(), 1000));
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
		loadFromFile.addActionListener(e -> this.presenter.loadInput());
		inputButtonPanel.add(loadFromFile);
		
		final JButton convertButton = new JButton("Convert");
		convertButton.addActionListener(e -> {
			if (this.presenter.convert()) {
				this.inputOutputPane.setSelectedIndex(2); // output
			}
		});
		inputButtonPanel.add(convertButton);
		
		final JButton convertAndSave = new JButton("Convert and Save");
		convertAndSave
				.addActionListener(e -> this.presenter.convertAndSave(true));
		inputButtonPanel.add(convertAndSave);
		
		final JButton saveInput = new JButton("Save Input");
		saveInput.addActionListener(e -> this.presenter.saveInput());
		inputButtonPanel.add(saveInput);
		
		// ----- INPUT EDITING -----
		final JPanel editingTab = new JPanel(new BorderLayout());
		this.inputOutputPane.addTab("Input Editing", editingTab);
		
		this.narrowedInput = new JTextArea(24, 80);
		this.narrowedInput.setBorder(new LineBorder(Color.BLACK));
		editingTab.add(
				new JScrollPane(this.narrowedInput,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		final JPanel editingPanel = new EditingPanel(this);
		editingTab.add(editingPanel, BorderLayout.SOUTH);
		
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
		saveToFile.addActionListener(e -> this.presenter.saveOutput());
		outputButtonPanel.add(saveToFile);
		
		// ----- INSTRUMENT SELECTION -----
		final JPanel instrumentSelectionPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		masterPanel.add(instrumentSelectionPanel, BorderLayout.SOUTH);
		
		instrumentSelectionPanel.add(new JLabel("Instrument:"));
		instrumentSelectionPanel.add(this.getInstrumentSelector());
		
		// set the correct size, then open the window
		this.frame.pack();
		this.frame.setVisible(true);
	}
	
	@Override
	protected JTextComponent getInput() {
		return this.input;
	}
	
	@Override
	public String getNarrowedText() {
		return this.narrowedInput.getText();
	}
	
	@Override
	protected JTextComponent getOutput() {
		return this.output;
	}
	
	@Override
	public void setNarrowedText(String text) {
		this.narrowedInput.setText(text);
	}
}
