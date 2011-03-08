import java.security.cert.*;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MyHTTPSServerAuth {
  public static void main(String args[ ]) {
       int i=0;
       try {
         SSLServerSocketFactory ssf= (SSLServerSocketFactory) SSLServerSocketFactory.getDefault( );
         SSLServerSocket ss=(SSLServerSocket)ssf.createServerSocket(443);
         ss.setNeedClientAuth(true);
         System.out.println("Web Server OK2 ");

         while(true){
            Socket s=ss.accept( );  //µÈ´ýÇëÇó
/*
SSLSession session=((SSLSocket) s).getSession();	 
Certificate[ ] cchain=session.getPeerCertificates( );
        System.out.println("The Certificates used by peer");
        for(int j=0;j<cchain.length;j++){
               System.out.println( ((X509Certificate)cchain[j]).
getSubjectDN() );
}
*/


            PrintStream out = new PrintStream(s.getOutputStream( ));
	    BufferedReader in = new BufferedReader(
                       new InputStreamReader(s.getInputStream( )));
	    String info=null;
            while(( info=in.readLine())!=null){
                   System.out.println("now got "+info);
                   if(info.equals("")) break;
            }

  	    System.out.println("now go");
  	    out.println("HTTP/1.0 200 OK");
	    out.println("MIME_version:1.0");
	    out.println("Content_Type:text/html");
            i++;
	    String c="<html> <head></head><body> <h1> Hi,  this is "
					+i+"</h1></Body></html>";
	    out.println("Content_Length:"+c.length( ));
            out.println("");
	    out.println(c);
	    out.close( );
	    s.close( );
	    in.close( );
       }
     } catch (IOException e) { 
	   System.out.println(e);
     }
 }
}
