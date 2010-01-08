package net.oliver.olframework.util.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

/**
 * �򵥵�Ftp�ļ����ع���
 * 
 * @author u
 *
 */
public class FtpHandle {
	
	private Log logger = LogFactory.getLog(FtpHandle.class);
	
	FTPClient ftpClient;
	
	// ��¼��FTP������
	public void connectServer(String server, String user, String password,String path) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(server);
			ftpClient.login(user, password);
			logger.info("�ɹ���¼��FTP������:" + server);
			if (path.length() != 0) {
				boolean flag = ftpClient.changeWorkingDirectory(path);
				if (flag) {
					logger.info("Ftp��ǰ����Ŀ¼����Ϊָ��Ŀ¼...! ");
				}
			}
		} catch (IOException e) {
			logger.error("��¼��FTP������ʱ����"+e.getMessage());
		}
	}

	// �ر�����
	public void closeConnect() {
		try {
			ftpClient.disconnect();
			logger.info(" disconnect success !!! ");
		} catch (IOException e) {
			logger.info(" not disconnect !!! ");
			logger.error(e.getMessage());
		}
	}

	//  �ӷ����������ļ�������
	public File download(String filename) {
		try {
			File temp_file = new File("C:/"+filename);
			FileOutputStream fos = new FileOutputStream(temp_file);
			if(ftpClient.retrieveFile(filename, fos)){
				fos.close();
				return temp_file;
			}
			fos.close();
		} catch (IOException e) {
			logger.error("��FTP����������"+filename+"ʱ����:"+e.getMessage());
		}
		return null;
	}

	// �÷�
	public static void main(String[] args) {
		FtpHandle fd = new FtpHandle();
		fd.connectServer("182.119.171.239", "icsadm", "icsadm","/app/ics/commu_ics");
		File file = fd.download("run.sh");
		fd.closeConnect();
	}
}
