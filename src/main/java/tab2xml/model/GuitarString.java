package tab2xml.model;

import java.util.List;
import java.util.ArrayList;

public class GuitarString extends StaffItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274699295630375450L;
	private ArrayList<StringItem> stringItems;
	private String tune;
	private static int count = 0;
	private int numMeasures;
	private int stringNum;

	public GuitarString() {
		stringItems = new ArrayList<>();
		setCount(count + 1);
		this.stringNum = getCount();
	}

	public GuitarString(int stringNum) {
		stringItems = new ArrayList<>();
		this.stringNum = stringNum;
	}

	public void addItem(StringItem item) {
		stringItems.add(item);
	}

	public void addAllItems(List<StringItem> items) {
		for (StringItem item : items)
			this.stringItems.add(item);
	}

	public String getTune() {
		return tune;
	}

	public void setTune(String tune) {
		this.tune = tune;
	}

	public int getStringNum() {
		return stringNum;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	public int getNumMeasures() {
		return numMeasures;
	}

	public void setNumMeasures(int numMeasures) {
		this.numMeasures = numMeasures;
	}

	public int getNoteCount() {
		int count = 0;
		for (int i = 1; i < stringItems.size(); i++) {
			StringItem item = stringItems.get(i);
			count += item.getNoteCount();
		}
		return count;
	}

	public ArrayList<StringItem> getItems() {
		return stringItems;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		GuitarString.count = count;
	}

	public List<StringItem> getStringItems() {
		return stringItems;
	}

	public boolean add(StringItem item) {
		if (item != null)
			stringItems.add(item);
		return true;
	}

	public boolean addAll(List<StringItem> stringItems) {
		for (StringItem item : stringItems)
			add(item);
		return true;
	}
}
