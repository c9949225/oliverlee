package net.olstudio.test.categories.swt.custom.slider;

import java.util.EventObject;

public class SliderEvent extends EventObject {

	private int value;

	public SliderEvent(Object source, int value) {
		super(source);
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
