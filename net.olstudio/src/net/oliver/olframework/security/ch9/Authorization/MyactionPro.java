import javax.security.auth.*;
import javax.security.auth.login.*;
import java.security.*;
import java.util.*;
import java.io.*;
class  MyactionPro implements PrivilegedAction {
public Object run() {
     //��ȡ��ǰ��¼�����ĵ�����
		AccessControlContext context = AccessController.getContext();
		Subject subject = Subject.getSubject(context );
		if (subject == null ) {
			throw new AccessControlException("Denied");
		}
     //��ȡ��ݱ�־����
		Set principals = subject.getPrincipals();
		Iterator iterator = principals.iterator();
     //��������
		String name="CN=Webmaster,OU=NC,O="+
                         "Shanghai University,L=ZB,ST=Shanghai,C=CN";
//��������⵱ǰ�������ݱ�־
		while (iterator.hasNext()) {
			Principal principal = (Principal)iterator.next();
       String nameiInSub= principal.getName( );
if (nameiInSub.equals( name )) {
   System.out.println("Now run code permitted by " 
+name );
//�Է������������ִ�еĶ�ȡ�ļ��Ĵ���
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
