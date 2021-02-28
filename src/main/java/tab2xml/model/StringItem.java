package tab2xml.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class StringItem implements Serializable, Comparable<StringItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470299774150433578L;

	/**
	 * Construct a deep copy clone of a {@code StringItem} using serialization.
	 * 
	 * @param object the object of type {@code StringItem} to deep copy
	 * @return
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
	 * Return the column position of this string item on the staff.
	 * 
	 * @return the position of this string item
	 */
	public abstract int getPosition();

	/**
	 * Return the string number of this item.
	 * 
	 * @return the string which this item is on
	 */
	public abstract int getStringNum();

	
	/**
	 *{@inheritDoc}
	 */
	@Override
	public int compareTo(StringItem o) {
		if (this.getPosition() == o.getPosition()) {
			if (this.getStringNum() == o.getStringNum())
				return 0;
			else {
				return this.getStringNum() - o.getStringNum();
			}
		} else
			return this.getPosition() - o.getPosition();
	}

}
