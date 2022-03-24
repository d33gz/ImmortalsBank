package revature.d33gz.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import revature.d33gz.connection.ConnectionUtils;
import revature.d33gz.entity.Client;
import revature.d33gz.services.ClientService;

import java.util.ArrayList;
import io.javalin.http.Handler;

public class ClientController {
	static PreparedStatement ps;
	static ResultSet rs;
	//TEMP
	static Statement stmt;
	//TEMP
	//Figure out later. Should be able to avoid Static with this
	
	private ClientService cserv;
	public ClientController(ClientService clientService) {
		this.cserv = clientService;
	}
	
	public Handler addClient = (ctx) -> {
		Client client = ctx.bodyAsClass(Client.class);
		System.out.println("A warm welcome to our newest Client, " + client);
		client = this.cserv.addClient(client);
//		try {
//			ps.execute();
//		} catch (SQLException e) {
//			ctx.result("That ID is a Duplicate of another in the table");
//			ctx.status(400);
//		}
//		ctx.status(201);

	};
	public Handler getAllClients = (ctx) -> {
		System.out.println("So you want to get all the Clients?");
		ArrayList<Client> cList = this.cserv.getAllClients();
		ctx.json(cList);
//		if (cList.size() == 0) {
//			ctx.result("There are no Client's in the Bank of the Immortals right now.");
//			ctx.status(400);
//		} else {
//			ctx.json(cList);
//			ctx.status(200);
//		}
	};
	public Handler getOneClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID#" + id + " coming up!");
		Client client = this.cserv.getOneClient(id);
		ctx.json(client);
//		String selectOneClient = "SELECT * FROM client WHERE client_id=?";
//		ps = conn.prepareStatement(selectOneClient);
//		ps.setInt(1, id);
//		rs = ps.executeQuery();
//		Client c;
//		if (rs.getFetchSize() == 0) {
//			ctx.result("Doesn't look like we have a Client with that ID here.");
//			ctx.status(404);
//		}
//		while (rs.next()) {
//			int cId = rs.getInt("client_id");
//			String cName = rs.getString("client_name");
//			c = new Client(cId, cName);
//			ctx.json(c);
//			ctx.status(200);
//		}
//		rs.close();ps.close();
	};
	public static Handler updateClient = (ctx) -> {
		String updateClient = "UPDATE client SET client_name=? WHERE client_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client clientOne = ctx.bodyAsClass(Client.class);
		Connection conn = ConnectionUtils.createConnection();
//		// v Convert this into a Callable Statement v
//		stmt = conn.createStatement();
//		rs = stmt.executeQuery("SELECT * FROM client WHERE client_id="+id);
//		if (rs.getFetchSize() == 0) {
//			ctx.result("Doesn't look like we have a Client with that ID here.");
//			ctx.status(404);
//		} else {
//		// ^ ^ ^
			ps = conn.prepareStatement(updateClient);
			ps.setString(1, clientOne.getName());
			ps.setInt(2, id);
			ps.execute();
			ctx.result("The Client has been updated.");
			ctx.status(200);
			ps.close();
		//}
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
