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

	/**
	 * Construct a {@code GuitarNote} object based on {@code NoteType}.
	 * 
	 * @param type the {@code NoteType} to create.
	 */
	public GuitarNote(NoteType type) {
		super(type);
	}

	/**
	 * Construct a {@code GuitarNote} object based on tune and a given fret.
	 *
	 * @param tune the tune of the string this note is on
	 * @param fret the fret of this note
	 */
	public GuitarNote(String tune, String fret) {
		super(setNoteType(tune, fret));
		this.tune = tune;
		this.fret = fret;
	}

	/**
	 * Construct a {@code GuitarNote} object based on a {@code GuitarString} and a
	 * given fret.
	 *
	 * @param guitarString the string this note is on
	 * @param fret         the fret of this note
	 */
	public GuitarNote(GuitarString guitarString, String fret) {
		this(guitarString.getTune(), fret);
		this.setLineNum(guitarString.getStringNum());
	}

	/** @return the string attribute of this note */
	public String getString() {
		return getLine();
	}

	/** @return the fret attribute of this note */
	public String getFret() {
		return fret;
	}

	/**
	 * Set the fret attribute of this note to the specified value.
	 * 
	 * @param fret the value to set
	 */
	public void setFret(String fret) {
		this.fret = fret;
	}

	/** @return the tune attribute of this note */
	public String getTune() {
		return tune;
	}

	/**
	 * Set the tune attribute of this note to the specified value.
	 * 
	 * @param tune the value to set
	 */
	public void setTune(String tune) {
		this.tune = tune;
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

	/** @return true if this note is the start of a hammer-on */
	public boolean isStartHammer() {
		return isStartHammer;
	}

	/**
	 * Set whether this note is the start of a hammer-on.
	 * 
	 * @param isStartHammer the value to set
	 */
	public void setStartHammer(boolean isStartHammer) {
		this.isStartHammer = isStartHammer;
	}

	/** @return true if this note is the end of a hammer-on */
	public boolean isStopHammer() {
		return isStopHammer;
	}

	/**
	 * Set whether this note is the end of a hammer-on.
	 * 
	 * @param isStopHammer the value to set
	 */
	public void setStopHammer(boolean isStopHammer) {
		this.isStopHammer = isStopHammer;
	}

	/** @return true if this note is the start of a pull-off */
	public boolean isStartPull() {
		return isStartPull;
	}

	/**
	 * Set whether this note is the start of a pull-off.
	 * 
	 * @param isStartPull the value to set
	 */
	public void setStartPull(boolean isStartPull) {
		this.isStartPull = isStartPull;
	}

	/** @return true if this note is the end of a pull-off */
	public boolean isStopPull() {
		return isStopPull;
	}

	/**
	 * Set whether this note is the end of a pull-off.
	 * 
	 * @param isStopPull the value to set
	 */
	public void setStopPull(boolean isStopPull) {
		this.isStopPull = isStopPull;
	}

	/** @return true if this note is the start of a hammer-on/pull-off chain */
	public boolean isStartChain() {
		return isStartChain;
	}

	/**
	 * Set whether this note is the start of a hammer-on/pull-off chain.
	 * 
	 * @param isStartChain the value to set
	 */
	public void setStartChain(boolean isStartChain) {
		this.isStartChain = isStartChain;
	}

	/**
	 * Set whether this note is the end of a hammer-on/pull-off chain.
	 * 
	 * @param isStopChain the value to set
	 */
	public boolean isStopChain() {
		return isStopChain;
	}

	/**
	 * Set whether this note is the end of a hammer-on/pull-off chain.
	 * 
	 * @param isStopChain the value to set
	 */
	public void setStopChain(boolean isStopChain) {
		this.isStopChain = isStopChain;
	}

	/** @return true if this note is the start of a slide */
	public boolean isStartSlide() {
		return isStartSlide;
	}

	/**
	 * Set whether this note is the start of a slide.
	 * 
	 * @param isStartSlide the value to set
	 */
	public void setStartSlide(boolean isStartSlide) {
		this.isStartSlide = isStartSlide;
	}

	/** @return true if this note is the end of a slide */
	public boolean isStopSlide() {
		return isStopSlide;
	}

	/**
	 * Set whether this note is the end of a slide.
	 * 
	 * @param isStopSlide the value to set
	 */
	public void setStopSlide(boolean isStopSlide) {
		this.isStopSlide = isStopSlide;
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

	/**
	 * Add a {@code GuitarNote} to group with this note.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>The <b>note</b> MUST note be {@code null}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param note the note to group with this note
	 * @return true if the note was added successfully
	 */
	public boolean add(GuitarNote note) {
		if (note == null)
			return false;
		this.getNotes().add(note);
		return true;
	}

	/**
	 * Add a collection of {@code GuitarNote} objects to group with this note.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>The <b>notes</b> MUST note be {@code null}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param notes the notes to group with this note
	 * @return true if all the notes were added successfully
	 */
	public boolean addAll(Collection<? extends GuitarNote> notes) {
		if (notes == null)
			return false;
		for (GuitarNote note : notes)
			if (!add(note))
				return false;
		return true;
	}

	@Override
	public int length() {
		return fret.length();
	}

	@Override
	public String toString() {
		return step;
	}

	/**
	 * A static utility method to construct a {@code GuitarNote} from the ASCII
	 * tablature.
	 * 
	 * @param input  a string of the format <em>"tune + fret"</em>
	 * @param string the string this note
	 * @return a {@code GuitarNote} based on the properties of the input
	 * @throws InvalidTokenException if the input doesn't match the format above
	 */
	public static final GuitarNote toNote(String input, int string) throws InvalidTokenException {
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
	 * A static guitar utility method to set the {@code NoteType} from a given tune
	 * and fret.
	 * 
	 * @return the the {@code NoteType} of the given input
	 * @throws InputMismatchException if the input combination is invalid
	 */
	private static final NoteType setNoteType(String tune, String fret) {
		String upperTune = tune.toUpperCase();
		int fretNum = Integer.parseInt(fret);
		Pattern p = Pattern.compile("^[A-G]#?\\d+$");
		String input = tune + fretNum;

		if (!p.matcher(input).matches())
			throw new InputMismatchException("The input is invalid.");

		int oldIndex = GuitarNote.getNoteType(upperTune).ordinal();
		int index = (oldIndex + fretNum) % 12;

		NoteType noteType = NoteType.values()[index];
		return noteType;
	}

	/**
	 * A static utility method to get the {@code NoteType} of a note given its step.
	 * 
	 * @param step
	 * @return the {@code NoteType} <b>note</b> argument otherwise null if
	 *         <b>note</b> is not valid
	 */
	public static final NoteType getNoteType(String step) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(step))
				return type;
		}
		return null;
	}
}
