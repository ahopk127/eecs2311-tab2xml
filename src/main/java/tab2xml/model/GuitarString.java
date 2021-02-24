package tab2xml.model;

import java.util.List;
import java.util.ArrayList;

public class GuitarString extends StaffItem {
	private List<StringItem> items;
	private String tune;
	private static int stringNum = 0;
	private int numMeasures;

	public GuitarString() {
		items = new ArrayList<>();
		stringNum++;
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

	public static int getStringNum() {
		return stringNum;
	}

	public static void setStringNum(int stringNum) {
		GuitarString.stringNum = stringNum;
	}

	public int getNumMeasures() {
		return numMeasures;
	}

	public void setNumMeasures(int numMeasures) {
		this.numMeasures = numMeasures;
	}

	public List<StringItem> getItems() {
		return items;
	}
}
