package tab2xml.model.drum;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

import tab2xml.exceptions.InvalidTokenException;
import tab2xml.model.drum.DrumType;
import tab2xml.model.StringItem;

public class Note extends StringItem{
	
	private String string;
	/**
	 * The note type of this note.
	 */
	

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
