import java.util.*;
import java.io.*;
import java.security.*;
public class SetPassSalt{
    public static void main(String args[ ]) throws Exception{
	  //�����˺ſ���
        String name=args[0];
        String passwd=args[1];
        //������
        Random  rand=new Random();
        byte[ ] salt=new byte[12];
        rand.nextBytes(salt);
        //������ϢժҪ
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(salt);
        m.update(passwd.getBytes("UTF8"));
        byte s[ ]=m.digest( );
        String result="";
        for (int i=0; i<s.length; i++){
            result+=Integer.toHexString((0x000000ff & s[i]) |
                                        0xffffff00).substring(6);
        }
        //�����˺š��κ���ϢժҪ
        PrintWriter out= new PrintWriter(
                             new FileOutputStream("passwdsalt.txt"));

        out.println(name);
        for (int i=0; i<salt.length; i++){
            out.print(salt[i]+",");
        }
        out.println("");

        out.println(result);
        out.close();
    }
}
