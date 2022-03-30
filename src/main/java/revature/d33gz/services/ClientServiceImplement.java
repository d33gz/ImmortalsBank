package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.dao.ClientDAO;
import revature.d33gz.entity.Account;
import revature.d33gz.entity.Client;
import revature.d33gz.utilities.RandomIdGenerator;

public class ClientServiceImplement implements ClientService {
	private AccountDAO adao;
	private ClientDAO cdao;
	private RandomIdGenerator gen;
	
	//Constructor
	public ClientServiceImplement(AccountDAO accountDAO, ClientDAO clientDAO, RandomIdGenerator gen) {
		this.adao = accountDAO;
		this.cdao = clientDAO;
		this.gen = gen;
	}
	
	//Create
	public boolean addClient(Client client) {
		int randoId = gen.randomIdGenerator("Client");
		System.out.println("The assigned ID will be " + randoId);
		while (randoId == this.cdao.getOneClient(randoId).getId()) {
			System.out.println("Oops! That Client ID already exists. Let's try another one.");
			randoId = gen.randomIdGenerator("Client");
			System.out.println("Here's the new one " + randoId);
		}
		client.setId(randoId);
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
		Client checkingClient = this.cdao.getOneClient(id);
		if (!(checkingClient.getId() == 0)) {
			this.cdao.updateClient(client, id);;
		}
		return client;
	}
	
	//Delete
	public boolean deleteClient(int id) {
		boolean yayOrNay;
		Client checkingClient = this.cdao.getOneClient(id);
		if (checkingClient.getId() == 0) {
			System.out.println("Expect they don't exist...");
			yayOrNay = false;
		} else {
			ArrayList<Account> accountList = new ArrayList<Account>();
			accountList = this.adao.getAllAccountsForClient(id);
			System.out.println("How many accounts they got " + accountList.size());
			while (accountList.size() > 0) {
				int accountCount = 0;
				for (Account a: accountList) {
					System.out.println("Deleting Account.");
					accountCount++;
					this.adao.deleteAccount(a.getId());
					accountList = this.adao.getAllAccountsForClient(id);
					System.out.println("Accounts left " +accountList.size());
				}
				System.out.println(accountCount + " accounts deleted.");
			}
			yayOrNay = this.cdao.deleteClient(id);
		}
		return yayOrNay;
	}
}