import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MySSLServerRule{
  public static void main(String args[ ]) throws Exception{
        byte[] x={
		(byte)0x47,(byte)0x49,(byte)0x46,(byte)0x38,(byte)0x39,
		(byte)0x61,(byte)0x05,(byte)0x00,(byte)0x05,(byte)0x00,
		(byte)0x80,(byte)0xff,(byte)0x00,(byte)0xff,(byte)0xff,
		(byte)0xff,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x2c,
		(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x05,
		(byte)0x00,(byte)0x05,(byte)0x00,(byte)0x40,(byte)0x02,
		(byte)0x07,(byte)0x44,(byte)0x0e,(byte)0x86,(byte)0xc7,
		(byte)0xed,(byte)0x51,(byte)0x00,(byte)0x00,(byte)0x3b
	     };
        System.setProperty("javax.net.ssl.keyStore","mykeystore");
        System.setProperty("javax.net.ssl.keyStorePassword","wshr.ut");
        SSLServerSocketFactory ssf= 
             (SSLServerSocketFactory) 
                    SSLServerSocketFactory.getDefault( );
        ServerSocket ss=ssf.createServerSocket(5432);
		System.out.println("Waiting for connection...");
        while(true){
	   		Socket s=ss.accept( );
            OutputStream outs=s.getOutputStream( );
	   		PrintStream out = new PrintStream(outs);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream( )));
            String line=in.readLine();
            System.out.println("Got "+line);
            if (line.endsWith(".html")){
             System.out.println("Now Sending HTML");
             out.println("Sending HTML");
	         out.println("<HTML><HEAD><TITLE>Test SSL</TITLE></HEAD>");
	         out.println("<BODY><h1> This is a test</BODY>");
	         out.println("</HTML>");
           }
	   else if(line.endsWith(".gif")){
                System.out.println("Now Sending GIF");
                out.println("Sending GIF");
                outs.write(x);
                out.println("");
           }
	   out.close( );
           s.close( );
       }

  }
}
