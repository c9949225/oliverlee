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
 * java �ж�Blob���ݵĲ������ݿ�Ĵ�������:
 * 
 * (1)db2 => create table blobTest ( id int not null generated always as identity, image blob )
 * 
 * ����û�java,����java
 * 
 * (2) db2=>! net user java java /add java����Ȩ��
 * 
 * (3)db2 => grant select,insert,update,delete on table weihuachao.blobTest to
 * user java
 * 
 * 
 * @author weihuachao ���´�����microsoft 2003 ϵͳ,DB2 9.0�в��Գɹ�.
 * 
 */

/*// ��Ķ��忪ʼ------------------------------------------------------------

�ܽ�: JAVA ��DB2�е�BLOB����Ĳ���,��Ҫ��ʹ�����ļ���ʵ��.��BLOB��ʵ����Ҫ������:

	(1)����Ҫд����ļ�  File file=new File("c:/a.jpg");


	(2)�����ļ���д����

	  java.io.BufferedInputStream imageInput = new java.io.BufferedInputStream(
	     new java.io.FileInputStream(file));


	(3)ʹ�ú���д��:

	   preparedStatement.setBinaryStream(1, imageInput,(int) file.length());

	(4)ִ��SQL���:

	��BLOB�Ķ�ȡ��ȡ�Ĳ�����:

	(1)��ȡBlob�Ķ���:  Blob blob= (Blob) rs.getBlob(1);

	(2)��Blob�Ķ���ת��Ϊ��:

	   java.io.InputStream inputStream =blob.getBinaryStream();
	(3)����Ҫд����ļ�
	    File fileOutput = new File("c:/backa.jpg");
	(4)�ļ���д�����Ķ���
	    FileOutputStream fo = new FileOutputStream(fileOutput);

	(5)д���ļ�(����д�뼼��,�Ͳ��ི��)
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

		// Blob����Ĳ���ķ���:
		try {

			// �����������.
			java.sql.PreparedStatement preparedStatement = conn
					.prepareStatement("insert into blobTest(image)values(?)");

			// �����ļ�����:

			File file = new File("c:/a.jpg");

			// ����������:
			java.io.BufferedInputStream imageInput = new java.io.BufferedInputStream(
					new java.io.FileInputStream(file));

			java.io.ByteArrayOutputStream byteary = new ByteArrayOutputStream();
			
			int c2;
			while ((c2 = imageInput.read()) != -1)
				byteary.write(c2);
			
//			byte[] source = GzipUtil.zip(byteary.toByteArray());
			byte[] source = JzlibUtil.Compress(byteary.toByteArray());
//			byte[] source =  byteary.toByteArray();
			
			// ������ֵ:
			preparedStatement.setBinaryStream(1, new ByteArrayInputStream(source),
					source.length);

			// ִ�����
			preparedStatement.executeUpdate();

			// ------------------------------------------------------------------
			// Blob�Ķ�ȡ����:

			java.sql.Statement st = conn.createStatement();

			java.sql.ResultSet rs = st
					.executeQuery("select image from blobTest");

			while (rs.next()) {
				// ��ȡBlob����
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

			// TODO �Զ����� catch ��
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

				// TODO �Զ����� catch ��
				e.printStackTrace();
			}

		}

	}

	/**
	 * �������ݿ����ӵķ���
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

}// ����.

