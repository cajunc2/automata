package org.cajunc2.automata.algorithm.neighborhood;

public class WrappingMooreNeighborhood extends MooreNeighborhood {

	@Override
	protected int valueForCell(int[] cells, int index) {
		if (index < 0) {
			return cells[index + cells.length];
		}
		if (index >= cells.length) {
			return cells[index - cells.length];
		}
		return cells[index];
	}

}
