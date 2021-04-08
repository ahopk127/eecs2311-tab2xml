package tab2xml.model;

import tab2xml.model.guitar.Tune;

/**
 * An {@code Enum} of the 12 basic music notes.
 * 
 * @author amir
 */
public enum NoteType {
	A("A"), AS("A#"), B("B"), C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"), FS("F#"), G("G"), GS("G#");

	private final String name;

	private NoteType(String name) {
		this.name = name;
	}

	/**
	 * Return the string value of this {@code NoteType}.
	 * 
	 * @return the string representation of this {@code NoteType}
	 */
	public String getValue() {
		return name;
	}

	public static int findNumNotesUntilC(NoteType note) {
		int c = note.ordinal();
		int count = 0;
		while (NoteType.values()[c] != NoteType.C) {
			c++;
			if (c > NoteType.values().length - 1)
				c = 0;
			count++;
		}
		return count;
	}

	public static String getOctave(Tune tune, int fret) {
		// The bounds:
		// lower = section * length + factor - length;
		// (lower < 0)? lower = 0: lower;
		// upper = section * length + factor - 1;
		int factor = findNumNotesUntilC(Note.getNoteType(tune.getTune()));
		int length = NoteType.values().length;
		int section = (fret + length - factor) / length;
		return String.valueOf(Integer.parseInt(tune.getOctave()) + section);
	}
}