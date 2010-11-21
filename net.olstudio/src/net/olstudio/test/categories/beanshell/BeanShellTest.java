package net.olstudio.test.categories.beanshell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bsh.EvalError;
import bsh.Interpreter;

public class BeanShellTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// ����������
		Interpreter i = new Interpreter(); 
		
		Map ctx= new HashMap();
		
		ctx.put("k1", "v1");
		try {
			// ���ñ���
			i.set("foo", 5);
			i.set("date", new Date() ); 
			i.set("ctx", ctx);
			
			i.eval("ctx.put(\"sdf\",\"xx\");" +
					"ctx.put(\"sdf2\",\"xx2\");");
			System.out.println(((Map)i.get("ctx")).get("sdf2") );
			
			
			
			
			/*Date date = (Date)i.get("date");    // retrieve a variable
			// ִ����䲢��ȡ���
			i.eval("bar = foo*10");           
			System.out.println( i.get("bar") );
			// ִ��һ���ⲿ�Ľű�
			i.source("somefile.bsh"); */
		} catch (EvalError e) {
			e.printStackTrace();
		}                    
	}

}
