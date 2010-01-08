package net.olstudio.test.categories.swt.capscreen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestSrceen {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.NO_TRIM | SWT.TOOL);
		Rectangle di = display.getBounds();
		Image temps = new Image(display, di.width, di.height);
		GC gc = new GC(display);
		gc.copyArea(temps, 0, 0);
		gc.dispose();
		shell.setSize(di.width, di.height);
		shell.setLocation(0, 0);
		GridLayout grid = new GridLayout();
		grid.marginHeight = grid.marginLeft = grid.marginRight = grid.marginTop = 0;
		grid.marginWidth = 0;
		shell.setLayout(grid);
		TempShell temp = new TempShell(shell, temps);
		temp.setLayoutData(new GridData(GridData.FILL_BOTH));
		shell.setVisible(true);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
