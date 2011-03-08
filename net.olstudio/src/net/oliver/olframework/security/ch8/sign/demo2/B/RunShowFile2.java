import myp.*;
import java.io.*;

public class RunShowFile2{
     public static void main(String args[]) throws IOException{
        ShowFile2 t=new ShowFile2();
        String s=t.go(args[0]);
        System.out.println(s);
        System.out.println("Over");
     }
}
