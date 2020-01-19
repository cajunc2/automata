package org.cajunc2.automata.algorithm;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.algorithm.colors.LifeAgeColorScheme;
import org.cajunc2.automata.algorithm.colors.MonochromeColorScheme;
import org.cajunc2.automata.algorithm.neighborhood.WrappingMooreNeighborhood;

public class LifeLikeAlgorithm extends AbstractMultithreadedAlgorithm {
	private final String name;
	private final int[] bValues;
	private final int[] sValues;

	private static final ColorScheme DEFAULT_PALETTE = new LifeAgeColorScheme();

	public LifeLikeAlgorithm(String sRule, String bRule) {
		super(new WrappingMooreNeighborhood());

		this.name = "S" + sRule + "/B" + bRule;

		bValues = new int[bRule.length()];
		for (int i = 0; i < bRule.length(); i++) {
			bValues[i] = Character.getNumericValue(bRule.charAt(i));
		}
		sValues = new int[sRule.length()];
		for (int i = 0; i < sRule.length(); i++) {
			sValues[i] = Character.getNumericValue(sRule.charAt(i));
		}
		Arrays.sort(bValues);
		Arrays.sort(sValues);
	}

	@Override
	protected int calculate(int[] surroundingCells, int currentValue) {
		int count = 0;
		for (int cell : surroundingCells) {
			if (cell != 0) {
				count++;
			}
		}

		if (currentValue == 0) {
			if (Arrays.binarySearch(bValues, count) >= 0) {
				return 1;
			}
			return 0;
		}

		if (Arrays.binarySearch(sValues, count) >= 0) {
			return currentValue + 1;
		}
		return 0;
	}

	@Override
	public void randomize(int[] cells) {
		Random r = new Random();
		for (int i = 0; i < cells.length; i++) {
			cells[i] = r.nextInt(2);
		}
	}

	@Override
	public int draw(int currentValue) {
		return 1;
	}

	@Override
	public int erase(int currentValue) {
		return 0;
	}

	@Override
	public ColorScheme getPreferredColorScheme() {
		return DEFAULT_PALETTE;
	}

	@Override
	public List<ColorScheme> getColorSchemeOptions() {
		return Arrays.asList(DEFAULT_PALETTE, new MonochromeColorScheme(Color.WHITE, "White"),
				new MonochromeColorScheme(Color.RED, "Red"), new MonochromeColorScheme(Color.ORANGE, "Orange"),
				new MonochromeColorScheme(Color.YELLOW, "Yellow"), new MonochromeColorScheme(Color.GREEN, "Green"),
				new MonochromeColorScheme(Color.BLUE, "Blue"),
				new MonochromeColorScheme(new Color(128, 0, 255), "Violet"),
				new MonochromeColorScheme(new Color(244, 0, 161), "Pink"));
	}

	@Override
	public String getName() {
		return this.name;
	}

}
