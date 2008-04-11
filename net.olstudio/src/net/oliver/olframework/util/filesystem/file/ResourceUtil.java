package net.oliver.olframework.util.filesystem.file;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceUtil
{
    private static final String ENCODING_ISO_8859_1 = "iso-8859-1";

    private static Pattern xmlEncodingPattern = Pattern
            .compile("encoding=['\"](.+)['\"]");
    
    /**
     * 将InputStream转为ByteArrayOutputStream
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] inputStreamToByteArray(InputStream is)
            throws IOException
    {
        if (is == null)
        {
            return null;
        }
        try
        {

            ByteArrayOutputStream result = new ByteArrayOutputStream(1 << 13);// 8k
            byte[] buffer = new byte[1 << 13]; // 8k
            int length = 0;
            while ((length = is.read(buffer)) >= 0)
            {
                result.write(buffer, 0, length);
            }

            return result.toByteArray();
        } finally
        {
            is.close();
        }
    }
    
    /**
     * InputStream转为XML字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputStreamToXmlString(InputStream is)
            throws IOException
    {
        if (is == null)
        {
            return null;
        }
        try
        {
            // 1. decide encoding
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, ENCODING_ISO_8859_1));
            String line = reader.readLine();
            Matcher m = xmlEncodingPattern.matcher(line);
            String encoding = "GBK";
            if (m.find())
            {
                encoding = m.group(1);
            }
            // 2. input stream to writer buffer
            StringWriter writer = new StringWriter();
            writer.write(line);
            writer.write('\n');
            while ((line = reader.readLine()) != null)
            {
                writer.write(new String(line.getBytes(ENCODING_ISO_8859_1),
                        encoding));
            }
            return writer.toString();
        } finally
        {
            is.close();
        }
    }

    private ResourceUtil()
    {
    }
    
    public static void main(String[] args){
//        ResourceUtil util = new ResourceUtil();
//        File file = new File("E:\\eclipse\\tapestry-workspace\\OLFrameWork\\build.xml");
//        try
//        {
//            InputStream is = new FileInputStream(file);
//            System.out.println(util.inputStreamToXmlString(is));
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
}
