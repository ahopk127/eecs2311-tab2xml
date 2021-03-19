package tab2xml.model.guitar;

import java.util.List;

import tab2xml.model.StaffItem;
import tab2xml.model.StringItem;

import java.util.ArrayList;
import java.util.Collection;

public class GuitarString extends StaffItem {
	private static final long serialVersionUID = -5274699295630375450L;
	private ArrayList<StringItem> stringItems;
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
		this.stringItems.add(new Tune(stringNum));
		this.stringNum = stringNum;
	}

	public String getTune() {
		return tune().getTune();
	}

	public String getOctave() {
		return tune().getOctave();
	}

	public Tune tune() {
		return (Tune) stringItems.get(0);
	}

	public void setTune(Tune tune) {
		stringItems.set(0, (Tune) StringItem.deepClone(tune));
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
			if (item.getClass() == Slide.class) {
				Slide sl = (Slide) item;
				notes.addAll(sl.getNotes());

			} else if (item.getClass() == PullOff.class) {
				PullOff po = (PullOff) item;
				notes.addAll(po.getNotes());

			} else if (item.getClass() == HammerOn.class) {
				HammerOn ho = (HammerOn) item;
				notes.addAll(ho.getNotes());

			} else if (item.getClass() == HammerPull.class) {
				HammerPull hp = (HammerPull) item;
				notes.addAll(hp.getNotes());

			} else if (item.getClass() == Harmonic.class) {
				Harmonic h = (Harmonic) item;
				notes.addAll(h.getNotes());

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

	@Override
	public String toString() {
		return String.format("(t:%s o:%s #:%d", getTune(), getOctave(), getNoteCount());
	}
}
