import java.net.*;
import java.io.*;
public class MyWebServer {
  public static void main(String args[ ]) {
       int i=0;
       try {
         ServerSocket ss=new ServerSocket(80);
         System.out.println("Web Server OK ");

         while(true){
            Socket s=ss.accept( );  //µÈ´ýÇëÇó
            PrintStream out = new PrintStream(s.getOutputStream( ));
	    BufferedReader in = new BufferedReader(
                       new InputStreamReader(s.getInputStream( )));
	    String info=null;
            while(( info=in.readLine())!=null){
                   System.out.println("now got "+info);
                   if(info.equals("")) break;
            }
  	    out.println("HTTP/1.0 200 OK");
	    out.println("MIME_version:1.0");
	    out.println("Content_Type:text/html");
            i++;
	    String c="<html> <head></head><body> <h1> hi  This is "
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
