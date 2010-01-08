package net.oliver.olframework.swt.browser;

/**
 * @author Administrator
 *
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import java.awt.*;

//import com.email.POPMail;
//import com.email.PropertyFileOperation;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * This class implements a web browser
 */
public class WebBrowser {
	// The "at rest" text of the throbber

	private Label throbber;

	private Button button;

	private Combo url;

	private Button btnStop;

	private Button btnRefresh;

	private Browser browser;

	private Browser browser2;

	private Label status;

	private Button btnForward;

	private Button btnBack;

	private static final String AT_REST = "";

	private String[] urlList = new String[256]; // 最多可以保存256个url

	int urlListItemCount = 0;
	private ProgressBar progressBar;
	private Tree tree;
//	private PropertyFileOperation op;
//	private POPMail pop;

	/**
	 * Runs the application
	 * 
	 * @param location
	 *            the initial location to display
	 */
	public void run(String location) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("JAVA - Internet Explorer"); // 设置窗体名称
		createContents(shell, location);
		shell.open();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		shell.setLocation(0, 0);
		shell.setSize(screensize.width - 100, screensize.height - 120);
		// shell.setBounds(0, 0, screensize.width,screensize.height-20);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.exit(0);
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param shell
	 *            the main window
	 * @param location
	 *            the initial location
	 */
	public void createContents(Shell shell, String location) {
		shell.setLayout(new FormLayout());
		// Create the composite to hold the buttons and text field

		Composite controls = new Composite(shell, SWT.NONE);
		FormData data = new FormData();
		data.top = new FormAttachment(0, 0);
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		controls.setLayoutData(data);
		// Create the status bar

		status = new Label(shell, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, -100);
		data.top = new FormAttachment(100, -15);
		data.bottom = new FormAttachment(100, 0);
		status.setLayoutData(data);
		//
		progressBar = new ProgressBar(shell, SWT.NONE);
		data = new FormData();
		data.left = new FormAttachment(status);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(100, -10);
		data.bottom = new FormAttachment(100, 0);
		progressBar.setLayoutData(data);

		// view = new Composite(shell, SWT.NONE);
		// data = new FormData();
		// data.top = new FormAttachment(controls);
		// data.left = new FormAttachment(0, 0);

		// data.bottom = new FormAttachment(this.status);
		// view.setLayoutData(data);设置树的位置
		{
			tree = new Tree(shell, SWT.BORDER);
			data = new FormData();
			data.top = new FormAttachment(controls);
			data.left = new FormAttachment(0, 0);
			data.right = new FormAttachment(10, 0);
			data.bottom = new FormAttachment(this.progressBar);
			tree.setLayoutData(data);
			tree.addSelectionListener(// 添加监听选择树的某一项事件
					new SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {

							String tmp, server, port;
							tmp = tree.getSelection()[0].getText();
							if (tmp.indexOf("]") == -1)
								return;
							tmp = tmp.substring(1, tmp.indexOf("]"));
							System.out.println(tmp);
//							PropertyFileOperation pfo = new PropertyFileOperation("GrainStoreServer.properties");
//							server = pfo.getVal("server" + tmp);
//							port = pfo.getVal("port" + tmp);
							
							port = "80";
							server = "www.baidu.com";
							
							if (server != null && port != null
									&& !server.equals("") && !port.equals(""))
								if (port.equals("80")) {
									url.setText("http://" + server);
								} else {
									url
											.setText("http://" + server + ":"
													+ port);
								}
							goUrl();// 打开网页
						}
					});
			// this.setVisible(false);
			// new POPMail(0);
			// initVal();

		}

		// /////// //
		// 创建浏览器/////////////////////////////////////////////////////////

