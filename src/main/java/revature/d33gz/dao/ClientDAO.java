package revature.d33gz.dao;


import java.util.ArrayList;

import revature.d33gz.entity.Client;

public interface ClientDAO {
	//C
	Client createClient(Client client);
	
	//R
	ArrayList<Client> getAllClients();
	Client getOneClient(int id);
	
}