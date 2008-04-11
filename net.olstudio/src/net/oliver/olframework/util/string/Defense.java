package net.oliver.olframework.util.string;

/**
 * <title>关于String的守护类</title> <description></description>
 * 
 * @author 李甫 <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0 2008-4-10 下午08:21:44
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
