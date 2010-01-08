package net.oliver.olframework.util.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class Test {

	private static FTPClient ftp_ = new FTPClient();

	public static void main(String[] args) {
		try {
			ftp_.connect("10.200.5.221");
			System.out.println("Connecting...");
			ftp_.login("jboss", "jboss");
			System.out.println("Connetcted");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
		/*	FTPFile[] files = null;
			
			FTPListParseEngine engine;
			ftp_.changeWorkingDirectory("/home/cjbatch/BatchServer/");
			files = ftp_.listFiles();
			
			System.out.println("Directory is " + ftp_.printWorkingDirectory());
			
			for(int i=0;i<files.length;i++)
			{
//				System.out.println(files[i].getName());
//				System.out.println(files[i].isDirectory()?"目录":"文件");
				System.out.println(files[i].getRawListing());
			}*/
			
//			ftp_.changeWorkingDirectory("/pub");
			
			fetchFile(ftp_,"/home/cjbatch/BatchServer/data/cjcif/00002/20080825/cjkf500120081016000001.txt","cjkf500120081016000001.txt");

//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//			System.out.println(dateFormat.format(files[1].getTimestamp()
//					.getTime()));
//			int idx = files[1].getRawListing().indexOf(" ");
//			System.out.println(files[1].getRawListing().substring(0, idx--));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * 从服务器下载文件
	 * @param fileName
	 * @throws IOException
	 */
	public static void fetchFile(FTPClient ftpTool,String fileName,String dest_file_name) throws IOException
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
