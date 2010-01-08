package net.oliver.olframework.util.filesystem.excel.example;

import java.util.List;

/**
 * <title>封装要生成的EXCEL文件的部分内容，多个该对象组成一个Excel文件</title>
 * <description></description>
 * @author 李甫
 * <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0
 * 2008-5-26 下午05:29:03
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
