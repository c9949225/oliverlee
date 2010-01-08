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
	//  ftp客户端
	FTPClient ftpClient;
	//  文件列表
	FTPFile[] fileList;

	/**
	 * @server：服务器名字
	 * @user：用户名
	 * @password：密码
	 * @path：服务器上的路径
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
	 * 关闭连接
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
	 * 上传本地文件到服务器
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
				// 上传本地文件到服务器上(文件名以'temp_'开头，当上传完毕后，名字改为正式名)
				boolean flag = ftpClient.storeFile(tempFileName, fis);
				if (flag) {
					log.info(" upload success !!! ");
					// 上传完毕后，名字改为正式名(该方法在远程有效，本地不用此方法，而用renameTo方法)
					ftpClient.rename(tempFileName, destinationFileName);
				}
				// 关闭文件流
				fis.close();
			}
		} catch (IOException e) {
			log.info(" not upload !!! ");
			log.info(e.getMessage());
		}
	}

	/** */
	/**
	 * 从服务器下载文件到本地
	 *  */
	public void download() {
    try {
      fileList = ftpClient.listFiles(); // 获取服务器上的所有文件 
//      ftpClient.makeDirectory("zjp"); // 在服务器上创建目录(测试用,可删除)
//      ftpClient.removeDirectory(" zjp "); // 在服务器上删除词此目录,注意该目录下为空(测试用,可删除)
      for (int i = 0; i < fileList.length; i++) {
        String name = fileList[i].getName();
        File temp_file = new File("c:\\" + "temp_" + name);
        File dest_file = new File("c:\\" + name);
        
        FileOutputStream fos = new FileOutputStream(temp_file);
        // 从服务器上下载文件
        boolean flag = ftpClient.retrieveFile(name, fos);
        // 关闭文件流
        fos.close();
        if (flag) {
          // 本地rename,前提是先关闭文件流
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
	 * 测试函数
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
