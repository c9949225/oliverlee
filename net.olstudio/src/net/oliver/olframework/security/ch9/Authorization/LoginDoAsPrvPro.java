import com.sun.security.auth.callback.TextCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.util.*;
import java.io.*;
public class LoginDoAsPrvPro{
   public static void main(String[] args) {
       LoginContext c=null;
        //��¼
       TextCallbackHandler handler=new TextCallbackHandler( );
       boolean pass;
       try {
	          c = new LoginContext("simp",handler);
 	      c.login();
       } 
	       catch (LoginException le) {
             System.out.println("Authentication failed!");
             System.exit(1);
} 
//��ȡSubject.doAsPrivileged( )�����Ĳ���
       Subject subj = c.getSubject();
       MyactionPro myact=new MyactionPro( );
	  try {
         //ִ�д���
		    String filecontent=(String) Subject.doAsPrivileged(
subj, myact,null);
                   //���ִ�н��
              System.out.println("\n Below is result of run MyactionKS");
              System.out.println(filecontent);
	  } catch (AccessControlException e) {
		  System.out.println(e);
	     } 
  }
}

