package revature.d33gz.app;
import io.javalin.Javalin;
import revature.d33gz.controllers.AccountController;
import revature.d33gz.controllers.ClientController;

public class Main {
	public static void main(String[] args) {
		String url = System.getenv("MY_DB_CONNECTION");
		
		Javalin app = Javalin.create().start();
		app.get("/", ctx -> {ctx.result("Welcome to Immortals Bank!");});
		app.get("/allClients", ClientController.getAllClients);
		app.get("/clients/{id}", ClientController.getOneClient);
		app.put("/clients/{id}", ClientController.updateClient);
		app.post("/clients", ClientController.addClient);
		app.delete("/clients/{id}", ClientController.deleteClient);
		app.post("/accounts/{id}", AccountController.addAccount);
		app.get("/clients/{id}/accounts", AccountController.getAllAccounts);
		app.get("/accounts", AccountController.getAccountsWithBalance);
		app.get("/accounts/{id}", AccountController.getOneAccount);
		app.put("/accounts/{id}", AccountController.updateAccount);
		app.delete("/accounts/{id}", AccountController.deleteAccount);
		app.patch("/accounts/{id}/deposit", AccountController.deposit);
		app.patch("/accounts/{id}/withdraw", AccountController.withdraw);
	}
}