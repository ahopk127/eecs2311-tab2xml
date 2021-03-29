package tab2xml.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * An abstract object that is within a staff.
 * 
 * @author amir
 */
public abstract class ScoreItem<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = -2287885769489399982L;

    /**
     * Construct a deep copy clone of a {@code StaffItem} using serialization.
     * 
     * @param object the object of type {@code StaffItem} to deep copy
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
     * @return get the total notes in this staff item.
     */
    public abstract int getNoteCount();
}
