package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Harmonic extends StringItem {
	private static final long serialVersionUID = 7485103510856786127L;
	private Note note;
	
	public Harmonic(Note note) {
		this.note = note;
	}
	
	@Override
	public int getNoteCount() {
		return this.getNotes().size();
	}
	
	public Collection<? extends StringItem> getNotes() {
		final List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(this.note));
		return notes;
	}
	
	@Override
	public double getPosition() {
		return note.getPosition();
	}

	@Override
	public int getStringNum() {
		return note.getStringNum();
	}
	
	@Override
	public String toString() {
		if (this.note == null)
			return "";
		return this.note.toString();
	}
}
