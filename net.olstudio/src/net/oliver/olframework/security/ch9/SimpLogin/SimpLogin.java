import com.sun.security.auth.callback.DialogCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;

public class SimpLogin {
   public static void main(String[] args) throws Exception {
//       TextCallbackHandler handler=new TextCallbackHandler( );
       DialogCallbackHandler handler=new DialogCallbackHandler( );
	LoginContext c = new LoginContext("simp",handler);
//	LoginContext c = new LoginContext("simp");

       boolean pass;
       try {
  	       c.login();
	       pass=true;
       } catch (LoginException le) {
             pass=false;
	      System.err.println("Authentication failed:");
	      System.err.println("  " + le.getMessage());
       } 
       if(!pass){
	    System.out.println("Sorry");
       }
       else{
           System.out.println("Authentication succeeded!");
           Subject s = c.getSubject();
           System.out.println(s.getPrincipals());
       }
     }
}
