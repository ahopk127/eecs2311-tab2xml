package tab2xml.gui;

import tab2xml.parser.Instrument;

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
	 * Gets the text outputted to the user. This method is optional.
	 * 
	 * @implNote This method is only used by the file-writing mechanism.
	 * @since 2021-01-29
	 * @throws UnsupportedOperationException if the implementation does not
	 *                                       support this method
	 */
	String getOutputText();
	
	/**
	 * Gets the instrument of the tab, as selected by the user.
	 *
	 * @since 2021-01-25
	 */
	Instrument getSelectedInstrument();
	
	/**
	 * Sets the view's input text to {@code text}. This method is optional.
	 * 
	 * @implNote This method is only used by the file-reading mechanism.
	 *
	 * @since 2021-01-29
	 * @throws UnsupportedOperationException if the implementation does not
	 *                                       support this method
	 */
	void setInputText(String text);
	
	/**
	 * Sets the output text to {@code text}.
	 *
	 * @since 2021-01-18
	 */
	void setOutputText(String text);
}
