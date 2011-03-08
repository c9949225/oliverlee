import java.io.*;
import java.util.*;
public class TXTPassword {
   public static void main(String argv[]) throws IOException {
    
        String prompt="Input:";
          MaskingThread mask= new MaskingThread(prompt,1);
          mask.start();
          System.out.print(prompt);
          String password = new BufferedReader(
new InputStreamReader(System.in)).readLine();
          mask.stopMasking();     
System.out.println("The Passowrd inputed is: "+password);
    }
}
