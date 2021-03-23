package tab2xml.model;

public abstract class Note extends LineItem {
	private static final long serialVersionUID = -1924142088563071902L;

	/** The note type of this note */
	protected NoteType note;

	/*Pitch attributes*/
	protected String step;
	protected String octave;

	/*Additional attributes*/
	protected boolean hasStem;
	protected String duration;
	protected String type;
	protected String voice;

	/* Functional attributes*/
	private double position;
	private int line;
	private int measure;
	private int repeatCount;

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
		return String.valueOf((Score.DEFAULT_BEATTYPE * (1 / Integer.parseInt(duration))) * Score.DEFAULT_DIVISION);
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getType() {
		switch (Integer.parseInt(getDuration())) {
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
		return String.valueOf(line);
	}

	public void setLine(int line) {
		this.line = line;
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

	public void setPosition(double position) {
		this.position = position;
	}

	public int getIndex() {
		return note.ordinal();
	}

	public static NoteType getNoteType(String note) {
		for (NoteType type : NoteType.values()) {
			if (type.getValue().equals(note))
				return type;
		}
		return null;
	}

	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public int getLineNum() {
		return line;
	}

	@Override
	public int getNoteCount() {
		return 1;
	}
}
