package net.oliver.olframework.util.reflection;

import java.lang.reflect.*;

public class Test
{

	public String name;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args)
    {

        try{
// ===============================================================================
            Test t = new Test();
            Class c = t.getClass();
            
            Class[] cargs = new Class[2];
            String[] realArgs = {"aa","bb"}; // 真正的参数
            cargs[0] = realArgs.getClass();
            
            Integer in = new Integer(2);
            cargs[1] = in.getClass();
            
            Method m = c.getMethod("test",cargs); //调用了test方法，返回值为Method变量，真正的参数在这里赋值
            
            Object[] inArgs = new Object[2];
            inArgs[0] = realArgs; 
            inArgs[1] = in;
            
            m.invoke(t,inArgs); 
// ===============================================================================
//        	Test t = new Test();
//        	Class tclass = t.getClass();
//        	Field field = tclass.getField("name");
//        	
//        	System.out.println(field.getName());
// ===============================================================================   
        	// 传入一个对象,得到该对象指定属性名的值
//        	Object obj = ReflectUtil.getProperty(t, "name");
//        	String str = (String)obj;
//        	System.out.println(str);
//
        }catch(Exception e){System.out.println(e);} 
        	
//          String str = "xxx";
//          System.out.println(str.split("[,]")[0]);//xxx

    }

    public void test(String[] str,Integer stri)
    {

        for(int j = 0; j < str.length; j ++)
        System.out.println(str[j]);
        System.out.println(stri.intValue()); 

    } 

}