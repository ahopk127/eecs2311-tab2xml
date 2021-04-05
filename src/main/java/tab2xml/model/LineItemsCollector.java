package tab2xml.model;

import java.util.List;

public class LineItemsCollector extends LineItem {
	private static final long serialVersionUID = -5896057496942602721L;
	private List<LineItem> lineItems;

	public LineItemsCollector(List<LineItem> stringItems) {
		this.lineItems = stringItems;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void add(LineItem item) {
		if (item != null)
			lineItems.add(item);
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public double getColumn() {
		return -1;
	}

	@Override
	public double getPosition() {
		return -1;
	}

	@Override
	public int getLineNum() {
		return -1;
	}

	@Override
	public int getNoteCount() {
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (lineItems == null || lineItems.size() == 0)
			return "[]";
		sb.append("[");
		for (LineItem item : lineItems) {
			sb.append(item.toString() + ", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
