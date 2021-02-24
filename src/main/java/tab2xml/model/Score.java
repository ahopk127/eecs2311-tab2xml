package tab2xml.model;

import java.util.ArrayList;
import java.util.List;

public class Score {
	public List<Staff> staffs;

	public Score() {
		this.staffs = new ArrayList<>();
	}

	public void addStaff(Staff s) {
		this.staffs.add(s);
	}

	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			List<GuitarString> strings = staff.getStrings();
			count += strings.get(0).getNumMeasures();
		}
		return count;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < staffs.size(); i++) {
			Staff staff = staffs.get(i);
			List<GuitarString> strings = staff.getStrings();
			for (int j = 0; j < strings.size(); j++) {
				List<StringItem> stringItems = strings.get(j).getItems();
				stringItems.stream().forEach(item -> sb.append(item.toString() + " "));
				sb.append("\n");
			}
			sb.append("\n\n");
		}
		return sb.toString();
	}
}
