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

		// ����һ���򵥵ı�
		final Table table = new Table(shell, SWT.MULTI);
		table.setLinesVisible(true);
		table.setBounds(10, 10, 100, 100);
		for (int i = 0; i < 9; i++) {
			new TableItem(table, SWT.NONE).setText("item" + i);
		}

		// ������׽ͼ��İ�ť
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Capture");
		button.pack();
		button.setLocation(10, 140);

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				Point tableSize = table.getSize(); // ��ȡ��Ĵ�С
				GC gc = new GC(table); // �������GC����
				final Image image = new Image(display, tableSize.x, tableSize.y); // �������С��ͼ��image
				gc.copyArea(image, 0, 0); // ���ñ��GC����ѱ��ͼ���Ƶ�image��
				gc.dispose();

				// ����һ���������Shell����popup
				Shell popup = new Shell(shell);
				popup.setText("Image");
				popup.addListener(SWT.Close, new Listener() {
					public void handleEvent(Event e) {
						image.dispose();
					}
				});
				// ��popup�Ͻ�����������canvas
				Canvas canvas = new Canvas(popup, SWT.NONE);
				canvas.setBounds(10, 10, tableSize.x + 10, tableSize.y + 10);
				canvas.addPaintListener(new PaintListener() {
					public void paintControl(PaintEvent e) {
						e.gc.drawImage(image, 0, 0); // �ڻ����ϻ�����ͼ��image
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
