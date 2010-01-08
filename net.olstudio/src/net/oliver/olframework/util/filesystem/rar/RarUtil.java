package net.oliver.olframework.util.filesystem.rar;

public class RarUtil {

	/**
	   * 解压
	   *
	   * @param compress
	   *             rar压缩文件
	   * @param decompression
	   *             解压路径
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
	   * @param outputRar   输出目录
	   * @param compression 要压缩的文件或目录
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
	  
	   String compress = "f:/增加转码过滤器.rar";// rar压缩文件
	   String decompression = "f:/test/";// 解压路径
	  
	   try {
	    test.zip("f:/test.rar", "说明.txt"); // 压缩
	    test.unZip(compress, decompression); // 解压缩
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	}
}
