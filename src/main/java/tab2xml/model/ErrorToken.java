package tab2xml.model;

/**
 * An atomic token in the ASCII tablature
 * 
 * @author amir
 */
public class ErrorToken {
	private String data;
	private String message;
	private int start;
	private int stop;
	
	/**
	 * Construct a default error token.
	 * 
	 * @param type the type of this error token
	 */
	public ErrorToken() {
		data = "";
		start = 0;
		stop = 0;
	}

	/**
	 * Construct an error token with it's data.
	 * 
	 * @param data the contents of this error token
	 */
	public ErrorToken(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getMesage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	/**
	 * Return this value of this error token.
	 * 
	 * @return this error token's value
	 */
	@Override
	public String toString() {
		return data;
	}
}