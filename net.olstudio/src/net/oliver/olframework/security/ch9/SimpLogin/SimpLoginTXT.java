import com.sun.security.auth.callback.TextCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;

public class SimpLoginTXT {
   public static void main(String[] args) throws Exception {
        //µÇÂ¼
       TextCallbackHandler handler=new TextCallbackHandler( );
	      LoginContext c = new LoginContext("simp",handler);
       boolean pass;
       try {
  	       c.login();
//µÇÂ¼³É¹¦
	            pass=true;
       } 
catch (LoginException le) {
     //µÇÂ¼Ê§°Ü
             pass=false;
	            System.err.println("Authentication failed:");
	            System.err.println("  " + le.getMessage());
       } 
     //ÏÔÊ¾µÇÂ¼½á¹û
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
