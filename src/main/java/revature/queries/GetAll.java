package revature.queries;
import java.sql.Connection;
import revature.connection.ConnectionUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAll {
	public static String gettingAll() {
		String returnString = "";
		//Our Query
		String selectAllClients = "SELECT * FROM client";

		//A Try-with-Resources Block for Handling our Query
		try(Connection conn = ConnectionUtils.createConnection()){
			PreparedStatement pstmt = conn.prepareStatement(selectAllClients);
			ResultSet rs = pstmt.executeQuery();
				while(rs.next())
					returnString += (rs.getInt(1) + " " +rs.getString(2) + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return returnString;
		}
	}
}