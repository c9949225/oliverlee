// created by mlrain on 2007-10-8 ����03:51:00
package net.oliver.olframework.commu.sock;

import net.oliver.olframework.util.string.StringUtil;


/**
 * �����ࡣ
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-8 ����03:51:03
 */
public class Utils
{
    /**
     * ���������ַ����Ƿ��ǺϷ���IP��ַ��<BR>
     * ֻ�ʺ���ipV4��
     * 
     * @param ipStr
     *            IP��ַ
     * @return true / false
     */
    public static boolean validateIP(String ipStr)
    {
        // ������Ϊ��
        if (StringUtil.empty(ipStr))
            return false;

        // ʹ�ö��ŷָ��������Ķ�
        String[] segs = StringUtil.split(ipStr, ".");
        if (segs.length != 4)
            return false;

        // ÿ�α��������Σ�����ֵ������0 ~ 255
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
     * �������ļ����˿��Ƿ�Ϸ���
     * 
     * @param port
     *            �����˿�
     * @return true / false
     */
    public static boolean validatePort(int port)
    {
        return (port > 0 && port < 65535);
    }
}
