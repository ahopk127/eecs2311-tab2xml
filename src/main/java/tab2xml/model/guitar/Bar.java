package tab2xml.model.guitar;

public class Bar extends StringItem {
	private static final long serialVersionUID = 6758542578259875168L;
	private int position;
	private int stringNum;
	private int repeatCount;
	private boolean isDoubleBar;
	private boolean isRepeat;
	private boolean start;
	private boolean stop;
	private String tune;

	public Bar() {
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public boolean isDoubleBar() {
		return isDoubleBar;
	}

	public void setDoubleBar(boolean isDoubleBar) {
		this.isDoubleBar = isDoubleBar;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public String getTune() {
		return tune;
	}

	public void setTune(String tune) {
		this.tune = tune;
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

	@Override
	public String toString() {
		if (isDoubleBar && !isRepeat)
			return "||";
		if (isDoubleBar && isRepeat && repeatCount != 0)
			return String.format("%d|", repeatCount);
		else if (isDoubleBar && isRepeat && start && !stop)
			return "||*";
		else if (isDoubleBar && isRepeat && stop && !start)
			return "*||";
		else if (isDoubleBar && isRepeat && stop && start)
			return "*||*";
		else
			return "|";
	}
}
