package tab2xml.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;

public class TabHighlightPainter extends DefaultHighlightPainter {

	public TabHighlightPainter(Color c) {
		super(c);
	}

	@Override
	public void paint(Graphics g, int p0, int p1, Shape bounds, JTextComponent c) {
		super.paint(g, p0, p1, bounds, c);
	}

	@Override
	public Shape paintLayer(Graphics g, int p0, int p1, Shape viewBounds, JTextComponent editor, View view) {
		return super.paintLayer(g, p0, p1, viewBounds, editor, view);
	}
}
