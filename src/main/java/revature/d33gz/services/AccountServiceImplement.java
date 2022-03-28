package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.entity.Account;

public class AccountServiceImplement implements AccountService {
	private AccountDAO adao;
	
	//Constructor
	public AccountServiceImplement(AccountDAO accountDAO) {
		this.adao = accountDAO;
	}
	
	//Create
	public boolean addAccount(Account account, int id) {
		boolean returnAccount = this.adao.createAccount(account, id);
		return returnAccount;
	}
	
	//Read
	public ArrayList<Account> getAllAccounts(int id) {
		ArrayList<Account> returnList = new ArrayList<Account>();
		return returnList;
	}
	public Account getOneAccount(int id) {
		Account account = this.adao.getOneAccount(id);
		return account;
	}
	
	//Update
	public ArrayList<Account> getAccountsWithBalance(int less, int more) {
		ArrayList<Account> accounts = this.adao.getAccountsWithBalance(less, more);
		return accounts;
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
}
