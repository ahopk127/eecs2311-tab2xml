package tab2xml.model.guitar;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.NoteType;
import tab2xml.model.StringItem;

/**
 * An atomic note with its attributes.
 * 
 * @author amir
 */
public class Note extends StringItem {
	private static final long serialVersionUID = -7423778159486375067L;

	/**
	 * The note type of this note.
	 */
	private NoteType note;

	/*
	 * Pitch attributes.
	 */
	private String step;
	private String octave;

	/*
	 * Additional attributes.
	 */
	private boolean hasStem;
	private String duration;
	private String voice = "1";

	/*
	 * Technical attributes.
	 */
	private String string;
	private String fret;
	private int type;

	private double position;
	private int measure;
	private int repeatCount;
	private String tune;

	private boolean isChord;
	private boolean isStartHammer;
	private boolean isStopHammer;

	private boolean isStartPull;
	private boolean isStopPull;

	private boolean isStartChain;
	private boolean isStopChain;
	private List<Note> middle;

	private boolean isStartSlide;
	private boolean isStopSlide;

	private boolean isHarmonic;
	private boolean isGrace;
	private boolean isRepeatedStart;
	private boolean isRepeatedStop;
	private boolean isDoubleBar;

	/**
	 * Construct a note object based on tune and a given fret.
	 *
	 * @param tune the tune of the string this note is on
	 * @param fret the fret of this note
	 */
	public Note(String tune, String fret) {
		this.tune = tune;
		this.fret = fret;
		this.note = setNoteType();
	}

	/**
	 * Construct a note object based on type of note.
	 * 
	 * @param type the type of this note
	 */
	public Note(NoteType type) {
		// TODO REMOVE THIS CONSTURCTOR
		this.note = type;
		this.step = type.getValue();
	}

	public Note(GuitarString guitarString, String fret) {
		this.fret = fret;
		this.tune = guitarString.getTune();
		this.note = setNoteType();
		this.string = Integer.toString(guitarString.getStringNum());
	}

	/**
	 * The position of this note within all the 12 notes.
	 * 
	 * @return the index of this note within {@code NoteType}
	 */
	public int getIndex() {
		return note.ordinal();
	}

	/**
	 * Return the stem attribute of this note.
	 * 
	 * @return the stem attribute of this note
	 */
	public boolean hasStem() {
		return hasStem;
	}

	/**
	 * Set the stem attribute to the specified note.
	 * 
	 * @param hasStem the value to set the stem attribute
	 */
	public void setHasStem(boolean hasStem) {
		this.hasStem = hasStem;
	}

	/**
	 * Return the duration attribute of this note.
	 * 
	 * @return the duration attribute of this note
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Set the duration attribute to the specified value.
	 * 
	 * @param duration the value to set the octave attribute
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * Return the octave attribute of this note.
	 * 
	 * @return the octave attribute of this note
	 */
	public String getOctave() {
		return octave;
	}

	/**
	 * Set the octave attribute to the specified value.
	 * 
	 * @param octave the value to set the octave attribute
	 */
	public void setOctave(String octave) {
		this.octave = octave;
	}

	/**
	 * Return the string attribute to the specified value.
	 * 
	 * @return the string attribute of this note
	 */
	public String getString() {
		return string;
	}

	/**
	 * Set the string attribute to the specified value.
	 * 
	 * @param string the value to set the string attribute
	 */
	public void setString(String string) {
		this.string = string;
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

	/**
	 * Set the voice attribute to the specified value.
	 * 
	 * @param voice the value to set the voice attribute
	 */
	public void setVoice(String voice) {
		this.voice = voice;
	}

	/**
	 * Return the voice attribute of this note.
	 * 
	 * @return the voice attribute of this note
	 */
	public String getVoice() {
		return voice;
	}

	/**
	 * Return the type attribute of this note.
	 * 
	 * @return the type attribute of this note
	 */
	public String getType() {
		switch (type) {
		case 16:
			return "sixteenth";
		case 8:
			return "eighth";
		case 4:
			return "quarter";
		case 2:
			return "half";
		default:
			return "whole";
		}
	}

	/**
	 * Return the type of this note.
	 * 
	 * @param type the type of this note (<em>A, B, C,..</em>) as defined by
	 *             {@code NoteType}
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Return the note type of this note.
	 * 
	 * @return the note type of this note
	 */
	public NoteType getNoteType() {
		return note;
	}

	public void setPosition(double position) {
		this.position = position;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public boolean isChord() {
		return isChord;
	}

	public void setChord(boolean isChord) {
		this.isChord = isChord;
	}

	public int getMeasure() {
		return measure;
	}

	public void setMeasure(int measure) {
		this.measure = measure;
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

	public List<Note> getMiddle() {
		return middle;
	}

	public void setMiddle(List<Note> middle) {
		this.middle = middle;
	}

	public boolean isMiddle() {
		return middle.contains(this);
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

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public boolean isDoubleBar() {
		return isDoubleBar;
	}

	public void setDoubleBar(boolean isDoubleBar) {
		this.isDoubleBar = isDoubleBar;
	}

	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public int getStringNum() {
		return Integer.parseInt(string);
	}

	@Override
	public int getNoteCount() {
		return 1;
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

	/**
	 * Construct a note from the ASCII tablature.
	 * 
	 * @param input  string input(<em>"tune + fret"</em>)
	 * @param string the string this note is on
	 * @return a note based on the properties of the input
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	public static Note toNote(String input, int string) throws InvalidTokenException {
		Pattern p = Pattern.compile("^[a-gA-G]\\d+$");
		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		String tune = input.substring(0, 1);
		tune = tune.toUpperCase();
		String fret = input.substring(1);

		Note note = new Note(Note.getNoteType(tune));
		int index = (note.getIndex() + Integer.parseInt(fret)) % 12;

		NoteType noteType = NoteType.values()[index];
		Note noteResult = new Note(noteType);
		noteResult.setString(Integer.toString(string));
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

	/**
	 * Set note type from a note already defined with a step and fret
	 * 
	 * @return the note type of this note
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	private NoteType setNoteType() {
		String tune = this.tune;
		tune = tune.toUpperCase();
		int fretNum = Integer.parseInt(fret);

		Pattern p = Pattern.compile("^[A-G]\\d+$");
		String input = tune + fretNum;

		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		int oldIndex = Note.getNoteType(tune).ordinal();
		int index = (oldIndex + fretNum) % 12;

		NoteType noteType = NoteType.values()[index];
		setStep(noteType.getValue());

		return noteType;
	}
}
