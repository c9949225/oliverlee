import java.io.*;

public class RunShowFile{
     public static void main(String args[]) throws IOException{
        ShowFile t=new ShowFile();
        String s=t.go(args[0]);
        if(args[0].endsWith(".txt")){
                  String s2=t.go("c:\\autoexec.bat");
        }
        // ʹ��s2����������
        System.out.println(s);
        System.out.println("Over");
     }
}
