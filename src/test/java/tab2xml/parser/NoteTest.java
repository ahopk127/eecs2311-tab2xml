package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.LineItem;
import tab2xml.model.NoteType;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.guitar.GuitarString;

/**
 * Tests related to the {@code Note} object.
 *
 * @author Sayed, Edward
 */
class NoteTest {

	/**
	 * A list of arguments to test that NoteType functions as expected.
	 * 
	 * @return an argument in the format (GuitarNote, step, ordinal)
	 */
	static Stream<Arguments> noteNames() {
		return Stream.of(Arguments.of(new GuitarNote(NoteType.A), "A", 0),
				Arguments.of(new GuitarNote(NoteType.AS), "A#", 1), Arguments.of(new GuitarNote(NoteType.B), "B", 2),
				Arguments.of(new GuitarNote(NoteType.C), "C", 3), Arguments.of(new GuitarNote(NoteType.CS), "C#", 4),
				Arguments.of(new GuitarNote(NoteType.D), "D", 5), Arguments.of(new GuitarNote(NoteType.DS), "D#", 6),
				Arguments.of(new GuitarNote(NoteType.E), "E", 7), Arguments.of(new GuitarNote(NoteType.F), "F", 8),
				Arguments.of(new GuitarNote(NoteType.FS), "F#", 9), Arguments.of(new GuitarNote(NoteType.G), "G", 10),
				Arguments.of(new GuitarNote(NoteType.GS), "G#", 11));
	}

	/**
	 * Tests that notes have the correct name and index.
	 * 
	 * @param note          note to test
	 * @param expectedName  expected name of note
	 * @param expectedIndex expected index of note
	 * @since 2021-02-24
	 */
	@ParameterizedTest
	@MethodSource("noteNames")
	void noteTest(GuitarNote note, String expectedName, int expectedIndex) {
		assertEquals(expectedName, note.getStep());
		assertEquals(expectedName, note.getNoteType().getValue());
		assertEquals(expectedIndex, note.getIndex());
	}

	/**
	 * Tests that notes are correctly converted.
	 * 
	 * @param note         note to test
	 * @param expectedName expected name of note
	 * @since 2021-02-24
	 */
	static Stream<Arguments> toNoteTest() {
		return Stream.of(Arguments.of("E0", "E"), Arguments.of("F0", "F"), Arguments.of("B0", "B"),
				Arguments.of("B3", "D"), Arguments.of("G0", "G"), Arguments.of("G5", "C"), Arguments.of("D0", "D"),
				Arguments.of("D4", "F#"), Arguments.of("A0", "A"), Arguments.of("A13", "A#"));
	}

	/**
	 * @author Edward and Sayed this tests the Tonote method in the Note class
	 * @throws InvalidTokenException
	 */
	@ParameterizedTest
	@MethodSource("toNoteTest")
	void testToNote(String input, String expected) throws InvalidTokenException {
		assertEquals(expected, GuitarNote.toNote(input, 0).getStep());
	}

	/**
	 * @author Edward and Sayed this tests the invalid notes
	 * @throws InvalidTokenException
	 */
	@ParameterizedTest
	@ValueSource(strings = { "x0", "20", "#2", "E1E", "9E", "", "--", "hadfs", "2193", "   12", "E  12" })
	void testToNote(String input) throws InvalidTokenException {
		InputMismatchException thrown = assertThrows(InputMismatchException.class, () -> GuitarNote.toNote(input, 0),
				"The Note is invalid.");
		assertTrue(thrown.getMessage().contains("The Note is invalid."));
	}

	/**
	 * This method tests the natural ordering of notes within a staff. This is an
	 * important component because all features will build upon a note's natural
	 * ordering.
	 * 
	 * <p>
	 * Given a tab such as:
	 * 
	 * <pre>
	 * |-----------0-----|-0---------------|
	 * |---------0---0---|-0---------------|
	 * |-------1-------1-|-1---------------|
	 * |-----2-----------|-2---------------|
	 * |---2-------------|-2---------------|
	 * |-0---------------|-0---------------|
	 * </pre>
	 * 
	 * The expected natural ordering is as follows:
	 * <p>
	 * (0, 2, 2, 1, 0, 0, 0, 1, 0, 2, 2, 1, 0, 0)
	 * </p>
	 *
	 * Highest priority property to least:
	 * <ul>
	 * </ul>
	 * <li>The note's column</li>
	 * <li>The note's string (bottom up)</li>
	 * </p>
	 * 
	 * This test will ensure correctness as random variable testing is used. To make sure the
	 * natural ordering holds in every case, random insertions are made on a given
	 * {@code GuitarString}.
	 */
	@Test
	void stringItemCompareTo() {
		final int NUM_NOTES = 12;
		final Random RAND = new Random();

		LineItem[] stringItems = new LineItem[NUM_NOTES];
		GuitarString[] strings = new GuitarString[6];

		for (int i = 1; i <= strings.length; i++) {
			strings[i - 1] = new GuitarString(i);
		}

		int index;

		// same string: insertion at random orders
		for (int i = 1; i <= NUM_NOTES; i++) {
			GuitarNote note = new GuitarNote(strings[0], String.valueOf(i));
			note.setColumn(i);

			do {
				index = RAND.nextInt(NUM_NOTES);
			} while (stringItems[index] != null);
			stringItems[index] = note;
		}

		Arrays.sort(stringItems);
		String[] expected1 = { "F", "F#", "G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E" };
		assertEquals(Arrays.toString(expected1), Arrays.toString(stringItems));
		Arrays.fill(stringItems, null);

		// different strings: insertion at random orders
		for (int i = 1; i <= NUM_NOTES; i++) {
			GuitarNote note = new GuitarNote(strings[(i - 1) % strings.length], String.valueOf(i));
			note.setColumn(i);

			do {
				index = RAND.nextInt(NUM_NOTES);
			} while (stringItems[index] != null);
			stringItems[index] = note;
		}

		Arrays.sort(stringItems);
		String[] expected2 = { "F", "C#", "A#", "F#", "D", "A#", "B", "G", "E", "C", "G#", "E" };
		assertEquals(Arrays.toString(expected2), Arrays.toString(stringItems));
		Arrays.fill(stringItems, null);
	}
}
