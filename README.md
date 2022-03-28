Hello and Welcome to the Bank of the Immortals!

Our Clients are some of the most famous individuals from Mythology, History, and works of Fiction. They have earned a place in the Collective Culture of Humanity as True Legends. Here, at the Bank of the Immortals, we are proud to handle their Accounts, whether that be a Checking or Savings Account.

What can you do with our Service?

You can:

	* Put a new Client into our Database
	    POST /clients => Creates a new client
	      return a 201 status code
	* Get a list of all of our Clients.
	    GET /clients => gets all clients
		  return 200
	* Get a specific Client, such as one with an ID of 10
	    GET /clients/10 => get client with id of 10
	      return 404 if no such client exist
	* Update a specific Client, such as one with an ID of 12
	    PUT /clients/12 => updates client with id of 12	
	      return 404 if no such client exist
	* Delete a specific Client, such as one with an ID of 15
	    DELETE /clients/15 => deletes client with the id of 15
	      return 404 if no such client exist
	      return 205 if success
	* Posting a new Account for a specific Client, such as Client 5
	    POST /clients/5/accounts =>creates a new account for client with the id of 5
	      return a 201 status code
	* Getting all the Accounts for a specific Client, such as Client 7
	    GET /clients/7/accounts => get all accounts for client 7
	      return 404 if no client exists
	* Get a specific Account, such as one with an ID of 4
	    GET /accounts/4 => get account with id 4 
	      return 404 if no account or client exists
	* Update a specific Account, such as one with an ID of 3
	    PUT /accounts/3 => update account with the id 3
	      return 404 if no account or client exists
	* Delete a specific Account, such as one with an ID of 6
	    DELETE /accounts/6 => delete account 6 
	      return 404 if no account or client exists
	* Get all Accounts with a certain balance criteria
	    GET /accounts?amountLessThan=2000&amountGreaterThan400 => get all accounts for between 400 and 2000
	* Deposit a certain amount of money into an Account
	    PATCH /accounts/12/deposit => deposit given amount (Body {"amount":500} )
	      return 404 if no account exists
	* Withdraw a certain amount of money from an Account
	    PATCH /accounts/12/withdraw => withdraw given amount (Body {"amount":500} )
	      return 404 if no account exists
	      return 422 if insufficient funds


Things to Do

 	
	*** GetAllAccounts doesn't 404 well. It can't tell the difference between no Client and a Client with No Accounts
	*** Lot's of Issues finding Client ID for Account DAO Stuff. Make a Callable Statement maybe.
	
	** CreateAccount needs a way of handling Errors if not Client
	** Change CreateClient/Account to not use a Boolean
	** Create a Random Id Generator so I can POST a Client without worrying about duplicate ID.
	** Make sure 404 is correct for all Accounts Balance between two Numbers.
	** Add Validation in case two Queries aren't provided for all Accounts Balance between two Numbers.
	** Create Service Layer Tests.
	** Sweep away out dated Code.
	
	* Return the newly created Client as a JSON Object.
	* Ensure all Imports are in alphabetical order.

Files I am 100% happy with

	* revature.d33gz.app
		I: App.java
				This file includes everything needed to start the Application.
				It contains 4 sections,
					1) Create's a Javalin instance.
					2) Creation of Object Layers.
					3) All the Endpoints.
					4) Starts the Server.
					
	* revature.d33gz.daoTests
		I: ClientDAOTests.java
				This file includes 7 Test Cases (5 Happy Paths and 2 Sad Paths).
				All Tests are conducted on the DAO Layer corresponding to the Client Relation in the Database.
	* 
	
Files I am 90% happy with

	* revature.d33gz.daoTests
		I: AccountDAOTests.java
				This file includes 6 Test Cases (6 Happy Paths and 0 Sad Paths).
				All Tests are conducted on the DAO Layer corresponding to the Account Relation in the Database.
				There are no Tests for Withdraw or Deposit as their logic is handled on the Service Layer. Really they are UpdateAccount now that I think about it, and can probably be deleted.
				There are no Sad Paths.
