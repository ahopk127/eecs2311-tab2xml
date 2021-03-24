package tab2xml.model;

import java.util.List;

public class LineItemsCollector extends LineItem {
	private static final long serialVersionUID = -5896057496942602721L;
	private List<LineItem> lineItems;

	public LineItemsCollector(List<LineItem> stringItems) {
		this.lineItems = stringItems;
	}

	public List<LineItem> getStringItems() {
		return lineItems;
	}

	public void add(LineItem item) {
		if (item != null)
			lineItems.add(item);
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
}
