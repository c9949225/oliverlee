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
	 * 计算布局自身的大小
	 */
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint,boolean flushCache) {
		Control[] children = composite.getChildren();
		int width = 0;
		int height = 0;
		// 遍历所有的孩子控件，根据孩子控件的偏好大小，计算布局自己的大小
		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			Point size = control.computeSize(wHint, hHint);
			MyData data = (MyData) control.getLayoutData();
			// 选择孩子控件中最大和最高的作为布局高度和宽度
			width = Math.max(width, size.x);
			height = Math.max(height, size.y * 100 / data.ratio);
		}
		// 再加上边框
		return new Point(width + 2 * margin, height + 2 * margin + (children.length - 1)*spacing);
	}

	/**
	 * 实际地进行布局
	 * 关键: 在layout方法中,根据孩子的LayoutData,设置孩子的大小和位置
	 */
	@Override
	protected void layout(Composite composite, boolean flushCache) {
		
		Control[] children = composite.getChildren();
		// 得到可绘制区域
		Rectangle clientArea = composite.getClientArea();
		// 扣除边界
		int width = clientArea.width - 2 * margin;
		int height = clientArea.height - 2 * margin - spacing * (children.length - 1);
		Point current = new Point(clientArea.x+margin, clientArea.y+margin);
		// 遍历所有孩子控件
		for (int i = 0; i < children.length; i++) {
			Control control = children[i];
			// 获取单个控件的布局信息
			MyData data = (MyData) control.getLayoutData();
			int currentWidth = width;
			// 计算孩子占布局总高度的比率
			int currentHeight = height * data.ratio / 100;
			// 在layout方法中设置孩子的大小和位置
			control.setBounds(current.x, current.y, currentWidth, currentHeight);
			// 更新当前的操作y坐标
			current.y += currentHeight + spacing;
		}
	}

}
