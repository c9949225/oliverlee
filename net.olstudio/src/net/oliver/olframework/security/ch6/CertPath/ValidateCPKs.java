import java.io.*;
import java.math.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;


public class ValidateCPKs{
             
   public static void main(String args[ ]) throws Exception{
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
	int i;
        List mylist = new ArrayList();
        for (i=0;i<args.length-2;i++){
		FileInputStream in=new FileInputStream(args[i]);
                Certificate c=cf.generateCertificate(in);
                mylist.add(c);
        }
        CertPath cp = cf.generateCertPath(mylist);


        FileInputStream kin=new FileInputStream(args[i]);
        KeyStore ks=KeyStore.getInstance("JKS");
        ks.load(kin,args[i+1].toCharArray());

        // Set the PKIX parameters
        PKIXParameters params = new PKIXParameters(ks);
        params.setRevocationEnabled(false);

        CertPathValidator cpv = CertPathValidator.getInstance("PKIX");
        try {
               PKIXCertPathValidatorResult result =
                    (PKIXCertPathValidatorResult) cpv.validate(cp, params);

               PublicKey pbk=result.getPublicKey();
               byte[ ] pkenc=pbk.getEncoded();
               System.out.println("¹«Ô¿");
               BigInteger pk=new BigInteger(pkenc);
               System.out.println(pk.toString(16));

               TrustAnchor anc=result.getTrustAnchor();
               X509Certificate xc=anc.getTrustedCert();
	       System.out.println(xc.getSubjectDN());
	       System.out.println(xc.getIssuerDN());
	       System.out.println("Validation OK");

        } catch (CertPathValidatorException cpve) {
                 System.out.println("Validation failure, cert[" 
                     + cpve.getIndex() + "] :" + cpve.getMessage());
       }
  }
}  



