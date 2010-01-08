package net.oliver.olframework.swt.focus;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class FocusTest2 {

	public static void main(String[] args) {

		final Display display = new Display();
		final Shell shell = new Shell(display);

		shell.open();

		final Menu menu = new Menu(shell, SWT.POP_UP);

		MenuItem menuItemShow = new MenuItem(menu, SWT.NONE);
		menuItemShow.setText("Show window");
//		menuItemShow.setImage(ImageDescriptor.createFromFile(FocusTest2.class,
//				"eclipse.png").createImage());
		menuItemShow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				shell.forceActive();
			}
		});

		Tray tray = display.getSystemTray();
		TrayItem item = new TrayItem(tray, SWT.NONE);
//		item.setImage(ImageDescriptor.createFromFile(FocusTest2.class,
//				"eclipse.png").createImage());
		item.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.forceActive();
			}
		});
		item.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent e) {
				menu.setVisible(true);
			}
		});

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();

	}

}
