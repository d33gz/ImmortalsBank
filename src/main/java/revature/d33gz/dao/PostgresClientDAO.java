package revature.d33gz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import revature.d33gz.connection.ConnectionUtils;
import revature.d33gz.entity.Client;

public class PostgresClientDAO implements ClientDAO {
	PreparedStatement ps;
	ResultSet rs;
	
	public Client createClient(Client client) {
		try (Connection conn = ConnectionUtils.createConnection();) {
			String newClient = "INSERT INTO client VALUES (?,?)";
			ps = conn.prepareStatement(newClient);
			//WARNING ID SHOULD BE SERIALIZED BUT RIGHT NOW WE HAVE TO SUPPLY IT
			ps.setInt(1, client.getId());
			ps.setString(2, client.getName());
			ps.execute();
//			try {
//				ps.execute();
//			} catch (SQLException e) {
//				ctx.result("That ID is a Duplicate of another in the table");
//				ctx.status(400);
//			}
//			ctx.status(201);
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return client;
	}
}
