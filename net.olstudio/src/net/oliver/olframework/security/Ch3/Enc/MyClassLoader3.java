import java.io.*;
import java.lang.reflect.*;
import java.security.*;
import javax.crypto.*;

public class MyClassLoader3 extends ClassLoader
{   
   //覆盖loadClass( )方法
   public Class loadClass( String name, boolean resolve )
      throws ClassNotFoundException {
       byte[ ] classData=null;
       Class c = null;
       try {
          c = findLoadedClass(name);
          if (c != null)  return(c);
          try {
               c=findClass(name);
            } catch( Exception fnfe ) {
          }
          if (c==null){
              c = findSystemClass (name);
           }
          if (resolve && c != null)
               resolveClass( c );
          return(c);
      } catch( Exception e ) {
throw new ClassNotFoundException( e.toString()); 
}

    }
   //以下代码和3.5节相同。
  public Class findClass( String name){
       byte[ ] classData=null;
       try{
        FileInputStream fkey=new FileInputStream("key1.dat");
	    ObjectInputStream ob=new ObjectInputStream(fkey);
		Key k=(Key)ob.readObject( );
Cipher cp=Cipher.getInstance("DESede");
         cp.init(Cipher.DECRYPT_MODE, k);
       FileInputStream in=
new FileInputStream(name+".class");
       ByteArrayOutputStream out=new ByteArrayOutputStream( );
       CipherOutputStream cout=new CipherOutputStream(out, cp);
	   int b=0;
	   while( (b=in.read())!=-1){
	         cout.write(b);
	    }
        cout.close();
       classData=out.toByteArray( );
   } catch(Exception e){  }

   Class x=defineClass(name, classData, 0, classData.length);
    return x;
  }
}

class MyURL4{
  static public void main( String args[] ) throws Exception {
      MyClassLoader3 x=new MyClassLoader3();
      Class c = x.loadClass(args[0]);
      Class getArg1[] = { (new String[1]).getClass() };
      Method m = c.getMethod( "main", getArg1 );
      String[] my1={"arg1 passed","arg2 passed"};
      Object myarg1[] = {my1};
      m.invoke( null, myarg1 );
  }
}
