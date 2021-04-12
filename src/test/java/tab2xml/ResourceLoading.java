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
	private static final Path TEST_FILES = Path.of("src", "test", "resources");
	
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
