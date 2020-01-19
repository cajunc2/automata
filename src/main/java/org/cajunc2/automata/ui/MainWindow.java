package org.cajunc2.automata.ui;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.cajunc2.automata.Speed;
import org.cajunc2.automata.Universe;
import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.ui.icons.AppIcon;

public class MainWindow implements OutputDevice {

	private final JFrame frame = new JFrame("Automata");
	private final UniversePanel panel;
	private final MainWindowStatusBar statusBar = new MainWindowStatusBar();
	private final MainWindowToolbar toolbar;

	private SwingInputDevice input;
	private Scale displayScale = Scale.ACTUAL;

	public MainWindow(Universe universe, int scaleFactor, ColorScheme colorScheme) {
		frame.setIconImage(new AppIcon().getIcon());
		panel = new UniversePanel(universe.getSize(), scaleFactor, colorScheme);
		toolbar = new MainWindowToolbar(universe);

		frame.setLayout(new BorderLayout());

		frame.add(toolbar, BorderLayout.NORTH);
		frame.add(statusBar, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		Point p = WindowPositionUtil.idealPosition(frame);
		frame.setLocation(p);
		frame.setVisible(true);

		input = new SwingInputDevice(universe, scaleFactor);
		panel.addMouseMotionListener(input);
		panel.addMouseListener(input);
		frame.addKeyListener(input);
	}

	public MainWindow(Universe universe, ColorScheme colorScheme) {
		this(universe, 1, colorScheme);
	}

	@Override
	public void display(int[] cells) {
		panel.display(cells);
	}

	@Override
	public void dispose() {
		panel.removeMouseListener(input);
		panel.removeMouseMotionListener(input);
		this.frame.removeKeyListener(input);
		this.frame.setVisible(false);
		this.frame.dispose();
	}

	@Override
	public void updateIterationNumber(int iteration) {
		statusBar.updateIterationNumber(iteration);
	}

	@Override
	public void updateRunState(boolean running) {
		statusBar.updateRunState(running);
		toolbar.updateRunState(running);
	}

	@Override
	public void updateSpeed(Speed speed) {
		statusBar.updateSpeed(speed);
	}

	@Override
	public void changeAlgorithm(Algorithm newAlgorithm) {
		statusBar.updateAlgorithmName(newAlgorithm.getName());
		panel.changeColorScheme(newAlgorithm.getPreferredColorScheme());
	}
	
	@Override
	public void setColorScheme(ColorScheme scheme) {
		panel.changeColorScheme(scheme);
	}

	@Override
	public void zoomIn() {
		displayScale = displayScale.larger();
		input.setScale(displayScale);
		panel.setScale(displayScale);
		this.frame.pack();
	}

	@Override
	public void zoomOut() {
		displayScale = displayScale.smaller();
		input.setScale(displayScale);
		panel.setScale(displayScale);
		this.frame.pack();
	}

}
