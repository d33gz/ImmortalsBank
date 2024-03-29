package revature.d33gz.dao;

import java.util.ArrayList;

import revature.d33gz.entity.Client;

public interface ClientDAO {
	//C
	boolean createClient(Client client);
	
	//R
	ArrayList<Client> getAllClients();
	Client getOneClient(int id);
	
	//U
	Client updateClient(Client client, int id);
	
	//D
	boolean deleteClient(int id);
}