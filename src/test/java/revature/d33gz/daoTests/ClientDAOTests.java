package revature.d33gz.daoTests;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import revature.d33gz.dao.ClientDAO;
import revature.d33gz.dao.PostgresClientDAO;
import revature.d33gz.entity.Client;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDAOTests {
	private static ClientDAO cdao = new PostgresClientDAO();
	private static Client testClient = null;
	
	//Do this to make sure the DB is empty before starting Tests
	@AfterAll
	static void cleanseDB() {
		cdao.deleteClient(207);
	}
	
	//Happy Paths
	@Test
	@Order(1)
	void createClientTest() {
		Client jf = new Client(207, "Joey Fatone");
		boolean testBoolean = cdao.createClient(jf);
		ClientDAOTests.testClient = jf;
		Assertions.assertEquals(true, testBoolean);
	}
	@Test
	@Order(2)
	void getAllClientsTest() {
		ArrayList<Client> emptyList = new ArrayList<Client>();
		ArrayList<Client> fullList = new ArrayList<Client>();
		fullList = cdao.getAllClients();
		Assertions.assertNotEquals(emptyList, fullList);
	}
	@Test
	@Order(3)
	void getOneClientTest() {
		Client jf = cdao.getOneClient(testClient.getId());
		Assertions.assertEquals("Joey Fatone", jf.getName());
	}
	@Test
	@Order(4)
	void updateClientTest() {
		Client jf = cdao.getOneClient(testClient.getId());
		String updateName = "Slick Rick";
		jf.setName(updateName);
		cdao.updateClient(jf, testClient.getId());
		jf = cdao.getOneClient(testClient.getId());
		Assertions.assertEquals(updateName, jf.getName());
	}
	@Test
	@Order(5)
	void deleteClient() {
		cdao.deleteClient(testClient.getId());
		Client expected = cdao.getOneClient(testClient.getId());
		Client doctorNull = new Client(0, null);
		Assertions.assertEquals(doctorNull.toString(), expected.toString());
	}
	
	//Sad Paths
	@Test
	@Order(6)
	void badClientCreateTest() {
		Client jf = new Client(207, "Joey Fatone");
		cdao.createClient(jf);
		Assertions.assertEquals(false, cdao.createClient(jf));
	}
	@Test
	@Order(7)
	void getOneClientWithNoIdTest() {
		Client jf = cdao.getOneClient(702);
		Assertions.assertEquals(null, jf.getName());
	}
}
