package org.cajunc2.automata.ui;

enum Scale {
	ACTUAL(1, "1:1"),
	DOUBLE(2, "2:1"),
	TRIPLE(3, "3:1"),
	QUADRUPLE(4, "4:1");

	private final int scaleFactor;
	private final String label;

	private Scale(int frameDelayNanos, String label) {
		this.scaleFactor = frameDelayNanos;
		this.label = label;
	}

	public int getScaleFactor() {
		return this.scaleFactor;
	}
	
	public String getLabel() {
		return this.label;
	}

	public Scale smaller() {
		if (this.ordinal() == Scale.values().length - 1) {
			return this;
		}
		return Scale.values()[this.ordinal() + 1];
	}

	public Scale larger() {
		if (this.ordinal() == 0) {
			return this;
		}
		return Scale.values()[this.ordinal() - 1];
	}
}
