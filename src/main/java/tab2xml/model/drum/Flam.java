package tab2xml.model.drum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.guitar.Note;
import tab2xml.model.guitar.StringItem;

public class Flam extends StringItem{

private Note note;
	
	public Flam(Note note) {
		this.note = note;
	}
	
	public Collection<? extends StringItem> getNotes() {
		// might be wrong flams are weird
		final List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(this.note));
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
		// this might be horribly wrong but basically a flam is just 2 of the same note in quick succession so i tried making the string the same note twice. also something with grace notes later
		StringBuilder sb = new StringBuilder();
		sb.append(this.note.toString());
		sb.append(" ");
		sb.append(this.note.toString());
		return sb.toString();
	}

}
