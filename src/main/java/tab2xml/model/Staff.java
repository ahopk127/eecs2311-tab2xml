package tab2xml.model;

import java.util.ArrayList;
import java.util.List;

public class Staff extends StaffItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5418273130827075188L;
	private List<GuitarString> strings;
	private String upperBeat;
	private String lowerBeat;

	public Staff() {
		strings = new ArrayList<>();
		GuitarString.setCount(0);
	}

	public void addString(GuitarString s) {
		strings.add(s);
	}

	public List<GuitarString> getStrings() {
		return strings;
	}

	public int numberOfMeasures() {
		return strings.get(0).getNumMeasures();
	}

	public String getUpperBeat() {
		return upperBeat;
	}

	public void setUpperBeat(String upperBeat) {
		this.upperBeat = upperBeat;
	}

	public String getLowerBeat() {
		return lowerBeat;
	}

	public void setLowerBeat(String lowerBeat) {
		this.lowerBeat = lowerBeat;
	}

	public String stringCount() {
		return Integer.toString(strings.size());
	}

	public int size() {
		return strings.size();
	}

	public List<ArrayList<StringItem>> toList() {
		List<ArrayList<StringItem>> staff = new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			ArrayList<StringItem> stringItems = strings.get(i).getItems();
			staff.add(stringItems);
		}
		return staff;
	}
}
