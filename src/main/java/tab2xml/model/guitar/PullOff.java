package tab2xml.model.guitar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tab2xml.model.LineItem;

/**
 * A pull-off representation.
 * 
 * @author amir
 */
public class PullOff extends LineItem {
	private static final long serialVersionUID = -216451585018707396L;

	private GuitarNote start;
	private GuitarNote stop;

	public PullOff(GuitarNote start, GuitarNote stop) {
		this.start = start;
		this.stop = stop;
	}

	public GuitarNote getStart() {
		return this.start;
	}

	public GuitarNote getStop() {
		return this.stop;
	}

	public Collection<? extends LineItem> getNotes() {
		final List<LineItem> notes = new ArrayList<>();
		notes.add((LineItem) LineItem.deepClone(this.start));
		notes.add((LineItem) LineItem.deepClone(this.stop));
		return notes;
	}

	@Override
	public int length() {
		return start.length() + stop.length() + 1;
	}

	@Override
	public int getLineNum() {
		return this.start.getLineNum();
	}

	@Override
	public int getNoteCount() {
		return this.getNotes().size();
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
