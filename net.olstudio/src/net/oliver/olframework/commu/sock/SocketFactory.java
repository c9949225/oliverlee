// created by mlrain on 2007-10-9 ����10:08:32
package net.oliver.olframework.commu.sock;

import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * socket factory.
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-9 ����10:09:09
 */
public class SocketFactory
{
    /**
     * logger
     */
    private static Logger logger = Logger.getLogger(SocketFactory.class);

    /**
     * ��ȡָ��������socket���ӡ�
     * 
     * @param ip
     *            ������IP
     * @param port
     *            �������˿�
     * @param timeout
     *            ͨѶ��ʱʱ�䣨���룩
     * @return socket����
     * @throws OlCommuException
     *             �����������ͨѶ�쳣ʱ�׳�
     */
    public static Socket getSocket(String ip, int port, int timeout)
            throws OlCommuException
    {
        if (!Utils.validateIP(ip) || !Utils.validatePort(port))
            throw new OlCommuException("�����Ƿ�");

        if (timeout < 0)
            throw new OlCommuException("�����Ƿ�");

        try
        {
            Socket sock = new Socket(ip, port);
            sock.setSoTimeout(timeout);
            return sock;
        } catch (Exception e)
        {
            throw new OlCommuException("���ӷ���������", e);
        }
    }

    /**
     * ��ȡָ��������socket���ӡ�
     * 
     * @param ip
     *            ������IP
     * @param port
     *            �������˿�
     * @return socket����
     * @throws OlCommuException
     *             �����������ͨѶ�쳣ʱ�׳�
     */
    public static Socket getSocket(String ip, int port) throws OlCommuException
    {
        return getSocket(ip, port,
                CommucationConstants.COMMUCATION_TIMEOUT_DEFAULT);
    }

    /**
     * ���������л�ȡ������������socket���ӡ�
     * 
     * @param context
     *            ������
     * @return socket����
     * @throws OlCommuException
     *             �����������ͨѶ�쳣ʱ�׳�
     */
    public static Socket getSocket(Map context) throws OlCommuException
    {
        if (context == null)
            throw new OlCommuException("��������:������Ϊ��");

        // ��ȡ����
        String ip = (String) context
                .get(CommucationConstants.CONTEXT_SERVER_IP);
        String port = (String) context
                .get(CommucationConstants.CONTEXT_SERVER_PORT);
        String timeout = (String) context
                .get(CommucationConstants.CONTEXT_SERVER_TIMEOUT);

        try
        {
            int iPort = Integer.parseInt(port);
            int iTimeout = timeout == null ? 0 : Integer.parseInt(timeout);

            return getSocket(ip, iPort, iTimeout);
        } catch (NumberFormatException e)
        {
            throw new OlCommuException("�����Ƿ�");
        }
    }

    /**
     * �ر�socket���ӡ�
     * 
     * @param sock
     *            socket����
     */
    public static void closeSocket(Socket sock)
    {
        // �ر�socket����
        try
        {
            sock.close();
        } catch (Exception e)
        {
            logger.error("�ر�socket���ӳ���", e);
        }
    }
}
