package tab2xml.gui;

import tab2xml.parser.Instrument;

/**
 * An automated View, whose actions are controlled by the program. Can be used
 * for testing the View and/or Presenter.
 *
 * @since 2021-01-20
 */
public final class ViewBot implements View {
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
	 * Creates a {@code ViewBot}. The initial input and output text will be the
	 * empty string, while the initial selected instrument will be {@code null}.
	 * 
	 * @since 2021-01-29
	 */
	ViewBot() {
		this.inputText = "";
		this.outputText = "";
		this.selectedInstrument = null;
	}
	
	@Override
	public final String getInputText() {
		return this.inputText;
	}
	
	@Override
	public final String getOutputText() {
		return this.outputText;
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
	
	@Override
	public final void setSelectedInstrument(Instrument selectedInstrument) {
		this.selectedInstrument = selectedInstrument;
	}
}
