package utility;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/* establish connection
 * 
 */

public class DatabaseUtility {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school_db";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD= "heGJ59Ax";
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);
		
		return conn;
	}
}
