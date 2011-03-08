package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Matcher;

public class MethodParaTypeMatcher implements Matcher<Method> {

	private String paratype;
	
	
	public MethodParaTypeMatcher(String paratype) {
		super();
		this.paratype = paratype;
	}


	public boolean accepts(Method element) {
		
		Object[] objs = element.getParameterTypes();
		if(objs[0].getClass().getName().substring(objs[0].getClass().getName().indexOf(" ")+1).equals(paratype))
			return true;
		return false;
	}

}
