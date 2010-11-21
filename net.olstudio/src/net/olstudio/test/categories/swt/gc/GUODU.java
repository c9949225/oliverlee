package net.olstudio.test.categories.swt.gc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUODU {
	public static void main(String[] args) {
		// 0. 准备窗口
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("GCT2");

		// 1. 创建画布 打开
		final Composite canvas = new Composite(shell, SWT.BORDER);
		canvas.setBounds(0, 0, 200, 200);
		shell.open(); // shell必须在canvas之后，gc 之前open

		// 2. 画布上创建GC
		GC gc = new GC(canvas);
		// gc.drawLine(0, 10, 140, 150);
		
		Color borader_color = new Color(Display.getDefault(),0,0,0);
		gc.setForeground(borader_color);
		gc.setLineWidth(5);
		gc.setLineStyle(SWT.LINE_DASHDOT);
		Rectangle rect = shell.getBounds();
		gc.drawRectangle(rect.x, rect.y, rect.width-2, rect.height-2);
		
//		gc.drawRectangle(4, 1, 190, 100);
//		ABWidgetUtils.fillGradientRoundRectangle(gc, 0, 0, 150, 30, 20,20, true);
//		gc.setBackground(color2);
//		ABWidgetUtils.fillGradientUpperRoundRectangle(gc, 1, 1, 200, 100, 4, 4,true);
		/*ABWidgetUtils.fillGradientLowerRoundRectangle(gc, 1, 1, 200, 50, 8,8, true);
		
		Text txt = new Text(canvas,SWT.BORDER);
		txt.setBounds(60, 35, 60, 30);
		
		Label label = new Label(canvas,SWT.NONE);
		label.setBounds(1, 40, 60, 30);
		label.setText("操作码");
		
		// 背景色决定了上下部分的区别
		gc.setBackground(color2);
		gc.setForeground(color1);
		// 2.3 fill lower
		// 左边高度一半开始，绘制整个宽度，一半高度，4，4
		ABWidgetUtils.fillGradientLowerRoundRectangle(gc, 1, 50, 200, 50, 8,8, true);*/
		
		
		
		
		// 画一个圆角矩形
//		ABWidgetUtils.fillGradientRoundRectangle(gc, 1, 50, 200, 100, 8,8, true);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}