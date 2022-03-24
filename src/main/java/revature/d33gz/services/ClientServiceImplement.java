package revature.d33gz.services;


import java.util.ArrayList;

import revature.d33gz.dao.ClientDAO;
import revature.d33gz.entity.Client;

public class ClientServiceImplement implements ClientService {
	private ClientDAO cdao;
	
	public ClientServiceImplement(ClientDAO clientDAO) {
		this.cdao = clientDAO;
	}

	public Client addClient(Client client) {
		this.cdao.createClient(client);
		return client;
	}
	
	public ArrayList<Client> getAllClients() {
		ArrayList<Client> clients = this.cdao.getAllClients();
		return clients;
	}
	
	public Client getOneClient(int id) {
		Client client = this.cdao.getOneClient(id);
		return client;
	}
}
