import java.io.*;

public class test{
     public static void main(String args[]) throws IOException{
        String file=args[0];
              int p1=file.lastIndexOf(".");
              int p2=file.length();

String s=file.substring(p1,p2);

System.out.println(s);

     }
}
