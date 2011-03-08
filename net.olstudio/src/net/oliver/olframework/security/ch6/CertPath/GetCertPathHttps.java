import java.io.*;
import java.util.*;
import java.security.cert.*;
import javax.net.ssl.*;


public class GetCertPathHttps {
  public static void main(String args[ ])throws Exception {
        int port = 443;
        String hostname =args[0];
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket(hostname, port);
        // Connect to the server
        socket.startHandshake();
        SSLSession session=socket.getSession();
        // Retrieve the server's certificate chain
        java.security.cert.Certificate[] servercerts =
                             session.getPeerCertificates();

        List mylist = new ArrayList();
        for(int i=0;i<servercerts.length;i++){
	      	      mylist.add(servercerts[i]);
        }

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        CertPath cp = cf.generateCertPath(mylist);

	System.out.println(cp);
        FileOutputStream  f=new FileOutputStream("CertPath.dat");
        ObjectOutputStream b=new  ObjectOutputStream(f);
	b.writeObject(cp);



  }
}
