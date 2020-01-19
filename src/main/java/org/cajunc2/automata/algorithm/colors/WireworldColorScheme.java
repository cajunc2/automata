package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class WireworldColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;

	public WireworldColorScheme() {
		super(buildColorArray());
	}

	private static Color[] buildColorArray() {
		final Color[] colors = new Color[4];
		colors[0] = Color.BLACK;
		colors[1] = Color.RED;
		colors[2] = Color.CYAN.darker();
		colors[3] = Color.GREEN.darker().darker().darker();
		return colors;
	}

	@Override
	public String getName() {
		return "Wireworld";
	}

}
