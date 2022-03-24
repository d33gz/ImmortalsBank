package revature.d33gz.dao;

import java.util.ArrayList;

import revature.d33gz.entity.Account;

public interface AccountDAO {
	//C
	Account createAccount(Account account, int id);
	
	//R
	ArrayList<Account> getAllAccounts(int id);
	Account getOneAccount(int id);
	ArrayList<Account> getAccountsWithBalance(int less, int more);
}
