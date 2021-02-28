package tab2xml.model;

import java.util.List;
import java.util.ArrayList;

public class GuitarString extends StaffItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274699295630375450L;
	private ArrayList<StringItem> items;
	private String tune;
	private static int count = 0;
	private int numMeasures;
	private int stringNum;

	public GuitarString() {
		items = new ArrayList<>();
		count = getCount() + 1;
		this.stringNum = getCount();
	}

	public void addItem(StringItem item) {
		items.add(item);
	}

	public void addAllItems(List<StringItem> items) {
		for (StringItem item : items)
			this.items.add(item);
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
		return (int) items.stream().filter(i -> i.getClass() == Note.class).count();
	}

	public ArrayList<StringItem> getItems() {
		return items;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		GuitarString.count = count;
	}
}
