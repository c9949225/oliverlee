/*
 * Copyright(C) 2009 Agree Tech, All rights reserved.
 * 
 * Created on 2009-3-4   by ShenBo
 */
package net.oliver.olframework.util.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

 /**
 * <DL><DT><B>
 * zip��ѹ�����ߣ��ṩzip��ѹ����̬����
 * </B></DT><p><DD>
 * 
 * @author ��
 * @author ��ͬ�Ƽ�
 * @version $Revision: 1.1.2.1 $ $Date: 2009/03/18 05:06:43 $ 
 */
public class ZipUtil {
	
	private static final Log logger = LogFactory.getLog(ZipUtil.class);
	 
	static int bufferLength = 2048;

	static byte[] buffer = new byte[bufferLength];
	
	/**  
     * ѹ����̬����  
     * @param inputFile  
     * @param zipFileName  
     */ 
	public static void zip(String inputFile, String zipFileName) throws Exception {
		zip(new File(inputFile), zipFileName, "");
	}
	
	/**  
     * ѹ����̬����  
     * @param inputFile  
     * @param zipFileName  
     * @param base ѹ���ļ��ĸ�Ŀ¼����
     */ 
	public static void zip(String inputFile, String zipFileName, String base) throws Exception {
		zip(new File(inputFile), zipFileName, base);
	}

	public static void zip(File inputFile, String zipFileName, String base) throws Exception {

		if(logger.isDebugEnabled()) {
			logger.debug("��ʼѹ���ļ�" + inputFile +" ��" + zipFileName);
		}
		
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		
		zip(out, inputFile, base);

		out.close();

		if(logger.isDebugEnabled()) {
			logger.debug("���ѹ���ļ�" + inputFile +" ��" + zipFileName);
		}
	}

	/**  
     * ��ѹ��̬����  
     * @param zipFileName  
     * @param outputDirectory  
     */  
	public static void unzip(String zipFileName,String outputDirectory) {   
        try {   
        	ZipFile zipFile = new ZipFile(zipFileName);   
            Enumeration e = zipFile.getEntries();   
  
            ZipEntry zipEntry = null;   
  
            while (e.hasMoreElements()){   
                zipEntry = (ZipEntry)e.nextElement();   
                if (zipEntry.isDirectory()){   
                    String name=zipEntry.getName();   
                    name=name.substring(0,name.length()-1);        
                    mkDirs(outputDirectory+File.separator+name);                       
  
                }else{   
                    String name=zipEntry.getName();   
                    String dir = name.substring(0,name.lastIndexOf("/"));   
                    mkDirs(outputDirectory+File.separator+dir);                    
                    File f=new File(outputDirectory+File.separator+zipEntry.getName());   
                    f.createNewFile();   
                    InputStream in = zipFile.getInputStream(zipEntry);   
                    FileOutputStream out=new FileOutputStream(f);                      
                    int c;   
                    byte[] by=new byte[1024];   
                    while((c=in.read(by)) != -1){   
                        out.write(by,0,c);   
                    }   
                    out.close();   
                    in.close();   
                }   
            }   
        }   
        catch (Exception ex){   
            System.out.println("��ѹ�ļ��쳣"+ex.getMessage());   
            ex.printStackTrace();   
        }   
    }  
	
	
	/**  
     * ����Ŀ¼��������Ŀ¼  
     * @param dir  
     * @throws Exception  
     */  
    private static void mkDirs(String dir) throws Exception{   
        if(dir == null || dir.equals("")) return;   
        File f1 = new File(dir);   
        if(!f1.exists())   
            f1.mkdirs();   
    }      
  

	public static void zip(ZipOutputStream out, File f, String base) throws Exception {

		if (f.isDirectory())

		{

			File[] fl = f.listFiles();

			out.putNextEntry(new ZipEntry(base + "/"));

			base = base.length() == 0 ? "" : base + "/";

			for (int i = 0; i < fl.length; i++)

			{

				if(fl[i].isDirectory()) {
					zip(out, fl[i], base + fl[i].getName());
				} else {
					zip(out, fl[i], base);
				}

			}

		}

		else

		{
			out.putNextEntry(new ZipEntry(base + f.getName()));

			FileInputStream in = new FileInputStream(f);
			BufferedInputStream inBuffer = new BufferedInputStream(in,
					bufferLength);
			int count = 0;
			while ((count = inBuffer.read(buffer, 0, bufferLength)) != -1)
				out.write(buffer, 0, count);
			inBuffer.close();
			in.close();
		}

	}

}
