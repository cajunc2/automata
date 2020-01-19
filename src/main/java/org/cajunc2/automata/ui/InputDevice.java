package org.cajunc2.automata.ui;

public interface InputDevice {

	boolean shouldRandomize();

	boolean shouldPause();

	boolean shouldExit();

	boolean shouldIntroduceMutation();

	boolean shouldClear();

}
