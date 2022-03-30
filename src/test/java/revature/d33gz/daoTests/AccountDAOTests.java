package revature.d33gz.daoTests;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import revature.d33gz.dao.AccountDAO;
import revature.d33gz.dao.ClientDAO;
import revature.d33gz.dao.PostgresAccountDAO;
import revature.d33gz.dao.PostgresClientDAO;
import revature.d33gz.entity.Account;
import revature.d33gz.entity.Client;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDAOTests {
	private static AccountDAO adao = new PostgresAccountDAO();
	private static ClientDAO cdao = new PostgresClientDAO();
	private static Account testAccount = null;
	private static Client jf = new Client(207, "Joey Fatone");
	
	//Make sure the DB is empty of Test Data before starting Tests
	@AfterAll
	static void cleanseDB() {
		adao.deleteAccount(702);
		adao.deleteAccount(703);
		cdao.deleteClient(jf.getId());
	}
	
	//Create a Test Client
	@BeforeAll
	static void createTestClient() {
		cdao.createClient(jf);
	}
	
	//Happy Paths
	@Test
	@Order(1)
	void createAccountTest() {
		Account jfa = new Account(702, 207, "Checking", 777);
		boolean testBoolean = adao.createAccount(jfa, jfa.getOwner());
		AccountDAOTests.testAccount = jfa;
		Assertions.assertEquals(true, testBoolean);
	}
	@Test
	@Order(2)
	void getAllAccountsForClientTest() {
		Account jfa2 = new Account(703, 207, "Saving", 1500);
		adao.createAccount(jfa2, jfa2.getOwner());
		ArrayList<Account> jfal = adao.getAllAccountsForClient(testAccount.getOwner());
		Assertions.assertEquals(jfal.size(), 2);
	}
	@Test
	@Order(3)
	void getOneAccountTest() {
		Account foundAccount = adao.getOneAccount(testAccount.getId());
		Assertions.assertEquals(foundAccount.toString(), testAccount.toString());
	}
	@Test
	@Order(4)
	void getAccountsWithBalanceTest() {
		ArrayList<Account> foundAccounts = adao.getAccountsWithBalance(778, 776);
		Assertions.assertEquals(1, foundAccounts.size());
	}
	
	@Test
	@Order(5)
	void updateAccountNameTest() {
		Account updateAccount = new Account(702, 207, "Investment", 777);
		adao.updateAccountName(updateAccount, testAccount.getId());
		Assertions.assertEquals(updateAccount.toString(), adao.getOneAccount(testAccount.getId()).toString());
	}
	@Test
	@Order(6)
	void updateAccountBalanceTest() {
		int updatedBalance = 707070;
		adao.updateAccountBalance(updatedBalance, testAccount.getId());
		Assertions.assertEquals(updatedBalance, adao.getOneAccount(testAccount.getId()).getBalance());
	}
	@Test
	@Order(7)
	void deleteAccountTest() {
		adao.deleteAccount(testAccount.getId());
		Account expected = adao.getOneAccount(testAccount.getId());
		Account nullAccount = new Account(0, 0, null, 0);
		Assertions.assertEquals(nullAccount.toString(), expected.toString());
	}
}
