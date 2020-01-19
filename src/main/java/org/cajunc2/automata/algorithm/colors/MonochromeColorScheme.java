package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class MonochromeColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;
	private final String name;

	public MonochromeColorScheme(Color foreground, String name) {
		super(buildColorArray(foreground, Color.BLACK));
		this.name = name;
	}

	public MonochromeColorScheme(Color foreground, Color background, String name) {
		super(buildColorArray(foreground, background));
		this.name = name;
	}

	private static Color[] buildColorArray(Color foreground, Color background) {
		final Color[] colors = new Color[2];
		colors[0] = background;
		colors[1] = foreground;
		return colors;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
