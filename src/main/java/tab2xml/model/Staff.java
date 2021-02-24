package tab2xml.model;

import java.util.ArrayList;
import java.util.List;

public class Staff extends StaffItem{
	List<GuitarString> strings;
	
	public Staff() {
		strings = new ArrayList<>();
		GuitarString.setStringNum(0);
	}
	
	public void addString(GuitarString s) {
		strings.add(s);
	}

	public List<GuitarString> getStrings() {
		return strings;
	}
}
