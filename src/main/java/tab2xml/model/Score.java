package tab2xml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import tab2xml.ImmutablePair;
import tab2xml.IntRange;
import tab2xml.model.Score;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.guitar.GuitarNote;
import tab2xml.xmlconversion.XMLMetadata;

/**
 * A representation of a tablature score.
 * 
 * @author amir
 */
public class Score<E extends Staff<? extends Line>> implements Iterable<E> {

	/* Default values of score */
	/** The default beats. */
	public final static int DEFAULT_BEATS = 4;
	/** The default beat type of every score. */
	public final static int DEFAULT_BEATTYPE = 4;
	/** The default number of divisions per quarter note. */
	public final static int DEFAULT_DIVISION = 2;

	private Optional<Integer> beats;
	private Optional<Integer> beatType;
	private Optional<Integer> division;

	private List<E> staffs;
	private List<Measure<? extends Note>> measures;
	private static int accumulateMeasure = 0;
	private static int accumulateMeasureScore = 0;

	/**
	 * Construct an empty score.
	 */
	public Score() {
		beats = Optional.ofNullable(DEFAULT_BEATS);
		beatType = Optional.ofNullable(DEFAULT_BEATTYPE);
		division = Optional.ofNullable(DEFAULT_DIVISION);
		this.staffs = new ArrayList<>();
		this.measures = new LinkedList<>();
		Score.setAccumulateMeasure(0);
		Score.setAccumulateMeasureScore(0);
	}

	/**
	 * Add a specified staff, <b>s</b> to this score.
	 * 
	 * @param s the staff to add to this score
	 */
	public boolean addStaff(E s) {
		if (s != null)
			this.staffs.add(s);
		return true;
	}

	/**
	 * Return the total number of staffs in this score.
	 * 
	 * @return the number of staffs in this score
	 */
	public int size() {
		return staffs.size();
	}

	/**
	 * Return a list of staffs.
	 * 
	 * @return the list of staffs in this score
	 */
	public List<E> getStaffs() {
		return staffs;
	}

	/**
	 * Get a measure at a specified index in the score.
	 * 
	 * @param index
	 * @return the measure at the specified <b>index</b>
	 */
	public Measure<? extends Note> getMeasure(int index) {
		return measures.get(index);
	}

	/**
	 * Return the bounds of the measure at a specified index. In the form
	 * {@code ImmutablePair<UpperRange, LowerRange>}
	 * 
	 * @param index the index of the desired measure.
	 * @return a range object containing the bounds of the measure in the input
	 *         stream.
	 */
	public ImmutablePair<Range, Range> getMeasureRange(int index) {
		return measures.get(index).getRange();
	}

	public List<Measure<? extends Note>> getMeasures() {
		return measures;
	}

