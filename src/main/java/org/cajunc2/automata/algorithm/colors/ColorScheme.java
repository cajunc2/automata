package org.cajunc2.automata.algorithm.colors;

import java.awt.Color;
import java.io.Serializable;

public interface ColorScheme extends Serializable {

	Color getColorForValue(int value);
	
	String getName();

}
