import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;


public class ValidateCP{
             
   public static void main(String args[ ]) throws Exception{
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
	int i;
        List mylist = new ArrayList();
        for (i=0;i<args.length-1;i++){
		FileInputStream in=new FileInputStream(args[i]);
                Certificate c=cf.generateCertificate(in);
                mylist.add(c);
        }
        CertPath cp = cf.generateCertPath(mylist);

        FileInputStream in=new FileInputStream(args[i]);
        Certificate trust=cf.generateCertificate(in);
        // Create TrustAnchor
        TrustAnchor anchor = new TrustAnchor( (X509Certificate)trust,null);
        // Set the PKIX parameters
        PKIXParameters params = new PKIXParameters(Collections.singleton(anchor));
        params.setRevocationEnabled(false);
        CertPathValidator cpv = CertPathValidator.getInstance("PKIX");
        try {
               PKIXCertPathValidatorResult result =
                    (PKIXCertPathValidatorResult) cpv.validate(cp, params);
	       System.out.println(result);
	       System.out.println("Validation OK");

        } catch (CertPathValidatorException cpve) {
                 System.out.println("Validation failure, cert[" 
                     + cpve.getIndex() + "] :" + cpve.getMessage());
       }
  }
}  



