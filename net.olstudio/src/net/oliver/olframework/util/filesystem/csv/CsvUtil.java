package net.oliver.olframework.util.filesystem.csv;

/**
 * Copyright(C) 2006 Agree Tech, All rights reserved.
 * 
 * Created on 2006-4-12 by Steve Pu &lt;pu.yun@agree.com.cn&gt;
 */


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author PuYun &lt;pu.yun@agree.com.cn&gt;
 * @version $Id: CsvUtil.java,v 1.6 2007/07/10 08:22:31 puyun Exp $
 */
public class CsvUtil
{
    public static Map csvToMap(String csv)
    {
        if (csv == null)
        {
            return new LinkedHashMap(1);
        }
        Map resultMap = new LinkedHashMap();
        try
        {
            CsvReader reader = new CsvReader(new StringReader(csv));
            String[] line;
            while ((line = reader.readLine()) != null && line.length > 1)
            {
                resultMap.put(line[0], line[1]);
            }
        } catch (IOException e)
        {
        }
        return resultMap;
    }

    public static String[] csvToStringArray(String csv)
    {
        if (csv == null || csv.length() == 0)
        {
            return new String[0];
        }
        CsvReader reader = new CsvReader(new StringReader(csv));
        try
        {
            return reader.readLine();
        } catch (IOException e)
        {
            // this should not happen
            return null;
        }
    }

    public static String mapToCsv(Map map)
    {
        if (map == null)
        {
            return "";
        }
        StringWriter inner = new StringWriter();
        CsvWriter writer = new CsvWriter(inner);
        boolean multi = false;
        try
        {
            for (Iterator it = map.entrySet().iterator(); it.hasNext();)
            {
                if (multi)
                {
                    writer.endBlock();
                }
                Map.Entry entry = (Map.Entry) it.next();
                writer.writeField(entry.getKey().toString());
                writer.writeField(entry.getValue() == null ? "" : entry
                        .getValue().toString());
                multi = true;
            }
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
            // take it easy, this won't happen :-)
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return inner.getBuffer().toString();
    }

    public static String stringArrayToCsv(String[] strArray)
    {
        if (strArray == null)
        {
            return "";
        }
        return stringArrayToCsv(strArray, 0, strArray.length);
    }

    public static String stringArrayToCsv(String[] strArray, int offset,
            int length)
    {
        if (strArray == null)
        {
            return "";
        }

        offset = Math.max(0, offset);

        int end = Math.min(strArray.length, offset + length);

        StringWriter inner = new StringWriter();
        CsvWriter writer = new CsvWriter(inner);
        try
        {
            for (int i = offset; i < end; i++)
            {
                writer.writeField(strArray[i]);
            }
            writer.close();
        } catch (IOException e)
        {
            // take it easy, this won't happen :-)
        }
        return inner.getBuffer().toString();
    }

    private CsvUtil()
    {
    }
}
