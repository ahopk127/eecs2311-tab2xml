package tab2xml.model;

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
}