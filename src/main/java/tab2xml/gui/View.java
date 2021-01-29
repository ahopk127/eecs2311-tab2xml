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
	 * A type of {@code View}. Does not have to correspond to a specific class.
	 * {@code ViewBot} is currently not included in this enum - it is handled
	 * seperately.
	 *
	 * @since 2021-01-29
	 */
	enum ViewType {
		/**
		 * A GUI view that uses a single text entry for input and output.
		 * 
		 * @since 2021-01-18
		 */
		SINGLE_ENTRY {
			@Override
			View create() {
				return new SingleEntryView();
			}
		},
		/**
		 * A GUI view that uses separate text entries for input and output.
		 * 
		 * @since 2021-01-18
		 */
		DOUBLE_ENTRY {
			@Override
			View create() {
				return new DoubleEntryView();
			}
		};
		
		/**
		 * @return a new {@code View} of the enum instance's specified type.
		 * @since 2021-01-29
		 */
		abstract View create();
	}
	
	/**
	 * Creates a new {@code View}.
	 *
	 * @param type type of view to create
	 * @return newly created {@code View}.
	 * @since 2021-01-29
	 */
	static View createView(ViewType type) {
		return type.create();
	}
	
	/**
	 * Creates a new {@code ViewBot}.
	 * <p>
	 * The {@code ViewBot}'s initial input and output text will be the empty
	 * string, and its initial selected instrument will be {@code null}.
	 *
	 * @return the {@code ViewBot}.
	 * @since 2021-01-29
	 */
	static ViewBot createViewBot() {
		return new ViewBot();
	}
	
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
	
	/**
	 * Sets the view's selected instrument to {@code instrument}. This method is
	 * optional.
	 * 
	 * @implNote This method is currently unused, but is planned to be used in
	 *           the future by instrument auto-detection.
	 * @since 2021-01-29
	 * @throws UnsupportedOperationException if the implementation does not
	 *                                       support this method
	 */
	void setSelectedInstrument(Instrument instrument);
}
