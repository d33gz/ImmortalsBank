package revature.d33gz.controllers;

import revature.d33gz.entity.Client;
import revature.d33gz.services.ClientService;

import java.util.ArrayList;
import io.javalin.http.Handler;

public class ClientController {
	//Constructor
	private ClientService cserv;
	public ClientController(ClientService clientService) {
		this.cserv = clientService;
	}
	
	//Create
	public Handler addClient = (ctx) -> {
		Client client = ctx.bodyAsClass(Client.class);
		if (this.cserv.addClient(client)) {
			System.out.println("New Client succesfully added.");
			ctx.result("A warm welcome to our newest Client, " + client);
			ctx.status(201);
		} else {
			System.out.println("Something went wrong with adding a New Client.");
			ctx.result("We weren't able to add a Client at this time.");
			ctx.status(404);
		}
	};
	
	//Read
	public Handler getAllClients = (ctx) -> {
		System.out.println("So you want to get all the Clients?");
		ArrayList<Client> cList = this.cserv.getAllClients();
		ctx.json(cList);
		if (cList.size() == 0) {
			System.out.println("Doesn't seem to be any Clients...");
			ctx.result("There are no Client's in the Bank of the Immortals right now.");
			ctx.status(400);
		} else {
			System.out.println("Here are all of our Clients.");
			ctx.json(cList);
			ctx.status(200);
		}
	};
	public Handler getOneClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " coming up!");
		Client client = this.cserv.getOneClient(id);
		if (client.getId() == 0) {
			System.out.println("Can't find a Client with ID# " + id);
			ctx.result("Doesn't look like we have a Client with that ID here.");
			ctx.status(404);
		} else {
			System.out.println("Client found!");
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
		if (updatedClient.getId() == 0) {
			System.out.println("Actually, doesn't look like we have a Client ID# " + id);
			ctx.result("Doesn't look like we have a Client with that ID here.");
			ctx.status(404);
		} else {
			System.out.println("Client has been updated.");
			ctx.result("The Client has been updated.\nHere they are: ID#: " + id + " Name: " + updatedClient.getName());
			ctx.status(200);
		}
	};
	
	//Delete
	public Handler deleteClient = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("It seems that Client #ID " + id + " wants to leave our bank.");
		if (this.cserv.deleteClient(id)) {
			System.out.println("Client ID# " + id + " has been deleted.");
			ctx.result("Goodbye, we enjoyed being your bank while it lasted!");
			ctx.status(205);
		} else {
			System.out.println("There is no Client with ID# "+ id);
			ctx.result("There doesn't actually appear to be a Client with that ID here...");
			ctx.status(404);
		}
	};
}
