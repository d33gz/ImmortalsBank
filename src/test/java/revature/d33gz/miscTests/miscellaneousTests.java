package revature.d33gz.miscTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import revature.d33gz.utilities.RandomIdGenerator;

public class miscellaneousTests {
	private static RandomIdGenerator gen = new RandomIdGenerator();
	
	@Test
	void ClientIdGeneratorTest() {
		int[] clientIds = new int[25];
		for (int i = 0; i < clientIds.length; i++) {
			clientIds[i] = gen.randomIdGenerator("Client");
			System.out.println(clientIds[i]);
		}
		for (int i = 0; i < clientIds.length; i++) {
			Assertions.assertTrue(clientIds[i] >= 1000 && clientIds[i] <= 4000);
		}
	}
	@Test
	void AccountIdGeneratorTest() {
		int[] accountIds = new int[25];
		for (int i = 0; i < accountIds.length; i++) {
			accountIds[i] = gen.randomIdGenerator("Account");
			System.out.println(accountIds[i]);
		}
		for (int i = 0; i < accountIds.length; i++) {
			Assertions.assertTrue(accountIds[i] >= 6000 && accountIds[i] <= 9000);
		}
	}
}
