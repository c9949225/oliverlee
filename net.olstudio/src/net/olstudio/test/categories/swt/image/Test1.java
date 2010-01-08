package net.olstudio.test.categories.swt.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * ��ͼ�������ת
 * 
 * @author u
 *
 */
public class Test1 {

	public static void main(String[] args) {
		final Display display = new Display();

		final Image image = new Image(display, 110, 60);
		GC gc = new GC(image);
		Font font = new Font(display, "Times", 30, SWT.BOLD);
		gc.setFont(font);
		gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
		gc.fillRectangle(0, 0, 110, 60);
		gc.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		gc.drawText("SWT", 10, 10, true);
		font.dispose();
		gc.dispose();

		final Rectangle rect = image.getBounds();
		Shell shell = new Shell(display);
		shell.setText("Matrix Tranformations");
		shell.setLayout(new FillLayout());
		final Canvas canvas = new Canvas(shell, SWT.DOUBLE_BUFFERED);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				gc.setAdvanced(true);
				if (!gc.getAdvanced()) {
					gc
							.drawText("Advanced graphics not supported", 30,
									30, true);
					return;
				}

				// Original image
				int x = 30, y = 30;
				gc.drawImage(image, x, y);
				x += rect.width + 30;

				Transform transform = new Transform(display);

				// Note that the tranform is applied to the whole GC therefore
				// the coordinates need to be adjusted too.

				// Reflect around the y axis.
				transform.setElements(-1, 0, 0, 1, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, -1 * x - rect.width, y);

				x = 30;
				y += rect.height + 30;

				// Reflect around the x axis.
				transform.setElements(1, 0, 0, -1, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, x, -1 * y - rect.height);

				x += rect.width + 30;

				// Reflect around the x and y axes
				transform.setElements(-1, 0, 0, -1, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, -1 * x - rect.width, -1 * y - rect.height);

				x = 30;
				y += rect.height + 30;

				// Shear in the x-direction
				transform.setElements(1, 0, -1, 1, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, 300, y);

				// Shear in y-direction
				transform.setElements(1, -1, 0, 1, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, 150, 475);

				// Rotate by 45 degrees
				float cos45 = (float) Math.cos(45);
				float sin45 = (float) Math.sin(45);
				transform.setElements(cos45, sin45, -sin45, cos45, 0, 0);
				gc.setTransform(transform);
				gc.drawImage(image, 350, 100);

				transform.dispose();
			}
		});

		shell.setSize(350, 550);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		image.dispose();
		display.dispose();
	}

}
