package tab2xml.xmlconversion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tab2xml.IntRange;
import tab2xml.model.TimeSignature;
import tab2xml.xmlconversion.XMLMetadata.Builder;

/**
 * Tests the time signature functionality of {@link XMLMetadata}
 *
 * @author Adrien Hopkins
 * @since 2021-03-31
 */
final class TimeSignatureTest {
	/**
	 * Tests that time signatures are set correctly, with overwriting.
	 * 
	 * @since 2021-03-31
	 */
	@Test
	void testNonDisjointIntervals() {
		final Builder b = new Builder();
		
		final TimeSignature ts1 = TimeSignature.valueOf(3, 4);
		final TimeSignature ts2 = TimeSignature.valueOf(6, 8);
		
		b.setTimeSignature(2, 5, ts1);
		b.setTimeSignature(4, 6, ts2); // overwrites measures 4 & 5
		
		final XMLMetadata metadata = b.build();
		final var timeSignatures = metadata.getTimeSignatures();
		final var timeSigRanges = metadata.getTimeSignatureRanges();
		
		for (int i = 2; i <= 3; i++) {
			assertEquals(ts1, timeSignatures.get(i));
		}
		for (int i = 4; i <= 6; i++) {
			assertEquals(ts2, timeSignatures.get(i));
		}
		
		assertTrue(timeSigRanges.containsKey(IntRange.inclusive(2, 3)));
		assertEquals(ts1, timeSigRanges.get(IntRange.inclusive(2, 3)));
		
		assertTrue(timeSigRanges.containsKey(IntRange.inclusive(4, 6)));
		assertEquals(ts2, timeSigRanges.get(IntRange.inclusive(4, 6)));
	}
	
	/**
	 * Tests that time signatures are set correctly. No overwriting is used in
	 * this test.
	 * 
	 * @since 2021-03-31
	 */
	@Test
	void testTimeSignatures() {
		final Builder b = new Builder();
		
		final TimeSignature ts1 = TimeSignature.valueOf(3, 4);
		final TimeSignature ts2 = TimeSignature.valueOf(6, 8);
		
		b.setTimeSignature(2, 3, ts1);
		b.setTimeSignature(8, 10, ts2);
		
		final XMLMetadata metadata = b.build();
		final var timeSignatures = metadata.getTimeSignatures();
		final var timeSigRanges = metadata.getTimeSignatureRanges();
		
		for (int i = 2; i <= 3; i++) {
			assertEquals(ts1, timeSignatures.get(i));
		}
		for (int i = 8; i <= 10; i++) {
			assertEquals(ts2, timeSignatures.get(i));
		}
		
		assertTrue(timeSigRanges.containsKey(IntRange.inclusive(2, 3)));
		assertEquals(ts1, timeSigRanges.get(IntRange.inclusive(2, 3)));
		
		assertTrue(timeSigRanges.containsKey(IntRange.inclusive(8, 10)));
		assertEquals(ts2, timeSigRanges.get(IntRange.inclusive(8, 10)));
		
		assertFalse(timeSigRanges.containsKey(IntRange.inclusive(1, 1)));
		assertFalse(timeSigRanges.containsKey(IntRange.inclusive(4, 7)));
	}
}
