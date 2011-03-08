import java.io.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
public class MySelector{
   public static void main(String args[ ]) throws Exception{
        //����CertStore����
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	        FileInputStream in=new FileInputStream(args[i]);
        	Certificate c=cf.generateCertificate(in);
	      	mylist.add(c);
        }
       CertStoreParameters cparam=
new CollectionCertStoreParameters(mylist);
       CertStore cs=CertStore.getInstance("Collection",cparam);
       //���ù���,ע�ⶺ�ź��治���пո�
      X509CertSelector selec=new X509CertSelector();
      selec.setIssuer("CN=Xu Yingxiao,OU=Network Center,"+
                "O=Shanghai University,L=ZB,ST=Shanghai,C=CN");
      //��ȡ֤��
      Set clct=(Set) cs.getCertificates(selec);
      Object o[]=clct.toArray();
      for(int i=0;i<o.length;i++){
           X509Certificate ct=(X509Certificate)o[i];
           System.out.println("Certificate "+i+" ");
           System.out.println(ct.getSubjectDN());

     }
  }
}  
