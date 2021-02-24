package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import tab2xml.gui.View.ViewType;
import tab2xml.parser.Instrument;

/**
 * Tests related to the View.
 * <p>
 * If any optional method used in a test is not implemented, the test will be
 * skipped.
 * 
 * @author Adrien Hopkins
 *
 * @since 2021-02-06
 */
class ViewTest {
	private static final String TEST_STRING = "Testing text.";
	
	/**
	 * @return One of each kind of View.
	 * @since 2021-02-06
	 */
	static final View[] getViews() {
		final View[] views = new View[ViewType.values().length + 1];
		for (final ViewType viewType : ViewType.values()) {
			views[viewType.ordinal()] = viewType.create();
		}
		views[ViewType.values().length] = new ViewBot();
		return views;
	}
	
	/**
	 * Tests that the view can correctly set and get input text.
	 *
	 * @param v view to test
	 * @since 2021-02-06
	 */
	@ParameterizedTest
	@MethodSource("getViews")
	final void testInputText(View v) {
		try {
			v.setInputText(TEST_STRING);
			
			assertEquals(TEST_STRING, v.getInputText());
		} catch (final UnsupportedOperationException e) {
			assumeTrue(false, v + " does not support setInputText().");
		}
	}
	
	/**
	 * Tests that the view can correctly set and get their selected instrument.
	 *
	 * @param v view to test
	 * @since 2021-02-06
	 */
	@ParameterizedTest
	@MethodSource("getViews")
	final void testinstrumentSelection(View v) {
		try {
			v.setSelectedInstrument(Instrument.DRUM);
			
			assertEquals(Instrument.DRUM, v.getSelectedInstrument());
		} catch (final UnsupportedOperationException e) {
			assumeTrue(false, v + " does not support setSelectedInstrument().");
		}
	}
	
	/**
	 * Tests that the view can correctly set and get their output text.
	 *
	 * @param v view to test
	 * @since 2021-02-06
	 */
	@ParameterizedTest
	@MethodSource("getViews")
	final void testOutputText(View v) {
		try {
			v.setOutputText(TEST_STRING);
			
			assertEquals(TEST_STRING, v.getOutputText());
		} catch (final UnsupportedOperationException e) {
			assumeTrue(false, v + " does not support getOutputText().");
		}
	}
}
