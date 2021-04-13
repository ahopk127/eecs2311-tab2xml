package tab2xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class that can be used to load text tabs for testing.
 *
 * @author Adrien Hopkins
 * @since 2021-04-05
 */
public final class ResourceLoading {
	/** Directory for test files */
	public static final Path TEST_FILES = Path.of("src", "test", "resources");
	/** Directory for test tabs */
	public static final Path TEST_TABS = TEST_FILES.resolve("test-tabs");
	
	/**
	 * Loads a text tab from the sample tabs directory, for testing.
	 *
	 * @param name name of tab
	 * @return fire contents as String
	 * @since 2021-04-13
	 */
	public static final String loadTestTab(String name) {
		final String filepath = name.contains(".") ? name : name + ".txt";
		
		try {
			return Files.readString(TEST_TABS.resolve(filepath))
					.replaceAll("\\r\\n", "\n");
		} catch (final IOException e) {
			throw new AssertionError("I/O Exception occured during setup");
		}
	}
	
	/**
	 * Loads a text resource
	 *
	 * @param filepath filepath in resources folder, such as "test0.txt"
	 * @return file contents as string
	 * @since 2021-04-05
	 */
	public static final String loadTextResource(String filepath) {
		try {
			return Files.readString(TEST_FILES.resolve(filepath))
					.replaceAll("\\r\\n", "\n");
		} catch (final IOException e) {
			throw new AssertionError("I/O Exception occured during setup");
		}
	}
}