	/**
	 * Return the number of measures in this score.
	 * 
	 * @return the number of measures in this score
	 */
	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			E staff = staffs.get(i);
			count += staff.numberOfMeasures();
		}
		return count;
	}

	/**
	 * Process the staff data to measures.
	 */
	public void processMeasures(XMLMetadata metadata) {
		Map<IntRange, TimeSignature> map = null;
		if (metadata != null) {
			map = metadata.getTimeSignatureRanges();
			System.out.println("the map: " + map);
		}

		for (E st : staffs) {
			// all staffs by default are set to the scores general defaults/set defaults.
			st.setBeats(this.getBeats());
			st.setBeatType(this.getBeats());
			st.setDivision(this.getDivision());
			// optional feature let user set time signature staff by staff as well.

			Iterator<? extends Measure<? extends Note>> itr = st.measureIterator();
			while (itr.hasNext()) {
				Measure<? extends Note> measure = itr.next();

				// all measures by default are set to the scores general defaults/set defaults.
				measure.setBeats(this.getBeats());
				measure.setBeatType(this.getBeatType());
				measure.setDivision(this.getDivision());

				// if metadata is not null and this measure is in the selected range change time signature of that measure.
				if (map != null) {
					for (IntRange range : map.keySet()) {
						if (range.contains(measure.getMeasure() + 1)) {
							TimeSignature ts = map.get(range);
							measure.setBeats(ts.upperNumeral());
							measure.setBeats(ts.lowerNumeral());
						}
					}
				}

				// if user sets the first measure to a time signature different from default, that replaces the score default.
				if (measure.getMeasure() == 0) {
					st.setBeats(measure.getBeats());
					st.setBeatType(measure.getBeatType());
				}

				// divisions is set to the score general; possibility of letting user set this as well.
				measure.setDivision(this.getDivision());
				st.getNotes().forEach(n -> n.setMeasureObj(measure));
				measure.getNotes().forEach(n -> n.setMeasureObj(measure));
				measures.add(measure);
			}
		}
	}

	/**
	 * Return a count of all the notes in this score.
	 * 
	 * @return the number of notes in this score
	 */
	public int getNoteCount() {
		int total = 0;
		for (E staff : staffs)
			total += staff.getNoteCount();
		return total;
	}

	public Iterator<Measure<? extends Note>> measureIterator() {
		return new MeasureIterator<>(this.getMeasures());
	}

	public int getBeats() {
		return beats.orElse(DEFAULT_BEATS);
	}

	public void setBeats(int beats) {
		this.beats = Optional.ofNullable(beats);
	}

	public int getBeatType() {
		return beatType.orElse(DEFAULT_BEATTYPE);
	}

	public void setBeatType(int beatType) {
		this.beatType = Optional.ofNullable(beatType);
	}

	public int getDivision() {
		return division.orElse(DEFAULT_DIVISION);
	}

	public void setDivision(int division) {
		this.division = Optional.ofNullable(division);
	}

	/**
	 * Iterate over the staffs in this score.
	 */
	@Override
	public Iterator<E> iterator() {
		return new ScoreIterator<>(this.getStaffs());
	}

	/**
	 * Return an array of staffs data. To use when verifying correctness in unit
	 * tests.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (E staff : staffs) {
			Iterator<? extends Note> noteItr = staff.noteIterator();
			sb.append("{");
			while (noteItr.hasNext()) {
				LineItem item = noteItr.next();
				sb.append("{\"");
				if (item == null) {
					sb.append("null");
				} else if (item.getClass() == GuitarNote.class) {
					sb.append(((GuitarNote) item).getFret());
					sb.append("\",");
					sb.append("\"");
					sb.append(item.toString());
					sb.append("\"},");
				} else if (item.getClass() == DrumNote.class) {
					sb.append(item.toString());
					sb.append("\"}");
				}
			}
			sb.append("},");
			sb.append("\n");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Get the accumulate measure.
	 * 
	 * @return the value of the accumulating measure count.
	 */
	public static int getAccumulateMeasure() {
		return accumulateMeasure;
	}

	/**
	 * Accumulating measure count.
	 * 
	 * @param accumulateMeasure the static variable that keeps track of measure
	 *                          count.
	 */
	public static void setAccumulateMeasure(int accumulateMeasure) {
		Score.accumulateMeasure = accumulateMeasure;
	}

	/**
	 * Get the accumulate measure of the score.
	 */
	public static int getAccumulateMeasureScore() {
		return accumulateMeasureScore;
	}

	/**
	 * @param accumulateMeasureScore the accumulateMeasureScore to set
	 */
	public static void setAccumulateMeasureScore(int accumulateMeasureScore) {
		Score.accumulateMeasureScore = accumulateMeasureScore;
	}

	private static final class ScoreIterator<T> implements Iterator<T> {
		int index = 0;
		List<T> staffs;

		public ScoreIterator(List<T> staffs) {
			this.staffs = staffs;
		}

		@Override
		public boolean hasNext() {
			return index < staffs.size();
		}

		@Override
		public T next() {
			return staffs.get(index++);
		}
	}

	private static final class MeasureIterator<T> implements Iterator<T> {
		int index = 0;
		List<T> measures;

		public MeasureIterator(List<T> measures) {
			this.measures = measures;
		}

		@Override
		public boolean hasNext() {
			return index < measures.size();
		}

		@Override
		public T next() {
			return measures.get(index++);
		}
	}
}
