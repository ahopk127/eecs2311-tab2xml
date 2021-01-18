package tab2xml.gui;

/**
 * A view for the Tab2XML application. This class will control all interaction
 * with the user.
 *
 * @since 2021-01-18
 */
public interface View {
	/**
	 * Gets the text of the text tab inputted by the user. This could be typed
	 * into a text box, or read from a file.
	 *
	 * @since 2021-01-18
	 */
	String getInputText();
	
	/**
	 * Sets the output text to {@code text}.
	 *
	 * @since 2021-01-18
	 */
	void setOutputText(String text);
}
