import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
public class PBEEnc{
   public static void main(String args[]) throws Exception{
       String s="Hello World!";
       char[] passwd=args[0].toCharArray( );
       PBEKeySpec pbks=new PBEKeySpec(passwd);
SecretKeyFactory kf=
SecretKeyFactory.getInstance("PBEWithMD5AndDES"); 
       SecretKey k=kf.generateSecret(pbks);
       byte[] salt=new byte[8];
       Random r=new Random( );
       r.nextBytes(salt);
       Cipher cp=Cipher.getInstance("PBEWithMD5AndDES");
PBEParameterSpec ps=new PBEParameterSpec(salt,1000);
       cp.init(Cipher.ENCRYPT_MODE, k,ps);
       byte ptext[]=s.getBytes("UTF8");
       byte ctext[]=cp.doFinal(ptext);
       //  将盐和加密结果合并在一起保存为密文
        FileOutputStream f=new FileOutputStream("PBEEnc.dat");
        f.write(salt);
        f.write(ctext);
       // 打印盐的值
       for(int i=0;i<salt.length;i++){
             System.out.print(salt[i] +",");
       }
       System.out.println("");
       // 打印加密结果
       for(int i=0;i<ctext.length;i++){
             System.out.print(ctext[i] +",");
       }
   }
}
