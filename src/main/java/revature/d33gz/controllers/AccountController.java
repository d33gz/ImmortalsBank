package revature.d33gz.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.entity.Account;
import revature.d33gz.services.AccountService;
import revature.d33gz.utilities.ConnectionUtils;
import io.javalin.http.Handler;

public class AccountController {
	//Constructor
	private AccountService aserv;
	public AccountController(AccountService accountService) {
		this.aserv = accountService;
	}
	
	//Create
	public Handler addAccount = (ctx) -> {
		Account account = ctx.bodyAsClass(Account.class);
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("It seems that Client ID# " + id + " wishes to open up a new Account.");
		if (this.aserv.addAccount(account, id)) {
			System.out.println("A new Account has been succesfully created.");
			ctx.result("Client ID# " + id + ", Congratulations on your new account!");
			ctx.status(201);
		} else {
			System.out.println("Something went wrong with adding a new Account.");
			ctx.result("We weren't able to add an Account at this time.");
			ctx.status(404);
		}
	};
	
	//Read
	public Handler getAllAccounts = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " wants to check out all of their Accounts.");
		ArrayList<Account> aList = this.aserv.getAllAccounts(id);
		System.out.println(aList);
		if (aList.size() == 0) {
			System.out.println("Can't find a Client with ID# " + id + " or maybe an Account for them.");
			ctx.result("Hmm... doesn't seem to be a Client with that ID here... Or maybe they don't have any Accounts...");
			ctx.status(404);
		} else {
			System.out.println("Here are all the Accounts for Client ID# " + id);
			ctx.json(aList);
			ctx.status(200);
		}
	};
	public Handler getOneAccount = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("We are looking for an Account with ID# " + id);
		Account account = this.aserv.getOneAccount(id);
		if (account.getId() == 0) {
			System.out.println("No Acccount found with ID# " + id);
			ctx.result("No Account found.");
			ctx.status(404);
		} else {
			System.out.println("Account found!");
			ctx.json(account);
			ctx.status(200);
		}
	};
	public Handler getAccountsWithBalance = (ctx) -> {
		int less = Integer.parseInt(ctx.queryParam("balanceLessThan"));
		int more = Integer.parseInt(ctx.queryParam("balanceGreaterThan"));
		System.out.println("We're looking for the accounts with a Balance that is less than " + less + " and greater than " + more);
		ArrayList<Account> bList = this.aserv.getAccountsWithBalance(less, more);
		ctx.json(bList);
		if (bList.size() == 0) {
			System.out.println("There are no valid Accounts here.");
			ctx.result("We aren't finding any Accounts with a Balance between " + less + " and " + more + " dollars.");
			ctx.status(404);
		} else {
			System.out.println("Here are the valid Accounts");
			ctx.json(bList);
			ctx.status(200);
		}
	};
	
	//Update
	public Handler updateAccount = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Account ID# " + id + " is going to be updated.");
		Account accountToUpdate = ctx.bodyAsClass(Account.class);
		System.out.println("Takting out " + accountToUpdate);
		Account updatedAccount = this.aserv.updateAccount(accountToUpdate, id);
		System.out.println("Putting out " + updatedAccount);
		if (updatedAccount.getId() == 0) {
			System.out.println("Doesn't seem to be an Account ID# " + id + " here.");
			ctx.result("Can't seem to find an Account with that ID.");
			ctx.status(404);
		} else {
			System.out.println("Account has been updated.");
			ctx.result("Account updated!");
			ctx.status(200);
		}
//		ps = conn.prepareStatement(updateAccount);
//		ps.setString(1, accountOne.getName());
//		ps.setInt(2, id);
//		ps.execute();
//		ctx.status(200);
//		ps.close();
	};
	public static Handler deleteAccount = (ctx) -> {
		String deleteClient = "DELETE FROM account WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
//		ps = conn.prepareStatement(deleteClient);
//		ps.setInt(1, id);
//		ps.execute();
//		ctx.status(200);
//		ps.close();
	};
	public Handler deposit = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " is trying to make a Deposit");
		Account accountDepositing = ctx.bodyAsClass(Account.class);
		this.aserv.deposit(accountDepositing, id);
//		String getAccountBalance = "SELECT account_balance FROM account WHERE account_id=?";
//		String updateAccountBalance = "UPDATE account SET account_balance=? WHERE account_id=?";
//		int id = Integer.parseInt(ctx.pathParam("id"));
//		Account amountToDeposit = ctx.bodyAsClass(Account.class);
//		Connection conn = ConnectionUtils.createConnection();
//		ps = conn.prepareStatement(getAccountBalance);
//		ps.setInt(1, id);
//		rs = ps.executeQuery();
//		int newBalance;
//		while (rs.next()) {
//			int currentBalance = rs.getInt("account_balance");
//			newBalance = currentBalance + amountToDeposit.getBalance();
//			ps = conn.prepareStatement(updateAccountBalance);
//			ps.setInt(1, newBalance);
//			ps.setInt(2, id);
//			ps.execute();
//			ctx.status(200);
//		}
//		rs.close();ps.close();
	};
	public Handler withdraw = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		System.out.println("Client ID# " + id + " is trying to make a Withdrawal.");
		Account accountWithdrawing = ctx.bodyAsClass(Account.class);
		this.aserv.withdraw(accountWithdrawing, id);
		
//		String getAccountBalance = "SELECT account_balance FROM account WHERE account_id=?";
//		String updateAccountBalance = "UPDATE account SET account_balance=? WHERE account_id=?";
//		Connection conn = ConnectionUtils.createConnection();
//		ps.setInt(1, id);
//		rs = ps.executeQuery();
//		int newBalance;
//		while (rs.next()) {
//			int currentBalance = rs.getInt("account_balance");
//			newBalance = currentBalance - amountToWithdraw.getBalance();
//			if (newBalance < 0) {
//				ctx.result("No... That's impossible!!");
//			} else {
//				ps = conn.prepareStatement(updateAccountBalance);
//				ps.setInt(1, newBalance);
//				ps.setInt(2, id);
//				ps.execute();
//				ctx.status(200);
//			}
//		}
//		rs.close();ps.close();
	};
}
