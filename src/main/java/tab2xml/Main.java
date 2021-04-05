package tab2xml;

import tab2xml.gui.View;

/**
 * The main class of the program, which executes the program and opens up a
 * View.
 *
 * @since 2021-02-28
 */
public class Main {
	/**
	 * The current version of TAB2XML.
	 */
	public static final String PROGRAM_VERSION = "v0.4.0";
	
	/**
	 * Executes the program using the Single Entry View.
	 */
	public static void main(String[] args) {
		View.createView(View.ViewType.TABBED);
	}
}
