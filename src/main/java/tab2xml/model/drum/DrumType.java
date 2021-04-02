package tab2xml.model.drum;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import tab2xml.model.LineItem;

public class DrumType extends LineItem {
	private static final long serialVersionUID = 382303579699360922L;

	/**
	 * An array of parts of a drum set in format, Map<id, <abbreviation, name, step,
	 * octave>>
	 */
	public final static Map<Integer, List<String>> drumSet;
	static {
		drumSet = new HashMap<>();
		drumSet.put(36, List.of("BD", "Bass Drum 1", "F", "4"));
		drumSet.put(37, List.of("Bd", "Bass Drum 2", "F", "4"));
		drumSet.put(38, List.of("SS", "Side Stick", "C", "5"));
		drumSet.put(39, List.of("SD", "Snare", "C", "5"));
		drumSet.put(41, List.of("ES", "Electic Snare", "C", "5"));
		drumSet.put(42, List.of("T1", "Low Floor Tom", "A", "4"));
		drumSet.put(43, List.of("CH", "Closed Hi-Hat", "G", "5")); // cymbal
		drumSet.put(44, List.of("T2", "High Floor Tom", "A", "4"));
		drumSet.put(45, List.of("PH", "Pedal Hi-Hat", "D", "4")); // cymbal
		drumSet.put(46, List.of("LT", "Low Tom", "D", "5"));
		drumSet.put(47, List.of("HH", "Open Hi-Hat", "E", "5")); // cymbal
		drumSet.put(48, List.of("LM", "Low-Mid Tom", "E", "5"));
		drumSet.put(49, List.of("MT", "Hi-Mid Tom", "F", "5"));
		drumSet.put(50, List.of("CC", "Crash Cymbal 1", "A", "5")); // cymbal
		drumSet.put(51, List.of("HT", "High Tom", "F", "5"));
		drumSet.put(52, List.of("RD", "Ride Cymbal 1", "F", "5")); // cymbal
		drumSet.put(53, List.of("Ch", "Chinese Cymbal", "B", "5")); // cymbal
		drumSet.put(54, List.of("RB", "Ride Bell", "F", "5")); // cymbal
		drumSet.put(55, List.of("TA", "Tambourine", "D", "5"));
		drumSet.put(56, List.of("SC", "Splash Cymbal", "B", "5")); // cymbal
		drumSet.put(57, List.of("CB", "Cowbell", "E", "5"));
		drumSet.put(58, List.of("Cc", "Crash Cymbal 2", "B", "5")); // cymbal
		drumSet.put(60, List.of("Rd", "Ride Cymbal 2", "D", "5")); // cymbal
		drumSet.put(64, List.of("HC", "Open Hi Conga", "B", "4"));
		drumSet.put(65, List.of("LC", "Low Conga", "G", "4"));
	}
	public final static int[] cymbalIDs = new int[] { 43, 45, 47, 50, 52, 53, 54, 56, 58, 60 };
	public final static int[] drumIDs = new int[] { 36, 37, 38, 39, 41, 42, 44, 46, 48, 49, 51, 55, 57, 64, 65 };
	
	private String drumType;
	private int id;

	public DrumType(int id) {
		if (drumSet.get(id) == null)
			throw new InputMismatchException("Insturment ID, " + id + " doesn't exist.");
		this.id = id;
		this.drumType = getDrumType();
	}

	public DrumType(String drumType) {
		this.drumType = drumType;
		this.id = getDrumID(drumType);
	}

	public String getDrumType() {
		return drumType;
	}

	public int getID() {
		return id;
	}

	public static int getDrumID(String abreviation) {
		for (Integer ID : drumSet.keySet())
			if (drumSet.get(ID).get(0).equals(abreviation) || drumSet.get(ID).get(1).equals(abreviation))
				return ID;
		return -1;
	}

	public String getDrumName() {
		if (this.id == -1)
			return null;
		return drumSet.get(this.id).get(1);
	}

	public String getDrumStep() {
		if (this.id == -1)
			return null;
		return drumSet.get(this.id).get(2);
	}

	public String getDrumOctave() {
		if (this.id == -1)
			return null;
		return drumSet.get(this.id).get(3);
	}

	@Override
	public int length() {
		return toString().length();
	}

	@Override
	public int getNoteCount() {
		return 0;
	}

	@Override
	public String toString() {
		return drumType + "|";
	}

}
