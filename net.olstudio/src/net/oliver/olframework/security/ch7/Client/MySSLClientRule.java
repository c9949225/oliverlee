import java.net.*;
import java.io.*;
import javax.net.ssl.*;
public class MySSLClientRule{
  public static void main(String args[ ]) throws Exception {
        FileOutputStream fouts=null;
        System.setProperty("javax.net.ssl.trustStore",
                   "clienttrust");
         SSLSocketFactory ssf= 
             (SSLSocketFactory) SSLSocketFactory.getDefault( );
          Socket s = ssf.createSocket("127.0.0.1", 5432);

          OutputStream outs=s.getOutputStream( );
          PrintStream out = new PrintStream(outs);
          InputStream  ins = s.getInputStream( );
          BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));

          out.println(args[0]);
	  		System.out.println("Sent");
          String x=in.readLine( );
	  		System.out.println(x);
          if( x.equals("Sending HTML")){
                fouts=new FileOutputStream("result.html");
           }
           else if( x.equals("Sending GIF")){
		           fouts=new FileOutputStream("result.gif");
          }
          int kk;
          while((kk=ins.read())!=-1){
		  			fouts.write(kk);
          }
          in.close( );
          fouts.close();
    }
}
