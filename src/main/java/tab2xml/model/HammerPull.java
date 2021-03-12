package tab2xml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HammerPull extends StringItem {
	private static final long serialVersionUID = -1117048664217523691L;
	private Note start;
	private Note stop;
	private List<Note> middle;

	public HammerPull(Note start, List<Note> middle, Note stop) {
		this.start = start;
		this.stop = stop;
		this.middle = middle;
	}

	public Note getStart() {
		return start;
	}

	public Note getStop() {
		return stop;
	}

	public List<Note> getMiddle() {
		return middle;
	}

	public Collection<? extends StringItem> getNotes() {
		List<StringItem> notes = new ArrayList<>();
		notes.add((StringItem) StringItem.deepClone(start));
		for (StringItem note : middle)
			notes.add((StringItem) StringItem.deepClone(note));
		notes.add((StringItem) StringItem.deepClone(stop));
		return notes;
	}

	@Override
	public double getPosition() {
		return start.getPosition();
	}

	@Override
	public int getStringNum() {
		return start.getStringNum();
	}

	@Override
	public int getNoteCount() {
		return getNotes().size();
	}

	@Override
	public String toString() {
		if (start == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(start.getStep());
		sb.append(" ");
		for (StringItem item : middle) {
			sb.append(((Note) item).getStep());
			sb.append(" ");
		}
		sb.append(stop.getStep());
		return sb.toString();
	}
}
