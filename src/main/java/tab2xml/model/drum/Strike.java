package tab2xml.model.drum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.drum.Note;
import tab2xml.model.StringItem;

public class Strike extends StringItem{
	private Note note;
	
	public Strike(Note note) {
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
