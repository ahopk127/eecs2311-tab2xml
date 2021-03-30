package tab2xml.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tab2xml.ImmutablePair;
import tab2xml.model.Score;
import tab2xml.model.drum.DrumNote;
import tab2xml.model.guitar.GuitarNote;

/**
 * A representation of a score.
 * 
 * @author amir s
 */
public class Score<E extends Staff<? extends Line<?>, ? extends Note>> implements Iterable<E> {

	/* Default values guitar */
	/**
	 * The default beats.
	 */
	public final static int DEFAULT_BEATS = 4;
	/**
	 * The default beat type of every score.
	 */
	public final static int DEFAULT_BEATTYPE = 4;
	/**
	 * The default number of divisions per quarter note.
	 */
	public final static int DEFAULT_DIVISION = 2;

	private List<E> staffs;
	private List<Measure<? extends Note>> measures;
	private static int accumulateMeasure = 0;
	private static int accumulateMeasureScore = 0;

	/**
	 * Construct an empty score.
	 */
	public Score() {
		this.staffs = new ArrayList<>();
		this.measures = new LinkedList<>();
		Score.setAccumulateMeasure(0);
		Score.setAccumulateMeasureScore(0);
	}

	/**
	 * Add a specified staff, <b>s></b> to this score.
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

	public List<Measure<? extends Note>> getMeasures() {
		return measures;
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
	public void processMeasures() {
		for (E st : staffs) {
			Iterator<? extends Measure<? extends Note>> itr = st.measureIterator();
			while (itr.hasNext()) {
				measures.add(itr.next());
			}
		}
	}

	/**
	 * Return the bounds of the measure at a specified index. In the form
	 * {@code ImmutablePair<UpperRange, LowerRange>}
	 * 
	 * @param index the index of the desired measure.
	 * @return a range object containing the bounds of the measure in the input
	 *         stream.
	 */
	public ImmutablePair<Range, Range> getMeasure(int index) {
		return measures.get(index).getRange();
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

	public Iterator<Measure<? extends Note>> measureIterator() {
		return new MeasureIterator<>(this.getMeasures());
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
			sb.append("{");
			for (LineItem item : staff) {
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
