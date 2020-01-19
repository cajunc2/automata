package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class RainbowColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_PERIOD = 15;

	public RainbowColorScheme(int period) {
		super(buildColorArray(period));
	}

	public RainbowColorScheme() {
		this(DEFAULT_PERIOD);
	}

	private static Color[] buildColorArray(int period) {
		final Color[] colors = new Color[period];
		final float hueStep = 1.0f / period;
		for (int i = 0; i < colors.length; i++) {
			float hue = i * hueStep;
			colors[i] = Color.getHSBColor(hue, 1.0f, 1.0f);
		}
		return colors;
	}

	@Override
	public String getName() {
		return "Rainbow";
	}

}
