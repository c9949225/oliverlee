package net.olstudio.test.categories.swt.multimedia;


import java.net.URISyntaxException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FLashActiveX1 {

	public static void main(String[] args) throws URISyntaxException {
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(500, 450);
		// ---------创建窗口中的其他界面组件-------------
		shell.setLayout(new GridLayout());

		OleFrame oleFrame = new OleFrame(shell, SWT.NONE);
		oleFrame.setLayoutData(new GridData(GridData.FILL_BOTH));
		// OleControlSite是OleClientSite的子类，提供了更多的功能。当然本实例用OleClientSite也一样。
		OleControlSite controlSite = new OleControlSite(oleFrame, SWT.NONE, "ShockwaveFlash.ShockwaveFlash");
		controlSite.doVerb(OLE.OLEIVERB_SHOW);// 用OleControlSite则需要设置verb类型

		// 开始通过OleAutomation来调用控件的方法
		final OleAutomation automation = new OleAutomation(controlSite);
		int[] methodIDs = automation.getIDsOfNames(new String[] { "LoadMovie" });
		// 得到swf文件的绝对路径，然后LoadMovie方法装入swf文件
		String file = "file:/" + FLashActiveX1.class.getResource("a.swf").getPath();
		Variant[] methodArgs = { new Variant(0), new Variant(file) };// 方法的参数
		automation.invoke(methodIDs[0], methodArgs);

		Button button = new Button(shell, SWT.NONE);
		button.setText("播放");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// 调用Flash控件的播放方法Play()。这里直接换算出方法的id值112
				automation.invoke(112);
			}
		});
		// -----------------END----------------------
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	// FileDialog dialog = new FileDialog(shell, SWT.OPEN);
	// dialog.setFilterExtensions(new String[] { "*.swf" });
	// String file = dialog.open();
	// System.out.println(file);
}
