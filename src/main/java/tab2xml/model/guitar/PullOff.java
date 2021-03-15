package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PullOff extends StringItem {
	private static final long serialVersionUID = -216451585018707396L;
	private Note start;
	private Note stop;
	
	public PullOff(Note start, Note stop) {
		this.start = start;
		this.stop = stop;
	}
	
	@Override
	public int getNoteCount() {
		return this.getNotes().size();
	}
	
	public Collection<? extends StringItem> getNotes() {
		final List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(this.start));
		notes.add((StringItem) StringItem.deepClone(this.stop));
		return notes;
	}

	@Override
	public double getPosition() {
		return start.getPosition();
	}
	
	public Note getStart() {
		return this.start;
	}
	
	public Note getStop() {
		return this.stop;
	}
	
	@Override
	public int getStringNum() {
		return this.start.getStringNum();
	}
	
	@Override
	public String toString() {
		if (this.start == null)
			return "";
		final StringBuilder sb = new StringBuilder();
		sb.append(this.start.getStep());
		sb.append(" ");
		sb.append(this.stop.getStep());
		return sb.toString();
	}
}
