// created by mlrain on 2007-10-8 下午03:51:00
package net.oliver.olframework.commu.sock;

import net.oliver.olframework.util.string.StringUtil;


/**
 * 工具类。
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-8 下午03:51:03
 */
public class Utils
{
    /**
     * 检测给定的字符串是否是合法的IP地址。<BR>
     * 只适合于ipV4。
     * 
     * @param ipStr
     *            IP地址
     * @return true / false
     */
    public static boolean validateIP(String ipStr)
    {
        // 不允许为空
        if (StringUtil.empty(ipStr))
            return false;

        // 使用逗号分割，必须包含四段
        String[] segs = StringUtil.split(ipStr, ".");
        if (segs.length != 4)
            return false;

        // 每段必须是整形，而且值必须是0 ~ 255
        for (int i = 0; i < 4; ++i)
        {
            try
            {
                int iTmp = Integer.parseInt(segs[i]);
                if (iTmp < 0 || iTmp > 255)
                    return false;
            } catch (NumberFormatException e)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测给定的监听端口是否合法。
     * 
     * @param port
     *            监听端口
     * @return true / false
     */
    public static boolean validatePort(int port)
    {
        return (port > 0 && port < 65535);
    }
}
