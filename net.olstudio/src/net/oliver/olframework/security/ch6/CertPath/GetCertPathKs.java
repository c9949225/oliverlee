import java.io.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;

public class GetCertPathKs{
             
   public static void main(String args[ ]) throws Exception{
       String storename=args[0];
       char[ ] storepass=args[1].toCharArray();
       String alias=args[2];
       KeyStore ks = KeyStore.getInstance("JKS");
       ks.load(new FileInputStream(storename),storepass);
       java.security.cert.Certificate[] cchain =ks.getCertificateChain(alias);
        List mylist = new ArrayList();
        for(int i=0;i<cchain.length;i++){
		mylist.add(cchain[i]);
        }
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        CertPath cp = cf.generateCertPath(mylist);
	System.out.println(cp);
      }
}  



