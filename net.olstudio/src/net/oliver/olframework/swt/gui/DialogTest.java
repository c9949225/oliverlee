package net.oliver.olframework.swt.gui;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DialogTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		final Shell shell = new Shell(display,SWT.NONE);
		FillLayout fillLayout = new FillLayout();
		shell.setLayout(fillLayout);
		
		Button btn = new Button(shell,SWT.NONE);
		
		
		
		
		
		btn.addMouseListener(new MouseListener(){

			public void mouseDoubleClick(MouseEvent arg0) {
				
			}

			public void mouseDown(MouseEvent arg0) {
				
			}

			public void mouseUp(MouseEvent arg0) {
				MyDialog dlg = new MyDialog(shell,SWT.NONE);
				Display.getDefault().asyncExec(new Runnable(){
					public void run() {
						MessageDialog.openConfirm(shell, "请确认", "升级完毕，是否现在重启客户端?");
					}
				});

				final Shell dialogshell = (Shell) dlg.open();
			}
			
		});
		
//		btn.addListener(SWT.MouseDown, new Listener()
//		{
//			public void handleEvent(Event arg0) {
//				MessageDialog.openConfirm(shell, "请确认", "升级完毕，是否现在重启客户端?");
//			}
//		}
//		);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}
