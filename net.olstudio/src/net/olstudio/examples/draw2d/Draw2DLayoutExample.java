package net.olstudio.examples.draw2d;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * swt里我们使用layout来控制各个控件的摆放位置一样，在Draw2D里最好也把这个工作交给LayoutManager来做
 */
/*除非是在自己实现的Layout里，一般程序里自己不要轻易使用setBounds()、setLocation()和setSize()这些方法控制图形的位置和大小，
而应该在为每个图形设置了适当的LayoutManager后，通过setConstraint()和 setPreferredSize()等方法告诉layoutmanager如何布局。
父图形的布局管理器负责修改每个子图形的位置和大小，
但计算每个子图形大小的工作可能是交给子图形自己的LayoutManager来做的，计算的方法一般是在这个LayoutManager的getPreferredSize()方法里体现。
当父图形使用XYLayout，子图形使用ToolbarLayout时，假设在子图形里又增加了子子图形（子图形里的子图形），add()方法会导致 revalidate()的调用，这时父图形的xylayout将检查子图形是否具有constraint，如果有并且有至少一个方向为-1，则利用子图形上的ToolbarLayout计算出子图形新的尺寸，
这个尺寸是和子图形里包含的子子图形的数目有关的（ToolbarLayout会把每个子图形的宽/高度加起来，加上其中间隔的空间，再考虑图形的边框，返回得到的尺寸）。
*/


/*
Draw2D里Figure类的setPreferredSize(Dimension)和setSize(Dimension)的区别是，
etSize()方法不会调用revalidate()方法导致重新layout，而只是调用repaint()对所涉及到的“脏”区域进行重绘（repaint）。
setPreferredSize()方法可以约等于setSize()方法+revalidate()方法，因为在Figure对 getPreferredSize(int,int)的实现里，
若该figure没有任何layoutmanager，则返回当前size
*/

/*ToolbarLayout对constraint是不感兴趣的，调用它的getConstraint()永远返回null值，
所以我们不必对放在使用 ToolbarLayout的图形的子图形设置constraint。
因此，假如我们的问题是，有图形A包含B，B包含C，要实现B（使用 ToolbarLayout）尺寸随C数目多少而自动改变该如何做呢？
这要看A使用何种LayoutManager，如果是ToolbarLayout则不用做特殊的设置，如果是XYLayout则要用A.getLayoutManager().setConstraint(B,new Rectangle(x,y,-1,-1))
这样的语句为A设置constraint，对图形C则用setPreferredSize()指定实际大小*/
public class Draw2DLayoutExample {

	static Figure canvas;//Parent figure which uses XYLayout as its layout manager

	static RectangleFigure containerFig;//canvas's only child, which uses ToolbarLayout

	static RectangleFigure innerContainerFig;//containerFig's only child, which uses ToolbarLayout, too

	static RectangleFigure firstGreenFig;//innerContainerFig's first child, which has no layout manager

	static Dimension dimension = new Dimension(40, 20);

	public static void main(String args[]) {
		Shell shell = new Shell();
		shell.setLayout(new GridLayout(1, false));

		//Create control buttons
		Button button = new Button(shell, SWT.PUSH);
		GridData gd = new GridData();
		button.setLayoutData(gd);
		button.setText("Add Red");
		Button button2 = new Button(shell, SWT.PUSH);
		gd = new GridData();
		button2.setLayoutData(gd);
		button2.setText("Add Green");
		Button button3 = new Button(shell, SWT.PUSH);
		gd = new GridData();
		button3.setLayoutData(gd);
		button3.setText("Enlarge Green");

		//Draw2d area
		LightweightSystem lws = new LightweightSystem(shell);

		//The canvas figure which fills right half of shell
		canvas = new Figure();
		canvas.setLayoutManager(new XYLayout());
		lws.setContents(canvas);

		//A rectangle figure
		containerFig = new RectangleFigure();
		canvas.add(containerFig);
		canvas.getLayoutManager().setConstraint(containerFig, new Rectangle(120, 10, -1, -1));
		ToolbarLayout layout = new ToolbarLayout();
		layout.setVertical(true);
		layout.setSpacing(3);
		layout.setStretchMinorAxis(false);
		containerFig.setLayoutManager(layout);
		containerFig.setBorder(new MarginBorder(5));
		RectangleFigure fig = new RectangleFigure();
		fig.setBackgroundColor(ColorConstants.red);
		fig.setSize(dimension);
		containerFig.add(fig);

		//A inner container figure
		innerContainerFig = new RectangleFigure();
		ToolbarLayout layout2 = new ToolbarLayout();
		layout2.setVertical(false);
		layout2.setSpacing(3);
		layout2.setStretchMinorAxis(false);
		innerContainerFig.setLayoutManager(layout2);
		innerContainerFig.setBorder(new MarginBorder(5));
		containerFig.add(innerContainerFig);

		//The first green figure in innerContainerFig
		firstGreenFig = new RectangleFigure();
		firstGreenFig.setBackgroundColor(ColorConstants.green);
		firstGreenFig.setSize(dimension);
		innerContainerFig.add(firstGreenFig);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RectangleFigure fig = new RectangleFigure();
				fig.setBackgroundColor(ColorConstants.red);
				fig.setPreferredSize(dimension);
				containerFig.add(fig);
			}

		});

		button2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RectangleFigure fig = new RectangleFigure();
				fig.setBackgroundColor(ColorConstants.green);
				fig.setPreferredSize(dimension);
				innerContainerFig.add(fig);
			}

		});

		button3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				//Make this figure bigger, and see if the outer figure grows accordingly
				firstGreenFig.setPreferredSize(100, 100);
			}

		});

		shell.setSize(500, 400);
		shell.open();
		shell.setText("Draw2D Layout Example");
		Display display = Display.getDefault();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}
}