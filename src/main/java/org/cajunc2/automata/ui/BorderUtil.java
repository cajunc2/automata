package org.cajunc2.automata.ui;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

class BorderUtil {

	private BorderUtil() {
		throw new IllegalStateException("Utility class - Do Not Instantiate!");
	}

	/**
	 * Creates a new border from an arbitrary number of existing borders. Useful
	 * for creating deeply nested complex borders.
	 * 
	 * @param borders
	 *            - component borders in order from outermost to innermost
	 */
	public static Border createCompoundBorder(Border... borders) {
		if (borders.length == 0) {
			return BorderFactory.createEmptyBorder();
		}
		if (borders.length == 1) {
			return borders[0];
		}
		if (borders.length == 2) {
			return BorderFactory.createCompoundBorder(borders[0], borders[1]);
		}
		Border result = borders[0];
		for (int i = 1; i < borders.length; i++) {
			result = BorderFactory.createCompoundBorder(result, borders[i]);
		}
		return result;
	}
}
