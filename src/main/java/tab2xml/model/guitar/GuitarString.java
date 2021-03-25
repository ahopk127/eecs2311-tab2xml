package tab2xml.model.guitar;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import tab2xml.model.Line;
import tab2xml.model.LineItem;

/**
 * A representation of a guitar string.
 * 
 * @author amir
 */
public class GuitarString extends Line {
	private static final long serialVersionUID = -5274699295630375450L;
	private int stringNum;

	/**
	 * Construct an empty string(with string number grouped by staffs).
	 */
	public GuitarString() {
		super();
		this.stringNum = getLine();
	}

	/**
	 * Construct an empty string with a specified string number. Since no tuning is
	 * provide, standard tune will be used.
	 * 
	 * @param stringNum the string number of this string.
	 */
	public GuitarString(int stringNum) {
		super(stringNum);
		lineItems.add(new Tune(stringNum));
		this.stringNum = stringNum;
	}

	/**
	 * @return the octave of this line.
	 */
	public String getOctave() {
		return tune().getOctave();
	}

	/**
	 * @return the tune step of this string.
	 */
	public String getTune() {
		return tune().getTune();
	}

	/**
	 * @return the {@code Tune} of this string.
	 */
	public Tune tune() {
		return (Tune) lineItems.get(0);
	}

	/**
	 * Set the tune of this string with a specified tune object.
	 * 
	 * @param tune the {@code Tune} object to set the {@code Tune} of this string.
	 */
	public void setTune(Tune tune) {
		lineItems.set(0, (Tune) LineItem.deepClone(tune));
	}

	/**
	 * @return the string number of this string.
	 */
	public int getStringNum() {
		return stringNum;
	}

	/**
	 * Set the string number of this string.
	 * 
	 * @param stringNum the value to set the string number.
	 */
	public void setStringNum(int stringNum) {
		this.stringNum = stringNum;
	}

	/**
	 * @return a list of notes in this string.
	 */
	@Override
	public Collection<? extends LineItem> getNotes() {
		List<LineItem> notes = new ArrayList<>();

		for (LineItem item : lineItems) {
			if (item.getClass() == Slide.class) {
				Slide sl = (Slide) item;
				notes.addAll(sl.getNotes());

			} else if (item.getClass() == PullOff.class) {
				PullOff po = (PullOff) item;
				notes.addAll(po.getNotes());

			} else if (item.getClass() == HammerOn.class) {
				HammerOn ho = (HammerOn) item;
				notes.addAll(ho.getNotes());

			} else if (item.getClass() == HammerPull.class) {
				HammerPull hp = (HammerPull) item;
				notes.addAll(hp.getNotes());

			} else if (item.getClass() == Harmonic.class) {
				Harmonic h = (Harmonic) item;
				notes.addAll(h.getNotes());

			} else if (item.getClass() == GuitarNote.class) {
				GuitarNote note = (GuitarNote) item;
				notes.add((LineItem) LineItem.deepClone(note));

			} else if (item.getClass() == Bar.class) {
				Bar bar = (Bar) item;
				notes.add((LineItem) LineItem.deepClone(bar));
			}
		}
		return notes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s%s-%d", getTune(), getOctave(), getNoteCount());
	}
}
