package revature.d33gz.services;

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
}
