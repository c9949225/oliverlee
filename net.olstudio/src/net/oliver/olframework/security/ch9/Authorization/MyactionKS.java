import java.security.*;
import java.io.*;
public class MyactionKS implements PrivilegedAction {
	public Object run() {
        String content="";
        String s;
        String filename="c:\\autoexec.bat";
        BufferedReader in;
        try{
	        in = new BufferedReader(new FileReader(filename));
   	        while ((s = in.readLine( )) != null) {
               content+=s+"\n";
           }
        }
        catch(IOException e){
             System.out.println(e);
        }
  	return content;
	}
}
