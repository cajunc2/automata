package org.cajunc2.automata.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.cajunc2.automata.algorithm.neighborhood.Neighborhood;

public abstract class AbstractMultithreadedAlgorithm implements Algorithm {
	private static final int WORKER_COUNT = 8;
	private static final ExecutorService executor = Executors.newWorkStealingPool();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				executor.shutdownNow();
			}
		});
	}

	private final Neighborhood neighborhood;
	private final List<GenThread> callables = new ArrayList<GenThread>(WORKER_COUNT);

	public AbstractMultithreadedAlgorithm(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
		for (int i = 0; i < WORKER_COUNT; i++) {
			callables.add(new GenThread(neighborhood));
		}
	}

	@Override
	public void buildIteration(int[] currentGeneration, int[] nextGeneration, int width) {
		int chunkSize = currentGeneration.length / WORKER_COUNT;
		for (int i = 0; i < WORKER_COUNT; i++) {
			callables.get(i).configure(width, chunkSize, chunkSize * i, currentGeneration, nextGeneration);
		}
		try {
			executor.invokeAll(this.callables);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	private class GenThread implements Callable<Void> {

		private int width;
		private int chunkStart;
		private int chunkEnd;
		private int[] currentGeneration;
		private int[] nextGeneration;
		private final int[] neighbors;

		public GenThread(Neighborhood n) {
			this.neighbors = new int[n.size()];
		}

		public void configure(int width, int chunkSize, int chunkStart, int[] currentGeneration, int[] nextGeneration) {
			this.width = width;
			this.chunkStart = chunkStart;
			this.chunkEnd = chunkStart + chunkSize;
			this.currentGeneration = currentGeneration;
			this.nextGeneration = nextGeneration;
		}

		@Override
		public Void call() throws Exception {
			for (int i = chunkStart; i < chunkEnd; i++) {
				neighborhood.values(currentGeneration, i, neighbors, width);
				int currentValue = currentGeneration[i];
				nextGeneration[i] = calculate(neighbors, currentValue);
			}
			return null;
		}
	}

	@Override
	public void reset() {
		// Does nothing by default
	}

	protected abstract int calculate(int[] surroundingCells, int currentValue);

}