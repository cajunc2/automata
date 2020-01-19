package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class GrayscaleColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;

	public GrayscaleColorScheme(int period) {
		super(buildColorArray(period));
	}

	private static Color[] buildColorArray(int period) {
		final Color[] colors = new Color[period];
		final float brightStep = 1.0f / (period - 1);
		for (int i = 0; i < colors.length; i++) {
			float bright = i * brightStep;
			colors[i] = Color.getHSBColor(0.0f, 0.0f, bright);
		}
		return colors;
	}

	@Override
	public String getName() {
		return "Grayscale";
	}

}
