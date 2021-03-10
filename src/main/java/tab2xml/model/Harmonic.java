package tab2xml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Harmonic extends StringItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7485103510856786127L;
	private final Note note;
	
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
	public int getPosition() {
		return this.note.getPosition();
	}
	
	@Override
	public int getStringNum() {
		return this.note.getStringNum();
	}
	
	@Override
	public String toString() {
		if (this.note == null)
			return "";
		return this.note.toString();
	}
}
