import java.io.*;
import java.security.*;
public class CheckPass{
     public static void main(String args[ ]) throws Exception{
          /* ��ȡ����Ŀ���ժҪ */
          String name="";
         String  passwd="";
          BufferedReader in = new BufferedReader(new FileReader("passwd.txt"));
          while ((name = in.readLine( )) != null) {
               passwd=in.readLine( );
               if (name.equals(args[0])){
                      break;
               }
          }

          /* �����û�����Ŀ���ժҪ */
          MessageDigest m=MessageDigest.getInstance("MD5");
          m.update(args[1].getBytes("UTF8"));
          byte s[ ]=m.digest( );
          String result="";
          for (int i=0; i<s.length; i++){
              result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
          }

          /* �������ժҪ�Ƿ�ƥ�� */
          if(name.equals(args[0])&&result.equals(passwd)){
		 	System.out.println("OK");
	  	  }
          else{
		 	System.out.println("Wrong password");
  	      }
       }   
}