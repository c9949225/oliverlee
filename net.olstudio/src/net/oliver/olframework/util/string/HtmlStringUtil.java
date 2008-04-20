package net.oliver.olframework.util.string;

import java.util.HashMap;
import java.util.Map;

/**
 * <title>和HTML有关的字符串解析</title> <description> 1-getMapFromPara 2-unescape
 * </description>
 * 
 * @author 李甫 
 * <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0 2008-4-10 下午08:12:54
 */
public class HtmlStringUtil
{

    /**
     * Parse the &name=value pattern like String into a Map
     * 
     * @param str
     * @return
     */
    public static Map getMapFromUrlPara(String str)
    {
        if (Defense.isBlank(str))
        {
            return null;
        }

        if(str.indexOf("&")<0)
        {
            return null;
        }
        
        Map result = new HashMap();

        String[] ary = str.split("[&]");

        for (int i = 0; i < ary.length; i++)
        {
            if (ary[i].indexOf("=") != -1)
            {
                int pos = ary[i].indexOf("=");
                result.put(ary[i].substring(0, pos), ary[i].substring(pos+1));
            }
        }

        return result;
    }

    /**
     * Response to escape() function in jacascript
     * 
     * @param src
     * @return
     */
    public static String unescape(String src)
    {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length())
        {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos + 1) == 'u')
                {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else
                {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else
                {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
}
