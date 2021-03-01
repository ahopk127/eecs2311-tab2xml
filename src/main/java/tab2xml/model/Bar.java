package tab2xml.model;

public class Bar extends StringItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6758542578259875168L;
	int position;
	private int stringNum;

	public Bar() {
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	@Override
	public int getPosition() {
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
	
	@Override
	public String toString() {
		return "|";
	}
}
