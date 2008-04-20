package net.oliver.olframework.util.filesystem.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * <title>文件通用工具类</title>
 * <description></description>
 * @author 李甫
 * <copyright>北京赞同科技有限发展公司@2008</copyright>
 * @version 1.0
 * 2008-4-11 上午11:18:19
 */
public class FileUtil
{
    /**
     * 保存文件
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
     * @param filename 文件名
     * @param fileData 文件字节数据
     * @param path 文件存放路径
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
    
    public String getDateTime()
    {
        SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = f.format(new java.util.Date());
        return time;
    }
    
    public String getFile(String file)
    {
        DealFile df = new DealFile();
        df.connFIS(file);
        String str = df.readCHStr();
        df.closeFIS();
        return str;
    }
    public boolean setFile(String file,String content)
    {
        DealFile df = new DealFile();
        df.connFOS(file);
        df.writeCHStr(content);
        df.closeFOS();
        return true;
    }
    public static boolean deleteFile(String file) {
        DealFile df = new DealFile();
        df.deleteFile(file);
        return true;
    }
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
}
