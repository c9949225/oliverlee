// created by mlrain on 2007-10-9 上午10:07:34
package net.oliver.olframework.commu.sock;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/**
 * socket通讯的工具类。
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-9 上午10:07:36
 */
public class MSocketUtil
{
    /**
     * 通过指定的socket连接发送数据并接收指定大小的返回信息。
     * 
     * @param sock
     *            socket连接
     * @param data
     *            需要发送的数据
     * @param dataSize
     *            需要接收的数据大小
     * @return 接收到的数据
     * @throws OlCommuException
     *             参数非法或者通讯错误时抛出
     */
    public static byte[] exchange(Socket sock, byte[] data, int dataSize)
            throws OlCommuException
    {
        sendData(sock, data);
        return receiveData(sock, dataSize);
    }

    /**
     * 通过指定的socket连接发送数据。
     * 
     * @param sock
     *            socket连接
     * @param data
     *            需要发送的数据
     * @throws OlCommuException
     *             参数非法或者通讯错误时抛出
     */
    public static void sendData(Socket sock, byte[] data)
            throws OlCommuException
    {
        if (data == null)
            throw new OlCommuException("参数错误:需要发送的数据为空");

        // 检测socket合法性
        checkSocketSatus(sock);

        try
        {
            BufferedOutputStream bos = new BufferedOutputStream(sock
                    .getOutputStream());
            bos.write(data);
            bos.flush();
        } catch (IOException e)
        {
            throw new OlCommuException("发送数据时出错", e);
        }
    }

    /**
     * 通过指定的socket连接接收数据。
     * 
     * @param sock
     *            socket连接
     * @param dataSize
     *            需要接收的数据大小
     * @return 接收到的数据
     * @throws OlCommuException
     *             参数非法或者通讯错误时抛出
     */
    public static byte[] receiveData(Socket sock, int dataSize)
            throws OlCommuException
    {
        // 检测socket合法性
        checkSocketSatus(sock);

        // 如果指定需要接收的数据大小小于等于零，则返回大小为零的字节数组
        if (dataSize <= 0)
            return new byte[0];

        try
        {
            InputStream is = sock.getInputStream();
            byte[] data = new byte[dataSize];
            int byteRead = 0; // 已经读取的字节数
            while (byteRead < dataSize)
            {
                int tmp = is.read(data, byteRead, dataSize - byteRead);
                if (tmp < 0)
                {
                    throw new OlCommuException("接收数据出错");
                }
                byteRead += tmp;
            }

            return data;
        } catch (IOException e)
        {
            throw new OlCommuException("接收数据时出错", e);
        }
    }

    /**
     * 检测socket合法性。
     * 
     * @param sock
     *            socket连接
     * @throws OlCommuException
     *             若参数不正确或者socket状态不正确时抛出
     */
    public static void checkSocketSatus(Socket sock) throws OlCommuException
    {
        // 参数为空
        if (sock == null)
            throw new OlCommuException("非法参数：socket为空");

        // 尚未连接
        if (!sock.isConnected())
            throw new OlCommuException("尚未连接服务器");

        // 连接已经关闭
        if (sock.isClosed())
            throw new OlCommuException("连接已关闭");
    }
}
