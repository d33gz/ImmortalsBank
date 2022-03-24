package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.entity.Account;

public interface AccountService {
	//C
	Account addAccount(Account account, int id);
	
	//R
	ArrayList<Account> getAllAccounts(int id);
	Account getOneAccount(int id);
	ArrayList<Account> getAccountsWithBalance(int less, int more);
	
}
