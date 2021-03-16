package tab2xml.model.drum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.guitar.Note;
import tab2xml.model.guitar.StringItem;

public class Accent extends StringItem{
private Note note;
	
	public Accent(Note note) {
		this.note = note;
	}
	
	public Collection<? extends StringItem> getNotes() {
		final List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(this.note));
		return notes;
	}
	
	public int getNoteCount() {
		return getNotes().size();
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
