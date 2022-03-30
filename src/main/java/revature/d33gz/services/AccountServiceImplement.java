package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.dao.ClientDAO;
import revature.d33gz.entity.Account;
import revature.d33gz.entity.Client;
import revature.d33gz.utilities.RandomIdGenerator;

public class AccountServiceImplement implements AccountService {
	private AccountDAO adao;
	private ClientDAO cdao;
	private RandomIdGenerator gen;
	
	//Constructor
	public AccountServiceImplement(AccountDAO accountDAO, ClientDAO clientDAO, RandomIdGenerator gen) {
		this.adao = accountDAO;
		this.cdao = clientDAO;
		this.gen = gen;
	}
	
	//Create
	public boolean addAccount(Account account, int id) {
		boolean returnAccount;
		Client checkingClient = this.cdao.getOneClient(id);
		if (checkingClient.getId() == 0) {
			returnAccount = false;
		} else {
			int randoId = gen.randomIdGenerator("Account");
			System.out.println("The assigned ID will be " + randoId);
			while (randoId == this.adao.getOneAccount(randoId).getId()) {
				System.out.println("Oops! That Account ID already exists. Let's try another one.");
				randoId = gen.randomIdGenerator("Account");
				System.out.println("Here's the new one " + randoId);
			}
			account.setId(randoId);
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
	public Account updateAccountName(Account account, int id) {
		Account checkingAccount = this.adao.getOneAccount(id);
		if (!(checkingAccount.getId() == 0)) {
			this.adao.updateAccountName(account, id);
		}
		account.setId(id);
		return account;
	}
	public void deposit(Account incomingAccount, int id) {
		Account currentAccount = this.adao.getOneAccount(id);
		if (!(currentAccount.getId() == 0)) {
			int currentBalance = currentAccount.getBalance();
			System.out.println("Their current Balance is " + currentBalance);
			int newBalance = currentBalance + incomingAccount.getBalance();
			System.out.println("Their new Balance is " + newBalance);
			this.adao.updateAccountBalance(newBalance, id);
		}
	}
	public boolean withdraw(Account incomingAccount, int id) {
		boolean isGood = true;
		Account currentAccount = this.adao.getOneAccount(id);
		if (!(currentAccount.getId() == 0)) {
			int currentBalance = currentAccount.getBalance();
			System.out.println("Their current Balance is " + currentBalance);
			int newBalance = currentBalance - incomingAccount.getBalance();
			System.out.println("Their new Balance is " + newBalance);
			if (newBalance < 0)	{
				System.out.println("No... That's impossible!!");
				isGood = false;
			} else {
				this.adao.updateAccountBalance(newBalance, id);
			}
		}
		return isGood;
	}
	
	//Delete
	public boolean deleteAccount(int id) {
		Account checkingAccount = this.adao.getOneAccount(id);
		if (!(checkingAccount.getId() == 0))
			return this.adao.deleteAccount(id);
		else return false;
	}
}
