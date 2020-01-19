package org.cajunc2.automata.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.algorithm.colors.FlameColorScheme;
import org.cajunc2.automata.algorithm.colors.GrayscaleColorScheme;
import org.cajunc2.automata.algorithm.colors.IceColorScheme;
import org.cajunc2.automata.algorithm.neighborhood.MooreNeighborhood;

public class GenerationsAlgorithm extends AbstractMultithreadedAlgorithm {

	private final int period;
	private final String name;
	private final int[] bValues;
	private final int[] sValues;
	private final ColorScheme defaultColorScheme;
	private final List<ColorScheme> colorSchemeOptions;

	public GenerationsAlgorithm(String sRule, String bRule, int period) {
		super(new MooreNeighborhood());
		this.period = period;

		this.name = "S" + sRule + "/B" + bRule + "/P" + period;

		bValues = new int[bRule.length()];
		for (int i = 0; i < bRule.length(); i++) {
			bValues[i] = Character.getNumericValue(bRule.charAt(i));
		}

		sValues = new int[sRule.length()];
		for (int i = 0; i < sRule.length(); i++) {
			sValues[i] = Character.getNumericValue(sRule.charAt(i));
		}

		this.defaultColorScheme = new GrayscaleColorScheme(period);
		this.colorSchemeOptions = Arrays.asList(defaultColorScheme, new FlameColorScheme(period),
				new IceColorScheme(period));
	}

	@Override
	public void randomize(int[] cells) {
		Random r = new Random();
		for (int i = 0; i < cells.length; i++) {
			cells[i] = r.nextInt(period + 1);
		}
	}

	@Override
	public int draw(int currentValue) {
		return period - 1;
	}

	@Override
	public int erase(int currentValue) {
		return 0;
	}

	@Override
	public ColorScheme getPreferredColorScheme() {
		return defaultColorScheme;
	}

	@Override
	public List<ColorScheme> getColorSchemeOptions() {
		return colorSchemeOptions;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	protected int calculate(int[] surroundingCells, int currentValue) {
		int liveNeighborCount = 0;
		for (int cell : surroundingCells) {
			if (cell == period - 1) {
				liveNeighborCount++;
				continue;
			}
		}

		if (currentValue == period - 1 && Arrays.binarySearch(sValues, liveNeighborCount) >= 0) {
			return period - 1;
		}

		if (currentValue > 0) {
			return currentValue - 1;
		}

		if (Arrays.binarySearch(bValues, liveNeighborCount) >= 0) {
			return period - 1;
		}
		return 0;
	}
}
