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
 * swt������ʹ��layout�����Ƹ����ؼ��İڷ�λ��һ������Draw2D�����Ҳ�������������LayoutManager����
 */
/*���������Լ�ʵ�ֵ�Layout�һ��������Լ���Ҫ����ʹ��setBounds()��setLocation()��setSize()��Щ��������ͼ�ε�λ�úʹ�С��
��Ӧ����Ϊÿ��ͼ���������ʵ���LayoutManager��ͨ��setConstraint()�� setPreferredSize()�ȷ�������layoutmanager��β��֡�
��ͼ�εĲ��ֹ����������޸�ÿ����ͼ�ε�λ�úʹ�С��
������ÿ����ͼ�δ�С�Ĺ��������ǽ�����ͼ���Լ���LayoutManager�����ģ�����ķ���һ���������LayoutManager��getPreferredSize()���������֡�
����ͼ��ʹ��XYLayout����ͼ��ʹ��ToolbarLayoutʱ����������ͼ����������������ͼ�Σ���ͼ�������ͼ�Σ���add()�����ᵼ�� revalidate()�ĵ��ã���ʱ��ͼ�ε�xylayout�������ͼ���Ƿ����constraint������в���������һ������Ϊ-1����������ͼ���ϵ�ToolbarLayout�������ͼ���µĳߴ磬
����ߴ��Ǻ���ͼ�������������ͼ�ε���Ŀ�йصģ�ToolbarLayout���ÿ����ͼ�εĿ�/�߶ȼ��������������м���Ŀռ䣬�ٿ���ͼ�εı߿򣬷��صõ��ĳߴ磩��
*/


/*
Draw2D��Figure���setPreferredSize(Dimension)��setSize(Dimension)�������ǣ�
etSize()�����������revalidate()������������layout����ֻ�ǵ���repaint()�����漰���ġ��ࡱ��������ػ棨repaint����
setPreferredSize()��������Լ����setSize()����+revalidate()��������Ϊ��Figure�� getPreferredSize(int,int)��ʵ���
����figureû���κ�layoutmanager���򷵻ص�ǰsize
*/

/*ToolbarLayout��constraint�ǲ�����Ȥ�ģ���������getConstraint()��Զ����nullֵ��
�������ǲ��ضԷ���ʹ�� ToolbarLayout��ͼ�ε���ͼ������constraint��
��ˣ��������ǵ������ǣ���ͼ��A����B��B����C��Ҫʵ��B��ʹ�� ToolbarLayout���ߴ���C��Ŀ���ٶ��Զ��ı��������أ�
��Ҫ��Aʹ�ú���LayoutManager�������ToolbarLayout��������������ã������XYLayout��Ҫ��A.getLayoutManager().setConstraint(B,new Rectangle(x,y,-1,-1))
���������ΪA����constraint����ͼ��C����setPreferredSize()ָ��ʵ�ʴ�С*/
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