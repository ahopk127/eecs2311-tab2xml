package tab2xml.model;

import java.util.List;

public class HammerPull extends StringItem {
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

	public int getPosition() {
		return start.getPosition();
	}
	
	@Override
	public String toString() {
		if (start == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(start.getStep());
		sb.append(" ");
		for (Note note : middle) {
			sb.append(note.getStep());
			sb.append(" ");
		}
		sb.append(stop.getStep());
		return sb.toString();
	}
}
