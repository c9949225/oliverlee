import com.sun.security.auth.callback.TextCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.util.*;
import java.io.*;
public class LoginDoAsPrvKS{
   public static void main(String[] args) {
       LoginContext c=null;
        //µÇÂ¼
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
       Subject subj = c.getSubject();
       MyactionKS myact=new MyactionKS( );
	try {
		String filecontent=(String) Subject.doAsPrivileged(subj, myact,null);
//		String filecontent=(String) Subject.doAs(subj, myact);
              System.out.println("\n Below is result of run MyactionKS");
              System.out.println(filecontent);
	} catch (AccessControlException e) {
		System.out.println(e);
	} 

     }
}
