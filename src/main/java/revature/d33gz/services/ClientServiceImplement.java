package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.ClientDAO;
import revature.d33gz.entity.Client;

public class ClientServiceImplement implements ClientService {
	private ClientDAO cdao;
	
	//Constructor
	public ClientServiceImplement(ClientDAO clientDAO) {
		this.cdao = clientDAO;
	}
	
	//Create
	public boolean addClient(Client client) {
		boolean yayOrNay = this.cdao.createClient(client);
		return yayOrNay;
	}
	
	//Read
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> clients = this.cdao.getAllClients();
		return clients;
	}
	public Client getOneClient(int id) {
		Client client = this.cdao.getOneClient(id);
		return client;
	}

	//Update
	public Client updateClient(Client client, int id) {
		Client returnClient;
		Client checkingClient = this.cdao.getOneClient(id);
		if (checkingClient.getId() == 0) {
			returnClient = checkingClient;
		} else {
			returnClient = this.cdao.updateClient(client, id);;
		}
		return returnClient;
	}
	
	//Delete
	public boolean deleteClient(int id) {
		boolean yayOrNay;
		Client checkingClient = this.cdao.getOneClient(id);
		System.out.println(checkingClient);
		if (checkingClient.getId() == 0) {
			yayOrNay = false;
		} else {
			yayOrNay = this.cdao.deleteClient(id);
		}
		return yayOrNay;
	}
}