import java.net.*;
import java.io.*;
public class  MyThreadWebServer2 {
	public static void main(String args[])	{
		try {
			ServerSocket ss=new ServerSocket(80);  
			System.out.println("Web Server OK");
			while (true) {
				Socket s=ss.accept();  
				Process p=new Process(s);
				Thread  t=new Thread(p);
				t.start( );
			}
		} catch (Exception e) {System.out.println(e);}
	}
}


class Process implements  Runnable{
	Socket s;  
	public Process (Socket s1) {
		s=s1;
	}
	public void run()   
	{       
		try {
			PrintStream out = new PrintStream(s.getOutputStream());
			BufferedReader in 
				= new BufferedReader(new InputStreamReader(s.getInputStream()));
	                String info=in.readLine();
        	        System.out.println("now got "+info);
			out.println("HTTP/1.0 200 OK");
			out.println("MIME_version:1.0");
			out.println("Content_Type:text/html");
                        // 浏览器请求形如  GET /t/1.html HTTP/1.1
			// sp1, sp2为第一次和第二次出现空格的位置，
			// filename从浏览器请求中提取出文件路径和名称 如 t/1.html
			int sp1=info.indexOf(' ');
                        int sp2=info.indexOf(' ',sp1+1);
                        String filename=info.substring(sp1+2,sp2);
                        // 若浏览器请求中无文件名，则加上默认文件名index.html
                        if(filename.equals("") || filename.endsWith("/")) filename+="index.html";
                        System.out.println("Sending "+filename);
                        // 向浏览器发送文件
                        File fi=new File(filename);
                        InputStream fs=new FileInputStream(fi);
                        int n=fs.available();
                        byte buf[]=new byte[1024];
			out.println("Content_Length:"+n);
	                out.println("");
                        while ((n=fs.read(buf))>=0){
                                out.write(buf,0,n);
                        }
			out.close();
			s.close();
			in.close( );
		} catch (IOException e) {
			System.out.println("Exception:"+e);
		}
	}
}
