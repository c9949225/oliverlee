import java.io.*;
import java.security.*;
public class DoAttack{
     public static void main(String args[ ]) throws Exception{
         String md;        
  BufferedReader in = new BufferedReader(
new FileReader("dict.txt"));
 while ((md = in.readLine( )) != null) {
    if (md.indexOf(args[0])!=-1){
             System.out.println(md);
             break;
    }
}
        in.close();
     }
}
