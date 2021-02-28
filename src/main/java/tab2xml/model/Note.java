package tab2xml.model;

import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;

/**
 * An atomic note with its attributes.
 * 
 * @author amir
 */
public class Note extends StringItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423778159486375067L;

	private final NoteType note;

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
	private String voice;

	/*
	 * Technical attributes.
	 */
	private String string;
	private Fret fret;
	private int type;

	/*
	 * Position of the note.
	 */
	private int position;
	private int measure;
	private GuitarString s;
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

	/**
	 * Construct a note object based on type and a given step.
	 * 
	 * @param fret the fret number of this note
	 * @param s the guitar string of this note
	 */
	public Note(Fret fret, GuitarString s) {
		this.fret = fret;
		this.s = s;
		this.note = setNoteType();
		this.position = fret.getPosition();
		this.string = Integer.toString(s.getStringNum());
	}

	public Note(NoteType note) {
		this.note = note;
	}

	public Note(NoteType note, String step) {
		this.note = note;
		this.step = step;
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
	public Fret getFret() {
		return fret;
	}

	/**
	 * Set the fret attribute to the specified value.
	 * 
	 * @param fret the value to set the fret attribute
	 */
	public void setFret(Fret fret) {
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

	/**
	 * Set the position of this note in within its measure.
	 * 
	 * @param position the value to set the position of this note.
	 */
	public void setPosition(int position) {
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

	public boolean isStart() {
		return isStartHammer;
	}

	public void setStart(boolean isStart) {
		this.isStartHammer = isStart;
	}

	public boolean isStop() {
		return isStopHammer;
	}

	public void setStop(boolean isStop) {
		this.isStopHammer = isStop;
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

	@Override
	public int getStringNum() {
		return s.getStringNum();
	}

	/**
	 * Return the position of the note in its measure.
	 * 
	 * @return the position of the note
	 */
	@Override
	public int getPosition() {
		return position;
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
	 * @param input string input(<em>"tune + fret"</em>)
	 * @return a note based on the properties of the input
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	public static Note toNote(String input) throws InvalidTokenException {
		Pattern p = Pattern.compile("^[a-gA-G]\\d+$");
		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		String tune = input.substring(0, 1);
		tune = tune.toUpperCase();
		int fret = Integer.parseInt(input.substring(1));

		Note note = new Note(Note.getNoteType(tune));
		int index = (note.getIndex() + fret) % 12;

		NoteType noteType = NoteType.values()[index];
		String step = noteType.getValue();

		Note value = new Note(noteType, step);
		return value;
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
	 * Construct a note from the ASCII tablature.
	 * 
	 * @param input string input(<em>"tune + fret"</em>)
	 * @return a note based on the properties of the input
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	private NoteType setNoteType() {
		String tune = s.getTune();
		tune = tune.toUpperCase();
		int fretNum = fret.toInt();

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
