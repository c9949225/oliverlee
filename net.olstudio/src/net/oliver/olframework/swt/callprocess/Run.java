package net.oliver.olframework.swt.callprocess;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		
		Shell  shell = new Shell(display);  
        shell.setLayout(new FillLayout());  
        final Composite composite = new Composite(shell, SWT.NONE);  
//        
//        Browser browser = new Browser(composite, SWT.NONE);
//        browser.setUrl("file://D:/html/test.html");
//        browser.setVisible(true);
//        shell.open();
//        
//        while(!shell.isDisposed()){
//        	if(!display.readAndDispatch())
//        	{
//        		display.sleep();
//        	}
//        		
//        }
//        
//        display.dispose();
        
		final IEAutomation ie = new IEAutomation();
		ie.setVisible(true);
		ie.navigate("file://D:/html/2.html");
		ie.setMenuBar(false);
		int hwnd = ie.getHWND();
//
		// compositeΪ���ر���������Ŀؼ�
//		OS.SetParent(hwnd, composite.handle); // ������ú��������
		
//		// �������
		OS.SendMessage(hwnd, OS.WM_SYSCOMMAND, OS.SC_MAXIMIZE, 0);
		OS.SendMessage(hwnd, OS.WM_ACTIVATE, 0, 0);
		composite.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e)
			{
				//�����ֶ�ָ���˳�������ᱨ�쳣
				ie.quit();
			}
		});
	}

}
