package net.olstudio.test.categories.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SwtTmpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Composite com = new Composite(shell,SWT.NONE);
		com.setLayout(new FillLayout());
//		Canvas canvas = new Canvas(com,SWT.NONE);
		com.setBackground(new Color(Display.getDefault(),123,193,123));
		shell.open();
		GC gc = new GC(com);
		gc.setForeground(new Color(Display.getDefault(),0,0,0));
		gc.drawLine(1, 1, 5, 5);
		
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		

	}

}
