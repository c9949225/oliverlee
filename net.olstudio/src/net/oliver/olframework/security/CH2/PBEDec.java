import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
public class PBEDec{
   public static void main(String args[]) throws Exception{
       char[] passwd=args[0].toCharArray( );
       PBEKeySpec pbks=new PBEKeySpec(passwd);
       SecretKeyFactory kf=
SecretKeyFactory.getInstance("PBEWithMD5AndDES"); 
       SecretKey k=kf.generateSecret(pbks);

       byte[] salt=new byte[8];
       FileInputStream f=new FileInputStream("PBEEnc.dat");
       f.read(salt);

       int num=f.available();
       byte[ ] ctext=new byte[num];          
       f.read(ctext);

       Cipher cp=Cipher.getInstance("PBEWithMD5AndDES");
       PBEParameterSpec ps=new PBEParameterSpec(salt,1000);
       cp.init(Cipher.DECRYPT_MODE, k,ps);
       byte ptext[]=cp.doFinal(ctext);
       // 显示解密结果
       for(int i=0;i<ptext.length;i++){
             System.out.print(ptext[i] +",");
       }
        System.out.println("");
       // 以字符串格式显示解密结果
       for(int i=0;i<ptext.length;i++){
             System.out.print((char) ptext[i]);
       }
   }
}
