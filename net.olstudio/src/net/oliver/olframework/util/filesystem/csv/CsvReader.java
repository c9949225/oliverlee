package net.oliver.olframework.util.filesystem.csv;

/*======================================================
 * Copyright(c) 2004 Agree Tech Co.(http://agree.com.cn/)
 * All rights reserved.
 * 
 * Created on 2004-9-7 12:10:03
 * 
 * Contributors:
 *     Puyun - initial implementation
 ======================================================*/

import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

/**
 * The class
 * 
 * @author Puyun&lt;pu.yun@agree.com.cn&gt;
 */
public class CsvReader
{

    // new String to stop internning
    private char field_delim = Csv.FIELD_DELIMITER;

    private char block_delim = Csv.BLOCK_DELIMITER;

    private Reader reader;

    private boolean newline;

    // should bbb,,,ccc be considered to be two elements?
    // useful for log parsing.
    private boolean consume = false;

    public CsvReader(Reader rdr)
    {
        this.reader = rdr;
    }

    public void setFieldDelimiter(char ch)
    {
        field_delim = ch;
    }

    public void setBlockDelimiter(char ch)
    {
        block_delim = ch;
    }

    public void setConsuming(boolean b)
    {
        this.consume = b;
    }

    public boolean isConsuming()
    {
        return this.consume;
    }

    public String[] readLine() throws IOException
    {
        List list = new LinkedList();
        String str;

        while (true)
        {
            str = readField();
            if (str == null)
            {
                break;
            }
            if (consume && str.length() == 0)
            {
                continue;
            }
            list.add(str);
        }
        if (list.isEmpty())
        {
            return null;
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    public String readField() throws IOException
    {
        if (this.newline)
        {
            this.newline = false;
            return null;
        }

        StringBuffer buffer = new StringBuffer();
        boolean quoted = false;
        int last = -1;
        int ch = this.reader.read();

        if (ch == -1)
        {
            return null;
        }

        if (ch == '"')
        {
            quoted = true;
        } else if (ch == block_delim)
        {
            return null;
        } else if (ch == field_delim)
        {
            return "";
        } else
        {
            buffer.append((char) ch);
        }

        while ((ch = this.reader.read()) != -1)
        {
            if (ch == block_delim)
            {
                if ((quoted && last == '"') || !quoted)
                {
                    this.newline = true;
                    break;
                }
            } else if (ch == field_delim)
            {
                if ((quoted && last == '"') || !quoted)
                {
                    break;
                }
            } else if (ch == '"')
            {
                if (quoted)
                {
                    if (last == '"')
                    {
                        // forget about this quote and move on
                        last = -1;
                    } else
                    {
                        last = '"';
                        continue;
                    }
                }
            }
            buffer.append((char) ch);
        }

        return buffer.toString();
    }

    public void close() throws IOException
    {
        this.reader.close();
    }

}
