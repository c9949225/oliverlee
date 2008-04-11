package net.oliver.olframework.util.filesystem.xml.xmlobject.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.oliver.olframework.util.filesystem.xml.xmlobject.IObjectAndXml;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * 1-��������е��ĵ������������ 2-��μ����ĵ��Ƿ���� ����byte���ж��Ƿ�Ϊ��1 �� �����Reader�Ļ����ж�readLine�Ƿ�Ϊnull
 * 
 * ����ͨ��InputStream�е�available()����,���Ե�ǰ��Ч��ȡ���ֽ����Ƿ���ԭ�ļ���ͬ!
 * 
 * @author Oliver Lee
 * @version 1.00, 2008-1-3 ����03:36:07
 */
public class ObjectAndXml implements IObjectAndXml
{
    private static XStream xstream = new XStream();

    public void transOut(List list, String path)
    {
        File file = new File(path);
        Writer writer;
        ObjectOutputStream out = null;
        try
        {
            writer = new FileWriter(file);
            out = xstream.createObjectOutputStream(writer);
            for (int i = 0; i < list.size(); i++)
            {
                out.writeObject(list.get(i));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                out.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

    /**
     * ��xml�ļ���ȡ���󷵻ض������� ע�⣺���ڼ���ļ���ȡ�����ķ�������ʱʹ�ýػ��쳣�ķ�ʽ������Ҫ�Ľ�
     * 
     * @param path
     *            Ҫ��ȡ���ļ�·����
     */
    public Object[] transIn(String path)
    {
        ObjectInputStream in = null;
//        File file = new File(path);
        List list = new ArrayList();
        Reader reader;
        try
        {
            reader = new FileReader(path);
            in = xstream.createObjectInputStream(reader);
            Object obj = new Object();
            if (in != null)
            {
                while (true)
                {
                    try
                    {
                        obj = in.readObject();
                    } catch (EOFException e)
                    {
                        break;
                    }
                    list.add(obj);
                }
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            // try
            // {
            // System.out.println(in.available());
            // } catch (IOException e1)
            // {
            // e1.printStackTrace();
            // }
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return list.toArray();
    }

}
