package tab2xml.gui;

import tab2xml.parser.Instrument;
import tab2xml.parser.Parser;

/**
 * The Tab2XML presenter, which handles event code. It acts as an intermediate
 * between the front-end (View) and back-end.
 *
 * @since 2021-01-18
 */
public final class Presenter {
	/** The view that this presenter takes input from and sends output to. */
	private final View view;
	
	/**
	 * Creates the presenter. Should be called from the view's constructor.
	 *
	 * @param view view associated with this presenter
	 * @since 2021-01-18
	 */
	public Presenter(View view) {
		this.view = view;
	}
	
	/**
	 * Converts the inputted text tab to MusicXML
	 * 
	 * @since 2021-01-18
	 */
	public void convert() {
		final String textTabInput = this.view.getInputText();
		
		final Parser parser = new Parser(textTabInput, Instrument.GUITAR);
		final String musicXMLOutput = parser.parse();
		
		this.view.setOutputText(musicXMLOutput);
	}
}
