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
 * ����ʹ��apache��ftp�ͻ��˰�ʵ���˼򵥵�ftp�ϴ����ܡ�.
 * 
 * @since 0.1
 */

public class ApacheFtpClient {
	
	/** The ftp tool. */
	private FTPClient ftpTool = null;

	/**
	 * ���ӵ��Է���ftp server��.
	 * 
	 * @param s_IP �Է�ftp server��IP��ַ��
	 * @param s_user �Է�ftp server�ĵ�¼�û�����
	 * @param s_password �Է�ftp server�����û�����Ӧ�����롣
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @since 0.1
	 */
	public void connectFtpServer(String s_IP, String s_user, String s_password)
			throws IOException {
		// ע�⣬����û�жԸ��������ķ���ֵ���ж�
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
	 * �Ͽ���Է�ftp server�����ӡ�.
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
	 * ִ���ɲ���ָ����ftp�������� ��������ĸ�ʽΪTreeMap������ȫ·���ļ����� Զ��Ŀ���ַ��
	 * ѭ����������ļ��������������δ�����׳��쳣�� <b>Ŀǰ�����������Ϣ���ֱ��ָ���˱�׼�������Ҫ���ǵ�����</b>.
	 * 
	 * @param filesTransferInfoObj ftp��������
	 * 
	 * @throws Exception the exception
	 * 
	 * @since 0.1
	 */
	public void transferAllFiles(TreeMap filesTransferInfoObj) throws Exception {
		System.out.println("passive   status   is   " + ftpTool.getStatus());
		int i_countErr = 0; // �������������
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

				// ���������޴�����������������������
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
	 * �ӷ����������ļ�
	 * @param fileName
	 * @throws IOException
	 */
	public void fetchFile(String fileName,String dest_file_name) throws IOException
	{
		File temp_file = new File("c:\\download_" + fileName.substring(fileName.length()-3) + ".txt");
		File dest_file = new File(dest_file_name);
		
		FileOutputStream fos = new FileOutputStream(temp_file);
		// �ӷ������������ļ�
        boolean flag = ftpTool.retrieveFile(fileName, fos);
        fos.close();
        if (flag) {
          // ����rename,ǰ�����ȹر��ļ���
          temp_file.renameTo(dest_file);
        }
	}
}
