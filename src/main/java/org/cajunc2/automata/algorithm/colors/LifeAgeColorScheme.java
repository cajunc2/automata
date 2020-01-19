package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;

public class LifeAgeColorScheme extends ArrayBasedColorScheme {

	private static final long serialVersionUID = 1L;
	private static final int STEPS = 50;

	public LifeAgeColorScheme() {
		super(buildColorArray());
	}

	private static Color[] buildColorArray() {
		Color[] colors = new Color[STEPS];
		colors[0] = Color.BLACK;
		colors[1] = Color.WHITE;

		float increment = 0.5f / (STEPS - 2);
		float val = 1.0f;
		for (int i = 2; i < STEPS; i++) {
			val -= increment;
			colors[i] = Color.getHSBColor(0, 0, val);
		}
		return colors;
	}

	@Override
	public String getName() {
		return "Life Age";
	}

}
