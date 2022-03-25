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
	public Client addClient(Client client) {
		this.cdao.createClient(client);
		return client;
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
		this.cdao.updateClient(client, id);
		return client;
	}
	
	//Delete
	public void deleteClient(int id) {
		this.cdao.deleteClient(id);
	}
}