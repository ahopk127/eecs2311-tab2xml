package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tab2xml.parser.MeasureNarrowing.bottomRightCorner;
import static tab2xml.parser.MeasureNarrowing.delinearize;
import static tab2xml.parser.MeasureNarrowing.extractMeasureRange;
import static tab2xml.parser.MeasureNarrowing.linearize;
import static tab2xml.parser.MeasureNarrowing.replaceMeasureRange;
import static tab2xml.parser.MeasureNarrowing.topLeftCorner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

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
	private static final String tab1 = loadTestFile("readme-sample-1.txt");
	private static final String tab1Modified = loadTestFile(
			"readme-sample-1-modified.txt");
	private static final String tab2 = loadTestFile("test2.txt");
	private static final String tab2Linearized = loadTestFile(
			"test2-linearized.txt");
	private static final String tab2Modified = loadTestFile(
			"test2-modified.txt");
	private static final String tab4 = loadTestFile("test4.txt");
	private static final String caprichoArabeTab = loadTestFile(
			"Capricho Arabe.txt");
	
	/**
	 * Loads from a test file.
	 *
	 * @param relativeName relative filename
	 * @return file
	 * @throws IOException from Files.readString
	 * @since 2021-03-29
	 */
	private static final String loadTestFile(String relativeName) {
		try {
			return Files.readString(TEST_FILES.resolve(relativeName))
					.replaceAll("\\r\\n", "\n");
		} catch (final IOException e) {
			throw new RuntimeException(
					"IOException during setup: " + e.getMessage());
		}
	}
	
	/**
	 * Tests that {@link MeasureNarrowing#bottomRightCorner} works properly.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testBottomRightCorner() {
		assertEquals(new StringPosition(5, 16), bottomRightCorner(tab1, 1));
		assertEquals(new StringPosition(5, 31), bottomRightCorner(tab1, 2));
	}
	
	/**
	 * Tests that {@link MeasureNarrowing#delinearize} works properly.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testDelinearize() {
		assertEquals(tab2, delinearize(tab2Linearized, 90));
	}
	
	/**
	 * Tests extracting complex decorated measures (Capricho Arabe tab)
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testExtractDecoratedMeasure() {
		final String measure24 = String.join("\n",
				"|---------------------------------||",
				"|-----3-------3-------4-------2---||",
				"|---------------------0-------2---||",
				"|-----3-------3---------------2---||",
				"|---------1---------------0-------||",
				"|-0---------------1---------------||", "");
		
		assertEquals(measure24, extractMeasureRange(caprichoArabeTab, 24, 24));
	}
	
	/**
	 * Tests extracting simple measures (extract one measure from a one-line tab)
	 */
	@Test
	final void testExtractMeasure() {
		final String measure1 = String.join("\n", "|-----2--------|",
				"|--------------|", "|--------------|", "|-----------2--|",
				"|--3--------2--|", "|--0--0--------|", "");
		
		final String measure3 = String.join("\n", "|--0-----3--0--|",
				"|--0-----0--3--|", "|-----------2--|", "|--2--2--------|",
				"|--------------|", "|--------------|", "");
		
		assertEquals(measure1, extractMeasureRange(tab1, 1, 1));
		assertEquals(measure3, extractMeasureRange(tab1, 3, 3));
	}
	
	/**
	 * Tests extracting complex measures and ranges.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testExtractMultilineMeasure() {
		final String measure23 = String.join("\n",
				"|---------------------------5-8-11--10|--8h10p8---6---5---3---6p5-10p9-12p10-13p12-|",
				"|-3h6-------7-------------7-----------|-----------8-------5------------------------|",
				"|-------7-------5-------5-------------|-------0-------3----------------------------|",
				"|-------7-------7-----7---------------|--------------------------------------------|",
				"|-------------------5-----------------|-----------------------0--------------------|",
				"|---0---------------------------------|--------------------------------------------|",
				"");
		
		assertEquals(measure23, extractMeasureRange(tab2, 2, 3));
	}
	
	/**
	 * Tests extracting repeated measures
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testExtractRepeatedMeasure() {
		final String measure2 = String.join("\n", "||----------0--------4|",
				"||----------0--------||", "||*---------1-------*||",
				"||*---------2-------*||", "||------2---2--------||",
				"||--0-------0--------||", "");
		
		assertEquals(measure2, extractMeasureRange(tab4, 2, 2));
	}
	
	/**
	 * Tests {@link MeasureNarrowing#linearize}.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testLinearize() {
		assertEquals(tab2Linearized, linearize(tab2));
	}
	
	/**
	 * Tests replacing simple measures (extract one measure from a one-line tab)
	 */
	@Test
	final void testReplaceMeasure() {
		// measure replacements
		final String newMeasure2 = String.join("\n", "|-----2--8-----|",
				"|-----5--1-----|", "|-----------0--|", "|-----0--------|",
				"|--0-----3--2--|", "|--------------|", "");
		
		final String newMeasure4 = String.join("\n", "|--------------|",
				"|---35---------|", "|-----2-----4--|", "|-----0--------|",
				"|--0-----------|", "|--------------|", "");
		
		// test the code
		final String measure2Modified = replaceMeasureRange(tab1, 2, 2,
				newMeasure2);
		final String measure24Modified = replaceMeasureRange(measure2Modified, 4,
				4, newMeasure4);
		assertEquals(tab1Modified, measure24Modified);
	}
	
	/**
	 * Tests replacing complex measures and ranges.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testReplaceMultilineMeasure() {
		final String newMeasure23 = String.join("\n",
				"|-----------0---------------5-8-11--10|",
				"|-3h6-------7--------------14---------|",
				"|-------7--12---5-------5--23---------|",
				"|-------7--19---7-----7----32---------|",
				"|----------24-------5------41---------|",
				"|---0------2B------edited--50---------|", "",
				"|--8h10p8---6---5---3---6p5-10p9-12p10-13p12-|",
				"|-----------8-------5---------------------2--|",
				"|-------0-------3-------------------------3--|",
				"|--------------------measures-------------4--|",
				"|-----------------------0-----------------5--|",
				"|-----------------------------------------6--|", "");
		
		assertEquals(tab2Modified, replaceMeasureRange(tab2, 2, 3, newMeasure23));
	}
	
	/**
	 * Tests {@link MeasureNarrowing#topLeftCorner}.
	 * 
	 * @since 2021-03-29
	 */
	@Test
	final void testTopLeftCorner() {
		assertEquals(new StringPosition(0, 1), topLeftCorner(tab1, 1));
		assertEquals(new StringPosition(0, 16), topLeftCorner(tab1, 2));
	}
}
