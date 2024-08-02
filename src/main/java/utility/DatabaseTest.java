package utility;

import java.sql.Connection;
import java.sql.SQLException;
/* testing if the connection to the database is successful or not
 * debug by running this as java application to see the messages
 */
public class DatabaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	       Connection connection = null;
	        try {
	            connection = DatabaseUtility.getConnection();
	            if (connection != null) {
	                System.out.println("Connection established successfully.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Failed to connect to the database.");
	            e.printStackTrace();
	        }
	    }
	}

