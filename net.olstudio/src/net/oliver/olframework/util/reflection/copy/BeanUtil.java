package net.oliver.olframework.util.reflection.copy;

import java.lang.reflect.*;
import java.util.*;

/**
  本类就是用于formBean之间属性的对拷,主要的方法是copyFormBeanPropertys
 */
public class BeanUtil {
	
  /**
    得到fields的容器
    @param  Class objClass 当前对象的Class对象
    @return  ArrayList承装对象属性的容器
   */
  public static ArrayList getFildsArray(Class objClass) {
    ArrayList al = null;
    try {
      //得到所有的属性
      Field[] fields = objClass.getDeclaredFields();
      al = new ArrayList();
      for (int i = 0; i < fields.length; i++) {
        al.add(fields[i].getName());
      }
    } catch (Exception e) {
      al = null;
      System.out.println(e);
    }
    return al;
  }


  /**
     得到bean属性与方法的映射关系
     @param  Class objClass 当前对象的Class对象
     @return  HashMap承装属性与方法的映射关系的容器
   */
  public static HashMap getMethodsMap(Class objClass) {
    HashMap hm = null;
    try {
      //得到此对象所有的方法
      Method[] methods = objClass.getDeclaredMethods();
      hm = new HashMap(); //承装属性与方法的映射关系的容器
      String fieldName = ""; //属性名称首字母为大写
      String methodName = ""; //方法名称
      ArrayList al = getFildsArray(objClass); //得到本类的所有属性
      boolean isEndWithGet = false; //是否是get开头的
      boolean isFind = false; //判断方法名称是否包含此属性名称
      boolean isEndWithSet = false; //是否是set开头的
      if (al != null) { //属性不能不存在
        int alSize = al.size(); //多少个属性
        for (int i = 0; i < alSize; i++) {
          //得到首字母为大写的属性名称
          fieldName = upFirstChar((String) al.get(i));
          //对应属性名称的get和set方法
          Method[] myMothodArrag = new Method[2];
          //遍历所有方法找到和属性名称相同的set和get方法
          for (int j = 0; j < methods.length; j++) {
            methodName = (methods[j].getName());
            isEndWithGet = methodName.startsWith("get");
            isFind = methodName.endsWith(fieldName);
            isEndWithSet = methodName.startsWith("set");
            if (isFind & isEndWithGet) {
              myMothodArrag[0] = methods[j]; //如果是get方法,则放到自定义容器的第一位
            } else if (isFind & isEndWithSet) {
              myMothodArrag[1] = methods[j]; //如果是set方法,则放到自定义容器的第2位
            }
          }
          //遍历后只有属性,缺少方法的情况,不放到影射容器里
          if (myMothodArrag[0] != null & myMothodArrag[0] != null) {
            hm.put(fieldName, myMothodArrag);
          }

        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }
    return hm;
  }


  /**
    change the fist letter to UpperCase
   */
  public static String upFirstChar(String str) {
    String first = (str.substring(0, 1)).toUpperCase();
    String other = str.substring(1);
    return first + other;
  }


  /**only copy the formbean property,that means bean's properties are String type
      将第一formbean的属性复制到第二个formbean属性(相同名称的属性)
     @param  Object form1 被复制的formbean
     @param  Object form2 要复制的formbean
     @param  boolean isCover   
     如果第一个formbean属性是null的话,你不需要用这个null来覆盖要复制的formbean,则此参数为false;
     @return  HashMap承装属性与方法的映射关系的容器
   */
  public static void copyFormBeanPropertys(Object form1, Object form2,boolean isCover) {
    try {
      Class form1Class = form1.getClass();
      Class form2Class = form2.getClass();
      HashMap hm1 = getMethodsMap(form1Class); //第一个bean属性与方法的映射关系的容器
      HashMap hm2 = getMethodsMap(form2Class); //第二个bean属性与方法的映射关系的容器
      Method[] myMothodArrag1 = new Method[2]; //form1承装get和set影射关系容器
      Method[] myMothodArrag2 = new Method[2]; //form2承装get和set影射关系容器
      Iterator it_2 = hm2.keySet().iterator(); //第二个容器的遍历器(就是要复制的formbean)
      String fieldsNameForm2 = null; //第二个form的属性名称
      String[] str = null; //参数列表
      Method getMethod = null; //对应get方法
      Method setMethod = null; //对应set方法
      Object obj = null; //反射方法的返回值
      boolean isContainsKey = false; //判断是存在
      if (hm1 == null | hm2 == null) {
        throw new Exception("遍历form方法和属性的影射关系出错!!!");
      } while (it_2.hasNext()) { //遍历第二个formbean属性
        fieldsNameForm2 = (String) it_2.next();
        //判断第一个formbean是否包含这个属性
        isContainsKey = hm1.containsKey(fieldsNameForm2); 
        //得到承装第二个get和set影射关系容器
        myMothodArrag2 = (Method[]) hm2.get(fieldsNameForm2);
        if (isContainsKey) { //第一个formbean如果包含这个属性
          myMothodArrag1 = (Method[]) hm1.get(fieldsNameForm2); //得到承装get和set影射关系容器
          getMethod = myMothodArrag1[0]; //自定义容器的第一个是get方法
          obj = getMethod.invoke(form1, null); //动态调用form1的get方法,返回属性的值
          System.out.println(obj + "    ________________________");
          str = new String[1]; //向下转型(因为formbean所有的属性都是String)
          str[0] = (String) obj;
          if (isCover) {
            setMethod = myMothodArrag2[1]; //第二个bean的set方法
            setMethod.invoke(form2, str); //动态调用第二个formbean的set方法
          } else {
            if (obj != null) {
              setMethod = myMothodArrag2[1]; //第二个bean的set方法
              System.out.println(setMethod.getName());
              setMethod.invoke(form2, str); //动态调用第二个formbean的set方法
            }
          }
        }
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) {
//    BeansTest form1 = new BeansTest();
//    Bean2 form2 = new Bean2();
//    copyFormBeanPropertys(form1, form2, false);

  }
}