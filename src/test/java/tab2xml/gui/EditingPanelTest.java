package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tab2xml.IntRange;
import tab2xml.model.TimeSignature;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * Tests the {@link EditingPanel} class.
 *
 * @since 2021-04-05
 */
class EditingPanelTest {
	private static final String TESTING_TITLE = "Testing Title";
	private static final String TESTING_COMPOSER = "Testing Composer";
	
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
