package tab2xml.model.guitar;

import java.util.List;
import java.util.ArrayList;

public class GuitarString extends StaffItem {
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
		int total = 0;
		for (int i = 1; i < stringItems.size(); i++) {
			StringItem item = stringItems.get(i);
			total += item.getNoteCount();
		}
		return total;
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

	public boolean add(StringItem item) {
		if (item != null)
			stringItems.add(item);
		return true;
	}

	public boolean addAll(List<StringItem> items) {
		for (StringItem item : items)
			add(item);
		return true;
	}
}
