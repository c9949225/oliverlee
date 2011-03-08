import java.util.*;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
public class CStore{
   public static void main(String args[ ]) throws Exception{

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	        FileInputStream in=new FileInputStream(args[i]);
        	Certificate c=cf.generateCertificate(in);
	      	mylist.add(c);
        }
       CertStoreParameters cparam=new CollectionCertStoreParameters(mylist);
       CertStore cs=CertStore.getInstance("Collection",cparam);
	System.out.println(cs.getCertStoreParameters() );
	System.out.println(cs.getProvider());
	System.out.println(cs.getType());

  }
}  
