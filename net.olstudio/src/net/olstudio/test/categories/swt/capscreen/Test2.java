package net.olstudio.test.categories.swt.capscreen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Test2 {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Capture");
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {

				/**//* Take the screen shot */
				GC gc = new GC(display);
				final Image image = new Image(display, display.getBounds());
				gc.copyArea(image, 0, 0);
				gc.dispose();

				Shell popup = new Shell(shell, SWT.SHELL_TRIM);
				popup.setLayout(new FillLayout());
				popup.setText("Image");
				popup.setBounds(50, 50, 200, 200);
				popup.addListener(SWT.Close, new Listener() {
					public void handleEvent(Event e) {
						image.dispose();
					}
				});

				ScrolledComposite sc = new ScrolledComposite(popup,
						SWT.V_SCROLL | SWT.H_SCROLL);
				Canvas canvas = new Canvas(sc, SWT.NONE);
				sc.setContent(canvas);
				canvas.setBounds(display.getBounds());
				canvas.addPaintListener(new PaintListener() {
					public void paintControl(PaintEvent e) {
						e.gc.drawImage(image, 0, 0);
					}
				});
				popup.open();
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
