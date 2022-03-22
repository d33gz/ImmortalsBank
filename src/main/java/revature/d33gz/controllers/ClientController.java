package revature.d33gz.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import revature.bank.entity.Client;
import java.util.ArrayList;
import io.javalin.http.Handler;
import revature.connection.ConnectionUtils;

public class ClientController {
	static PreparedStatement ps;
	static ResultSet rs;
	
	//Figure out later. Should be able to avoid Static with this
	/*
	* private ClientService cserv;
	* public clientController(ClientService clientService) {
	* 	this.cserv = ClientService;
	* }
	*/
	public static Handler addClient = (ctx) -> {
		Client client = ctx.bodyAsClass(Client.class);
		String newClient = "INSERT INTO client VALUES (?,?)";
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(newClient);
		//WARNING ID SHOULD BE SERIALIZED BUT RIGHT NOW WE HAVE TO SUPPLY IT
		ps.setInt(1, client.getId());
		ps.setString(2, client.getName());
		ps.execute();
		ctx.status(201);
		ps.close();
	};
	public static Handler getAllClients = (ctx) -> {
		String selectAllClients = "SELECT * FROM client";
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(selectAllClients);
		rs = ps.executeQuery();
		ArrayList<Client> cList = new ArrayList<Client>();
		Client c;
		while (rs.next()) {
			int id = rs.getInt("client_id");
			String name = rs.getString("client_name");
			c = new Client(id, name);
			cList.add(c);
		}
		ctx.json(cList);
		rs.close();ps.close();
	};
	public static Handler getOneClient = (ctx) -> {
		String selectOneClient = "SELECT * FROM client WHERE client_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(selectOneClient);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		Client c;
		while (rs.next()) {
			int cId = rs.getInt("client_id");
			String cName = rs.getString("client_name");
			c = new Client(cId, cName);
			ctx.json(c);
		}
		rs.close();ps.close();
	};
	public static Handler updateClient = (ctx) -> {
		String updateClient = "UPDATE client SET client_name=? WHERE client_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client clientOne = ctx.bodyAsClass(Client.class);
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(updateClient);
		ps.setString(1, clientOne.getName());
		ps.setInt(2, id);
		ps.execute();
		ctx.status(200);
		ps.close();
	};
	public static Handler deleteClient = (ctx) -> {
		String deleteClient = "DELETE FROM client WHERE client_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(deleteClient);
		ps.setInt(1, id);
		ps.execute();
		ctx.status(200);
		ps.close();
	};
}
