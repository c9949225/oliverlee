package net.olstudio.test.categories.swt.custom.slider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DemoShell extends Shell implements SliderListener {
	private Slider sliderv;

	private Slider sliderh;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			DemoShell shell = new DemoShell(display, SWT.SHELL_TRIM);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell
	 * 
	 * @param display
	 * @param style
	 */
	public DemoShell(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(500, 375);
		setBackground(new Color(Display.getCurrent(), 148, 159, 180));
		setBackgroundMode(SWT.INHERIT_DEFAULT);

		sliderv = new Slider(this, SliderOrientation.VERTICAL);
		sliderv.setValue(50);
		sliderv.setBounds(166, 50, 15, 212);
		sliderv.addSliderListener(this);

		sliderh = new Slider(this, SliderOrientation.HORIZONTAL);
		sliderh.setValue(50);
		sliderh.setBounds(10, 10, 212, 15);
		sliderh.addSliderListener(this);
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void valueChanged(SliderEvent event) {
		if (event.getSource() == sliderv) {
			System.out.println("V" + event.getValue());
		} else if (event.getSource() == sliderh) {
			System.out.println("H" + event.getValue());
		}
	}

}
