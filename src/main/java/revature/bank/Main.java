package revature.bank;
import io.javalin.Javalin;
import revature.d33gz.controllers.AccountController;
import revature.d33gz.controllers.ClientController;

public class Main {
	public static void main(String[] args) {
		String url = System.getenv("MY_DB_CONNECTION");
		System.out.println(url);
		
		Javalin app = Javalin.create().start();
		app.get("/", ctx -> {ctx.result("Welcome to Immortals Bank!");});
		/*All the Stories that are done
		 *	GET all clients
		 */		
		//app.get("/clients", Handlers.getClients); OUTDATED
		app.get("/allClients", ClientController.getAllClients);
		
		/*All the Stories that are Halfway Done
		 * GET client id 10 (404 if no client)
		 * PUT updates client id 12 (404 if no client)
		 * POST a new client ()
		 * DELETE deletes client id 15 (404 if no client, 205 if success)
		 * POST create new account for client id 5 (201 if success)
		 * GET all accounts for client 7
		 */
		//app.get("/clients/{num}", Handlers.getOneClient); OUTDATED
		app.get("/allClients/{id}", ClientController.getOneClient);
		//app.put("/clients/{num}/{name}", Handlers.updateClientName); OUTDATED
		app.put("/allClients/{id}", ClientController.updateClient);
		//app.post("/clients/new/{name}", Handlers.postClient); OUTDATED
		app.post("/allClients", ClientController.addClient);
		app.delete("/allClients/{id}", ClientController.deleteClient);
		app.post("/accounts/{id}", AccountController.addAccount);
		 app.get("/clients/{id}/accounts", AccountController.getAllAccounts);
		/*All the Stories that are Not Done


		
		 *  
		 *  OPTIONAL
		 *  GET all accounts balance between 400 and 2000
		 *  app.get("/accounts?amountLessThan=2000&amountGreaterThan400", ctx -> {});
		 *  GET account id 4
		 *  app.get("/accounts/4", ctx -> {});
		 *  PUT update account with id 3 (404 if no client)
		 *  app.put("/accounts/3", ctx -> {});
		 *  DELETE account 6 (404 if no client)
		 *  app.delete("/accounts/6", ctx -> {});
		 *  PATCH deposit given amount (Body {"amount":500)) (404 if no client)
		 *  app.patch("/accounts/12/deposit", ctx -> {});
		 *  PATCH withdraw given amount (Body {"amount":500)) (404 if no client, 422 if insufficient funds)
		 *  app.patch("/accounts/12/withdraw", ctx -> {});
		 */

		//Endpoints Testing Ground

	}
}
