package tab2xml.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tab2xml.parser.Note.NoteType;

class NoteTest {

	//@Test
	//void testToNote() {
	//	Note note= new Note(NoteType.A);
	//	String expected = "A";
	//	assertEquals(expected, Note.toNote("A"));
	//}
	/**
	 * @author Edward and Sayed
	 * this tests the getNoteType method in the Note class
	 */
	@Test
	void getNoteTypeTester() {
		Note note= new Note(NoteType.A);
		NoteType expected = NoteType.A;
		
		assertEquals(expected, note.getNoteType());
	}
	/**
	 * @author Edward and Sayed
	 * this tests the getIndex method in the Note class
	 */
	@Test
	void getIndexTester() {
	Note note= new Note(NoteType.B);
	int expected = 2;
		
		assertEquals(expected, note.getIndex());
	}
	/**
	 * @author Edward and Sayed
	 * this tests the getName method in the Note class
	 */
	@Test
	void getNameTester() {
		Note note= new Note(NoteType.AS);
		String expected = "A#";
		
		assertEquals(expected, note.getName());
	}
}
