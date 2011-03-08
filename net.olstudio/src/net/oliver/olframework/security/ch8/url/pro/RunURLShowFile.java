import java.io.*;
import java.net.*;
import java.lang.reflect.*;

public class RunURLShowFile{
     public static void main(String args[]) throws Exception{
          URL myurl[] = {
            new URL ("http://127.0.0.1/")
          };
          URLClassLoader x = new URLClassLoader (myurl);
	  Class c = x.loadClass ("ShowFile");
	  Object ob = c.newInstance();

	  Class arg3[] = {(new String()).getClass()};
	  Method m3 = c.getMethod("go", arg3 );
	  Object myarg2[] = {args[0]};
	  String s=(String) m3.invoke( ob,myarg2);
        System.out.println(s);
        System.out.println("Over");

      }
}
    
