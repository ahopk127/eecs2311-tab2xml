package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
	 * Reads a string from the provided file, handling any errors that occur and
	 * replacing all newlines with the standard \n.
	 *
	 * @since 2021-03-17
	 */
	private static final String readStringHandleErrors(Path path) {
		return readStringHandleErrors(path, true);
	}
	
	/**
	 * Reads a string from the provided file, handling any errors that occur.
	 * 
	 * @param path            file to read from
	 * @param replaceNewlines whether to replace \r\n with \n
	 *
	 * @since 2021-03-17
	 */
	private static final String readStringHandleErrors(Path path,
			boolean replaceNewlines) {
		try {
			final String text = Files.readString(path);
			return replaceNewlines ? text.replaceAll("\\r\\n", "\n") : text;
		} catch (final IOException e) {
			e.printStackTrace();
			assumeTrue(false,
					"IOException occured during setup of testLoadFromFile().");
			return null;
		}
	}
	
	/**
	 * Tests that the convert() method correctly reads text from the View's
	 * input, converts it, and returns it to the View's output.
	 * <p>
	 * This method does <b>NOT</b> test that the converted tab is correct - that
	 * is the job of the backend tests. The purpose of this test is to test the
	 * integration between the frontend and backend (i.e. that input is recieved
	 * properly, the correct method is triggered, and the output is set
	 * properly).
	 * 
	 * @since 2021-02-22
	 */
	@Test
	final void testConvert() {
		// SETUP - get input and expected output text
		final String input = readStringHandleErrors(
				TEST_FILES.resolve("example-e-major.txt"));
		final String expectedOutput;
		final Instrument instrument = Instrument.GUITAR;
		
		try {
			expectedOutput = new Parser(input, instrument).parse().getFirst();
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass()
					+ " occured during parsing setup of testLoadFromFile().");
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
		final String input = readStringHandleErrors(
				TEST_FILES.resolve("example-e-major.txt"));
		final String expectedOutput;
		final Instrument instrument = Instrument.GUITAR;
		
		try {
			expectedOutput = new Parser(input, instrument).parse().getFirst();
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass()
					+ " occured during parsing setup of testLoadFromFile().");
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
		assertEquals(expectedOutput, readStringHandleErrors(TEST_FILE, false));
	}
	
	@Test
	final void testErrorNoSelectedFile() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		
		// set input text & instrument so that parsing doesn't cause an error
		view.setSelectedInstrument(Instrument.GUITAR);
		view.setInputText(
				readStringHandleErrors(TEST_FILES.resolve("example-e-major.txt")));
		
		// simulate not selecting a file
		view.setSelectedFile(null);
		assertFalse(view.promptForFile(null).isPresent());
		
		// ensure that load/save operations cancel without an error
		assertFalse(presenter.loadInput());
		assertFalse(presenter.saveInput());
		assertFalse(presenter.saveOutput());
		assertFalse(presenter.convertAndSave(true));
		assertFalse(presenter.convertAndSave(false));
		
		// ensure the previous operations did not do any file modification
		assertEquals(0, presenter.fileReads);
		assertEquals(0, presenter.fileWrites);
	}
	
	/**
	 * Tests that invalid text tabs and files show an error message.
	 * 
	 * @since 2021-03-12
	 */
	@Test
	final void testErrors() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		
		// instrument is null
		assertThrows(RuntimeException.class, () -> presenter.convert());
		
		view.setSelectedInstrument(Instrument.GUITAR);
		assertThrows(RuntimeException.class, () -> presenter.convert());
		
		// invalid files
		view.setSelectedFile(TEST_FILES.resolve("NONEXISTENT"));
		assertThrows(RuntimeException.class, () -> presenter.loadInput());
	}
	
	/**
	 * Asserts that text is read from a file and put in the view's input.
	 * 
	 * @since 2021-02-06
	 */
	@Test
	final void testLoadInput() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-read.txt");
		final String expected = readStringHandleErrors(TEST_FILE);
		
		view.setSelectedFile(TEST_FILE);
		presenter.loadInput();
		
		assertEquals(expected, view.getInputText());
	}
	
	/**
	 * Tsets that the save-input feature works.
	 * 
	 * @since 2021-03-17
	 */
	@Test
	final void testSaveInput() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-write.txt");
		
		// save input
		view.setInputText(TEST_STRING);
		view.setSelectedFile(TEST_FILE);
		presenter.saveInput();
		
		// get result of saving
		final String actual = readStringHandleErrors(TEST_FILE);
		
		assertEquals(TEST_STRING, actual);
	}
	
	/**
	 * Asserts that text is read from the view's output and put in the supplied
	 * file.
	 * 
	 * @since 2021-02-06
	 */
	@Test
	final void testSaveOutput() {
		final ViewBot view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-write.txt");
		
		// ensure file does not already have the contents that should be written
		try {
			Files.writeString(TEST_FILE, "Nothing was written!");
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass() + " occured during execution of testSaveToFile().");
		}
		
		view.setOutputText(TEST_STRING);
		view.setSelectedFile(TEST_FILE);
		presenter.saveOutput();
		
		assertEquals(TEST_STRING, readStringHandleErrors(TEST_FILE));
	}
	
}
