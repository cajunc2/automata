package org.cajunc2.automata.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.GenerationsAlgorithm;

class GenerationsAlgorithmMenuItem extends JCheckBoxMenuItem {
	private final AlgorithmMenu algorithmMenu;
	private static final long serialVersionUID = 1L;
	private final Pattern rulePattern = Pattern.compile("[Ss]?([0-9]*)/[Bb]?([0-9]*)/[Pp]?([0-9]*)");
	private final String rule;

	public GenerationsAlgorithmMenuItem(AlgorithmMenu algorithmMenu) {
		this(algorithmMenu, "Generations", null);
	}

	public GenerationsAlgorithmMenuItem(AlgorithmMenu algorithmMenu, String title, final String rule) {
		super(title);
		this.algorithmMenu = algorithmMenu;
		this.rule = rule;
		addActionListener(new GenerationsAlgorithmMenuItemActionListener());
	}

	private void setAlgorithm() {
		String newRule = this.rule;
		if (newRule == null) {
			newRule = JOptionPane.showInputDialog("Rule: (S/B/P format)");
			if(newRule == null) {
				GenerationsAlgorithmMenuItem.this.setSelected(false);
				return;
			}
		}
		Matcher m = rulePattern.matcher(newRule);
		if (!m.matches()) {
			String msg = "Rule must be in B###/S###/P### format";
			JOptionPane.showMessageDialog(null, msg, "Rule Error", JOptionPane.ERROR_MESSAGE);
			GenerationsAlgorithmMenuItem.this.setSelected(false);
			return;
		}
		String bRule = m.group(1);
		String sRule = m.group(2);
		String pRule = m.group(3);

		Algorithm a = new GenerationsAlgorithm(bRule, sRule, Integer.parseInt(pRule));
		GenerationsAlgorithmMenuItem.this.algorithmMenu.universe.changeAlgorithm(a);
		GenerationsAlgorithmMenuItem.this.algorithmMenu.lastSelectedItem.setSelected(false);
		GenerationsAlgorithmMenuItem.this.algorithmMenu.lastSelectedItem = GenerationsAlgorithmMenuItem.this;
	}

	private class GenerationsAlgorithmMenuItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setAlgorithm();
		}
	}

}