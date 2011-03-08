import java.io.*;
import java.net.*;
import java.security.*;
class HttpsClient {
     public static void main(String args[ ]) throws IOException{
        String line;
        URL u = new URL(args[0]);

//  System.setProperty("java.protocol.handler.pkgs",
//      "com.sun.net.ssl.internal.www.protocol");
// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        InputStream  in = u.openStream( );
        BufferedReader  f= new BufferedReader(new InputStreamReader (in));
        while ((line = f.readLine( )) != null) {
                 System.out.println(line+"\n");
        }
	in.close();
  }
}
