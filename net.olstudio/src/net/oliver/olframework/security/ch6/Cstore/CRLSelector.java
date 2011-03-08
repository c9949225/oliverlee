import java.io.*;
import java.math.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
public class CRLSelector{
   public static void main(String args[ ]) throws Exception{
        //����CertStore����
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	        FileInputStream in=new FileInputStream(args[i]);
               X509CRL crl = (X509CRL)cf.generateCRL(in);
	      	mylist.add(crl);
        }
       CertStoreParameters cparam=new CollectionCertStoreParameters(mylist);
       CertStore cs=CertStore.getInstance("Collection",cparam);
        //����Selector
       X509CRLSelector selec=new X509CRLSelector();
 //���ù���,ע�ⶺ�ź��治���пո�
       selec.addIssuerName("CN=E-Commerce PKI CA,"+
"OU=WISeKey Affiliate CA,O=E-Commerce PKI,C=CH");
       //��ȡ֤������嵥
       Set clct=(Set) cs.getCRLs(selec);
       Object o[]=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));
       }
//���ù���ע��ת�����
String name="CN=VeriSign Class 2 CA - Individual Subscriber,"
          +"OU=www.verisign.com/repository/RPA Incorp."
+" By Ref.\\,LIAB.LTD(c)98,"  //Byǰ���и��ո�
          +"OU=VeriSign Trust Network,"
          +"O=VeriSign\\, Inc.";
        selec.addIssuerName(name);
       //��ȡ֤������嵥
       clct=(Set) cs.getCRLs(selec);
       o=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));

      }
//���ù���ע��ת�����
selec.setIssuerNames(null);
//��ȡ֤������嵥
       clct=(Set) cs.getCRLs(selec);

       o=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));
      }
  }
}  
