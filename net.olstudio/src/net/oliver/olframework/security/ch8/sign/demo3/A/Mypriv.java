package myp;
import java.security.*;
import java.io.*;
class Mypriv implements PrivilegedAction {
      private String filename;
      private String value;
      //ͨ������������Ҫ��Ȩ��ȡ���ļ�����
      public Mypriv(String fname) {
            filename = fname;
      }
      //����Ȩ��ʽִ�еĴ���
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
      //������Ȩ����ִ�н��
      public String getValue() {
         return value;
      }

   }
