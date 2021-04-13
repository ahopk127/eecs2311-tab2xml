package tab2xml.model;

/**
 * A custom {@code Enum} of supported instruments.
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
	 * Return the string value of this instrument.
	 * 
	 * @return the string representation of this instrument
	 */
	public String getName() {
		return name;
	}
}
