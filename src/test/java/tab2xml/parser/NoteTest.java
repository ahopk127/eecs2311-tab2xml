package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.InputMismatchException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.*;

class NoteTest {

	static Stream<Arguments> noteNames() {
		return Stream.of(Arguments.of(new Note(NoteType.A), "A", 0), Arguments.of(new Note(NoteType.AS), "A#", 1),
				Arguments.of(new Note(NoteType.B), "B", 2), Arguments.of(new Note(NoteType.C), "C", 3),
				Arguments.of(new Note(NoteType.CS), "C#", 4), Arguments.of(new Note(NoteType.D), "D", 5),
				Arguments.of(new Note(NoteType.DS), "D#", 6), Arguments.of(new Note(NoteType.E), "E", 7),
				Arguments.of(new Note(NoteType.F), "F", 8), Arguments.of(new Note(NoteType.FS), "F#", 9),
				Arguments.of(new Note(NoteType.G), "G", 10), Arguments.of(new Note(NoteType.GS), "G#", 11));
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
	void noteTest(Note note, String expectedName, int expectedIndex) {
		assertEquals(expectedName, note.getStep());
		assertEquals(expectedName, note.getNoteType().getValue());
		assertEquals(expectedIndex, note.getIndex());
	}

	/**
	 * Tests that notes are correctly converted.
	 * 
	 * @param note          note to test
	 * @param expectedName  expected name of note
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
		assertEquals(expected, Note.toNote(input, 0).getStep());
	}

	/**
	 * @author Edward and Sayed this tests the invalid notes
	 * @throws InvalidTokenException
	 */
	@ParameterizedTest
	@ValueSource(strings = {"x0", "20", "#2", "E1E", "9E"})
	void testToNote(String input) throws InvalidTokenException {
		InputMismatchException thrown = assertThrows(InputMismatchException.class, () -> Note.toNote(input, 0),
				"The Note is invalid.");
		assertTrue(thrown.getMessage().contains("The Note is invalid."));
	}
}
