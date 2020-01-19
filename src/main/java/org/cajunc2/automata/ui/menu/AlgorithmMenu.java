package org.cajunc2.automata.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.cajunc2.automata.Universe;
import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.CyclicAlgorithm;

public class AlgorithmMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	final Universe universe;
	JMenuItem lastSelectedItem = null;

	public AlgorithmMenu(Universe universe) {
		super("Algorithm");
		this.universe = universe;

		final JMenuItem ll = new LifeLikeAlgorithmMenuItem(this);
		final JMenuItem conway = new LifeLikeAlgorithmMenuItem(this, "Conway's Game of Life", "23/3");
		final JMenuItem anneal = new LifeLikeAlgorithmMenuItem(this, "Anneal (S35678/B4678)", "35678/4678");
		final JMenuItem dayNight = new LifeLikeAlgorithmMenuItem(this, "Day & Night (S34678/B3678)", "34678/3678");
		final JMenuItem diamoeba = new LifeLikeAlgorithmMenuItem(this, "Diamoeba (S5678/B35678)", "5678/35678");
		final JMenuItem highLife = new LifeLikeAlgorithmMenuItem(this, "HighLife (S23/B36)", "23/36");
		final JMenuItem lifeDeath = new LifeLikeAlgorithmMenuItem(this, "Life Without Death (S012345678/B3)",
				"012345678/3");
		final JMenuItem morley = new LifeLikeAlgorithmMenuItem(this, "Morley (S245/B368)", "245/368");
		final JMenuItem replicator = new LifeLikeAlgorithmMenuItem(this, "Replicator (S1357/B1357)", "1357/1357");
		final JMenuItem seeds = new LifeLikeAlgorithmMenuItem(this, "Seeds (S/B2)", "/2");
		final JMenuItem thirtyFour = new LifeLikeAlgorithmMenuItem(this, "34 Life (S34/B34)", "34/34");
		final JMenuItem twoByTwo = new LifeLikeAlgorithmMenuItem(this, "2x2 (S125/B36)", "125/36");

		final JMenuItem generations = new GenerationsAlgorithmMenuItem(this);
		final JMenuItem briansBrain = new GenerationsAlgorithmMenuItem(this, "Brian's Brain", "/2/3");
		final JMenuItem starWars = new GenerationsAlgorithmMenuItem(this, "Star Wars", "345/2/4");
		final JMenuItem faders = new GenerationsAlgorithmMenuItem(this, "Faders", "2/2/25");
		final JMenuItem softFreeze = new GenerationsAlgorithmMenuItem(this, "SoftFreeze", "13458/38/6");

		final JMenuItem cyclic = new ChangeAlgorithmMenuItem(new CyclicAlgorithm());

		final JMenuItem elem = new ElementaryAlgorithmMenuItem(this, universe.getSize().height);

		add(elem);

		addSeparator();

		add(ll);
		add(conway);
		add(anneal);
		add(dayNight);
		add(diamoeba);
		add(highLife);
		add(lifeDeath);
		add(morley);
		add(replicator);
		add(seeds);
		add(thirtyFour);
		add(twoByTwo);

		addSeparator();

		add(generations);
		add(briansBrain);
		add(starWars);
		add(faders);
		add(softFreeze);

		addSeparator();

		add(cyclic);

		conway.setSelected(true);
		this.lastSelectedItem = conway;
	}

	private class ChangeAlgorithmMenuItem extends JCheckBoxMenuItem {

		private static final long serialVersionUID = 1L;

		public ChangeAlgorithmMenuItem(final Algorithm algorithm) {
			super(algorithm.getName());
			addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					universe.changeAlgorithm(algorithm);
					AlgorithmMenu.this.lastSelectedItem.setSelected(false);
					AlgorithmMenu.this.lastSelectedItem = ChangeAlgorithmMenuItem.this;
					ChangeAlgorithmMenuItem.this.setSelected(true);
				}
			});
		}

	}

}