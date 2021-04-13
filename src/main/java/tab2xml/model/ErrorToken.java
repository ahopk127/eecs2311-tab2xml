package tab2xml.model;

import java.util.Objects;

/**
 * An error token for invalid inputs when parsing.
 * 
 * @author amir
 */
public class ErrorToken implements Comparable<ErrorToken> {
	private String data;
	private String message;
	private int start;
	private int stop;

	/**
	 * Construct an empty error token.
	 */
	public ErrorToken() {
	}

	/**
	 * Construct an error token with it's data.
	 * 
	 * @param data the contents of this error token
	 */
	public ErrorToken(String data) {
		this.data = data;
	}

	/**
	 * Construct an error token with specified attributes.
	 * 
	 * @param message the message describing this error token
	 * @param data    the value of this error token
	 * @param start   the index of the first character of this error token in the input
	 *                stream
	 * @param stop    the index of the last character of this error token in the input
	 *                stream
	 */
	public ErrorToken(String message, String data, int start, int stop) {
		this(data);
		this.message = message;
		this.start = start;
		this.stop = stop;
	}

	/**
	 * Get the data held by this error token.
	 * 
	 * @return the value of this error token
	 */
	public String getData() {
		return data;
	}

	/**
	 * Set the data of this token.
	 * 
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Return the message contents of this error token.
	 * 
	 * @return the message description of this error token
	 */
	public String getMesage() {
		return message;
	}

	/**
	 * Set the message of this error token.
	 * 
	 * @param message a description of this error token
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Return the start index of this error token in the input.
	 * 
	 * @return the start index of this error token token in the input stream
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Set the starting index of this error token in the input stream.
	 * 
	 * @param start the value to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * Return the stop index of this error token in the input.
	 * 
	 * @return the stop index of this error token token in the input stream
	 */
	public int getStop() {
		return stop;
	}

	/**
	 * Set the last index of this error token in the input stream.
	 * 
	 * @param stop the value to set
	 */
	public void setStop(int stop) {
		this.stop = stop;
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorToken other = (ErrorToken) obj;
		if (this.getStart() == other.getStart() && this.getData().equals(other.getData()))
			return true;
		return false;
	}

	@Override
	public int compareTo(ErrorToken o) {
		return this.getStart() - o.getStart();
	}

	@Override
	public String toString() {
		return data;
	}

}