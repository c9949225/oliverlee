import java.io.*;
import java.util.*;
import java.security.cert.*;
import java.security.*;
import java.security.cert.X509Certificate;

public class StoreCert {
  public static void main(String args[ ])throws Exception {
        FileInputStream  f=new FileInputStream("CertPath.dat");
        ObjectInputStream b=new  ObjectInputStream(f);
	CertPath cp=(CertPath)b.readObject();

	KeyStore ks=KeyStore.getInstance("JKS");
        ks.load(null,null);
        List cplist=cp.getCertificates( );
	Object[ ] o=cplist.toArray();
	for(int i=0; i<o.length;i++){
	      X509Certificate c=(X509Certificate) o[i];
              ks.setCertificateEntry("my"+i,c);
	}
        FileOutputStream output=new FileOutputStream("MyCertPathStore");
        ks.store(output,"mypass".toCharArray( ));
        output.close(); 

  }
}
