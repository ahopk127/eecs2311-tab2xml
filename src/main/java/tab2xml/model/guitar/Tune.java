package tab2xml.model.guitar;

import tab2xml.model.LineItem;

public class Tune extends LineItem {
	private static final long serialVersionUID = 7283815230989809053L;
	public static String standardTuning[][] = { { "E", "2" }, { "A", "2" }, { "D", "3" }, { "G", "3" }, { "B", "3" },
			{ "E", "4" } };

	private String tune;
	private boolean isStandard;
	private boolean isBass;

	public Tune() {
		this.isStandard = true;
	}

	public Tune(String tune) {
		this.tune = tune;
	}

	public Tune(int lineNum) {
		this();
		this.lineNum = lineNum;
	}

	public String getTune() {
		if (isBass)
			return standardTuning[((lineNum - 1) % 4)][0];
		if (!isStandard)
			return tune;
		return standardTuning[(lineNum - 1) % 6][0];
	}

	public String getOctave() {
		if (isBass)
			return String.valueOf(Integer.parseInt(standardTuning[((lineNum - 1) % 4)][1]) - 1);
		return standardTuning[(lineNum - 1) % 6][1];
	}

	public boolean isStandard() {
		return isStandard;
	}

	public boolean isBass() {
		return isBass;
	}

	public void setBass(boolean isBass) {
		this.isBass = isBass;
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
		return (isStandard ? ("|") : (tune + "|"));
	}
}
