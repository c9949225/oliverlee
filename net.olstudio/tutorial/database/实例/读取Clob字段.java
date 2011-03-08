import java.sql.*;
import java.io.*;
import weblogic.jdbc.common.*;
import weblogic.jdbc.common.*;
import java.util.Properties;

public class LargeObject {

	public static void main(String argv[]) {
		String user = "scott";//连接oracle数据库所用的用户名
		String password = "tiger";//用户scott的密码
		String server = "myoracle";//在本地建立的oracle数据库网络服务名

		java.sql.Blob myBlob = null;
		java.sql.Clob myClob = null;
		java.sql.Connection conn = null;

		Properties props = new Properties();
		props.put("user", user);
		props.put("password", password);
		props.put("server", server);

		try {
			//加载JDBC驱动程序：JDriver for oracle
			Driver myDriver = (Driver) Class.forName("weblogic.jdbc.oci.Driver").newInstance();
			//建立数据库连接
			conn = myDriver.connect("jdbc:weblogic:oracle", props);
			// 在使用BLOB、CLOB时需要设置连接的事务自动提交属性为false
			conn.setAutoCommit(false);

			// ============== 建立带有BLOB和CLOB类型字段的数据表lobtest==================
			try {
				// 如果表lobtest不存在，建立该表
				Statement crstmt = conn.createStatement();
				System.out.println("\nCreating table with Blobs and Clobs...");
				crstmt.execute("create table lobtest (id int, blobcol Blob, clobcol Clob)");
				crstmt.close();
			} catch (Exception e) {
				System.out.println("Exception: " + e);
				System.out.println("Table already exists. Dropping it and re-creating...");
				Statement crstmt2 = conn.createStatement();
				crstmt2.execute("drop table lobtest");
				crstmt2.execute("create table lobtest (id int, blobcol Blob, clobcol Clob)");
				crstmt2.close();
			}
			System.out.println("table lobtest created.");

			// ============== 初始化表中的BLOB和CLOB字段 ==================
			Statement stmt = conn.createStatement();
			System.out.println("\nInserting row with blank blob and clob columns...");
			stmt.execute("insert into lobtest values (44,EMPTY_BLOB(),EMPTY_CLOB())");
			System.out.println("Row has been inserted.");

			// ============== 生成BLOB对象myBLOB ======================
			stmt.execute("select * from lobtest where id=44");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				myBlob = rs.getBlob("blobcol");
			}

			// 建立一个byte型数组b，在该数组中存放一些数据
			System.out.println("\nCreating the following byte array:");
			int STREAM_SIZE = 10;
			byte[] b = new byte[STREAM_SIZE];
			for (int i = 0; i < STREAM_SIZE; i++) {
				b = (byte) (40 + (i % 20)); // range 40-60
				System.out.println("byte[" + i + "] = " + b);
			}

			// 将数组b存放到数据流中，然后将数据流的数据写入myBlob所代表的BLOB列中
			System.out.println("\nWriting the byte array to a stream"
					+ " and storing it in the table as a blob...");
			InputStream is = new ByteArrayInputStream(b);
			java.io.OutputStream os = ((weblogic.jdbc.common.OracleBlob) myBlob)
					.getBinaryOutputStream();
			byte[] inBytes = new byte[STREAM_SIZE];
			int numBytes = is.read(inBytes);
			while (numBytes > 0) {
				os.write(inBytes, 0, numBytes);
				numBytes = is.read(inBytes);
			}
			os.flush();

			//  将保存到BLOB列中的数据取出，然后存放到byte数组r中，最后将r中数据显示出来
			System.out
					.println("\nReading the blob back from the table and displaying:");
			Statement readblob = conn.createStatement();
			readblob.execute("select * from lobtest where id=44");
			ResultSet rsreadblob = readblob.getResultSet();
			byte[] r = new byte[STREAM_SIZE];
			while (rsreadblob.next()) {
				Blob myReadBlob = rsreadblob.getBlob("blobcol");
				java.io.InputStream readis = myReadBlob.getBinaryStream();
				for (int i = 0; i < STREAM_SIZE; i++) {
					r = (byte) readis.read();
					System.out.println("output [" + i + "] = " + r);
				}
			}

			//以下代码演示的是CLOB的操作
			// 首先建立一个字符串，该字符串中的字符将会存入CLOB列中
			String ss = "abcdefghijklmnopqrstuvwxyz";
			System.out
					.println("\nCreated the following string to be stored as a clob:\n"
							+ ss);

			//将ss中的字符存入CLOB列中
			stmt.execute("select * from lobtest where id=44");
			ResultSet crs = stmt.getResultSet();
			while (crs.next()) {
				myClob = crs.getClob("clobcol");

				java.io.OutputStream osss = ((weblogic.jdbc.common.OracleClob) myClob)
						.getAsciiOutputStream();
				byte[] bss = ss.getBytes("ASCII");
				osss.write(bss);
				osss.flush();
			}
			conn.commit();

			// 从CLOB列中读出数据并显示出来
			System.out
					.println("\nReading the clob back from the table and displaying:");
			Statement readclob = conn.createStatement();
			readclob.execute("select * from lobtest where id=44");
			ResultSet rsreadclob = readclob.getResultSet();

			while (rsreadclob.next()) {
				Clob myReadClob = rsreadclob.getClob("clobcol");
				java.io.InputStream readClobis = myReadClob.getAsciiStream();
				char[] c = new char[26];
				for (int i = 0; i < 26; i++) {
					c = (char) readClobis.read();
					System.out.println("output [" + i + "] = " + c);
				}
			}

			// 删除建立的表lobtest
			System.out.println("\nDropping table...");
			Statement dropstmt = conn.createStatement();
			dropstmt.execute("drop table lobtest");
			System.out.println("Table dropped.");

		} catch (Exception e) {
			System.out.println("Exception was thrown: " + e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println("SQLException was thrown: "
						+ sqle.getMessage());
			}
		}
	}

}