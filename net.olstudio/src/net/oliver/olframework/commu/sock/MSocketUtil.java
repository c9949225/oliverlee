// created by mlrain on 2007-10-9 ����10:07:34
package net.oliver.olframework.commu.sock;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


/**
 * socketͨѶ�Ĺ����ࡣ
 * 
 * @author mlrain
 * @version 1.0.0, 2007-10-9 ����10:07:36
 */
public class MSocketUtil
{
    /**
     * ͨ��ָ����socket���ӷ������ݲ�����ָ����С�ķ�����Ϣ��
     * 
     * @param sock
     *            socket����
     * @param data
     *            ��Ҫ���͵�����
     * @param dataSize
     *            ��Ҫ���յ����ݴ�С
     * @return ���յ�������
     * @throws OlCommuException
     *             �����Ƿ�����ͨѶ����ʱ�׳�
     */
    public static byte[] exchange(Socket sock, byte[] data, int dataSize)
            throws OlCommuException
    {
        sendData(sock, data);
        return receiveData(sock, dataSize);
    }

    /**
     * ͨ��ָ����socket���ӷ������ݡ�
     * 
     * @param sock
     *            socket����
     * @param data
     *            ��Ҫ���͵�����
     * @throws OlCommuException
     *             �����Ƿ�����ͨѶ����ʱ�׳�
     */
    public static void sendData(Socket sock, byte[] data)
            throws OlCommuException
    {
        if (data == null)
            throw new OlCommuException("��������:��Ҫ���͵�����Ϊ��");

        // ���socket�Ϸ���
        checkSocketSatus(sock);

        try
        {
            BufferedOutputStream bos = new BufferedOutputStream(sock
                    .getOutputStream());
            bos.write(data);
            bos.flush();
        } catch (IOException e)
        {
            throw new OlCommuException("��������ʱ����", e);
        }
    }

    /**
     * ͨ��ָ����socket���ӽ������ݡ�
     * 
     * @param sock
     *            socket����
     * @param dataSize
     *            ��Ҫ���յ����ݴ�С
     * @return ���յ�������
     * @throws OlCommuException
     *             �����Ƿ�����ͨѶ����ʱ�׳�
     */
    public static byte[] receiveData(Socket sock, int dataSize)
            throws OlCommuException
    {
        // ���socket�Ϸ���
        checkSocketSatus(sock);

        // ���ָ����Ҫ���յ����ݴ�СС�ڵ����㣬�򷵻ش�СΪ����ֽ�����
        if (dataSize <= 0)
            return new byte[0];

        try
        {
            InputStream is = sock.getInputStream();
            byte[] data = new byte[dataSize];
            int byteRead = 0; // �Ѿ���ȡ���ֽ���
            while (byteRead < dataSize)
            {
                int tmp = is.read(data, byteRead, dataSize - byteRead);
                if (tmp < 0)
                {
                    throw new OlCommuException("�������ݳ���");
                }
                byteRead += tmp;
            }

            return data;
        } catch (IOException e)
        {
            throw new OlCommuException("��������ʱ����", e);
        }
    }

    /**
     * ���socket�Ϸ��ԡ�
     * 
     * @param sock
     *            socket����
     * @throws OlCommuException
     *             ����������ȷ����socket״̬����ȷʱ�׳�
     */
    public static void checkSocketSatus(Socket sock) throws OlCommuException
    {
        // ����Ϊ��
        if (sock == null)
            throw new OlCommuException("�Ƿ�������socketΪ��");

        // ��δ����
        if (!sock.isConnected())
            throw new OlCommuException("��δ���ӷ�����");

        // �����Ѿ��ر�
        if (sock.isClosed())
            throw new OlCommuException("�����ѹر�");
    }
}
