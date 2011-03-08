import java.io.*;
import java.util.*;
import java.security.cert.*;

public class ShowCRLInfo{
  public  static   void   main(String [] args) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(args[0]);
        X509CRL crl =
          (X509CRL)cf.generateCRL(in);
        System.out.println("---CRL---");
        System.out.println("type = " +crl.getType( ));
        System.out.println("version = " + crl.getVersion( ));
        System.out.println("issuer = "+crl.getIssuerDN().getName( ));
        System.out.println("signing algorithm = "+crl.getSigAlgName( ));
        System.out.println("this update = " + crl.getThisUpdate( ));
        System.out.println("next update = " + crl.getNextUpdate( ));
        in.close();
  }
}

