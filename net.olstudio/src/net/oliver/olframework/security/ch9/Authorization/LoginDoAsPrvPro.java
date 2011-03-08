import com.sun.security.auth.callback.TextCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.util.*;
import java.io.*;
public class LoginDoAsPrvPro{
   public static void main(String[] args) {
       LoginContext c=null;
        //登录
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
//获取Subject.doAsPrivileged( )方法的参数
       Subject subj = c.getSubject();
       MyactionPro myact=new MyactionPro( );
	  try {
         //执行代码
		    String filecontent=(String) Subject.doAsPrivileged(
subj, myact,null);
                   //输出执行结果
              System.out.println("\n Below is result of run MyactionKS");
              System.out.println(filecontent);
	  } catch (AccessControlException e) {
		  System.out.println(e);
	     } 
  }
}

