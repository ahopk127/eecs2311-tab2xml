package tab2xml.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A background panel used in the TabbedView.
 *
 * @since 2021-03-12
 */
public final class BackgroundPanel extends JPanel {
	/** Space allowed aronud the main app for the background. */
	private static final Insets DEFAULT_INSETS = new Insets(80, 200, 80, 200);
	
	private static final long serialVersionUID = 7022962804082734271L;
	
	/** The font used for the title. */
	private static final Font TITLE_FONT = new Font("Agency FB", Font.PLAIN, 60);
	
	/** The font used for the tab/XML on the sides. */
	private static final Font TAB_XML_FONT = new Font("Courier", Font.BOLD, 30);
	
	/** The title string. */
	private static final String TITLE = "TAB2XML";
	
	/**
	 * Gets the bounds of a string if it were drawn.
	 *
	 * @param toDraw string to draw
	 * @param font   font to use
	 * @since 2021-03-12
	 */
	private static final Rectangle2D stringBounds(String toDraw, Font font) {
		return font.getStringBounds(toDraw,
				new FontRenderContext(null, true, true));
	}
	
	/**
	 * 
	 * @since 2021-03-12
	 */
	public BackgroundPanel() {
		this.setLayout(new GridBagLayout());
	}
	
	public BackgroundPanel(JComponent comp) {
		this();
		
		// put component on background
		final GridBagConstraints gbc = new GridBagConstraints();
		super.add(comp, gbc);
		
		// set preferred component settings
		comp.setOpaque(false);
		comp.setBorder(new EmptyBorder(DEFAULT_INSETS));
	}
	
	/**
	 * Adds the component with the {@code BackgroundPanel}'s default layout.
	 *
	 * @see #add(Component)
	 * @since 2021-03-12
	 */
	@Override
	public Component add(Component comp) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = (Insets) DEFAULT_INSETS.clone();
		super.add(comp, gbc);
		return comp;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		
		// background
		final Dimension size = this.getSize();
		final int width = (int) size.getWidth();
		final int height = (int) size.getHeight();
		
		// top half
		final Color cyan = new Color(0x00, 0xE8, 0xFF);
		final Color blue = new Color(0x55, 0x99, 0xFF);
		final Point2D start = new Point2D.Double(0, 0);
		final Point2D end = new Point2D.Double(width, height / 2);
		
		final GradientPaint gradient = new GradientPaint(start, cyan, end, blue);
		g2.setPaint(gradient);
		g2.fillRect(0, 0, width, height / 2);
		
		// bottom half
		g2.setColor(Color.DARK_GRAY);
		g2.fillRect(0, height / 2, width, height / 2);
		
		g2.setColor(Color.WHITE);
		// text
		final Rectangle2D titleBounds = stringBounds(TITLE, TITLE_FONT);
		g2.setFont(TITLE_FONT);
		g2.drawString(TITLE, (int) (width / 2 - titleBounds.getWidth() / 2),
				(int) titleBounds.getHeight() - 5);
		
		final String tabExample = "|---0---1---|---6---";
		final Rectangle2D tabBounds = stringBounds(tabExample, TAB_XML_FONT);
		g2.setFont(TAB_XML_FONT);
		
		g2.setColor(Color.WHITE);
		g2.drawString(tabExample, 5, height / 2 - 15);
		g2.setColor(blue);
		g2.drawString(tabExample, 5,
				height / 2 - 5 + (int) tabBounds.getHeight());
		
		final String xmlExample = "<?xml version=\"1.0\" encoding=\"UTF-8\">";
		final Rectangle2D xmlBounds = stringBounds(xmlExample, TAB_XML_FONT);
		
		g2.setColor(Color.WHITE);
		g2.drawString(xmlExample, width - 5 - (int) xmlBounds.getWidth(),
				height / 2 - 15);
		g2.setColor(blue);
		g2.drawString(xmlExample, width - 5 - (int) xmlBounds.getWidth(),
				height / 2 - 5 + (int) xmlBounds.getHeight());
	}
}
