package tab2xml.model;

public class Harmonic extends StringItem {
	private Note note;

	public Harmonic(Note note) {
		this.note = note;
	}
	
	public int getPosition() {
		return note.getPosition();
	}
	
	@Override
	public String toString() {
		if (note == null) return "";
		return note.toString();
	}
}
