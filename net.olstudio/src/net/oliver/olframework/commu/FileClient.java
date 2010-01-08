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
		 //�����ļ���������ȡ�ļ��е�����

		File file = new File(fn);
		InputStream fos = new FileInputStream(file);
		
		Socket server = new Socket(InetAddress.getByName(FileClient.ADDRESS), FileClient.PORT);
		
        //����������������ṩ���ݰ�װ��
		OutputStream netOut = server.getOutputStream();
		OutputStream doc = new DataOutputStream(new BufferedOutputStream(netOut));
		
		//�����ļ���ȡ������

		byte[] buf = new byte[2048];

		int num = fos.read(buf);

		while (num != (-1)) {//�Ƿ�����ļ�

			doc.write(buf, 0, num);//���ļ�����д�����绺����

			doc.flush();//ˢ�»�����������д���ͻ���

			num = fos.read(buf);//�������ļ��ж�ȡ����

		}

		fos.close();

		doc.close();
	}

}
