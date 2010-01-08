package net.olstudio.test.categories.swt.gc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GUODU {
	public static void main(String[] args) {
		// 0. ׼������
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("GCT2");

		// 1. �������� ��
		final Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(0, 0, 200, 200);
		shell.open(); // shell������canvas֮��gc ֮ǰopen

		// 2. �����ϴ���GC
		GC gc = new GC(canvas);
		// gc.drawLine(0, 10, 140, 150);

		RGB widgetRGB = new RGB(198, 197, 215);
		RGB lightRGB = new RGB(255, 255, 255);

		Color color1, color2;
		color1 = new Color(display, widgetRGB);
		color2 = new Color(display, lightRGB);
		
		// ����ɫ���������²��ֵ�����
		gc.setBackground(color1);
		
		// ǰ��ɫ�������ϰ벿�ֺ��°벿�ֵĽ���ɫ
		gc.setForeground(color2);
		
		// 2.2 fill upper
		// ���Ͻǿ�ʼ������������ȣ�һ��߶ȣ�4��4
		gc.setLineWidth(4);
//		gc.drawRectangle(4, 1, 190, 100);
		ABWidgetUtils.fillGradientRoundRectangle(gc, 0, 0, 150, 30, 20,20, true);
//		gc.setBackground(color2);
//		ABWidgetUtils.fillGradientUpperRoundRectangle(gc, 1, 1, 200, 100, 4, 4,true);
		/*ABWidgetUtils.fillGradientLowerRoundRectangle(gc, 1, 1, 200, 50, 8,8, true);
		
		Text txt = new Text(canvas,SWT.BORDER);
		txt.setBounds(60, 35, 60, 30);
		
		Label label = new Label(canvas,SWT.NONE);
		label.setBounds(1, 40, 60, 30);
		label.setText("������");
		
		// ����ɫ���������²��ֵ�����
		gc.setBackground(color2);
		gc.setForeground(color1);
		// 2.3 fill lower
		// ��߸߶�һ�뿪ʼ������������ȣ�һ��߶ȣ�4��4
		ABWidgetUtils.fillGradientLowerRoundRectangle(gc, 1, 50, 200, 50, 8,8, true);*/
		
		
		
		
		// ��һ��Բ�Ǿ���
//		ABWidgetUtils.fillGradientRoundRectangle(gc, 1, 50, 200, 100, 8,8, true);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}