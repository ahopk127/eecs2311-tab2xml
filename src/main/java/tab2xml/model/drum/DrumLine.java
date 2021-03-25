package tab2xml.model.drum;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import tab2xml.model.Line;
import tab2xml.model.LineItem;
import tab2xml.model.guitar.Tune;

/**
 * A representation of a drum line in drum tablature.
 * 
 * @author amir
 */
public class DrumLine extends Line {
	private static final long serialVersionUID = 3885940852032675744L;
	private int lineNum;

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
		return drumtype().getOctave();
	}

	/**
	 * @return the step of this drum line's drum part.
	 */
	public String getDrumStep() {
		return drumtype().getTune();
	}

	/**
	 * @return the {@code DrumType} of this drum line.
	 */
	public Tune drumtype() {
		return (Tune) lineItems.get(0);
	}

	/**
	 * Set the {@code DrumType} of this drum line with a specified {@code DrumType}
	 * object.
	 * 
	 * @param drumType the {@code DrumType} object to set the {@code DrumType} of
	 *                 this drum line.
	 */
	public void setDrumType(Tune drumType) {
		lineItems.set(0, (Tune) LineItem.deepClone(drumType));
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
			//TODO: get the list of notes in the drum line.
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

}