		browser = new Browser(shell, SWT.BORDER);
		browser.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent arg0) {
				String urlStr = arg0.browser.getUrl();
				int flag = 1;
				browser.setVisible(false);
				browser2.setVisible(true);
				arg0.browser = browser2;
				for (int i = 0; i < urlListItemCount; i++) {
					if (urlList[i].equals(urlStr)) {
						flag = 0;
					}
				}
				if (flag == 1) {
					urlList[urlListItemCount] = urlStr;
					url.add(urlStr);
					url.setText(urlStr);
					urlListItemCount++;
				}
			}
		});
		data = new FormData();
		data.top = new FormAttachment(controls);
		data.bottom = new FormAttachment(progressBar);
		data.left = new FormAttachment(this.tree);
		data.right = new FormAttachment(100, 0);
		browser.setLayoutData(data);
		progressBar.setMaximum(100); // 初始化进度条数据
		progressBar.setMinimum(0);
		// /////// //
		// 创建浏览器/////////////////////////////////////////////////////////

		browser2 = new Browser(shell, SWT.BORDER);
		browser2.addOpenWindowListener(new OpenWindowListener() {
			public void open(WindowEvent arg0) {
				String urlStr = arg0.browser.getUrl();
				int flag = 1;
				browser2.setVisible(false);
				browser.setVisible(true);
				arg0.browser = browser;
				for (int i = 0; i < urlListItemCount; i++) {
					if (urlList[i].equals(urlStr)) {
						flag = 0;
					}
				}
				if (flag == 1) {
					urlList[urlListItemCount] = urlStr;
					url.add(urlStr);
					url.setText(urlStr);
					urlListItemCount++;
				}
			}
		});
		data = new FormData();
		data.right = new FormAttachment(1000, 1000, 0);
		browser2.setLayoutData(data);

		// Create the controls and wire them to the browser

		controls.setLayout(new GridLayout(7, false));

		// Create the back button

		btnBack = new Button(controls, SWT.PUSH);
		btnBack.setText("后退");
		btnBack.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.back();
			}
		});

		// Create the forward button

		btnForward = new Button(controls, SWT.PUSH);
		btnForward.setText("前进");
		btnForward.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.forward();
			}
		});

		// Create the refresh button

		btnRefresh = new Button(controls, SWT.PUSH);
		btnRefresh.setText("刷新");
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.refresh();
			}
		});

		// Create the stop button

		btnStop = new Button(controls, SWT.PUSH);
		btnStop.setText("停止");
		btnStop.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				browser.stop();
			}
		});

		// Create the address entry field and set focus to it

		url = new Combo(controls, SWT.ARROW_DOWN);
		url.setLayoutData(new GridData(296, SWT.DEFAULT));
		url.setSize(600, url.getSize().y);
		url.setFocus();

		// Create the go button

		button = new Button(controls, SWT.PUSH);
		button.setText("转到");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				goUrl();
			}
		});

		// Create the animated "throbber"

		throbber = new Label(controls, SWT.NONE);
		throbber.setText(AT_REST);

		// Allow users to hit enter to go to the typed URL

		shell.setDefaultButton(button);

		// Add event handlers

		browser.addCloseWindowListener(new AdvancedCloseWindowListener());
		browser.addLocationListener(new AdvancedLocationListener(url));
		browser.addProgressListener(new AdvancedProgressListener(throbber));
		browser.addStatusTextListener(new AdvancedStatusTextListener(status));

		// Go to the initial URL

		if (location != null) {
			browser.setUrl(location);
		}
