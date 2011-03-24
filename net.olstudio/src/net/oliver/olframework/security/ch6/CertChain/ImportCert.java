import java.io.*;
import java.security.*;
import java.security.cert.*;
public class ImportCert{
     public static void main(String args[ ]) throws Exception{
	//����
        String cacert="mytest.cer";
        String lfcert="lf_signed.cer";
        String lfstore="lfkeystore";
        char[] lfstorepass="wshr.ut".toCharArray( );
        char[] lfkeypass="wshr.ut".toCharArray( );
	//CA "Xu Yingxiao"��֤��
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in1=new FileInputStream(cacert);
        java.security.cert.Certificate cac=cf.generateCertificate(in1);
        in1.close();
	//�û�"Liu Fang"��ǩ��֤��
        FileInputStream in2=new FileInputStream(lfcert);
        java.security.cert.Certificate lfc=cf.generateCertificate(in2);
        in2.close();
	//֤����
        java.security.cert.Certificate[] cchain={lfc,cac};
	//�û�����Կ��
        FileInputStream in3=new FileInputStream(lfstore);
        KeyStore ks=KeyStore.getInstance("JKS");
        ks.load(in3,lfstorepass);
	PrivateKey prk=(PrivateKey)ks.getKey("lf",lfkeypass);
	//����֤��
        ks.setKeyEntry("lf_signed",prk,lfstorepass,cchain);
	//������Կ��
       FileOutputStream out4=new FileOutputStream("lfnewstore");
       ks.store(out4,"newpass".toCharArray());
       out4.close(); 
  }
}  