import java.util.*;
import java.io.*;
import java.security.*;
public class Test{
     public static void main(String args[ ]) throws Exception{
           Random  rand=new Random();
                   byte[ ] salt=new byte[12];
                    rand.nextBytes(salt);
           for (int i=0; i<salt.length; i++){
                    System.out.print(salt[i]+" ");
           }
          System.out.println();

         String  s1=new String(salt,"UTF8");
         salt=s1.getBytes("UTF8");

           for (int i=0; i<salt.length; i++){
                    System.out.print(salt[i]+" ");
           }
          System.out.println();


       }   
}