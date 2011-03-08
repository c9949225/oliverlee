import java.io.*;
import java.math.*;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.crypto.interfaces.*;

public class KeyAgree{
   public static void main(String args[ ]) throws Exception{

      FileInputStream f1=new FileInputStream(args[0]);
      ObjectInputStream b1=new ObjectInputStream(f1);
      PublicKey  pbk=(PublicKey)b1.readObject( );

      FileInputStream f2=new FileInputStream(args[1]);
      ObjectInputStream b2=new ObjectInputStream(f2);
      PrivateKey  prk=(PrivateKey)b2.readObject( );

     KeyAgreement ka=KeyAgreement.getInstance("DH");
     ka.init(prk);
     ka.doPhase(pbk,true);

     byte[ ] sb=ka.generateSecret();
     for(int i=0;i<sb.length;i++){
        System.out.print(sb[i]+",");
     }
    SecretKeySpec k=new  SecretKeySpec(sb,"DESede")£»
  }
}  