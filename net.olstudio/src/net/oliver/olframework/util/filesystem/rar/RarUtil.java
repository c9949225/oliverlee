package net.oliver.olframework.util.filesystem.rar;

public class RarUtil {

	/**
	   * ��ѹ
	   *
	   * @param compress
	   *             rarѹ���ļ�
	   * @param decompression
	   *             ��ѹ·��
	   */
	public void unZip(String compress, String decompression) throws Exception {
	   java.lang.Runtime rt = java.lang.Runtime.getRuntime();
	   Process p = rt.exec("C:\\Program Files\\WinRAR\\UNRAR.EXE x -o+ -p- " + compress + " " + decompression);
	   StringBuffer sb = new StringBuffer();
	   java.io.InputStream fis = p.getInputStream();
	   int value = 0;
	   while ((value = fis.read()) != -1)
	   {
	           sb.append((char) value);
	   }
	   fis.close();
	   String result = new String(sb.toString().getBytes("ISO-8859-1"), "GBK");
	   System.out.println(result);
	}
	
	/**
	   *
	   * @param outputRar   ���Ŀ¼
	   * @param compression Ҫѹ�����ļ���Ŀ¼
	   * @throws Exception
	   */
	public void zip(String outputRar, String compression) throws Exception {
	   java.lang.Runtime rt = java.lang.Runtime.getRuntime();
	   //rar.exe    x    -t    -o+    -p-    E:\2.rar    E:\  
	   Process p = rt.exec("C:\\Program Files\\WinRAR\\rar.exe x -t -o+ -p- " + outputRar + " " + compression);
	   StringBuffer sb = new StringBuffer();
	   java.io.InputStream fis = p.getInputStream();
	   int value = 0;
	   while ((value = fis.read()) != -1)
	   {
	           sb.append((char) value);
	   }
	   fis.close();
	   String result = new String(sb.toString().getBytes("ISO-8859-1"), "GBK");
	   System.out.println(result);
	}
	
	/**
	   * @param args
	   */
	public static void main(String[] args) {

	   RarUtil test = new RarUtil();
	  
	   String compress = "f:/����ת�������.rar";// rarѹ���ļ�
	   String decompression = "f:/test/";// ��ѹ·��
	  
	   try {
	    test.zip("f:/test.rar", "˵��.txt"); // ѹ��
	    test.unZip(compress, decompression); // ��ѹ��
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	}
}
