package tab2xml.model.drum;

import tab2xml.model.Note;

/**
 * A drum note and its attributes.
 * 
 * @author amir
 */
public class DrumNote extends Note {
	private static final long serialVersionUID = -1373454153112345088L;

	/* Technical attributes */
	private DrumType drumType;
	private int id;
	private String stem;
	private String notehead;

	/* Functional attributes */
	private boolean isChord;
	private boolean isHarmonic;
	private boolean isGrace;
	private boolean isRepeatedStart;
	private boolean isRepeatedStop;
	private boolean isDoubleBar;

	/*************************/

	/**
	 * Construct a note object based on {@code DrumType} of note.
	 * 
	 * @param type the type of this note
	 */
	public DrumNote(DrumType drumType) {
		this.drumType = drumType;
		this.step = drumType.getDrumStep();
		this.octave = drumType.getDrumOctave();
		this.id = drumType.getID();
		this.stem = DrumType.drumSet.get(id).get(4);
		this.notehead = DrumType.drumSet.get(id).get(5);
		setNoteType();
	}

	public DrumNote(DrumLine drumLine) {
		this(drumLine.drumtype());
		this.setLineNum(drumLine.getLineNum());
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

	public DrumType getDrumType() {
		return drumType;
	}

	public void setDrumType(DrumType drumType) {
		this.drumType = drumType;
	}

	public int getId() {
		return id;
	}

	/**
	 * Set note type from a note already defined with a defined step
	 */
	@Override
	protected void setNoteType() {
		setNote(getNoteType(this.step));
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

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		setHasStem(true);
		this.stem = stem;
	}

	/**
	 * @return the notehead
	 */
	public String getNotehead() {
		return notehead;
	}

	/**
	 * @param notehead the notehead to set
	 */
	public void setNotehead(String notehead) {
		this.notehead = notehead;
	}
}
