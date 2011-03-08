import java.util.*;
import java.io.*;
import java.lang.reflect.*;

class Test1{
    public static void main(String args[] ) throws Exception{
        //获取ClassLoader并加载命令行参数指定的类
        ClassLoader cl=ClassLoader.getSystemClassLoader();
        Class c= cl.loadClass(args[0]);
        //创建命令行参数指定的类的对象
        Object ob = c.newInstance();
        //创建空的参数对象
        Class arg2[] = { };
        //获取命令行参数指定的类的getMyDate( )方法
        Method m2 = c.getMethod("getMyDate", arg2 );
        //执行getMyDate( )方法
        Object o=m2.invoke( ob, null);
        //处理方法返回结果
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
        //以加密方式连接时间服务器，获取时间
        int year=2003;
        return(year);
    }
}
