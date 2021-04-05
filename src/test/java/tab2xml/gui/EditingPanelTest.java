package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static tab2xml.ResourceLoading.loadTextResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tab2xml.IntRange;
import tab2xml.model.TimeSignature;
import tab2xml.parser.MeasureNarrowing;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * Tests the {@link EditingPanel} class.
 *
 * @since 2021-04-05
 */
class EditingPanelTest {
	private static final String TESTING_TITLE = "Testing Title";
	private static final String TESTING_COMPOSER = "Testing Composer";
	
	/** The tab to be used for testing. */
	private static final String testTab = loadTextResource(
			"readme-sample-1.txt");
	
	private ViewBot view;
	private EditingPanel panel;
	
	/**
	 * Replaces {@link #view} and {@link #panel} with a fresh ViewBot and
	 * EditingPanel, and loads a five-measure guitar tab into the view.
	 * 
	 * @since 2021-04-05
	 */
	@BeforeEach
	final void setUp() {
		this.view = new ViewBot();
		this.panel = new EditingPanel(this.view);
		
		// load input text
		final String textTab;
		try {
			textTab = Files.readString(
					Path.of("src", "test", "resources", "readme-sample-1.txt"));
		} catch (final IOException e) {
			assumeTrue(false, "IOException occured loading sample tabs.");
			return;
		}
		this.view.setInputText(textTab);
	}
	
	/**
	 * Tests that the composer field is set properly.
	 * 
	 * @since 2021-04-05
	 */
	@Test
	final void testComposer() {
		// test that default title is set
		this.panel.composerField.setText("");
		final XMLMetadata metadata1 = this.panel.getMetadata();
		assertFalse(metadata1.composer().isPresent());
		
		// set the title and test that that is set
		this.panel.composerField.setText(TESTING_COMPOSER);
		final XMLMetadata metadata2 = this.panel.getMetadata();
		assertEquals(TESTING_COMPOSER, metadata2.composer().orElseThrow());
	}
	
	/**
	 * Tests that errors in the set measure numbers are determined correctly.
	 * 
	 * @since 2021-04-05
	 */
	@Test
	void testErrors() {
		// measures
		this.panel.measureStart.setText("-1");
		this.panel.measureEnd.setText("");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Negative measure start did not cause error.");
		
		this.panel.measureStart.setText("");
		this.panel.measureEnd.setText("-1");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Negative measure end did not cause error.");
		
		this.panel.measureStart.setText("100");
		this.panel.measureEnd.setText("");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Measure start above measure count did not cause error.");
		
		this.panel.measureStart.setText("100");
		this.panel.measureEnd.setText("200");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Measure start above measure count did not cause error.");
		
		this.panel.measureStart.setText("");
		this.panel.measureEnd.setText("6");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Measure end above measure count did not cause error.");
		
		this.panel.measureStart.setText("4");
		this.panel.measureEnd.setText("2");
		assertThrows(RuntimeException.class,
				() -> this.panel.editMeasureButton.doClick(),
				"Measure start after end did not cause error.");
		
		this.panel.measureStart.setText("2");
		this.panel.measureEnd.setText("5");
		assertDoesNotThrow(() -> this.panel.editMeasureButton.doClick(),
				"Error thrown with correct measure input.");
		
		// time signatures
		this.panel.topSignatureField.setText("-1");
		this.panel.bottomSignatureField.setText("4");
		assertThrows(RuntimeException.class,
				() -> this.panel.setSignatureButton.doClick(),
				"Negative time signature top did not cause error.");
		
		this.panel.topSignatureField.setText("4");
		this.panel.bottomSignatureField.setText("-1");
		assertThrows(RuntimeException.class,
				() -> this.panel.setSignatureButton.doClick(),
				"Negative time signature bottom did not cause error.");
		
		this.panel.topSignatureField.setText("4");
		this.panel.bottomSignatureField.setText("4");
		assertDoesNotThrow(() -> this.panel.setSignatureButton.doClick(),
				"Error thrown with correct time signature input");
	}
	
	/**
	 * Tests that the measure range is correctly calculated for empty text boxes
	 * 
	 * @since 2021-04-05
	 */
	@Test
	final void testMeasureRange1() {
		this.panel.measureStart.setText("");
		this.panel.measureEnd.setText("");
		
		assertEquals(1, this.panel.measureStart());
		assertEquals(5, this.panel.measureEnd());
	}
	
	/**
	 * Tests that the measure range is correctly set for nen-empty text boxes
	 * 
	 * @since 2021-04-05
	 */
	@Test
	final void testMeasureRange2() {
		this.panel.measureStart.setText("3");
		this.panel.measureEnd.setText("4");
		
		assertEquals(3, this.panel.measureStart());
		assertEquals(4, this.panel.measureEnd());
	}
	
	/**
	 * Tests that the narrowing functionality is used correctly. Does not test
	 * whether the narrowed text is correct, since that is the job of
	 * MeasureNarrowingTest.
	 * 
	 * @since 2021-04-05
	 */
	@Test
	void testNarrowing() {
		// set tab (5 measures) and measure range
		this.view.setInputText(testTab);
		this.panel.measureStart.setText("2");
		this.panel.measureEnd.setText("4");
		
		// press edit button, if it is enabled
		assertTrue(this.panel.editMeasureButton.isEnabled(),
				"Edit button failed to enable");
		this.panel.editMeasureButton.doClick();
		
		// assert that panel is narrowing & has the correct text
		final String expected = MeasureNarrowing.extractMeasureRange(testTab, 2,
				4);
		assertEquals(expected, this.view.getNarrowedText());
		assertTrue(this.panel.isNarrowing(), "Panel did not enable narrowing");
		
		// edit narrowed text
		final String replacement = expected.replaceFirst("2", "3");
		this.view.setNarrowedText(replacement);
		
		// press Done button, if it is enabled
		assertTrue(this.panel.doneEditingButton.isEnabled(),
				"Done button failed to enable");
		this.panel.doneEditingButton.doClick();
		
		// assert that panel is not narrowing & input text is changed
		final String expected2 = MeasureNarrowing.replaceMeasureRange(testTab, 2,
				4, replacement);
		assertEquals(expected2, this.view.getInputText());
		assertFalse(this.panel.isNarrowing(), "Panel did not disable narrowing");
	}
	
	/**
	 * Tests that time signatures are set properly.
	 * 
	 * @since 2021-04-05
	 */
	@Test
	final void testTimeSignature() {
		final TimeSignature ts = TimeSignature.valueOf(11, 16);
		
		this.panel.measureStart.setText("2");
		this.panel.measureEnd.setText("4");
		
		this.panel.topSignatureField.setText("11");
		this.panel.bottomSignatureField.setText("16");
		this.panel.setTimeSignature(null);
		
		final XMLMetadata metadata = this.panel.getMetadata();
		assertEquals(ts,
				metadata.timeSignatureRanges().get(IntRange.inclusive(2, 4)));
	}
	
	/**
	 * Test that the title field is set correctly.
	 * 
	 * @since 2021-04-05
	 */
	@Test
	final void testTitle() {
		// test that default title is set
		this.panel.titleField.setText("");
		final XMLMetadata metadata1 = this.panel.getMetadata();
		assertEquals(XMLMetadata.DEFAULT_TITLE, metadata1.title());
		
		// set the title and test that that is set
		this.panel.titleField.setText(TESTING_TITLE);
		final XMLMetadata metadata2 = this.panel.getMetadata();
		assertEquals(TESTING_TITLE, metadata2.title());
	}
	
}
