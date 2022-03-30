package revature.d33gz.utilities;

public class RandomIdGenerator {
	public int randomIdGenerator(String type) {
		int randomId = 0;
		if (type.equals("Client")) {
			randomId = (int) (Math.random() * (4000 - 1000) + 1000);
			if (randomId < 1000) {
				randomId += 1000;
			} else if (randomId > 4000) {
				randomId -= 1000;
			}
		} else if (type.equals("Account")){
			 randomId = (int) (Math.random() * (9000 - 6000) + 6000);
			if (randomId < 6000) {
				randomId += 1000;
			} else if (randomId > 9000) {
				randomId -= 1000;
			}
		}
		return randomId;
	}
}
	