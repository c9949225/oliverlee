import java.io.*;
public class MySecurityManager extends SecurityManager {
  public void checkRead(String file) {
    if ( !(file.endsWith(".txt")) 
                    && !(file.endsWith(".java")) 
                    && !(file.endsWith(".class")) 
                    && !(file.startsWith("C:\\j2sdk1.4.0")) ) {
      throw new SecurityException ("No Read Permission for : " + file);
    }
  }
}

