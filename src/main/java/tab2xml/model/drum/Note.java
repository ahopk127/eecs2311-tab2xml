package tab2xml.model.drum;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.guitar.NoteType;
import tab2xml.model.guitar.StringItem;

public class Note extends StringItem{
	
	private String string;
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
	private String voice;

	private double position;
	private int measure;
	private int repeatCount;
	private String drumType;

	private boolean isChord;
	private boolean isGrace;
	private boolean isRepeatedStart;
	private boolean isRepeatedStop;
	
	public Note(String drumType) {
		this.drumType = drumType;
		this.note = setNoteType();
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
	 * Set note type from a drumType. will only work for basic example then need to be changed later
	 * 
	 * @return the note type of this note
	 * @throws InvalidTokenException if the parsed note type doesn't match the
	 *                               parsed step
	 */
	private NoteType setNoteType() {
		String drumType = this.drumType;
		NoteType noteType = null;
		if(drumType.toString()  == "CC|") {
			noteType = NoteType.values()[0];
		}
		else if(drumType.toString()  == "HH|") {
			noteType = NoteType.values()[10];
		}
		else if(drumType.toString()  == "SD|") {
			noteType = NoteType.values()[3];
		}
		else if(drumType.toString()  == "HT|") {
			noteType = NoteType.values()[7];
		}
		else if(drumType.toString()  == "MT|") {
			noteType = NoteType.values()[5];
		}
		else if(drumType.toString()  == "BD|") {
			noteType = NoteType.values()[8];
		}
		return noteType;
	}
	@Override
	public double getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getStringNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNoteCount() {
		
		return 1;
	}
//TODO literally everything. needs a rework for drums
}
