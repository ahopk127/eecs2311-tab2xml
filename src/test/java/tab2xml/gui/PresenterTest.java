package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tab2xml.parser.Instrument;
import tab2xml.parser.Parser;

/**
 * Tests related to the Presenter.
 *
 * @author Adrien Hopkins
 *
 * @since 2021-02-06
 */
class PresenterTest {
	private static final String TEST_STRING = "Testing text.";
	private static final Path TEST_FILES = Path.of("src", "test", "resources");
	
	/**
	 * Tests that the convert() method correctly reads text from the View's
	 * input, converts it, and returns it to the View's output.
	 * <p>
	 * This method does <b>NOT</b> that the converted tab is correct - that is
	 * the job of the backend tests. The purpose of this test is to test the
	 * integration between the frontend and backend (i.e. that input is recieved
	 * properly, the correct method is triggered, and the output is set
	 * properly).
	 * 
	 * @since 2021-02-22
	 */
	@Test
	final void testConvert() {
		// SETUP - get input and expected output text
		final String input, expectedOutput;
		final Instrument instrument = Instrument.GUITAR;
		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("example-e-major.txt");
			input = Files.readString(TEST_INPUT_FILE);
			expectedOutput = new Parser(input, instrument).parse();
		} catch (final Exception e) {
			e.printStackTrace();
			assumeTrue(false,
					e.getClass() + " occured during setup of testLoadFromFile().");
			return;
		}
		
		// the body of the test
		final View view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		
		view.setInputText(input);
		view.setSelectedInstrument(instrument);
		presenter.convert();
		assertEquals(expectedOutput, view.getOutputText());
	}
	
	/**
	 * Tests that the "Convert and Save" functionality works properly. Like
	 * {@link #testConvert}, it does not test that the outputted MusicXML is
	 * correct, only that the Presenter correctly uses the backend code and
	 * correctly saves the converted MusicXML.
	 *
	 * @param showInView argument passed to {@link Presenter#convertAndSave}. The
	 *                   functionality of this argument is also tested.
	 * @since 2021-02-27
	 */
	@ParameterizedTest
	@ValueSource(booleans = { true, false })
	final void testConvertAndSave(boolean showInView) {
		// SETUP - get input and expected output text
		final String input, expectedOutput;
		final Instrument instrument = Instrument.GUITAR;
		try {
			final Path TEST_INPUT_FILE = TEST_FILES.resolve("example-e-major.txt");
			input = Files.readString(TEST_INPUT_FILE);
			expectedOutput = new Parser(input, instrument).parse();
		} catch (final Exception e) {
			e.printStackTrace();
			assumeTrue(false,
					e.getClass() + " occured during setup of testLoadFromFile().");
			return;
		}
		
		// setup view and presenter
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-write.txt");
		
		view.setOutputText("");
		view.setInputText(input);
		view.setSelectedInstrument(instrument);
		view.setSelectedFile(TEST_FILE);
		
		presenter.convertAndSave(showInView);
		
		// if showInView is true, output should be in output text
		// if showInView is false, output should NOT be in output text
		if (showInView) {
			assertEquals(expectedOutput, view.getOutputText());
		} else {
			assertEquals("", view.getOutputText());
		}
		
		// test that saving worked
		try {
			assertEquals(expectedOutput, Files.readString(TEST_FILE));
		} catch (final IOException e) {
			e.printStackTrace();
			fail("I/O Error occured during testing: " + e.getMessage());
		}
	}
	
	/**
	 * Asserts that text is read from a file and put in the view's input.
	 * 
	 * @since 2021-02-06
	 */
	@Test
	final void testLoadFromFile() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-read.txt");
		
		try {
			assumeTrue(TEST_STRING.equals(Files.readString(TEST_FILE)));
			
			view.setSelectedFile(TEST_FILE);
			presenter.loadFromFile();
			
			assertEquals(TEST_STRING, view.getInputText());
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass()
					+ " occured during execution of testLoadFromFile().");
		}
	}
	
	/**
	 * Asserts that text is read from the view's output and put in the supplied
	 * file.
	 * 
	 * @since 2021-02-06
	 */
	@Test
	final void testSaveToFile() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-write.txt");
		
		try {
			Files.writeString(TEST_FILE, "");
			
			view.setOutputText(TEST_STRING);
			view.setSelectedFile(TEST_FILE);
			presenter.saveToFile();
			
			assertEquals(TEST_STRING, Files.readString(TEST_FILE));
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass() + " occured during execution of testSaveToFile().");
		}
	}
	
}
