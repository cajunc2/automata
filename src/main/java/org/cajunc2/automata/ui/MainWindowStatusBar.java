package org.cajunc2.automata.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.cajunc2.automata.Speed;
import org.cajunc2.automata.ui.icons.Icons;

class MainWindowStatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel algorithmLabel = new JLabel(" ");
	private final JLabel speedLabel = new JLabel("30 gen/sec");
	private final JLabel iterationNumberLabel = new JLabel("0");
	private final JLabel liveCellCountLabel = new JLabel("0");

	public MainWindowStatusBar() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);

		Color highlightColor = UIManager.getLookAndFeelDefaults().getColor("Button.highlight");
		Color shadowColor = UIManager.getLookAndFeelDefaults().getColor("controlDkShadow");
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, shadowColor));
		iterationNumberLabel.setIcon(Icons.STATUS_PAUSED);
		iterationNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		iterationNumberLabel.setIconTextGap(4);
		iterationNumberLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		final Border topLeft = BorderFactory.createMatteBorder(1, 1, 0, 0, shadowColor);
		final Border bottomRight = BorderFactory.createMatteBorder(0, 0, 1, 1, highlightColor);
		final Border padding = BorderFactory.createEmptyBorder(2, 8, 2, 8);
		final Border border = BorderUtil.createCompoundBorder(topLeft, bottomRight, padding);
		final Border iterationPadding = BorderFactory.createEmptyBorder(2, 8, 2, 2);
		final Border iterationBorder = BorderUtil.createCompoundBorder(topLeft, bottomRight, iterationPadding);
		algorithmLabel.setBorder(border);
		speedLabel.setBorder(border);
		iterationNumberLabel.setBorder(iterationBorder);
		liveCellCountLabel.setBorder(border);
		speedLabel.setHorizontalTextPosition(SwingConstants.CENTER);

		c.gridx = 0;
		c.weightx = 3;
		c.insets = new Insets(2, 2, 2, 2);
		add(algorithmLabel, c);
		
		c.gridx = 1;
		c.weightx = 0;
		c.insets = new Insets(2, 0, 2, 0);
		add(speedLabel, c);
		
		c.gridx = 2;
		c.weightx = 0;
		c.insets = new Insets(2, 2, 2, 2);
		add(iterationNumberLabel, c);
	}

	void updateAlgorithmName(String algorithmName) {
		this.algorithmLabel.setText(algorithmName);
	}

	void updateIterationNumber(int iteration) {
		iterationNumberLabel.setText(String.valueOf(iteration));
	}

	void updateSpeed(Speed speed) {
		this.speedLabel.setText(speed.getLabel());
	}

	void updateLiveCellCount(int count) {
		this.speedLabel.setText(String.valueOf(count));
	}

	void updateRunState(boolean running) {
		Icon newIcon = running ? Icons.STATUS_RUNNING : Icons.STATUS_PAUSED;
		iterationNumberLabel.setIcon(newIcon);
	}

}
