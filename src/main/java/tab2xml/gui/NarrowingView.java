package tab2xml.gui;

/**
 * A View that supports narrowing the input down to measures.
 *
 * @since 2021-03-22
 */
public interface NarrowingView extends View {
	/**
	 * @return narrowed measure text
	 * @since 2021-03-22
	 */
	String getNarrowedText();
	
	/**
	 * Sets the narrowed measure text to {@code text}.
	 *
	 * @since 2021-03-22
	 */
	void setNarrowedText(String text);
}
