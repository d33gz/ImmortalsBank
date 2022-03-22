package revature.queries;
import java.sql.Connection;
import revature.connection.ConnectionUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Post {
	public static String postClient(String name) {
		String returnString = "";
		//Our Query
		String postClient = "INSERT INTO client (client_name) VALUES (?)";

		//A Try-with-Resources Block for Handling our Query
		try(Connection conn = ConnectionUtils.createConnection()){
			PreparedStatement pstmt = conn.prepareStatement(postClient);
			pstmt.setString(1, name);
			pstmt.executeQuery();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return returnString;
		}
	}
}
