package net.oliver.olframework.util.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 简单的Ftp文件下载工具
 * 
 * @author u
 *
 */
public class FtpHandle {
	
	private Log logger = LogFactory.getLog(FtpHandle.class);
	
	FTPClient ftpClient;
	
	// 登录到FTP服务器
	public void connectServer(String server, String user, String password,String path) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(server);
			ftpClient.login(user, password);
			logger.info("成功登录到FTP服务器:" + server);
			if (path.length() != 0) {
				boolean flag = ftpClient.changeWorkingDirectory(path);
				if (flag) {
					logger.info("Ftp当前工作目录更换为指定目录...! ");
				}
			}
		} catch (IOException e) {
			logger.error("登录到FTP服务器时出错："+e.getMessage());
		}
	}

	// 关闭连接
	public void closeConnect() {
		try {
			ftpClient.disconnect();
			logger.info(" disconnect success !!! ");
		} catch (IOException e) {
			logger.info(" not disconnect !!! ");
			logger.error(e.getMessage());
		}
	}

	//  从服务器下载文件到本地
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
			logger.error("从FTP服务器下载"+filename+"时出错:"+e.getMessage());
		}
		return null;
	}

	// 用法
	public static void main(String[] args) {
		FtpHandle fd = new FtpHandle();
		fd.connectServer("182.119.171.239", "icsadm", "icsadm","/app/ics/commu_ics");
		File file = fd.download("run.sh");
		fd.closeConnect();
	}
}
