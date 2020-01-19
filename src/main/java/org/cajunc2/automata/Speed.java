package org.cajunc2.automata;

public enum Speed {
	SLOWEST(2),
	SLOWER(4),
	SLOW(8),
	MEDIUM(15),
	FAST(30),
	FASTEST(60);

	private final int frameDelayNanos;
	private final String label;

	private Speed(int genPerSecond) {
		this.frameDelayNanos = 1000000000 / genPerSecond;
		this.label = genPerSecond + " gen/sec";
	}

	public int getFrameDelayNanos() {
		return this.frameDelayNanos;
	}

	public String getLabel() {
		return this.label;
	}

	public Speed faster() {
		if (this.ordinal() == Speed.values().length - 1) {
			return this;
		}
		return Speed.values()[this.ordinal() + 1];
	}

	public Speed slower() {
		if (this.ordinal() == 0) {
			return this;
		}
		return Speed.values()[this.ordinal() - 1];
	}
}
