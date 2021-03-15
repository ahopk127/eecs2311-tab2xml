package tab2xml.gui;

import static java.util.stream.Collectors.toList;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

final class FileDragDropTarget extends DropTarget {
	private static final long serialVersionUID = -281039257803497320L;
	
	/**
	 * The target of the dragging and dropping
	 */
	private final JTextComponent target;
	
	/**
	 * Creates the drag and drop method, targeting {@code target}.
	 *
	 * @param target
	 * @since 2021-02-08
	 */
	public FileDragDropTarget(JTextComponent target) {
		this.target = target;
	}
	
	@Override
	public synchronized void drop(DropTargetDropEvent event) {
		event.acceptDrop(DnDConstants.ACTION_COPY);
		
		// get a list of the files that were dragged and dropped into the text
		// area.
		final List<Path> droppedFiles;
		try {
			// Using DataFlavor.javaFileListFlavor as an argument guarantees the
			// runtime type of result will be List, and all its elements will be
			// instances of File. Therefore, this cast will never cause an error.
			@SuppressWarnings("unchecked")
			final List<File> result = (List<File>) event.getTransferable()
					.getTransferData(DataFlavor.javaFileListFlavor);
			
			// convert File to the more useful Path
			droppedFiles = result.stream().map(File::toPath).collect(toList());
		} catch (final IOException | UnsupportedFlavorException e) {
			e.printStackTrace();
			this.showErrorMessage(e.getClass() + " occurred.",
					e.getLocalizedMessage());
			return;
		}
		
		// read files
		if (droppedFiles.size() == 1) {
			final Path droppedFile = droppedFiles.get(0);
			try {
				this.target.setText(
						Files.readString(droppedFile).replaceAll("\\r\\n", "\n"));
			} catch (final IOException e) {
				e.printStackTrace();
				this.showErrorMessage("I/O Error",
						"Error reading file: " + e.getLocalizedMessage());
				return;
			}
		} else {
			this.showErrorMessage("Wrong number of files.",
					"You can only use one file at a time.");
			return;
		}
	}
	
	/**
	 * Shows an error message.
	 *
	 * @since 2021-02-08
	 */
	private final void showErrorMessage(String title, String message,
			Object... args) {
		final JRootPane pane = this.target.getRootPane();
		
		JOptionPane.showMessageDialog(pane, String.format(message, args), title,
				JOptionPane.ERROR_MESSAGE);
	}
}
