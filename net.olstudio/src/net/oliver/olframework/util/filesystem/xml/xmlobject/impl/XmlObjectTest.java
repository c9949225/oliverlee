package net.oliver.olframework.util.filesystem.xml.xmlobject.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlObjectTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map alias = new HashMap();
		alias.put("bean","net.oliver.olframework.util.filesystem.xml.xmlobject.impl.Bean");
		
		ObjectAndXmlImpl app = new ObjectAndXmlImpl(alias);
		List list = new ArrayList();
		for(int i=0;i<5;i++)
		{
			Bean bean = new Bean(""+i,""+i,""+i,""+i);
			list.add(bean);
		}
		app.transOut(list, "D:\\test.xml");
//		Object[] list = app.transIn("D:\\test.xml");
//		for(int i=0;i<list.length;i++)
//		{
//			System.out.println(((Bean)list[i]).getAttr1());
//		}
	}

}
