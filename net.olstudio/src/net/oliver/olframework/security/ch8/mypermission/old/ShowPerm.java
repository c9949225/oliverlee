import java.util.*;
import java.io.*;
import java.security.*;
public class ShowPerm{
     public static void main(String args[]){
           ShowPerm sp=new ShowPerm();
                    sp.go();
     }
     void go(){
          ProtectionDomain domain = this.getClass().getProtectionDomain();
    
    // With the protection domain, get all the permissions from the Policy object
    PermissionCollection pcoll = Policy.getPolicy().getPermissions(domain);
  
    // View each permission in the permission collection
    Enumeration enum = pcoll.elements();
    for (; enum.hasMoreElements(); ) {
        Permission p = (Permission)enum.nextElement();
       System.out.println(p);
    }
       
     }
}
