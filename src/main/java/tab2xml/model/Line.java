package tab2xml.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An abstract line within a staff. This abstract {@code Line} will have a basic
 * function of containing {@code LineItem} objects to the respective format of
 * tablature.
 * 
 * @author amir
 */
public abstract class Line extends ScoreItem {
	private static final long serialVersionUID = 4752002711545090298L;

	/** The items on this line */
	protected ArrayList<LineItem> lineItems;
	/** The line number */
	protected static int line = 0;
	/** The number of measures in this line */
	protected int numMeasures;

	/**
	 * Construct an empty line of a staff.
	 */
	public Line() {
		lineItems = new ArrayList<>();
		setLine(line + 1);
	}

	/**
	 * Construct an empty line with a specified line position.
	 * 
	 * @param line the line number of this line
	 */
	public Line(int line) {
		this.lineItems = new ArrayList<>();
		Line.line = line;
	}

	/**
	 * @return the total number of measures in this line
	 */
	public int getNumMeasures() {
		return numMeasures;
	}

	/**
	 * Set the total measures in this line.
	 * 
	 * @param numMeasures the value to set the total measures in this line
	 */
	public void setNumMeasures(int numMeasures) {
		this.numMeasures = numMeasures;
	}

	/**
	 * @return a list of notes within this line
	 */
	public abstract Collection<? extends LineItem> getNotes();

	/**
	 * @return the line position of this line
	 */
	public static int getLine() {
		return line;
	}

	/**
	 * A static method to keep track of the construction of groups of lines.
	 * 
	 * @param line the value to set the line count
	 */
	public static void setLine(int line) {
		Line.line = line;
	}

	/**
	 * @return the list of items in this line
	 */
	public ArrayList<LineItem> getItems() {
		return lineItems;
	}

	/**
	 * Add a single item to this line.
	 * 
	 * @param item the item to add to this line
	 * @return {@code true} if the item has been added
	 */
	public boolean add(LineItem item) {
		if (item == null)
			return false;
		lineItems.add(item);
		return true;
	}

	/**
	 * Add a list of items to this line.
	 * 
	 * @param items a list of line items to add
	 * @return {@code true} if all the items have been added
	 */
	public boolean addAll(List<LineItem> items) {
		if (items == null)
			return false;
		for (LineItem item : items)
			if (!add(item))
				return false;
		return true;
	}

	/** @return the width of this line */
	public int width() {
		int count = 0;
		for (int i = 0; i < lineItems.size(); i++)
			count += lineItems.get(i).length();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public int getNoteCount() {
		int total = 0;
		for (int i = 1; i < lineItems.size(); i++) {
			LineItem item = lineItems.get(i);
			total += item.getNoteCount();
		}
		return total;
	}

	@Override
	public String toString() {
		return String.format("line-%d");
	}
}
