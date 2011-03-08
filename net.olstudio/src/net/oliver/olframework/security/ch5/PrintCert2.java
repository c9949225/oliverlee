import java.io.*;
import java.security.*;
/* Certificate
PrintCert2.java:15: reference to Certificate is ambiguous, both class java.security.cert.Certificate in java.security.cert and class java.security.Certificate in java.security match
So import exactly
*/
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
public class PrintCert2{
    
   public static void main(String args[ ]) throws Exception{
          String pass="wshr.ut";
          String alias="mytest";
          String name="mykeystore";

          FileInputStream in=new FileInputStream(name);
          //ע��,K, S����д
          KeyStore ks=KeyStore.getInstance("JKS");           
          ks.load(in,pass.toCharArray());

        Certificate c=ks.getCertificate(alias);
        in.close();
        System.out.println(c);
       
  }

}  


/*        ��ʹ��Ĭ�ϵ�keystore������»�ȡ·������  
    String userhome=System.getProperty("user.home");
          String name=userhome+File.separator+".keystore";
*/
