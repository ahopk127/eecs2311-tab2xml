package tab2xml.model;

public class Tune extends StringItem {
	public static String standardTuning[] = { "E", "B", "G", "D", "A", "E" };
	private String tune;

	public Tune(String tune) {
		this.tune = tune;
	}

	public String getTune() {
		return tune;
	}

	@Override
	public String toString() {
		return tune;
	}
}
