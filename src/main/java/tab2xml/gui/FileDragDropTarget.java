package tab2xml.gui;

import static java.util.stream.Collectors.toList;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

final class FileDragDropTarget extends DropTarget {
	private static final long serialVersionUID = -281039257803497320L;
	
	/**
	 * The target of the dragging and dropping
	 */
	private final AbstractSwingView view;
	
	/**
	 * Creates the drag and drop method, targeting {@code view}.
	 *
	 * @param view view to target
	 * @since 2021-02-08
	 */
	public FileDragDropTarget(AbstractSwingView view) {
		this.view = view;
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
			this.view.showErrorMessage(e.getClass() + " occurred.",
					e.getLocalizedMessage());
			return;
		}
		
		// read files
		if (droppedFiles.size() == 1) {
			this.view.presenter.loadFromFile(droppedFiles.get(0));
			this.view.setDefaultDirectory(droppedFiles.get(0));
		} else {
			this.view.showErrorMessage("Wrong number of files.",
					"You can only use one file at a time.");
			return;
		}
	}
}
