package myp;
import java.security.*;
import java.io.*;
public class ShowFile2{
     public String go(String name) throws IOException{
        String s;
        String content="";
        BufferedReader in;
        in = new BufferedReader(new FileReader(name));
        while ((s = in.readLine( )) != null) {
           content+=s+"\n";
        }
        in = new BufferedReader(new FileReader("c:\\autoexec.bat"));
        while ((s = in.readLine( )) != null) {
           //...
           //临时用于特殊用途
        }
        return content;
     }
}
