package revature.d33gz.dao;

import java.util.ArrayList;

import revature.d33gz.entity.Account;

public interface AccountDAO {
	//C
	boolean createAccount(Account account, int id);
	
	//R
	ArrayList<Account> getAllAccountsForClient(int id);
	Account getOneAccount(int id);
	ArrayList<Account> getAccountsWithBalance(int less, int more);
	
	//U
	Account updateAccountName(Account account, int id);
	void updateAccountBalance(int newBalance, int id);
	
	//D
	boolean deleteAccount(int id);
}
