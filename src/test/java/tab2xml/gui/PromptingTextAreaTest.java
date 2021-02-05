package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.FocusListener;

import org.junit.jupiter.api.Test;

/**
 * Testing the {@link PromptingTextArea} class.
 *
 * @since 2021-02-05
 */
class PromptingTextAreaTest {
	
	private static void simulateGainingFocus(PromptingTextArea area) {
		final FocusListener[] array = area.getFocusListeners();
		array[array.length - 1].focusGained(null);
	}
	
	private static void simulateLosingFocus(PromptingTextArea area) {
		final FocusListener[] array = area.getFocusListeners();
		array[array.length - 1].focusLost(null);
	}
	
	/**
	 * Ensures the prompt text displays properly & reacts to focus
	 * 
	 * @since 2021-02-05
	 */
	@Test
	final void testPrompt1() {
		final String PROMPT_TEXT = "PROMPT";
		final PromptingTextArea area = new PromptingTextArea(PROMPT_TEXT);
		
		simulateLosingFocus(area);
		
		assertEquals(PROMPT_TEXT, area.getText());
		
		simulateGainingFocus(area);
		
		assertEquals("", area.getText());
	}
	
	/**
	 * Ensures the prompt text reacts correctly to typing and setText()
	 * 
	 * @since 2021-02-05
	 */
	@Test
	final void testPrompt2() {
		final String PROMPT_TEXT = "PROMPT";
		final PromptingTextArea area = new PromptingTextArea(PROMPT_TEXT);
		
		simulateLosingFocus(area);
		
		area.setText("hi");
		
		assertEquals("hi", area.getText());
		assertFalse(area.isPrompting());
		
		simulateGainingFocus(area);
		area.setText(""); // simulate deleting everything
		
		assertEquals("", area.getText());
		assertFalse(area.isPrompting());
		
		simulateLosingFocus(area);
		
		assertEquals(PROMPT_TEXT, area.getText());
		assertTrue(area.isPrompting());
	}
	
	/**
	 * Ensure that the PromptingTextArea sets its colour properly
	 * 
	 * @since 2021-02-05
	 */
	@Test
	final void testPromptColour() {
		final String PROMPT_TEXT = "PROMPT";
		final PromptingTextArea area = new PromptingTextArea(PROMPT_TEXT);
		
		area.setPrompting(false);
		assertEquals(PromptingTextArea.REGULAR_TEXT_COLOR, area.getForeground());
		
		area.setPrompting(true);
		assertEquals(PromptingTextArea.PROMPT_TEXT_COLOR, area.getForeground());
		
	}
}
