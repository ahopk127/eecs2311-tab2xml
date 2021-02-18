package tab2xml.parser;

/**
 * List of supported instruments.
 * 
 * @author amir
 */
public enum Instrument {
	GUITAR, DRUM, BASS;

	public static final String[] standardTuningGuitar = new String[] { "E", "B", "G", "D", "A", "E" };
	public static String[] tuningOctaveGuitar = new String[] { "2", "2", "3", "3", "3", "4" };
	public static boolean isStandardTuningGuitar = false;
}
