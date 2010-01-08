package net.oliver.olframework.swt.focus;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.graphics.Image;

class HelloTray implements SelectionListener {
	Display display;
	Shell shell;
	Button hello;
	Tray tray;
	TrayItem ti;
	Menu menu;
	MenuItem mi1;
	MenuItem mi2;
	MenuItem quit;

	public HelloTray() {
		display = Display.getDefault();
		shell = new Shell();
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		shell.setLayout(fillLayout);
		hello = new Button(shell, SWT.NONE);
		hello.setText("Hello World!");
		hello.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (hello.getText().equals("Hello World!"))
					hello.setText("Clicked");
				else
					hello.setText("Hello World!");
			}
		});
		menu = new Menu(shell);
		mi1 = new MenuItem(menu, SWT.PUSH);
		mi1.setText("MenuItem 1");
		mi1.addSelectionListener(this);
		mi2 = new MenuItem(menu, SWT.PUSH);
		mi2.setText("MenuItem 2");
		mi2.addSelectionListener(this);
		quit = new MenuItem(menu, SWT.PUSH);
		quit.setText("Quit");
		quit.addSelectionListener(this);
		// 生成swt的tray
		tray = display.getSystemTray();
		ti = new TrayItem(tray, 0);
		ti.setToolTipText("This is a swt Tray!");
		ti
				.setImage(new Image(display,
						"E:\\My Documents\\My Pictures\\alm.gif"));
		// swt,tray的所有事件:
		ti.addSelectionListener(new SelectionListener() {
			// 左键单击
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Tray Selcted");
				// 左键单击时 ,隐藏,显示主窗口
				if (shell.isVisible()) {
					shell.setVisible(false);
				} else {
					shell.setVisible(true);
					shell.forceActive();
				}
			}

			// 左键双击,右键双击,都是它
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("Tray widgetDefaultSelected");
			}
		});
		// 右键单击,弹出菜单
		ti.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				System.out.println("SWT.MenuDetect");
				menu.setLocation(display.getCursorLocation());
				menu.setVisible(true);
			}
		});
	}

	public void start() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static void main(String[] args) {
		HelloTray app = new HelloTray();
		app.start();
	}

	public void widgetSelected(SelectionEvent e) {
		System.out.println(e.getSource() + " selected.");
		if (e.getSource() == quit) {
			System.out.println("Quit");
			display.dispose();
			System.exit(0);
		}
	}

	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
