package org.cajunc2.automata.algorithm.neighborhood;

public class WrappingVonNeumannNeighborhood implements Neighborhood {

	@Override
	public int size() {
		return 4;
	}

	@Override
	public void values(int[] cells, int i, int[] result, int width) {
		result[0] = valueForCell(cells, i - width);
		result[1] = valueForCell(cells, i + width);
		result[2] = valueForCell(cells, i - 1);
		result[3] = valueForCell(cells, i + 1);
	}

	private static int valueForCell(int[] cells, int index) {
		if (index < 0) {
			return cells[index + cells.length];
		}
		if (index >= cells.length) {
			return cells[index - cells.length];
		}
		return cells[index];
	}

}
