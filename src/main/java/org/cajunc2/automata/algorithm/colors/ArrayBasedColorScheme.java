package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;
import java.util.Arrays;

public abstract class ArrayBasedColorScheme implements ColorScheme {

	private static final long serialVersionUID = 1L;
	private final Color[] cellColors;

	public ArrayBasedColorScheme(Color[] cellColors) {
		this.cellColors = new Color[cellColors.length];
		System.arraycopy(cellColors, 0, this.cellColors, 0, cellColors.length);
	}

	@Override
	public final Color getColorForValue(int value) {
		if(value >= cellColors.length) {
			return cellColors[cellColors.length - 1];
		}
		if(value < 0) {
			return cellColors[0];
		}
		return cellColors[value % cellColors.length];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cellColors);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ArrayBasedColorScheme)) {
			return false;
		}
		if (this == obj)
			return true;
		ArrayBasedColorScheme other = (ArrayBasedColorScheme) obj;
		return Arrays.equals(cellColors, other.cellColors);
	}
}
