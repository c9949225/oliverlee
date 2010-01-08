package net.oliver.olframework.swt.gui;

import java.awt.event.MouseAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MyDialog extends Dialog {
	Object result;
	public static Shell parent;
	public MyDialog (Shell parent, int style) {
		super (parent, style);
		this.parent = parent;
	}
	public MyDialog (Shell parent) {
		this (parent, 0); // your default style bits go here (not the Shell's style bits)
	}
	public Object open() {
		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setLayout(new FillLayout());
		shell.setText(getText());
		// Your code goes here (widget creation, set result, etc).
		Button btn2 = new Button(shell,SWT.BUTTON1);
		btn2.addMouseListener(new MouseListener(){

			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseUp(MouseEvent e) {
				boolean flag = MessageDialog.openConfirm(Display.getDefault().getActiveShell().getParent().getShell(), "请确认", "升级完毕，是否现在重启客户端?");
				
			}});
		result = shell;
		// ====================================================
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		return result;
	}
 }
