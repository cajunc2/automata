package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class IceColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;

	public IceColorScheme(int period) {
		super(buildColorArray(period));
	}

	private static Color[] buildColorArray(int period) {
		final Color[] colors = new Color[period];
		final float brightStep = 1.0f / (period - 1);
		for (int i = 0; i < colors.length; i++) {
			float bright = i * brightStep;
			colors[i] = new Color(bright, bright, Math.min(bright * 4, 1.0f));
		}
		return colors;
	}

	@Override
	public String getName() {
		return "Ice";
	}

}
