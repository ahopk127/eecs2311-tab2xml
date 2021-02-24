package tab2xml.model;

public class Fret extends StringItem{
	private String value;
	private int position;
	
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
