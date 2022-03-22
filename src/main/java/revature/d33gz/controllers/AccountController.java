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
}
