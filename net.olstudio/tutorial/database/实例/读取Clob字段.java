import java.sql.*;
import java.io.*;
import weblogic.jdbc.common.*;
import weblogic.jdbc.common.*;
import java.util.Properties;

public class LargeObject {

	public static void main(String argv[]) {
		String user = "scott";//����oracle���ݿ����õ��û���
		String password = "tiger";//�û�scott������
		String server = "myoracle";//�ڱ��ؽ�����oracle���ݿ����������

		java.sql.Blob myBlob = null;
		java.sql.Clob myClob = null;
		java.sql.Connection conn = null;

		Properties props = new Properties();
		props.put("user", user);
		props.put("password", password);
		props.put("server", server);

		try {
			//����JDBC��������JDriver for oracle
			Driver myDriver = (Driver) Class.forName("weblogic.jdbc.oci.Driver").newInstance();
			//�������ݿ�����
			conn = myDriver.connect("jdbc:weblogic:oracle", props);
			// ��ʹ��BLOB��CLOBʱ��Ҫ�������ӵ������Զ��ύ����Ϊfalse
			conn.setAutoCommit(false);

			// ============== ��������BLOB��CLOB�����ֶε����ݱ�lobtest==================
			try {
				// �����lobtest�����ڣ������ñ�
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

			// ============== ��ʼ�����е�BLOB��CLOB�ֶ� ==================
			Statement stmt = conn.createStatement();
			System.out.println("\nInserting row with blank blob and clob columns...");
			stmt.execute("insert into lobtest values (44,EMPTY_BLOB(),EMPTY_CLOB())");
			System.out.println("Row has been inserted.");

			// ============== ����BLOB����myBLOB ======================
			stmt.execute("select * from lobtest where id=44");
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				myBlob = rs.getBlob("blobcol");
			}

			// ����һ��byte������b���ڸ������д��һЩ����
			System.out.println("\nCreating the following byte array:");
			int STREAM_SIZE = 10;
			byte[] b = new byte[STREAM_SIZE];
			for (int i = 0; i < STREAM_SIZE; i++) {
				b = (byte) (40 + (i % 20)); // range 40-60
				System.out.println("byte[" + i + "] = " + b);
			}

			// ������b��ŵ��������У�Ȼ��������������д��myBlob�������BLOB����
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

			//  �����浽BLOB���е�����ȡ����Ȼ���ŵ�byte����r�У����r��������ʾ����
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

			//���´�����ʾ����CLOB�Ĳ���
			// ���Ƚ���һ���ַ��������ַ����е��ַ��������CLOB����
			String ss = "abcdefghijklmnopqrstuvwxyz";
			System.out
					.println("\nCreated the following string to be stored as a clob:\n"
							+ ss);

			//��ss�е��ַ�����CLOB����
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

			// ��CLOB���ж������ݲ���ʾ����
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

			// ɾ�������ı�lobtest
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