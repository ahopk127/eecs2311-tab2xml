package tab2xml.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import tab2xml.model.TimeSignature;
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
	final JTextField measureStart;
	/** Text field describing the end of the measure range */
	final JTextField measureEnd;
	/** Text field describing the score title */
	final JTextField titleField;
	/** Text field describing the score composer */
	final JTextField composerField;
	/** Field for top part of time signature */
	final JTextField topSignatureField;
	/** Field for bottom part of time signature */
	final JTextField bottomSignatureField;
	
	// control buttons
	final JButton editMeasureButton;
	final JButton doneEditingButton;
	final JButton setSignatureButton;
	
	/** Whether or not narrowing is currently being used */
	private boolean isNarrowing = false;
	
	// metadata obtained from view
	private final XMLMetadata.Builder metadataBuilder;
	
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
		super(new GridLayout(1, 2));
		
		this.metadataBuilder = new XMLMetadata.Builder();
		this.view = view;
		
		final JPanel measureEditingPanel = new JPanel(new GridLayout(2, 1));
		measureEditingPanel.setBorder(new TitledBorder("Edit Measure(s)"));
		this.add(measureEditingPanel);
		
		final JPanel measureSelectionPanel = new JPanel();
		measureEditingPanel.add(measureSelectionPanel);
		
		final JLabel measureLabel = new JLabel("Measure Range:");
		measureSelectionPanel.add(measureLabel);
		
		this.measureStart = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.measureStart.setColumns(5);
		this.measureStart.addCaretListener(this::onEntryUpdate);
		measureSelectionPanel.add(this.measureStart);
		
		final JLabel dashLabel = new JLabel("-");
		measureSelectionPanel.add(dashLabel);
		
		this.measureEnd = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.measureEnd.setColumns(5);
		this.measureEnd.addCaretListener(this::onEntryUpdate);
		measureSelectionPanel.add(this.measureEnd);
		
		this.editMeasureButton = new JButton("Edit");
		this.editMeasureButton.addActionListener(this::editMeasures);
		measureSelectionPanel.add(this.editMeasureButton);
		
		this.doneEditingButton = new JButton("Done");
		this.doneEditingButton.setEnabled(false);
		this.doneEditingButton.addActionListener(this::doneEditing);
		measureSelectionPanel.add(this.doneEditingButton);
		
		final JPanel timeSignaturePanel = new JPanel();
		measureEditingPanel.add(timeSignaturePanel);
		
		final JLabel timeSignatureLabel = new JLabel("Time Signature:");
		timeSignaturePanel.add(timeSignatureLabel);
		
		this.topSignatureField = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.topSignatureField.setColumns(2);
		this.topSignatureField.setText("4");
		this.topSignatureField.addCaretListener(this::onEntryUpdate);
		timeSignaturePanel.add(this.topSignatureField);
		
		final JLabel colonLabel = new JLabel(":");
		timeSignaturePanel.add(colonLabel);
		
		this.bottomSignatureField = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		this.bottomSignatureField.setColumns(2);
		this.bottomSignatureField.setText("4");
		this.bottomSignatureField.addCaretListener(this::onEntryUpdate);
		timeSignaturePanel.add(this.bottomSignatureField);
		
		this.setSignatureButton = new JButton("Set Time Signature for Range");
		this.setSignatureButton.addActionListener(this::setTimeSignature);
		timeSignaturePanel.add(this.setSignatureButton);
		
		final JPanel metadataPanel = new JPanel(new GridBagLayout());
		metadataPanel.setBorder(new TitledBorder("Metadata for Whole Score"));
		this.add(metadataPanel);
		
		final JLabel titleLabel = new JLabel("Title:");
		metadataPanel.add(titleLabel, gridBag(0, 0, 1, 1, INSETS));
		
		this.titleField = new JTextField(30);
		metadataPanel.add(this.titleField, gridBag(1, 0, 2, 1, INSETS));
		
		final JLabel composerLabel = new JLabel("Composer:");
		metadataPanel.add(composerLabel, gridBag(0, 1, 1, 1, INSETS));
		
		this.composerField = new JTextField(30);
		metadataPanel.add(this.composerField, gridBag(1, 1, 2, 1, INSETS));
		
