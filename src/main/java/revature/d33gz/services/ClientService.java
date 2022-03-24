package revature.d33gz.services;


import java.util.ArrayList;

import revature.d33gz.entity.Client;

public interface ClientService {
	//C
	Client addClient(Client client);
	
	//R
	ArrayList<Client> getAllClients();
	Client getOneClient(int id);
	
	//U
	Client updateClient(Client client, int id);
	
	//D
	void deleteClient(int id);
	
}
