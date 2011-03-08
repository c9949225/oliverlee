package com.eibm.persistence.springhibernate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author firebody utility class for dealing with java beans
 */
public class BeanUtil
{
    /**
     * dumps the properties names and values of a bean into a string
     * 
     * @param bean
     *            the JavaBean to be intropected
     * @return String a dump of the property names and values
     */
    public static String toString(Object bean)
    {
        StringBuffer buf = new StringBuffer();
        if (bean != null)
        {
            try
            {
                BeanInfo binfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] properties = binfo
                        .getPropertyDescriptors();
                if (properties != null)
                {
                    for (int i = 0; i < properties.length; i++)
                    {
                        Method readMethod = properties[i].getReadMethod();
                        if (readMethod != null)
                        {
                            buf.append(properties[i].getName());
                            buf.append(" = ");
                            Object obj = readMethod.invoke(bean, null);
                            if (obj != null)
                            {
                                buf.append(obj.toString());
                            } else
                            {
                                buf.append("<empty>");
                            }
                            buf.append("\n");
                        }
                    }
                }
            } catch (Exception e)
            {
                // ignore exceptions thrown, this is a development aid
            }
        }
        return buf.toString();
    }

    // �ǵ���get��������װ��,�Ҿ���session�е�������ʵ�ּ���װ��
    // ���û�����£����ܽ��Ǵ����ʧ�����������Ŷ��
    // ���Ƕ���һЩ��Ҫ���ʼ������ݵ�action��˵��������DAO���޶���������� 
    public static void loadCollectionsForLazy(Object bean)
    {

        if (bean != null)
        {
            try
            {
                BeanInfo binfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] properties = binfo
                        .getPropertyDescriptors();
                if (properties != null)
                {
                    for (int i = 0; i < properties.length; i++)
                    {
                        Method readMethod = properties[i].getReadMethod();
                        if (readMethod != null)
                        {

                            Object object = readMethod.invoke(bean, null);
                            if (object instanceof Collection)
                                initCollection(object);
                            /*System.out.println("In BeanUtil invokeAllReadMethods,The readed Methods:\n"+
                             "ReadMethod:\n"+readMethod.getName()+" Object:\n"+object);*/
                        }
                    }
                }
            } catch (Exception e)
            {
                // ignore exceptions thrown, this is a development aid
            }
        }

    }

    /**
     *
     * @param o
     */
    private static void initCollection(Object o)
    {

        Collection collection = (Collection) o;
        Iterator iter = collection.iterator();
        Object tmp;
        while (iter.hasNext())
        {
            tmp = iter.next();
        }

    }
}