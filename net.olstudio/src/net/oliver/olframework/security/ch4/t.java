import java.io.*;
import java.security.*;
import javax.crypto.*;
public class t{
   public static void main(String args[]) throws Exception{
        String s="0123456789";
        byte ptext[]=s.getBytes("UTF8");
        for(int i=0;i<ptext.length;i++){
            System.out.print(ptext[i]+",");
        }
   }
}
