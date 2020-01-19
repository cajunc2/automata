package org.cajunc2.automata.algorithm;

import org.cajunc2.automata.algorithm.neighborhood.Neighborhood;

public abstract class AbstractAlgorithm implements Algorithm {
	private final Neighborhood neighborhood;
	private final int[] neighbors;

	public AbstractAlgorithm(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		this.neighbors = new int[neighborhood.size()];
	}

	@Override
	public void buildIteration(int[] currentGeneration, int[] nextGeneration, int width) {
		for (int i = 0; i < currentGeneration.length; i++) {
			neighborhood.values(currentGeneration, i, neighbors, width);
			int currentValue = currentGeneration[i];
			nextGeneration[i] = calculate(neighbors, currentValue);
		}
	}

	@Override
	public void reset() {
		// Does nothing by default
	}

	protected abstract int calculate(int[] surroundingCells, int currentValue);

}