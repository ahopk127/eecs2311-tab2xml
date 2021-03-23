package tab2xml.model.drum;

import tab2xml.model.Note;
import tab2xml.model.NoteType;

/**
 * A drum note and its attributes.
 * 
 * @author amir
 */
public class DrumNote extends Note {
	private static final long serialVersionUID = -1373454153112345088L;

	/*Technical attributes*/

	/* Functional attributes*/
	private boolean isChord;
	private boolean isHarmonic;
	private boolean isGrace;
	private boolean isRepeatedStart;
	private boolean isRepeatedStop;
	private boolean isDoubleBar;

	/*************************/

	/**
	 * Construct a note object based specified step and octave.
	 *
	 * @param drumType the drumType of the line this note is on.
	 */
	public DrumNote(String step, String octave) {
		this.step = step;
		this.octave = octave;
		this.note = getNoteType(octave);
	}

	/**
	 * Construct a note object based on type of note.
	 * 
	 * @param type the type of this note
	 */
	public DrumNote(NoteType type) {
		super(type);
	}

	public DrumNote(DrumLine drumLine) {
		this.setLine(drumLine.getLineNum());
		setNoteType();
	}

	/**
	 * Return the string attribute to the specified value.
	 * 
	 * @return the string attribute of this note
	 */
	public String getString() {
		return getLine();
	}

	public boolean isChord() {
		return isChord;
	}

	public void setChord(boolean isChord) {
		this.isChord = isChord;
	}

	public boolean isHarmonic() {
		return isHarmonic;
	}

	public void setHarmonic(boolean isHarmonic) {
		this.isHarmonic = isHarmonic;
	}

	public boolean isGrace() {
		return isGrace;
	}

	public void setGrace(boolean isGrace) {
		this.isGrace = isGrace;
	}

	public boolean isRepeatedStart() {
		return isRepeatedStart;
	}

	public void setRepeatedStart(boolean isRepeatedStart) {
		this.isRepeatedStart = isRepeatedStart;
	}

	public boolean isRepeatedStop() {
		return isRepeatedStop;
	}

	public void setRepeatedStop(boolean isRepeatedStop) {
		this.isRepeatedStop = isRepeatedStop;
	}

	public boolean isDoubleBar() {
		return isDoubleBar;
	}

	public void setDoubleBar(boolean isDoubleBar) {
		this.isDoubleBar = isDoubleBar;
	}

	/**
	 * Set note type from a note already defined with a defined step
	 */
	@Override
	protected void setNoteType() {

	}

	/**
	 * Return the string representation of this note.
	 * 
	 * @return the string of this note's value
	 */
	@Override
	public String toString() {
		return step;
	}

}
