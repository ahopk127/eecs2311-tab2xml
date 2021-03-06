package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.SortedSet;
import java.util.TreeSet;

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
	private final SortedSet<ErrorToken> errors;
	
	/** The pane containing the input and output tabs. */
	private final JTabbedPane inputOutputPane;
	
	// INPUT COMPONENTS
	final JTextComponent input;
	final JTextComponent narrowedInput;
	final JLabel errorLabel;
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
		this.errors = new TreeSet<>();
		
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
		this.convertButton.setToolTipText("Please input a text tab.");
		this.convertButton.setEnabled(false);
		this.convertButton.addActionListener(e -> {
			if (this.presenter.convert()) {
				this.inputOutputPane.setSelectedIndex(2); // output
			}
		});
		this.convertButton.addActionListener(
				e -> this.getInput().getHighlighter().removeAllHighlights());
		inputButtonPanel.add(this.convertButton);
		
		this.convertAndSave = new JButton("Convert and Save");
		this.convertAndSave.setToolTipText("Please input a text tab.");
		this.convertAndSave.setEnabled(false);
		this.convertAndSave
				.addActionListener(e -> this.presenter.convertAndSave(true));
		this.convertAndSave.addActionListener(
				e -> this.getInput().getHighlighter().removeAllHighlights());
		inputButtonPanel.add(this.convertAndSave);
		
		this.saveInput = new JButton("Save Input");
		this.saveInput.setEnabled(false);
		this.saveInput.addActionListener(e -> this.presenter.saveInput());
		inputButtonPanel.add(this.saveInput);
		
		this.errorLabel = new JLabel("");
		inputButtonPanel.add(this.errorLabel);
		
		// input text box
		this.input = new JTextArea(24, 80);
		this.input.setBorder(new LineBorder(Color.BLACK));
		this.input.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		this.setUpFileDragAndDrop();
		// this.setUpHighlightingRemoval();
		this.setUpUndoManager();
		this.setUpRedoManager();
		this.input.addCaretListener(e -> this.presenter.detectInstrument());
		this.input.addCaretListener(e -> this.updateButtons());
		this.input.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TabbedView.this.checkErrorToModel(e.getPoint());
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				TabbedView.this.checkErrorToModel(e.getPoint());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// do nothing
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				TabbedView.this.checkErrorToModel(e.getPoint());
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// do nothing
			}
		});
		
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
		this.narrowedInput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
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
		this.output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		this.output.addCaretListener(e -> this.updateButtons());
		outputPanel.add(
				new JScrollPane(this.output,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		// ----- INSTRUMENT SELECTION & ERRORS -----
		final JPanel instrumentSelectionPanel = new JPanel(
				new FlowLayout(FlowLayout.CENTER, 10, 5));
		masterPanel.add(instrumentSelectionPanel, BorderLayout.SOUTH);
		
		instrumentSelectionPanel.add(new JLabel("Instrument:"));
		instrumentSelectionPanel.add(this.getInstrumentSelector());
		
		// set the correct size, then open the window
		this.frame.pack();
		this.frame.setVisible(true);
	}
	
	final void checkErrorToModel(Point mousePos) {
		if (this.errors == null)
			return;
		if (this.errors.size() == 0)
			return;
		for (final ErrorToken error : this.errors) {
			final int offset = this.input.viewToModel2D(mousePos);
			if (offset >= error.getStart() && offset <= error.getStop()) {
				this.input.setToolTipText(error.getMesage());
				return;
			}
			this.input.setToolTipText(null);
		}
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
	public void onParseError(UnparseableInputException error) {
		this.convertButton.setEnabled(false);
		this.convertAndSave.setEnabled(false);
		this.errors.addAll(error.getErrors());
		super.onParseError(error);
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
		final InputValidation validateUpdate = InputValidation
				.newInstance(this.getInputText(), this.getSelectedInstrument());
		validateUpdate.validate();
		this.errors.removeIf(e -> !validateUpdate.getScoreErrors().contains(e));
		
		// use back-end validation to update errors
		if (!validateUpdate.isValidScore()) {
			this.errors.addAll(validateUpdate.getScoreErrors());
		} else if (!validateUpdate.isValidStaffs()) {
			this.errors.addAll(validateUpdate.getStaffErrors());
		}
		
		this.highlightErrors(this.errors);
		
		if (validateUpdate.isValid()) {
			this.removeAllHighlights();
			this.input.setToolTipText(null);
		}
		
		this.convertButton.setEnabled(!inputBlank && this.errors.isEmpty());
		this.convertAndSave.setEnabled(!inputBlank && this.errors.isEmpty());
		this.saveInput.setEnabled(!inputBlank);
		this.saveOutput.setEnabled(!this.output.getText().isBlank());
		
		// error tooltip indicator
		if (inputBlank) {
			this.errorLabel.setText("");
			this.errorLabel.setToolTipText("Please input a text tab.");
			this.convertButton.setToolTipText("Please input a text tab.");
			this.convertAndSave.setToolTipText("Please input a text tab.");
		} else if (validateUpdate.isValid()) {
			this.errorLabel.setText("\u2713");
			this.errorLabel.setForeground(Color.GREEN);
			this.errorLabel
					.setToolTipText("No errors in input.  Ready to convert!");
			this.convertButton
					.setToolTipText("No errors in input.  Ready to convert!");
			this.convertAndSave
					.setToolTipText("No errors in input.  Ready to convert!");
		} else {
			this.errorLabel.setText("\u26A0 " + this.errors.size());
			this.errorLabel.setForeground(Color.RED);
			this.errorLabel.setToolTipText("Please fix errors before converting.");
			this.convertButton
					.setToolTipText("Please fix errors before converting.");
			this.convertAndSave
					.setToolTipText("Please fix errors before converting.");
		}
	}
}
