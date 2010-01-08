package net.oliver.olframework.util.reflection.copy;

import java.lang.reflect.*;
import java.util.*;

/**
  �����������formBean֮�����ԵĶԿ�,��Ҫ�ķ�����copyFormBeanPropertys
 */
public class BeanUtil {
	
  /**
    �õ�fields������
    @param  Class objClass ��ǰ�����Class����
    @return  ArrayList��װ�������Ե�����
   */
  public static ArrayList getFildsArray(Class objClass) {
    ArrayList al = null;
    try {
      //�õ����е�����
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
     �õ�bean�����뷽����ӳ���ϵ
     @param  Class objClass ��ǰ�����Class����
     @return  HashMap��װ�����뷽����ӳ���ϵ������
   */
  public static HashMap getMethodsMap(Class objClass) {
    HashMap hm = null;
    try {
      //�õ��˶������еķ���
      Method[] methods = objClass.getDeclaredMethods();
      hm = new HashMap(); //��װ�����뷽����ӳ���ϵ������
      String fieldName = ""; //������������ĸΪ��д
      String methodName = ""; //��������
      ArrayList al = getFildsArray(objClass); //�õ��������������
      boolean isEndWithGet = false; //�Ƿ���get��ͷ��
      boolean isFind = false; //�жϷ��������Ƿ��������������
      boolean isEndWithSet = false; //�Ƿ���set��ͷ��
      if (al != null) { //���Բ��ܲ�����
        int alSize = al.size(); //���ٸ�����
        for (int i = 0; i < alSize; i++) {
          //�õ�����ĸΪ��д����������
          fieldName = upFirstChar((String) al.get(i));
          //��Ӧ�������Ƶ�get��set����
          Method[] myMothodArrag = new Method[2];
          //�������з����ҵ�������������ͬ��set��get����
          for (int j = 0; j < methods.length; j++) {
            methodName = (methods[j].getName());
            isEndWithGet = methodName.startsWith("get");
            isFind = methodName.endsWith(fieldName);
            isEndWithSet = methodName.startsWith("set");
            if (isFind & isEndWithGet) {
              myMothodArrag[0] = methods[j]; //�����get����,��ŵ��Զ��������ĵ�һλ
            } else if (isFind & isEndWithSet) {
              myMothodArrag[1] = methods[j]; //�����set����,��ŵ��Զ��������ĵ�2λ
            }
          }
          //������ֻ������,ȱ�ٷ��������,���ŵ�Ӱ��������
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
      ����һformbean�����Ը��Ƶ��ڶ���formbean����(��ͬ���Ƶ�����)
     @param  Object form1 �����Ƶ�formbean
     @param  Object form2 Ҫ���Ƶ�formbean
     @param  boolean isCover   
     �����һ��formbean������null�Ļ�,�㲻��Ҫ�����null������Ҫ���Ƶ�formbean,��˲���Ϊfalse;
     @return  HashMap��װ�����뷽����ӳ���ϵ������
   */
  public static void copyFormBeanPropertys(Object form1, Object form2,boolean isCover) {
    try {
      Class form1Class = form1.getClass();
      Class form2Class = form2.getClass();
      HashMap hm1 = getMethodsMap(form1Class); //��һ��bean�����뷽����ӳ���ϵ������
      HashMap hm2 = getMethodsMap(form2Class); //�ڶ���bean�����뷽����ӳ���ϵ������
      Method[] myMothodArrag1 = new Method[2]; //form1��װget��setӰ���ϵ����
      Method[] myMothodArrag2 = new Method[2]; //form2��װget��setӰ���ϵ����
      Iterator it_2 = hm2.keySet().iterator(); //�ڶ��������ı�����(����Ҫ���Ƶ�formbean)
      String fieldsNameForm2 = null; //�ڶ���form����������
      String[] str = null; //�����б�
      Method getMethod = null; //��Ӧget����
      Method setMethod = null; //��Ӧset����
      Object obj = null; //���䷽���ķ���ֵ
      boolean isContainsKey = false; //�ж��Ǵ���
      if (hm1 == null | hm2 == null) {
        throw new Exception("����form���������Ե�Ӱ���ϵ����!!!");
      } while (it_2.hasNext()) { //�����ڶ���formbean����
        fieldsNameForm2 = (String) it_2.next();
        //�жϵ�һ��formbean�Ƿ�����������
        isContainsKey = hm1.containsKey(fieldsNameForm2); 
        //�õ���װ�ڶ���get��setӰ���ϵ����
        myMothodArrag2 = (Method[]) hm2.get(fieldsNameForm2);
        if (isContainsKey) { //��һ��formbean��������������
          myMothodArrag1 = (Method[]) hm1.get(fieldsNameForm2); //�õ���װget��setӰ���ϵ����
          getMethod = myMothodArrag1[0]; //�Զ��������ĵ�һ����get����
          obj = getMethod.invoke(form1, null); //��̬����form1��get����,�������Ե�ֵ
          System.out.println(obj + "    ________________________");
          str = new String[1]; //����ת��(��Ϊformbean���е����Զ���String)
          str[0] = (String) obj;
          if (isCover) {
            setMethod = myMothodArrag2[1]; //�ڶ���bean��set����
            setMethod.invoke(form2, str); //��̬���õڶ���formbean��set����
          } else {
            if (obj != null) {
              setMethod = myMothodArrag2[1]; //�ڶ���bean��set����
              System.out.println(setMethod.getName());
              setMethod.invoke(form2, str); //��̬���õڶ���formbean��set����
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