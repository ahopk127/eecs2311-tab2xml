/**
 * Copyright (C) 2021 Adrien Hopkins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
	
	private static final Color PROMPT_TEXT = Color.GRAY;
	private static final Color REGULAR_TEXT = Color.BLACK;
	
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
		this.setForeground(REGULAR_TEXT);
		this.setFont(
				this.regularFont == null ? this.getFont() : this.regularFont);
	}
	
	/**
	 * Brings the prompt text back.
	 * 
	 * @since 2021-02-05
	 */
	private final void enablePrompt() {
		this.prompting = true;
		super.setText(this.getPromptText());
		this.setForeground(PROMPT_TEXT);
		
		if (this.promptFont == null) {
			this.promptFont = this.getFont().deriveFont(Font.ITALIC);
		}
		this.setFont(this.promptFont);
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
	 * @param promptFont the promptFont to set
	 * @since 2021-02-05
	 */
	public void setPromptFont(Font promptFont) {
		this.promptFont = promptFont == null
				? this.regularFont.deriveFont(Font.ITALIC)
				: promptFont;
		if (this.prompting) {
			this.setFont(this.promptFont);
		}
	}
	
	/**
	 * @param promptText the promptText to set
	 * @since 2021-02-03
	 */
	public void setPromptText(String promptText) {
		this.promptText = promptText;
		if (this.prompting) {
			super.setText(this.promptText);
		}
	}
	
	/**
	 * @param regularFont the regularFont to set
	 * @since 2021-02-05
	 */
	public void setRegularFont(Font regularFont) {
		this.regularFont = regularFont;
		if (!this.prompting) {
			this.setFont(this.regularFont);
		}
	}
	
	@Override
	public void setText(String t) {
		this.disablePrompt();
		super.setText(t);
	}
}
