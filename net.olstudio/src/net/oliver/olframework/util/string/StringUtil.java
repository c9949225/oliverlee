package net.oliver.olframework.util.string;

public class StringUtil
{

    /**
     * ȡһ���ַ��������һ������������
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
