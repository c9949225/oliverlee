package net.oliver.olframework.util.filesystem.csv;

/*======================================================
 * Copyright(c) 2004 Agree Tech Co.(http://agree.com.cn/)
 * All rights reserved.
 * 
 * Created on 2004-9-7 12:08:44
 * 
 * Contributors:
 *     Puyun - initial implementation
 ======================================================*/

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Set;

/**
 * The class
 * 
 * @author Puyun&lt;pu.yun@agree.com.cn&gt;
 */
public class Csv
{
    static public char FIELD_DELIMITER = ',';

    static public char BLOCK_DELIMITER = '\n';

    private LinkedList list = new LinkedList();

    private String[] headers;

    public Csv(CsvReader reader) throws IOException
    {
        this.headers = reader.readLine();

        String[] line = null;

        // suck in the file.
        // TODO: What's the optimal structure for this
        while ((line = reader.readLine()) != null)
        {
            list.add(line);
        }
    }

    // get all of the unique fields for this header
    public String[] getAll(String header)
    {
        HashSet values = new HashSet();
        int idx = getHeaderIndex(header);
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            values.add(((String[]) iterator.next())[idx]);
        }
        return (String[]) values.toArray(new String[0]);
    }

    private int getHeaderIndex(String header)
    {
        for (int i = 0; i < this.headers.length; i++)
        {
            if (header.equals(this.headers[i]))
            {
                return i;
            }
        }
        return -1;
    }

    public String[] get(String header, String subheader, String value)
    {
        HashSet values = new HashSet();
        int idx = getHeaderIndex(header);
        int subidx = getHeaderIndex(subheader);
        Iterator iterator = list.iterator();
        while (iterator.hasNext())
        {
            String[] strs = (String[]) iterator.next();
            if (value.equals(strs[idx]))
            {
                values.add(strs[subidx]);
            }
        }
        return (String[]) values.toArray(new String[0]);
    }

    public String[] get(String header, Properties context)
    {
        // optimisation
        if (context.contains(header))
        {
            String[] ret = new String[1];
            ret[1] = context.getProperty(header);
            return ret;
        }

        HashSet values = new HashSet();
        int idx = getHeaderIndex(header);
        Iterator iterator = list.iterator();
        Set keys = context.keySet();
        LABEL: while (iterator.hasNext())
        {
            // get next csv row
            String[] strs = (String[]) iterator.next();

            // check that this row is in context
            Iterator keysIterator = keys.iterator();
            while (keysIterator.hasNext())
            {
                String key = (String) keysIterator.next();
                String value = context.getProperty(key);
                int hdrIndex = getHeaderIndex(key);
                if (!value.equals(strs[hdrIndex]))
                {
                    continue LABEL;
                }
            }

            values.add(strs[idx]);
        }

        return (String[]) values.toArray(new String[0]);
    }

}