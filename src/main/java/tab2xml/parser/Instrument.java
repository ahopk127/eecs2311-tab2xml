package tab2xml.parser;

/**
 * List of supported instruments.
 * 
 * @author amir
 */
public enum Instrument {
	// expandable
	GUITAR, DRUM;
	
	public static final String[] standardTuning = new String[] {"E", "B", "G", "D", "A", "E"};
}
