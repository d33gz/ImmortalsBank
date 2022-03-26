package revature.d33gz.utilities;

public class RandomIdGenerator {
	public int randomIdGenerator() {
		int randomId = (int) Math.random() * 4000;
		if (randomId < 1000) {
			randomId += 1000;
		} else if (randomId > 4000) {
			randomId -= 1000;
		}
		return randomId;
	}
}
