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
	private int octave;

	private boolean hasStem;
	private int duration;
	private int voice;

	/*
	 * Technical attributes.
	 */
	private int string;
	private int fret;
	private int type;

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
	 * Construct a note object based on type.
	 * 
	 * @param type the type of note
	 * @throws InvalidTokenException
	 */
	public Note(NoteType type, String step) throws InvalidTokenException {
		super(tokenType, step);
		this.note = type;
		this.step = step;
	}

	public NoteType getNoteType() {
		return note;
	}

	public int getIndex() {
		return note.ordinal();
	}

	public boolean hasStem() {
		return hasStem;
	}

	public void setHasStem(boolean hasStem) {
		this.hasStem = hasStem;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}

	public int getString() {
		return string;
	}

	public void setString(int string) {
		this.string = string;
	}

	public int getFret() {
		return fret;
	}

	public void setFret(int fret) {
		this.fret = fret;
	}

	public void setVoice() {
	}

	public int getVoice() {
		return voice;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public TokenType type() {
		return tokenType;
	}

	@Override
	public String getData() {
		return step;
	}

	@Override
	public String toString() {
		return String.format("%s", step);
	}

	/**
	 * Construct a note from the ASCII tablature.
	 * 
	 * @param input string input containing: tuning+fret number
	 * @return a note based on the properties of the input
	 * @throws InvalidTokenException
	 */
	public static Note toNote(String input) throws InvalidTokenException {
		Pattern p = Pattern.compile("^[A-G]\\d+$");

		if (!p.matcher(input).matches())
			throw new InputMismatchException();

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
