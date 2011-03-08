package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

public class ReflectionUtil {

	/**
	 * ���ݷ�������ȡ�������������
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
	 * �������������ҷ���
	 * 
	 * @param className
	 * @param objects
	 * @return
	 */
	public static Method findMethodByParameter(Class clazz,String... objects )
	{
//		Class<?> clazz = new Mirror().reflectClass(className);
		
		List<Method> lm = new Mirror().on(clazz).reflectAll().methods();
		
		// 0.1 �����������з���
		boolean fg = true;
		for(int i=0;i<lm.size();i++)
		{
			
			Method md = (Method)lm.get(i);
			Object[] paras = md.getParameterTypes();
			// �������������ͬ
			if(objects.length == paras.length)
			{
				// ƥ��ÿ����������
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
