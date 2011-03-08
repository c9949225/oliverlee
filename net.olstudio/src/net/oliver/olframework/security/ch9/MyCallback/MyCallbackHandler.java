import java.io.*;
import javax.security.auth.callback.*;
public class MyCallbackHandler implements CallbackHandler{
public void handle(Callback[] callbacks)
     throws IOException, UnsupportedCallbackException {
     for (int i = 0; i < callbacks.length; i++) {
if (callbacks[i] instanceof NameCallback) {
    //强制转换
NameCallback nameCallback = (NameCallback)callbacks[i];
 // 显示提示信息
System.out.print( nameCallback.getPrompt() 
+ "["+ nameCallback.getDefaultName()+"]:");
System.out.flush();
//读取用户输入
String username = new BufferedReader(
new InputStreamReader(System.in)).readLine();
if(username==null || username.length( )==0){
username= nameCallback.getDefaultName();
}
//保存用户输入
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