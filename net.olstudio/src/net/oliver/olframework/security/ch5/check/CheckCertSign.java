import java.io.*;
import java.security.*;
import java.security.cert.*;
public class CheckCertSign{
     public static void main(String args[ ]) throws Exception{
	//����
        String cacert=args[0];
        String lfcert=args[1];
	//CA "Xu Yingxiao"��֤��
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in1=new FileInputStream(cacert);
        java.security.cert.Certificate cac=cf.generateCertificate(in1);
        in1.close();
	//�û�"Liu Fang"��ǩ��֤��
        FileInputStream in2=new FileInputStream(lfcert);
        java.security.cert.Certificate lfc=cf.generateCertificate(in2);
        in2.close();

        PublicKey pbk=cac.getPublicKey( );
	boolean pass=false;
	try{
	        lfc.verify(pbk);
		pass=true;
        }
	catch(Exception e){
		pass=false;
		System.out.println(e);
	}
	if(pass){
		System.out.println("The Certificate is signed by the CA Xu Yingxiao");
	}
	else{
		System.out.println("!!!The Certificate is not signed by the CA Xu Yingxiao");
	}
  }
}  