package net.olstudio.test.categories.swt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SwtTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		
		Composite composite = new Composite(shell,SWT.NONE);
		File file = new File("D:/sb.jpg");
		Image bck;
		try {
			bck = new Image(Display.getCurrent(), new FileInputStream(file));
			composite.setBackgroundImage(bck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		shell.setLayout(new FillLayout());
		
		Text txt = new Text(composite,SWT.BORDER);
		txt.setBounds(10, 10, 200, 20);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
