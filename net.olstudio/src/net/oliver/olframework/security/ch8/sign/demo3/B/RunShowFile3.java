import myp.*;
import java.io.*;
public class RunShowFile3{
     public static void main(String args[]) throws IOException{
        ShowFile3 t=new ShowFile3();
        String s=t.go(args[0]);
        System.out.println(s);
        System.out.println("Over");
     }
}
