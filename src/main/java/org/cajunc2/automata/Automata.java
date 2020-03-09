package org.cajunc2.automata;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.formdev.flatlaf.FlatLightLaf;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.LifeLikeAlgorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.ui.MainWindow;

public class Automata {
	private static final Logger logger = Logger.getLogger(Automata.class.getName());

	public static void main(String[] args) throws Exception {
		FlatLightLaf.install();
		 System.setProperty("sun.java2d.opengl", "True");
		try {
			// setSystemLookAndFeel();
			startApplication();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected exception caught, exiting...", e);
			System.exit(-1);
		}
	}

	private static void startApplication() {
		final int scaleFactor = 1;
		Universe.Size size = new Universe.Size(640, 400);

		Algorithm algorithm = new LifeLikeAlgorithm("23", "3");
		Universe u = new Universe(algorithm, size);

		ColorScheme cs = algorithm.getPreferredColorScheme();
		MainWindow output = new MainWindow(u, scaleFactor, cs);
		u.attachOutputDevice(output);

		u.run();
	}

}
