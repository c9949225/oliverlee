package net.oliver.olframework.swt.nebula;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DateTimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display,SWT.NONE);
		FillLayout fillLayout = new FillLayout();
		shell.setLayout(fillLayout);
		
		 
		
		
		
		
	 
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
