package org.cajunc2.automata;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.cajunc2.automata.algorithm.Algorithm;
import org.cajunc2.automata.algorithm.colors.ColorScheme;
import org.cajunc2.automata.ui.OutputDevice;

public class Universe {
	private final Object mutex = new Object();
	private OutputDevice output;
	private Algorithm algorithm;
	private final Size size;
	private int iteration = 0;
	private Speed speed = Speed.FAST;
	private boolean paused = true;
	private boolean running = false;

	private int[] generationA;
	private int[] generationB;

	private int[] cells;
	private int[] nextGeneration;

	public Universe(Algorithm algorithm, Size size) {
		this.algorithm = algorithm;
		this.size = size;
		generationA = new int[size.width * size.height];
		generationB = new int[size.width * size.height];
		cells = generationA;
		nextGeneration = generationB;
	}

	public Size getSize() {
		return this.size;
	}

	public void attachOutputDevice(OutputDevice newOutputDevice) {
		this.output = newOutputDevice;
		newOutputDevice.changeAlgorithm(algorithm);
		newOutputDevice.updateRunState(!paused);
		newOutputDevice.updateIterationNumber(iteration);
	}

	public void run() {
		if (output == null) {
			System.err.println("No output device attached");
			return;
		}
		running = true;
		try {
			while (running) {
				final long startTime = System.nanoTime();
				if (!paused) {
					tick();
				}
				try {
					sleepNanos(startTime, speed.getFrameDelayNanos());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} finally {
			output.dispose();
		}
	}

	private static final long SLEEP_PRECISION = TimeUnit.MILLISECONDS.toNanos(2);

	private static void sleepNanos(long startTime, long nanoDuration) throws InterruptedException {
		final long end = startTime + nanoDuration;
		long timeLeft = end - System.nanoTime();
		while (timeLeft > SLEEP_PRECISION) {
			Thread.sleep(1);
			timeLeft = end - System.nanoTime();
		}
		while (timeLeft > 0) {
			timeLeft = end - System.nanoTime();
		}
	}

	private void tick() {
		synchronized (mutex) {
			createGeneration();
			output.display(cells);
		}
	}

	public void step() {
		this.paused = true;
		output.updateRunState(false);
		this.tick();
	}

	public void randomize() {
		synchronized (mutex) {
			algorithm.randomize(cells);
			iteration = 0;
			output.updateIterationNumber(iteration);
			this.paused = true;
			output.updateRunState(!paused);
			output.display(cells);
		}
	}

	public void createGeneration() {
		algorithm.buildIteration(cells, nextGeneration, size.width);
		if (cells == generationA) {
			cells = generationB;
			nextGeneration = generationA;
		} else {
			cells = generationA;
			nextGeneration = generationB;
		}
		iteration++;
		output.updateIterationNumber(iteration);
	}

	public void introduceMutation() {
		synchronized (mutex) {
			Random r = new Random();
			for (int x = 0; x < size.width; x++) {
				int y = r.nextInt(size.height);
				int index = y * size.width + x;

				this.cells[index] = algorithm.draw(this.cells[index]);
			}
			output.display(cells);
		}
	}

	public void clear() {
		synchronized (mutex) {
			Arrays.fill(cells, 0);
			Arrays.fill(nextGeneration, 0);
			algorithm.reset();
			iteration = 0;
			output.updateIterationNumber(iteration);
			this.paused = true;
			output.updateRunState(!paused);
			output.display(cells);
		}
	}

	public void pause() {
		this.paused = !this.paused;
		output.updateRunState(!paused);
	}

	public void draw(int x, int y) {
		if (x < 0 || x >= this.size.width) {
			return;
		}

		if (y < 0 || y >= this.size.height) {
			return;
		}
		int index = y * size.width + x;
		synchronized (mutex) {
			this.cells[index] = algorithm.draw(this.cells[index]);
			output.display(cells);
		}
	}

	public void erase(int x, int y) {
		if (x < 0 || x >= this.size.width) {
			return;
		}

		if (y < 0 || y >= this.size.height) {
			return;
		}
		int index = y * size.width + x;
		synchronized (mutex) {
			this.cells[index] = algorithm.erase(this.cells[index]);
			output.display(cells);
		}
	}

	public void end() {
		this.running = false;
	}

	public void goFaster() {
		this.speed = this.speed.faster();
		output.updateSpeed(speed);
	}

	public void goSlower() {
		this.speed = this.speed.slower();
		output.updateSpeed(speed);
	}

	public void zoomOut() {
		output.zoomOut();
	}

	public void zoomIn() {
		output.zoomIn();
	}

	public void changeAlgorithm(Algorithm newAlgorithm) {
		algorithm = newAlgorithm;
		output.changeAlgorithm(newAlgorithm);
		clear();
	}

	public Algorithm getAlgorithm() {
		return this.algorithm;
	}

	public void setColorScheme(ColorScheme scheme) {
		output.setColorScheme(scheme);
		output.display(cells);
	}
	
	public static class Size implements Serializable {
		private static final long serialVersionUID = 1L;

		public final int width;
		public final int height;

		public Size(int width, int height) {
			this.width = width;
			this.height = height;
		}
	}
}