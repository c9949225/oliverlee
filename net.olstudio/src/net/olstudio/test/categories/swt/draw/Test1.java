package net.olstudio.test.categories.swt.draw;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 点击鼠标左键在shell里画线。
 * 
 * @author u
 *
 */
public class Test1 {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		Listener listener = new Listener() {
			int lastX = 0, lastY = 0;

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MouseMove:
					if ((event.stateMask & SWT.BUTTON1) == 0)
						break; // 判断是否为鼠标左键，如果不是跳出
					GC gc = new GC(shell);
					gc.drawLine(lastX, lastY, event.x, event.y);
					gc.dispose();
					// FALL THROUGH
				case SWT.MouseDown:
					lastX = event.x;
					lastY = event.y;
					break;
				}
			}
		};
		shell.addListener(SWT.MouseDown, listener);
		shell.addListener(SWT.MouseMove, listener);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
