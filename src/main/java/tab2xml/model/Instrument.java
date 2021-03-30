package tab2xml.model;

/**
 * List of supported instruments.
 * 
 * @author amir
 */
public enum Instrument {
	GUITAR("Classical Guitar"), BASS("Bass Guitar"), DRUM("Drumset");

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
	public final static String[][] drumSet = { {"36", "Bd", "Bass Drum 1", "F", "4" }, {"37", "BD", "Bass Drum 2", "F", "4" },
			{ "38", "SS", "Side Stick", "C", "5" }, {"39", "SD", "Snare", "C", "5" }, { "40", "ES", "Electic Snare", "C", "5" },
			{ "42", "T1", "Low Floor Tom", "A", "4" }, { "43", "CH", "Closed Hi-Hat", "G", "5" },
			{ "44", "T2", "High Floor Tom", "A", "4" }, {"45", "PH", "Pedal Hi-Hat", "D", "4" }, {"46", "LT", "Low Tom", "D", "5" },
			{"47", "HH", "Open Hi-Hat", "E", "5" }, {"48", "LM", "Low-Mid Tom", "E", "5" }, {"49", "MT", "Hi-Mid Tom", "F", "5" },
			{ "50", "CC", "Crash Cymbal 1", "A", "5" }, {"51", "HT", "High Tom", "F", "5" }, {"52", "Rd", "Ride Cymbal 1", "F", "5" },
			{ "53","Ch", "Chinese Cymbal", "B", "5" }, {"54", "RB", "Ride Bell", "F", "5" }, { "55", "TA", "Tambourine", "D", "5" },
			{"56", "SC", "Splash Cymbal", "B", "5" }, {"57", "CB", "Cowbell", "E", "5" }, { "58","Cc", "Crash Cymbal", "B", "5" },
			{ "60", "RD", "Ride Cymbal 2", "D", "5" }, {"64", "HC", "Open Hi Conga", "B", "4" }, { "65", "LC", "Low Conga", "G", "4" } };
}
