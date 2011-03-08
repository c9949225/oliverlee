
import java.net.*;
import java.io.*;
import javax.net.ssl.*;


public class HttpsSocketClient {
  public static void main(String args[ ])throws Exception {

    try {
        int port = 443;
        System.setProperty("javax.net.ssl.trustStore",
                   "clienttrust");
        String hostname = args[0];
         SSLSocketFactory ssf= 
             (SSLSocketFactory) SSLSocketFactory.getDefault( );
        Socket s = ssf.createSocket(hostname, port);
   
        OutputStream outs=s.getOutputStream( );
        PrintStream out = new PrintStream(outs);
        InputStream  ins = s.getInputStream( );
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));

        out.println("Hi,How are u!");
        out.println("");
	String line=null;
	while((line=in.readLine())!=null){
		System.out.println(line);
	}

        in.close();
        out.close();
    } catch(IOException e) {
    }
  }
}