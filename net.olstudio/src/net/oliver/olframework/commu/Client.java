package net.oliver.olframework.commu;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
public class Client {
	public static InputStream in;
	public static OutputStream out;
	public static String URL = "182.119.172.18";
	public static String sendString = "";
	public static Client client;
	static {
		try {
			client = new Client();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private Client() throws IOException {

		InetAddress addr = InetAddress.getByName(URL);
		// System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, 9901);
		// socket.
		// socket.sendUrgentData(data);
		try {
//			in = new BufferedReader(new InputStreamReader(socket
//					.getInputStream()));
			in = socket.getInputStream();
			out = socket.getOutputStream();
//			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
//					socket.getOutputStream())), true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getReceived() {
		String receiveString = "";
		try {
			// while(( receiveString=in.readLine().trim())!=null||(
			// receiveString=in.readLine().trim())!="")
			// {
			// receiveString=in.readLine().trim()+receiveString;
			// }
			//	
			if (in != null) {
				 byte [] test=new byte[256];

			in.read(test);
		    String str = new String(test);
		    System.out.println("在client中得到的： " + str);
		    in.read(test);
		    String str1 = new String(test);
		    receiveString=str1.trim();
		    System.out.println("在client中得到的1： " + receiveString);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return receiveString;
	}
	public void sendString(String buff) {
		sendString = buff;
		try {
			byte [] bytw;
			bytw = sendString.getBytes();
			   out.write(bytw);
//			out.println(sendString);
//			out.flush();
			System.out.println("发送到服务器的登陆信息：" + sendString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("在发送数据时发生异常");
		}

	}
	public void closeOut() {
		try {
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void closeIn() {
		try {
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	public void close() {
		try {
			out.close();
			in.close();
			client.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Client getClient() {

		return client;

	}
}
