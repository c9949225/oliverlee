package net.olstudio.test.categories.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StartHsqlDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String params = "port=9001;silent=true;trace=false;database.0=/db/test";
		org.hsqldb.Server server = new org.hsqldb.Server();
		server.putPropertiesFromString(params);
		server.setLogWriter(null);
		server.setErrWriter(null);
		server.start();

		//
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001",
					"sa", "");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("SHUTDOWN;");
			stmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
