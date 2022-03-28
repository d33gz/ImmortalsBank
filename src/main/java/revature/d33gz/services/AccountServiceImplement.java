package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.dao.ClientDAO;
import revature.d33gz.entity.Account;
import revature.d33gz.entity.Client;

public class AccountServiceImplement implements AccountService {
	private AccountDAO adao;
	private ClientDAO cdao;
	
	//Constructor
	public AccountServiceImplement(AccountDAO accountDAO, ClientDAO clientDAO) {
		this.adao = accountDAO;
		this.cdao = clientDAO;
	}
	
	//Create
	public boolean addAccount(Account account, int id) {
		boolean returnAccount;
		Client checkingClient = this.cdao.getOneClient(id);
		if (checkingClient.getId() == 0) {
			returnAccount = false;
		} else {
			returnAccount = this.adao.createAccount(account, id);
		}
		return returnAccount;
	}
	
	//Read
	public ArrayList<Account> getAllAccountsForClient(int id) {
		ArrayList<Account> returnList = new ArrayList<Account>();
		Client checkingClient = this.cdao.getOneClient(id);
		if (checkingClient.getId() == 0) {
			Account clientFailure = new Account(-1, -1, "noName", -1);
			returnList.add(clientFailure);
			return returnList;
		} else {
			returnList = this.adao.getAllAccountsForClient(id);
		}
		if (returnList.size() == 0) {
			Account accountFailure = new Account(0, 0, "noAccounts", 0);
			returnList.add(accountFailure);
		}
		return returnList;
	}
	public Account getOneAccount(int id) {
		Account account = this.adao.getOneAccount(id);
		return account;
	}
	public ArrayList<Account> getAccountsWithBalance(int less, int more) {
		ArrayList<Account> accounts = this.adao.getAccountsWithBalance(less, more);
		return accounts;
	}
	
	//Update
	public Account updateAccount(Account account, int id) {
		Account returnAccount;
		Account checkingAccount = this.adao.getOneAccount(id);
		if (checkingAccount.getId() == 0) {
			returnAccount = checkingAccount;
		} else {
			returnAccount = this.adao.updateAccount(account, id);
		}
		return returnAccount;
	}
	public void deposit(Account incomingAccount, int id) {
		Account currentAccount = this.adao.getOneAccount(id);
		int currentBalance = currentAccount.getBalance();
		System.out.println("Their current Balance is " + currentBalance);
		int newBalance = currentBalance + incomingAccount.getBalance();
		System.out.println("Their new Balance is " + newBalance);
		this.adao.deposit(newBalance, id);
	}
	public void withdraw(Account incomingAccount, int id) {
		Account currentAccount = this.adao.getOneAccount(id);
		int currentBalance = currentAccount.getBalance();
		System.out.println("Their current Balance is " + currentBalance);
		int newBalance = currentBalance - incomingAccount.getBalance();
		System.out.println("Their new Balance is " + newBalance);
		if (newBalance < 0)	System.out.println("No... That's impossible!!");
		else this.adao.withdraw(newBalance, id);
	}
	
	//Delete
	public boolean deleteAccount(int id) {
		return this.adao.deleteAccount(id);
	}
}
