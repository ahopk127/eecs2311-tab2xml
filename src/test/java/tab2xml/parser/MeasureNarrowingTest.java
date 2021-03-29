package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tab2xml.parser.MeasureNarrowing.bottomRightCorner;
import static tab2xml.parser.MeasureNarrowing.extractMeasureRange;
import static tab2xml.parser.MeasureNarrowing.replaceMeasureRange;
import static tab2xml.parser.MeasureNarrowing.topLeftCorner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import tab2xml.parser.MeasureNarrowing;
import tab2xml.parser.MeasureNarrowing.StringPosition;

/**
 * Test for {@link MeasureNarrowing}.
 *
 * @author Adrien Hopkins
 * @since 2021-03-26
 */
class MeasureNarrowingTest {
	/**
	 * Test files.
	 */
	private static final Path TEST_FILES = Path.of("src", "test", "resources");
	
	// Sample tabs
	private static final String tab1;
	private static final String tab1Modified;
	
	// load testing tabs
	static {
		try {
			tab1 = Files.readString(TEST_FILES.resolve("readme-sample-1.txt"));
			tab1Modified = Files
					.readString(TEST_FILES.resolve("readme-sample-1-modified.txt"));
		} catch (final IOException e) {
			throw new RuntimeException(
					"IOException during setup:" + e.getMessage());
		}
	}
	
	@Test
	final void testBottomRightCorner() {
		assertEquals(new StringPosition(5, 16), bottomRightCorner(tab1, 1));
		assertEquals(new StringPosition(5, 31), bottomRightCorner(tab1, 2));
	}
	
	/**
	 * Tests extracting simple measures (extract one measure from a one-line tab)
	 */
	@Test
	final void testExtractMeasure() {
		final String measure1 = String.join("\n", "|-----2--------|",
				"|--------------|", "|--------------|", "|-----------2--|",
				"|--3--------2--|", "|--0--0--------|") + "\n";
		
		final String measure3 = String.join("\n", "|--0-----3--0--|",
				"|--0-----0--3--|", "|-----------2--|", "|--2--2--------|",
				"|--------------|", "|--------------|") + "\n";
		
		assertEquals(measure1, extractMeasureRange(tab1, 1, 1));
		assertEquals(measure3, extractMeasureRange(tab1, 3, 3));
	}
	
	/**
	 * Tests replacing simple measures (extract one measure from a one-line tab)
	 */
	@Test
	final void testReplaceMeasure() {
		// measure replacements
		final String newMeasure2 = String.join("\n", "|-----2--8-----|",
				"|-----5--1-----|", "|-----------0--|", "|-----0--------|",
				"|--0-----3--2--|", "|--------------|") + "\n";
		
		final String newMeasure4 = String.join("\n", "|--------------|",
				"|---35---------|", "|-----2-----4--|", "|-----0--------|",
				"|--0-----------|", "|--------------|") + "\n";
		
		// test the code
		final String measure2Modified = replaceMeasureRange(tab1, 2, 2,
				newMeasure2);
		final String measure24Modified = replaceMeasureRange(measure2Modified, 4,
				4, newMeasure4);
		assertEquals(tab1Modified, measure24Modified);
	}
	
	@Test
	final void testTopLeftCorner() {
		assertEquals(new StringPosition(0, 1), topLeftCorner(tab1, 1));
		assertEquals(new StringPosition(0, 16), topLeftCorner(tab1, 2));
	}
	
}
