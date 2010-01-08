package net.oliver.olframework.test;

import java.io.IOException;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec("mstsc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
