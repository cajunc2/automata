package org.cajunc2.automata.ui;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.cajunc2.automata.Speed;
import org.cajunc2.automata.ui.icons.Icons;

class MainWindowStatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel algorithmLabel = new JLabel(" ");
	private final JLabel speedLabel = new JLabel("30 gen/sec");
	private final JLabel iterationNumberLabel = new JLabel("0");

	public MainWindowStatusBar() {
		SpringLayout layout = new SpringLayout();

		iterationNumberLabel.setIcon(Icons.STATUS_PAUSED);
		iterationNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		iterationNumberLabel.setIconTextGap(4);
		iterationNumberLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		speedLabel.setHorizontalTextPosition(SwingConstants.CENTER);

		layout.putConstraint(SpringLayout.WEST, algorithmLabel, 2, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, iterationNumberLabel, 2, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, speedLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);

		layout.putConstraint(SpringLayout.SOUTH, algorithmLabel, 2, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, iterationNumberLabel, 2, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, speedLabel, 2, SpringLayout.SOUTH, this);

		// setLayout(layout);
		add(algorithmLabel);
		add(speedLabel);
		add(iterationNumberLabel);
		System.out.println(this.getSize());
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
