package net.olstudio.test.categories.swt.draw;

import net.olstudio.test.categories.swt.custom.list.ColorList;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class TransformationFigure extends RoundedRectangle {

	static final Color BG = new Color(null, 242, 240, 255);

	static Image classImage = new Image(null, "D:\\sb.jpg");

	static Font BOLD = new Font(null, "", 10, SWT.BOLD);

	public static void main(String args[])
	{
		 final Display display = Display.getDefault();   
         final Shell shell = new Shell();   
         shell.setSize(296, 255);   
         shell.setText("CTabFolder Á·Ï°");   
         shell.setLayout(new GridLayout());   
         shell.open();   
         
         TransformationFigure customlist = new TransformationFigure();
		  shell.setSize(300, 200);   
          shell.layout();   
          while (!shell.isDisposed()) {   
              if (!display.readAndDispatch())   
                  display.sleep();   
          }   
	}
	
	public TransformationFigure() {
		class SeparatorBorder extends MarginBorder {
			SeparatorBorder() {
				super(3, 5, 3, 5);
			}

			public void paint(IFigure figure, Graphics graphics, Insets insets) {
				Rectangle where = getPaintRectangle(figure, insets);
				graphics.drawLine(where.getTopLeft(), where.getTopRight());
			}
		}
		this.setCornerDimensions(new Dimension(20, 20));

		Label header = new Label("ClassName", classImage);
		header.setFont(BOLD);
		header.setBorder(new MarginBorder(3, 5, 3, 5));

		Figure attributes = new Figure();
		ToolbarLayout layout;
		attributes.setLayoutManager(layout = new ToolbarLayout());
		layout.setStretchMinorAxis(false);
		attributes.add(new Label("name : String"));
		attributes.add(new Label("ID : String"));
		attributes.setBorder(new SeparatorBorder());

		Figure methods = new Figure();
		methods.setLayoutManager(layout = new ToolbarLayout());
		layout.setStretchMinorAxis(false);
		methods.add(new Label("foo() : int"));
		methods.add(new Label("bar() : char"));
		methods.setBorder(new SeparatorBorder());

		setLayoutManager(new ToolbarLayout());
		add(header);
		add(attributes);
		add(methods);
		setOpaque(true);
		setBackgroundColor(BG);
	}

}
