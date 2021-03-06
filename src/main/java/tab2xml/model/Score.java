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
 * A representation of a score containing a collection of a parameterized
 * {@code Staff} types.
 * 
 * <p>
 * Properties of this object:
 * <ul>
 * <li>A {@code Score} can by default iterate over its {@code Staff} objects in
 * their natural ordering.
 * <li>A {@code Score} can also iterate over its {@code Measure} objects in
 * their natural ordering.
 * </ul>
 * 
 * @author amir
 * @param <E> a type of {@code Staff} which this score contains
 */
public class Score<E extends Staff<? extends Line>> implements Iterable<E> {
	/* Default values of score */
	/** The default beats. */
	public final static int DEFAULT_BEATS = 4;
	/** The default beat type of every score. */
	public final static int DEFAULT_BEATTYPE = 4;
	/** The default number of divisions per quarter note. */
	public final static int DEFAULT_DIVISION = 2;

	/** The beats of this score. */
	private Optional<Integer> beats = Optional.empty();
	/** The beatType of this score. */
	private Optional<Integer> beatType = Optional.empty();
	/** The division of this score. */
	private Optional<Integer> division = Optional.empty();

	/** The list of staffs in this score. */
	private List<E> staffs;
	/** The list of measures in this score. */
	private List<Measure<? extends Note>> measures;
	/** The static measure counter that keeps track of note measures. */
	private static int accumulateMeasure = 0;
	/** The static measure counter used when serializing measures. */
	private static int accumulateMeasureScore = 0;

	/** Construct an empty score with set default attributes. */
	public Score() {
		beats = Optional.of(DEFAULT_BEATS);
		beatType = Optional.of(DEFAULT_BEATTYPE);
		division = Optional.of(DEFAULT_DIVISION);
		this.staffs = new ArrayList<>();
		this.measures = new LinkedList<>();
		Score.setAccumulateMeasure(0);
		Score.setAccumulateMeasureScore(0);
	}

	/**
	 * Add a specified staff, <b>s</b> to this score.
	 * 
	 * @param s the staff to add to this score
	 * @return {@code true} if the staff was added successfully
	 */
	public boolean addStaff(E s) {
		if (s == null)
			return false;
		this.staffs.add(s);
		return true;
	}

	/** @return the list of staffs in this score */
	public List<E> getStaffs() {
		return staffs;
	}

	/** @return the number of staffs in this score */
	public int size() {
		return staffs.size();
	}

	/**
	 * Get a measure at a specified index in the score.
	 * 
	 * @param index the index of the desired measure
	 * @return the measure at the specified <b>index</b>
	 */
	public Measure<? extends Note> getMeasure(int index) {
		return measures.get(index);
	}

	/**
	 * Return the bounds of the measure at a specified index. In the form
	 * {@code ImmutablePair<UpperRange, LowerRange>}
	 * 
	 * @param index the index of the desired measure
	 * @return a range object containing the bounds of the measure in the input
	 *         stream
	 */
	public ImmutablePair<Range, Range> getMeasureRange(int index) {
		return measures.get(index).getRange();
	}

	/** @return the list of measures in this score */
	public List<Measure<? extends Note>> getMeasures() {
		return measures;
	}

	/** @return the number of measures in this score */
	public int numberOfMeasures() {
		int count = 0;
		for (int i = 0; i < staffs.size(); i++) {
			E staff = staffs.get(i);
			count += staff.numberOfMeasures();
		}
		return count;
	}

