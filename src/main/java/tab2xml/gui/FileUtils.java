package tab2xml.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utilities related to reading from and writing to files.
 *
 * @since 2021-01-20
 */
final class FileUtils {
	/**
	 * Reads a file and returns its contents as a String. Lines are separated by
	 * the newline (\n) character.
	 *
	 * @throws IOException if the file cannot be opened for reading, or any other
	 *                     I/O error occurs
	 * @since 2021-01-20
	 */
	public static String readFile(File file) throws IOException {
		final StringBuilder result = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			// read file line by line
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append("\n");
			}
		}
		return result.toString();
	}
	
	/**
	 * Writes the text {@code text} to the file {@code file}.
	 *
	 * @throws IOException if the file cannot be opened for writing, or another
	 *                     I/O error occurs
	 * @since 2021-01-20
	 */
	public static void writeToFile(File file, String text) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(text);
		}
	}
	
	// static utility class - should not be initialized
	private FileUtils() {
		throw new AssertionError();
	}
}
