package net.oliver.olframework.util.filesystem.excel.example;

import java.util.List;

/**
 * <title>��װҪ���ɵ�EXCEL�ļ��Ĳ������ݣ�����ö������һ��Excel�ļ�</title>
 * <description></description>
 * @author �
 * <copyright>������ͬ�Ƽ����޷�չ��˾@2008</copyright>
 * @version 1.0
 * 2008-5-26 ����05:29:03
 */
public class ExcelContent
{
    private String[] headers;
    private List objects;
    
    public ExcelContent(String[] headers,List objects)
    {
        this.headers = headers;
        this.objects = objects;
    }
    
    public String[] getHeaders()
    {
        return headers;
    }
    public void setHeaders(String[] headers)
    {
        this.headers = headers;
    }
    public List getObjects()
    {
        return objects;
    }
    public void setObjects(List objects)
    {
        this.objects = objects;
    }
}
