package org.cajunc2.automata.ui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import org.cajunc2.automata.Universe;
import org.cajunc2.automata.ui.icons.Icons;
import org.cajunc2.automata.ui.menu.AlgorithmMenu;
import org.cajunc2.automata.ui.menu.PaletteMenu;

class MainWindowToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;

	private final Universe universe;
	private final JButton runButton;

	public MainWindowToolbar(final Universe universe) {
		this.universe = universe;
		setFloatable(false);
		Color c = UIManager.getLookAndFeelDefaults().getColor("controlDkShadow");
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, c));

		JButton algorithmButton = createAlgorithmButton();
		add(algorithmButton);

		JButton colorButton = createColorButton();
		add(colorButton);

		addSeparator();

		runButton = createRunButton();
		add(runButton);
		JButton stepButton = createStepButton();
		add(stepButton);

		addSeparator();

		JButton speedDownButton = createSpeedDownButton();
		add(speedDownButton);
		JButton speedUpButton = createSpeedUpButton();
		add(speedUpButton);

		addSeparator();

		JButton zoomOutButton = createZoomOutButton();
		add(zoomOutButton);
		JButton zoomInButton = createZoomInButton();
		add(zoomInButton);

		addSeparator();

		JButton randomizeButton = createRandomizeButton();
		add(randomizeButton);

		JButton mutateButton = createMutateButton();
		add(mutateButton);

		addSeparator();

		JButton clearButton = createClearButton();
		add(clearButton);
	}

	private JButton createMutateButton() {
		JButton mutateButton = new JButton(Icons.MUTATE);
		mutateButton.setFocusable(false);
		mutateButton.setToolTipText("Mutate: introduces a little randomness (M)");
		mutateButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.introduceMutation();
			}
		});
		return mutateButton;
	}

	private JButton createRandomizeButton() {
		JButton randomizeButton = new JButton(Icons.RANDOM);
		randomizeButton.setFocusable(false);
		randomizeButton.setToolTipText("Randomize: fills the universe with randomness (R)");
		randomizeButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.randomize();
			}
		});
		return randomizeButton;
	}

	private JButton createClearButton() {
		JButton clearButton = new JButton(Icons.CROSS);
		clearButton.setFocusable(false);
		clearButton.setToolTipText("Clear the universe (C)");
		clearButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.clear();
			}
		});
		return clearButton;
	}

	private JButton createAlgorithmButton() {
		final JPopupMenu popup = new AlgorithmMenu(universe);
		JButton algorithmButton = new JButton(Icons.ALGORITHM);
		algorithmButton.setFocusable(false);
		algorithmButton.setToolTipText("Choose a different cellular automaton");
		algorithmButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				Rectangle b = e.getComponent().getBounds();
				popup.show(e.getComponent(), 0, b.height);
			}
		});
		return algorithmButton;
	}

	private JButton createColorButton() {
		final PaletteMenu popup = new PaletteMenu(universe);
		JButton algorithmButton = new JButton(Icons.PALETTE);
		algorithmButton.setFocusable(false);
		algorithmButton.setToolTipText("Choose a different cellular automaton");
		algorithmButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				popup.refresh();
				Rectangle b = e.getComponent().getBounds();
				popup.show(e.getComponent(), 0, b.height);
			}
		});
		return algorithmButton;
	}

	private JButton createRunButton() {
		JButton button = new JButton(Icons.RUN);
		button.setFocusable(false);
		button.setToolTipText("Run/pause simulation (P)");
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.pause();
			}
		});
		return button;
	}

	private JButton createStepButton() {
		JButton button = new JButton(Icons.STEP);
		button.setFocusable(false);
		button.setToolTipText("Advance one generation and stop");
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.step();
			}
		});
		return button;
	}

	private JButton createSpeedUpButton() {
		JButton button = new JButton(Icons.FASTER);
		button.setFocusable(false);
		button.setToolTipText("Increase speed (])");
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.goFaster();
			}
		});
		return button;
	}

	private JButton createSpeedDownButton() {
		JButton button = new JButton(Icons.SLOWER);
		button.setFocusable(false);
		button.setToolTipText("Decrease speed ([)");
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				universe.goSlower();
			}
		});
		return button;
	}

	private JButton createZoomOutButton() {
		JButton button = new JButton(Icons.ZOOM_OUT);
		button.setFocusable(false);
		button.setToolTipText("Zoom out (-)");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				universe.zoomIn();				
			}
		});
		return button;
	}

	private JButton createZoomInButton() {
		JButton button = new JButton(Icons.ZOOM_IN);
		button.setFocusable(false);
		button.setToolTipText("Zoom in (=)");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				universe.zoomOut();
			}
		});
		return button;
	}

	public void updateRunState(boolean running) {
		Icon newIcon = running ? Icons.PAUSE : Icons.RUN;
		runButton.setIcon(newIcon);
	}
}
