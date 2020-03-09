package org.cajunc2.automata.ui.menu;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.LifeLikeAlgorithm;

class LifeLikeAlgorithmMenuItem extends JCheckBoxMenuItem {
	private static final long serialVersionUID = 1L;
	private final Pattern rulePattern = Pattern.compile("[Ss]?([0-9]*)/[Bb]?([0-9]*)");

	public LifeLikeAlgorithmMenuItem(final AlgorithmMenu algorithmMenu) {
		super("Life-Like");
		addActionListener((ActionEvent e) -> {
			String rule = JOptionPane.showInputDialog("Rule: (S/B format)");
			if (rule == null || rule.trim().isEmpty()) {
				return;
			}

			Matcher m = rulePattern.matcher(rule);
			if (!m.matches()) {
				String msg = "Rule must be in S###/B### format";
				JOptionPane.showMessageDialog(null, msg, "Rule Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String sRule = m.group(1);
			String bRule = m.group(2);

			Algorithm a = new LifeLikeAlgorithm(sRule, bRule);
			algorithmMenu.universe.changeAlgorithm(a);
			algorithmMenu.lastSelectedItem.setSelected(false);
			algorithmMenu.lastSelectedItem = LifeLikeAlgorithmMenuItem.this;
		});
	}

	public LifeLikeAlgorithmMenuItem(final AlgorithmMenu algorithmMenu, String name, final String rule) {
		super(name);
		addActionListener((ActionEvent e) -> {
			if (rule == null || rule.trim().isEmpty()) {
				return;
			}

			Matcher m = rulePattern.matcher(rule);
			if (!m.matches()) {
				String msg = "Rule must be in S###/B### format";
				throw new IllegalArgumentException(msg);
			}

			String sRule = m.group(1);
			String bRule = m.group(2);

			Algorithm a = new LifeLikeAlgorithm(sRule, bRule);
			algorithmMenu.universe.changeAlgorithm(a);
			algorithmMenu.lastSelectedItem.setSelected(false);
			algorithmMenu.lastSelectedItem = LifeLikeAlgorithmMenuItem.this;
		});
	}

}