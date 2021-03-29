package tab2xml.model.drum;

import tab2xml.model.LineItem;

public class Roll extends LineItem {
    private static final long serialVersionUID = -1343164172042736445L;
    private DrumNote note;

    @Override
    public int length() {
	return 0;
    }

    @Override
    public int getLineNum() {

	return note.getLineNum();
    }

    @Override
    public int getNoteCount() {

	return 4;
    }

    @Override
    public String toString() {
	if (this.note == null)
	    return "";
	StringBuilder sb = new StringBuilder();
	sb.append(this.note.toString());
	sb.append(" ");
	sb.append(this.note.toString());
	sb.append(" ");
	sb.append(this.note.toString());
	sb.append(" ");
	sb.append(this.note.toString());
	return sb.toString();
    }
}
