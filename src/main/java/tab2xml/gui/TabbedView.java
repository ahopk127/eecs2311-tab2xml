package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;

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

import tab2xml.exceptions.UnparseableInputException;
import tab2xml.model.ErrorToken;
import tab2xml.parser.InputValidation;
import tab2xml.xmlconversion.XMLMetadata;

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
	
	
	/** List of error tokens */
	private List<ErrorToken> errors;
	
	/** The pane containing the input and output tabs. */
	private final JTabbedPane inputOutputPane;
	
	// INPUT COMPONENTS
	final JTextComponent input;
	final JTextComponent narrowedInput;
	final EditingPanel editingPanel;
	
	// OUTPUT COMPONENTS
	final JTextComponent output;
	
	// BUTTONS (package-private for testing)
	final JButton loadFromFile;
	final JButton convertButton;
	final JButton convertAndSave;
	final JButton saveInput;
	final JButton saveOutput;
	
	/**
	 * Creates the TabbedView.
	 * 
	 * @since 2021-03-10
	 */
	public TabbedView() {
		this.errors = new LinkedList<>();
		
		this.frame.setResizable(false);
		
		// master components - for both input and output
		final JPanel masterPanel = new JPanel(new BorderLayout());
		this.frame.add(new BackgroundPanel(masterPanel));
		
		this.inputOutputPane = new JTabbedPane();
		masterPanel.add(this.inputOutputPane);
		
		// ----- INPUT -----
		final JPanel inputPanel = new JPanel(new BorderLayout());
		this.inputOutputPane.addTab("Input - Text Tab", inputPanel);
		
		final JLabel inputLabel = new JLabel(
				"Enter text tab or load it from a file...");
		inputLabel.setBorder(new EmptyBorder(LABEL_INSETS));
		inputPanel.add(inputLabel, BorderLayout.NORTH);
		
		// input buttons
		final JPanel inputButtonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		inputPanel.add(inputButtonPanel, BorderLayout.SOUTH);
		
		this.loadFromFile = new JButton("Load from File");
		this.loadFromFile.addActionListener(e -> this.presenter.loadInput());
		inputButtonPanel.add(this.loadFromFile);
		
		this.convertButton = new JButton("Convert");
		this.convertButton.setEnabled(false);
		this.convertButton.addActionListener(e -> {
			if (this.presenter.convert()) {
				this.inputOutputPane.setSelectedIndex(2); // output
			}
		});
		this.convertButton.addActionListener(e -> this.getInput().getHighlighter()
				.removeAllHighlights());
		inputButtonPanel.add(this.convertButton);
		
		this.convertAndSave = new JButton("Convert and Save");
		this.convertAndSave.setEnabled(false);
		this.convertAndSave
				.addActionListener(e -> this.presenter.convertAndSave(true));
		this.convertAndSave.addActionListener(e -> this.getInput().getHighlighter()
				.removeAllHighlights());
		inputButtonPanel.add(this.convertAndSave);
		
		this.saveInput = new JButton("Save Input");
		this.saveInput.setEnabled(false);
		this.saveInput.addActionListener(e -> this.presenter.saveInput());
		inputButtonPanel.add(this.saveInput);
		
		// input text box
		this.input = new JTextArea(24, 80);
		this.input.setBorder(new LineBorder(Color.BLACK));
		this.input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		this.setUpFileDragAndDrop();
		//this.setUpHighlightingRemoval();
		this.setUpUndoManager();
		this.setUpRedoManager();
		this.input.addCaretListener(e -> this.presenter.detectInstrument());
		this.input.addCaretListener(e -> this.updateButtons());
		inputPanel.add(
				new JScrollPane(this.input,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
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
		
		this.editingPanel = new EditingPanel(this);
		editingTab.add(this.editingPanel, BorderLayout.SOUTH);
		
		// ----- OUTPUT -----
		final JPanel outputPanel = new JPanel(new BorderLayout());
		this.inputOutputPane.addTab("Output - MusicXML", outputPanel);
		
		final JLabel outputLabel = new JLabel(
				"The output MusicXML will be displayed here.");
		outputLabel.setBorder(new EmptyBorder(LABEL_INSETS));
		outputPanel.add(outputLabel, BorderLayout.NORTH);
		
		final JPanel outputButtonPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		outputPanel.add(outputButtonPanel, BorderLayout.SOUTH);
		
		this.saveOutput = new JButton("Save to File");
		this.saveOutput.setEnabled(false);
		this.saveOutput.addActionListener(e -> this.presenter.saveOutput());
		outputButtonPanel.add(this.saveOutput);
		
		// output text box
		this.output = new JTextArea(24, 80);
		this.output.setBorder(new LineBorder(Color.BLACK));
		this.output.addCaretListener(e -> this.updateButtons());
		outputPanel.add(
				new JScrollPane(this.output,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
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
	public void onParseError(UnparseableInputException error) {
		this.convertButton.setEnabled(false);
		this.convertAndSave.setEnabled(false);
		this.errors.addAll(error.getErrors());
		super.onParseError(error);
	}

	@Override
	protected JTextComponent getInput() {
		return this.input;
	}
	
	@Override
	public XMLMetadata getMetadata() {
		return this.editingPanel.getMetadata();
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
	public void setInputText(String text) {
		this.input.setText(text);
		this.updateButtons();
	}
	
	@Override
	public void setNarrowedText(String text) {
		this.narrowedInput.setText(text);
	}
	
	@Override
	public void setOutputText(String text) {
		this.output.setText(text);
		this.updateButtons();
	}
	
	/**
	 * Updates the state of the view's buttons.
	 * 
	 * @since 2021-04-05
	 */
	final void updateButtons() {
		final boolean inputBlank = this.input.getText().isBlank();
		// check if errors are still here 
		errors.removeIf(e -> !InputValidation.containsError(e, this.getInputText()));
		
		if (errors.isEmpty()) {
			this.removeAllHighlights();
		}
		
		this.convertButton.setEnabled(!inputBlank && this.errors.isEmpty());
		this.convertAndSave.setEnabled(!inputBlank && this.errors.isEmpty());
		this.saveInput.setEnabled(!inputBlank);
		
		this.saveOutput.setEnabled(!this.output.getText().isBlank());
	}
}
