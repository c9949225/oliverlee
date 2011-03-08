import java.io.*;
import java.security.cert.*;
public class PrintCert{
    
   public static void main(String args[ ]) throws Exception{
        CertificateFactory cf=CertificateFactory.getInstance("X.509");
        FileInputStream in=new FileInputStream(args[0]);
        Certificate c=cf.generateCertificate(in);
        in.close();
	String s=c.toString( );
        // œ‘ æ÷§ È
	FileOutputStream fout=new FileOutputStream("tmp.txt");
      BufferedWriter out= new BufferedWriter(new OutputStreamWriter(fout));
    out.write(s,0,s.length( )); 
        out.close();
       
  }

}  