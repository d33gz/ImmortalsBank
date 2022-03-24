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
	public ArrayList<Account> getAllAccounts(int id){
		ArrayList<Account> aList = new ArrayList<Account>();
		try(Connection conn = ConnectionUtils.createConnection();) {
			String selectAllAccounts = "SELECT * FROM account WHERE account_owner=?";
			ps = conn.prepareStatement(selectAllAccounts);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Account a;
			while (rs.next()) {
				int aId = rs.getInt("account_id");
				int oId = rs.getInt("account_owner");
				String aName = rs.getString("account_name");
				int aBal = rs.getInt("account_balance");
				a = new Account(aId, oId, aName, aBal);
				aList.add(a);
			}
			rs.close();ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return aList;
	}
	public Account getOneAccount(int id) {
		Account a = new Account();
		try(Connection conn = ConnectionUtils.createConnection();) {
			String selectOneAccount = "SELECT * FROM account WHERE account_id=?";
			ps = conn.prepareStatement(selectOneAccount);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int aId = rs.getInt("account_id");
				int oId = rs.getInt("account_owner");
				String aName = rs.getString("account_name");
				int aBal = rs.getInt("account_balance");
				a = new Account(aId, oId, aName, aBal);
			}
			rs.close();ps.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	public ArrayList<Account> getAccountsWithBalance(int less, int more) {
		ArrayList<Account> bList = new ArrayList<Account>();
		try(Connection conn = ConnectionUtils.createConnection();) {
			String selectAllAccountsWithBalanceOf = "SELECT * FROM account WHERE account_balance<? AND account_balance>?";
			ps = conn.prepareStatement(selectAllAccountsWithBalanceOf);
			ps.setInt(1, less);
			ps.setInt(2, more);
			rs = ps.executeQuery();
			Account a;
			while (rs.next()) {
				int aId = rs.getInt("account_id");
				int oId = rs.getInt("account_owner");
				String aName = rs.getString("account_name");
				int aBal = rs.getInt("account_balance");
				a = new Account(aId, oId, aName, aBal);
				bList.add(a);
			}
			rs.close();ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bList;
	}
}
