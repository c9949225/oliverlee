package net.oliver.olframework.util.reflection;

import java.lang.reflect.*;

public class Test
{

    public static void main(String[] args)
    {

        try{

            Test t = new Test();
            Class c = t.getClass();
            
            Class[] cargs = new Class[2];
            String[] realArgs = {"aa","bb"}; // �����Ĳ���
            cargs[0] = realArgs.getClass();
            
            Integer in = new Integer(2);
            cargs[1] = in.getClass();
            
            Method m = c.getMethod("test",cargs); //������test����������ֵΪMethod�����������Ĳ��������︳ֵ
            
            Object[] inArgs = new Object[2];
            inArgs[0] = realArgs; 
            inArgs[1] = in;
            
            m.invoke(t,inArgs); 

        }catch(Exception e){System.out.println(e);} 

    }

    public void test(String[] str,Integer stri)
    {

        for(int j = 0; j < str.length; j ++)
        System.out.println(str[j]);
        System.out.println(stri.intValue()); 

    } 

}