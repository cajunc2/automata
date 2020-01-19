package org.cajunc2.automata.algorithm.neighborhood;

public interface Neighborhood {

	void values(int[] cells, int i, int[] result, int width);
	
	int size();
}
