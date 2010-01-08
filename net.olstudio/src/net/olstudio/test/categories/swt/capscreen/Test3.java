package net.olstudio.test.categories.swt.capscreen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class Test3 {

	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Widget");

		// 建立一个简单的表
		final Table table = new Table(shell, SWT.MULTI);
		table.setLinesVisible(true);
		table.setBounds(10, 10, 100, 100);
		for (int i = 0; i < 9; i++) {
			new TableItem(table, SWT.NONE).setText("item" + i);
		}

		// 建立捕捉图像的按钮
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Capture");
		button.pack();
		button.setLocation(10, 140);

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				Point tableSize = table.getSize(); // 获取表的大小
				GC gc = new GC(table); // 建立表的GC对象
				final Image image = new Image(display, tableSize.x, tableSize.y); // 建立表大小的图像image
				gc.copyArea(image, 0, 0); // 利用表的GC对象把表的图像复制到image中
				gc.dispose();

				// 建立一个弹出面板Shell对象popup
				Shell popup = new Shell(shell);
				popup.setText("Image");
				popup.addListener(SWT.Close, new Listener() {
					public void handleEvent(Event e) {
						image.dispose();
					}
				});
				// 在popup上建立画布对象canvas
				Canvas canvas = new Canvas(popup, SWT.NONE);
				canvas.setBounds(10, 10, tableSize.x + 10, tableSize.y + 10);
				canvas.addPaintListener(new PaintListener() {
					public void paintControl(PaintEvent e) {
						e.gc.drawImage(image, 0, 0); // 在画布上绘出表的图像image
					}
				});
				popup.pack();
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
