package tab2xml.model;

import java.util.List;

public class StringItemsCollector extends StringItem {
	private List<StringItem> stringItems;
	
	public StringItemsCollector(List<StringItem> stringItems) {
		this.stringItems = stringItems;
	}

	public List<StringItem> getStringItems() {
		return stringItems;
	}
	
	public void add(StringItem item) {
		stringItems.add(item);
	}
}
