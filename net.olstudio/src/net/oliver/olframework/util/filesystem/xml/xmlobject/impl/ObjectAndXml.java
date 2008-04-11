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
 * 1-如果在现有的文档上添加新内容 2-如何检测读文档是否结束 面向byte的判断是否为－1 × 如果用Reader的话，判断readLine是否为null
 * 
 * 可以通过InputStream中的available()方法,测试当前有效读取的字节数是否与原文件相同!
 * 
 * @author Oliver Lee
 * @version 1.00, 2008-1-3 下午03:36:07
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
     * 从xml文件读取对象返回对象数组 注意：关于检测文件读取结束的方法，暂时使用截获异常的方式，还需要改进
     * 
     * @param path
     *            要读取的文件路径名
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
