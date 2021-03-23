package tab2xml.model;

/**
 * List of supported instruments.
 * 
 * @author amir
 */
public enum Instrument {
	GUITAR("Classical Guitar"), BASS("Bass"), DRUM("Drumset");
	
	private final String name;

	private Instrument(String name) {
		this.name = name;
	}

	/**
	 * Return the string value of this note type.
	 * 
	 * @return the string representation of this note type 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * An array of parts of a drum set in format, [name, step, octave]
	 */
	public final static String[][] drumSet = {
			{"Bass Drum 1", "F", "4"},
			{"Bass Drum 2", "F", "4"},
			{"Side Stick", "C", "5"},
			{"Snare", "C", "5"},
			{"Electic Snare", "C", "5"},
			{"Low Floor Tom", "A", "4"},
			{"Closed Hi-Hat", "G", "5"},
			{"High Floor Tom", "A", "4"},
			{"Pedal Hi-Hat", "D", "4"},
			{"Low Tom", "D", "5"},
			{"Open Hi-Hat", "E", "5"},
			{"Low-Mid Tom", "E", "5"},
			{"Hi-Mid Tom", "F", "5"},
			{"Crash Cymbal 1", "A", "5"},
			{"High Tom", "F", "5"},
			{"Ride Cymbal 1", "F", "5"},
			{"Chinese Cymbal", "B", "5"},
			{"Ride Bell", "F", "5"},
			{"Tambourine", "D", "5"},
			{"Splash Cymbal", "B", "5"},
			{"Cowbell", "E", "5"},
			{"Crash Cymbal B", "5"},
			{"Ride Cymbal 2", "D", "5"},
			{"Open Hi Conga", "B", "4"},
			{"Low Conga", "G", "4"}
	};
}
