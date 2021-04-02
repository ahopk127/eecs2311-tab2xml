package tab2xml.model.drum;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tab2xml.model.Line;
import tab2xml.model.LineItem;

/**
 * A representation of a drum line in drum tablature.
 * 
 * @author amir
 */
public class DrumLine extends Line<DrumNote> {
	private static final long serialVersionUID = 3885940852032675744L;
	private int lineNum;
	private boolean isCymbal;
	private boolean isDrum;

	/**
	 * Construct an empty drum line(with line number grouped by staffs).
	 */
	public DrumLine() {
		super();
		this.lineNum = getLine();
	}

	/**
	 * Construct an empty drum line with a specified line number.
	 * 
	 * @param lineNum the line number of this drum line.
	 */
	public DrumLine(int lineNum) {
		super(lineNum);
		this.lineNum = lineNum;
	}

	/**
	 * @return the octave of this drum line's drum part.
	 */
	public String getOctave() {
		return null;
	}

	/**
	 * @return the step of this drum line's drum part.
	 */
	public String getDrumStep() {
		return drumtype().getDrumType();
	}

	/**
	 * @return the {@code DrumType} of this drum line.
	 */
	public DrumType drumtype() {
		return (DrumType) lineItems.get(0);
	}

	/**
	 * Set the {@code DrumType} of this drum line with a specified {@code DrumType}
	 * object.
	 * 
	 * @param drumType the {@code DrumType} object to set the {@code DrumType} of
	 *                 this drum line.
	 */
	public void setDrumType(DrumType drumType) {
		lineItems.set(0, (DrumType) LineItem.deepClone(drumType));
	}

	/**
	 * @return the line number of this drum line.
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * Set the line number of this drum line.
	 * 
	 * @param lineNum the value to set the drum line number.
	 */
	public void setLineNum(int stringNum) {
		this.lineNum = stringNum;
	}

	/**
	 * @return a list of notes in this drum line.
	 */
	@Override
	public Collection<? extends LineItem> getNotes() {
		List<LineItem> notes = new ArrayList<>();
		for (@SuppressWarnings("unused")
		LineItem item : lineItems) {
			// TODO: get the list of notes in the drum line.
		}
		return notes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s%s-%d", getDrumStep(), getOctave(), getNoteCount());
	}

	@Override
	public Iterator<DrumNote> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the isCymbal
	 */
	public boolean isCymbal() {
		return isCymbal;
	}

	/**
	 * @param isCymbal the isCymbal to set
	 */
	public void setCymbal(boolean isCymbal) {
		this.isCymbal = isCymbal;
	}

	/**
	 * @return the isDrum
	 */
	public boolean isDrum() {
		return isDrum;
	}

	/**
	 * @param isDrum the isDrum to set
	 */
	public void setDrum(boolean isDrum) {
		this.isDrum = isDrum;
	}

}