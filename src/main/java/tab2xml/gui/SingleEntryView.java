package tab2xml.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

/**
 * A view with a single text box, handling both input and output.
 *
 * @since 2021-01-18
 */
final class SingleEntryView extends AbstractSwingView {
	/**
	 * Whether the text box is storing input text or output text.
	 *
	 * @since 2021-01-29
	 */
	private static enum State {
		INPUT {
			@Override
			public void enable(SingleEntryView view) {
				view.textBoxState = INPUT;
				view.convertButton.setEnabled(true);
				view.revertButton.setEnabled(false);
				view.saveFileButton.setText("Convert and Save");
			}
		},
		OUTPUT {
			@Override
			public void enable(SingleEntryView view) {
				view.textBoxState = OUTPUT;
				view.convertButton.setEnabled(false);
				view.revertButton.setEnabled(true);
				view.saveFileButton.setText("     Save to File     ");
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
	 * Creates and opens a View.
	 *
	 * @since 2021-01-18
	 */
	public static void main(String[] args) {
		View.enableSystemLookAndFeel();
		View.createView(View.ViewType.SINGLE_ENTRY);
	}
	
	/** The text box that handles both input and output. */
	private final PromptingTextArea textBox;
	/** The button that converts tab to XML. */
	private final JButton convertButton;
	/** The undo button for conversion. */
	private final JButton revertButton;
	
	/** The button that saves output text to files. */
	private final JButton saveFileButton;
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
		this.frame.setResizable(false);
		
		// create components
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.frame.add(new BackgroundPanel(mainPanel));
		
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// text box
		this.textBox = new PromptingTextArea(
				"Enter text tab or load it from a file...", 24, 80);
		this.textBox.setBorder(new LineBorder(Color.BLACK));
		this.textBox.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 12));
		this.textBox.addCaretListener(
				e -> this.textBox.getHighlighter().removeAllHighlights());
		this.setUpFileDragAndDrop();
		mainPanel.add(
				new JScrollPane(this.textBox,
						ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS),
				BorderLayout.CENTER);
		
		// buttons
		final Insets buttonInsets = new Insets(3, 8, 3, 8);
		
		final JButton loadFileButton = new JButton("Load From File");
		loadFileButton.addActionListener(e -> this.presenter.loadInput());
		buttonPanel.add(loadFileButton, gridBag(1, 0, 1, 1, buttonInsets));
		
		this.convertButton = new JButton("Convert");
		this.convertButton.addActionListener(e -> this.presenter.convert());
		buttonPanel.add(this.convertButton, gridBag(2, 0, 1, 1, buttonInsets));
		
		this.revertButton = new JButton("Undo Conversion");
		this.revertButton
				.addActionListener(e -> this.setInputText(this.previousInputText));
		buttonPanel.add(this.revertButton, gridBag(3, 0, 1, 1, buttonInsets));
		
		this.saveFileButton = new JButton("Save to File");
		this.saveFileButton.addActionListener(e -> {
			if (this.textBoxState == State.INPUT) {
				this.presenter.convertAndSave(false);
			} else {
				this.presenter.saveOutput();
			}
		});
		buttonPanel.add(this.saveFileButton, gridBag(4, 0, 1, 1, buttonInsets));
		
		// combo boxes
		buttonPanel.add(this.getInstrumentSelector(),
				gridBag(0, 0, 1, 1, buttonInsets));
		
		// set the frame to INPUT state.
		State.INPUT.enable(this);
		
		// give everything the correct size
		this.frame.pack();
		
		loadFileButton.requestFocusInWindow();
		
		// open the window
		this.frame.setVisible(true);
	}
	
	@Override
	protected JTextComponent getInput() {
		return this.textBox;
	}
	
	@Override
	public String getInputText() {
		return this.textBoxState == State.INPUT ? this.textBox.getText()
				: this.previousInputText;
	}
	
	@Override
	protected JTextComponent getOutput() {
		return this.textBox;
	}
	
	@Override
	public String getOutputText() {
		return this.textBoxState == State.OUTPUT ? this.textBox.getText() : "";
	}
	
	@Override
	public void setInputText(String text) {
		this.previousInputText = null;
		if (text.isBlank()) {
			this.textBox.setPrompting(true);
		} else {
			this.textBox.setText(text);
		}
		State.INPUT.enable(this);
	}
	
	@Override
	public void setOutputText(String text) {
		this.previousInputText = this.textBox.isPrompting() ? ""
				: this.textBox.getText();
		this.textBox.setText(text);
		State.OUTPUT.enable(this);
	}
}
