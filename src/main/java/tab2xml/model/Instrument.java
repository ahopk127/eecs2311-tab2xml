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
}
