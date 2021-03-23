package tab2xml.model;

import java.util.List;

public class StringItemsCollector extends LineItem {
	private static final long serialVersionUID = -5896057496942602721L;
	private List<LineItem> stringItems;

	public StringItemsCollector(List<LineItem> stringItems) {
		this.stringItems = stringItems;
	}

	public List<LineItem> getStringItems() {
		return stringItems;
	}

	public void add(LineItem item) {
		if (item != null)
			stringItems.add(item);
	}

	@Override
	public double getPosition() {
		return -1;
	}

	@Override
	public int getLineNum() {
		return 0;
	}
	
	@Override
	public int getNoteCount() {
		return 0;
	}
}
