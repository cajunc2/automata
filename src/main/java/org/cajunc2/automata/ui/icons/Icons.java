package org.cajunc2.automata.ui.icons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A utility class containing common icons for various controls throughout the
 * application. Also contains some utility methods for creating icons from
 * images or solid colors.
 */
public final class Icons {

	public static final Icon RUN = iconFile("control.png");
	public static final Icon PAUSE = iconFile("control-pause.png");
	public static final Icon STEP = iconFile("arrow-step-over.png");
	public static final Icon RANDOM = iconFile("burn.png");
	public static final Icon ALGORITHM = iconFile("function.png");
	public static final Icon PALETTE = iconFile("color-swatches.png");
	public static final Icon MUTATE = iconFile("pill.png");
	public static final Icon CROSS = iconFile("cross.png");
	public static final Icon STATUS_RUNNING = iconFile("status.png");
	public static final Icon STATUS_PAUSED = iconFile("status-busy.png");
	public static final Icon FASTER = iconFile("clock--plus.png");
	public static final Icon SLOWER = iconFile("clock--minus.png");
	public static final Icon ZOOM_IN = iconFile("magnifier-zoom-in.png");
	public static final Icon ZOOM_OUT = iconFile("magnifier-zoom-out.png");

	private Icons() {
		throw new IllegalStateException("You didn't REALLY just try to instantiate a utility class, did you?");
	}

	/**
	 * Creates an ImageIcon with the specified image file
	 * 
	 * @param string
	 *            - The filename for the icon image file
	 */
	public static ImageIcon iconFile(String resourceName) {
		URL iconLocation = Icons.class.getResource(resourceName);
		if (iconLocation == null) {
			throw new IllegalArgumentException("Unable to load icon: " + resourceName);
		}
		return new ImageIcon(iconLocation);
	}

	/**
	 * Creates solid-color icon with the specified color and size
	 * 
	 * @param color
	 *            - The color for the icon
	 * @param size
	 *            - The size of the icon
	 */
	public static Icon solidColor(Color color, Dimension size) {
		return solidColor(color, size.width, size.height);
	}

	/**
	 * Creates solid-color icon with the specified color and size
	 * 
	 * @param color
	 *            - The color for the icon
	 * @param width
	 *            - The width of the icon
	 * @param height
	 *            - The height of the icon
	 */
	public static Icon solidColor(Color color, int width, int height) {
		Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		image.flush();
		return new ImageIcon(image);
	}
}
