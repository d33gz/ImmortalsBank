package revature.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection createConnection() {
		//Getting our Database Connection from the System Environment Variables
		String url = System.getenv("MY_DB_CONNECTION");
		
		//A Try Block for Connecting to our Database
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
