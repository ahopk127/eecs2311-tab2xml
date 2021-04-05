package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.LineItem;

public class Harmonic extends LineItem {
	private static final long serialVersionUID = 7485103510856786127L;
	private GuitarNote note;

	public Harmonic(GuitarNote note) {
		this.note = note;
	}

	public Collection<? extends LineItem> getNotes() {
		final List<LineItem> notes = new ArrayList<>();
		notes.add((LineItem) LineItem.deepClone(this.note));
		return notes;
	}

	@Override
	public int length() {
		return note.length() + 2;
	}

	@Override
	public int getLineNum() {
		return note.getLineNum();
	}

	@Override
	public int getNoteCount() {
		return this.getNotes().size();
	}

	@Override
	public String toString() {
		if (this.note == null)
			return "";
		return this.note.toString();
	}
}
