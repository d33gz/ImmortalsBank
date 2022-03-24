package revature.d33gz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import revature.d33gz.connection.ConnectionUtils;
import revature.d33gz.entity.Account;

public class PostgresAccountDAO implements AccountDAO {
	PreparedStatement ps;
	ResultSet rs;
	
	public Account createAccount(Account account, int id) {
		try (Connection conn = ConnectionUtils.createConnection();) {
			String newAccount = "INSERT INTO account VALUES (?,?,?,?)";
			
			ps = conn.prepareStatement(newAccount);
			//WARNING ID SHOULD BE SERIALIZED BUT RIGHT NOW HAVE TO SUPPLY IT
			ps.setInt(1, account.getId());
			ps.setInt(2, id);
			ps.setString(3, account.getName());
			ps.setInt(4, account.getBalance());
			ps.execute();
			//ctx.status(201);
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public ArrayList<Account> getAllAccounts(){
		ArrayList<Account> aList = new ArrayList<Account>();
		return aList;
	};
	
	public Account getOneAccount(int id) {
		Account a = new Account();
		return a;
	};
	
}