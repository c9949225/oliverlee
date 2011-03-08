import java.io.*;
import java.security.*;
import java.awt.*;
public class AccessCCheckPerm{
     public static void main(String args[]){
        try{
              FilePermission fp=new FilePermission("c:\\autoexec.bat", "read");
              AccessController.checkPermission(fp);
  	      System.out.println("Has FilePermission to read c:\\autoexec.bat");
        } 
        catch(AccessControlException  e){
		System.out.println(e);
        }

        try{
              AWTPermission ap=new AWTPermission("accessClipboard");
              AccessController.checkPermission(ap);
  	      System.out.println("Has AWTPermission to access AWT Clipboard");
        } 
        catch(AccessControlException  e){
		System.out.println(e);
        }
     }
}
