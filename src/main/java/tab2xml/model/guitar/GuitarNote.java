package tab2xml.model.guitar;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;

import tab2xml.model.NoteType;
import tab2xml.model.Note;

/**
 * A guitar note and its attributes.
 * 
 * @author amir
 */
public class GuitarNote extends Note {
	private static final long serialVersionUID = -7423778159486375067L;

	/* Technical attributes */
	private String fret;

	/* Functional attributes */
	private String tune;
	private boolean isChord;
	private boolean isStartHammer;
	private boolean isStopHammer;
	private boolean isStartPull;
	private boolean isStopPull;
	private boolean isStartChain;
	private boolean isStopChain;
	private boolean isStartSlide;
	private boolean isStopSlide;
	private boolean isHarmonic;
	private boolean isGrace;
	private boolean isRepeatedStart;
	private boolean isRepeatedStop;
	private boolean isDoubleBar;

	/*************************/

	/**
	 * Construct a note object based on tune and a given fret.
	 *
	 * @param tune the tune of the string this note is on
	 * @param fret the fret of this note
	 */
	public GuitarNote(String tune, String fret) {
		this.tune = tune;
		this.fret = fret;
		setNoteType();
	}

	/**
	 * Construct a note object based on type of note.
	 * 
	 * @param type the type of this note
	 */
	public GuitarNote(NoteType type) {
		super(type);
	}

	public GuitarNote(GuitarString guitarString, String fret) {
		this(guitarString.getTune(), fret);
		this.setLineNum(guitarString.getStringNum());
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

	/**
	 * Return the fret attribute of this note.
	 * 
	 * @return the fret attribute of this note
	 */
	public String getFret() {
		return fret;
	}

	/**
	 * Set the fret attribute to the specified value.
	 * 
	 * @param fret the value to set the fret attribute
	 */
	public void setFret(String fret) {
		this.fret = fret;
	}

	public String getTune() {
		return tune;
	}

	public void setTune(String tune) {
		this.tune = tune;
	}

	public boolean isChord() {
		return isChord;
	}

	public void setChord(boolean isChord) {
		this.isChord = isChord;
	}

	public boolean isStartHammer() {
		return isStartHammer;
	}

	public void setStartHammer(boolean isStartHammer) {
		this.isStartHammer = isStartHammer;
	}

	public boolean isStopHammer() {
		return isStopHammer;
	}

	public void setStopHammer(boolean isStopHammer) {
		this.isStopHammer = isStopHammer;
	}

	public boolean isStartPull() {
		return isStartPull;
	}

	public void setStartPull(boolean isStartPull) {
		this.isStartPull = isStartPull;
	}

	public boolean isStopPull() {
		return isStopPull;
	}

	public void setStopPull(boolean isStopPull) {
		this.isStopPull = isStopPull;
	}

	public boolean isStartChain() {
		return isStartChain;
	}

	public void setStartChain(boolean isStartChain) {
		this.isStartChain = isStartChain;
	}

	public boolean addAll(Collection<? extends GuitarNote> notes) {
		for (GuitarNote note : notes)
			add(note);
		return true;
	}

	public boolean add(GuitarNote note) {
		if (note != null)
			this.getNotes().add(note);
		return true;
	}

	public boolean isStopChain() {
		return isStopChain;
	}

	public void setStopChain(boolean isStopChain) {
		this.isStopChain = isStopChain;
	}

	public boolean isStartSlide() {
		return isStartSlide;
	}

	public void setStartSlide(boolean isStartSlide) {
		this.isStartSlide = isStartSlide;
	}

	public boolean isStopSlide() {
		return isStopSlide;
	}

	public void setStopSlide(boolean isStopSlide) {
		this.isStopSlide = isStopSlide;
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
	 * Construct a note from the ASCII tablature.
	 * 
	 * @param input  string input(<em>"tune + fret"</em>)
	 * @param string the string this note is on
	 * @return a note based on the properties of the input
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	public static GuitarNote toNote(String input, int string) throws InvalidTokenException {
		Pattern p = Pattern.compile("^[a-gA-G]\\d+$");
		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		String tune = input.substring(0, 1);
		tune = tune.toUpperCase();
		String fret = input.substring(1);

		GuitarNote note = new GuitarNote(GuitarNote.getNoteType(tune));
		int index = (note.getIndex() + Integer.parseInt(fret)) % 12;

		NoteType noteType = NoteType.values()[index];
		GuitarNote noteResult = new GuitarNote(noteType);
		noteResult.setLineNum(string);
		return noteResult;
	}

	/**
	 * Get the note type of a note as a string.
	 * 
	 * @param note
	 * @return the type of note of <b>note</b> argument otherwise null if
	 *         <b>note</b> is not valid
	 */
	public static NoteType getNoteType(String note) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(note))
				return type;
		}
		return null;
	}

	@Override
	public int length() {
		return fret.length();
	}

	/**
	 * Set note type from a note already defined with a step and fret
	 * 
	 * @return the note type of this note
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	@Override
	protected void setNoteType() {
		String tune = this.tune;
		tune = tune.toUpperCase();
		int fretNum = Integer.parseInt(fret);

		Pattern p = Pattern.compile("^[A-G]\\d+$");
		String input = tune + fretNum;

		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		int oldIndex = GuitarNote.getNoteType(tune).ordinal();
		int index = (oldIndex + fretNum) % 12;

		NoteType noteType = NoteType.values()[index];
		setStep(noteType.getValue());

		this.note = noteType;
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
