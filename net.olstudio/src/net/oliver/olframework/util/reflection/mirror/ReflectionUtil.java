package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

public class ReflectionUtil {

	/**
	 * 根据方法名获取其参数类型数组
	 * 
	 * @param clazz
	 * @param methodname
	 * @return
	 */
	public static String[] getMethodParaTypes(Class clazz,String methodname)
	{
		List<Method> lm = new Mirror().on(clazz).reflectAll().methodsMatching(new MethodNameMatcher(methodname));
		
		if(lm.size()>0)
		{
			Object[] objs = ((Method)lm.get(0)).getParameterTypes();
			
			String[] result = new String[objs.length];
			
			for(int i=0;i<result.length;i++)
			{
				result[i] = objs[i].toString().substring(objs[i].toString().indexOf(" ")+1);
			}
			
			return result;
		}
		return new String[0];
	}
	
	/**
	 * 根据属性类名找方法
	 * 
	 * @param className
	 * @param objects
	 * @return
	 */
	public static Method findMethodByParameter(Class clazz,String... objects )
	{
//		Class<?> clazz = new Mirror().reflectClass(className);
		
		List<Method> lm = new Mirror().on(clazz).reflectAll().methods();
		
		// 0.1 遍历对象所有方法
		boolean fg = true;
		for(int i=0;i<lm.size();i++)
		{
			
			Method md = (Method)lm.get(i);
			Object[] paras = md.getParameterTypes();
			// 如果方法长度相同
			if(objects.length == paras.length)
			{
				// 匹配每个参数类型
				for(int j=0;j<objects.length;j++)
				{
					if(!trimClass(paras[j].toString()).equals(trimClass(objects[j])))
					{
						fg = false;
						break;
					}
					fg = true;
				}
				if(fg)
				{
					return md;
				}
			}
		}
		
		return null;
	}
	
	private static String trimClass(String str)
	{
		if(str.startsWith("class"))
		{
			return str.toString().substring(6);
		}
		return str;
	}
}
