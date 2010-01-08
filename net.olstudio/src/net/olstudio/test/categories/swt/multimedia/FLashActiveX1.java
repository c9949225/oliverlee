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
		// ---------���������е������������-------------
		shell.setLayout(new GridLayout());

		OleFrame oleFrame = new OleFrame(shell, SWT.NONE);
		oleFrame.setLayoutData(new GridData(GridData.FILL_BOTH));
		// OleControlSite��OleClientSite�����࣬�ṩ�˸���Ĺ��ܡ���Ȼ��ʵ����OleClientSiteҲһ����
		OleControlSite controlSite = new OleControlSite(oleFrame, SWT.NONE, "ShockwaveFlash.ShockwaveFlash");
		controlSite.doVerb(OLE.OLEIVERB_SHOW);// ��OleControlSite����Ҫ����verb����

		// ��ʼͨ��OleAutomation�����ÿؼ��ķ���
		final OleAutomation automation = new OleAutomation(controlSite);
		int[] methodIDs = automation.getIDsOfNames(new String[] { "LoadMovie" });
		// �õ�swf�ļ��ľ���·����Ȼ��LoadMovie����װ��swf�ļ�
		String file = "file:/" + FLashActiveX1.class.getResource("a.swf").getPath();
		Variant[] methodArgs = { new Variant(0), new Variant(file) };// �����Ĳ���
		automation.invoke(methodIDs[0], methodArgs);

		Button button = new Button(shell, SWT.NONE);
		button.setText("����");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// ����Flash�ؼ��Ĳ��ŷ���Play()������ֱ�ӻ����������idֵ112
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
