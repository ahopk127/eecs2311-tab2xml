package tab2xml.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

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
	 * Asserts that text is read from a file and put in the view's input.
	 * 
	 * @since 2021-02-06
	 */
	@Test
	final void testLoadFromFile() {
		final View view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-read.txt");
		
		try {
			assumeTrue(TEST_STRING.equals(Files.readString(TEST_FILE)));
			
			presenter.loadFromFile(TEST_FILE);
			
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
		final View view = View.createViewBot();
		final Presenter presenter = new Presenter(view);
		final Path TEST_FILE = TEST_FILES.resolve("test-write.txt");
		
		try {
			Files.writeString(TEST_FILE, "");
			
			view.setOutputText(TEST_STRING);
			presenter.saveToFile(TEST_FILE);
			
			assertEquals(TEST_STRING, Files.readString(TEST_FILE));
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getClass() + " occured during execution of testSaveToFile().");
		}
	}
	
}
