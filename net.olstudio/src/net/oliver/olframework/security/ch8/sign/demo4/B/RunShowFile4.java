import myp.*;
import java.io.*;
public class RunShowFile4{
     public static void main(String args[]) throws IOException{
        ShowFile4 t=new ShowFile4();
        String s=t.go(args[0]);
        System.out.println(s);
        System.out.println("Over");
     }
}
