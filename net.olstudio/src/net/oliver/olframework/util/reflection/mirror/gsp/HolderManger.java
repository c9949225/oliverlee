package net.oliver.olframework.util.reflection.mirror.gsp;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.oliver.olframework.util.reflection.mirror.ReflectionUtil;
import net.oliver.olframework.util.reflection.mirror.beans.Level1Bean;
import net.vidageek.mirror.dsl.Mirror;

public class HolderManger {

	private IHolder root;
	
	public void initRootHolder(String clazzName,String methodname)
	{
//		String name = ReflectionUtil.findMethodByParameter("net.oliver.olframework.util.reflection.mirror.beans.Level1Bean", "net.oliver.olframework.util.reflection.mirror.beans.Level2Bean");
		
		// 0.1 找出对应的参数类名
		String[] paras = {"net.oliver.olframework.util.reflection.mirror.beans.Level1Bean"};
		root =  new ConfigHolder("root");
		// 0.2 
		for(int i=0;i<paras.length;i++)
		{
			IHolder holder = new ConfigHolder(paras[i]);
			// 0.2.1 为hodler创建对象类
			holder.setObj(generateNewInstance(paras[i]));
			root.set(paras[i], holder);
		}
	}
	
	public static void main(String[] args)
	{
		HolderManger mgr = new HolderManger();
		mgr.initRootHolder("", "");
		mgr.anlyz(null);
	}
	public IHolder get(String key)
	{
		return (IHolder)root.get(key);
	}
	
	public void anlyz(String[] ary)
	{
		String[] arys = {"net.oliver.olframework.util.reflection.mirror.beans.Level1Bean@Name","net.oliver.olframework.util.reflection.mirror.beans.Level1Bean@net.oliver.olframework.util.reflection.mirror.beans.Level2Bean@Name","net.oliver.olframework.util.reflection.mirror.beans.Level1Bean@net.oliver.olframework.util.reflection.mirror.beans.Level2Bean@net.oliver.olframework.util.reflection.mirror.beans.Level3Bean@Name"};
		
		for(String item : arys)
		{
			String[] bb = item.split("[@]");
			
			IHolder holder = get(bb[0]);
			IHolder last = holder;
			
			for(int i=1;i<bb.length;i++)
			{
				if(i == bb.length -1)
				{
					// 最后一个调用赋值
					last.setMethodname(bb[i]);
					new Mirror().on(last.getObj()).invoke().method("set"+bb[i]).withArgs("名称:"+last.getObj());
					
				}else{
					IHolder subhodler = last.get(bb[i]);
					if(subhodler!=null)
					{
						last = subhodler;
					}else{
//						生成对象
						subhodler = new ConfigHolder(bb[i]);
						subhodler.setObj(generateNewInstance(bb[i]));
						
						subhodler.setParent(last);
						last.set(bb[i], subhodler);
						last = subhodler;
					}
				}
			}
		}
		
		
//		IHolder hodler2 = hodler.get("net.oliver.olframework.util.reflection.mirror.beans.Level2Bean");
//		System.out.println(hodler2);
		
		 IHolder hodler = root.get("net.oliver.olframework.util.reflection.mirror.beans.Level1Bean");
		 organz(hodler);
		 System.out.println(((Level1Bean)hodler.getObj()).getLevel2Bean().getName());
	}
	
	public void organz(IHolder hodler)
	{
		// 已经是底层,返回
		if(hodler.getCache().size()==0)
		{
			return;
		}
		
		Map map = (Map)hodler.getCache();
		Iterator iter = map.entrySet().iterator();
		
		// 遍历当前的所有孩子
		while(iter.hasNext())
		{
			Entry entry = (Entry)iter.next();
			IHolder sub = (IHolder)entry.getValue();
			
			// 将孩子设置到当前
			Method md = ReflectionUtil.findMethodByParameter(hodler.getObj().getClass(), sub.getObj().getClass().toString());
			new Mirror().on(hodler.getObj()).invoke().method(md) .withArgs(sub.getObj());
			organz(sub);
		}
	}
	
	
	
	
	//  反射 ===========================================================
	public Object generateNewInstance(String str)
	{
		Class<?> clazz = new Mirror().reflectClass(str);
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Object execute(Object target,String methodname,Object... para)
	{
		return new Mirror().on(target).invoke().method(methodname).withArgs(para);
	}
	
	
	
}
