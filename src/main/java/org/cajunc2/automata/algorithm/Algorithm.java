package org.cajunc2.automata.algorithm;

import java.util.List;

import org.cajunc2.automata.algorithm.colors.ColorScheme;

public interface Algorithm {

	/**
	 * Returns an algorithm iteration that will compute the next generation of
	 * cell values for the given universe.
	 */
	void buildIteration(int[] currentGeneration, int[] nextGeneration, int width);

	/**
	 * Returns a universe that has been randomized with an algoritm appropriate
	 * to this type of automaton.
	 */
	void randomize(int[] cells);

	/**
	 * Returns the new value for this cell after a draw operation.
	 */
	int draw(int currentValue);

	/**
	 * Returns the new value for this cell after an erase operation.
	 */
	int erase(int currentValue);

	/**
	 * Returns a {@link ColorScheme} that is particularly suited for visualizing
	 * this algorithm.
	 */
	ColorScheme getPreferredColorScheme();
	
	/**
	 * Returns a {@link ColorScheme} that is particularly suited for visualizing
	 * this algorithm.
	 */
	List<ColorScheme> getColorSchemeOptions();
	
	/**
	 * Returns a user-friendly display name for this algorithm.
	 */
	String getName();

	/**
	 * Resets this algorithm to a pre-defined start state.
	 */
	void reset();
}
