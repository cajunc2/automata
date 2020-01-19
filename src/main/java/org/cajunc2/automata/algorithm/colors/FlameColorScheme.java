package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class FlameColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;

	public FlameColorScheme(int period) {
		super(buildColorArray(period));
	}

	private static Color[] buildColorArray(int period) {
		final Color[] colors = new Color[period];
		final float brightStep = 1.0f / (period - 1);
		for (int i = 0; i < colors.length; i++) {
			float bright = i * brightStep;
			colors[i] = new Color(Math.min(bright * 3, 1.0f), bright * 0.85f, 0.0f);
		}
		return colors;
	}

	@Override
	public String getName() {
		return "Flame";
	}

}
