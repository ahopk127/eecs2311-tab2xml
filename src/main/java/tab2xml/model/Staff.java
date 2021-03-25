package tab2xml.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

import tab2xml.model.guitar.GuitarStaff;
import tab2xml.model.guitar.GuitarString;

/**
 * An iterable abstract staff object.
 * 
 * @author amir
 */
public abstract class Staff extends StaffItem implements Iterable<LineItem> {
	private static final long serialVersionUID = -1721480917571243686L;
	protected List<GuitarString> strings;
	protected String beats;
	protected String beatType;
	protected static int accumulateMeasure = 0;

	/**
	 * Construct an empty staff.
	 */
	public Staff() {
		strings = new ArrayList<>();
		GuitarString.setLine(0);
	}

	/**
	 * Add an abstract string/line item to this staff.
	 * 
	 * @param s the string/line object to add to this staff.
	 */
	public void addString(GuitarString s) {
		strings.add(s);
	}

	/**
	 * @return a list of abstract string/line objectsj.
	 */
	public List<GuitarString> getStrings() {
		return strings;
	}

	/**
	 * @return return the beats attribute of a staff.
	 */
	public String getBeats() {
		return beats;
	}

	/**
	 * @param beatType the value to set the beats of this staff.
	 */
	public void setBeats(String beatType) {
		this.beatType = beatType;
	}

	/**
	 * @return the beat type attribute of this staff.
	 */
	public String getBeatType() {
		return beatType;
	}

	/**
	 * @param beatType the value to set the beat type of this staff.
	 */
	public void setBeatType(String beatType) {
		this.beatType = beatType;
	}

	/**
	 * @return the number of measures in this staff
	 */
	public int numberOfMeasures() {
		return strings.get(0).getNumMeasures();
	}

	/**
	 * @return the number of strings in the staff as a string
	 */
	public String stringCount() {
		return Integer.toString(strings.size());
	}

	/**
	 * @return the number of strings in the staff
	 */
	public int size() {
		return strings.size();
	}

	/**
	 * @return the total number of notes in the staff
	 */
	public int getNoteCount() {
		int count = 0;
		for (GuitarString string : strings) {
			count += string.getNoteCount();
		}
		return count;
	}

	/**
	 * Extract notes from a staff at a specified index.
	 * 
	 * @return a list of notes representing a staff at a specified index
	 */
	public List<LinkedList<LineItem>> toList() {
		List<LinkedList<LineItem>> res = new ArrayList<>();

		for (GuitarString s : this.getStrings()) {
			LinkedList<LineItem> notes = new LinkedList<>();
			notes.addAll(s.getNotes());
			res.add(notes);
		}

		return res;
	}
	
	public static void setAccumulateMeasure(int accumulateMeasure) {
		GuitarStaff.accumulateMeasure = accumulateMeasure;
	}
	
	@Override
	public abstract Iterator<LineItem> iterator();

}
