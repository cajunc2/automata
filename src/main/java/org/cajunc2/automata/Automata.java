package org.cajunc2.automata;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.LifeLikeAlgorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.ui.MainWindow;

public class Automata {
	private static final Logger logger = Logger.getLogger(Automata.class.getName());

	public static void main(String[] args) throws Exception {
		 System.setProperty("sun.java2d.opengl", "True");
		try {
			setSystemLookAndFeel();
			startApplication();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Unexpected exception caught, exiting...", e);
			System.exit(-1);
		}
	}

	private static void setSystemLookAndFeel() {
		String lafName = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lafName);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			// Unable to set any particular LAF. Java will go ahead
			// and use whatever it's got, so this isn't fatal, just ugly :)
		}
	}

	private static void startApplication() {
		final int scaleFactor = 1;
		Universe.Size size = new Universe.Size(480, 320);

		Algorithm algorithm = new LifeLikeAlgorithm("23", "3");
		Universe u = new Universe(algorithm, size);

		ColorScheme cs = algorithm.getPreferredColorScheme();
		MainWindow output = new MainWindow(u, scaleFactor, cs);
		u.attachOutputDevice(output);

		u.run();
	}

}
