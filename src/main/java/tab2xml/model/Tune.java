package tab2xml.model;

public class Tune extends StringItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7283815230989809053L;
	public static String standardTuning[][] = { { "E", "4" }, { "B", "3" }, { "G", "3" }, { "D", "3" }, { "A", "2" },
			{ "E", "2" } };

	private String tune;
	private boolean isStandard;
	private int stringNum;

	public Tune(String tune, boolean isStandard) {
		this.tune = tune;
		this.isStandard = isStandard;
	}

	public String getTune() {
		return tune;
	}

	public boolean isStandard() {
		return isStandard;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	@Override
	public int getPosition() {
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
