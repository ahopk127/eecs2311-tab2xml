package tab2xml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Harmonic extends StringItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7485103510856786127L;
	private Note note;

	public Harmonic(Note note) {
		this.note = note;
	}

	public int getPosition() {
		return note.getPosition();
	}

	public Collection<? extends StringItem> getNotes() {
		List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(note));
		return notes;
	}

	@Override
	public int getStringNum() {
		return note.getStringNum();
	}

	@Override
	public int getNoteCount() {
		return getNotes().size();
	}

	@Override
	public String toString() {
		if (note == null)
			return "";
		return note.toString();
	}
}
