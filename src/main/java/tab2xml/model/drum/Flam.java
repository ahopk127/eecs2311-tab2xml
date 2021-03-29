package tab2xml.model.drum;

import tab2xml.model.LineItem;

public class Flam extends LineItem {
	private static final long serialVersionUID = 1650809271375302772L;
	private DrumNote note;

	public Flam(DrumNote note) {
		this.note = note;
	}

	public int getNoteCount() {
		return 2;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public int getLineNum() {

		return note.getLineNum();
	}

	@Override
	public String toString() {
		if (this.note == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(this.note.toString());
		sb.append(" ");
		sb.append(this.note.toString());
		return sb.toString();
	}

}
