import java.util.*;
import java.io.*;
import java.security.*;
public class ShowAlias{
   public static void main(String args[ ]) throws Exception{
          String pass="wshr.ut";
          String name="mykeystore";
          FileInputStream in=new FileInputStream(name);
          KeyStore ks=KeyStore.getInstance("JKS");           
          ks.load(in,pass.toCharArray());
	  Enumeration e=ks.aliases( );
          while( e.hasMoreElements()) {
            System.out.println(e.nextElement());
          }
     
  }
}  