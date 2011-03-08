import java.io.*;
import java.security.*;

import java.security.cert.*;
import java.util.*;
import java.math.*;
// import sun.security.x509.*;
public class ShowCertInfo{
    
   public static void main(String args[ ]) throws Exception{
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in=new FileInputStream(args[0]);
        java.security.cert.Certificate c=cf.generateCertificate(in);
        in.close();

        X509Certificate t=(X509Certificate) c;
        System.out.println("�汾�� "+t.getVersion());
        System.out.println("���к� "+t.getSerialNumber().toString(16));
        System.out.println("ȫ�� "+t.getSubjectDN());
        System.out.println("ǩ����ȫ��\n"+t.getIssuerDN());
        System.out.println("��Ч����ʼ�� "+t.getNotBefore());
        System.out.println("��Ч�ڽ����� "+t.getNotAfter());
        System.out.println("ǩ���㷨 "+t.getSigAlgName());
        byte[] sig=t.getSignature();
        System.out.println("ǩ��\n"+new BigInteger(sig).toString(16));
        PublicKey pk=t.getPublicKey();
        byte[ ] pkenc=pk.getEncoded();
	System.out.println("��Կ");
	for(int i=0;i<pkenc.length;i++){
		System.out.print(pkenc[i]+",");
	}
  }
}  


