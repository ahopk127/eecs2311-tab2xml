package tab2xml.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;

import tab2xml.parser.MeasureNarrowing;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * A panel that can be used to edit MusicXML.
 *
 * @since 2021-03-22
 */
final class EditingPanel extends JPanel {
	private static final long serialVersionUID = 5463866379475862781L;
	/**
	 * The insets used for all items in the panel.
	 */
	private static final Insets INSETS = new Insets(2, 3, 2, 3);
	
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
	 * The view that this panel supports.
	 */
	private final NarrowingView view;
	
	/** Text field describing the beginning of the measure range */
	private final JTextField measureStart;
	/** Text field describing the end of the measure range */
	private final JTextField measureEnd;
	/** Text field describing the score title */
	private final JTextField titleField;
	
	// control buttons
	private final JButton editMeasureButton;
	private final JButton doneEditingButton;
	
	/**
	 * Creates the editing panel.
	 * 
	 * @param loadFunction function that takes a measure string and loads it into
	 *                     the view
	 * @param saveFunction function that gets an (edited) measure string from the
	 *                     view
	 * 
	 * @since 2021-03-22
	 */
	public EditingPanel(NarrowingView view) {
		super(new GridBagLayout());
		
		this.view = view;
		
		final JPanel measureSelectionPanel = new JPanel();
		this.add(measureSelectionPanel, gridBag(0, 0));
		
		final JLabel measureLabel = new JLabel("Measure Range:");
		measureSelectionPanel.add(measureLabel);
		
		this.measureStart = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.measureStart.setColumns(5);
		this.measureStart.addCaretListener(this::onMeasureEntryUpdate);
		measureSelectionPanel.add(this.measureStart);
		
		final JLabel dashLabel = new JLabel("-");
		measureSelectionPanel.add(dashLabel);
		
		this.measureEnd = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.measureEnd.setColumns(5);
		this.measureEnd.addCaretListener(this::onMeasureEntryUpdate);
		measureSelectionPanel.add(this.measureEnd);
		
		this.editMeasureButton = new JButton("Edit");
		this.editMeasureButton.setEnabled(false);
		this.editMeasureButton.addActionListener(this::editMeasures);
		measureSelectionPanel.add(this.editMeasureButton);
		
		this.doneEditingButton = new JButton("Done");
		this.doneEditingButton.setEnabled(false);
		this.doneEditingButton.addActionListener(this::doneEditing);
		measureSelectionPanel.add(this.doneEditingButton);
		
		final JPanel metadataPanel = new JPanel(new GridBagLayout());
		metadataPanel.setBorder(new TitledBorder("Metadata"));
		this.add(metadataPanel, gridBag(0, 1));
		
		final JLabel titleLabel = new JLabel("Title:");
		metadataPanel.add(titleLabel, gridBag(0, 0, 1, 1, INSETS));
		
		this.titleField = new JTextField(40);
		metadataPanel.add(this.titleField, gridBag(1, 0, 4, 1, INSETS));
		
//		final JButton setTitleButton = new JButton("Set Title");
//		metadataPanel.add(setTitleButton, gridBag(4, 0, 1, 1, INSETS));
		
		final JLabel timeSignatureLabel = new JLabel("Time Signature:");
		metadataPanel.add(timeSignatureLabel, gridBag(0, 1, 1, 1, INSETS));
		
		final JPanel timeSignaturePanel = new JPanel();
		metadataPanel.add(timeSignaturePanel, gridBag(1, 1, 3, 1, INSETS));
		
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
		
		final JButton setSignatureButton = new JButton(
				"Set Time Signature for Measure(s)");
		metadataPanel.add(setSignatureButton, gridBag(4, 1, 1, 1, INSETS));
	}
	
	/**
	 * Runs whenever the "Done" button is pressed.
	 *
	 * @param e button-press action
	 * @since 2021-03-22
	 */
	private void doneEditing(ActionEvent e) {
		final int measureStart = Integer.valueOf(this.measureStart.getText());
		final int measureEnd = Integer.valueOf(this.measureEnd.getText());
		
		final String editedInput = MeasureNarrowing.replaceMeasureRange(
				this.view.getInputText(), measureStart, measureEnd,
				this.view.getNarrowedText());
		this.view.setNarrowedText("");
		this.view.setInputText(editedInput);
		
		this.doneEditingButton.setEnabled(true);
	}
	
	/**
	 * Runs whenever the "Edit" button is pressed.
	 * 
	 * @param e button-press action
	 * @since 2021-03-22
	 */
	private void editMeasures(ActionEvent e) {
		final int measureStart = Integer.valueOf(this.measureStart.getText());
		final int measureEnd = Integer.valueOf(this.measureEnd.getText());
		
		final String measureText = MeasureNarrowing.extractMeasureRange(
				this.view.getInputText(), measureStart, measureEnd);
		this.view.setNarrowedText(measureText);
		
		this.doneEditingButton.setEnabled(false);
	}
	
	/**
	 * @return title entered by user
	 * @since 2021-03-26
	 */
	public String getTitle() {
		final String title = this.titleField.getText();
		return title.isBlank() ? XMLMetadata.DEFAULT_TITLE : title;
	}
	
	/**
	 * Runs whenever the measure entries are edited
	 *
	 * @param e editing action
	 * @since 2021-03-29
	 */
	private void onMeasureEntryUpdate(CaretEvent e) {
		final boolean measuresEntered = !(this.measureStart.getText().isEmpty()
				|| this.measureEnd.getText().isEmpty());
		this.editMeasureButton.setEnabled(measuresEntered);
	}
}