	/**
	 * Process the measures from this score's {@code Staff} objects.
	 * 
	 * <p>
	 * Pre-conditions:
	 * <ul>
	 * <li>A {@code Score} must process the {@code Staff} objects before this
	 * process</li>
	 * </ul>
	 * </p>
	 * 
	 * @param metadata the metadata set in the GUI
	 */
	public void processMeasures(XMLMetadata metadata) {
		Map<IntRange, TimeSignature> map = null;

		if (metadata != null) {
			map = metadata.timeSignatureRanges();
		}

		E firstStaff = null;
		for (E st : staffs) {
			// all staffs by default are set to the scores general defaults/set defaults.
			st.setBeats(this.getBeats());
			st.setBeatType(this.getBeats());
			st.setDivision(this.getDivision());
			// optional feature let user set time signature staff by staff as well.

			Iterator<? extends Measure<? extends Note>> itr = st.measureIterator();
			while (itr.hasNext()) {
				Measure<? extends Note> measure = itr.next();

				// if metadata is not null and this measure is in the selected range change time signature of that measure.
				if (map != null) {
					for (IntRange range : map.keySet()) {
						if (range.contains(measure.getMeasure() + 1)) {
							TimeSignature ts = map.get(range);
							if (ts != null) {
								measure.setBeats(ts.upperNumeral());
								measure.setBeatType(ts.lowerNumeral());
							}
						}
					}
				}

				// if user sets the first measure to a time signature different from default, that replaces the score default.
				if (measure.getMeasure() == 0) {
					st.setBeats(measure.getBeats());
					st.setBeatType(measure.getBeatType());
					st.setDivision(measure.getDivision());
					firstStaff = st;
				}

				// first staff won't be null as there is at least one measure.
				// all measures by default are set to the first measure's/score's set attributes.
				if (firstStaff == null)
					throw new AssertionError("First staff should not be null.");

				measure.setBeats(firstStaff.getBeats());
				measure.setBeatType(firstStaff.getBeatType());
				measure.setDivision(firstStaff.getDivision());

				// if metadata is not null and this measure is in the selected range change time signature of that measure.
				if (map != null) {
					for (IntRange range : map.keySet()) {
						if (range.contains(measure.getMeasure() + 1)) {
							TimeSignature ts = map.get(range);
							if (ts != null) {
								measure.setBeats(ts.upperNumeral());
								measure.setBeatType(ts.lowerNumeral());
							}
						}
					}
				}

				// divisions is set to the score general; possibility of letting user set this as well.
				measure.setDivision(this.getDivision());
				measure.getNotes().forEach(n -> n.setMeasureObj(measure));
				this.measures.add(measure);
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

	/** @return the beats attribute of this score (default value if not set) */
	public int getBeats() {
		return beats.orElse(DEFAULT_BEATS);
	}

	/**
	 * Set the beats attribute of this score to a specified value.
	 * 
	 * @param beats the value to set
	 */
	public void setBeats(int beats) {
		this.beats = Optional.of(beats);
	}

	/** @return the beatType attribute of this score (default value if not set) */
	public int getBeatType() {
		return beatType.orElse(DEFAULT_BEATTYPE);
	}

	/**
	 * Set the beatType attribute of this score to a specified value.
	 * 
	 * @param beatType the value to set
	 */
	public void setBeatType(int beatType) {
		this.beatType = Optional.of(beatType);
	}

	/** @return the division attribute of this score (default value if not set) */
	public int getDivision() {
		return division.orElse(DEFAULT_DIVISION);
	}

	/**
	 * Set the division attribute of this score to a specified value.
	 * 
	 * @param division the value to set
	 */
	public void setDivision(int division) {
		this.division = Optional.of(division);
	}

	/**
	 * @return an {@code Iterator} which iterates over this score measure by measure
	 */
	public Iterator<Measure<? extends Note>> measureIterator() {
		return new MeasureIterator<>(this.getMeasures());
	}

	/** @return an {@code Iterator} which iterates over the staffs in this score */
	@Override
	public Iterator<E> iterator() {
		return new ScoreIterator<>(this.getStaffs());
	}

	/**
	 * Return an array of staffs with their note data. To use when verifying
	 * correctness in unit tests.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		final int staffCount = staffs.size();
		int i = 0;
		for (E staff : staffs) {
			final int noteCount = staff.getNoteCount();
			int j = 0;
			Iterator<? extends Note> noteItr = staff.noteIterator();
			sb.append("{");
			while (noteItr.hasNext()) {
				LineItem item = noteItr.next();
				sb.append("{\"");
				if (item == null) {
					sb.append("null");
				} else if (item.getClass() == GuitarNote.class) {
					sb.append(((GuitarNote) item).getFret());
					sb.append("\",\"");
					sb.append(item.toString());
					sb.append((j == noteCount - 1 ? "\"}" : "\"},"));
				} else if (item.getClass() == DrumNote.class) {
					sb.append(item.toString());
					sb.append((j == noteCount - 1 ? "\"}" : "\"},"));
				}
				j++;
			}
			sb.append((i == staffCount - 1 ? "}" : "},"));
			sb.append("\n");
			i++;
		}
		sb.append("}");
		return sb.toString();
	}

	/** @return the value of the accumulating measure count */
	public static int getAccumulateMeasure() {
		return accumulateMeasure;
	}

	/**
	 * Accumulating measure count for notes.
	 * 
	 * @param accumulateMeasure the accumulateMeasure to set
	 */
	public static void setAccumulateMeasure(int accumulateMeasure) {
		Score.accumulateMeasure = accumulateMeasure;
	}

	/** @return the value of the accumulating measure count for the score */
	public static int getAccumulateMeasureScore() {
		return accumulateMeasureScore;
	}

	/**
	 * Accumulating measure count for the score.
	 * 
	 * @param accumulateMeasureScore the accumulateMeasureScore to set
	 */
	public static void setAccumulateMeasureScore(int accumulateMeasureScore) {
		Score.accumulateMeasureScore = accumulateMeasureScore;
	}

	/**
	 * A custom {@code Iterator} implementation which iterates the staffs in this
	 * score.
	 */
	private static final class ScoreIterator<T> implements Iterator<T> {
		int index = 0;
		List<T> staffs;

		public ScoreIterator(List<T> staffs) {
			this.staffs = new ArrayList<>(staffs);
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

	/**
	 * A custom {@code Iterator} implementation which iterates the measures in this
	 * score.
	 */
	private static final class MeasureIterator<T> implements Iterator<T> {
		int index = 0;
		List<T> measures;

		public MeasureIterator(List<T> measures) {
			this.measures = new ArrayList<>(measures);
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
