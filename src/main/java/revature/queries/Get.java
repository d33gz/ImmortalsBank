package revature.queries;
import java.sql.Connection;
import revature.connection.ConnectionUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Get {
	public static String getClient(int id) {
		String returnString = "";
		//Our Query
		String selectOneClient = "SELECT * FROM client WHERE client_id = ?";

		//A Try-with-Resources Block for Handling our Query
		try(Connection conn = ConnectionUtils.createConnection()){
			PreparedStatement pstmt = conn.prepareStatement(selectOneClient);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				returnString = rs.getInt(1) + " " +rs.getString(2);
			rs.close();pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return returnString;
		}
	}
}
