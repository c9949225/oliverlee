import java.io.Serializable;
import java.security.Principal;
public class MyPrincipal implements Principal, Serializable {
     private String name;
     public MyPrincipal(String n) {
        name = n;
     }
     public String getName( ) {
         return name;
     }
     public int hashCode( ) {
        return name.hashCode();
     }
     public String toString( ) {
       return getName();
     }
     public boolean equals(Object obj) {
        if ( (obj!=null)&& (obj  instanceof  MyPrincipal)) {
               MyPrincipal obj2 = (MyPrincipal)obj;
                   if (name.equals(obj2.getName())) {
                        return true;
                   }
         }
      return false;
    }
}