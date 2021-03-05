package tab2xml.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

final class BackgroundImagePanel extends JPanel {
	private static final long serialVersionUID = 4927837888049603051L;
	
	private final Image img;
	
	/**
	 * 
	 * @since 2021-03-03
	 */
	public BackgroundImagePanel(Image img) {
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.img, 0, 0, null);
	}
}
