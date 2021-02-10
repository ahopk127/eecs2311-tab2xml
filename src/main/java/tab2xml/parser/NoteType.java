package tab2xml.parser;

/**
 * The 12 basic music notes.
 * 
 * @author amir
 */
public enum NoteType {
	A("A"), AS("A#"), B("B"), C("C"), CS("C#"), D("D"), DS("D#"), E("E"), F("F"), FS("F#"), G("G"), GS("G#");

	private final String name;

	private NoteType(String name) {
		this.name = name;
	}

	public String getValue() {
		return name;
	}
}