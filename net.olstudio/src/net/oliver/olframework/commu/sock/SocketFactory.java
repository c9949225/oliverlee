// created by mlrain on 2007-10-9 上午10:08:32
package net.oliver.olframework.commu.sock;

import java.net.Socket;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * socket factory.
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-9 上午10:09:09
 */
public class SocketFactory
{
    /**
     * logger
     */
    private static Logger logger = Logger.getLogger(SocketFactory.class);

    /**
     * 获取指定参数的socket连接。
     * 
     * @param ip
     *            服务器IP
     * @param port
     *            服务器端口
     * @param timeout
     *            通讯超时时间（毫秒）
     * @return socket连接
     * @throws OlCommuException
     *             参数错误或者通讯异常时抛出
     */
    public static Socket getSocket(String ip, int port, int timeout)
            throws OlCommuException
    {
        if (!Utils.validateIP(ip) || !Utils.validatePort(port))
            throw new OlCommuException("参数非法");

        if (timeout < 0)
            throw new OlCommuException("参数非法");

        try
        {
            Socket sock = new Socket(ip, port);
            sock.setSoTimeout(timeout);
            return sock;
        } catch (Exception e)
        {
            throw new OlCommuException("连接服务器出错", e);
        }
    }

    /**
     * 获取指定参数的socket连接。
     * 
     * @param ip
     *            服务器IP
     * @param port
     *            服务器端口
     * @return socket连接
     * @throws OlCommuException
     *             参数错误或者通讯异常时抛出
     */
    public static Socket getSocket(String ip, int port) throws OlCommuException
    {
        return getSocket(ip, port,
                CommucationConstants.COMMUCATION_TIMEOUT_DEFAULT);
    }

    /**
     * 从上下文中获取参数，并返回socket连接。
     * 
     * @param context
     *            上下文
     * @return socket连接
     * @throws OlCommuException
     *             参数错误或者通讯异常时抛出
     */
    public static Socket getSocket(Map context) throws OlCommuException
    {
        if (context == null)
            throw new OlCommuException("参数错误:上下文为空");

        // 获取参数
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
            throw new OlCommuException("参数非法");
        }
    }

    /**
     * 关闭socket连接。
     * 
     * @param sock
     *            socket连接
     */
    public static void closeSocket(Socket sock)
    {
        // 关闭socket连接
        try
        {
            sock.close();
        } catch (Exception e)
        {
            logger.error("关闭socket连接出错", e);
        }
    }
}
