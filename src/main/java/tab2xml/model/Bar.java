package tab2xml.model;

import java.util.regex.Pattern;

import tab2xml.model.drum.DrumType;
import tab2xml.model.guitar.Tune;

/**
 * A model for a bar in tablature.
 * 
 * @author amir
 */
public class Bar extends LineItem {
	private static final long serialVersionUID = 6758542578259875168L;

	/** Pattern that matches bars. */
	public static final Pattern pattern = Pattern.compile("\\*?(\\||\\d+)?\\|\\|?\\*?");
	/* Functional attributes. */
	private int repeatCount;
	private boolean isDoubleBar;
	private boolean isRepeat;
	private boolean start;
	private boolean stop;
	private Tune tune;
	private DrumType drumType;
	private double rightPos;
	private double leftPos;

	/**
	 * Construct an empty bar with a default repeat count
	 */
	public Bar() {
		this.repeatCount = -1;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public boolean hasDoubleBar() {
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

	public Tune getTune() {
		return tune;
	}

	public void setTune(Tune tune) {
		this.tune = tune;
	}

	public void resetFretEndBars() {
		this.stop = false;
		this.repeatCount = -1;
	}

	public boolean isDoubleBar() {
		return isDoubleBar && !isRepeat && !start && !stop && repeatCount == -1;
	}

	public boolean isFretEndBar() {
		return !isDoubleBar && isRepeat && stop && repeatCount != -1;
	}

	public boolean isFretEndDoubleBar() {
		return isDoubleBar && isRepeat && stop && repeatCount != -1;
	}

	public boolean isStartBar() {
		return isDoubleBar && isRepeat && start && !stop;
	}

	public boolean isStopBar() {
		return isDoubleBar && isRepeat && !start && stop && repeatCount == -1;
	}

	public boolean isStartStopBar() {
		return isDoubleBar && isRepeat && start && stop && repeatCount == -1;
	}

	public boolean isRegularBar() {
		return !isDoubleBar() && !isFretEndBar() && !isFretEndDoubleBar() && !isStartBar() && !isStopBar()
				&& !isStartStopBar();
	}

	public double rightPos() {
		return rightPos;
	}

	public void setRightPos(double rightPos) {
		this.rightPos = rightPos;
	}

	public double leftPos() {
		return leftPos;
	}

	public void setLeftPos(double leftPos) {
		this.leftPos = leftPos;
	}

	public static String pattern() {
		return Bar.pattern.pattern();
	}

	public DrumType getDrumType() {
		return drumType;
	}

	public void setDrumType(DrumType type) {
		this.drumType = type;
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
		if (isDoubleBar())
			return "||";
		else if (isFretEndBar())
			return String.format("%d|", repeatCount);
		else if (isFretEndDoubleBar())
			return String.format("%d||", repeatCount);
		else if (isStartBar())
			return "||*";
		else if (isStopBar())
			return "*||";
		else if (isStartStopBar())
			return "*||*";
		else
			return "|";
	}
}
