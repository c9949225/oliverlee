package net.oliver.olframework.util.filesystem.csv;

/*======================================================
 * Copyright(c) 2004 Agree Tech Co.(http://agree.com.cn/)
 * All rights reserved.
 * 
 * Created on 2004-9-7 12:09:10
 * 
 * Contributors:
 *     Puyun - initial implementation
 ======================================================*/

import java.io.IOException;
import java.io.Reader;

/**
 * The class
 * 
 * @author Puyun&lt;pu.yun@agree.com.cn&gt;
 */
public class CsvFieldReader extends CsvReader
{

    private String[] headers;

    private String[] currentLine;

    public CsvFieldReader(Reader reader)
    {
        super(reader);
    }

    public void loadHeaders() throws IOException
    {
        if (this.headers == null)
        {
            useHeaders(super.readLine());
        }
    }

    public void useHeaders(String[] headers)
    {
        this.headers = headers;
    }

    public String readField(String name) throws IOException
    {
        if (this.headers == null)
        {
            loadHeaders();
        }

        if (this.currentLine == null)
        {
            nextBlock();
            if (this.currentLine == null)
            {
                return null;
            }
        }

        int idx = -1;
        for (int i = 0; i < this.headers.length; i++)
        {
            if (name.equals(this.headers[i]))
            {
                idx = i;
            }
        }

        String field = null;

        if (idx != -1)
        {
            field = currentLine[idx];
        }

        return field;
    }

    public boolean nextBlock() throws IOException
    {
        this.currentLine = super.readLine();
        return (this.currentLine != null);
    }

}