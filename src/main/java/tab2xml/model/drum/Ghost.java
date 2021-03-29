package tab2xml.model.drum;

import tab2xml.model.LineItem;

public class Ghost extends LineItem {
    private static final long serialVersionUID = 6023707654846459611L;
    private DrumNote note;

    public Ghost(DrumNote note) {
	this.note = note;
    }

    public int getNoteCount() {
	return 1;
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
	return this.note.toString();
    }

}
