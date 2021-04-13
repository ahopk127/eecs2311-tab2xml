package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.LineItem;

/**
 * A hammer-on/pull-off chain representation.
 * 
 * @author amir
 */
public class HammerPull extends LineItem {
	private static final long serialVersionUID = -1117048664217523691L;

	private GuitarNote start;
	private GuitarNote stop;
	private List<GuitarNote> middle;

	public HammerPull(GuitarNote start, List<GuitarNote> middle, GuitarNote stop) {
		this.start = start;
		this.stop = stop;
		this.middle = middle;
	}

	public GuitarNote getStart() {
		return start;
	}

	public GuitarNote getStop() {
		return stop;
	}

	public List<GuitarNote> getMiddle() {
		return middle;
	}

	public Collection<? extends LineItem> getNotes() {
		List<LineItem> notes = new ArrayList<>();
		notes.add((LineItem) LineItem.deepClone(start));
		for (LineItem note : middle)
			notes.add((LineItem) LineItem.deepClone(note));
		notes.add((LineItem) LineItem.deepClone(stop));
		return notes;
	}

	@Override
	public int length() {
		int length = 0;
		length += start.length() + stop.length();
		for (int i = 0; i < middle.size(); i++)
			length += middle.get(i).length();
		return length + getNoteCount() - 1;
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
		for (LineItem item : middle) {
			sb.append(((GuitarNote) item).getStep());
			sb.append(" ");
		}
		sb.append(stop.getStep());
		return sb.toString();
	}
}
