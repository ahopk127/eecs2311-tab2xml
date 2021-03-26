package tab2xml.model.drum;

import tab2xml.model.LineItem;

public class Accent extends LineItem {
	private static final long serialVersionUID = -6425568262366908657L;
	private DrumNote note;

	public Accent(DrumNote note) {
		this.note = note;
	}

	public int getNoteCount() {
		return 1;
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
