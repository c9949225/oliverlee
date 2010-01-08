package net.oliver.olframework.util.filesystem.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
     * �õ�����ʱ��
     * @return
     */
    public String getDateTime()
    {
        SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = f.format(new java.util.Date());
        return time;
    }
    
    /**
     * �õ��ļ��ַ�������
     * @param file
     * @return
     */
    public String getFile(String file)
    {
        DealFile df = new DealFile();
        df.connFIS(file);
        String str = df.readCHStr();
        df.closeFIS();
        return str;
    }
    
    /**
     * �����ļ�����
     * @param file
     * @param content
     * @return
     */
    public boolean setFile(String file,String content)
    {
        DealFile df = new DealFile();
        df.connFOS(file);
        df.writeCHStr(content);
        df.closeFOS();
        return true;
    }
    
    /**
     * ɾ���ļ�
     * @param file
     * @return
     */
    public static boolean deleteFile(String file) {
        DealFile df = new DealFile();
        df.deleteFile(file);
        return true;
    }
    
    /**
     * ����Ŀ¼
     * @param dirUrl
     */
    public static void createDir(String dirUrl)
    {   
        File dir = new File(dirUrl);
        if(!dir.exists())
        {
            dir.mkdir();
        }
        else
        {
            if(!dir.isDirectory())
            {
                dir.delete();
                dir.mkdir();
            }
        }
    }
    
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
    public static void saveFile(String filename, byte[] fileData, String path)
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
