package net.oliver.olframework.util.network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.net.ftp.FTPClient;

// TODO: Auto-generated Javadoc
/**
 * 本类使用apache的ftp客户端包实现了简单的ftp上传功能。.
 * 
 * @since 0.1
 */

public class ApacheFtpClient {
	
	/** The ftp tool. */
	private FTPClient ftpTool = null;

	/**
	 * 连接到对方的ftp server。.
	 * 
	 * @param s_IP 对方ftp server的IP地址。
	 * @param s_user 对方ftp server的登录用户名。
	 * @param s_password 对方ftp server的与用户名对应的密码。
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @since 0.1
	 */
	public void connectFtpServer(String s_IP, String s_user, String s_password)
			throws IOException {
		// 注意，现在没有对各个方法的返回值作判断
		try {
			ftpTool = new FTPClient();
			ftpTool.connect(s_IP);
			ftpTool.login(s_user, s_password);
			ftpTool.setFileType(ftpTool.BINARY_FILE_TYPE);
			// ftpTool.enterLocalPassiveMode();
			ftpTool.enterLocalActiveMode();
			ftpTool.setSoTimeout(6000); // timeout is 6 seconds.
		} catch (IOException ex) {
			throw new IOException("can   not   login   ftp   server,   " + ex);
		}

	}

	/**
	 * 断开与对方ftp server的连接。.
	 * 
	 * @since 0.1
	 */
	public void disconnectFtpServer() {
		try {
			ftpTool.logout();
			ftpTool.disconnect();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			ftpTool = null;
		}
	} // end disconnectFtpServer()

	/**
	 * 执行由参数指定的ftp传输任务。 传输任务的格式为TreeMap（本地全路径文件名， 远程目标地址）
	 * 循环传输各个文件，连续出现三次错误就抛出异常。 <b>目前这个方法的信息输出直接指向了标准输出，需要考虑调整。</b>.
	 * 
	 * @param filesTransferInfoObj ftp传输任务。
	 * 
	 * @throws Exception the exception
	 * 
	 * @since 0.1
	 */
	public void transferAllFiles(TreeMap filesTransferInfoObj) throws Exception {
		System.out.println("passive   status   is   " + ftpTool.getStatus());
		int i_countErr = 0; // 连续错误计数器
		Iterator itor = filesTransferInfoObj.entrySet().iterator();
		while (itor.hasNext()) {
			Map.Entry e = (Map.Entry) itor.next();
			String s_localFileName = e.getKey().toString();
			String s_destDirectory = e.getValue().toString();
			//   
			try {
				System.out.println("\t--->");
				System.out.println("\t     local   [" + s_localFileName + "]");
				System.out.println("\t     dest     [" + s_destDirectory + "]");
				ftpTool.changeWorkingDirectory(s_destDirectory);
				ftpTool.storeFile(new File(s_localFileName).getName(),
						new BufferedInputStream(new FileInputStream(
								s_localFileName)));

				// 正常传输无错误，则把连续错误计数器清零
				i_countErr = 0;
				System.out.println("\t     succeed.");
			} catch (Exception ex) {
				i_countErr++;
				System.out.println("\t     failed.   |   " + ex);
				if (i_countErr > 3) {
					throw new Exception(
							"too   many   errors,   ftp   terminate.");
				}
			}
		}
	} // end transferAllFiles()

	/**
	 * 从服务器下载文件
	 * @param fileName
	 * @throws IOException
	 */
	public void fetchFile(String fileName,String dest_file_name) throws IOException
	{
		File temp_file = new File("c:\\download_" + fileName.substring(fileName.length()-3) + ".txt");
		File dest_file = new File(dest_file_name);
		
		FileOutputStream fos = new FileOutputStream(temp_file);
		// 从服务器上下载文件
        boolean flag = ftpTool.retrieveFile(fileName, fos);
        fos.close();
        if (flag) {
          // 本地rename,前提是先关闭文件流
          temp_file.renameTo(dest_file);
        }
	}
}
