package tab2xml.model.guitar;

import tab2xml.model.LineItem;

public class Tune extends LineItem {
	private static final long serialVersionUID = 7283815230989809053L;
	public static String standardTuning[][] = { { "E", "2" }, { "B", "2" }, { "G", "3" }, { "D", "3" }, { "A", "3" },
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
		if (isBass)
			return standardTuning[((stringNum - 1) % 4) + 2][0];
		if (!isStandard)
			return tune;
		return standardTuning[(stringNum - 1) % 6][0];
	}

	public String getOctave() {
		if (isBass)
			return String.valueOf(Integer.parseInt(standardTuning[((stringNum - 1) % 4) + 2][1]) - 1);
		return standardTuning[(stringNum - 1) % 6][1];
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
	public int getLineNum() {
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
