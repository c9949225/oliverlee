package net.oliver.olframework.util.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpHandle2 {
	private Logger log = Logger.getLogger(FtpHandle2.class);
	//  ftp�ͻ���
	FTPClient ftpClient;
	//  �ļ��б�
	FTPFile[] fileList;

	/**
	 * @server������������
	 * @user���û���
	 * @password������
	 * @path���������ϵ�·��
	 *  */
	public void connectServer(String server, String user, String password,
			String path) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(server);
			ftpClient.login(user, password);
			log.info(" login success !!! ");
			if (path.length() != 0) {
				boolean flag = ftpClient.changeWorkingDirectory(path);
				if (flag) {
					log.debug(" set working directory successful !!! ");
				}
			}
		} catch (IOException e) {
			log.info(" not login !!! ");
			log.error(e.getMessage());
		}
	}

	/**
	 * �ر�����
	 *  */
	public void closeConnect() {
		try {
			ftpClient.disconnect();
			log.info(" disconnect success !!! ");
		} catch (IOException e) {
			log.info(" not disconnect !!! ");
			log.error(e.getMessage());
		}
	}

	/**
	 * �ϴ������ļ���������
	 *
	 * */
	public void upload() {
		String uploadFileName = "c:\\work";
		try {
			File uploadFile = new File(uploadFileName);
			File[] fileList = uploadFile.listFiles();
			if (fileList == null) {
				return;
			}
			for (int i = 0; i < fileList.length; i++) {
				FileInputStream fis = new FileInputStream(fileList[i]);
				String destinationFileName = fileList[i].getName();
				String tempFileName = " temp_ " + destinationFileName;
				// �ϴ������ļ�����������(�ļ�����'temp_'��ͷ�����ϴ���Ϻ����ָ�Ϊ��ʽ��)
				boolean flag = ftpClient.storeFile(tempFileName, fis);
				if (flag) {
					log.info(" upload success !!! ");
					// �ϴ���Ϻ����ָ�Ϊ��ʽ��(�÷�����Զ����Ч�����ز��ô˷���������renameTo����)
					ftpClient.rename(tempFileName, destinationFileName);
				}
				// �ر��ļ���
				fis.close();
			}
		} catch (IOException e) {
			log.info(" not upload !!! ");
			log.info(e.getMessage());
		}
	}

	/** */
	/**
	 * �ӷ����������ļ�������
	 *  */
	public void download() {
    try {
      fileList = ftpClient.listFiles(); // ��ȡ�������ϵ������ļ� 
//      ftpClient.makeDirectory("zjp"); // �ڷ������ϴ���Ŀ¼(������,��ɾ��)
//      ftpClient.removeDirectory(" zjp "); // �ڷ�������ɾ���ʴ�Ŀ¼,ע���Ŀ¼��Ϊ��(������,��ɾ��)
      for (int i = 0; i < fileList.length; i++) {
        String name = fileList[i].getName();
        File temp_file = new File("c:\\" + "temp_" + name);
        File dest_file = new File("c:\\" + name);
        
        FileOutputStream fos = new FileOutputStream(temp_file);
        // �ӷ������������ļ�
        boolean flag = ftpClient.retrieveFile(name, fos);
        // �ر��ļ���
        fos.close();
        if (flag) {
          // ����rename,ǰ�����ȹر��ļ���
          temp_file.renameTo(dest_file);
          log.info(" download success !!! ");
        }
      }
    }
    catch (IOException e) {
      log.info(" not download !!! ");
      log.error(e.getMessage());
    }
  }

	/** */
	/**
	 * ���Ժ���
	 *  */
	public static void main(String[] args) {
		FtpHandle2 fd = new FtpHandle2();
		fd.connectServer(" 127.0.0.1 ", " iss ", " iss ",
				" /home/iss/connectCDR ");
		fd.upload();
		fd.download();
		fd.closeConnect();
	}

}
