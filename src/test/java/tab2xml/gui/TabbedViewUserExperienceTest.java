package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tab2xml.ResourceLoading.loadTextResource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tab2xml.exceptions.InvalidInputException;
import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.Instrument;
import tab2xml.parser.Parser;
import tab2xml.xmlconversion.XMLMetadata;

class TabbedViewUserExperienceTest {
	/** The tab to be used for testing. */
	private static final String testTab = loadTextResource(
			"readme-sample-1.txt");
	
	private static final String xmlOutput;
	static {
		try {
			final Parser parser = new Parser(testTab, Instrument.GUITAR,
					XMLMetadata.fromDefaultTitle());
			xmlOutput = parser.parse().getFirst();
		} catch (final InvalidInputException | InvalidTokenException e) {
			throw new AssertionError("Parsing error occured during setup.", e);
		}
	}
	
	/** The view being tested. */
	private final TabbedView view = new TabbedView();
	
	/**
	 * Tests pressing the Convert button
	 * 
	 * @since 2021-04-05
	 */
	@Test
	void testConvert() {
		this.view.setInputText(testTab);
		this.view.updateButtons();
		assertTrue(this.view.convertButton.isEnabled());
		
		this.view.convertButton.doClick();
		assertEquals(xmlOutput, this.view.getOutputText());
		assertEquals(xmlOutput, this.view.output.getText());
	}
	
	/**
	 * Tests the button enabling/disabling functionality for buttons affected by
	 * the state of the input text box
	 *
	 * @param addText whether text should be put in the box or not
	 * @since 2021-04-05
	 */
	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	void testInputButtonState(boolean addText) {
		this.view.setInputText(addText ? testTab : "");
		
		assertEquals(addText, this.view.convertButton.isEnabled());
		assertEquals(addText, this.view.convertAndSave.isEnabled());
		assertEquals(addText, this.view.saveInput.isEnabled());
	}
	
	/**
	 * Tests the button enabling/disabling functionality for buttons affected by
	 * the state of the output text box
	 *
	 * @param addText whether text should be put in the box or not
	 * @since 2021-04-05
	 */
	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	void testOutputButtonState(boolean addText) {
		this.view.setOutputText(addText ? testTab : "");
		
		assertEquals(addText, this.view.saveOutput.isEnabled());
	}
}
