package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.LineItem;

public class HammerOn extends LineItem {
	private static final long serialVersionUID = 1578840013025953896L;
	private GuitarNote start;
	private GuitarNote stop;

	public HammerOn(GuitarNote start, GuitarNote stop) {
		this.start = start;
		this.stop = stop;
	}

	public GuitarNote getStart() {
		return start;
	}

	public GuitarNote getStop() {
		return stop;
	}

	public Collection<? extends LineItem> getNotes() {
		List<LineItem> notes = new ArrayList<>();
		notes.add((LineItem) LineItem.deepClone(start));
		notes.add((LineItem) LineItem.deepClone(stop));
		return notes;
	}

	@Override
	public double getPosition() {
		return start.getPosition();
	}

	@Override
	public int getLineNum() {
		return start.getLineNum();
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
		sb.append(stop.getStep());
		return sb.toString();
	}
}
