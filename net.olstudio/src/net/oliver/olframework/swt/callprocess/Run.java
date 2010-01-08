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
		// composite为承载被启动程序的控件
//		OS.SetParent(hwnd, composite.handle); // 这里调用后会有问题
		
//		// 窗口最大化
		OS.SendMessage(hwnd, OS.WM_SYSCOMMAND, OS.SC_MAXIMIZE, 0);
		OS.SendMessage(hwnd, OS.WM_ACTIVATE, 0, 0);
		composite.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e)
			{
				//必须手动指定退出，否则会报异常
				ie.quit();
			}
		});
	}

}
