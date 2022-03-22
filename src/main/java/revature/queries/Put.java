package revature.queries;
import java.sql.Connection;
import revature.connection.ConnectionUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Put {
	public static String updateClient(int id, String update) {
		//Our Query
		String updateName = "UPDATE client SET client_name = ? WHERE client_id = ?";

		//A Try-with-Resources Block for Handling our Query
		try(Connection conn = ConnectionUtils.createConnection()){
			PreparedStatement pstmt = conn.prepareStatement(updateName);
			pstmt.setInt(2, id);
			pstmt.setString(1, update);
			pstmt.executeQuery();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//return null;
		}
		return null;
	}
}
