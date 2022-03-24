package revature.d33gz.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import revature.bank.entity.Account;
import io.javalin.http.Handler;
import revature.connection.ConnectionUtils;

public class AccountController {
	static PreparedStatement ps;
	static ResultSet rs;
	
	public static Handler addAccount = (ctx) -> {
		Account account = ctx.bodyAsClass(Account.class);
		int id = Integer.parseInt(ctx.pathParam("id"));
		String newAccount = "INSERT INTO account VALUES (?,?,?,?)";
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(newAccount);
		//WARNING ID SHOULD BE SERIALIZED BUT RIGHT NOW HAVE TO SUPPLY IT
		ps.setInt(1, account.getId());
		ps.setInt(2, id);
		ps.setString(3, account.getName());
		ps.setInt(4, account.getBalance());
		ps.execute();
		ctx.status(201);
		ps.close();
	};
	public static Handler getAllAccounts = (ctx) -> {
		String selectAllAccounts = "SELECT * FROM account WHERE account_owner=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(selectAllAccounts);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		ArrayList<Account> aList = new ArrayList<Account>();
		Account a;
		while (rs.next()) {
			int aId = rs.getInt("account_id");
			int oId = rs.getInt("account_owner");
			String aName = rs.getString("account_name");
			int aBal = rs.getInt("account_balance");
			a = new Account(aId, oId, aName, aBal);
			aList.add(a);
		}
		ctx.json(aList);
		rs.close();ps.close();
	};
	public static Handler getAccountsWithBalance = (ctx) -> {
		String selectAllAccountsWithBalanceOf = "SELECT * FROM account WHERE account_balance<? AND account_balance>?";
		int less = Integer.parseInt(ctx.queryParam("balanceLessThan"));
		int more = Integer.parseInt(ctx.queryParam("balanceGreaterThan"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(selectAllAccountsWithBalanceOf);
		ps.setInt(1, less);
		ps.setInt(2, more);
		rs = ps.executeQuery();
		ArrayList<Account> aList = new ArrayList<Account>();
		Account a;
		while (rs.next()) {
			int aId = rs.getInt("account_id");
			int oId = rs.getInt("account_owner");
			String aName = rs.getString("account_name");
			int aBal = rs.getInt("account_balance");
			a = new Account(aId, oId, aName, aBal);
			aList.add(a);
		}
		ctx.json(aList);
		rs.close();ps.close();
	};
	public static Handler getOneAccount = (ctx) -> {
		String selectOneAccount = "SELECT * FROM account WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(selectOneAccount);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		Account a;
		while (rs.next()) {
			int aId = rs.getInt("account_id");
			int oId = rs.getInt("account_owner");
			String aName = rs.getString("account_name");
			int aBal = rs.getInt("account_balance");
			a = new Account(aId, oId, aName, aBal);
			ctx.json(a);
		}
		rs.close();ps.close();
	};
	public static Handler updateAccount = (ctx) -> {
		String updateAccount = "UPDATE account SET account_name=? WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Account accountOne = ctx.bodyAsClass(Account.class);
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(updateAccount);
		ps.setString(1, accountOne.getName());
		ps.setInt(2, id);
		ps.execute();
		ctx.status(200);
		ps.close();
	};
	public static Handler deleteAccount = (ctx) -> {
		String deleteClient = "DELETE FROM account WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(deleteClient);
		ps.setInt(1, id);
		ps.execute();
		ctx.status(200);
		ps.close();
	};
	public static Handler deposit = (ctx) -> {
		String getAccountBalance = "SELECT account_balance FROM account WHERE account_id=?";
		String updateAccountBalance = "UPDATE account SET account_balance=? WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Account amountToDeposit = ctx.bodyAsClass(Account.class);
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(getAccountBalance);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int newBalance;
		while (rs.next()) {
			int currentBalance = rs.getInt("account_balance");
			newBalance = currentBalance + amountToDeposit.getBalance();
			ps = conn.prepareStatement(updateAccountBalance);
			ps.setInt(1, newBalance);
			ps.setInt(2, id);
			ps.execute();
			ctx.status(200);
		}
		rs.close();ps.close();
	};
	public static Handler withdraw = (ctx) -> {
		String getAccountBalance = "SELECT account_balance FROM account WHERE account_id=?";
		String updateAccountBalance = "UPDATE account SET account_balance=? WHERE account_id=?";
		int id = Integer.parseInt(ctx.pathParam("id"));
		Account amountToWithdraw = ctx.bodyAsClass(Account.class);
		Connection conn = ConnectionUtils.createConnection();
		ps = conn.prepareStatement(getAccountBalance);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int newBalance;
		while (rs.next()) {
			int currentBalance = rs.getInt("account_balance");
			newBalance = currentBalance - amountToWithdraw.getBalance();
			if (newBalance <= 0) {
				ctx.result("No... That's impossible!!");
			} else {
				ps = conn.prepareStatement(updateAccountBalance);
				ps.setInt(1, newBalance);
				ps.setInt(2, id);
				ps.execute();
				ctx.status(200);
			}
		}
		rs.close();ps.close();
	};
}
