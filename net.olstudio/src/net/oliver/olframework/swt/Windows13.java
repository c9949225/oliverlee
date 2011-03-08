package net.oliver.olframework.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Windows13 {
	protected Shell shell;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Windows13 window = new Windows13();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		final Menu menu = new Menu(shell, SWT.POP_UP);
		shell.setMenu(menu);
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
//		menuItem
//				.setImage(SWTResourceManager
//						.getImage("C:\\abc-workspace\\workspace\\hkcb\\resources\\image\\help.png"));
		menuItem.setText("New Item");
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
//		menuItem_1
//				.setImage(SWTResourceManager
//						.getImage("C:\\abc-workspace\\workspace\\hkcb\\resources\\image\\mulitLanguage.png"));
		menuItem_1.setText("New Item");
		new MenuItem(menu, SWT.SEPARATOR);
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
//		menuItem_2
//				.setImage(SWTResourceManager
//						.getImage("C:\\abc-workspace\\workspace\\hkcb\\resources\\image\\quit.png"));
		menuItem_2.setText("New Item");
		MenuItem menuItem_3 = new MenuItem(menu, SWT.NONE);
//		menuItem_3
//				.setImage(SWTResourceManager
//						.getImage("C:\\abc-workspace\\workspace\\hkcb\\resources\\image\\theme.png"));
		menuItem_3.setText("New Item");
		new MenuItem(menu, SWT.SEPARATOR);
		MenuItem menuItem_4 = new MenuItem(menu, SWT.NONE);
//		menuItem_4
//				.setImage(SWTResourceManager
//						.getImage("C:\\abc-workspace\\workspace\\hkcb\\resources\\image\\config.png"));
		menuItem_4.setText("New Item");
		final Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Point loc = button.toDisplay(0, 0);
				menu.setLocation(loc.x, loc.y - 120);
				menu.setVisible(true);
			}
		});
		button.setBounds(32, 235, 80, 27);
		button.setText("New Button");
	}
}
