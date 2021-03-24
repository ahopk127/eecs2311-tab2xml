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
			{"Bd","Bass Drum 1", "F", "4"},
			{"BD","Bass Drum 2", "F", "4"},
			{"SS","Side Stick", "C", "5"},
			{"SD","Snare", "C", "5"},
			{"ES","Electic Snare", "C", "5"},
			{"T1","Low Floor Tom", "A", "4"},
			{"CH","Closed Hi-Hat", "G", "5"},
			{"T2","High Floor Tom", "A", "4"},
			{"PH","Pedal Hi-Hat", "D", "4"},
			{"LT","Low Tom", "D", "5"},
			{"HH","Open Hi-Hat", "E", "5"},
			{"LM","Low-Mid Tom", "E", "5"},
			{"MT","Hi-Mid Tom", "F", "5"},
			{"Cc","Crash Cymbal 1", "A", "5"},
			{"HT","High Tom", "F", "5"},
			{"Rd","Ride Cymbal 1", "F", "5"},
			{"Ch","Chinese Cymbal", "B", "5"},
			{"RB","Ride Bell", "F", "5"},
			{"TA","Tambourine", "D", "5"},
			{"SC","Splash Cymbal", "B", "5"},
			{"CB","Cowbell", "E", "5"},
			{"CC","Crash Cymbal B", "5"},
			{"RD","Ride Cymbal 2", "D", "5"},
			{"HC","Open Hi Conga", "B", "4"},
			{"LC","Low Conga", "G", "4"}
	};
}
