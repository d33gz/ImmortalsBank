package revature.d33gz.app;

import io.javalin.Javalin;
import revature.d33gz.controllers.AccountController;
import revature.d33gz.controllers.ClientController;
import revature.d33gz.dao.AccountDAO;
import revature.d33gz.dao.ClientDAO;
import revature.d33gz.dao.PostgresAccountDAO;
import revature.d33gz.dao.PostgresClientDAO;
import revature.d33gz.services.AccountService;
import revature.d33gz.services.AccountServiceImplement;
import revature.d33gz.services.ClientService;
import revature.d33gz.services.ClientServiceImplement;
import revature.d33gz.utilities.RandomIdGenerator;

public class App {
	public static void main(String[] args) {
		//Let's make our App
		Javalin app = Javalin.create();
		
		//Prepare our Layers
		AccountDAO adao = new PostgresAccountDAO();
		ClientDAO cdao = new PostgresClientDAO();
		RandomIdGenerator gen = new RandomIdGenerator();
		AccountService aserv = new AccountServiceImplement(adao, cdao, gen);
		ClientService cserv = new ClientServiceImplement(adao, cdao, gen);
		AccountController accountController = new AccountController(aserv);
		ClientController clientController = new ClientController(cserv);
		
		//Set up all of our Endpoints
		app.get("/", ctx -> {ctx.result("Welcome to Immortals Bank!");});
		app.post("/clients", clientController.addClient);
		app.get("/clients", clientController.getAllClients);
		app.get("/clients/{id}", clientController.getOneClient);
		app.put("/clients/{id}", clientController.updateClient);
		app.delete("/clients/{id}", clientController.deleteClient);
		app.get("/clients/{id}/accounts", accountController.getAllAccounts);
		app.post("/accounts/{id}", accountController.addAccount);
		app.get("/accounts/{id}", accountController.getOneAccount);
		app.get("/accounts", accountController.getAccountsWithBalance);
		app.put("/accounts/{id}", accountController.updateAccountName);
		app.patch("/accounts/{id}/deposit", accountController.deposit);
		app.patch("/accounts/{id}/withdraw", accountController.withdraw);
		app.delete("/accounts/{id}", accountController.deleteAccount);
		
		//Starting our Application
		app.start(1337);
	}
}