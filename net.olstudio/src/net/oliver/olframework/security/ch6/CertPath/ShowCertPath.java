import java.io.*;
import java.util.*;
import java.security.cert.*;

public class ShowCertPath{
             
   public static void main(String args[ ]) throws Exception{
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	              FileInputStream in=new FileInputStream(args[i]);
        	     Certificate c=cf.generateCertificate(in);
	      	      mylist.add(c);
        }
        CertPath cp = cf.generateCertPath(mylist);
        List cplist=cp.getCertificates( );
        Object[ ] o=cplist.toArray();
       for(int i=0; i<o.length;i++){
           X509Certificate c=(X509Certificate) o[i];
	   System.out.println(c.getSubjectDN( ));
  	   System.out.println("Owns PublicKey:");
           byte[ ] pbk=c.getPublicKey( ).getEncoded( );
	   for(int j=0;j<pbk.length;j++){
                  System.out.print(pbk[j]+",");
           }
  	   System.out.println("\nIssued by "+c.getIssuerDN( ));
  	   System.out.println("--------------------");
      }
  }
}  



