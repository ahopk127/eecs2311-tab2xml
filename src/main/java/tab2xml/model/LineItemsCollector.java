package tab2xml.model;

import java.util.List;

/**
 * A collector used in the extraction process to collect {@code LineItem}
 * objects on a given {@code Line}.
 * 
 * @author amir
 */
public class LineItemsCollector extends LineItem {
	private static final long serialVersionUID = -5896057496942602721L;

	/** The list of {@code LineItem} objects */
	private List<LineItem> lineItems;

	/**
	 * Construct a collector with an initially empty list of {@code LineItem}
	 * objects.
	 * 
	 * @param stringItems an initial list of {@code LineItem} objects
	 */
	public LineItemsCollector(List<LineItem> stringItems) {
		this.lineItems = stringItems;
	}

	/** @return a list of {@code LineItem} objects collected by this collector */
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * Add an item to this collector with the condition that the item.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>The item is <b>NOT NULL</b></li>
	 * </ul>
	 * </p>
	 * 
	 * @param item the item to add to this collector
	 * @return {@code true} if the item was added successfully
	 */
	public boolean add(LineItem item) {
		if (item == null)
			return false;
		lineItems.add(item);
		return true;
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
