package tab2xml.gui;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import tab2xml.exceptions.ParsingWarning;
import tab2xml.exceptions.UnparseableInputException;
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
		},
		/**
		 * A GUI view that uses seperate text entries in separate tabs for input
		 * and output.
		 * 
		 * @since 2021-03-10
		 */
		TABBED {
			@Override
			View create() {
				return new TabbedView();
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
	 * Enables the system look-and-feel in Swing, if it works.
	 * 
	 * @since 2021-03-10
	 */
	public static void enableSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.err.println("Failed to enable system look-and-feel.");
			e.printStackTrace();
		}
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
	 * <p>
	 * <b>Implementation Note:</b> This method is only used by the file-writing
	 * mechanism.
	 *
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
	 * Runs whenever the backend code returns one or more parsing warning. Should
	 * display the warnings to the user, but should not stop the parsing.
	 *
	 * @param warnings warnings returned by backend code
	 * @since 2021-03-05
	 */
	default void handleParseWarnings(Collection<ParsingWarning> warnings) {
		final String message = warnings.size()
				+ " warnings occured during parsing: \n"
				+ warnings.stream().map(ParsingWarning::toString)
						.collect(Collectors.joining("\n"));
		this.showErrorMessage("Parsing Warning", message);
	}
	
	/**
	 * This method is run when the backend code throws an
	 * {@code UnparseableInputException}. It should highlight the exception to
	 * the user.
	 *
	 * @param error error, with all available details about the parsing.
	 * @since 2021-02-28
	 */
	default void onParseError(UnparseableInputException error) {
		this.showErrorMessage("Input Parsing Error", error.getMessage());
	}
	
	/**
	 * Prompt the user for a filepath, and returns the filepath. If the user
	 * doesn't enter a filepath, returns an empty optional.
	 * 
	 * @param preferredFileType file choosing should prefer files of this type,
	 *                          but the returned file does not have to match this
	 *                          filter. For example, JFileChoosers can simply use
	 *                          the filter.
	 *
	 * @since 2021-02-25
	 */
	Optional<Path> promptForFile(FileNameExtensionFilter preferredFileType);
	
	/**
	 * Sets the view's input text to {@code text}. This method is optional.
	 * 
	 * <p>
	 * <b>Implementation Note:</b> This method is only used by the file-reading
	 * mechanism.
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
	 * <p>
	 * <b>Implementation Note:</b> This method is currently unused, but is
	 * planned to be used in the future by instrument auto-detection.
	 *
	 * @since 2021-01-29
	 * @throws UnsupportedOperationException if the implementation does not
	 *                                       support this method
	 */
	void setSelectedInstrument(Instrument instrument);
	
	/**
	 * Shows an error message.
	 *
	 * @param title   title of error message; on any view that uses an error
	 *                dialog, this should be the title of the error dialog.
	 * @param message error message
	 * @since 2021-02-08
	 */
	void showErrorMessage(String title, String message);
}
