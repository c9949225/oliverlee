package net.oliver.olframework.test;

import org.apache.log4j.Logger;


/*
 * Created on 2008-1-3 下午02:15:57 by oliverlee
 */

import net.oliver.olframework.util.filesystem.xml.xmlobject.impl.ObjectAndXmlImpl;


public class MyTest
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MyTest.class);

    /**
     * 对对象和Xml之间的转换的测试
     */
    public static void main(String[] args)
    {
//        XStream xstream = new XStream(); //require XPP3 library
//        xstream.alias("person", Person.class);
//        xstream.alias("phonenumber", PhoneNumber.class);
//        Person joe = new Person("Joe", "Walnes");
//        joe.setPhone(new PhoneNumber(123, "1234-456"));
//        joe.setFax(new PhoneNumber(123, "9999-999"));
        
//        System.out.println(xstream.toXML(joe));
         /* List list = new ArrayList();
          Map<String, Person> map = new HashMap<String, Person>();
          for(int i=0;i<10;i++){
              Person inside =  new Person(String.valueOf(i),"foobar");
              inside.setPhone(new PhoneNumber(123,"456"));
              list.add(inside);
          }
          ObjectAndXml util = new ObjectAndXml();
          String path = "D:\\test2.xml";
          util.transOut(list, path);*/
//          ObjectAndXml util = new ObjectAndXml();
//          Object[] obj = util.transIn("D:\\test2.xml");
//          for(int j=0;j<obj.length;j++){
//              System.out.println((Person)obj[j]);
//          }
//    	String str = "tttttttttttttt\r\n                  \r\n              \r\nsdfsdf[$]ssfsdsdfsdf[$]sdfsdf";
//    	String[]  ary = str.split("\\[\\$\\]");
//    	for(int i=0;i<ary.length;i++)
//    	{
//    		System.out.println(ary[i]);
//    	}
    	
    	/*System.out.println("部分撤销记录明细:");
    	System.out.println("序号           姓名                证件号码            撤消原因");
    	int count = 500;
    	int TOTAL_ROWS = 30;// 每页30行
    	for(int i=0;i<count;i++)
    	{
    		System.out.println("第"+i+"行"+"     blahblah        blahblah      blahblah");
    		if(i/(TOTAL_ROWS-2) <=1)
    		{
    			if(i%(TOTAL_ROWS-2) == 0 && i!=0)
            	{
    				System.out.println("换页");
            	}
    			
    		}else{
    			if((i+2)%TOTAL_ROWS == 0)
            	{
    				System.out.println("换页");
            	}
    		}
    	}*/
    	String x = "50.00";
		System.out.println(Integer.parseInt(x));
	}
    }

}
