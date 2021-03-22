package tab2xml.model.guitar;

import tab2xml.model.StringItem;

public class Tune extends StringItem {
	private static final long serialVersionUID = 7283815230989809053L;
	public static String standardTuning[][] = { { "E", "2" }, { "A", "2" }, { "D", "3" }, { "G", "3" }, { "B", "3" },
			{ "E", "4" } };

	private String tune;
	private int stringNum;
	private boolean isStandard;
	private boolean isBass;

	public Tune() {
		this.isStandard = true;
	}

	public Tune(String tune) {
		this.tune = tune;
	}

	public Tune(int stringNum) {
		this();
		this.stringNum = stringNum;
	}

	public String getTune() {
		if (!isStandard)
			return tune;
		return standardTuning[(6 - stringNum) % 6][0];
	}

	public String getOctave() {
		if (isBass)
			return String.valueOf(Integer.parseInt(standardTuning[(6 - stringNum) % 6][1]) - 1);
		return standardTuning[(6 - stringNum) % 6][1];
	}

	public boolean isStandard() {
		return isStandard;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	public boolean isBass() {
		return isBass;
	}

	public void setBass(boolean isBass) {
		this.isBass = isBass;
	}

	@Override
	public double getPosition() {
		return 0;
	}

	@Override
	public int getStringNum() {
		return stringNum;
	}

	@Override
	public int getNoteCount() {
		return 0;
	}

	@Override
	public String toString() {
		return (isStandard ? ("|") : (tune + "|"));
	}
}
