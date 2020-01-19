package org.cajunc2.automata.ui.icons;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.algorithm.colors.IceColorScheme;

public class AppIcon {
	private final ColorScheme cs = new IceColorScheme(3);

	public Image getIcon() {
		Random r = new Random();
		BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < 16; x++) {
			bi.setRGB(x, 0, Color.BLACK.getRGB());
			bi.setRGB(x, 15, Color.BLACK.getRGB());
		}
		for (int y = 1; y < 15; y++) {
			bi.setRGB(0, y, Color.BLACK.getRGB());
			bi.setRGB(15, y, Color.BLACK.getRGB());
		}
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				Color c = cs.getColorForValue(r.nextInt(3));
				int drawX = x * 2 + 1;
				int drawY = y * 2 + 1;
				bi.setRGB(drawX, drawY, c.getRGB());
				bi.setRGB(drawX + 1, drawY, c.getRGB());
				bi.setRGB(drawX, drawY + 1, c.getRGB());
				bi.setRGB(drawX + 1, drawY + 1, c.getRGB());
			}
		}
		
		int bigSize = 256;
		BufferedImage bi2 = new BufferedImage(bigSize, bigSize, BufferedImage.TYPE_INT_ARGB);
		bi2.getGraphics().drawImage(bi, 0, 0, bigSize, bigSize, null);
		return bi2;
	}
}
