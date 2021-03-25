package tab2xml.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * An abstract object that is on a single line of a staff.
 * 
 * @author amir
 */
public abstract class LineItem implements Serializable, Comparable<LineItem> {
	private static final long serialVersionUID = 3470299774150433578L;

	/**
	 * Construct a deep copy clone of a {@code LineItem} using serialization.
	 * 
	 * @param object the object of type {@code LineItem} to deep copy.
	 * @return a clone of the specified {@code LineItem} object.
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

	/**
	 * Return the column position of this line item on the staff.
	 * 
	 * @return the position of this line item.
	 */
	public abstract double getPosition();

	/**
	 * Return the string number of this item.
	 * 
	 * @return the line number which this item is on.
	 */
	public abstract int getLineNum();

	/**
	 * Return the number of notes in this line item.
	 * 
	 * @return the number of notes in this item.
	 */
	public abstract int getNoteCount();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(LineItem o) {
		if (this.getPosition() == o.getPosition()) {
			if (this.getLineNum() == o.getLineNum())
				return 0;
			return o.getLineNum() - this.getLineNum();
		}
		return Double.compare(this.getPosition(), o.getPosition());
	}
}
