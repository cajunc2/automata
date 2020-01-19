package org.cajunc2.automata.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.algorithm.colors.RainbowColorScheme;
import org.cajunc2.automata.algorithm.neighborhood.Neighborhood;
import org.cajunc2.automata.algorithm.neighborhood.VonNeumannNeighborhood;

public class CyclicAlgorithm extends AbstractMultithreadedAlgorithm {
	private static final int DEFAULT_PERIOD = 15;
	private final int period;

	public CyclicAlgorithm(Neighborhood neighborhood, int period) {
		super(neighborhood);
		this.period = period;
	}

	public CyclicAlgorithm(Neighborhood neighborhood) {
		this(neighborhood, DEFAULT_PERIOD);
	}

	public CyclicAlgorithm() {
		this(new VonNeumannNeighborhood(), DEFAULT_PERIOD);
	}

	@Override
	protected int calculate(int[] surroundingCells, int currentValue) {
		if (currentValue == period) {
			for (int i : surroundingCells) {
				if (i == 1) {
					return 1;
				}
			}
			return currentValue;
		}

		for (int i : surroundingCells) {
			if (i == currentValue + 1) {
				return i;
			}
		}
		return currentValue;
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
		if (currentValue >= period) {
			return 0;
		}
		return currentValue + 1;
	}

	@Override
	public int erase(int currentValue) {
		if (currentValue == 0) {
			return period - 1;
		}
		return currentValue - 1;
	}

	@Override
	public ColorScheme getPreferredColorScheme() {
		return new RainbowColorScheme();
	}

	@Override
	public List<ColorScheme> getColorSchemeOptions() {
		return Arrays.asList(getPreferredColorScheme());
	}

	@Override
	public String getName() {
		return "Cyclic";
	}

}
