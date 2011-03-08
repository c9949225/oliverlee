import java.io.*;
import java.math.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
public class CRLSelector{
   public static void main(String args[ ]) throws Exception{
        //创建CertStore对象
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        List mylist = new ArrayList();
        for(int i=0;i<args.length;i++){
	        FileInputStream in=new FileInputStream(args[i]);
               X509CRL crl = (X509CRL)cf.generateCRL(in);
	      	mylist.add(crl);
        }
       CertStoreParameters cparam=new CollectionCertStoreParameters(mylist);
       CertStore cs=CertStore.getInstance("Collection",cparam);
        //创建Selector
       X509CRLSelector selec=new X509CRLSelector();
 //设置规则，,注意逗号后面不可有空格
       selec.addIssuerName("CN=E-Commerce PKI CA,"+
"OU=WISeKey Affiliate CA,O=E-Commerce PKI,C=CH");
       //提取证书吊销清单
       Set clct=(Set) cs.getCRLs(selec);
       Object o[]=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));
       }
//设置规则，注意转义符号
String name="CN=VeriSign Class 2 CA - Individual Subscriber,"
          +"OU=www.verisign.com/repository/RPA Incorp."
+" By Ref.\\,LIAB.LTD(c)98,"  //By前面有个空格
          +"OU=VeriSign Trust Network,"
          +"O=VeriSign\\, Inc.";
        selec.addIssuerName(name);
       //提取证书吊销清单
       clct=(Set) cs.getCRLs(selec);
       o=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));

      }
//设置规则，注意转义符号
selec.setIssuerNames(null);
//提取证书吊销清单
       clct=(Set) cs.getCRLs(selec);

       o=clct.toArray();
       System.out.println("Find "+o.length);
       for(int i=0;i<o.length;i++){
             X509CRL crl = (X509CRL)o[i];
             System.out.println("issuer = "+crl.getIssuerDN().getName( ));
      }
  }
}  
