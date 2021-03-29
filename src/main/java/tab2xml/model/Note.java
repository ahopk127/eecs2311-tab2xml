package tab2xml.model;

public abstract class Note extends LineItem {
	private static final long serialVersionUID = -1924142088563071902L;

	/** The note type of this note */
	protected NoteType note;

	/* Pitch attributes */
	protected String step;
	protected String octave;

	/* Additional attributes */
	protected boolean hasStem;
	protected String duration;
	protected String type;
	protected String voice;

	/* Functional attributes */
	private double column;
	private int measure;
	private int repeatCount;
	private boolean lastInMeasure;
	private double durationVal;

	public Note() {
	}

	/**
	 * Construct a note object based {@code NoteType}.
	 * 
	 * @param type the type of note to create.
	 */
	public Note(NoteType type) {
		this.note = type;
		this.step = type.getValue();
	}

	protected abstract void setNoteType();

	public NoteType getNote() {
		return note;
	}

	public void setNote(NoteType note) {
		this.note = note;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getOctave() {
		return octave;
	}

	public void setOctave(String octave) {
		this.octave = octave;
	}

	public boolean isHasStem() {
		return hasStem;
	}

	public void setHasStem(boolean hasStem) {
		this.hasStem = hasStem;
	}

	public String getDuration() {
		return String.valueOf((int)(Staff.DEFAULT_BEATS / getDurationVal() * Staff.DEFAULT_DIVISION));
	}

	public void setDurationVal(double duration) {
		this.durationVal = duration;
	}
	
	public double getDurationVal() {
		return durationVal;
	}

	public String getType() {
		switch ((int)(durationVal)) {
		case 128:
			return "128th";
		case 64:
			return "64th";
		case 32:
			return "32nd";
		case 16:
			return "16th";
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

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public String getLine() {
		return String.valueOf(getLineNum());
	}

	public int getMeasure() {
		return measure;
	}

	public void setMeasure(int measure) {
		this.measure = measure;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public void setColumn(double column) {
		this.column = column;
	}

	public int getIndex() {
		return note.ordinal();
	}

	public boolean isLastInMeasure() {
		return lastInMeasure;
	}

	public void setLastInMeasure(boolean lastInMeasure) {
		this.lastInMeasure = lastInMeasure;
	}

	public static NoteType getNoteType(String note) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(note))
				return type;
		}
		return null;
	}

	@Override
	public abstract int length();

	@Override
	public double getColumn() {
		return column;
	}

	@Override
	public int getNoteCount() {
		return 1;
	}
}
