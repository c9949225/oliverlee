import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.util.*;
import java.io.*;
class  MyactionPro implements PrivilegedAction {
public Object run() {
     //获取当前登录上下文的主体
		AccessControlContext context = AccessController.getContext();
		Subject subject = Subject.getSubject(context );
		if (subject == null ) {
			throw new AccessControlException("Denied");
		}
     //获取身份标志集合
		Set principals = subject.getPrincipals();
		Iterator iterator = principals.iterator();
     //允许的身份
		String name="CN=Webmaster,OU=NC,O="+
                         "Shanghai University,L=ZB,ST=Shanghai,C=CN";
//遍历并检测当前主体的身份标志
		while (iterator.hasNext()) {
			Principal principal = (Principal)iterator.next();
       String nameiInSub= principal.getName( );
if (nameiInSub.equals( name )) {
   System.out.println("Now run code permitted by " 
+name );
//以符合条件的身份执行的读取文件的代码
String content="";
String s;
String filename="c:\\autoexec.bat";
        try{
	            BufferedReader in = new BufferedReader(
new FileReader(filename));
   	        while ((s = in.readLine( )) != null) {
               content+=s+"\n";
           }
        }
        catch(IOException e){
             System.out.println(e);
        }

   return content;
}
		}
		throw new AccessControlException("Denied in MyactionPro");
	  }
}
