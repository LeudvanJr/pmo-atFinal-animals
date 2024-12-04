package persistence;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ResourceManager {
	private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String JDBC_URL    = "jdbc:mysql://localhost:3306/animalsdb";
	private static String JDBC_USER   = "YOUR_USER";
	private static String JDBC_PASS   = "YOUR_PASS";
	private static Driver driver = null;
	
	public static synchronized Connection getConnection() throws SQLException {
		if (driver == null) {
			try {
				Class<?> jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.getDeclaredConstructor().newInstance();
				DriverManager.registerDriver(driver);
			}catch(Exception e) {
				System.out.println("Failed to initialize JDBC driver");
				e.printStackTrace();
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
