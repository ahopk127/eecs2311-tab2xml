package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.InputMismatchException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.*;

class NoteTest {

	/**
	 * @author Edward and Sayed this tests the getNoteType method in the Note class
	 */
	@Test
	void getNoteTypeTester() {
		Note note = new Note(NoteType.A);
		NoteType expected = NoteType.A;
		assertEquals(expected, note.getNoteType());
	}

	static Stream<Arguments> noteNames() {
		return Stream.of(Arguments.of(new Note(NoteType.A, "A"), "A", 0),
				Arguments.of(new Note(NoteType.AS, "A#"), "A#", 1), Arguments.of(new Note(NoteType.B, "B"), "B", 2),
				Arguments.of(new Note(NoteType.C, "C"), "C", 3), Arguments.of(new Note(NoteType.CS, "C#"), "C#", 4),
				Arguments.of(new Note(NoteType.D, "D"), "D", 5), Arguments.of(new Note(NoteType.DS, "D#"), "D#", 6),
				Arguments.of(new Note(NoteType.E, "E"), "E", 7), Arguments.of(new Note(NoteType.F, "F"), "F", 8),
				Arguments.of(new Note(NoteType.FS, "F#"), "F#", 9),
				Arguments.of(new Note(NoteType.G, "G"), "G", 10),
				Arguments.of(new Note(NoteType.GS, "G#"), "G#", 11));
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
	 * @author Edward and Sayed this tests the Tonote method in the Note class
	 * @throws InvalidTokenException
	 */
	@Test
	void testToNote() throws InvalidTokenException {
		NoteType expected = NoteType.A;
		assertEquals(expected, Note.toNote("A0").getNoteType());
		try {
			assertEquals(expected, Note.toNote("A0").getNoteType());
		} catch (InvalidTokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("InvalidTokenException occured.");
		}
	}

	/**
	 * @author Edward, Sayed and amir Tests that trying to convert an invalid string
	 *         to a note is invalid
	 * @throws InvalidTokenException
	 */
	@Test
	void testToNoteInvalid() throws InvalidTokenException {
		String invalidNote = "R0R";
		InputMismatchException thrown = assertThrows(InputMismatchException.class, () -> Note.toNote(invalidNote),
				"The Note is invalid.");
		assertTrue(thrown.getMessage().contains("The Note is invalid."));
	}

}
