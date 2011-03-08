package net.oliver.olframework.util.reflection.mirror;

import java.lang.reflect.Method;

import net.vidageek.mirror.dsl.Matcher;

public class MethodNameMatcher implements Matcher<Method> {

	
	private String methodname;
	
	
	
	public MethodNameMatcher(String methodname) {
		super();
		this.methodname = methodname;
	}


	public String getMethodname() {
		return methodname;
	}


	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}


	public boolean accepts(Method element) {
		if(this.methodname!=null&&methodname.equals(element.getName()))
			return true;
		return false;
	}

}
