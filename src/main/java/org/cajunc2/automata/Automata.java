package org.cajunc2.automata;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.LifeLikeAlgorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.ui.MainWindow;

public class Automata {
	private static final Logger logger = Logger.getLogger(Automata.class.getName());
	public static final boolean DARK_MODE;
	static {
		DARK_MODE = isMacMenuBarDarkMode();
	}

	public static void main(String[] args) throws Exception {
		if (DARK_MODE) {
			FlatDarkLaf.install();
		} else {
			FlatLightLaf.install();
		}
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

	/**
	 * @return true if <code>defaults read -g AppleInterfaceStyle</code> has an exit
	 *         status of <code>0</code> (i.e. _not_ returning "key not found").
	 */
	private static boolean isMacMenuBarDarkMode() {
		try {
			// check for exit status only. Once there are more modes than "dark" and
			// "default", we might need to analyze string contents..
			final Process proc = Runtime.getRuntime()
					.exec(new String[] { "defaults", "read", "-g", "AppleInterfaceStyle" });
			proc.waitFor(100, TimeUnit.MILLISECONDS);
			return proc.exitValue() == 0;
		} catch (Exception ex) {
			// IllegalThreadStateException thrown by proc.exitValue(), if process didn't
			// terminate
			System.err.println(
					"Could not determine, whether 'dark mode' is being used. Falling back to default (light) mode.");
			return false;
		}
	}

}
