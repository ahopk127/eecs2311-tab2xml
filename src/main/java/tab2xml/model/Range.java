package tab2xml.model;

public class Range {
	private double start;
	private double stop;
	private double division;

	public Range(Measure<? extends Note> measure) {
		this.division = measure.width() / measure.getBeats();
	}

	public Range(double start, double stop) {
		if (start > stop)
			throw new IllegalArgumentException("Invalid rrange.");
		this.start = start;
		this.stop = stop;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getStop() {
		return stop;
	}

	public void setStop(double stop) {
		this.stop = stop;
	}

	public double getDivisionFactor() {
		return division;
	}

	public void setDivisionFactor(double divisionFactor) {
		this.division = divisionFactor;
	}

	public double size() {
		return stop - start + 1;
	}

	public Range getDivision(int i) {
		return new Range(i * division, i * division + division);
	}
}
