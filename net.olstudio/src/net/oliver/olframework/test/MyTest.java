package net.oliver.olframework.test;
/*
 * Created on 2008-1-3 ÏÂÎç02:15:57 by oliverlee
 */

import net.oliver.olframework.util.filesystem.xml.xmlobject.impl.ObjectAndXml;


public class MyTest
{

    /**
     * @param args
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
          ObjectAndXml util = new ObjectAndXml();
          Object[] obj = util.transIn("D:\\test2.xml");
          for(int j=0;j<obj.length;j++){
              System.out.println((Person)obj[j]);
          }
        
    }

}
