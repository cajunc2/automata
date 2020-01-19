package org.cajunc2.automata.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.cajunc2.automata.Universe;
import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;

public class PaletteMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	private final Universe universe;
	JMenuItem lastSelectedItem = null;

	public PaletteMenu(Universe universe) {
		super("Algorithm");
		this.universe = universe;
		refresh();
	}

	private class PaletteMenuItem extends JCheckBoxMenuItem {

		private static final long serialVersionUID = 1L;

		public PaletteMenuItem(final ColorScheme scheme) {
			super(scheme.getName());
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					universe.setColorScheme(scheme);
					if (PaletteMenu.this.lastSelectedItem != null) {
						PaletteMenu.this.lastSelectedItem.setSelected(false);
						PaletteMenu.this.lastSelectedItem = PaletteMenuItem.this;
						PaletteMenuItem.this.setSelected(true);
					}
				}
			});
		}

	}

	/** Refreshes the menu options based on the current algorithm */
	public void refresh() {
		this.removeAll();
		Algorithm a = this.universe.getAlgorithm();
		List<ColorScheme> schemes = a.getColorSchemeOptions();
		for (ColorScheme scheme : schemes) {
			this.add(new PaletteMenuItem(scheme));
		}

	}

}