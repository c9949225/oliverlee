package net.oliver.olframework.swt.listener;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ControlListenerExample {

	public void run() {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.addControlListener(new ControlListener() {

			public void controlMoved(ControlEvent e) {

				System.out.println("control move");

			}

			public void controlResized(ControlEvent e) {

				System.out.println("control resize");

			}

		}

		);

		shell.pack();

		shell.open();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {

				display.sleep();

			}

		}

		display.dispose();

	}

	public static void main(String[] args) {

		new ControlListenerExample().run();

	}

}
