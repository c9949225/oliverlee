package net.oliver.olframework.db.db2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

import net.oliver.olframework.util.compress.jzlib.JzlibUtil;

/**
 * java 中对Blob数据的操作数据库的创建代码:
 * 
 * (1)db2 => create table blobTest ( id int not null generated always as identity, image blob )
 * 
 * 添加用户java,密码java
 * 
 * (2) db2=>! net user java java /add java分配权限
 * 
 * (3)db2 => grant select,insert,update,delete on table weihuachao.blobTest to
 * user java
 * 
 * 
 * @author weihuachao 以下代码在microsoft 2003 系统,DB2 9.0中测试成功.
 * 
 */

/*// 类的定义开始------------------------------------------------------------

总结: JAVA 对DB2中的BLOB对象的操作,主要是使用流的技术实现.对BLOB的实现主要步骤有:

	(1)定义要写入的文件  File file=new File("c:/a.jpg");


	(2)定义文件的写入流

	  java.io.BufferedInputStream imageInput = new java.io.BufferedInputStream(
	     new java.io.FileInputStream(file));


	(3)使用函数写入:

	   preparedStatement.setBinaryStream(1, imageInput,(int) file.length());

	(4)执行SQL语句:

	对BLOB的读取采取的步骤有:

	(1)读取Blob的对象:  Blob blob= (Blob) rs.getBlob(1);

	(2)把Blob的对象转化为流:

	   java.io.InputStream inputStream =blob.getBinaryStream();
	(3)定义要写入的文件
	    File fileOutput = new File("c:/backa.jpg");
	(4)文件的写入流的定义
	    FileOutputStream fo = new FileOutputStream(fileOutput);

	(5)写入文件(流的写入技术,就不多讲了)
	    int c;
	    while ((c = inputStream.read()) != -1)

	    {
	    fo.write(c);

	   }
	    //END
*/
public class Test {

	public static void main(String[] args) throws Exception {

		Test test = new Test();
		Connection conn = test.createConnection();

		// Blob对象的插入的方法:
		try {

			// 创建插入语句.
			java.sql.PreparedStatement preparedStatement = conn
					.prepareStatement("insert into blobTest(image)values(?)");

			// 创建文件对象:

			File file = new File("c:/a.jpg");

			// 创建流对象:
			java.io.BufferedInputStream imageInput = new java.io.BufferedInputStream(
					new java.io.FileInputStream(file));

			java.io.ByteArrayOutputStream byteary = new ByteArrayOutputStream();
			
			int c2;
			while ((c2 = imageInput.read()) != -1)
				byteary.write(c2);
			
//			byte[] source = GzipUtil.zip(byteary.toByteArray());
			byte[] source = JzlibUtil.Compress(byteary.toByteArray());
//			byte[] source =  byteary.toByteArray();
			
			// 参数赋值:
			preparedStatement.setBinaryStream(1, new ByteArrayInputStream(source),
					source.length);

			// 执行语句
			preparedStatement.executeUpdate();

			// ------------------------------------------------------------------
			// Blob的读取工作:

			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet rs = st
					.executeQuery("select image from blobTest");

			while (rs.next()) {
				// 读取Blob对象
				Blob blob = (Blob) rs.getBlob(1);

				java.io.InputStream inputStream = blob.getBinaryStream();

				
				
				File fileOutput = new File("c:/x.jpg");

				FileOutputStream fo = new FileOutputStream(fileOutput);
				
				java.io.ByteArrayOutputStream byteary2 = new ByteArrayOutputStream();
				
				int c;
				while ((c = inputStream.read()) != -1)
					byteary2.write(c);
				
//				System.out.println(byteary2.toByteArray().length);
//				byte[] result = GzipUtil.unzip(byteary2.toByteArray());
				byte[] result = JzlibUtil.UnCompress(byteary2.toByteArray());
				for(int x:byteary2.toByteArray())
				{
					fo.write(x);	
				}
				
				byteary.close();
				byteary2.close();

			}

		} catch (SQLException e) {

			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (java.io.FileNotFoundException ex)

		{

			ex.printStackTrace();
		} catch (java.io.IOException ex) {

			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				// TODO 自动生成 catch 块
				e.printStackTrace();
			}

		}

	}

	/**
	 * 定义数据库连接的方法
	 * 
	 * @return
	 */
	private Connection createConnection() {
		Connection conn = null;
		try {

			Class.forName("com.ibm.db2.jcc.DB2Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:db2://182.97.199.92:50000/hkcb","db2inst1", "1234");

		} catch (SQLException ex1) {
			ex1.printStackTrace();

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return conn;
	}

}// 结束.

