package net.olstudio.test.categories.swt.custom.slider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * A custom SWT vertical slider component
 * 
 * @author liuyitong
 * 
 */
public class Slider extends Composite implements ControlListener,
		PaintListener, MouseListener, MouseMoveListener, MouseTrackListener {

	private final Color BORDER_COLOR = new Color(Display.getCurrent(), 180,
			188, 203);

	private final Color FILL_COLOR = new Color(Display.getCurrent(), 147, 217,
			72);

	private final Color BLANK_COLOR = new Color(Display.getCurrent(), 254, 254,
			254);

	private final Color DISABLE_COLOR = new Color(Display.getCurrent(), 192,
			192, 192);

	private final Image THUMB_ICON_V = new Image(Display.getDefault(),
			"slider_up_v.png");

	private final Image THUMB_OVER_ICON_V = new Image(Display.getDefault(),
			"slider_over_v.png");

	private final Image THUMB_ICON_H = new Image(Display.getDefault(),
			"slider_up_h.png");

	private final Image THUMB_OVER_ICON_H = new Image(Display.getDefault(),
			"slider_over_h.png");

	private final Image TEMP_H = new Image(Display.getDefault(), "temp_h.png");

	private final Image TEMP_V = new Image(Display.getDefault(), "temp_v.png");

	private SliderOrientation orientation;

	private Composite thumb;

	private Point controlPoint;

	private int value;

	private int tempLocation;

	private final int DEFAULT_MAX_VALUE = 100;

	private int maxValue = DEFAULT_MAX_VALUE;

	private List<SliderListener> listeners = new ArrayList<SliderListener>();

	public void addSliderListener(SliderListener sliderListener) {
		listeners.add(sliderListener);
	}

	public void removeSliderListener(SliderListener sliderListener) {
		listeners.remove(sliderListener);
	}

	public Slider(Composite parent, SliderOrientation orientation) {
		super(parent, SWT.FLAT);
		this.orientation = orientation;
		thumb = new Composite(this, SWT.FLAT);
		thumb.setBackgroundImage(orientation == SliderOrientation.VERTICAL ? THUMB_ICON_V : THUMB_ICON_H);
		addControlListener(this);
		addPaintListener(this);
		thumb.addMouseListener(this);
		thumb.addMouseMoveListener(this);
		thumb.addMouseTrackListener(this);
	}

	public void controlResized(ControlEvent e) {
		moveThumb();
	}

	public void controlMoved(ControlEvent e) {

	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		redraw();
	}

	public void paintControl(PaintEvent e) {
		int w = getBounds().width;
		int h = getBounds().height;
		int fillLength = valueToPels(getValue());
		GC gc = e.gc;
		switch (orientation) {
		case HORIZONTAL:
			gc.setForeground(BORDER_COLOR);
			gc.drawRectangle(0, 2, w - 1, h - 5);
			if (getEnabled()) {
				gc.setBackground(FILL_COLOR);
				for (int i = 2; i < w - 2; i += 4) {
					if (i > fillLength) {
						gc.setBackground(BLANK_COLOR);
					}
					gc.fillRectangle(i, 4, 3, h - 8);
				}
				if (controlPoint != null) {
					gc.drawImage(TEMP_H, tempLocation, 0);
				}
			} else {
				gc.setBackground(DISABLE_COLOR);
				gc.fillRectangle(1, 4, w - 1, h - 8);
			}
			break;
		case VERTICAL:
			gc.setForeground(BORDER_COLOR);
			gc.drawRectangle(2, 0, w - 5, h - 1);
			if (getEnabled()) {
				gc.setBackground(FILL_COLOR);
				for (int i = 2; i < h - 2; i += 4) {
					if (i > fillLength) {
						gc.setBackground(BLANK_COLOR);
					}
					gc.fillRectangle(4, i, w - 8, 3);
				}
				if (controlPoint != null) {
					gc.drawImage(TEMP_V, 0, tempLocation);
				}
			} else {
				gc.setBackground(DISABLE_COLOR);
				gc.fillRectangle(4, 1, w - 8, h - 1);
			}
			break;
		}
	}

	public void mouseDoubleClick(MouseEvent e) {

	}

	public void mouseDown(MouseEvent e) {
		controlPoint = new Point(e.x, e.y);
		thumb
				.setBackgroundImage(orientation == SliderOrientation.VERTICAL ? THUMB_OVER_ICON_V
						: THUMB_OVER_ICON_H);
	}

	public void mouseUp(MouseEvent e) {
		try {
			thumb
					.setBackgroundImage(orientation == SliderOrientation.VERTICAL ? THUMB_ICON_V
							: THUMB_ICON_H);
			countValue(e);
		} finally {
			controlPoint = null;
		}
	}

	public void mouseMove(MouseEvent e) {
		if (controlPoint == null) {
			return;
		}
		int maxLength;
		int maxLocator;
		switch (orientation) {
		case HORIZONTAL:
			maxLength = valueToPels(getMaxValue());
			maxLocator = maxLength - TEMP_H.getBounds().width;
			int movedX = e.x - controlPoint.x;
			redraw(tempLocation, 0, TEMP_H.getBounds().width,
					TEMP_H.getBounds().height, false);
			tempLocation = valueToPels(getValue()) + movedX
					- TEMP_H.getBounds().width / 2;
			if (tempLocation < 0) {
				tempLocation = 0;
			} else if (tempLocation > maxLocator) {
				tempLocation = maxLocator;
			}
			break;
		case VERTICAL:
			maxLength = valueToPels(getMaxValue());
			maxLocator = maxLength - TEMP_V.getBounds().height;
			int movedY = e.y - controlPoint.y;
			redraw(0, tempLocation, TEMP_V.getBounds().width,
					TEMP_V.getBounds().height, false);
			tempLocation = valueToPels(getValue()) + movedY
					- TEMP_V.getBounds().height / 2;
			if (tempLocation < 0) {
				tempLocation = 0;
			} else if (tempLocation > valueToPels(getMaxValue())
					- TEMP_V.getBounds().height) {
				tempLocation = valueToPels(getMaxValue())
						- TEMP_V.getBounds().height;
			}
			break;
		}
		// countValue(e);
	}

	public void mouseEnter(MouseEvent e) {

	}

	public void mouseExit(MouseEvent e) {

	}

	public void mouseHover(MouseEvent e) {

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private int valueToPels(int value) {
		float widgetLength = (orientation == SliderOrientation.HORIZONTAL) ? getBounds().width
				: getBounds().height;
		return (int) (widgetLength * (float) value / (float) maxValue);
	}

	private int pelsToValue(int pels) {
		float widgetLength = (orientation == SliderOrientation.HORIZONTAL) ? getBounds().width
				: getBounds().height;
		return (int) ((float) pels * (float) maxValue / (float) widgetLength);
	}

	private void countValue(MouseEvent e) {
		switch (orientation) {
		case HORIZONTAL:
			int movedX = e.x - controlPoint.x;
			setValue(getValue() + pelsToValue(movedX));
			break;
		case VERTICAL:
			int movedY = e.y - controlPoint.y;
			setValue(getValue() + pelsToValue(movedY));
			break;
		}
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (value < 0) {
			this.value = 0;
		} else if (value > getMaxValue()) {
			this.value = getMaxValue();
		} else {
			this.value = value;
		}
		try {
			moveThumb();
			redraw();
		} finally {
			for (SliderListener listener : listeners) {
				try {
					SliderEvent event = new SliderEvent(this, getValue());
					listener.valueChanged(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void moveThumb() {
		Image icon = thumb.getBackgroundImage();
		int iconw = (icon != null) ? icon.getBounds().width : 0;
		int iconh = (icon != null) ? icon.getBounds().height : 0;
		switch (orientation) {
		case HORIZONTAL:
			int x = valueToPels(getValue()) - iconw / 2;
			if (x < 0) {
				x = 0;
			} else if (x > getBounds().width - iconw) {
				x = getBounds().width - iconw;
			}
			thumb.setBounds(x, 0, iconw, iconh);
			break;
		case VERTICAL:
			int y = valueToPels(getValue()) - iconh / 2;
			if (y < 0) {
				y = 0;
			} else if (y > getBounds().height - iconh) {
				y = getBounds().height - iconh;
			}
			thumb.setBounds(0, y, iconw, iconh);
			break;
		}
	}
}