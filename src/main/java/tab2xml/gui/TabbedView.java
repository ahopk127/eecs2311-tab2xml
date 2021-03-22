package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import com.ibm.icu.text.NumberFormat;

/**
 * A view that shows input and output in separate text boxes, in tabs.
 *
 * @since 2021-03-10
 */
final class TabbedView extends AbstractSwingView {
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
				this.inputOutputPane.setSelectedIndex(1); // output
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
		
		final JTextArea editingArea = new JTextArea(24, 80);
		editingArea.setBorder(new LineBorder(Color.BLACK));
		editingTab.add(
				new JScrollPane(editingArea,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		final JPanel editingPanel = new JPanel(new GridBagLayout());
		editingTab.add(editingPanel, BorderLayout.SOUTH);
		
		final JPanel measureSelectionPanel = new JPanel();
		editingPanel.add(measureSelectionPanel, gridBag(0, 0));
		
		final JLabel measureLabel = new JLabel("Measure Range:");
		measureSelectionPanel.add(measureLabel);
		
		final JTextField measureStart = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		measureStart.setColumns(5);
		measureSelectionPanel.add(measureStart);
		
		final JLabel dashLabel = new JLabel("-");
		measureSelectionPanel.add(dashLabel);
		
		final JTextField measureEnd = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		measureEnd.setColumns(5);
		measureSelectionPanel.add(measureEnd);
		
		final JButton editMeasureButton = new JButton("Edit");
		measureSelectionPanel.add(editMeasureButton);
		
		final JButton doneEditingButton = new JButton("Done");
		measureSelectionPanel.add(doneEditingButton);
		
		final JPanel metadataPanel = new JPanel(new GridBagLayout());
		metadataPanel.setBorder(new TitledBorder("Metadata"));
		editingPanel.add(metadataPanel, gridBag(0, 1));
		
		final JLabel titleLabel = new JLabel("Title:");
		metadataPanel.add(titleLabel, gridBag(0, 0, 1, 1, LABEL_INSETS));
		
		final JTextField titleField = new JTextField(40);
		metadataPanel.add(titleField, gridBag(1, 0, 3, 1, LABEL_INSETS));
		
		final JButton setTitleButton = new JButton("Set Title");
		metadataPanel.add(setTitleButton, gridBag(4, 0, 1, 1, LABEL_INSETS));
		
		final JLabel timeSignatureLabel = new JLabel("Time Signature:");
		metadataPanel.add(timeSignatureLabel, gridBag(0, 1, 1, 1, LABEL_INSETS));
		
		final JPanel timeSignaturePanel = new JPanel();
		metadataPanel.add(timeSignaturePanel, gridBag(1, 1, 3, 1, LABEL_INSETS));
		
		final JTextField topSignatureField = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		topSignatureField.setColumns(2);
		timeSignaturePanel.add(topSignatureField);
		
		final JLabel colonLabel = new JLabel(":");
		timeSignaturePanel.add(colonLabel);
		
		final JTextField bottomSignatureField = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		bottomSignatureField.setColumns(2);
		timeSignaturePanel.add(bottomSignatureField);
		
		final JButton setSignatureButton = new JButton("Set Time Signature");
		metadataPanel.add(setSignatureButton, gridBag(4, 1, 1, 1, LABEL_INSETS));
		
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
	protected JTextComponent getOutput() {
		return this.output;
	}
}
