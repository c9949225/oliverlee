package net.oliver.olframework.commu;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {

	private static int BUFSIZE = 1024;

	private static String ADDRESS = "";

	private static int PORT = 0;

	public FileClient() {
		super();
	}

	public void transFile(String fn) throws UnknownHostException,
			IOException, Exception {
		 //创建文件流用来读取文件中的数据

		File file = new File(fn);
		InputStream fos = new FileInputStream(file);
		
		Socket server = new Socket(InetAddress.getByName(FileClient.ADDRESS), FileClient.PORT);
		
        //创建网络输出流并提供数据包装器
		OutputStream netOut = server.getOutputStream();
		OutputStream doc = new DataOutputStream(new BufferedOutputStream(netOut));
		
		//创建文件读取缓冲区

		byte[] buf = new byte[2048];

		int num = fos.read(buf);

		while (num != (-1)) {//是否读完文件

			doc.write(buf, 0, num);//把文件数据写出网络缓冲区

			doc.flush();//刷新缓冲区把数据写往客户端

			num = fos.read(buf);//继续从文件中读取数据

		}

		fos.close();

		doc.close();
	}

}
