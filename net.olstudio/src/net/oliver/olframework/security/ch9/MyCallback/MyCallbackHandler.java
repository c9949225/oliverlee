import java.io.*;
import javax.security.auth.callback.*;
public class MyCallbackHandler implements CallbackHandler{
public void handle(Callback[] callbacks)
     throws IOException, UnsupportedCallbackException {
     for (int i = 0; i < callbacks.length; i++) {
if (callbacks[i] instanceof NameCallback) {
    //ǿ��ת��
NameCallback nameCallback = (NameCallback)callbacks[i];
 // ��ʾ��ʾ��Ϣ
System.out.print( nameCallback.getPrompt() 
+ "["+ nameCallback.getDefaultName()+"]:");
System.out.flush();
//��ȡ�û�����
String username = new BufferedReader(
new InputStreamReader(System.in)).readLine();
if(username==null || username.length( )==0){
username= nameCallback.getDefaultName();
}
//�����û�����
nameCallback.setName(username);
 } else if (callbacks[i] instanceof PasswordCallback) {
  			PasswordCallback passwordCallback = 
(PasswordCallback) callbacks[i];
System.out.print( passwordCallback.getPrompt() + "? ");
System.out.flush();
String password = new BufferedReader(
new InputStreamReader(System.in)).readLine();
passwordCallback.setPassword(password.toCharArray());
password = null;  
      } else  if (callbacks[i] instanceof TextOutputCallback) {
TextOutputCallback  textOutputCallback=
(TextOutputCallback)callbacks[i];
System.out.println(textOutputCallback.getMessage( ));
   	 } else  if (callbacks[i] instanceof ConfirmationCallback) {
       }
else {
     		     throw new UnsupportedCallbackException(
		callbacks[i], "Unrecognized Callback");
   	 }
  } // end for
}  // end handle( )
} // end class