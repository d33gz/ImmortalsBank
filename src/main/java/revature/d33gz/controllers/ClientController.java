package revature.d33gz.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import revature.d33gz.entity.Client;
import revature.d33gz.services.ClientService;

import java.util.ArrayList;
import io.javalin.http.Handler;

public class ClientController {
	static PreparedStatement ps;
	static ResultSet rs;
	static Statement stmt;
	
	private ClientService cserv;
	public ClientController(ClientService clientService) {
		this.cserv = clientService;
	}
	
	public Handler addClient = (ctx) -> {
		Client client = ctx.bodyAsClass(Client.class);
		if (this.cserv.addClient(client)) {
			ctx.status(201);
		} else {
			ctx.status(404);
		}
		System.out.println("A warm welcome to our newest Client, " + client);

	};
	public Handler getAllClients = (ctx) -> {
		System.out.println("So you want to get all the Clients?");
		ArrayList<Client> cList = this.cserv.getAllClients();
		ctx.json(cList);
		if (cList.size() == 0) {
			ctx.result("There are no Client's in the Bank of the Immortals right now.");
			ctx.status(400);
		} else {
			ctx.json(cList);
			ctx.status(200);
		}
	};
	public Handler getOneClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " coming up!");
		Client client = this.cserv.getOneClient(id);
		System.out.println(client);
		if (client == null) {
			ctx.result("Doesn't look like we have a Client with that ID here.");
			ctx.status(404);
		} else {
			ctx.json(client);
			ctx.status(200);
		}
	};
	
	//Update
	public Handler updateClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " wants to change their name.");
		Client clientToUpdate = ctx.bodyAsClass(Client.class);
		Client updatedClient = this.cserv.updateClient(clientToUpdate, id);
		if (updatedClient == null) {
			ctx.result("Doesn't look like we have a Client with that ID here.");
			ctx.status(404);
		} else {
			ctx.result("The Client has been updated.\nHere they are: ID#: " + id + " Name: " + updatedClient.getName());
			ctx.status(200);
		}
	};
	
	
	public Handler deleteClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("It seems that Client #ID " + id + " wants to leave our bank.");
		this.cserv.deleteClient(id);
	};
}
