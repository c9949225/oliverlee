package net.olstudio.test.categories.swt;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SwtTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		
		Composite composite = new Composite(shell,SWT.NONE);
		/*File file = new File("D:/sb.jpg");
		Image bck;
		try {
			bck = new Image(Display.getCurrent(), new FileInputStream(file));
			composite.setBackgroundImage(bck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} */
		shell.setLayout(new FillLayout());
		
		
         
//         composite.setBackgroundImage(bg);
		Button txt = new Button(composite,SWT.NONE);
		txt.setBounds(0, 0, 200, 30);
		txt.addPaintListener(new PaintListener()
		{

			public void paintControl(PaintEvent e) {
				 InputStream is = SwtTest.class.getResourceAsStream("statusbtn_bg.png");
		         ImageData imdata = new ImageData(is);
		         Image bg = new Image(Display.getDefault(),imdata);
		         GC gc = e.gc;
		 		Button bt = (Button) e.widget;
		 		gc.drawImage(bg, e.x, e.y);
//		 		bt.setImage(img);
		 		gc.drawText(bt.getText(), e.x + 23, e.y + 7, true);
		 		gc = null;
			}
			
		});
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

}
