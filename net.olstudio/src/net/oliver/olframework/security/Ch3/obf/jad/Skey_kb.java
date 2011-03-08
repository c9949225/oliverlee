// Decompiled by Jad v1.5.7d. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Skey_kb.java

import java.io.*;
import java.security.Key;

public class Skey_kb
{

    public Skey_kb()
    {
    }

    public static void main(String args[])
        throws Exception
    {
        FileInputStream fileinputstream = new FileInputStream("key1.dat");
        ObjectInputStream objectinputstream = new ObjectInputStream(fileinputstream);
        Key key = (Key)objectinputstream.readObject();
        byte abyte0[] = key.getEncoded();
        FileOutputStream fileoutputstream = new FileOutputStream("keykb1.dat");
        fileoutputstream.write(abyte0);
        for(int i = 0; i < abyte0.length; i++)
            System.out.print(abyte0[i] + ",");

    }
}
