import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;

public class ValiSubIssu{
   public static void main(String args[ ]) throws Exception{
          Certificate[] certpath=new Certificate[args.length];
          CertificateFactory cf = CertificateFactory.getInstance("X.509");

          for (int i=0;i<args.length;i++){
		  FileInputStream in=new FileInputStream(args[i]);
                  certpath[i]=cf.generateCertificate(in);
          }
	  boolean pass=true;	  
          String reason="";
          for(int i=0;i<certpath.length-1;i++){
               Principal issuer = ((X509Certificate)certpath[i]).getIssuerDN();
               Principal subject =((X509Certificate)certpath[i+1]).getSubjectDN();
               if(! issuer.equals(subject) ){
		      pass=false;
                      reason+="in "+i+"\n";
                      reason+="issuer is " +issuer+"\n";
	 	      reason+="But in "+(i+1)+"\n";
		      reason+="subject is "+subject+"\n";
		      break;
               }
	  }
          if(pass){
                 System.out.println("subject/issuer verification OK");
	  }
	  else{
                 System.out.println("subject/issuer verification Wrong \n"+reason);
	  }
   }  
}