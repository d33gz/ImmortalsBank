package revature.d33gz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import revature.d33gz.entity.Client;
import revature.d33gz.utilities.ConnectionUtils;

public class PostgresClientDAO implements ClientDAO {
	PreparedStatement ps;
	ResultSet rs;
	
	//Create
	public boolean createClient(Client client) {
		try (Connection conn = ConnectionUtils.createConnection();) {
			String newClient = "INSERT INTO client VALUES (?,?)";
			ps = conn.prepareStatement(newClient);
			//WARNING ID SHOULD BE SERIALIZED BUT RIGHT NOW WE HAVE TO SUPPLY IT
			ps.setInt(1, client.getId());
			ps.setString(2, client.getName());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Read
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
			rs.close();ps.close();
		} catch (SQLException e) {
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
			while (rs.next()) {
				int cId = rs.getInt("client_id");
				String cName = rs.getString("client_name");
				c = new Client(cId, cName);
			}
			rs.close();ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return c;
	}
	
	//Update
	public Client updateClient(Client client, int id) {
		try (Connection conn = ConnectionUtils.createConnection();) {
			String updateClientString = "UPDATE client SET client_name=? WHERE client_id=?";
			ps = conn.prepareStatement(updateClientString);
			ps.setString(1, client.getName());
			ps.setInt(2, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public void deleteClient(int id) {
		try (Connection conn = ConnectionUtils.createConnection();) {
			String deleteClient = "DELETE FROM client WHERE client_id=?";
			ps = conn.prepareStatement(deleteClient);
			ps.setInt(1, id);
			ps.execute();
//			ctx.status(200);
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
