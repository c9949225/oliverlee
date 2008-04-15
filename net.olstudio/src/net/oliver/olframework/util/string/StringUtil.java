package net.oliver.olframework.util.string;

public class StringUtil
{

    /**
     * 取一段字符串的最后一个点后面的内容
     * @param str
     * @return
     */
    public static String getStrAfterPoint(String str)
    {
        if(!Defense.notBlank(str))
        {
           return null; 
        }
        
        if(str.indexOf(".")<0)
        {
            return null;
        }
        
        int index = str.lastIndexOf(".");
        
        return str.substring(index);
        
    }
}
