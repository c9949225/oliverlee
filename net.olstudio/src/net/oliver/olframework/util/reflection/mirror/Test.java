package net.oliver.olframework.util.reflection.mirror;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private Map<String,Object> cache = new HashMap<String,Object>();

	public void run()
	{
		String[] arys = {"Level1Bean.Name","Level1Bean.Level2Bean.Name","Level1Bean.Level2Bean.Level3Bean.Name"};
		
		for(int i = 0;i<arys.length;i++)
		{
			if(cache.containsKey(arys[i].substring(arys[i].lastIndexOf(".")+1)))
			{
				// 调用object里面的set方法
			}else{
				
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		
	}

}
