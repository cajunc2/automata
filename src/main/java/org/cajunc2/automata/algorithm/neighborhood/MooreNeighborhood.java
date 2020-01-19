package org.cajunc2.automata.algorithm.neighborhood;

public class MooreNeighborhood implements Neighborhood {

	@Override
	public void values(int[] cells, int i, int[] result, int width) {
		result[0] = valueForCell(cells, i - width - 1);
		result[1] = valueForCell(cells, i - width);
		result[2] = valueForCell(cells, i - width + 1);
		result[3] = valueForCell(cells, i - 1);
		result[4] = valueForCell(cells, i + 1);
		result[5] = valueForCell(cells, i + width - 1);
		result[6] = valueForCell(cells, i + width);
		result[7] = valueForCell(cells, i + width + 1);
	}

	protected int valueForCell(int[] cells, int index) {
		if (index < 0) {
			return 0;
		}
		if (index >= cells.length) {
			return 0;
		}
		return cells[index];
	}

	@Override
	public int size() {
		return 8;
	}

}
