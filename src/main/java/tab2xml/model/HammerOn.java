package tab2xml.model;

public class HammerOn extends StringItem {
	private Note start;
	private Note stop;

	public HammerOn(Note start, Note stop) {
		this.start = start;
		this.stop = stop;
	}

	public Note getStart() {
		return start;
	}

	public Note getStop() {
		return stop;
	}

	public int getPosition() {
		return start.getPosition();
	}

	@Override
	public String toString() {
		if (start == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(start.getStep());
		sb.append(" ");
		sb.append(stop.getStep());
		return sb.toString();
	}
}
