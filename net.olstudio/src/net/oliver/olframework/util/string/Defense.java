package net.oliver.olframework.util.string;

/**
 * <title>����String���ػ���</title> <description></description>
 * 
 * @author � <copyright>������ͬ�Ƽ����޷�չ��˾@2008</copyright>
 * @version 1.0 2008-4-10 ����08:21:44
 */
public class Defense
{
    /**
     * Make sure String is not blank or null
     * 
     * @param source
     * @return
     */
    public static boolean notBlank(String str)
    {
        if (str == null || str.equals(""))
        {
            return false;
        } else
        {
            return true;
        }
    }
}
