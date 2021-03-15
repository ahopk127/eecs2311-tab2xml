package tab2xml.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

/**
 * A JTextArea with prompt text. The prompt text is shown when there is no text
 * in the area and the area is not focused.
 *
 * @author Adrien Hopkins
 *
 * @since 2021-02-03
 */
public final class PromptingTextArea extends JTextArea {
	/**
	 * When the text area is focused and the prompt text is there, removes the
	 * prompt text to allow the user to type.
	 * <p>
	 * When the text area is unfocused and the text area is empty, adds prompt
	 * text back.
	 *
	 * @since 2021-02-05
	 */
	private final class PromptFocusListener implements FocusListener {
		
		@Override
		public void focusGained(FocusEvent e) {
			if (PromptingTextArea.this.prompting) {
				PromptingTextArea.this.disablePrompt();
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			if (PromptingTextArea.this.getText().isBlank()) {
				PromptingTextArea.this.enablePrompt();
			}
		}
		
	}
	
	/** For serialization. */
	private static final long serialVersionUID = 1238525148916870118L;
	
	/** The colour of prompt text. */
	public static final Color PROMPT_TEXT_COLOR = Color.GRAY;
	/** The colour of non-prompt text. */
	public static final Color REGULAR_TEXT_COLOR = Color.BLACK;
	
	/** The text that is prompted. */
	private String promptText;
	
	/** Whether prompt text is active or not. */
	private boolean prompting;
	
	/** The font used for regular text. */
	private Font regularFont;
	
	/** The font used for prompting. */
	private Font promptFont;
	
	public PromptingTextArea(String promptText) {
		this(promptText, 0, 0);
	}
	
	public PromptingTextArea(String promptText, int rows, int columns) {
		super(promptText, rows, columns);
		this.promptText = promptText;
		
		this.regularFont = this.getFont();
		this.promptFont = this.getFont().deriveFont(Font.ITALIC);
		
		this.enablePrompt();
		this.addFocusListener(new PromptFocusListener());
	}
	
	/**
	 * Removes the prompt text.
	 * 
	 * @since 2021-02-05
	 */
	private final void disablePrompt() {
		this.prompting = false;
		super.setText("");
		this.setForeground(REGULAR_TEXT_COLOR);
		super.setFont(this.regularFont);
	}
	
	/**
	 * Brings the prompt text back.
	 * 
	 * @since 2021-02-05
	 */
	private final void enablePrompt() {
		this.prompting = true;
		super.setText(this.getPromptText());
		this.setForeground(PROMPT_TEXT_COLOR);
		
		super.setFont(this.promptFont);
	}
	
	/**
	 * @return the promptFont
	 * @since 2021-02-05
	 */
	public Font getPromptFont() {
		return this.promptFont;
	}
	
	/**
	 * @return the promptText
	 * @since 2021-02-03
	 */
	public String getPromptText() {
		return this.promptText;
	}
	
	/**
	 * @return the regularFont
	 * @since 2021-02-05
	 */
	public Font getRegularFont() {
		return this.regularFont;
	}
	
	/**
	 * @return whether the prompt text is active or not
	 * @since 2021-02-05
	 */
	public boolean isPrompting() {
		return this.prompting;
	}
	
	/**
	 * Sets both the prompt font and the regular font.
	 * <p>
	 * The resulting fonts depend on the current state (this means that the
	 * currently-used font will always be the provided font):
	 * <p>
	 * If the prompt text is active, the prompt font will be the inputted font
	 * and the regular font will be the inputted font with the style set to
	 * {@link Font#PLAIN}.<br>
	 * If the prompt text is inactive, the regular font will be the inputted font
	 * and the prompt font will be the inputted font with the style set to
	 * {@link Font#ITALIC}.
	 */
	@Override
	public void setFont(Font f) {
		if (this.prompting) {
			this.setPromptFont(f);
			this.setRegularFont(f.deriveFont(Font.PLAIN));
		} else {
			this.setPromptFont(f.deriveFont(Font.ITALIC));
			this.setRegularFont(f);
		}
	}
	
	/**
	 * @param promptFont the font to use as prompting, if null, uses an italic
	 *                   version of regular font
	 * @since 2021-02-05
	 */
	public void setPromptFont(Font promptFont) {
		this.promptFont = promptFont == null
				? this.regularFont.deriveFont(Font.ITALIC)
				: promptFont;
		if (this.prompting) {
			super.setFont(this.promptFont);
		}
	}
	
	/**
	 * Forcibly enables or disables the prompt. Enabling the prompt clears any
	 * existing text in the text area.
	 *
	 * @param prompting
	 * @since 2021-02-05
	 */
	public void setPrompting(boolean prompting) {
		if (prompting) {
			this.enablePrompt();
		} else {
			this.disablePrompt();
		}
	}
	
	/**
	 * @param promptText the text to use as a prompt
	 * @since 2021-02-03
	 */
	public void setPromptText(String promptText) {
		this.promptText = promptText;
		if (this.prompting) {
			super.setText(this.promptText);
		}
	}
	
	/**
	 * @param regularFont the font to use when not prompting
	 * @since 2021-02-05
	 */
	public void setRegularFont(Font regularFont) {
		this.regularFont = regularFont;
		if (!this.prompting) {
			super.setFont(this.regularFont);
		}
	}
	
	@Override
	public void setText(String t) {
		if (this.prompting) {
			this.disablePrompt();
		}
		super.setText(t);
	}
}
