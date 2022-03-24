package revature.d33gz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> cList = new ArrayList<Client>();
		try (Connection conn = ConnectionUtils.createConnection();) {
			String selectAllClients = "SELECT * FROM client";
			ps = conn.prepareStatement(selectAllClients);
			rs = ps.executeQuery();
			Client c;
			while (rs.next()) {
				int id = rs.getInt("client_id");
				String name = rs.getString("client_name");
				c = new Client(id, name);
				cList.add(c);
			}
//			if (cList.size() == 0) {
//				ctx.result("There are no Client's in the Bank of the Immortals right now.");
//				ctx.status(400);
//			} else {
//				ctx.json(cList);
//				ctx.status(200);
//			}

			rs.close();ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cList;
	}
	
	public Client getOneClient(int id) {
		Client c = new Client();
		try (Connection conn = ConnectionUtils.createConnection();) {
			String selectOneClient = "SELECT * FROM client WHERE client_id=?";
			ps = conn.prepareStatement(selectOneClient);
			ps.setInt(1, id);
			rs = ps.executeQuery();
//			if (rs.getFetchSize() == 0) {
//				ctx.result("Doesn't look like we have a Client with that ID here.");
//				ctx.status(404);
//			}
			while (rs.next()) {
				int cId = rs.getInt("client_id");
				String cName = rs.getString("client_name");
				c = new Client(cId, cName);
//				ctx.json(c);
//				ctx.status(200);
			}
			rs.close();ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
}
