package tab2xml.model;

import java.util.LinkedList;
import java.util.List;

import tab2xml.model.guitar.GuitarNote;

/**
 * An abstract mapping of a note in tablature and musicXML.
 * 
 * @author amir
 */
public abstract class Note extends LineItem {
	private static final long serialVersionUID = -1924142088563071902L;

	/** The note type of this note. */
	protected NoteType note;

	/* Pitch attributes. */
	protected String step;
	protected String octave;

	/* Additional attributes */
	protected boolean hasStem;
	protected boolean isDotted;
	protected String duration;
	protected String type;
	protected String voice;

	/* Functional attributes: should not be accessed by children. */
	private int measure;
	private int repeatCount;
	private boolean lastInMeasure;
	private double durationVal;

	/** A list of notes that are grouped with this note. */
	private List<GuitarNote> notes;

	/** The {@code Measure} object this note is in. */
	private Measure<? extends Note> measureObj;

	/** Construct an empty {@code Note} object. */
	public Note() {
		this.notes = new LinkedList<>();
	}

	/**
	 * Construct a {@code Note} object based on {@code NoteType}.
	 * 
	 * @param type the {@code NoteType} to create.
	 */
	public Note(NoteType type) {
		this();
		this.note = type;
		this.step = type.getValue();
	}

	/** @return the {@code NoteType} of this note */
	public NoteType getNoteType() {
		return note;
	}

	/**
	 * Set the {@code NoteType} of this note.
	 * 
	 * @param note the type {@code NoteTpe} to set this note to
	 */
	public void setNoteType(NoteType note) {
		this.note = note;
	}

	/** @return the step of this note */
	public String getStep() {
		return step;
	}

	/**
	 * Set the step of this note.
	 * 
	 * @param step the value to set
	 */
	public void setStep(String step) {
		this.step = step;
	}

	/** @return the octave of this note */
	public String getOctave() {
		return octave;
	}

	/**
	 * Set the octave of this note.
	 * 
	 * @param octave the value to set
	 */
	public void setOctave(String octave) {
		this.octave = octave;
	}

	/** @return {@code true} is this note has a stem */
	public boolean hasStem() {
		return hasStem;
	}

	/**
	 * Set whether this note has a stem.
	 * 
	 * @param hasStem the value to set
	 */
	public void setHasStem(boolean hasStem) {
		this.hasStem = hasStem;
	}

	/** @return {@code true} if this note is dotted. */
	public boolean isDotted() {
		return isDotted;
	}

	/**
	 * Set whether this note's duration is increased by half its original.
	 * 
	 * @param isDotted the value to set
	 */
	public void setDotted(boolean isDotted) {
		this.isDotted = isDotted;
	}

	/** @return the duration of this note */
	public String getDuration() {
		// by this point note have measure objects with set defaults
		int value = (int) (measureObj.getBeats() / getDurationVal() * measureObj.getDivision());
		return String.valueOf((value <= 0 ? 1 : value));
	}

	/** @return the duration value (used in the duration calculation process) */
	public double getDurationVal() {
		return durationVal;
	}

	/**
	 * Set the duration value of this note.
	 * 
	 * @param duration the value to set
	 */
	public void setDurationVal(double duration) {
		this.durationVal = duration;
	}

	/** @return the type of this note */
	public String getType() {
		if (durationVal == 128.0) {
			return "128th";
		} else if (durationVal == 64) {
			return "64th";
		} else if (durationVal == 32) {
			return "32nd";
		} else if (durationVal == 16) {
			return "16th";
		} else if (durationVal == 8) {
			return "eighth";
		} else if (durationVal == 4) {
			return "quarter";
		} else if (durationVal == 2) {
			return "half";
		} else if (durationVal == 192) {
			setDotted(true);
			return "128th";
		} else if (durationVal == 96) {
			setDotted(true);
			return "64th";
		} else if (durationVal == 48) {
			setDotted(true);
			return "32nd";
		} else if (durationVal == 24) {
			setDotted(true);
			return "16th";
		} else if (durationVal == 12) {
			setDotted(true);
			return "eighth";
		} else if (durationVal == 6) {
			setDotted(true);
			return "quarter";
		} else if (durationVal == 3) {
			setDotted(true);
			return "half";
		} else if (durationVal == 1.5) {
			setDotted(true);
			return "whole";
		} else {
			return "whole";
		}
	}

	/** @return the voice of this note */
	public String getVoice() {
		return "1";
	}

	/**
	 * Set the voice value of this note.
	 * 
	 * @param voice the value to set
	 */
	public void setVoice(String voice) {
		this.voice = voice;
	}

	/** @return the line number of this note as a {@code String} */
	public String getLine() {
		return String.valueOf(getLineNum());
	}

	/** @return the measure number of this note */
	public int getMeasure() {
		return measure;
	}

	/**
	 * Set the measure number of this note (starting from 0).
	 * 
	 * @param duration the value to set
	 */
	public void setMeasure(int measure) {
		this.measure = measure;
	}

	/**
	 * @return the repeat count of this note (of the first note in a repeat measure)
	 */
	public int getRepeatCount() {
		return repeatCount;
	}

	/**
	 * Set the repeat count of this note.
	 * 
	 * @param repeatCount the value to set
	 */
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	/** @return {@code true} if this note is the last note in its measure */
	public boolean isLastInMeasure() {
		return lastInMeasure;
	}

	/**
	 * Set whether this note is the last note in its measure.
	 * 
	 * @param lastInMeasure the value to set
	 */
	public void setLastInMeasure(boolean lastInMeasure) {
		this.lastInMeasure = lastInMeasure;
	}

	/** @return the list of notes grouped within this note */
	public List<GuitarNote> getNotes() {
		return notes;
	}

	/** @return the {@code Measure} object of this note */
	public Measure<? extends Note> getMeasureObj() {
		return measureObj;
	}

	/**
	 * Set the {@code Measure} object this note.
	 * 
	 * @param measureObj the value to set
	 */
	public void setMeasureObj(Measure<? extends Note> measureObj) {
		this.measureObj = measureObj;
	}

	/** @return The index of this note within the {@code NoteType} {@code Enum} */
	public int getIndex() {
		return note.ordinal();
	}

	/**
	 * A static utility method to find the {@code NoteType} of a notes step.
	 * 
	 * @param step the step of a note
	 * @return the {@code NoteType} of the given step
	 */
	public static NoteType getNoteType(String step) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(step))
				return type;
		}
		return null;
	}

	@Override
	public abstract int length();

	@Override
	public int getNoteCount() {
		return 1;
	}
}
