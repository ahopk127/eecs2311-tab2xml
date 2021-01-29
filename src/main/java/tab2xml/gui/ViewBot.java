package tab2xml.gui;

import tab2xml.parser.Instrument;

/**
 * An automated View, whose actions are controlled by the program. Can be used
 * for testing the View and/or Presenter.
 *
 * @since 2021-01-20
 */
public final class ViewBot implements View {
	/** The presenter linked with this View. */
	private final Presenter presenter;
	/**
	 * The text inputted by the user. (represents whatever text area the user is
	 * using to input text)
	 */
	private String inputText;
	/**
	 * The text outputed by the program (represents whatever output the program
	 * gives to the user)
	 */
	private String outputText;
	/**
	 * The instrument selected by the bot.
	 */
	private Instrument selectedInstrument;
	
	/**
	 * Creates the view test.
	 * 
	 * @since 2021-01-20
	 */
	public ViewBot() {
		this.presenter = new Presenter(this);
	}
	
	@Override
	public final String getInputText() {
		return this.inputText;
	}
	
	@Override
	public final String getOutputText() {
		return this.outputText;
	}
	
	/**
	 * @return the presenter associated with this view
	 * @since 2021-01-20
	 */
	public final Presenter getPresenter() {
		return this.presenter;
	}
	
	@Override
	public final Instrument getSelectedInstrument() {
		return this.selectedInstrument;
	}
	
	@Override
	public final void setInputText(String inputText) {
		this.inputText = inputText;
	}
	
	@Override
	public final void setOutputText(String outputText) {
		this.outputText = outputText;
	}
	
	/**
	 * @param selectedInstrument the selectedInstrument to set
	 * @since 2021-01-25
	 */
	public final void setSelectedInstrument(Instrument selectedInstrument) {
		this.selectedInstrument = selectedInstrument;
	}
}
