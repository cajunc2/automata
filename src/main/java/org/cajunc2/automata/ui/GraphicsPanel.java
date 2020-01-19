package org.cajunc2.automata.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import javax.swing.JComponent;

class GraphicsPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	private final Dimension size;
	private int drawWidth;
	private int drawHeight;

	private final transient BufferedImage image;
	private final int[] drawBuffer;

	public GraphicsPanel(Dimension size, int scaleFactor) {
		super();
		this.size = size;
		this.drawWidth = size.width * scaleFactor;
		this.drawHeight = size.height * scaleFactor;
		setIgnoreRepaint(true);
		GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		this.image = config.createCompatibleImage(size.width, size.height, Transparency.OPAQUE);
		DataBufferInt buffer = (DataBufferInt) image.getRaster().getDataBuffer();
		this.drawBuffer = buffer.getData();

		Arrays.fill(drawBuffer, 0);
		setPreferredSize(new Dimension(drawWidth, drawHeight));
	}

	public GraphicsPanel(Dimension size) {
		this(size, 1);
	}

	public void setRGB(int x, int y, int rgb) {
		int index = y * size.width + x;
		drawBuffer[index] = rgb;
	}

	public void setRGB(int index, int rgb) {
		drawBuffer[index] = rgb;
	}
	
	public void setAll(int[] data) {
		if(data.length != drawBuffer.length) {
			throw new IllegalArgumentException("Arrays must be same length");
		}
		System.arraycopy(data, 0, drawBuffer, 0, drawBuffer.length);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g.drawImage(image, 0, 0, drawWidth, drawHeight, null);
	}

	public void setScale(Scale newScale) {
		this.drawWidth = size.width * newScale.getScaleFactor();
		this.drawHeight = size.height * newScale.getScaleFactor();
		setPreferredSize(new Dimension(drawWidth, drawHeight));
	}

}
