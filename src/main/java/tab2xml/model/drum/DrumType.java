package tab2xml.model.drum;

import tab2xml.model.StringItem;

public class DrumType extends StringItem{
	private String drumType;
	private int stringNum;
	public DrumType(String drumType){
		this.drumType = drumType;
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

	public String getDrumType() {
		
		return drumType;
	}
	@Override
	public String toString() {
		return drumType + "|";
	}

}
