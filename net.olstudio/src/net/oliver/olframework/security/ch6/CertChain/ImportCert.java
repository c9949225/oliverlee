import java.io.*;
import java.security.*;
import java.security.cert.*;
public class ImportCert{
     public static void main(String args[ ]) throws Exception{
	//参数
        String cacert="mytest.cer";
        String lfcert="lf_signed.cer";
        String lfstore="lfkeystore";
        char[] lfstorepass="wshr.ut".toCharArray( );
        char[] lfkeypass="wshr.ut".toCharArray( );
	//CA "Xu Yingxiao"的证书
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in1=new FileInputStream(cacert);
        java.security.cert.Certificate cac=cf.generateCertificate(in1);
        in1.close();
	//用户"Liu Fang"的签名证书
        FileInputStream in2=new FileInputStream(lfcert);
        java.security.cert.Certificate lfc=cf.generateCertificate(in2);
        in2.close();
	//证书链
        java.security.cert.Certificate[] cchain={lfc,cac};
	//用户的密钥库
        FileInputStream in3=new FileInputStream(lfstore);
        KeyStore ks=KeyStore.getInstance("JKS");
        ks.load(in3,lfstorepass);
	PrivateKey prk=(PrivateKey)ks.getKey("lf",lfkeypass);
	//导入证书
        ks.setKeyEntry("lf_signed",prk,lfstorepass,cchain);
	//保存密钥库
       FileOutputStream out4=new FileOutputStream("lfnewstore");
       ks.store(out4,"newpass".toCharArray());
       out4.close(); 
  }
}  
