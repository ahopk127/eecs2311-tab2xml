package tab2xml.model.drum;

import java.util.List;


import tab2xml.model.drum.Note;
import tab2xml.model.guitar.Bar;
import tab2xml.model.StaffItem;
import tab2xml.model.StringItem;

import java.util.ArrayList;
import java.util.Collection;

public class Line extends StaffItem {
	private ArrayList<StringItem> stringItems;
	private String tune;
	private static int count = 0;
	private int numMeasures;
	private int stringNum;

	public Line() {
		stringItems = new ArrayList<>();
		setCount(count + 1);
		this.stringNum = getCount();
	}

	public Line(int stringNum) {
		stringItems = new ArrayList<>();
		this.stringNum = stringNum;
	}

	public String getTune() {
		return tune;
	}

	public void setDrumType(String tune) {
		this.tune = tune;
	}

	public DrumType getDrumTypeObj() {
		return (DrumType) stringItems.get(0);
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

	public Collection<? extends StringItem> getNotes() {
		List<StringItem> notes = new ArrayList<>();

		for (StringItem item : stringItems) {
			if (item.getClass() == Strike.class) {
				Strike st = (Strike) item;
				notes.addAll(st.getNotes());

			} else if (item.getClass() == Accent.class) {
				Accent ac = (Accent) item;
				notes.addAll(ac.getNotes());

			} else if (item.getClass() == Choke.class) {
				Choke ch = (Choke) item;
				notes.addAll(ch.getNotes());

			} else if (item.getClass() == Flam.class) {
				Flam fl = (Flam) item;
				notes.addAll(fl.getNotes());

			} else if (item.getClass() == Ghost.class) {
				Ghost gh = (Ghost) item;
				notes.addAll(gh.getNotes());
			//TODO rolls i dont really understand them 	
//			} else if (item.getClass() == Roll.class) {
//				Roll ro = (Roll) item;
//				notes.addAll(ro.getNotes());
			} else if (item.getClass() == Note.class) {
				Note note = (Note) item;
				notes.add((StringItem) StringItem.deepClone(note));

			} else if (item.getClass() == Bar.class) {
				Bar bar = (Bar) item;
				notes.add((StringItem) StringItem.deepClone(bar));
			}
		}
		return notes;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Line.count = count;
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