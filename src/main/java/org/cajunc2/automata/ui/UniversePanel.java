package org.cajunc2.automata.ui;

import java.awt.Color;
import java.awt.Dimension;

import org.cajunc2.automata.Universe;
import org.cajunc2.automata.algorithm.colors.ColorScheme;

class UniversePanel extends GraphicsPanel {

	private static final long serialVersionUID = 1L;
	private ColorScheme colorScheme;

	public UniversePanel(Universe.Size size, int scaleFactor, ColorScheme colorScheme) {
		super(new Dimension(size.width, size.height), scaleFactor);
		setBackground(Color.BLACK);
		this.colorScheme = colorScheme;
	}

	public UniversePanel(Universe.Size size, ColorScheme colorScheme) {
		this(size, 1, colorScheme);
	}

	public void display(int[] cells) {
		for (int i = 0; i < cells.length; i++) {
			Color c = colorScheme.getColorForValue(cells[i]);
			setRGB(i, c.getRGB());
		}
		repaint();
	}

	public void changeColorScheme(ColorScheme newScheme) {
		this.colorScheme = newScheme;
		repaint();
	}

}
