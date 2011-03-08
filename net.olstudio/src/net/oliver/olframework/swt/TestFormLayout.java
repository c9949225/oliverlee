/*
 * �ر������������������ܡ��л����񹲺͹�����Ȩ���������������������������ȷ��ɡ����桢����
 * �����Լ��йع�����Լ�ı�������ͬ�Ƽ�����֪ʶ��Ȩ������һ��Ȩ��������Ϊ�������ܡ�δ������˾��
 * ����ɣ��κ��˲������ԣ������������ڣ��ԷǷ��ķ�ʽ���ơ�������չʾ���������ء����أ�ʹ�ã�
 * �����������й¶��͸¶����¶�����򣬱���˾������׷����Ȩ�ߵķ������Ρ��ش�������
 *
 * Special Declaration: These technical material reserved as the technical secrets by AGREE 
 * TECHNOLOGY have been protected by the "Copyright Law" "ordinances on Protection of Computer 
 * Software" and other relevant administrative regulations and international treaties. Without 
 * the written permission of the Company, no person may use (including but not limited to the 
 * illegal copy, distribute, display, image, upload, and download) and disclose the above 
 * technical documents to any third party. Otherwise, any infringer shall afford the legal 
 * liability to the company.
 * 
 * Created on 2010-10-13 by ShenBo &lt;shen.bo@agree.com.cn&gt;
 */
package cn.com.agree.ab.widget.sidebar.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 *  ��
 * 
 * <B>ʹ�÷���</B>
 * {ʹ�÷���˵��}
 * 
 * @author ��Ң��
 * @author Agree Tech
 * @version     at  2010-10-13 
 */
public class TestFormLayout {

	private Table table;
	private Text text;
	protected Shell shell;

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestFormLayout window = new TestFormLayout();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setLayout(new FormLayout());
		shell.setSize(716, 593);
		shell.setText("SWT Application");

		text = new Text(shell, SWT.BORDER);
		final FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -5);
		fd_text.top = new FormAttachment(0, 2);
		fd_text.bottom = new FormAttachment(0, 20);
		fd_text.left = new FormAttachment(0, 0);
		text.setLayoutData(fd_text);

		table = new Table(shell, SWT.BORDER);
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -5);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(text, 0, SWT.BOTTOM);
		fd_table.left = new FormAttachment(text, 0, SWT.LEFT);
		table.setLayoutData(fd_table);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		//
	}

}
