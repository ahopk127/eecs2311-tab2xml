package tab2xml.model.drum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.LineItem;

public class Choke extends LineItem {
	private DrumNote note;

	public Choke(DrumNote note) {
		this.note = note;
	}

	public Collection<? extends LineItem> getNotes() {
		final List<LineItem> notes = new ArrayList<>();
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
		return this.note.toString();
	}
}
