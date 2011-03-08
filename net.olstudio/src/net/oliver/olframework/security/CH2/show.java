import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class show{
   public static void main(String args[]) throws Exception{



        FileInputStream f=new FileInputStream(args[0]);
        int num=f.available();
        byte[ ] ctext=new byte[num];          
        f.read(ctext);

        for(int i=0;i<ctext.length;i++){
             System.out.print(ctext[i] +",");
        }


   }
}