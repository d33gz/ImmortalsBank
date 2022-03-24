package revature.d33gz.connection;
import revature.queries.GetAll;
import revature.queries.Get;
import revature.queries.Put;
import revature.queries.Post;

public class DBConnection {
	public static void main(String[] args) {
		//Let's see all the Clients
		System.out.println(GetAll.gettingAll());
		//Let's get all 4 Clients Individually
		System.out.println("====== Getting a Client ======");
		System.out.println(Get.getClient(1));
		System.out.println("====== Getting another Client ======");
		System.out.println(Get.getClient(2));
		System.out.println("====== Getting another Client ======");
		System.out.println(Get.getClient(3));
		System.out.println("====== Getting yet one more Client ======");
		System.out.println(Get.getClient(4));
		System.out.println("====== Updating a Client ======");
		//Put.updateClient(1, "Cloud Strife");
		System.out.println("====== Posting a new Client ======");
		Post.postClient("Barret Wallace");
	}
}