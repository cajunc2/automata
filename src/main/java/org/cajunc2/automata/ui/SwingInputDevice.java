package org.cajunc2.automata.ui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.cajunc2.automata.Universe;

class SwingInputDevice implements MouseListener, MouseMotionListener, KeyListener {

	private final Universe universe;
	private int scaleFactor;

	public SwingInputDevice(Universe universe, int scaleFactor) {
		this.universe = universe;
		this.scaleFactor = scaleFactor;
	}

	public void setScale(Scale scale) {
		this.scaleFactor = scale.getScaleFactor();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
			drawDot(e.getX(), e.getY());
		}
		if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK) {
			eraseDot(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) {
			drawDot(e.getX(), e.getY());
		}
		if ((e.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK) {
			eraseDot(e.getX(), e.getY());
		}
	}

	public void drawDot(int x, int y) {
		universe.draw(x / scaleFactor, y / scaleFactor);
	}

	public void eraseDot(int x, int y) {
		universe.erase(x / scaleFactor, y / scaleFactor);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_M:
			universe.introduceMutation();
			break;
		case KeyEvent.VK_P:
			universe.pause();
			break;
		case KeyEvent.VK_R:
			universe.randomize();
			break;
		case KeyEvent.VK_C:
			universe.clear();
			break;
		case KeyEvent.VK_OPEN_BRACKET:
			universe.goSlower();
			break;
		case KeyEvent.VK_CLOSE_BRACKET:
			universe.goFaster();
			break;
		case KeyEvent.VK_Q:
		case KeyEvent.VK_ESCAPE:
			universe.end();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Does Nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Does Nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Does Nothing
	}

}
