package net.olstudio.test.categories.swt.layout.custom;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class MyLayout extends Layout {

	public int margin = 0;
	
	public int spacing = 1;

	/**
	 * ���㲼������Ĵ�С
	 */
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint,boolean flushCache) {
		Control[] children = composite.getChildren();
		int width = 0;
		int height = 0;
		// �������еĺ��ӿؼ������ݺ��ӿؼ���ƫ�ô�С�����㲼���Լ��Ĵ�С
		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			Point size = control.computeSize(wHint, hHint);
			MyData data = (MyData) control.getLayoutData();
			// ѡ���ӿؼ���������ߵ���Ϊ���ָ߶ȺͿ��
			width = Math.max(width, size.x);
			height = Math.max(height, size.y * 100 / data.ratio);
		}
		// �ټ��ϱ߿�
		return new Point(width + 2 * margin, height + 2 * margin + (children.length - 1)*spacing);
	}

	/**
	 * ʵ�ʵؽ��в���
	 * �ؼ�: ��layout������,���ݺ��ӵ�LayoutData,���ú��ӵĴ�С��λ��
	 */
	@Override
	protected void layout(Composite composite, boolean flushCache) {
		
		Control[] children = composite.getChildren();
		// �õ��ɻ�������
		Rectangle clientArea = composite.getClientArea();
		// �۳��߽�
		int width = clientArea.width - 2 * margin;
		int height = clientArea.height - 2 * margin - spacing * (children.length - 1);
		Point current = new Point(clientArea.x+margin, clientArea.y+margin);
		// �������к��ӿؼ�
		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			// ��ȡ�����ؼ��Ĳ�����Ϣ
			MyData data = (MyData) control.getLayoutData();
			int currentWidth = width;
			// ���㺢��ռ�����ܸ߶ȵı���
			int currentHeight = height * data.ratio / 100;
			// ��layout���������ú��ӵĴ�С��λ��
			control.setBounds(current.x, current.y, currentWidth, currentHeight);
			// ���µ�ǰ�Ĳ���y����
			current.y += currentHeight + spacing;
		}
	}

}
