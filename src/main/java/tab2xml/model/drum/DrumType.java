package tab2xml.model.drum;

import tab2xml.model.Instrument;
import tab2xml.model.LineItem;

public class DrumType extends LineItem {
	private String drumType;
	private int stringNum;

	public DrumType() {
	}

	public DrumType(String drumType) {
		this.drumType = drumType;
	}

	public String getDrumType() {
		return drumType;
	}
	
	public static String getDrumType(String input) {
		for (int i = 0; i < Instrument.drumSet.length; i++) {
			if (Instrument.drumSet[i][0].equals(input) || Instrument.drumSet[i][1].equals(input))
				return Instrument.drumSet[i][0];
		}
		return "";
	}

	@Override
	public double getPosition() {
		return 0;
	}

	@Override
	public int getLineNum() {
		return stringNum;
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
