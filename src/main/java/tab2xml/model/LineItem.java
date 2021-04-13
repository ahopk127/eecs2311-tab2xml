package tab2xml.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * An abstract object that is on a {@Line} object within a {@Staff}.
 * 
 * @author amir
 */
public abstract class LineItem implements Serializable, Comparable<LineItem> {
	private static final long serialVersionUID = 3470299774150433578L;

	/** The position of this item in the input stream. */
	protected double position;
	/** The column of this item with respect to the start of its line. */
	protected double column;
	/** The line number of this item with respect to its {@code Staff}. */
	protected int lineNum;

	/**
	 * Construct a deep copy clone of a {@code LineItem} using serialization.
	 * 
	 * @param object the object of type {@code LineItem} to deep copy
	 * @return a deep copy of the given <b>object</b>
	 */
	public static Object deepClone(Object object) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** @return the number of characters in this line item */
	public abstract int length();

	/**
	 * @return the column of this line item with respect to the start of its line
	 */
	public double getColumn() {
		return column;
	}

	/**
	 * Set the column attribute of this {@code LineItem}.
	 * 
	 * @param column the value to set
	 */
	public void setColumn(double column) {
		this.column = column;
	}

	/** @return the position of this {@code LineItem} in the input stream */
	public double getPosition() {
		return position;
	}

	/**
	 * Set the position attribute of this {@code LineItem}.
	 * 
	 * @param position the value to set
	 */
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	 * @return the line number which this {@code LineItem} is on with respect to its
	 *         {@code Staff}
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * Set the line number attribute of this {@code LineItem}.
	 * 
	 * @param lineNum the value to set
	 */
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	/** @return the number of notes in this {@code LineItem} */
	public abstract int getNoteCount();

	/** {@inheritDoc} */
	@Override
	public int compareTo(LineItem o) {
		if (this.getColumn() == o.getColumn()) {
			if (this.getLineNum() == o.getLineNum())
				return 0;
			return o.getLineNum() - this.getLineNum();
		}
		return Double.compare(this.getColumn(), o.getColumn());
	}
}
