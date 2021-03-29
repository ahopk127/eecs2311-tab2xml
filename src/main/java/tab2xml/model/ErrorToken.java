package tab2xml.model;

/**
 * An error token for invalid inputs when parsing.
 * 
 * @author amir
 */
public class ErrorToken {
	private String data;
	private String message;
	private int start;
	private int stop;
	private int line;
	private int column;

	/**
	 * Construct a default error token.
	 * 
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

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return data;
	}
}