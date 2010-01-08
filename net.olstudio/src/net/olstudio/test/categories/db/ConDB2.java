package net.olstudio.test.categories.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConDB2 {

	Connection con;

	Statement st;

	ResultSet rs;

	public ConDB2() {

		try {

			String name = "com.ibm.db2.jcc.DB2Driver";
			String url = "jdbc:db2://localhost:50001/camadb"; // 跟数据库名称

			Class.forName(name);
			con = DriverManager.getConnection(url, "db2admin", "db2admin");

			System.out.println(" 连接成功 !");
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ConDB2 cd = new ConDB2();
	}
}