//		new POPMail(0);
		this.initVal();
	}

	/**
	 * This class implements a CloseWindowListener for TestBrowser
	 */
	class AdvancedCloseWindowListener implements CloseWindowListener {
		/**
		 * Called when the parent window should be closed
		 */
		public void close(WindowEvent event) {
			// Close the parent window

			((Browser) event.widget).getShell().close();
		}
	}

	/**
	 * This class implements a LocationListener for TestBrowser
	 */
	class AdvancedLocationListener implements LocationListener {
		// The address text box to update

		private Combo location;

		/**
		 * Constructs an AdvancedLocationListener
		 * 
		 * @param text
		 *            the address text box to update
		 */
		public AdvancedLocationListener(Combo text) {
			// Store the address box for updates

			location = text;
		}

		/**
		 * Called before the location changes
		 * 
		 * @param event
		 *            the event
		 */
		public void changing(LocationEvent event) {
			// Show the location that's loading

			// location.setText("Loading " + event.location + "...");
			location.setText(event.location);
		}

		/**
		 * Called after the location changes
		 * 
		 * @param event
		 *            the event
		 */
		public void changed(LocationEvent event) {
			// Show the loaded location

			location.setText(event.location);
		}
	}

	/**
	 * This class implements a ProgressListener for TestBrowser
	 */
	class AdvancedProgressListener implements ProgressListener {
		// The label on which to report progress

		// private Label progress;

		/**
		 * Constructs an AdvancedProgressListener
		 * 
		 * @param label
		 *            the label on which to report progress
		 */
		public AdvancedProgressListener(Label label) {
			// Store the label on which to report updates

			// progress = label;
		}

		/**
		 * Called when progress is made
		 * 
		 * @param event
		 *            the event
		 */
		public void changed(ProgressEvent event) {
			// Avoid divide-by-zero

			if (event.total != 0) {
				// Calculate a percentage and display it

				int percent = (int) (event.current / event.total) * 100;
				// progress.setText(percent + "%");

				progressBar.setSelection(percent);// 设置网页显示进度
			} // else {
			// Since we can't calculate a percent, show confusion :-)

			// progress.setText("");
			// }
		}

		/**
		 * Called when load is complete
		 * 
		 * @param event
		 *            the event
		 */
		public void completed(ProgressEvent event) {
			// Reset to the "at rest" message

			// progress.setText(AT_REST);
		}
	}

	/**
	 * This class implements a StatusTextListener for TestBrowser
	 */
	class AdvancedStatusTextListener implements StatusTextListener {
		// The label on which to report status

		private Label status;

		/**
		 * Constructs an AdvancedStatusTextListener
		 * 
		 * @param label
		 *            the label on which to report status
		 */
		public AdvancedStatusTextListener(Label label) {
			// Store the label on which to report status

			status = label;
		}

		/**
		 * Called when the status changes
		 * 
		 * @param event
		 *            the event
		 */
		public void changed(StatusTextEvent event) {
			// Report the status

			status.setText(event.text);
		}
	}

	// 解决编码转换
	public String getStr(String str) {
		try {
			String temp_p = str;
			byte[] temp_t = temp_p.getBytes("ISO8859-1");
			String temp = new String(temp_t);
			return temp;
		} catch (Exception e) {
			return "null";
		}
	}

	public void initVal() {

		// /////////////
//		op = new PropertyFileOperation("MailServer.properties");// 读取邮箱配置
//		// //////////////重邮箱更新粮库服务器信息/////////////////////////////////////////////////////
//
//		int num = Integer.parseInt(op.getVal("num"));
//		// pop=new
//		// POPMail(op.getVal("server"),op.getVal("user"),op.getVal("pass"),num,op.getVal("from"));
//
//		// ///////////加载树数据，粮库地址///////////////////////////////////////////////////////
//		PropertyFileOperation pfo = new PropertyFileOperation("Tree.properties");
//
//		for (int i = 1; i <= num; i++) {
//			String str = pfo.getVal("server" + i + "");
//			if (str != null && !str.equals("")) {
//				TreeItem treeItem = new TreeItem(tree, SWT.NONE);
//				treeItem.setText(getStr(pfo.getVal("server" + i + "")));
//			}
//		}
	}

	public void goUrl() {
		String urlStr = url.getText();
		int flag = 1;
		browser.setUrl(urlStr);
		for (int i = 0; i < urlListItemCount; i++) {
			if (urlList[i].equals(urlStr)) {
				flag = 0;
			}
		}
		if (flag == 1) {
			urlList[urlListItemCount] = urlStr;
			url.add(urlStr);
			urlListItemCount++;
		}
	}

	/**
	 * The application entry point
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		// new POPMail(0);
		new WebBrowser().run(args.length == 0 ? "about:blank" : args[0]);
	}
}
