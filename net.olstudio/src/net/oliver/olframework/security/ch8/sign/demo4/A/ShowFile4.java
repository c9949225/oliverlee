package myp;
import java.security.*;
import java.io.*;
public class ShowFile4{
     public String go(String name) throws IOException{
        String s;
           String pname="c:\\autoexec.bat";  //特权代码准备访问的文件
        String content="";
        BufferedReader in;
        in = new BufferedReader(new FileReader(name));
        while ((s = in.readLine( )) != null) {
           content+=s+"\n";
        }
         //特权代码
        final String filename=pname;
        String result = (String) AccessController.doPrivileged(
              new PrivilegedAction() {
                  public Object run() {
                     String value="";
                     String s;
                     try{
		           BufferedReader in = new BufferedReader(
             		     new FileReader(filename));
		              while ((s = in.readLine( )) != null) {
                                  value+=s+"\n";
                        }
                     }
                     catch(IOException e){
                     }
                    return value; //传出执行结果
               }
            }
       );
       content+=result;
       return content;
     }
}