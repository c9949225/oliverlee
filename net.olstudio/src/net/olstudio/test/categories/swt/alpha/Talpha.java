package net.olstudio.test.categories.swt.alpha;

import org.eclipse.swt.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Talpha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Display display = new Display();
		final Shell shell = new Shell(display);
		Canvas canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		
		// 使用paintlistener，保证每次均重新绘制。
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				GC gc = e.gc;
				// 读图像
				ImageData imageData = new ImageData("D:\\sb.jpg");
				// 这里是建立从左到右的渐进Alpha。
				byte[] alphaValues = new byte[imageData.height * imageData.width];
				for (int j = 0; j < imageData.height; j++) {
					for (int i = 0; i < imageData.width; i++) {
						alphaValues[j * imageData.width + i] = 
							(byte) (255 - 255 * i / imageData.width);
					}
				}
				imageData.alphaData = alphaValues;
				Image image = new Image(display, imageData);
				// 绘制
				gc.drawImage(image, 0, 0);
			}
		});

		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);
		shell.setSize(200, 600);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}