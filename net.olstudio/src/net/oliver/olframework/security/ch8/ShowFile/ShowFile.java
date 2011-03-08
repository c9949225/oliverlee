import java.io.*;
public class ShowFile{
     public String go(String name) throws IOException{
        String s;
              String content="";
        BufferedReader in;
        in = new BufferedReader(new FileReader(name));
        while ((s = in.readLine( )) != null) {
           content+=s+"\n";
        }
        return content;
     }
}


