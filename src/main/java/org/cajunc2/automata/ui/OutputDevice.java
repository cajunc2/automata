package org.cajunc2.automata.ui;

import org.cajunc2.automata.Speed;
import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;

public interface OutputDevice {

	void display(int[] cells);

	void updateIterationNumber(int iteration);

	void updateRunState(boolean running);

	void dispose();

	void changeAlgorithm(Algorithm newAlgorithm);

	void updateSpeed(Speed speed);
	
	void zoomIn();
	
	void zoomOut();

	void setColorScheme(ColorScheme scheme);
}
