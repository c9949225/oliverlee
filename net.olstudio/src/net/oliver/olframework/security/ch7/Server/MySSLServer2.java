import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MySSLServer2{
  public static void main(String args[ ]) throws Exception{
         SSLServerSocketFactory ssf= 
             (SSLServerSocketFactory) SSLServerSocketFactory.getDefault( );
         ServerSocket ss=ssf.createServerSocket(5432);
	System.out.println("Waiting for connection...");
         while(true){
	   Socket s=ss.accept( );
	   PrintStream out = new PrintStream(s.getOutputStream( ));
	   out.println("Hi");
	   out.close( );
           s.close( );
       }

  }
}
