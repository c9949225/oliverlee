/*

C:\java\ch6\Cstore>java DefineSelector my0.cer 2003 1 1
not Matched

C:\java\ch6\Cstore>java DefineSelector mytest.cer 2003 1 1
Matched

C:\java\ch6\Cstore>java DefineSelector mytest.cer 2013 1 1
Matched

C:\java\ch6\Cstore>java DefineSelector mytest.cer 2113 1 1
not Matched


*/

import java.util.*;
import java.io.*;
import java.math.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
public class DefineSelector{
   public static void main(String args[ ]) throws Exception{
      X509CertSelector selec=new X509CertSelector();
      //�������ж�ȡ֤��
      CertificateFactory cf = CertificateFactory.getInstance("X.509");
      FileInputStream in=new FileInputStream(args[0]);
     	Certificate c=cf.generateCertificate(in);
      //�����Ƿ��������
      if(selec.match(c)){
System.out.println("Matched 1");
      } else{
System.out.println("not Matched 1");
      }
      //����һ������
      selec.setIssuer("CN=Xu Yingxiao,OU=Network Center,"+
                   "O=Shanghai University,L=ZB,ST=Shanghai,C=CN");
      //�����Ƿ��������
      if(selec.match(c)){
System.out.println("Matched 2");
      } else{
	System.out.println("not Matched 2");
      }
      //��ȡ����ֵ
       Calendar cld=Calendar.getInstance();
       int year=Integer.parseInt(args[1]);
        int month=Integer.parseInt(args[2])-1;  // as 0 is Jan, 11 
        int day=Integer.parseInt(args[3]);
        cld.set(year,month,day);
        Date d=cld.getTime();
        //����һ������
      selec.setCertificateValid(d);
//�����Ƿ��������
    if(selec.match(c)){
	System.out.println("Matched 3");
      } else{
	System.out.println("not Matched 3");
        }
    //����һ������
        BigInteger sn=new BigInteger("1039056963");
        selec.setSerialNumber(sn);
//�����Ƿ��������
       if(selec.match(c)){
	System.out.println("Matched 4");
      } else{
	System.out.println("not Matched 4");
      }
  }
}  
