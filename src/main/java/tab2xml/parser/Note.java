package tab2xml.parser;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

/**
 * An atomic note with its attributes.
 * 
 * @author amir
 */
public class Note {
	// to add: pitch, duration, and other attributes of a note.
	private final NoteType note;

	/**
	 * Construct a note object based on type.
	 * 
	 * @param type the type of note
	 */
	public Note(NoteType type) {
		this.note = type;
	}

	/**
	 * @return the type of this note
	 */
	public NoteType getNoteType() {
		return note;
	}

	/**
	 * @return the name of this note
	 */
	public String getName() {
		return note.getValue();
	}

	
	/**
	 * @return the index of the note within the NoteType enum
	 */
	public int getIndex() {
		return note.ordinal();
	}

	/**
	 * Construct a note from the ASCII tablature.
	 * 
	 * @param input string input containing: tuning+fret number
	 * @return a note based on the properties of the input
	 */
	public static Note toNote(String input) {
		Pattern p = Pattern.compile("^[A-G]\\d+$");

		if (!p.matcher(input).matches())
			throw new InputMismatchException();

		String n = input.substring(0, 1);
		int fret = Integer.parseInt(input.substring(1));

		Note note = new Note(Note.getNoteType(n + ""));
		int index = (note.getIndex() + fret) % 12;
		Note value = new Note(NoteType.values()[index]);

		return value;
	}

	/**
	 * Get the note type of a note as a string.
	 * 
	 * @param note
	 * @return the type of note of <i>note</i> argument otherwise null if <i>note</i> is not valid 
	 */
	public static NoteType getNoteType(String note) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(note))
				return type;
		}
		return null;
	}

	/**
	 * The 12 basic music notes.
	 * 
	 * @author amir
	 */
	public static enum NoteType {
		A("A"), AS("A#"), B("B"), C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"), FS("F#"), G("G"), GS("G#");

		private final String name;

		private NoteType(String name) {
			this.name = name;
		}

		public String getValue() {
			return name;
		}
	}
}
