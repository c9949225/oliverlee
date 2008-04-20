package net.oliver.olframework.test;

import java.util.Map;

import net.oliver.olframework.util.string.HtmlStringUtil;

public class HtmlStringUtilTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String url = "&para1=value1&para2=value2&para3=value3";
        
        Map map = HtmlStringUtil.getMapFromUrlPara(url);
        
        System.out.println(map.size());
    }

}
