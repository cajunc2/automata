package org.cajunc2.automata.algorithm;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.algorithm.colors.MonochromeColorScheme;

public class ElementaryAlgorithm implements Algorithm {
	private static final ColorScheme DEFAULT_PALETTE = new MonochromeColorScheme(Color.WHITE, "White");

	private final int lines;
	private final String name;
	private final int rule;
	private final List<ColorScheme> colorSchemeOptions;

	private int currentLine;

	public ElementaryAlgorithm(int rule, int lines) {
		this.lines = lines;
		this.rule = rule;
		this.name = "Rule " + rule;

		this.colorSchemeOptions = Arrays.asList(DEFAULT_PALETTE,
				new MonochromeColorScheme(Color.WHITE, "White"),
				new MonochromeColorScheme(Color.RED, "Red"),
				new MonochromeColorScheme(Color.ORANGE, "Orange"),
				new MonochromeColorScheme(Color.YELLOW, "Yellow"),
				new MonochromeColorScheme(Color.GREEN, "Green"),
				new MonochromeColorScheme(Color.BLUE, "Blue"),
				new MonochromeColorScheme(new Color(128, 0, 255), "Violet"),
				new MonochromeColorScheme(new Color(244, 0, 161), "Pink"));
	}

	@Override
	public void randomize(int[] cells) {
		int width = cells.length / lines;
		Random r = new Random();
		int lineStart = currentLine * width;
		for (int i = 0; i < width; i++) {
			cells[lineStart + i] = r.nextInt(2);
		}
		nextLine();
	}

	@Override
	public int draw(int currentValue) {
		return currentValue;
	}

	@Override
	public int erase(int currentValue) {
		return currentValue;
	}

	@Override
	public ColorScheme getPreferredColorScheme() {
		return DEFAULT_PALETTE;
	}

	@Override
	public List<ColorScheme> getColorSchemeOptions() {
		return colorSchemeOptions;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void buildIteration(int[] currentGeneration, int[] nextGeneration, int width) {
		int begin = width * currentLine;
		int nextRowBegin = width * (currentLine + 1);
		for (int i = begin; i < nextRowBegin; i++) {
			int p = (i == begin) ? i - 1 : i - width - 1;
			if (p < 0) {
				p += nextGeneration.length;
			}

			int c = i - width;
			if (c < 0) {
				c += nextGeneration.length;
			}

			int n = (i == nextRowBegin - 1) ? begin - width : i - width + 1;
			if (n < 0) {
				n += nextGeneration.length;
			}

			nextGeneration[c] = currentGeneration[c];
			nextGeneration[i] = compute(currentGeneration[p], currentGeneration[c], currentGeneration[n]);
		}
		nextLine();
	}

	private void nextLine() {
		currentLine++;
		if (currentLine >= lines) {
			currentLine = 0;
		}
	}

	private int compute(int i, int j, int k) {
		byte slots = 0;
		if (i != 0) {
			slots += 4;
		}
		if (j != 0) {
			slots += 2;
		}
		if (k != 0) {
			slots += 1;
		}
		return calc(slots) ? 1 : 0;
	}

	private boolean calc(byte slots) {
		return (rule & (1 << slots)) != 0;
	}

	@Override
	public void reset() {
		this.currentLine = 0;
	}
}
