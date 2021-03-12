package tab2xml.model.guitar;

public class Fret extends StringItem {
	private static final long serialVersionUID = 5648596253080025937L;
	private String value;
	private int position;
	private int stringNum;

	public Fret(String value) {
		this.value = value;
	}

	public Fret(String value, int position) {
		this.value = value;
		this.position = position;
	}

	public String getValue() {
		return value;
	}

	public int toInt() {
		return Integer.parseInt(value);
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public int getStringNum() {
		return stringNum;
	}

	@Override
	public int getNoteCount() {
		return 0;
	}
}
