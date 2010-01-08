package net.olstudio.test.categories.swt.custom.msndialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ComboDemo extends Shell {

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ComboDemo shell = new ComboDemo(display, SWT.SHELL_TRIM);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell
	 * 
	 * @param display
	 * @param style
	 */
	public ComboDemo(Display display, int style) {
		super(display, style);
		createContents();
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		setText("ComboSelector Demo");
		setSize(500, 375);
		setBackground(new Color(Display.getDefault(), 236, 246, 249));

		final ComboSelector selector = new ComboSelector(this) {
			
			// ������ڰ��»س�����Ч
			@Override
			protected void commit() {
				System.out.println("current data is "
						+ ((Person) getSelectedItem()).getUserName());
			}

			// �����б���ѡ��һ��ʱ��Ч
			@Override
			protected void selected(Object object) {
				System.out.println(((Person) object).getPassword());
			}
		};
		// ���ÿؼ�λ�ô�С
		selector.setBounds(114, 78, 200, 20);
		Person[] persons = new Person[] {
				new Person("play_station3@sina.com", "111111"),
				new Person("rehte@hotmail.com", "222222"),
				new Person("yitong.liu@bea.com", "password"),
				new Person("ʹ������Windows Live ID ��¼", "no") };
		selector.loadMenuItems(persons); // �����б���
		selector.select(1);// Ĭ��ѡ�е�һ��
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
