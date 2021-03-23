package tab2xml.model.drum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.guitar.GuitarNote;
import tab2xml.model.LineItem;

public class Flam extends LineItem {

	private GuitarNote note;

	public Flam(GuitarNote note) {
		this.note = note;
	}

	public Collection<? extends LineItem> getNotes() {
		// might be wrong flams are weird
		final List<LineItem> notes = new ArrayList<>();
		notes.add((LineItem) LineItem.deepClone(this.note));
		notes.add((LineItem) LineItem.deepClone(this.note));
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
	public int getLineNum() {

		return note.getLineNum();
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
