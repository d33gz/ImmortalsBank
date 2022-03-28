package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.entity.Account;

public interface AccountService {
	//C
	boolean addAccount(Account account, int id);
	
	//R
	ArrayList<Account> getAllAccounts(int id);
	Account getOneAccount(int id);
	ArrayList<Account> getAccountsWithBalance(int less, int more);
	
	//U
	void deposit(Account incomingAccount, int id);
	void withdraw(Account incomingAccount, int id);
}
