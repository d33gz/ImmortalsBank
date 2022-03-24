package revature.d33gz.dao;

import java.util.ArrayList;

import revature.d33gz.entity.Account;

public interface AccountDAO {
	//C
	Account createAccount(Account account, int id);
	
	//R
	ArrayList<Account> getAllAccounts();
	Account getOneAccount(int id);
}
