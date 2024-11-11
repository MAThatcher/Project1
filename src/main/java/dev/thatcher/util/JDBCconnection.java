package dev.thatcher.util;

import java.util.logging.Logger;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
//this is a test
//this is a test2
public class JDBCconnection {
	Logger logger = Logger.getLogger(getClass().getName());

	private static Connection conn = null;

	public static void main(String[] args) {
		Connection con1 = getConnection();
		System.out.println(con1);
	}

	public static Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCconnection.class.getClassLoader().getResource("connection.properties").getFile());
				props.load(input);
				conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
				return conn;
			} else {
				return conn;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; ) { // Noncompliant, i not updated in increment clause
			// ...
			i++;
		  }
		  logger.info("My Message");  // Compliant, output via logger
		  return null;
	}
}
