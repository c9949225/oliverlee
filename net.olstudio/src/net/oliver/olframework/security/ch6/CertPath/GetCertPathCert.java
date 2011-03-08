import java.io.*;
import java.util.*;
import java.security.cert.*;

public class GetCertPathCert{
   public static void main(String args[ ]) throws Exception{
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	        FileInputStream in=new FileInputStream(args[i]);
        	Certificate c=cf.generateCertificate(in);
	      	mylist.add(c);
        }
        CertPath cp = cf.generateCertPath(mylist);
	System.out.println(cp);
  }
}  


