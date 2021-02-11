package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

import tab2xml.parser.Lexer.InvalidTokenException;

class NoteTest {

	/**
	 * @author Edward and Sayed this tests the constructor method in the Note class
	 */
	@Test
	void constructorTest() {
		Field[] fields = Note.class.getDeclaredFields();
		for (Field f : fields) {
			assertTrue(!Modifier.isPublic(f.getModifiers()), "SparseList contains a public field");
		}

		assertTrue(Note.class.getDeclaredConstructors().length == 1, "Number of constructors != 1");

	}

	/**
	 * @author Edward and Sayed this tests the getNoteType method in the Note class
	 */
	@Test
	void getNoteTypeTester() {
		Note note = new Note(NoteType.A);
		NoteType expected = NoteType.A;
		assertEquals(expected, note.getNoteType());
	}

	/**
	 * @author Edward and Sayed this tests the getName method for A sharp in the
	 *         Note class
	 * @throws InvalidTokenException 
	 */
	@Test
	void getNameTester() throws InvalidTokenException {
		Note note = new Note(NoteType.AS, "A#");
		String expected = "A#";
		assertEquals(expected, note.getData());
		assertEquals(expected, note.getNoteType().getValue());
	}

	/**
	 * @author Edward and Sayed this tests the getName method for note A in the Note
	 *         class
	 */
	@Test
	void getNameTester2() {
		Note note = new Note(NoteType.A);
		String expected = "A";
		assertEquals(expected, note.getNoteType().getValue());
	}

	/**
	 * @author Edward and Sayed this tests the getIndex method for note B in the
	 *         Note class
	 */
	@Test
	void getIndexTester() {
		Note note = new Note(NoteType.B);
		int expected = 2;
		assertEquals(expected, note.getIndex());
	}

	/**
	 * @author Edward and Sayed this tests the getIndex method for note A in the
	 *         Note class
	 */
	@Test
	void getIndexTester2() {
		Note note = new Note(NoteType.A);
		int expected = 0;
		assertEquals(expected, note.getIndex());
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

}
