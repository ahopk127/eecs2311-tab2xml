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

	/**
	 * Construct a {@code DrumNote} object based on {@code DrumType}
	 *
	 * @param drumType the {@code DrumType} of the note
	 */
	public DrumNote(DrumType drumType) {
		this.drumType = drumType;
		this.step = drumType.getDrumStep();
		this.octave = drumType.getDrumOctave();
		this.id = drumType.getID();
		this.stem = DrumType.drumSet.get(id).get(4);
		this.notehead = DrumType.drumSet.get(id).get(5);
		this.note = Note.getNoteType(this.step);
	}

	/**
	 * Construct a {@code DrumNote} object based on a {@code DrumLine}.
	 *
	 * @param drumLine the {@code DrumNote}
	 */
	public DrumNote(DrumLine drumLine) {
		this(drumLine.drumtype());
		this.setLineNum(drumLine.getLineNum());
	}

	/** @return true if this note is apart of a chord */
	public boolean isChord() {
		return isChord;
	}

	/**
	 * Set whether this note is apart of a chord.
	 * 
	 * @param isChord the value to set
	 */
	public void setChord(boolean isChord) {
		this.isChord = isChord;
	}

	/** @return true if this note is a natural harmonic */
	public boolean isHarmonic() {
		return isHarmonic;
	}

	/**
	 * Set whether this note is a natural harmonic.
	 * 
	 * @param isHarmonic the value to set
	 */
	public void setHarmonic(boolean isHarmonic) {
		this.isHarmonic = isHarmonic;
	}

	/** @return true if this note is a grace note */
	public boolean isGrace() {
		return isGrace;
	}

	/**
	 * Set whether this note is a grace note.
	 * 
	 * @param isGrace the value to set
	 */
	public void setGrace(boolean isGrace) {
		this.isGrace = isGrace;
	}

	/** @return true if this note is the first note in a repeat section */
	public boolean isRepeatedStart() {
		return isRepeatedStart;
	}

	/**
	 * Set whether this note is the first note in a repeat section.
	 * 
	 * @param isRepeatedStart the value to set
	 */
	public void setRepeatedStart(boolean isRepeatedStart) {
		this.isRepeatedStart = isRepeatedStart;
	}

	/** @return true if this note is the last note in a repeat section */
	public boolean isRepeatedStop() {
		return isRepeatedStop;
	}

	/**
	 * Set whether this note is the last note in a repeat section.
	 * 
	 * @param isRepeatedStop the value to set
	 */
	public void setRepeatedStop(boolean isRepeatedStop) {
		this.isRepeatedStop = isRepeatedStop;
	}

	/** @return true if this note is the last note before a double bar */
	public boolean isDoubleBar() {
		return isDoubleBar;
	}

	/**
	 * Set whether this note is the last note before a double bar.
	 * 
	 * @param isDoubleBar the value to set
	 */
	public void setDoubleBar(boolean isDoubleBar) {
		this.isDoubleBar = isDoubleBar;
	}

	/** @return the {@code DrumType} object of this note */
	public DrumType drumtype() {
		return drumType;
	}

	/**
	 * Set the {@code DrumType} object of this note to the specified value.
	 * 
	 * @param drumType the value to set
	 */
	public void setDrumType(DrumType drumType) {
		this.drumType = drumType;
	}

	/** @return true if this note has a stem */
	public String getStem() {
		return stem;
	}

	/**
	 * Set whether this note has a stem.
	 * 
	 * @param stem the value to set
	 */
	public void setStem(String stem) {
		setHasStem(true);
		this.stem = stem;
	}

	/** @return the notehead attribute of this note */
	public String getNotehead() {
		return notehead;
	}

	/**
	 * Set the notehead attribute to a specified value.
	 * 
	 * @param notehead the value to set
	 */
	public void setNotehead(String notehead) {
		this.notehead = notehead;
	}

	/** @return the id of this note's {@code DrumType} */
	public int getId() {
		return id;
	}

	@Override
	public int length() {
		return 0;
	}

	@Override
	public String toString() {
		return step;
	}
}
