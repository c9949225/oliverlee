package myp;
import java.security.*;
import java.io.*;
class Mypriv implements PrivilegedAction {
      private String filename;
      private String value;
      //通过构造器传入要特权读取的文件名称
      public Mypriv(String fname) {
            filename = fname;
      }
      //以特权方式执行的代码
      public Object run() { 
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
        return value;
      } 
      //返回特权代码执行结果
      public String getValue() {
         return value;
      }

   }
