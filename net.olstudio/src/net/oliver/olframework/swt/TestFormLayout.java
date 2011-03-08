/*
 * 特别声明：本技术材料受《中华人民共和国著作权法》、《计算机软件保护条例》等法律、法规、行政
 * 规章以及有关国际条约的保护，赞同科技享有知识产权、保留一切权利并视其为技术秘密。未经本公司书
 * 面许可，任何人不得擅自（包括但不限于：以非法的方式复制、传播、展示、镜像、上载、下载）使用，
 * 不得向第三方泄露、透露、披露。否则，本公司将依法追究侵权者的法律责任。特此声明！
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
 *  。
 * 
 * <B>使用范例</B>
 * {使用范例说明}
 * 
 * @author 刘尧兴
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
