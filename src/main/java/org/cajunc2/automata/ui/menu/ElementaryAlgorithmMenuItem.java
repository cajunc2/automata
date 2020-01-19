package org.cajunc2.automata.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.ElementaryAlgorithm;

class ElementaryAlgorithmMenuItem extends JCheckBoxMenuItem {
	private final AlgorithmMenu algorithmMenu;
	private static final long serialVersionUID = 1L;
	private final Pattern rulePattern = Pattern.compile("[0-9]*");
	private final String rule;
	private final int lines;

	public ElementaryAlgorithmMenuItem(AlgorithmMenu algorithmMenu, int lines) {
		this(algorithmMenu, lines, "Elementary ", null);
	}

	public ElementaryAlgorithmMenuItem(AlgorithmMenu algorithmMenu, int lines, String title, final String rule) {
		super(title);
		this.lines = lines;
		this.algorithmMenu = algorithmMenu;
		this.rule = rule;
		addActionListener(new GenerationsAlgorithmMenuItemActionListener());
	}

	private void setAlgorithm() {
		String newRule = this.rule;
		if (newRule == null) {
			newRule = JOptionPane.showInputDialog("Rule Number: (0-255)");
			if(newRule == null) {
				ElementaryAlgorithmMenuItem.this.setSelected(false);
				return;
			}
		}
		Matcher m = rulePattern.matcher(newRule);
		if (!m.matches()) {
			String msg = "Rule must be 0-255";
			JOptionPane.showMessageDialog(null, msg, "Rule Error", JOptionPane.ERROR_MESSAGE);
			ElementaryAlgorithmMenuItem.this.setSelected(false);
			return;
		}
		int intRule = Integer.parseInt(newRule);
		Algorithm a = new ElementaryAlgorithm(intRule, lines);
		ElementaryAlgorithmMenuItem.this.algorithmMenu.universe.changeAlgorithm(a);
		ElementaryAlgorithmMenuItem.this.algorithmMenu.lastSelectedItem.setSelected(false);
		ElementaryAlgorithmMenuItem.this.algorithmMenu.lastSelectedItem = ElementaryAlgorithmMenuItem.this;
	}

	private class GenerationsAlgorithmMenuItemActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setAlgorithm();
		}
	}

}