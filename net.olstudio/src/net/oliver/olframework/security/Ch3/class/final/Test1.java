import java.util.*;
import java.io.*;
import java.lang.reflect.*;

class Test1{
    public static void main(String args[] ) throws Exception{
        //��ȡClassLoader�����������в���ָ������
        ClassLoader cl=ClassLoader.getSystemClassLoader();
        Class c= cl.loadClass(args[0]);
        //���������в���ָ������Ķ���
        Object ob = c.newInstance();
        //�����յĲ�������
        Class arg2[] = { };
        //��ȡ�����в���ָ�������getMyDate( )����
        Method m2 = c.getMethod("getMyDate", arg2 );
        //ִ��getMyDate( )����
        Object o=m2.invoke( ob, null);
        //���������ؽ��
        MyDate d=(MyDate) o;
        if(d.getYear( ) >2050){
            System.out.println("Do critical things here!");
        }
        else{
            System.out.println("Do normal things here!");
        }
    }
}

class MyDate{
    public int year, month, day;
    public int getYear(){
        // ...
        //�Լ��ܷ�ʽ����ʱ�����������ȡʱ��
        int year=2003;
        return(year);
    }
}
