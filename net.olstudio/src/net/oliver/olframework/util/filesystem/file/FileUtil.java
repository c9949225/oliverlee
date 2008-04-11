package net.oliver.olframework.util.filesystem.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <title>�ļ�ͨ�ù�����</title>
 * <description></description>
 * @author �
 * <copyright>������ͬ�Ƽ����޷�չ��˾@2008</copyright>
 * @version 1.0
 * 2008-4-11 ����11:18:19
 */
public class FileUtil
{
    /**
     * �����ļ�
     * 
     * byte docData[] = new byte[(int) getFile().getSize()];
     * InputStream fileInput = getFile().getStream();
     *         
     * try {
     *     fileInput.read(docData);
     * } catch (IOException e) {
     *      throw new RuntimeException(e);
     * }
     * saveFile("x.dat",docData,"D:\\");
     * 
     * @param filename �ļ���
     * @param fileData �ļ��ֽ�����
     * @param path �ļ����·��
     */
    public void saveFile(String filename, byte[] fileData, String path)
    {
        File file = new File(path, filename);
        try
        {
            FileOutputStream output = new FileOutputStream(file);
            try
            {
                output.write(fileData);
            } finally
            {
                output.close();
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
