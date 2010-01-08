package net.oliver.olframework.util.reflection.copy;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class CopyProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Object1 obj = new Object1();
		Object2 obj2 = new Object2();
		
		obj.setId("1");
		obj.setName("2");
		
		try {
			BeanUtils.copyProperties(obj2, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println(obj2.getId());
		System.out.println(obj2.getName());

	}

}
