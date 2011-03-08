import com.sun.security.auth.callback.DialogCallbackHandler;
import javax.security.auth.*;
import javax.security.auth.login.*;

public class SimpLogin2 {
   public static void main(String[] args) throws Exception {
        //��¼
	  LoginContext c = new LoginContext("simp");
       boolean pass;
       try {
  	       c.login();
//��¼�ɹ�
	            pass=true;
       } 
catch (LoginException le) {
     //��¼ʧ��
             pass=false;
	            System.err.println("Authentication failed:");
	            System.err.println("  " + le.getMessage());
       } 
     //��ʾ��¼���
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