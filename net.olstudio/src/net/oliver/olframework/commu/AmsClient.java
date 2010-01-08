package net.oliver.olframework.commu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class AmsClient {

	private String addr; // server address
	private int port; // server port
	private int timeout; // timeout

	byte[] bytr;
	private StringBuffer snd;

	public AmsClient(String addr, int port, int timeout) {
		this.addr = addr;
		this.port = port;
		this.timeout = timeout;
		this.bytr = new byte[1985]; //1024
		this.snd = new StringBuffer(1024);
	}

	public AmsClient(String addr, int port, int timeout, int sndsize,
			int rcvsize) {
		this.addr = addr;
		this.port = port;
		this.timeout = timeout;
		if (rcvsize <= 1024)
			rcvsize = 1024;
		this.bytr = new byte[rcvsize];
		if (sndsize <= 512)
			sndsize = 512;
		this.snd = new StringBuffer(sndsize);
	}

	public String exchangeByte(String app, String code, String sndData)
			throws IOException {
		if (app.length() != 2)
			throw new IOException("application lenth must be 2 bytes.");
		if (code.length() != 6)
			throw new IOException("trade code lenth must be 6 bytes.");
		snd.replace(0, snd.length(), "0000");
		snd.append(app).append(code);

		int len = sndData.getBytes().length;
		snd.append(len + 67);

		for (len = 18 - snd.length(); len > 0; len--)
			snd.insert(12, 0);

		snd.append(sndData);
		Socket socket = new Socket(addr, port); // 端口就可以用来区分不同的应用

		OutputStream out = socket.getOutputStream();

		//开始通讯..加67位公共信息
		snd.insert(18,"9000000000000000000000000000000000000000000000000000000000000000009");
		//	  byte[] bytw = addCommonInfo(snd).toString().getBytes();
		byte[] bytw = snd.toString().getBytes();

		out.write(bytw);
		out.flush();
		InputStream in = socket.getInputStream();
		readn(in, bytr, 18);
		boolean bError = false;
		if (new String(bytr, 4, 8).compareTo("sesyserr") == 0)
			bError = true;
		len = 0;
		for (int i = 12; i < 18; i++)
			len = len * 10 + bytr[i] - '0';
		String result = readn2(in);
		in.close();
		out.close();
		socket.close();
		if (bError)
			throw new IOException(new String(bytr, 0, len));

		//	  return new String(bytr,0,len);
		return result;
	}

	public String exchange(String app, String code, String sndData, int charnum)
			throws IOException {
		if (app.length() != 2)
			throw new IOException("application lenth must be 2 bytes.");
		if (code.length() != 6)
			throw new IOException("trade code lenth must be 6 bytes.");
		snd.replace(0, snd.length(), "0000");
		snd.append(app).append(code);
		int len = sndData.length();
		if (charnum > 0) {
			len += charnum;
		}
		snd.append(len + 67);
		for (len = 18 - snd.length(); len > 0; len--)
			snd.insert(12, 0);
		snd.append(sndData);
		Socket socket = new Socket(addr, port);

		OutputStream out = socket.getOutputStream();

		//开始通讯..加67位公共信息
		snd
				.insert(18,
						"9000000000000000000000000000000000000000000000000000000000000000009");
		//	  byte[] bytw = addCommonInfo(snd).toString().getBytes();
		byte[] bytw = snd.toString().getBytes();

		out.write(bytw);
		out.flush();
		InputStream in = socket.getInputStream();
		readn(in, bytr, 18);
		boolean bError = false;
		if (new String(bytr, 4, 8).compareTo("sesyserr") == 0)
			bError = true;
		//	  System.out.println("head="+new String(bytr,0,18));
		len = 0;
		for (int i = 12; i < 18; i++)
			len = len * 10 + bytr[i] - '0';
		//	  System.out.println("body len="+len);
		readn(in, bytr, len);
		//	  System.out.println("body="+new String(bytr,0,len));
		in.close();
		out.close();
		socket.close();
		if (bError)
			throw new IOException(new String(bytr, 0, len));

		return new String(bytr, 0, len);
	}

	public byte[] exchangeForByte(String app, String code, String sndData,
			int charnum) throws IOException {
		if (app.length() != 2)
			throw new IOException("application lenth must be 2 bytes.");
		if (code.length() != 6)
			throw new IOException("trade code lenth must be 6 bytes.");
		snd.replace(0, snd.length(), "0000");
		snd.append(app).append(code);
		int len = sndData.length();
		if (charnum > 0) {
			len += charnum;
		}
		snd.append(len + 67);
		for (len = 18 - snd.length(); len > 0; len--)
			snd.insert(12, 0);
		snd.append(sndData);
		Socket socket = new Socket(addr, port);

		OutputStream out = socket.getOutputStream();

		//开始通讯..加67位公共信息
		snd
				.insert(18,
						"9000000000000000000000000000000000000000000000000000000000000000009");
		//	  byte[] bytw = addCommonInfo(snd).toString().getBytes();
		byte[] bytw = snd.toString().getBytes();

		out.write(bytw);
		out.flush();
		InputStream in = socket.getInputStream();
		readn(in, bytr, 18);
		boolean bError = false;
		if (new String(bytr, 4, 8).compareTo("sesyserr") == 0)
			bError = true;
		//	  System.out.println("head="+new String(bytr,0,18));
		len = 0;
		for (int i = 12; i < 18; i++)
			len = len * 10 + bytr[i] - '0';
		//	  System.out.println("body len="+len);
		readn(in, bytr, len);
		//	  System.out.println("body="+new String(bytr,0,len));
		in.close();
		out.close();
		socket.close();
		if (bError)
			throw new IOException(new String(bytr, 0, len));

		//	  return new String(bytr,0,len);
		return bytr;
	}

	private static int readn(InputStream in, byte[] buf, int len)
			throws IOException {

		//		 byte buffer[] = new byte[4096];
		//		 for(int length = 0; (length = is.read(buffer)) > 0;)
		//		 	os.write(buffer, 0, length);

		int cnt, n = 0;
		for (cnt = 0; n < len; n += cnt)
			cnt = in.read(buf, n, len - n);
		return len;
	}

	private static String readn2(InputStream in) throws IOException {
		String result = "";
		byte buffer[] = new byte[4096];
		for (int length = 0; (length = in.read(buffer)) > 0;)
			result += new String(buffer);
		return result;
	}

	public static StringBuffer addCommonInfo(StringBuffer input) {
		input
				.insert(18,
						"9000000000000000000000000000000000000000000000000000000000000000009");
		return input;
	}

}
