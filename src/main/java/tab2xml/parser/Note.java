package tab2xml.parser;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import tab2xml.parser.Lexer.InvalidTokenException;

/**
 * An atomic note with its attributes.
 * 
 * @author amir
 */
public class Note extends Token {
	private final NoteType note;
	private static final TokenType tokenType = TokenType.NOTE;

	/*
	 * Pitch attributes.
	 */
	private String step;
	private String octave;

	private boolean hasStem;
	private int duration;
	private int voice;

	/*
	 * Technical attributes.
	 */
	private int string;
	private int fret;
	private int type;

	/*
	 * Hyphen position.
	 */
	private int position;

	/**
	 * Construct a note object based on type.
	 * 
	 * @param type the type of note
	 */
	public Note(NoteType type) {
		super(tokenType);
		this.note = type;

	}

	/**
	 * Construct a note object based on type and a given step.
	 * 
	 * @param type the type of note
	 * @param step the data of the note
	 * @throws InvalidTokenException if the data doesn't match the note type
	 */
	public Note(NoteType type, String step) throws InvalidTokenException {
		super(tokenType, step);
		this.note = type;
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
	public int getDuration() {
		return duration;
	}

	/**
	 * Set the duration attribute to the specified value.
	 * 
	 * @param duration the value to set the octave attribute
	 */
	public void setDuration(int duration) {
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
	public int getString() {
		return string;
	}

	/**
	 * Set the string attribute to the specified value.
	 * 
	 * @param string the value to set the string attribute
	 */
	public void setString(int string) {
		this.string = string;
	}

	/**
	 * Return the fret attribute of this note.
	 * 
	 * @return the fret attribute of this note
	 */
	public int getFret() {
		return fret;
	}

	/**
	 * Set the fret attribute to the specified value.
	 * 
	 * @param fret the value to set the fret attribute
	 */
	public void setFret(int fret) {
		this.fret = fret;
	}

	/**
	 * Set the voice attribute to the specified value.
	 * 
	 * @param voice the value to set the voice attribute
	 */
	public void setVoice(int voice) {
		this.voice = voice;
	}

	/**
	 * Return the voice attribute of this note.
	 * 
	 * @return the voice attribute of this note
	 */
	public int getVoice() {
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
	 * Return the position of the note in its measure.
	 * 
	 * @return the position of the note
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Set the position of this note in within its measure.
	 * 
	 * @param position the value to set the position of this note.
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Return the token type of this note({@code NoteType.NOTE}).
	 * 
	 * @return a {@code NOTE} type as defined by {@code NoteType}
	 */
	@Override
	public TokenType type() {
		return tokenType;
	}

	/**
	 * Return the value of this note.
	 * 
	 * @return the step of this note
	 */
	@Override
	public String getData() {
		return step;
	}

	/**
	 * Return the string representation of this note.
	 * 
	 * @return the string of this note's value
	 */
	@Override
	public String toString() {
		return String.format("%s", step);
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
		Pattern p = Pattern.compile("^[A-G]\\d+$");

		if (!p.matcher(input).matches())
			throw new InputMismatchException("The Note is invalid.");

		String tune = input.substring(0, 1);
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

}
