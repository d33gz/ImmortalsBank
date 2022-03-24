package revature.d33gz.services;

import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.entity.Account;

public class AccountServiceImplement implements AccountService {
	private AccountDAO adao;
	
	public AccountServiceImplement(AccountDAO accountDAO) {
		this.adao = accountDAO;
	}
	
	public Account addAccount(Account account, int id) {
		this.adao.createAccount(account, id);
		return account;
	}
	
	public ArrayList<Account> getAllAccounts() {
		ArrayList<Account> accounts = this.adao.getAllAccounts();
		return accounts;
	}
	
	public Account getOneAccount(int id) {
		Account account = this.adao.getOneAccount(id);
		return account;
	}
}