//		final JButton setTitleButton = new JButton("Set Title");
//		metadataPanel.add(setTitleButton, gridBag(4, 0, 1, 1, INSETS));
		
	}
	
	/**
	 * Checks for any input errors
	 *
	 * @param checkTimeSignature whether time signature errors should be checked
	 * @return whether the input is correct (there are no errors)
	 * @since 2021-03-31
	 */
	private boolean checkInputErrors(boolean checkTimeSignature) {
		final int measureStart = this.measureStart();
		final int measureEnd = this.measureEnd();
		final int measureCount = MeasureNarrowing
				.measureCount(this.view.getInputText());
		final int top, bottom;
		try {
			if (checkTimeSignature) {
				top = Integer.valueOf(this.topSignatureField.getText());
				bottom = Integer.valueOf(this.bottomSignatureField.getText());
			} else {
				top = -1;
				bottom = -1;
			}
		} catch (final NumberFormatException e) {
			this.view.showErrorMessage("Number Parsing Exception",
					"One of the measure or time signature fields is not a number!");
			return false;
		}
		
		if (measureStart <= 0) {
			this.view.showErrorMessage("Measure Range Error",
					"Start measure must be positive");
		} else if (measureEnd <= 0) {
			this.view.showErrorMessage("Measure Range Error",
					"End measure must be positive");
		} else if (measureStart > measureCount) {
			this.view.showErrorMessage("Measure Range Error",
					"Start measure must be less than or equal to number of measures");
		} else if (measureEnd > measureCount) {
			this.view.showErrorMessage("Measure Range Error",
					"End measure must be less than or equal to number of measures");
		} else if (measureStart > measureEnd) {
			this.view.showErrorMessage("Measure Range Error",
					"Start measure must be before or equal to end measure.");
		} else if (checkTimeSignature && top <= 0) {
			this.view.showErrorMessage("Time Signature Error",
					"Top numeral of time signature must be positive");
		} else if (checkTimeSignature && bottom <= 0) {
			this.view.showErrorMessage("Time Signature Error",
					"Bottom numeral of time signature must be positive");
		} else
			return true;
		return false;
	}
	
	/**
	 * Runs whenever the "Done" button is pressed.
	 *
	 * @param e button-press action
	 * @since 2021-03-22
	 */
	void doneEditing(ActionEvent e) {
		final int measureStart = this.measureStart();
		final int measureEnd = this.measureEnd();
		
		final String editedInput = MeasureNarrowing.replaceMeasureRange(
				this.view.getInputText(), measureStart, measureEnd,
				this.view.getNarrowedText());
		this.view.setNarrowedText("");
		this.view.setInputText(editedInput);
		
		this.doneEditingButton.setEnabled(false);
		this.measureStart.setEditable(true);
		this.measureEnd.setEditable(true);
		this.isNarrowing = false;
	}
	
	/**
	 * Runs whenever the "Edit" button is pressed.
	 * 
	 * @param e button-press action
	 * @since 2021-03-22
	 */
	void editMeasures(ActionEvent e) {
		if (this.checkInputErrors(false)) {
			final int measureStart = this.measureStart();
			final int measureEnd = this.measureEnd();
			
			final String measureText = MeasureNarrowing.extractMeasureRange(
					this.view.getInputText(), measureStart, measureEnd);
			this.view.setNarrowedText(measureText);
			
			this.doneEditingButton.setEnabled(true);
			this.measureStart.setEditable(false);
			this.measureEnd.setEditable(false);
			this.isNarrowing = true;
		}
	}
	
	/**
	 * @return title entered by user
	 * @since 2021-03-26
	 */
	public XMLMetadata getMetadata() {
		final String title = this.titleField.getText();
		final String composer = this.composerField.getText();
		
		this.metadataBuilder
				.title(title.isBlank() ? XMLMetadata.DEFAULT_TITLE : title);
		this.metadataBuilder.composer(composer.isBlank() ? null : composer);
		this.metadataBuilder.maxMeasure(
				MeasureNarrowing.measureCount(this.view.getInputText()));
		
		return this.metadataBuilder.build();
	}
	
	/**
	 * @return true iff the narrowing function is active
	 * @since 2021-03-31
	 */
	public boolean isNarrowing() {
		return this.isNarrowing;
	}
	
	/**
	 * @return end of measure range; if it is 0 or less it will be the end of the
	 *         input measure range
	 * @since 2021-04-03
	 */
	int measureEnd() {
		if (this.measureEnd.getText().isEmpty())
			return MeasureNarrowing.measureCount(this.view.getInputText());
		else
			return Integer.valueOf(this.measureEnd.getText());
	}
	
	/**
	 * @return start of measure range
	 * @since 2021-04-03
	 */
	int measureStart() {
		if (this.measureStart.getText().isEmpty())
			return 1;
		else
			return Integer.valueOf(this.measureStart.getText());
	}
	
	/**
	 * Runs whenever the measure entries are edited
	 *
	 * @param e editing action
	 * @since 2021-03-29
	 */
	private void onEntryUpdate(CaretEvent e) {
		// currently, I am doing nothing on an entry update
	}
	
	/**
	 * Sets the time signature for the selected measure range; runs when the "Set
	 * Time Signature" button is pressed
	 *
	 * @param e botton-press action, unused
	 * @since 2021-03-31
	 */
	void setTimeSignature(ActionEvent e) {
		/*
		 * Note: this method allows you to set the time signature when not on
		 * narrowing mode as long as the measure/signature fields are entered, and
		 * that's a good thing.
		 */
		
		if (this.checkInputErrors(true)) {
			final int top = Integer.valueOf(this.topSignatureField.getText());
			final int bottom = Integer
					.valueOf(this.bottomSignatureField.getText());
			
			this.metadataBuilder.setTimeSignature(this.measureStart(),
					this.measureEnd(), TimeSignature.valueOf(top, bottom));
		}
	}
}
