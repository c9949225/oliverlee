import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class MySSLServerKs {
  public static void main(String args[ ])throws Exception {
        SSLContext context;
        KeyManagerFactory kmf;
	KeyStore ks;
        char[] storepass="newpass".toCharArray();
        char[] keypass="wshr.ut".toCharArray();
        String storename="lfnewstore";

	context=SSLContext.getInstance("TLS");
        kmf=KeyManagerFactory.getInstance("SunX509");
        FileInputStream fin=new FileInputStream(storename);
        ks=KeyStore.getInstance("JKS");
        ks.load(fin,storepass);

        kmf.init(ks,keypass);
        context.init(kmf.getKeyManagers(),null,null);
        SSLServerSocketFactory ssf= context.getServerSocketFactory(); 

//         SSLServerSocketFactory ssf= 
//             (SSLServerSocketFactory) SSLServerSocketFactory.getDefault( );


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

